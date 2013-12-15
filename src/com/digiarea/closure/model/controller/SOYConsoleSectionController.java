package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.controller.IConsole;
import javafx.scene.control.Tab;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import com.digiarea.closurefx.build.console.ClosureStatus;
import javafx.scene.control.TableView;
import com.digiarea.closurefx.build.validation.Status;
import javafx.scene.control.ListView;
import com.digiarea.closurefx.build.validation.IStatus;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closurefx.build.console.SOYConsoleManager;
import java.net.URL;
import com.digiarea.closurefx.IConstants;
import java.text.MessageFormat;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import com.digiarea.closure.model.providers.ConsoleCellFactory;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import com.digiarea.closurefx.ResourceUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableRow;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closurefx.cli.EditorLoader;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.stage.WindowEvent;
import javafx.scene.control.Tooltip;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TabPane.TabClosingPolicy;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYConsoleSectionController extends ClosureController implements Initializable, IConsole {

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

    public SOYConsoleSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
        modelFacade.setSoyConsole(new SOYConsoleManager(this, modelFacade.getClosure(), bundle, modelFacade.getDocument().getPathResolver()));
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
                return new SOYConsoleSectionController.TextCell();
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
                return new SOYConsoleSectionController.TextCell();
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
                return new SOYConsoleSectionController.TextCell();
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
                return new SOYConsoleSectionController.TextCell();
            }
        });
        controlProblemsType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureStatus, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureStatus, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty("Closure Templates Problem");
                } else {
                    return new SimpleStringProperty("<no type>");
                }
            }
        });
        controlProblemsType.setCellFactory(new Callback<TableColumn<ClosureStatus, String>, TableCell<ClosureStatus, String>>() {

            @Override
            public TableCell<ClosureStatus, String> call(TableColumn<ClosureStatus, String> param) {
                return new SOYConsoleSectionController.TextCell();
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
        modelFacade.getSoyConsole().start();
    }

    @FXML
    private void handleStopButton(ActionEvent event) {
        modelFacade.getSoyConsole().stop();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
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
        HBox hBox12 = new HBox();
        hBox12.setId("HBox");
        hBox12.setAlignment(Pos.CENTER);
        hBox12.setMinHeight(Control.USE_PREF_SIZE);
        hBox12.setMinWidth(Control.USE_PREF_SIZE);
        hBox12.setSpacing(5.0);
        VBox vBox51 = new VBox();
        vBox51.setMaxHeight(Control.USE_COMPUTED_SIZE);
        vBox51.setMaxWidth(Control.USE_COMPUTED_SIZE);
        vBox51.setMinHeight(Control.USE_COMPUTED_SIZE);
        vBox51.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox51.setSpacing(0.0);
        HBox.setHgrow(vBox51, Priority.ALWAYS);
        VBox consoleBox2 = new VBox();
        consoleBox2.setId("ConsoleBox");
        consoleBox2.setAlignment(Pos.CENTER_LEFT);
        consoleBox2.setSpacing(5.0);
        VBox.setVgrow(consoleBox2, Priority.NEVER);
        GridPane gridPane59 = new GridPane();
        gridPane59.setHgap(5.0);
        gridPane59.setPrefWidth(377.0001220703125);
        VBox.setVgrow(gridPane59, Priority.NEVER);
        Button button55 = new Button();
        button55.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button55.setMnemonicParsing(false);
        button55.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleStartButton(event);
            }
        });
        button55.setText("");
        GridPane.setColumnIndex(button55, 1);
        GridPane.setRowIndex(button55, 0);
        ImageView imageView33 = new ImageView();
        imageView33.setFitHeight(16.0);
        imageView33.setFitWidth(16.0);
        imageView33.setMouseTransparent(true);
        imageView33.setPickOnBounds(true);
        imageView33.setPreserveRatio(true);
        Image image33 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView33.setImage(image33);
        button55.setGraphic(imageView33);
        Tooltip tooltip18 = new Tooltip();
        tooltip18.setText(bundle.getString("ProgressDialog_Run"));
        button55.setTooltip(tooltip18);
        gridPane59.getChildren().add(button55);
        Button button56 = new Button();
        button56.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        button56.setMnemonicParsing(false);
        button56.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleStopButton(event);
            }
        });
        button56.setText("");
        GridPane.setColumnIndex(button56, 2);
        GridPane.setRowIndex(button56, 0);
        ImageView imageView34 = new ImageView();
        imageView34.setFitHeight(16.0);
        imageView34.setFitWidth(16.0);
        imageView34.setMouseTransparent(true);
        imageView34.setPickOnBounds(true);
        imageView34.setPreserveRatio(true);
        Image image34 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-error.png").openStream());
        imageView34.setImage(image34);
        button56.setGraphic(imageView34);
        Tooltip tooltip19 = new Tooltip();
        tooltip19.setText(bundle.getString("ProgressDialog_Cancel"));
        button56.setTooltip(tooltip19);
        gridPane59.getChildren().add(button56);
        ColumnConstraints columnConstraints132 = new ColumnConstraints();
        columnConstraints132.setHgrow(Priority.ALWAYS);
        columnConstraints132.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints132.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane59.getColumnConstraints().add(columnConstraints132);
        ColumnConstraints columnConstraints133 = new ColumnConstraints();
        columnConstraints133.setHgrow(Priority.NEVER);
        columnConstraints133.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints133.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane59.getColumnConstraints().add(columnConstraints133);
        ColumnConstraints columnConstraints134 = new ColumnConstraints();
        columnConstraints134.setHgrow(Priority.NEVER);
        columnConstraints134.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints134.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane59.getColumnConstraints().add(columnConstraints134);
        RowConstraints rowConstraints116 = new RowConstraints();
        rowConstraints116.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints116.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints116.setVgrow(Priority.NEVER);
        gridPane59.getRowConstraints().add(rowConstraints116);
        consoleBox2.getChildren().add(gridPane59);
        progressBar = new ProgressBar();
        progressBar.setMaxWidth(1.7976931348623157E308);
        progressBar.setMinHeight(Control.USE_PREF_SIZE);
        progressBar.setPrefHeight(25.0);
        progressBar.setPrefWidth(Control.USE_COMPUTED_SIZE);
        progressBar.setProgress(0.0);
        VBox.setVgrow(progressBar, Priority.NEVER);
        consoleBox2.getChildren().add(progressBar);
        Insets insets73 = new Insets(10.0, 10.0, 10.0, 10.0);
        consoleBox2.setPadding(insets73);
        vBox51.getChildren().add(consoleBox2);
        TabPane tabPane3 = new TabPane();
        tabPane3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        tabPane3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        tabPane3.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabPane3, Priority.ALWAYS);
        tabMessages = new Tab();
        tabMessages.setClosable(false);
        tabMessages.setText(bundle.getString("ProgressDialog_Console"));
        VBox vBox52 = new VBox();
        vBox52.setId("VBox");
        vBox52.setAlignment(Pos.CENTER);
        vBox52.setSpacing(5.0);
        controlConsole = new ListView();
        controlConsole.setPrefHeight(100.0);
        controlConsole.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(controlConsole, Priority.ALWAYS);
        vBox52.getChildren().add(controlConsole);
        tabMessages.setContent(vBox52);
        ImageView imageView35 = new ImageView();
        imageView35.setFitHeight(16.0);
        imageView35.setFitWidth(16.0);
        imageView35.setMouseTransparent(true);
        imageView35.setPickOnBounds(true);
        imageView35.setPreserveRatio(true);
        Image image35 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView35.setImage(image35);
        tabMessages.setGraphic(imageView35);
        tabPane3.getTabs().add(tabMessages);
        tabProblems = new Tab();
        tabProblems.setClosable(false);
        tabProblems.setText(bundle.getString("ProgressDialog_Problems"));
        VBox vBox53 = new VBox();
        vBox53.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox53.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox53.setSpacing(6.0);
        GridPane gridPane60 = new GridPane();
        gridPane60.setHgap(5.0);
        controlErrors = new Label();
        controlErrors.setText(bundle.getString("ProgressDialog_Errors"));
        GridPane.setColumnIndex(controlErrors, 0);
        GridPane.setRowIndex(controlErrors, 0);
        gridPane60.getChildren().add(controlErrors);
        controlWarnings = new Label();
        controlWarnings.setText(bundle.getString("ProgressDialog_Warnings"));
        GridPane.setColumnIndex(controlWarnings, 1);
        GridPane.setRowIndex(controlWarnings, 0);
        gridPane60.getChildren().add(controlWarnings);
        ColumnConstraints columnConstraints135 = new ColumnConstraints();
        columnConstraints135.setHgrow(Priority.NEVER);
        columnConstraints135.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints135.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane60.getColumnConstraints().add(columnConstraints135);
        ColumnConstraints columnConstraints136 = new ColumnConstraints();
        columnConstraints136.setHgrow(Priority.ALWAYS);
        columnConstraints136.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints136.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane60.getColumnConstraints().add(columnConstraints136);
        Insets insets74 = new Insets(5.0, 5.0, 5.0, 5.0);
        gridPane60.setPadding(insets74);
        RowConstraints rowConstraints117 = new RowConstraints();
        rowConstraints117.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints117.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints117.setVgrow(Priority.NEVER);
        gridPane60.getRowConstraints().add(rowConstraints117);
        vBox53.getChildren().add(gridPane60);
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
        vBox53.getChildren().add(controlProblems);
        Insets insets75 = new Insets(5.0, 0.0, 0.0, 0.0);
        vBox53.setPadding(insets75);
        tabProblems.setContent(vBox53);
        ImageView imageView36 = new ImageView();
        imageView36.setFitHeight(16.0);
        imageView36.setFitWidth(16.0);
        imageView36.setMouseTransparent(true);
        imageView36.setPickOnBounds(true);
        imageView36.setPreserveRatio(true);
        Image image36 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-exclamation.png").openStream());
        imageView36.setImage(image36);
        tabProblems.setGraphic(imageView36);
        tabPane3.getTabs().add(tabProblems);
        vBox51.getChildren().add(tabPane3);
        Insets insets76 = new Insets(0.0, 0.0, 0.0, 0.0);
        vBox51.setPadding(insets76);
        hBox12.getChildren().add(vBox51);
        Insets insets77 = new Insets(0.0, 0.0, 0.0, 0.0);
        hBox12.setPadding(insets77);
        initialize(null, bundle);
        return hBox12;
    }

}
