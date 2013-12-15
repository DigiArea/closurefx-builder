package com.digiarea.closurefx;

import java.io.File;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.digiarea.closure.help.model.controller.HelpController;
import com.digiarea.closure.help.model.controller.HelpFactory;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.preferences.model.controller.PreferencesFactory;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;

/**
 * 
 * @author norb
 */
public class ClosureFXController implements Initializable {

	private Stage stage;
	private ResourceBundle bundle;
	private DocumentManager documentManager;
	private TooltipManager tooltipManager;

	@FXML
	private TabPane docTabPane;
	@FXML
	private Button newButton;
	@FXML
	private Button openButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button runButton;
	@FXML
	private VBox controlTooltips;

	public static ClosureFXController APPLICATION;
	private HelpController help;

	public ClosureFXController() {
		APPLICATION = this;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bundle = rb;
		documentManager = new DocumentManager();
		tooltipManager = new TooltipManager(controlTooltips);
	}

	@FXML
	private void handleAboutButtonAction(ActionEvent event) {
		DialogFactory.getAboutDialog(bundle,
				bundle.getString(IConstants.About_Title));
	}

	@FXML
	private void handleHelpContentsButtonAction(ActionEvent event) {
		openHelp();
	}

	public HelpController openHelp() {
		if (help == null) {
			help = HelpFactory.getHelp(bundle, IConstants.Help_Title);
		}
		help.getStage().show();
		return help;
	}

	@FXML
	private void handleRunButton(ActionEvent event) {
		Document document = getDocument(getActiveTab());
		if (document != null) {
			document.getModelFacade().getJsConsole().start();
			document.getModelFacade().getSoyConsole().start();
			document.getModelFacade().getGssConsole().start();
		}
	}

	@FXML
	private void handleDuplicateButtonAction(ActionEvent event) {
		Tab activeTab = getActiveTab();
		if (activeTab != null) {
			Document document = getDocument(activeTab);
			if (document != null) {
				try {
					Closure closure = (Closure) document.getClosure().clone();
					openNewTab(null, closure);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	private void handleSettingsButtonAction(ActionEvent event) {
		PreferencesFactory.getPreferenceDialog(bundle);
	}

	@FXML
	private void handleNewButtonAction(ActionEvent event) {
		openNewTab(null, null);
	}

	@FXML
	private void handleOpenButtonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter mwFilter = new FileChooser.ExtensionFilter(
				IConstants.CLOSURE_BUILD_FILE, IConstants.EXTENSION__CLOSURE);
		fileChooser.getExtensionFilters().add(mwFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			openNewTab(file, null);
		}
	}

	@FXML
	private void handleSaveButtonAction(ActionEvent event) {
		Document document = getDocument(getActiveTab());
		IStatus status = documentManager.saveWithoutPromt(document, bundle);
		if (document != null && status.getSeverity() == StatusType.OK) {
			tooltipManager.addTooltip(new Status(StatusType.WARNING,
					MessageFormat.format(
							bundle.getString(IConstants.TooltipMsg_Saved),
							document.getName()), null));
		}
	}

	@FXML
	private void handleSaveAllButtonAction(ActionEvent event) {
		for (Tab tab : docTabPane.getTabs()) {
			Document document = getDocument(tab);
			documentManager.saveDocument(document, bundle);
		}

	}

	@FXML
	private void handleExitButtonAction(ActionEvent event) {
		Iterator<Tab> iterator = docTabPane.getTabs().iterator();
		while (iterator.hasNext()) {
			Tab tab = iterator.next();
			Document document = getDocument(tab);
			IStatus result = documentManager.saveDocument(document, bundle);
			// process result
			if (result.getSeverity() != StatusType.CANCEL) {
				iterator.remove();
				documentManager.removeDocument(document);
			} else {
				return;
			}
		}
		stage.close();
	}

	@FXML
	private void handleCloseButtonAction(ActionEvent event) {
		Tab tab = getActiveTab();
		if (tab != null) {
			closeTab(tab, getDocument(tab));
		}
	}

	@FXML
	private void handleCloseAllButtonAction(ActionEvent event) {
		Iterator<Tab> iterator = docTabPane.getTabs().iterator();
		while (iterator.hasNext()) {
			Tab tab = iterator.next();
			Document document = getDocument(tab);
			IStatus result = documentManager.saveDocument(document, bundle);
			// process result
			if (result.getSeverity() != StatusType.CANCEL) {
				iterator.remove();
				documentManager.removeDocument(document);
			} else {
				return;
			}
		}
	}

	private void openNewTab(File file, Closure closure) {
		final Document document = new Document(file);
		document.setClosure(closure);
		document.setBundle(bundle);
		document.setName(getNewName(file));

		boolean result = documentManager.addDocument(document);
		// if file is already open
		if (!result) {
			Tab existedTab = docTabPane.getTabs().get(
					documentManager.getIndex(document));
			docTabPane.getSelectionModel().select(existedTab);
			tooltipManager.addTooltip(new Status(StatusType.WARNING, bundle
					.getString(IConstants.TooltipMsg_DocumentExist), null));
		} else {
			// MAKE NEW TAB
			final Tab tab = new Tab(document.getName());
			tab.setClosable(false);
			tab.setGraphic(new ImageView(ResourceUtils.CLOSURE_ICON));
			tab.setTooltip(new Tooltip(getTooltip(file)));

			document.nameProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(
						ObservableValue<? extends String> observable,
						String oldValue, String newValue) {
					tab.setText(newValue);
				}
			});

			// document.dirtyProperty().addListener(new
			// ChangeListener<Boolean>() {
			// @Override
			// public void changed(
			// ObservableValue<? extends Boolean> observable,
			// Boolean oldValue, Boolean newValue) {
			// if (newValue) {
			// tab.getStyleClass().add(IConstants.CSS_TAB_BOLD);
			// } else {
			// tab.getStyleClass().add(IConstants.CSS_TAB_NORMAL);
			// }
			// }
			// });

			// CLOSURE EDITOR
			TabPane editor = document.load();

			Button closeButton = new Button();
			closeButton.setGraphic(new ImageView(ResourceUtils.BUTTON_CLOSE));
			closeButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			closeButton.getStyleClass().addAll(IConstants.CSS_INVISIBLE_BUTTON,
					IConstants.CSS_CLOSE_BUTTON);
			// Handler for the close button.
			closeButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent paramT) {
					closeTab(tab, document);
				}
			});
			tab.setGraphic(closeButton);

