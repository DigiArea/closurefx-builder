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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSLintingSectionController extends ClosureController implements Initializable {

    public GSSLintingSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private CheckBox controlAllowUnrecognizedFunctions;

    @FXML
    private CheckBox controlAllowUnrecognizedProperties;

    @FXML
    private ListView<GssNonStandardFunction> controlGssNonStandardFunction;

    @FXML
    private ListView<GssUnrecognizeProperty> controlGssUnrecognizeProperty;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlGssNonStandardFunction.setEditable(true);
        controlGssNonStandardFunction.setCellFactory(new GSSLintingSectionController.FunctionCellFactory());
        controlGssUnrecognizeProperty.setEditable(true);
        controlGssUnrecognizeProperty.setCellFactory(new GSSLintingSectionController.PropertiesCellFactory());
    }

    @FXML
    private void handleAddFunctionButtonAction(ActionEvent event) {
        GssNonStandardFunction function = new GssNonStandardFunction();
        function.setValue("func");
        modelFacade.addSOYFunction(function);
    }

    @FXML
    private void handleRemoveFunctionButtonAction(ActionEvent event) {
        ObservableList<GssNonStandardFunction> functions = controlGssNonStandardFunction.getSelectionModel().getSelectedItems();
        if (functions != null && !functions.isEmpty()) {
            for (GssNonStandardFunction func : functions) {
                modelFacade.removeSOYFunction(func);
            }
        }
    }

    @FXML
    private void handleAddPropertyButtonAction(ActionEvent event) {
        GssUnrecognizeProperty property = new GssUnrecognizeProperty();
        property.setValue("prop");
        modelFacade.addSOYProperty(property);
    }

    @FXML
    private void handleRemovePropertyButtonAction(ActionEvent event) {
        ObservableList<GssUnrecognizeProperty> properties = controlGssUnrecognizeProperty.getSelectionModel().getSelectedItems();
        if (properties != null && !properties.isEmpty()) {
            for (GssUnrecognizeProperty prop : properties) {
                modelFacade.removeSOYProperty(prop);
            }
        }
    }

    public CheckBox getControlAllowUnrecognizedFunctions() {
        return controlAllowUnrecognizedFunctions;
    }

    public CheckBox getControlAllowUnrecognizedProperties() {
        return controlAllowUnrecognizedProperties;
    }

    private class FunctionCellFactory implements Callback<ListView<GssNonStandardFunction>, ListCell<GssNonStandardFunction>> {

        @Override
        public ListCell<GssNonStandardFunction> call(ListView<GssNonStandardFunction> list) {
            return new com.digiarea.closure.model.controller.GSSLintingSectionController.FunctionsListCell();
        }

    }

    private class FunctionsListCell extends ListCell<GssNonStandardFunction> {

        private TextField textField;

        public FunctionsListCell() {
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
            setText(getString());
            setGraphic(null);
        }

        @Override
        public void updateItem(GssNonStandardFunction item, boolean empty) {
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
            if (getItem() != null) {
                return getItem().getValue();
            }
            return "";
        }

    }

    private class PropertiesCellFactory implements Callback<ListView<GssUnrecognizeProperty>, ListCell<GssUnrecognizeProperty>> {

        @Override
        public ListCell<GssUnrecognizeProperty> call(ListView<GssUnrecognizeProperty> list) {
            return new com.digiarea.closure.model.controller.GSSLintingSectionController.PropertiesListCell();
        }

    }

    private class PropertiesListCell extends ListCell<GssUnrecognizeProperty> {

        private TextField textField;

        public PropertiesListCell() {
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
            setText(getString());
            setGraphic(null);
        }

        @Override
        public void updateItem(GssUnrecognizeProperty item, boolean empty) {
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
            if (getItem() != null) {
                return getItem().getValue();
            }
            return "";
        }

    }

    public ListView<GssNonStandardFunction> getControlGssNonStandardFunction() {
        return controlGssNonStandardFunction;
    }

    public ListView<GssUnrecognizeProperty> getControlGssUnrecognizeProperty() {
        return controlGssUnrecognizeProperty;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane5 = new AnchorPane();
        anchorPane5.setId("AnchorPane");
        anchorPane5.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane5.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane5.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane5.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane5 = new TitledPane();
        titledPane5.setAnimated(false);
        titledPane5.setCollapsible(false);
        titledPane5.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane5.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane5.setText(bundle.getString("GSSLintingSection"));
        AnchorPane.setBottomAnchor(titledPane5, 0.0);
        AnchorPane.setLeftAnchor(titledPane5, 0.0);
        AnchorPane.setRightAnchor(titledPane5, 0.0);
        AnchorPane.setTopAnchor(titledPane5, 0.0);
        VBox vBox7 = new VBox();
        vBox7.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox7.setSpacing(5.0);
        Label label10 = new Label();
        label10.setText(bundle.getString("GSSLintingSection_Desc"));
        label10.setWrapText(true);
        vBox7.getChildren().add(label10);
        controlAllowUnrecognizedFunctions = new CheckBox();
        controlAllowUnrecognizedFunctions.setMnemonicParsing(false);
        controlAllowUnrecognizedFunctions.setText(bundle.getString("GSSLintingSection_Functions"));
        controlAllowUnrecognizedFunctions.setWrapText(true);
        vBox7.getChildren().add(controlAllowUnrecognizedFunctions);
        GridPane gridPane11 = new GridPane();
        gridPane11.setHgap(5.0);
        gridPane11.setMinHeight(Control.USE_COMPUTED_SIZE);
        gridPane11.setMinWidth(Control.USE_PREF_SIZE);
        gridPane11.setPrefHeight(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(gridPane11, Priority.ALWAYS);
        controlGssNonStandardFunction = new ListView();
        controlGssNonStandardFunction.setPrefHeight(100.0);
        controlGssNonStandardFunction.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlGssNonStandardFunction, 0);
        GridPane.setHgrow(controlGssNonStandardFunction, Priority.ALWAYS);
        GridPane.setRowIndex(controlGssNonStandardFunction, 0);
        GridPane.setVgrow(controlGssNonStandardFunction, Priority.ALWAYS);
        gridPane11.getChildren().add(controlGssNonStandardFunction);
        GridPane gridPane12 = new GridPane();
        gridPane12.setVgap(5.0);
        GridPane.setColumnIndex(gridPane12, 1);
        GridPane.setRowIndex(gridPane12, 0);
        GridPane.setVgrow(gridPane12, Priority.NEVER);
        Button button8 = new Button();
        button8.setMaxWidth(1.7976931348623157E308);
        button8.setMnemonicParsing(false);
        button8.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddFunctionButtonAction(event);
            }
        });
        button8.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button8, 0);
        GridPane.setRowIndex(button8, 0);
        gridPane12.getChildren().add(button8);
        Button button9 = new Button();
        button9.setMaxWidth(1.7976931348623157E308);
        button9.setMnemonicParsing(false);
        button9.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveFunctionButtonAction(event);
            }
        });
        button9.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button9, 0);
        GridPane.setRowIndex(button9, 1);
        gridPane12.getChildren().add(button9);
        ColumnConstraints columnConstraints18 = new ColumnConstraints();
        columnConstraints18.setHgrow(Priority.NEVER);
        columnConstraints18.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints18.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane12.getColumnConstraints().add(columnConstraints18);
        RowConstraints rowConstraints22 = new RowConstraints();
        rowConstraints22.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints22.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints22.setVgrow(Priority.NEVER);
        gridPane12.getRowConstraints().add(rowConstraints22);
        RowConstraints rowConstraints23 = new RowConstraints();
        rowConstraints23.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints23.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints23.setVgrow(Priority.NEVER);
        gridPane12.getRowConstraints().add(rowConstraints23);
        gridPane11.getChildren().add(gridPane12);
        ColumnConstraints columnConstraints19 = new ColumnConstraints();
        columnConstraints19.setFillWidth(true);
        columnConstraints19.setHgrow(Priority.ALWAYS);
        columnConstraints19.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints19.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane11.getColumnConstraints().add(columnConstraints19);
        ColumnConstraints columnConstraints20 = new ColumnConstraints();
        columnConstraints20.setHgrow(Priority.NEVER);
        columnConstraints20.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints20.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane11.getColumnConstraints().add(columnConstraints20);
        RowConstraints rowConstraints24 = new RowConstraints();
        rowConstraints24.setFillHeight(true);
        rowConstraints24.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints24.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints24.setVgrow(Priority.ALWAYS);
        gridPane11.getRowConstraints().add(rowConstraints24);
        vBox7.getChildren().add(gridPane11);
        controlAllowUnrecognizedProperties = new CheckBox();
        controlAllowUnrecognizedProperties.setMnemonicParsing(false);
        controlAllowUnrecognizedProperties.setText(bundle.getString("GSSLintingSection_Properties"));
        controlAllowUnrecognizedProperties.setWrapText(true);
        vBox7.getChildren().add(controlAllowUnrecognizedProperties);
        GridPane gridPane13 = new GridPane();
        gridPane13.setHgap(5.0);
        VBox.setVgrow(gridPane13, Priority.ALWAYS);
        controlGssUnrecognizeProperty = new ListView();
        controlGssUnrecognizeProperty.setPrefHeight(100.0);
        controlGssUnrecognizeProperty.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlGssUnrecognizeProperty, 0);
        GridPane.setHgrow(controlGssUnrecognizeProperty, Priority.ALWAYS);
        GridPane.setRowIndex(controlGssUnrecognizeProperty, 0);
        GridPane.setVgrow(controlGssUnrecognizeProperty, Priority.ALWAYS);
        gridPane13.getChildren().add(controlGssUnrecognizeProperty);
        GridPane gridPane14 = new GridPane();
        gridPane14.setVgap(5.0);
        GridPane.setColumnIndex(gridPane14, 1);
        GridPane.setRowIndex(gridPane14, 0);
        Button button10 = new Button();
        button10.setMaxWidth(1.7976931348623157E308);
        button10.setMnemonicParsing(false);
        button10.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddPropertyButtonAction(event);
            }
        });
        button10.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button10, 0);
        GridPane.setRowIndex(button10, 0);
        gridPane14.getChildren().add(button10);
        Button button11 = new Button();
        button11.setMaxWidth(1.7976931348623157E308);
        button11.setMnemonicParsing(false);
        button11.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemovePropertyButtonAction(event);
            }
        });
        button11.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button11, 0);
        GridPane.setRowIndex(button11, 1);
        gridPane14.getChildren().add(button11);
        ColumnConstraints columnConstraints21 = new ColumnConstraints();
        columnConstraints21.setHgrow(Priority.NEVER);
        columnConstraints21.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints21.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane14.getColumnConstraints().add(columnConstraints21);
        RowConstraints rowConstraints25 = new RowConstraints();
        rowConstraints25.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints25.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints25.setVgrow(Priority.NEVER);
        gridPane14.getRowConstraints().add(rowConstraints25);
        RowConstraints rowConstraints26 = new RowConstraints();
        rowConstraints26.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints26.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints26.setVgrow(Priority.NEVER);
        gridPane14.getRowConstraints().add(rowConstraints26);
        gridPane13.getChildren().add(gridPane14);
        ColumnConstraints columnConstraints22 = new ColumnConstraints();
        columnConstraints22.setHgrow(Priority.ALWAYS);
        columnConstraints22.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints22.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane13.getColumnConstraints().add(columnConstraints22);
        ColumnConstraints columnConstraints23 = new ColumnConstraints();
        columnConstraints23.setHgrow(Priority.NEVER);
        columnConstraints23.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints23.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane13.getColumnConstraints().add(columnConstraints23);
        RowConstraints rowConstraints27 = new RowConstraints();
        rowConstraints27.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints27.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints27.setVgrow(Priority.ALWAYS);
        gridPane13.getRowConstraints().add(rowConstraints27);
        vBox7.getChildren().add(gridPane13);
        Insets insets14 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox7.setPadding(insets14);
        titledPane5.setContent(vBox7);
        anchorPane5.getChildren().add(titledPane5);
        initialize(null, bundle);
        return anchorPane5;
    }

}
