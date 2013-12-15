package com.digiarea.closure.help.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import netscape.javascript.JSObject;

import com.digiarea.closure.help.model.HelpTopic;
import com.digiarea.closure.help.providers.HelpProviderFactory;
import com.digiarea.closure.help.providers.IHelpProvider;
import com.digiarea.closurefx.ResourceUtils;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class HelpController implements Initializable {

	@FXML
	private VBox container;

	private Stage stage;
	private Helpwalker helpwalker;

	@FXML
	private TreeView<HelpTopic> controlTree;

	private TreeItem<HelpTopic> root;
	private HelpTopic help;

	@FXML
	private WebView controlWebview;
	private WebEngine webEngine;

	@FXML
	private Button btnUpdate;

	@FXML
	private SplitPane controlSplit;

	@FXML
	private ProgressBar controlProgress;

	private URL initialURL;

	public void setInitialURL(URL initialURL, String link) {
		this.initialURL = initialURL;
		if (this.initialURL != null) {
			webEngine.load(this.initialURL.toString() + link);
		}
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		webEngine = controlWebview.getEngine();
		controlProgress.progressProperty().bind(
				webEngine.getLoadWorker().progressProperty());

		helpwalker = new Helpwalker();
		IHelpProvider helpProvider = HelpProviderFactory.getHelp();

		help = helpProvider.getHelp();
		root = new TreeItem<HelpTopic>(help);
		helpwalker.walk(help, root);
		controlTree.setRoot(root);

		controlTree.setShowRoot(false);
		controlTree
				.setCellFactory(new Callback<TreeView<HelpTopic>, TreeCell<HelpTopic>>() {
					@Override
					public TreeCell<HelpTopic> call(TreeView<HelpTopic> p) {
						return new HelpCell();
					}
				});

		controlTree.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<TreeItem<HelpTopic>>() {
					@Override
					public void changed(
							ObservableValue<? extends TreeItem<HelpTopic>> observable,
							TreeItem<HelpTopic> oldValue,
							TreeItem<HelpTopic> newValue) {
						if (newValue != null) {
							if (newValue.getValue().getLink() != null) {
								webEngine.load(newValue.getValue().getLink()
										.toString());
							}
						}
					}
				});

		loadHome();
	}

	private double lastWidth = 0;

	public void setStage(Stage stage) {
		this.stage = stage;
		Scene scene = this.stage.getScene();
		if (scene != null) {
			scene.getAccelerators().put(new KeyCodeCombination(KeyCode.F5),
					new Runnable() {
						@Override
						public void run() {
							btnUpdate.fireEvent(new ActionEvent());
						}
					});
		}
		lastWidth = scene.getWidth();
		controlSplit.getDividers().get(0).positionProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
						if (lastWidth != getStage().getScene().getWidth()) {
							controlSplit.getDividers().get(0).setPosition(0.25);
							lastWidth = getStage().getScene().getWidth();
						}
					}
				});
	}

	public Stage getStage() {
		return stage;
	}

	private class HelpCell extends TreeCell<HelpTopic> {

		public HelpCell() {
			setOnMouseExited(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					setCursor(Cursor.DEFAULT);
				}
			});
			setOnMouseMoved(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					if (!isEmpty()) {
						setCursor(Cursor.HAND);
					}
				}
			});

		}

		@Override
		public void updateItem(HelpTopic item, boolean empty) {
			super.updateItem(item, empty);
			if (isEmpty() || item == null) {
				setText(null);
				setGraphic(null);
				setTooltip(null);
			} else {
				setText(item.getName());
				setTooltip(new Tooltip(item.getName()));
				setGraphic(getGraphics());
			}
		}

		private ImageView getGraphics() {
			if (getItem().isRoot()) {
				return new ImageView(ResourceUtils.HELP_HELP);
			}
			if (getItem().hasChildren()) {
				return new ImageView(ResourceUtils.HELP_BOOK);
			}
			return new ImageView(ResourceUtils.SIMPLE_FILE);
		}

	}

	@FXML
	private void handleBackButtonAction(ActionEvent event) {
		JSObject history = (JSObject) webEngine.executeScript("history");
		history.call("back");
	}

	@FXML
	private void handleForwardButtonAction(ActionEvent event) {
		JSObject history = (JSObject) webEngine.executeScript("history");
		history.call("forward");
	}

	@FXML
	private void handleHomeButtonAction(ActionEvent event) {
		loadHome();
	}

	private void loadHome() {
		webEngine.load(help.getLink().toString());
	}

	@FXML
	private void handleUpdateButtonAction(ActionEvent event) {
		controlWebview.getEngine().reload();
	}

	private class Helpwalker {

		public Helpwalker() {
		}

		public void walk(HelpTopic topics, TreeItem<HelpTopic> tree) {
			for (HelpTopic topic : topics.getChildren()) {
				TreeItem<HelpTopic> currentTopic = new TreeItem<HelpTopic>(
						topic);
				tree.getChildren().add(currentTopic);
				if (topic.hasChildren()) {
					walk(topic, currentTopic);
				}
			}
		}
	}

}
