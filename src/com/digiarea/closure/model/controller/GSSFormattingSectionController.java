package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSFormattingSectionController extends ClosureController implements Initializable {

    public GSSFormattingSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private ComboBox<GssInputOrientation> controlInputOrientation;

    @FXML
    private ComboBox<GssOutputOrientation> controlOutputOrientation;

    @FXML
    private RadioButton controlCompressed;

    @FXML
    private RadioButton controlPrettyPrint;

    @FXML
    private RadioButton controlDebug;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlInputOrientation.setItems(FXCollections.observableArrayList(GssInputOrientation.values()));
        controlOutputOrientation.setItems(FXCollections.observableArrayList(GssOutputOrientation.values()));
    }

    public ComboBox<GssInputOrientation> getControlInputOrientation() {
        return controlInputOrientation;
    }

    public ComboBox<GssOutputOrientation> getControlOutputOrientation() {
        return controlOutputOrientation;
    }

    public RadioButton getControlCompressed() {
        return controlCompressed;
    }

    public RadioButton getControlPrettyPrint() {
        return controlPrettyPrint;
    }

    public RadioButton getControlDebug() {
        return controlDebug;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane2 = new AnchorPane();
        anchorPane2.setId("AnchorPane");
        anchorPane2.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane2.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane2.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane2.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane2 = new TitledPane();
        titledPane2.setAnimated(false);
        titledPane2.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane2.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane2.setText(bundle.getString("GSSFormattingSection"));
        AnchorPane.setBottomAnchor(titledPane2, 0.0);
        AnchorPane.setLeftAnchor(titledPane2, 0.0);
        AnchorPane.setRightAnchor(titledPane2, 0.0);
        AnchorPane.setTopAnchor(titledPane2, 0.0);
        VBox vBox4 = new VBox();
        vBox4.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox4.setSpacing(5.0);
        Label label1 = new Label();
        label1.setText(bundle.getString("GSSFormattingSection_Desc"));
        label1.setWrapText(true);
        vBox4.getChildren().add(label1);
        GridPane gridPane7 = new GridPane();
        gridPane7.setHgap(5.0);
        gridPane7.setMinHeight(Control.USE_PREF_SIZE);
        VBox.setVgrow(gridPane7, Priority.NEVER);
        controlCompressed = new RadioButton();
        controlCompressed.setMnemonicParsing(false);
        controlCompressed.setText(bundle.getString("GSSFormattingSection_Compressed"));
        GridPane.setColumnIndex(controlCompressed, 0);
        GridPane.setRowIndex(controlCompressed, 0);
        toggleGroup = new ToggleGroup();
        controlCompressed.setToggleGroup(toggleGroup);
        gridPane7.getChildren().add(controlCompressed);
        controlPrettyPrint = new RadioButton();
        controlPrettyPrint.setId("controlCompressed");
        controlPrettyPrint.setMnemonicParsing(false);
        controlPrettyPrint.setText(bundle.getString("GSSFormattingSection_PrettyPrint"));
        controlPrettyPrint.setToggleGroup(toggleGroup);
        GridPane.setColumnIndex(controlPrettyPrint, 1);
        GridPane.setRowIndex(controlPrettyPrint, 0);
        gridPane7.getChildren().add(controlPrettyPrint);
        controlDebug = new RadioButton();
        controlDebug.setMnemonicParsing(false);
        controlDebug.setText(bundle.getString("GSSFormattingSection_Debug"));
        controlDebug.setToggleGroup(toggleGroup);
        GridPane.setColumnIndex(controlDebug, 2);
        GridPane.setRowIndex(controlDebug, 0);
        gridPane7.getChildren().add(controlDebug);
        ColumnConstraints columnConstraints10 = new ColumnConstraints();
        columnConstraints10.setHgrow(Priority.NEVER);
        columnConstraints10.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints10.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane7.getColumnConstraints().add(columnConstraints10);
        ColumnConstraints columnConstraints11 = new ColumnConstraints();
        columnConstraints11.setHgrow(Priority.NEVER);
        columnConstraints11.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints11.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane7.getColumnConstraints().add(columnConstraints11);
        ColumnConstraints columnConstraints12 = new ColumnConstraints();
        columnConstraints12.setHgrow(Priority.NEVER);
        columnConstraints12.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints12.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane7.getColumnConstraints().add(columnConstraints12);
        RowConstraints rowConstraints9 = new RowConstraints();
        rowConstraints9.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints9.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints9.setVgrow(Priority.NEVER);
        gridPane7.getRowConstraints().add(rowConstraints9);
        vBox4.getChildren().add(gridPane7);
        GridPane gridPane8 = new GridPane();
        gridPane8.setAlignment(Pos.CENTER_LEFT);
        gridPane8.setHgap(5.0);
        gridPane8.setVgap(5.0);
        VBox.setVgrow(gridPane8, Priority.NEVER);
        controlInputOrientation = new ComboBox();
        controlInputOrientation.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlInputOrientation, 1);
        GridPane.setRowIndex(controlInputOrientation, 0);
        gridPane8.getChildren().add(controlInputOrientation);
        controlOutputOrientation = new ComboBox();
        controlOutputOrientation.setId("controlOutputOrienation");
        controlOutputOrientation.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlOutputOrientation, 1);
        GridPane.setRowIndex(controlOutputOrientation, 1);
        gridPane8.getChildren().add(controlOutputOrientation);
        Label label2 = new Label();
        label2.setContentDisplay(ContentDisplay.LEFT);
        label2.setText(bundle.getString("GSSFormattingSection_Input"));
        GridPane.setColumnIndex(label2, 0);
        GridPane.setHalignment(label2, HPos.RIGHT);
        GridPane.setRowIndex(label2, 0);
        gridPane8.getChildren().add(label2);
        Label label3 = new Label();
        label3.setText(bundle.getString("GSSFormattingSection_Output"));
        GridPane.setColumnIndex(label3, 0);
        GridPane.setHalignment(label3, HPos.RIGHT);
        GridPane.setRowIndex(label3, 1);
        gridPane8.getChildren().add(label3);
        ColumnConstraints columnConstraints13 = new ColumnConstraints();
        columnConstraints13.setHgrow(Priority.NEVER);
        columnConstraints13.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints13.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane8.getColumnConstraints().add(columnConstraints13);
        ColumnConstraints columnConstraints14 = new ColumnConstraints();
        columnConstraints14.setHgrow(Priority.ALWAYS);
        columnConstraints14.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints14.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane8.getColumnConstraints().add(columnConstraints14);
        RowConstraints rowConstraints10 = new RowConstraints();
        rowConstraints10.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints10.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints10.setVgrow(Priority.NEVER);
        gridPane8.getRowConstraints().add(rowConstraints10);
        RowConstraints rowConstraints11 = new RowConstraints();
        rowConstraints11.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints11.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints11.setVgrow(Priority.NEVER);
        gridPane8.getRowConstraints().add(rowConstraints11);
        vBox4.getChildren().add(gridPane8);
        Insets insets7 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox4.setPadding(insets7);
        titledPane2.setContent(vBox4);
        anchorPane2.getChildren().add(titledPane2);
        initialize(null, bundle);
        return anchorPane2;
    }

}
