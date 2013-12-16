package com.digiarea.closure.model.controller;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import com.digiarea.closure.help.model.controller.HelpController;
import com.digiarea.closure.help.providers.HelpProvider;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.providers.ConsoleCellFactory;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closurefx.ClosureFXController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.console.JSCClosureStatus;
import com.digiarea.closurefx.build.console.JSCConsoleManager;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;
import com.digiarea.closurefx.cli.EditorLoader;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSConsoleSectionController extends ClosureController implements
		Initializable, IConsole {

	@FXML
	private Tab tabProblems;

	@FXML
	private Tab tabMessages;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TableView<ClosureStatus> controlProblems;

	@FXML
	private ListView<Status> controlConsole;

	@FXML
	private TableColumn<ClosureStatus, IStatus.StatusType> controlProblemsLevel;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsDescription;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsKey;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsResource;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsPath;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsLocation;

	@FXML
	private TableColumn<ClosureStatus, String> controlProblemsType;

	@FXML
	private Label controlWarnings;

	@FXML
	private Label controlErrors;

	private ObservableList<ClosureStatus> errors;

	private ObservableList<Status> messages;

	public JSConsoleSectionController(ModelFacade modelFacade,
			ResourceBundle bundle) {
		super(modelFacade, bundle);
		modelFacade.setJsConsole(new JSCConsoleManager(this, modelFacade
				.getClosure(), bundle, modelFacade.getDocument()
				.getPathResolver()));
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		controlErrors
				.setText(MessageFormat.format(
						bundle.getString(IConstants.ConsoleErrors),
						Integer.toString(0)));
		controlWarnings.setText(MessageFormat.format(
				bundle.getString(IConstants.ConsoleWarnings),
				Integer.toString(0)));
		tabProblems.setText(MessageFormat.format(
				bundle.getString(IConstants.ProgressDialog_Problems),
				Integer.toString(0)));
		errors = FXCollections.observableArrayList();
		messages = FXCollections.observableArrayList();
		controlProblems.setItems(errors);
		controlConsole.setItems(messages);
		errors.addListener(new ListChangeListener<ClosureStatus>() {

			@SuppressWarnings("incomplete-switch")
			@Override
			public void onChanged(
					ListChangeListener.Change<? extends ClosureStatus> c) {
				int errorsInt = 0;
				int warningsInt = 0;
				for (ClosureStatus error : errors) {
					IStatus.StatusType checkLevel = error.getSeverity();
					switch (checkLevel) {
					case ERROR:
						errorsInt++;
						break;
					case OFF:
						break;
					case WARNING:
						warningsInt++;
						break;
					}
				}
				controlErrors.setText(MessageFormat.format(
						bundle.getString(IConstants.ConsoleErrors),
						Integer.toString(errorsInt)));
				controlWarnings.setText(MessageFormat.format(
						bundle.getString(IConstants.ConsoleWarnings),
						Integer.toString(warningsInt)));
				tabProblems.setText(MessageFormat.format(
						bundle.getString(IConstants.ProgressDialog_Problems),
						Integer.toString(warningsInt + errorsInt)));
			}
		});
		controlConsole.setCellFactory(new ConsoleCellFactory());
		controlProblemsLevel
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, IStatus.StatusType>, ObservableValue<IStatus.StatusType>>() {

					@Override
					public ObservableValue<IStatus.StatusType> call(
							TableColumn.CellDataFeatures<ClosureStatus, IStatus.StatusType> p) {
						return new SimpleObjectProperty<IStatus.StatusType>(
								(IStatus.StatusType) p.getValue().getSeverity());
					}
				});
		controlProblemsLevel
				.setCellFactory((new Callback<TableColumn<ClosureStatus, IStatus.StatusType>, TableCell<ClosureStatus, IStatus.StatusType>>() {

					@Override
					public TableCell<ClosureStatus, IStatus.StatusType> call(
							TableColumn<ClosureStatus, IStatus.StatusType> param) {
						TableCell<ClosureStatus, IStatus.StatusType> cell = new TableCell<ClosureStatus, IStatus.StatusType>() {

							@SuppressWarnings("incomplete-switch")
							@Override
							public void updateItem(IStatus.StatusType item,
									boolean empty) {
								if (item != null) {
									switch (item) {
									case ERROR:
										setGraphic(new ImageView(
												ResourceUtils.MARK_ERROR));
										break;
									case OFF:
										setGraphic(new ImageView(
												ResourceUtils.MARK_OFF));
										break;
									case WARNING:
										setGraphic(new ImageView(
												ResourceUtils.MARK_WARNING));
										break;
									}
								}
							}
						};
						return cell;
					}
				}));
		controlProblemsDescription
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null) {
							return new SimpleStringProperty(p.getValue()
									.getMessage());
						} else {
							return new SimpleStringProperty("<no description>");
						}
					}
				});
		controlProblemsDescription
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.TextCell();
					}
				});
		controlProblemsKey
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null
								&& p.getValue() instanceof JSCClosureStatus) {
							JSCClosureStatus status = (JSCClosureStatus) p
									.getValue();
							return new SimpleStringProperty(status
									.getDiagnosticType().key);
						} else {
							return new SimpleStringProperty("<no type>");
						}
					}
				});
		controlProblemsKey
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.KeyCell();
					}
				});
		controlProblemsResource
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null
								&& p.getValue().getResource() != null) {
							String path = p.getValue().getResource();
							String resource = path;
							if (path.contains("/")) {
								String[] splits = path.split("/");
								resource = splits[splits.length - 1];
							} else if (path.contains("\\")) {
								String[] splits = path.split("\\\\");
								resource = splits[splits.length - 1];
							}
							return new SimpleStringProperty(resource);
						} else {
							return new SimpleStringProperty("<no resource>");
						}
					}
				});
		controlProblemsResource
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.TextCell();
					}
				});
		controlProblemsPath
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null) {
							return new SimpleStringProperty(p.getValue()
									.getResource());
						} else {
							return new SimpleStringProperty("<no path>");
						}
					}
				});
		controlProblemsPath
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.TextCell();
					}
				});
		controlProblemsLocation
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null) {
							return new SimpleStringProperty("line "
									+ Integer.toString(p.getValue().getLine()));
						} else {
							return new SimpleStringProperty("<no line>");
						}
					}
				});
		controlProblemsLocation
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.TextCell();
					}
				});
		controlProblemsType
				.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(
							TableColumn.CellDataFeatures<ClosureStatus, String> p) {
						if (p.getValue() != null) {
							return new SimpleStringProperty(
									"Closure Compiler Problem");
						} else {
							return new SimpleStringProperty("<no line>");
						}
					}
				});
		controlProblemsType
				.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

					@Override
					public TableCell<ClosureStatus, String> call(
							TableColumn<ClosureStatus, String> param) {
						return new JSConsoleSectionController.TextCell();
					}
				});
	}

	public ContextMenu getConsoleContextMenu(
			final TableRow<ClosureStatus> tableRow,
			final TableCell<ClosureStatus, ?> tableCell) {
		ContextMenu menu = new ContextMenu();
		final PreferencesSerializer serializer = new PreferencesSerializer();
		Editors editors = serializer.readEditors();
		final Editor defaultEditor = serializer.readDefaultEditor();
		final EditorLoader loader = new EditorLoader();
		tableCell.addEventFilter(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						if (event.getClickCount() > 1) {
							ClosureStatus closureStatus = (ClosureStatus) tableRow
									.getItem();
							loader.load(defaultEditor,
									closureStatus.getResource(),
									Integer.toString(closureStatus.getLine()),
									Integer.toString(closureStatus.getColumn()));
						}
					}
				});
		MenuItem openItem = new MenuItem("Open");
		menu.getItems().add(openItem);
		openItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ClosureStatus closureStatus = (ClosureStatus) tableRow
						.getItem();
				loader.load(defaultEditor, closureStatus.getResource(),
						Integer.toString(closureStatus.getLine()),
						Integer.toString(closureStatus.getColumn()));
			}
		});
		Menu openWith = new Menu("Open with..");
		for (final Editor editor : editors.getEditors()) {
			MenuItem editorItem = new MenuItem(editor.getName());
			editorItem.setId(editor.getPath());
			openWith.getItems().add(editorItem);
			editorItem.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent t) {
					ClosureStatus closureStatus = (ClosureStatus) tableRow
							.getItem();
					loader.load(editor, closureStatus.getResource(),
							Integer.toString(closureStatus.getLine()),
							Integer.toString(closureStatus.getColumn()));
				}
			});
		}
		menu.getItems().add(openWith);
		return menu;
	}

	private class KeyCell
			extends
			com.digiarea.closure.model.controller.JSConsoleSectionController.TextCell {

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (!isEmpty()) {
				Hyperlink hyperlink = new Hyperlink(item);
				if (getTableRow().getItem() instanceof JSCClosureStatus) {
					final JSCClosureStatus status = (JSCClosureStatus) getTableRow()
							.getItem();
					hyperlink.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							HelpController help = ClosureFXController.APPLICATION
									.openHelp();
							help.setInitialURL(
									HelpProvider.class
											.getResource(IConstants.HELP_COMPILER
													+ "/closure-compiler-warnings.html"),
									"#" + status.getDiagnosticType().key);
						}
					});
				}
				setGraphic(hyperlink);
				setTooltip(new Tooltip(item));
			}
			setText(null);
		}

	}

	private class TextCell extends TableCell<ClosureStatus, String> {

		private ContextMenu menu;

		public TextCell() {
			menu = new ContextMenu();
			final PreferencesSerializer serializer = new PreferencesSerializer();
			addEventFilter(MouseEvent.MOUSE_CLICKED,
					new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							if (event.getClickCount() > 1) {
								Editor defaultEditor = serializer
										.readDefaultEditor();
								EditorLoader loader = new EditorLoader();
								if (defaultEditor != null
										&& EditorLoader
												.isSupported(defaultEditor)
										&& EditorLoader.isValid(defaultEditor)) {
									ClosureStatus closureStatus = (ClosureStatus) getTableRow()
											.getItem();
									loader.load(defaultEditor, closureStatus
											.getResource(), Integer
											.toString(closureStatus.getLine()),
											Integer.toString(closureStatus
													.getColumn()));
								}
							}
						}
					});
			final MenuItem openItem = new MenuItem("Open");
			menu.getItems().add(openItem);
			openItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Editor defaultEditor = serializer.readDefaultEditor();
					EditorLoader loader = new EditorLoader();
					if (defaultEditor != null
							&& EditorLoader.isSupported(defaultEditor)
							&& EditorLoader.isValid(defaultEditor)) {
						ClosureStatus closureStatus = (ClosureStatus) getTableRow()
								.getItem();
						loader.load(defaultEditor, closureStatus.getResource(),
								Integer.toString(closureStatus.getLine()),
								Integer.toString(closureStatus.getColumn()));
					}
				}
			});
			final Menu openWith = new Menu("Open with..");
			menu.setOnShowing(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					openWith.getItems().clear();
					Editors editors = serializer.readEditors();
					Editor defaultEditor = serializer.readDefaultEditor();
					openItem.setVisible(defaultEditor != null
							&& EditorLoader.isSupported(defaultEditor)
							&& EditorLoader.isValid(defaultEditor));
					final EditorLoader loader = new EditorLoader();
					for (final Editor editor : editors.getEditors()) {
						if (EditorLoader.isSupported(editor)
								&& EditorLoader.isValid(editor)) {
							MenuItem editorItem = new MenuItem(editor.getName());
							editorItem.setId(editor.getPath());
							openWith.getItems().add(editorItem);
							editorItem
									.setOnAction(new EventHandler<ActionEvent>() {

										public void handle(ActionEvent t) {
											ClosureStatus closureStatus = (ClosureStatus) getTableRow()
													.getItem();
											loader.load(
													editor,
													closureStatus.getResource(),
													Integer.toString(closureStatus
															.getLine()),
													Integer.toString(closureStatus
															.getColumn()));
										}
									});
						}
					}
				}
			});
			menu.getItems().add(openWith);
			setContextMenu(menu);
		}

		@Override
		protected void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (!isEmpty()) {
				setText(item);
				setTooltip(new Tooltip(item));
			}
		}

	}

	@FXML
	private void handleStartButton(ActionEvent event) {
		modelFacade.getJsConsole().start();
	}

	@FXML
	private void handleStopButton(ActionEvent event) {
		modelFacade.getJsConsole().stop();
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public void addErrors(List<ClosureStatus> error) {
		errors.addAll(new ArrayList<ClosureStatus>(error));
	}

	public void addError(ClosureStatus error) {
		errors.add(error);
	}

	public void addMessage(Status error) {
		messages.add(error);
	}

	public ObservableList<Status> getConsole() {
		return messages;
	}

	public Label getErrorLabel() {
		return controlErrors;
	}

	public Label getWarningLabel() {
		return controlWarnings;
	}

	public ObservableList<ClosureStatus> getProblems() {
		return errors;
	}

	public TableView<ClosureStatus> getControlProblems() {
		return controlProblems;
	}

	public TableColumn<ClosureStatus, IStatus.StatusType> getControlProblemsLevel() {
		return controlProblemsLevel;
	}

	public TableColumn<ClosureStatus, String> getControlProblemsDescription() {
		return controlProblemsDescription;
	}

	public TableColumn<ClosureStatus, String> getControlProblemsResource() {
		return controlProblemsResource;
	}

	public TableColumn<ClosureStatus, String> getControlProblemsPath() {
		return controlProblemsPath;
	}

	public TableColumn<ClosureStatus, String> getControlProblemsLocation() {
		return controlProblemsLocation;
	}

	public TableColumn<ClosureStatus, String> getControlProblemsType() {
		return controlProblemsType;
	}

	public Label getControlWarnings() {
		return controlWarnings;
	}

	public Label getControlErrors() {
		return controlErrors;
	}

	public Tab getTabProblems() {
		return tabProblems;
	}

	public Tab getTabMessages() {
		return tabMessages;
	}

	public ListView<Status> getControlConsole() {
		return controlConsole;
	}

	public HBox create() throws Exception {
		HBox hBox5 = new HBox();
		hBox5.setId("HBox");
		hBox5.setAlignment(Pos.CENTER);
		hBox5.setMinHeight(Control.USE_PREF_SIZE);
		hBox5.setMinWidth(Control.USE_PREF_SIZE);
		hBox5.setSpacing(5.0);
		VBox vBox21 = new VBox();
		vBox21.setMaxHeight(Control.USE_COMPUTED_SIZE);
		vBox21.setMaxWidth(Control.USE_COMPUTED_SIZE);
		vBox21.setMinHeight(Control.USE_COMPUTED_SIZE);
		vBox21.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox21.setSpacing(0.0);
		HBox.setHgrow(vBox21, Priority.ALWAYS);
		VBox consoleBox1 = new VBox();
		consoleBox1.setId("ConsoleBox");
		consoleBox1.setAlignment(Pos.CENTER_LEFT);
		consoleBox1.setSpacing(5.0);
		VBox.setVgrow(consoleBox1, Priority.NEVER);
		GridPane gridPane31 = new GridPane();
		gridPane31.setHgap(5.0);
		gridPane31.setPrefWidth(377.0001220703125);
		VBox.setVgrow(gridPane31, Priority.NEVER);
		Button button25 = new Button();
		button25.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		button25.setMnemonicParsing(false);
		button25.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleStartButton(event);
			}
		});
		button25.setText("");
		GridPane.setColumnIndex(button25, 1);
		GridPane.setRowIndex(button25, 0);
		ImageView imageView14 = new ImageView();
		imageView14.setFitHeight(16.0);
		imageView14.setFitWidth(16.0);
		imageView14.setMouseTransparent(true);
		imageView14.setPickOnBounds(true);
		imageView14.setPreserveRatio(true);
		Image image14 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/launch.png").openStream());
		imageView14.setImage(image14);
		button25.setGraphic(imageView14);
		Tooltip tooltip8 = new Tooltip();
		tooltip8.setText(bundle.getString("ProgressDialog_Run"));
		button25.setTooltip(tooltip8);
		gridPane31.getChildren().add(button25);
		Button button26 = new Button();
		button26.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		button26.setMnemonicParsing(false);
		button26.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleStopButton(event);
			}
		});
		button26.setText("");
		GridPane.setColumnIndex(button26, 2);
		GridPane.setRowIndex(button26, 0);
		ImageView imageView15 = new ImageView();
		imageView15.setFitHeight(16.0);
		imageView15.setFitWidth(16.0);
		imageView15.setMouseTransparent(true);
		imageView15.setPickOnBounds(true);
		imageView15.setPreserveRatio(true);
		Image image15 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/mark-error.png")
				.openStream());
		imageView15.setImage(image15);
		button26.setGraphic(imageView15);
		Tooltip tooltip9 = new Tooltip();
		tooltip9.setText(bundle.getString("ProgressDialog_Cancel"));
		button26.setTooltip(tooltip9);
		gridPane31.getChildren().add(button26);
		ColumnConstraints columnConstraints63 = new ColumnConstraints();
		columnConstraints63.setHgrow(Priority.ALWAYS);
		columnConstraints63.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints63.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane31.getColumnConstraints().add(columnConstraints63);
		ColumnConstraints columnConstraints64 = new ColumnConstraints();
		columnConstraints64.setHgrow(Priority.NEVER);
		columnConstraints64.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints64.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane31.getColumnConstraints().add(columnConstraints64);
		ColumnConstraints columnConstraints65 = new ColumnConstraints();
		columnConstraints65.setHgrow(Priority.NEVER);
		columnConstraints65.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints65.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane31.getColumnConstraints().add(columnConstraints65);
		RowConstraints rowConstraints55 = new RowConstraints();
		rowConstraints55.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints55.setPrefHeight(Control.USE_COMPUTED_SIZE);
		rowConstraints55.setVgrow(Priority.NEVER);
		gridPane31.getRowConstraints().add(rowConstraints55);
		consoleBox1.getChildren().add(gridPane31);
		progressBar = new ProgressBar();
		progressBar.setMaxWidth(1.7976931348623157E308);
		progressBar.setMinHeight(Control.USE_PREF_SIZE);
		progressBar.setPrefHeight(25.0);
		progressBar.setPrefWidth(Control.USE_COMPUTED_SIZE);
		progressBar.setProgress(0.0);
		VBox.setVgrow(progressBar, Priority.NEVER);
		consoleBox1.getChildren().add(progressBar);
		Insets insets29 = new Insets(10.0, 10.0, 10.0, 10.0);
		consoleBox1.setPadding(insets29);
		vBox21.getChildren().add(consoleBox1);
		TabPane tabPane2 = new TabPane();
		tabPane2.setPrefHeight(Control.USE_COMPUTED_SIZE);
		tabPane2.setPrefWidth(Control.USE_COMPUTED_SIZE);
		tabPane2.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		VBox.setVgrow(tabPane2, Priority.ALWAYS);
		tabMessages = new Tab();
		tabMessages.setClosable(false);
		tabMessages.setText(bundle.getString("ProgressDialog_Console"));
		VBox vBox22 = new VBox();
		vBox22.setId("VBox");
		vBox22.setAlignment(Pos.CENTER);
		vBox22.setSpacing(5.0);
		controlConsole = new ListView();
		controlConsole.setPrefHeight(100.0);
		controlConsole.setPrefWidth(Control.USE_COMPUTED_SIZE);
		VBox.setVgrow(controlConsole, Priority.ALWAYS);
		vBox22.getChildren().add(controlConsole);
		tabMessages.setContent(vBox22);
		ImageView imageView16 = new ImageView();
		imageView16.setFitHeight(16.0);
		imageView16.setFitWidth(16.0);
		imageView16.setMouseTransparent(true);
		imageView16.setPickOnBounds(true);
		imageView16.setPreserveRatio(true);
		Image image16 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/launch.png").openStream());
		imageView16.setImage(image16);
		tabMessages.setGraphic(imageView16);
		tabPane2.getTabs().add(tabMessages);
		tabProblems = new Tab();
		tabProblems.setClosable(false);
		tabProblems.setText(bundle.getString("ProgressDialog_Problems"));
		VBox vBox23 = new VBox();
		vBox23.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox23.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox23.setSpacing(6.0);
		GridPane gridPane32 = new GridPane();
		gridPane32.setHgap(5.0);
		controlErrors = new Label();
		controlErrors.setText(bundle.getString("ProgressDialog_Errors"));
		GridPane.setColumnIndex(controlErrors, 0);
		GridPane.setRowIndex(controlErrors, 0);
		gridPane32.getChildren().add(controlErrors);
		controlWarnings = new Label();
		controlWarnings.setText(bundle.getString("ProgressDialog_Warnings"));
		GridPane.setColumnIndex(controlWarnings, 1);
		GridPane.setRowIndex(controlWarnings, 0);
		gridPane32.getChildren().add(controlWarnings);
		ColumnConstraints columnConstraints66 = new ColumnConstraints();
		columnConstraints66.setHgrow(Priority.NEVER);
		columnConstraints66.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints66.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane32.getColumnConstraints().add(columnConstraints66);
		ColumnConstraints columnConstraints67 = new ColumnConstraints();
		columnConstraints67.setHgrow(Priority.ALWAYS);
		columnConstraints67.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints67.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane32.getColumnConstraints().add(columnConstraints67);
		Insets insets30 = new Insets(5.0, 5.0, 5.0, 5.0);
		gridPane32.setPadding(insets30);
		RowConstraints rowConstraints56 = new RowConstraints();
		rowConstraints56.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints56.setPrefHeight(Control.USE_COMPUTED_SIZE);
		rowConstraints56.setVgrow(Priority.NEVER);
		gridPane32.getRowConstraints().add(rowConstraints56);
		vBox23.getChildren().add(gridPane32);
		controlProblems = new TableView();
		controlProblems.setMinHeight(Control.USE_COMPUTED_SIZE);
		controlProblems.setMinWidth(Control.USE_PREF_SIZE);
		controlProblems.setPrefHeight(100.0);
		controlProblems.setPrefWidth(Control.USE_COMPUTED_SIZE);
		controlProblems.setTableMenuButtonVisible(false);
		VBox.setVgrow(controlProblems, Priority.ALWAYS);
		controlProblemsLevel = new TableColumn();
		controlProblemsLevel.setMaxWidth(20.0);
		controlProblemsLevel.setMinWidth(16.0);
		controlProblemsLevel.setPrefWidth(20.0);
		controlProblemsLevel.setText("");
		controlProblems.getColumns().add(controlProblemsLevel);
		controlProblemsKey = new TableColumn();
		controlProblemsKey.setMaxWidth(5000.0);
		controlProblemsKey.setMinWidth(100.0);
		controlProblemsKey.setPrefWidth(150.0);
		controlProblemsKey.setText(bundle
				.getString("ProgressDialog_Table_Warning"));
		controlProblems.getColumns().add(controlProblemsKey);
		controlProblemsDescription = new TableColumn();
		controlProblemsDescription.setMinWidth(100.0);
		controlProblemsDescription.setPrefWidth(300.0);
		controlProblemsDescription.setText(bundle
				.getString("ProgressDialog_Table_Description"));
		controlProblems.getColumns().add(controlProblemsDescription);
		controlProblemsResource = new TableColumn();
		controlProblemsResource.setPrefWidth(100.0);
		controlProblemsResource.setText(bundle
				.getString("ProgressDialog_Table_Resource"));
		controlProblems.getColumns().add(controlProblemsResource);
		controlProblemsPath = new TableColumn();
		controlProblemsPath.setPrefWidth(250.0);
		controlProblemsPath.setText(bundle
				.getString("ProgressDialog_Table_Path"));
		controlProblems.getColumns().add(controlProblemsPath);
		controlProblemsLocation = new TableColumn();
		controlProblemsLocation.setPrefWidth(100.0);
		controlProblemsLocation.setText(bundle
				.getString("ProgressDialog_Table_Location"));
		controlProblems.getColumns().add(controlProblemsLocation);
		controlProblemsType = new TableColumn();
		controlProblemsType.setPrefWidth(140.0);
		controlProblemsType.setText(bundle
				.getString("ProgressDialog_Table_Type"));
		controlProblems.getColumns().add(controlProblemsType);
		vBox23.getChildren().add(controlProblems);
		Insets insets31 = new Insets(5.0, 0.0, 0.0, 0.0);
		vBox23.setPadding(insets31);
		tabProblems.setContent(vBox23);
		ImageView imageView17 = new ImageView();
		imageView17.setFitHeight(16.0);
		imageView17.setFitWidth(16.0);
		imageView17.setMouseTransparent(true);
		imageView17.setPickOnBounds(true);
		imageView17.setPreserveRatio(true);
		Image image17 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/mark-exclamation.png")
				.openStream());
		imageView17.setImage(image17);
		tabProblems.setGraphic(imageView17);
		tabPane2.getTabs().add(tabProblems);
		vBox21.getChildren().add(tabPane2);
		Insets insets32 = new Insets(0.0, 0.0, 0.0, 0.0);
		vBox21.setPadding(insets32);
		hBox5.getChildren().add(vBox21);
		Insets insets33 = new Insets(0.0, 0.0, 0.0, 0.0);
		hBox5.setPadding(insets33);
		initialize(null, bundle);
		return hBox5;
	}

}
