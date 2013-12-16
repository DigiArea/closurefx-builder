package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;

import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSDefinesSectionController extends ClosureController implements Initializable {

    public GSSDefinesSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<GssDefine> controlGssDefine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlGssDefine.setEditable(true);
        controlGssDefine.setCellFactory(new GSSDefinesSectionController.GSSDefinesCellFactory());
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        GssDefine define = new GssDefine();
        define.setValue("define");
        modelFacade.assSOYDefine(define);
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<GssDefine> defines = controlGssDefine.getSelectionModel().getSelectedItems();
        if (defines != null && !defines.isEmpty()) {
            for (GssDefine define : defines) {
                modelFacade.removeGSSDefine(define);
            }
        }
    }

    private class GSSDefinesCellFactory implements Callback<ListView<GssDefine>, ListCell<GssDefine>> {

        @Override
        public ListCell<GssDefine> call(ListView<GssDefine> list) {
            return new com.digiarea.closure.model.controller.GSSDefinesSectionController.GSSDefinesListCell();
        }

    }

    private class GSSDefinesListCell extends ListCell<GssDefine> {

        private TextField textField;

        public GSSDefinesListCell() {
        }

        @Override
        public void startEdit() {
            if (!isEditable() || !getListView().isEditable()) {
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
            setText(getItem().getValue());
            setGraphic(null);
        }

        @Override
        public void updateItem(GssDefine item, boolean empty) {
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
                        getItem().setValue(textField.getText());
                        commitEdit(getItem());
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().getValue();
        }

    }

    public ListView<GssDefine> getControlGssDefine() {
        return controlGssDefine;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane1 = new AnchorPane();
        anchorPane1.setId("AnchorPane");
        anchorPane1.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane1.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane1.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane1.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane1 = new TitledPane();
        titledPane1.setAnimated(false);
        titledPane1.setCollapsible(false);
        titledPane1.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane1.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane1.setText(bundle.getString("GSSDefinesSection"));
        AnchorPane.setBottomAnchor(titledPane1, 0.0);
        AnchorPane.setLeftAnchor(titledPane1, 0.0);
        AnchorPane.setRightAnchor(titledPane1, 0.0);
        AnchorPane.setTopAnchor(titledPane1, 0.0);
        GridPane gridPane5 = new GridPane();
        gridPane5.setId("GridPane");
        gridPane5.setVgap(5.0);
        Label label = new Label();
        label.setMaxWidth(1.7976931348623157E308);
        label.setMinWidth(100.0);
        label.setPrefWidth(100.0);
        label.setText(bundle.getString("GSSDefinesSection_Desc"));
        label.setWrapText(true);
        GridPane.setColumnIndex(label, 0);
        GridPane.setHalignment(label, HPos.LEFT);
        GridPane.setRowIndex(label, 0);
        GridPane.setVgrow(label, Priority.NEVER);
        gridPane5.getChildren().add(label);
        HBox hBox1 = new HBox();
        hBox1.setId("HBox");
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(5.0);
        GridPane.setColumnIndex(hBox1, 0);
        GridPane.setRowIndex(hBox1, 1);
        controlGssDefine = new ListView();
        controlGssDefine.setPrefHeight(100.0);
        controlGssDefine.setPrefWidth(Control.USE_COMPUTED_SIZE);
        HBox.setHgrow(controlGssDefine, Priority.ALWAYS);
        hBox1.getChildren().add(controlGssDefine);
        GridPane gridPane6 = new GridPane();
        gridPane6.setMinWidth(Control.USE_PREF_SIZE);
        gridPane6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane6.setVgap(5.0);
        Button button2 = new Button();
        button2.setMaxWidth(1.7976931348623157E308);
        button2.setMnemonicParsing(false);
        button2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        button2.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button2, 0);
        GridPane.setRowIndex(button2, 0);
        gridPane6.getChildren().add(button2);
        Button button3 = new Button();
        button3.setMaxWidth(1.7976931348623157E308);
        button3.setMnemonicParsing(false);
        button3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button3.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button3, 0);
        GridPane.setRowIndex(button3, 1);
        gridPane6.getChildren().add(button3);
        ColumnConstraints columnConstraints8 = new ColumnConstraints();
        columnConstraints8.setHgrow(Priority.NEVER);
        columnConstraints8.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints8.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane6.getColumnConstraints().add(columnConstraints8);
        RowConstraints rowConstraints5 = new RowConstraints();
        rowConstraints5.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints5.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints5.setVgrow(Priority.NEVER);
        gridPane6.getRowConstraints().add(rowConstraints5);
        RowConstraints rowConstraints6 = new RowConstraints();
        rowConstraints6.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints6.setVgrow(Priority.NEVER);
        gridPane6.getRowConstraints().add(rowConstraints6);
        hBox1.getChildren().add(gridPane6);
        gridPane5.getChildren().add(hBox1);
        ColumnConstraints columnConstraints9 = new ColumnConstraints();
        columnConstraints9.setHgrow(Priority.SOMETIMES);
        columnConstraints9.setMinWidth(10.0);
        gridPane5.getColumnConstraints().add(columnConstraints9);
        Insets insets6 = new Insets(10.0, 10.0, 10.0, 10.0);
        gridPane5.setPadding(insets6);
        RowConstraints rowConstraints7 = new RowConstraints();
        rowConstraints7.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints7.setVgrow(Priority.NEVER);
        gridPane5.getRowConstraints().add(rowConstraints7);
        RowConstraints rowConstraints8 = new RowConstraints();
        rowConstraints8.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints8.setVgrow(Priority.ALWAYS);
        gridPane5.getRowConstraints().add(rowConstraints8);
        titledPane1.setContent(gridPane5);
        anchorPane1.getChildren().add(titledPane1);
        initialize(null, bundle);
        return anchorPane1;
    }

}
