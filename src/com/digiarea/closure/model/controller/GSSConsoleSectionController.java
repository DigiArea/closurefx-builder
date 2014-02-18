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

import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.providers.ConsoleCellFactory;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.console.GSSConsoleManager;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;
import com.digiarea.closurefx.editors.EditorLoader;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSConsoleSectionController extends ClosureController implements Initializable, IConsole {

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

    public GSSConsoleSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
        modelFacade.setGssConsole(new GSSConsoleManager(this, modelFacade.getClosure(), bundle, modelFacade.getDocument().getPathResolver()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlErrors.setText(MessageFormat.format(bundle.getString(IConstants.ConsoleErrors), Integer.toString(0)));
        controlWarnings.setText(MessageFormat.format(bundle.getString(IConstants.ConsoleWarnings), Integer.toString(0)));
        tabProblems.setText(MessageFormat.format(bundle.getString(IConstants.ProgressDialog_Problems), Integer.toString(0)));
        errors = FXCollections.observableArrayList();
        messages = FXCollections.observableArrayList();
        controlProblems.setItems(errors);
        controlConsole.setItems(messages);
        errors.addListener(new ListChangeListener<ClosureStatus>() {

            @SuppressWarnings("incomplete-switch")
            @Override
            public void onChanged(ListChangeListener.Change<? extends ClosureStatus> c) {
                int errorsInt = 0;
                int warningsInt = 0;
                for (ClosureStatus error : errors) {
                    IStatus.StatusType checkLevel = error.getSeverity();
                    switch(checkLevel) {
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
                controlErrors.setText(MessageFormat.format(bundle.getString(IConstants.ConsoleErrors), Integer.toString(errorsInt)));
                controlWarnings.setText(MessageFormat.format(bundle.getString(IConstants.ConsoleWarnings), Integer.toString(warningsInt)));
                tabProblems.setText(MessageFormat.format(bundle.getString(IConstants.ProgressDialog_Problems), Integer.toString(warningsInt + errorsInt)));
            }
        });
        controlConsole.setCellFactory(new ConsoleCellFactory());
        controlProblemsLevel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, IStatus.StatusType>, ObservableValue<IStatus.StatusType>>() {

            @Override
            public ObservableValue<IStatus.StatusType> call(TableColumn.CellDataFeatures<ClosureStatus, IStatus.StatusType> p) {
                return new SimpleObjectProperty<IStatus.StatusType>((IStatus.StatusType) p.getValue().getSeverity());
            }
        });
        controlProblemsLevel.setCellFactory((new Callback<TableColumn<ClosureStatus, IStatus.StatusType>, TableCell<ClosureStatus, IStatus.StatusType>>() {

            @Override
            public TableCell<ClosureStatus, IStatus.StatusType> call(TableColumn<ClosureStatus, IStatus.StatusType> param) {
                TableCell<ClosureStatus, IStatus.StatusType> cell = new TableCell<ClosureStatus, IStatus.StatusType>() {

                    @SuppressWarnings("incomplete-switch")
                    @Override
                    public void updateItem(IStatus.StatusType item, boolean empty) {
                        if (item != null) {
                            switch(item) {
                                case ERROR:
                                    setGraphic(new ImageView(ResourceUtils.MARK_ERROR));
                                    break;
                                case OFF:
                                    setGraphic(new ImageView(ResourceUtils.MARK_OFF));
                                    break;
                                case WARNING:
                                    setGraphic(new ImageView(ResourceUtils.MARK_WARNING));
                                    break;
                            }
                        }
                    }
                };
                return cell;
            }
        }));
        controlProblemsDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getMessage());
                } else {
                    return new SimpleStringProperty("<no description>");
                }
            }
        });
        controlProblemsDescription.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new GSSConsoleSectionController.TextCell();
            }
        });
        controlProblemsResource.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null && p.getValue().getResource() != null) {
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
        controlProblemsResource.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new GSSConsoleSectionController.TextCell();
            }
        });
        controlProblemsPath.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getResource());
                } else {
                    return new SimpleStringProperty("<no path>");
                }
            }
        });
        controlProblemsPath.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new GSSConsoleSectionController.TextCell();
            }
        });
        controlProblemsLocation.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty("line " + Integer.toString(p.getValue().getLine()));
                } else {
                    return new SimpleStringProperty("<no line>");
                }
            }
        });
        controlProblemsLocation.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new GSSConsoleSectionController.TextCell();
            }
        });
        controlProblemsType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty("Closure Stylesheets Problem");
                } else {
                    return new SimpleStringProperty("<no type>");
                }
            }
        });
        controlProblemsType.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new GSSConsoleSectionController.TextCell();
            }
        });
    }

    public ContextMenu getConsoleContextMenu(final TableRow<ClosureStatus> tableRow, final TableCell<ClosureStatus, ?> tableCell) {
        ContextMenu menu = new ContextMenu();
        final PreferencesSerializer serializer = new PreferencesSerializer();
        Editors editors = serializer.readEditors();
        final Editor defaultEditor = serializer.readDefaultEditor();
        final EditorLoader loader = new EditorLoader();
        tableCell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() > 1) {
                    ClosureStatus closureStatus = (ClosureStatus) tableRow.getItem();
                    loader.load(defaultEditor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
                }
            }
        });
        MenuItem openItem = new MenuItem("Open");
        menu.getItems().add(openItem);
        openItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                ClosureStatus closureStatus = (ClosureStatus) tableRow.getItem();
                loader.load(defaultEditor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
            }
        });
        Menu openWith = new Menu("Open with..");
        for (final Editor editor : editors.getEditors()) {
            MenuItem editorItem = new MenuItem(editor.getName());
            editorItem.setId(editor.getPath());
            openWith.getItems().add(editorItem);
            editorItem.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent t) {
                    ClosureStatus closureStatus = (ClosureStatus) tableRow.getItem();
                    loader.load(editor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
                }
            });
        }
        menu.getItems().add(openWith);
        return menu;
    }

    private class TextCell extends TableCell<ClosureStatus, String> {

        private ContextMenu menu;

        public TextCell() {
            menu = new ContextMenu();
            final PreferencesSerializer serializer = new PreferencesSerializer();
            addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() > 1) {
                        Editor defaultEditor = serializer.readDefaultEditor();
                        EditorLoader loader = new EditorLoader();
                        if (defaultEditor != null && EditorLoader.isSupported(defaultEditor) && EditorLoader.isValid(defaultEditor)) {
                            ClosureStatus closureStatus = (ClosureStatus) getTableRow().getItem();
                            loader.load(defaultEditor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
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
                    if (defaultEditor != null && EditorLoader.isSupported(defaultEditor) && EditorLoader.isValid(defaultEditor)) {
                        ClosureStatus closureStatus = (ClosureStatus) getTableRow().getItem();
                        loader.load(defaultEditor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
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
                    openItem.setVisible(defaultEditor != null && EditorLoader.isSupported(defaultEditor) && EditorLoader.isValid(defaultEditor));
                    final EditorLoader loader = new EditorLoader();
                    for (final Editor editor : editors.getEditors()) {
                        if (EditorLoader.isSupported(editor) && EditorLoader.isValid(editor)) {
                            MenuItem editorItem = new MenuItem(editor.getName());
                            editorItem.setId(editor.getPath());
                            openWith.getItems().add(editorItem);
                            editorItem.setOnAction(new EventHandler<ActionEvent>() {

                                public void handle(ActionEvent t) {
                                    ClosureStatus closureStatus = (ClosureStatus) getTableRow().getItem();
                                    loader.load(editor, closureStatus.getResource(), Integer.toString(closureStatus.getLine()), Integer.toString(closureStatus.getColumn()));
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
        modelFacade.getGssConsole().start();
    }

    @FXML
    private void handleStopButton(ActionEvent event) {
        modelFacade.getGssConsole().stop();
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
        HBox hBox = new HBox();
        hBox.setId("HBox");
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinHeight(Control.USE_PREF_SIZE);
        hBox.setMinWidth(Control.USE_PREF_SIZE);
        hBox.setSpacing(5.0);
        VBox vBox = new VBox();
        vBox.setMaxHeight(Control.USE_COMPUTED_SIZE);
        vBox.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBox.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBox.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox.setSpacing(0.0);
        HBox.setHgrow(vBox, Priority.ALWAYS);
        VBox consoleBox = new VBox();
        consoleBox.setId("ConsoleBox");
        consoleBox.setAlignment(Pos.CENTER_LEFT);
        consoleBox.setSpacing(5.0);
        VBox.setVgrow(consoleBox, Priority.NEVER);
        GridPane gridPane3 = new GridPane();
        gridPane3.setHgap(5.0);
        gridPane3.setPrefWidth(377.0001220703125);
        VBox.setVgrow(gridPane3, Priority.NEVER);
        Button button = new Button();
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button.setMnemonicParsing(false);
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleStartButton(event);
            }
        });
        button.setText("");
        GridPane.setColumnIndex(button, 1);
        GridPane.setRowIndex(button, 0);
        ImageView imageView3 = new ImageView();
        imageView3.setFitHeight(16.0);
        imageView3.setFitWidth(16.0);
        imageView3.setMouseTransparent(true);
        imageView3.setPickOnBounds(true);
        imageView3.setPreserveRatio(true);
        Image image3 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView3.setImage(image3);
        button.setGraphic(imageView3);
        Tooltip tooltip = new Tooltip();
        tooltip.setText(bundle.getString("ProgressDialog_Run"));
        button.setTooltip(tooltip);
        gridPane3.getChildren().add(button);
        Button button1 = new Button();
        button1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button1.setMnemonicParsing(false);
        button1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleStopButton(event);
            }
        });
        button1.setText("");
        GridPane.setColumnIndex(button1, 2);
        GridPane.setRowIndex(button1, 0);
        ImageView imageView4 = new ImageView();
        imageView4.setFitHeight(16.0);
        imageView4.setFitWidth(16.0);
        imageView4.setMouseTransparent(true);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        Image image4 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-error.png").openStream());
        imageView4.setImage(image4);
        button1.setGraphic(imageView4);
        Tooltip tooltip1 = new Tooltip();
        tooltip1.setText(bundle.getString("ProgressDialog_Cancel"));
        button1.setTooltip(tooltip1);
        gridPane3.getChildren().add(button1);
        ColumnConstraints columnConstraints3 = new ColumnConstraints();
        columnConstraints3.setHgrow(Priority.ALWAYS);
        columnConstraints3.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane3.getColumnConstraints().add(columnConstraints3);
        ColumnConstraints columnConstraints4 = new ColumnConstraints();
        columnConstraints4.setHgrow(Priority.NEVER);
        columnConstraints4.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane3.getColumnConstraints().add(columnConstraints4);
        ColumnConstraints columnConstraints5 = new ColumnConstraints();
        columnConstraints5.setHgrow(Priority.NEVER);
        columnConstraints5.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints5.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane3.getColumnConstraints().add(columnConstraints5);
        RowConstraints rowConstraints3 = new RowConstraints();
        rowConstraints3.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints3.setVgrow(Priority.NEVER);
        gridPane3.getRowConstraints().add(rowConstraints3);
        consoleBox.getChildren().add(gridPane3);
        progressBar = new ProgressBar();
        progressBar.setMaxWidth(1.7976931348623157E308);
        progressBar.setMinHeight(Control.USE_PREF_SIZE);
        progressBar.setPrefHeight(25.0);
        progressBar.setPrefWidth(Control.USE_COMPUTED_SIZE);
        progressBar.setProgress(0.0);
        VBox.setVgrow(progressBar, Priority.NEVER);
        consoleBox.getChildren().add(progressBar);
        Insets insets = new Insets(10.0, 10.0, 10.0, 10.0);
        consoleBox.setPadding(insets);
        vBox.getChildren().add(consoleBox);
        TabPane tabPane1 = new TabPane();
        tabPane1.setPrefHeight(Control.USE_COMPUTED_SIZE);
        tabPane1.setPrefWidth(Control.USE_COMPUTED_SIZE);
        tabPane1.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabPane1, Priority.ALWAYS);
        tabMessages = new Tab();
        tabMessages.setClosable(false);
        tabMessages.setText(bundle.getString("ProgressDialog_Console"));
        VBox vBox1 = new VBox();
        vBox1.setId("VBox");
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(5.0);
        controlConsole = new ListView();
        controlConsole.setPrefHeight(100.0);
        controlConsole.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(controlConsole, Priority.ALWAYS);
        vBox1.getChildren().add(controlConsole);
        tabMessages.setContent(vBox1);
        ImageView imageView5 = new ImageView();
        imageView5.setFitHeight(16.0);
        imageView5.setFitWidth(16.0);
        imageView5.setMouseTransparent(true);
        imageView5.setPickOnBounds(true);
        imageView5.setPreserveRatio(true);
        Image image5 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView5.setImage(image5);
        tabMessages.setGraphic(imageView5);
        tabPane1.getTabs().add(tabMessages);
        tabProblems = new Tab();
        tabProblems.setClosable(false);
        tabProblems.setText(bundle.getString("ProgressDialog_Problems"));
        VBox vBox2 = new VBox();
        vBox2.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox2.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox2.setSpacing(6.0);
        GridPane gridPane4 = new GridPane();
        gridPane4.setHgap(5.0);
        controlErrors = new Label();
        controlErrors.setText(bundle.getString("ProgressDialog_Errors"));
        GridPane.setColumnIndex(controlErrors, 0);
        GridPane.setRowIndex(controlErrors, 0);
        gridPane4.getChildren().add(controlErrors);
        controlWarnings = new Label();
        controlWarnings.setText(bundle.getString("ProgressDialog_Warnings"));
        GridPane.setColumnIndex(controlWarnings, 1);
        GridPane.setRowIndex(controlWarnings, 0);
        gridPane4.getChildren().add(controlWarnings);
        ColumnConstraints columnConstraints6 = new ColumnConstraints();
        columnConstraints6.setHgrow(Priority.NEVER);
        columnConstraints6.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane4.getColumnConstraints().add(columnConstraints6);
        ColumnConstraints columnConstraints7 = new ColumnConstraints();
        columnConstraints7.setHgrow(Priority.ALWAYS);
        columnConstraints7.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane4.getColumnConstraints().add(columnConstraints7);
        Insets insets1 = new Insets(5.0, 5.0, 5.0, 5.0);
        gridPane4.setPadding(insets1);
        RowConstraints rowConstraints4 = new RowConstraints();
        rowConstraints4.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints4.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints4.setVgrow(Priority.NEVER);
        gridPane4.getRowConstraints().add(rowConstraints4);
        vBox2.getChildren().add(gridPane4);
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
        controlProblemsDescription = new TableColumn();
        controlProblemsDescription.setMinWidth(100.0);
        controlProblemsDescription.setPrefWidth(300.0);
        controlProblemsDescription.setText(bundle.getString("ProgressDialog_Table_Description"));
        controlProblems.getColumns().add(controlProblemsDescription);
        controlProblemsResource = new TableColumn();
        controlProblemsResource.setPrefWidth(100.0);
        controlProblemsResource.setText(bundle.getString("ProgressDialog_Table_Resource"));
        controlProblems.getColumns().add(controlProblemsResource);
        controlProblemsPath = new TableColumn();
        controlProblemsPath.setPrefWidth(200.0);
        controlProblemsPath.setText(bundle.getString("ProgressDialog_Table_Path"));
        controlProblems.getColumns().add(controlProblemsPath);
        controlProblemsLocation = new TableColumn();
        controlProblemsLocation.setPrefWidth(100.0);
        controlProblemsLocation.setText(bundle.getString("ProgressDialog_Table_Location"));
        controlProblems.getColumns().add(controlProblemsLocation);
        controlProblemsType = new TableColumn();
        controlProblemsType.setPrefWidth(100.0);
        controlProblemsType.setText(bundle.getString("ProgressDialog_Table_Type"));
        controlProblems.getColumns().add(controlProblemsType);
        vBox2.getChildren().add(controlProblems);
        Insets insets2 = new Insets(5.0, 0.0, 0.0, 0.0);
        vBox2.setPadding(insets2);
        tabProblems.setContent(vBox2);
        ImageView imageView6 = new ImageView();
        imageView6.setFitHeight(16.0);
        imageView6.setFitWidth(16.0);
        imageView6.setMouseTransparent(true);
        imageView6.setPickOnBounds(true);
        imageView6.setPreserveRatio(true);
        Image image6 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-exclamation.png").openStream());
        imageView6.setImage(image6);
        tabProblems.setGraphic(imageView6);
        tabPane1.getTabs().add(tabProblems);
        vBox.getChildren().add(tabPane1);
        Insets insets3 = new Insets(0.0, 0.0, 0.0, 0.0);
        vBox.setPadding(insets3);
        hBox.getChildren().add(vBox);
        Insets insets4 = new Insets(0.0, 0.0, 0.0, 0.0);
        hBox.setPadding(insets4);
        initialize(null, bundle);
        return hBox;
    }

}
