package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closure.model.JsDefine;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import com.digiarea.closure.model.JsDefineType;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import com.digiarea.closure.model.providers.ComboboxTableCell;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSDefinesSectionController extends ClosureController implements Initializable {

    public JSDefinesSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TableView<JsDefine> controlJsDefine;

    @FXML
    private TableColumn<JsDefine, String> controlName;

    @FXML
    private TableColumn<JsDefine, String> controlValue;

    @FXML
    private TableColumn<JsDefine, JsDefineType> controlType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlJsDefine.setEditable(true);
        controlName.setCellValueFactory(new PropertyValueFactory<JsDefine, String>("name"));
        controlName.setCellFactory(new JSDefinesSectionController.DefineCellFactory());
        controlName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<JsDefine, String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<JsDefine, String> t) {
                ((JsDefine) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
            }
        });
        controlValue.setCellValueFactory(new PropertyValueFactory<JsDefine, String>("value"));
        controlValue.setCellFactory(new JSDefinesSectionController.DefineCellFactory());
        controlValue.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<JsDefine, String>>() {

            @Override
            public void handle(TableColumn.CellEditEvent<JsDefine, String> t) {
                ((JsDefine) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue());
            }
        });
        controlType.setEditable(true);
        controlType.setCellValueFactory(new PropertyValueFactory<JsDefine, JsDefineType>("type"));
        controlType.setCellFactory(new JSDefinesSectionController.ComboboxCellFactory());
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        JsDefine define = new JsDefine();
        define.setName("define");
        define.setType(JsDefineType.BOOLEAN);
        define.setValue("true");
        modelFacade.addJSDefine(define);
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<JsDefine> defines = controlJsDefine.getSelectionModel().getSelectedItems();
        if (defines != null && !defines.isEmpty()) {
            for (JsDefine define : defines) {
                modelFacade.removeJSDefine(define);
            }
        }
    }

    public TableView<JsDefine> getControlJsDefine() {
        return controlJsDefine;
    }

    public static class ComboboxCellFactory implements Callback<TableColumn<JsDefine, JsDefineType>, TableCell<JsDefine, JsDefineType>> {

        @Override
        public TableCell<JsDefine, JsDefineType> call(TableColumn<JsDefine, JsDefineType> arg0) {
            return new ComboboxTableCell(JsDefineType.values());
        }

    }

    public TableColumn<JsDefine, String> getControlName() {
        return controlName;
    }

    public TableColumn<JsDefine, String> getControlValue() {
        return controlValue;
    }

    public TableColumn<JsDefine, JsDefineType> getControlType() {
        return controlType;
    }

    private class DefineCellFactory implements Callback<TableColumn<JsDefine, String>, TableCell<JsDefine, String>> {

        @Override
        public TableCell<JsDefine, String> call(TableColumn<JsDefine, String> arg0) {
            return new com.digiarea.closure.model.controller.JSDefinesSectionController.DefineTableCell();
        }

    }

    private class DefineTableCell extends TableCell<JsDefine, String> {

        private TextField textField;

        public DefineTableCell() {
        }

        @Override
        public void startEdit() {
            if (!isEditable() || !getTableView().isEditable() || !getTableColumn().isEditable()) {
                return;
            }
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }

    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane13 = new AnchorPane();
        anchorPane13.setId("AnchorPane");
        anchorPane13.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane13.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane13.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane13.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane12 = new TitledPane();
        titledPane12.setAnimated(false);
        titledPane12.setCollapsible(false);
        titledPane12.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane12.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane12.setText(bundle.getString("JSDefinesSection"));
        AnchorPane.setBottomAnchor(titledPane12, 0.0);
        AnchorPane.setLeftAnchor(titledPane12, 0.0);
        AnchorPane.setRightAnchor(titledPane12, 0.0);
        AnchorPane.setTopAnchor(titledPane12, 0.0);
        GridPane gridPane33 = new GridPane();
        gridPane33.setId("GridPane");
        gridPane33.setVgap(5.0);
        Label label23 = new Label();
        label23.setMaxWidth(1.7976931348623157E308);
        label23.setMinWidth(Control.USE_COMPUTED_SIZE);
        label23.setPrefWidth(100.0);
        label23.setText(bundle.getString("JSDefinesSection_Desc"));
        label23.setWrapText(true);
        GridPane.setColumnIndex(label23, 0);
        GridPane.setRowIndex(label23, 0);
        gridPane33.getChildren().add(label23);
        HBox hBox6 = new HBox();
        hBox6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        hBox6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        hBox6.setSpacing(5.0);
        GridPane.setColumnIndex(hBox6, 0);
        GridPane.setRowIndex(hBox6, 1);
        controlJsDefine = new TableView();
        controlJsDefine.setPrefHeight(50.0);
        controlJsDefine.setPrefWidth(Control.USE_COMPUTED_SIZE);
        HBox.setHgrow(controlJsDefine, Priority.ALWAYS);
        controlName = new TableColumn();
        controlName.setMinWidth(75.0);
        controlName.setPrefWidth(75.0);
        controlName.setText(bundle.getString("JSDefinesSection_Column_Name"));
        controlJsDefine.getColumns().add(controlName);
        controlValue = new TableColumn();
        controlValue.setPrefWidth(75.0);
        controlValue.setText(bundle.getString("JSDefinesSection_Column_Value"));
        controlJsDefine.getColumns().add(controlValue);
        controlType = new TableColumn();
        controlType.setPrefWidth(75.0);
        controlType.setText(bundle.getString("JSDefinesSection_Column_Type"));
        controlJsDefine.getColumns().add(controlType);
        hBox6.getChildren().add(controlJsDefine);
        GridPane gridPane34 = new GridPane();
        gridPane34.setId("GridPane");
        gridPane34.setMinWidth(Control.USE_PREF_SIZE);
        gridPane34.setVgap(5.0);
        Button button27 = new Button();
        button27.setMaxWidth(1.7976931348623157E308);
        button27.setMnemonicParsing(false);
        button27.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        button27.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button27, 0);
        GridPane.setRowIndex(button27, 0);
        gridPane34.getChildren().add(button27);
        Button button28 = new Button();
        button28.setMaxWidth(1.7976931348623157E308);
        button28.setMnemonicParsing(false);
        button28.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button28.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button28, 0);
        GridPane.setRowIndex(button28, 1);
        Insets insets34 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button28, insets34);
        gridPane34.getChildren().add(button28);
        ColumnConstraints columnConstraints68 = new ColumnConstraints();
        columnConstraints68.setHgrow(Priority.NEVER);
        columnConstraints68.setMinWidth(Control.USE_PREF_SIZE);
        gridPane34.getColumnConstraints().add(columnConstraints68);
        RowConstraints rowConstraints57 = new RowConstraints();
        rowConstraints57.setMaxHeight(19.0);
        rowConstraints57.setPrefHeight(19.0);
        rowConstraints57.setVgrow(Priority.NEVER);
        gridPane34.getRowConstraints().add(rowConstraints57);
        RowConstraints rowConstraints58 = new RowConstraints();
        rowConstraints58.setMaxHeight(19.0);
        rowConstraints58.setPrefHeight(19.0);
        rowConstraints58.setVgrow(Priority.NEVER);
        gridPane34.getRowConstraints().add(rowConstraints58);
        hBox6.getChildren().add(gridPane34);
        gridPane33.getChildren().add(hBox6);
        ColumnConstraints columnConstraints69 = new ColumnConstraints();
        columnConstraints69.setHgrow(Priority.ALWAYS);
        columnConstraints69.setMinWidth(Control.USE_PREF_SIZE);
        gridPane33.getColumnConstraints().add(columnConstraints69);
        Insets insets35 = new Insets(10.0, 10.0, 10.0, 10.0);
        gridPane33.setPadding(insets35);
        RowConstraints rowConstraints59 = new RowConstraints();
        rowConstraints59.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints59.setVgrow(Priority.NEVER);
        gridPane33.getRowConstraints().add(rowConstraints59);
        RowConstraints rowConstraints60 = new RowConstraints();
        rowConstraints60.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints60.setVgrow(Priority.ALWAYS);
        gridPane33.getRowConstraints().add(rowConstraints60);
        titledPane12.setContent(gridPane33);
        anchorPane13.getChildren().add(titledPane12);
        initialize(null, bundle);
        return anchorPane13;
    }

}
