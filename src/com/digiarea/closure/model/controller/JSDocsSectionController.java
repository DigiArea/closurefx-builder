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
import com.digiarea.closure.model.JsDoc;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSDocsSectionController extends ClosureController implements Initializable {

    public JSDocsSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<JsDoc> controlJsDoc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlJsDoc.setEditable(true);
        controlJsDoc.setCellFactory(new JSDocsSectionController.DocsCellFactory());
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        JsDoc doc = new JsDoc();
        doc.setValue("new");
        modelFacade.addJSDoc(doc);
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<JsDoc> docs = controlJsDoc.getSelectionModel().getSelectedItems();
        if (docs != null && !docs.isEmpty()) {
            for (JsDoc doc : docs) {
                modelFacade.removeJSDoc(doc);
            }
        }
    }

    public ListView<JsDoc> getControlJsDoc() {
        return controlJsDoc;
    }

    private class DocsCellFactory implements Callback<ListView<JsDoc>, ListCell<JsDoc>> {

        @Override
        public ListCell<JsDoc> call(ListView<JsDoc> list) {
            return new com.digiarea.closure.model.controller.JSDocsSectionController.DocsListCell();
        }

    }

    private class DocsListCell extends ListCell<JsDoc> {

        private TextField textField;

        public DocsListCell() {
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
        public void updateItem(JsDoc item, boolean empty) {
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

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane14 = new AnchorPane();
        anchorPane14.setId("AnchorPane");
        anchorPane14.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane14.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane14.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane14.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane13 = new TitledPane();
        titledPane13.setAnimated(false);
        titledPane13.setCollapsible(false);
        titledPane13.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane13.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane13.setText(bundle.getString("JSDocsSection"));
        AnchorPane.setBottomAnchor(titledPane13, 0.0);
        AnchorPane.setLeftAnchor(titledPane13, 0.0);
        AnchorPane.setRightAnchor(titledPane13, 0.0);
        AnchorPane.setTopAnchor(titledPane13, 0.0);
        VBox vBox24 = new VBox();
        vBox24.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox24.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox24.setSpacing(5.0);
        Label label24 = new Label();
        label24.setMaxWidth(1.7976931348623157E308);
        label24.setPrefWidth(100.0);
        label24.setText(bundle.getString("JSDocsSection_Desc"));
        label24.setWrapText(true);
        vBox24.getChildren().add(label24);
        HBox hBox7 = new HBox();
        hBox7.setPrefHeight(Control.USE_COMPUTED_SIZE);
        hBox7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        hBox7.setSpacing(5.0);
        VBox.setVgrow(hBox7, Priority.ALWAYS);
        controlJsDoc = new ListView();
        controlJsDoc.setPrefHeight(50.0);
        controlJsDoc.setPrefWidth(50.0);
        HBox.setHgrow(controlJsDoc, Priority.ALWAYS);
        hBox7.getChildren().add(controlJsDoc);
        GridPane gridPane35 = new GridPane();
        gridPane35.setId("GridPane");
        gridPane35.setMinWidth(Control.USE_PREF_SIZE);
        gridPane35.setVgap(5.0);
        Button button29 = new Button();
        button29.setMaxWidth(1.7976931348623157E308);
        button29.setMnemonicParsing(false);
        button29.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        button29.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button29, 0);
        GridPane.setRowIndex(button29, 0);
        gridPane35.getChildren().add(button29);
        Button button30 = new Button();
        button30.setMaxWidth(1.7976931348623157E308);
        button30.setMnemonicParsing(false);
        button30.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button30.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button30, 0);
        GridPane.setRowIndex(button30, 1);
        Insets insets36 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button30, insets36);
        gridPane35.getChildren().add(button30);
        ColumnConstraints columnConstraints70 = new ColumnConstraints();
        columnConstraints70.setHgrow(Priority.SOMETIMES);
        columnConstraints70.setMinWidth(10.0);
        gridPane35.getColumnConstraints().add(columnConstraints70);
        RowConstraints rowConstraints61 = new RowConstraints();
        rowConstraints61.setMaxHeight(19.0);
        rowConstraints61.setPrefHeight(19.0);
        rowConstraints61.setVgrow(Priority.NEVER);
        gridPane35.getRowConstraints().add(rowConstraints61);
        RowConstraints rowConstraints62 = new RowConstraints();
        rowConstraints62.setMaxHeight(0.0);
        rowConstraints62.setPrefHeight(0.0);
        rowConstraints62.setVgrow(Priority.NEVER);
        gridPane35.getRowConstraints().add(rowConstraints62);
        hBox7.getChildren().add(gridPane35);
        vBox24.getChildren().add(hBox7);
        Insets insets37 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox24.setPadding(insets37);
        titledPane13.setContent(vBox24);
        anchorPane14.getChildren().add(titledPane13);
        initialize(null, bundle);
        return anchorPane14;
    }

}
