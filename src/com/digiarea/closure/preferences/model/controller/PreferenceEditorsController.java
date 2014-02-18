package com.digiarea.closure.preferences.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Callback;

import com.digiarea.closure.model.controller.UIUtils;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.bind.ModelFacade;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.editors.EditorLoader;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class PreferenceEditorsController extends ClosurePreferencesController implements Initializable {

    public PreferenceEditorsController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TableView<Editor> controlEditors;

    @FXML
    private TableColumn<Editor, Boolean> controlDefault;

    @FXML
    private TableColumn<Editor, String> controlName;

    @FXML
    private TableColumn<Editor, String> controlEditorPath;

    @FXML
    private TableColumn<Editor, String> controlButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlEditors.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        final ToggleGroup toggleGroup = new ToggleGroup();
        controlEditors.setEditable(true);
        controlEditors.widthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                Pane header = (Pane) controlEditors.lookup("TableHeaderRow");
                if (header != null && header.isVisible()) {
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                    header.setManaged(false);
                }
            }
        });
        controlDefault.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Editor, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Editor, Boolean> p) {
                if (p.getValue() != null) {
                    return new SimpleBooleanProperty(p.getValue().isDefault());
                } else {
                    return new SimpleBooleanProperty(false);
                }
            }
        });
        controlDefault.setCellFactory(new Callback<TableColumn<Editor, Boolean>, TableCell<Editor, Boolean>>() {

            @Override
            public TableCell<Editor, Boolean> call(TableColumn<Editor, Boolean> param) {
                return new PreferenceEditorsController.EditorRectCell(toggleGroup);
            }
        });
        controlButton.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Editor, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Editor, String> p) {
                if (p.getValue() != null) {
                    return p.getValue().nameProperty();
                } else {
                    return new SimpleStringProperty();
                }
            }
        });
        controlButton.setCellFactory(new Callback<TableColumn<Editor, String>, TableCell<Editor, String>>() {

            @Override
            public TableCell<Editor, String> call(TableColumn<Editor, String> param) {
                return new PreferenceEditorsController.ButtonCell();
            }
        });
        controlEditorPath.setCellFactory(new Callback<TableColumn<Editor, String>, TableCell<Editor, String>>() {

            @Override
            public TableCell<Editor, String> call(TableColumn<Editor, String> param) {
                return new PreferenceEditorsController.PathCell();
            }
        });
        controlEditorPath.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Editor, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Editor, String> p) {
                return p.getValue().pathProperty();
            }
        });
        controlName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Editor, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Editor, String> p) {
                if (p.getValue() != null) {
                    return p.getValue().nameProperty();
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
    }

    @FXML
    private void handleApplyButtonAction(ActionEvent event) {
        modelFacade.saveEditors();
    }

    public class PathCell extends TableCell<Editor, String> {

        public PathCell() {
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Editor editor = (Editor) getTableRow().getItem();
            if (editor != null) {
                if (EditorLoader.isSupported(editor)) {
                    if (editor.getPath() == null || editor.getPath().isEmpty()) {
                        setText("");
                    } else if (!EditorLoader.isValid((Editor) getTableRow().getItem())) {
                        setText("<invalid path> " + item);
                        setTooltip(new Tooltip(item));
                        setTextFill(Color.RED);
                        setFont(Font.font("Arial", FontPosture.ITALIC, 11));
                    } else {
                        setText(item);
                        setTooltip(new Tooltip(item));
                        setTextFill(Color.BLACK);
                        setFont(Font.font("Arial", FontPosture.REGULAR, 11));
                    }
                } else {
                    setText("<unsupported for your os>");
                    setTextFill(Color.LIGHTCORAL);
                    setFont(Font.font("Arial", FontPosture.ITALIC, 11));
                }
            }
        }

    }

    public class ButtonCell extends TableCell<Editor, String> {

        private Button box;

        public ButtonCell() {
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                if (box == null) {
                    createCheckBox();
                }
                setGraphic(box);
            }
        }

        private void createCheckBox() {
            box = new Button("Browse");
            box.getStyleClass().add(IConstants.CSS_INVISIBLE_BUTTON);
            box.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    File file = UIUtils.chooseFolder(null, "Select source folder");
                    if (file != null) {
                        Editor editor = (Editor) getTableRow().getItem();
                        if (editor != null) {
                            modelFacade.updateEditorPath(editor, file.getAbsolutePath());
                        }
                    }
                }
            });
        }

    }

    public class EditorRectCell extends TableCell<Editor, Boolean> {

        private RadioButton box;

        private ToggleGroup toggleGroup;

        public EditorRectCell(ToggleGroup toggleGroup) {
            this.toggleGroup = toggleGroup;
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                if (box == null) {
                    createCheckBox();
                }
                if (item != null) {
                    box.setSelected(item);
                }
                setGraphic(box);
                setTooltip(new Tooltip("Mark as Default"));
                getTableRow().setDisable(!EditorLoader.isSupported(((Editor) getTableRow().getItem())));
            }
        }

        private void createCheckBox() {
            box = new RadioButton();
            box.setToggleGroup(toggleGroup);
            box.getStyleClass().add(IConstants.CSS_INVISIBLE_RADIO_BUTTON);
            box.selectedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                    ((Editor) getTableRow().getItem()).setDefault(box.isSelected());
                    commitEdit(getItem());
                }
            });
        }

    }

    public TableView<Editor> getControlEditors() {
        return controlEditors;
    }

    public TableColumn<Editor, Boolean> getControlDefault() {
        return controlDefault;
    }

    public TableColumn<Editor, String> getControlName() {
        return controlName;
    }

    public TableColumn<Editor, String> getControlEditorPath() {
        return controlEditorPath;
    }

    public TableColumn<Editor, String> getControlButton() {
        return controlButton;
    }

}