			if (editor != null) {
				editor.setMinHeight(Control.USE_COMPUTED_SIZE);
				editor.setMinWidth(Control.USE_COMPUTED_SIZE);
				editor.setPrefHeight(Control.USE_COMPUTED_SIZE);
				editor.setPrefWidth(Control.USE_COMPUTED_SIZE);
				editor.setMaxHeight(1.7976931348623157E308);
				editor.setPrefWidth(Control.USE_COMPUTED_SIZE);
				HBox.setHgrow(editor, Priority.ALWAYS);
				VBox.setVgrow(editor, Priority.ALWAYS);
				VBox pane = new VBox();
				pane.setMinHeight(Control.USE_COMPUTED_SIZE);
				pane.setMinWidth(Control.USE_COMPUTED_SIZE);
				pane.setPrefHeight(Control.USE_COMPUTED_SIZE);
				pane.setPrefWidth(Control.USE_COMPUTED_SIZE);
				pane.setMaxHeight(Control.USE_COMPUTED_SIZE);
				pane.setMinHeight(Control.USE_COMPUTED_SIZE);
				pane.getChildren().add(editor);
				tab.setContent(pane);
			}

			docTabPane.getTabs().add(tab);
			docTabPane.getSelectionModel().select(tab);
		}
	}

	private String getNewName(File file) {
		if (file != null) {
			return file.getName();
		} else {
			return getNewName();
		}
	}

	public String getTooltip(File file) {
		if (file != null) {
			return file.getAbsolutePath();
		} else {
			return getNewName();
		}
	}

	private String getNewName() {
		return MessageFormat.format(bundle.getString(IConstants.Tab_Untitled),
				documentManager.getDocumentsCount());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Iterator<Tab> iterator = docTabPane.getTabs().iterator();
				while (iterator.hasNext()) {
					Tab tab = iterator.next();
					Document document = getDocument(tab);
					IStatus result = documentManager.saveDocument(document,
							bundle);
					// process result
					if (result.getSeverity() != StatusType.CANCEL) {
						iterator.remove();
						documentManager.removeDocument(document);
					} else {
						event.consume();
						return;
					}
				}
			}
		});
		Scene scene = this.stage.getScene();
		if (scene != null) {
			scene.getAccelerators().put(
					new KeyCodeCombination(KeyCode.R,
							KeyCombination.CONTROL_DOWN), new Runnable() {
						@Override
						public void run() {
							runButton.fireEvent(new ActionEvent());
						}
					});
		}
	}

	private IStatus closeTab(Tab tab, Document document) {
		IStatus result = documentManager.saveDocument(document, bundle);
		if (result.getSeverity() != StatusType.CANCEL) {
			docTabPane.getTabs().remove(tab);
			documentManager.removeDocument(document);
		}
		return result;
	}

	private Tab getActiveTab() {
		final ObservableList<Tab> tabs = docTabPane.getTabs();
		if (!tabs.isEmpty()) {
			for (Tab tab : tabs) {
				if (tab.isSelected()) {
					return tab;
				}
			}
		}
		return null;
	}

	private Document getDocument(Tab tab) {
		int index = docTabPane.getTabs().indexOf(tab);
		return documentManager.getDocument(index);
	}
}
