package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSInfoSectionController extends ClosureController implements Initializable {

    public GSSInfoSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextField controlId;

    @FXML
    private TextField controlVersion;

    @FXML
    private TextField controlName;

    @FXML
    private TextField controlVendor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public TextField getControlId() {
        return controlId;
    }

    public TextField getControlVersion() {
        return controlVersion;
    }

    public TextField getControlName() {
        return controlName;
    }

    public TextField getControlVendor() {
        return controlVendor;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane3 = new AnchorPane();
        anchorPane3.setId("AnchorPane");
        anchorPane3.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane3.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane3 = new TitledPane();
        titledPane3.setAnimated(true);
        titledPane3.setCollapsible(true);
        titledPane3.setExpanded(false);
        titledPane3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane3.setText(bundle.getString("InfoSection"));
        AnchorPane.setBottomAnchor(titledPane3, 0.0);
        AnchorPane.setLeftAnchor(titledPane3, 0.0);
        AnchorPane.setRightAnchor(titledPane3, 0.0);
        AnchorPane.setTopAnchor(titledPane3, 0.0);
        VBox vBox5 = new VBox();
        vBox5.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox5.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label4 = new Label();
        label4.setText(bundle.getString("InfoSection_Desc"));
        label4.setWrapText(true);
        vBox5.getChildren().add(label4);
        GridPane gridPane9 = new GridPane();
        gridPane9.setMinHeight(Control.USE_PREF_SIZE);
        gridPane9.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane9.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(gridPane9, Priority.NEVER);
        Label label5 = new Label();
        label5.setText(bundle.getString("InfoSection_Id"));
        GridPane.setColumnIndex(label5, 0);
        GridPane.setRowIndex(label5, 0);
        gridPane9.getChildren().add(label5);
        Label label6 = new Label();
        label6.setText(bundle.getString("InfoSection_Name"));
        GridPane.setColumnIndex(label6, 0);
        GridPane.setRowIndex(label6, 1);
        gridPane9.getChildren().add(label6);
        Label label7 = new Label();
        label7.setText(bundle.getString("InfoSection_Version"));
        GridPane.setColumnIndex(label7, 0);
        GridPane.setRowIndex(label7, 2);
        gridPane9.getChildren().add(label7);
        Label label8 = new Label();
        label8.setText(bundle.getString("InfoSection_Vendor"));
        GridPane.setColumnIndex(label8, 0);
        GridPane.setRowIndex(label8, 3);
        gridPane9.getChildren().add(label8);
        controlId = new TextField();
        controlId.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlId, 1);
        GridPane.setRowIndex(controlId, 0);
        gridPane9.getChildren().add(controlId);
        controlName = new TextField();
        controlName.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlName, 1);
        GridPane.setRowIndex(controlName, 1);
        gridPane9.getChildren().add(controlName);
        controlVersion = new TextField();
        controlVersion.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVersion, 1);
        GridPane.setRowIndex(controlVersion, 2);
        gridPane9.getChildren().add(controlVersion);
        controlVendor = new TextField();
        controlVendor.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVendor, 1);
        GridPane.setRowIndex(controlVendor, 3);
        gridPane9.getChildren().add(controlVendor);
        ColumnConstraints columnConstraints15 = new ColumnConstraints();
        columnConstraints15.setHgrow(Priority.SOMETIMES);
        columnConstraints15.setMaxWidth(50.0);
        columnConstraints15.setMinWidth(50.0);
        columnConstraints15.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane9.getColumnConstraints().add(columnConstraints15);
        ColumnConstraints columnConstraints16 = new ColumnConstraints();
        columnConstraints16.setHgrow(Priority.SOMETIMES);
        columnConstraints16.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints16.setMinWidth(10.0);
        columnConstraints16.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane9.getColumnConstraints().add(columnConstraints16);
        RowConstraints rowConstraints12 = new RowConstraints();
        rowConstraints12.setMinHeight(10.0);
        rowConstraints12.setPrefHeight(25.0);
        rowConstraints12.setVgrow(Priority.NEVER);
        gridPane9.getRowConstraints().add(rowConstraints12);
        RowConstraints rowConstraints13 = new RowConstraints();
        rowConstraints13.setMinHeight(10.0);
        rowConstraints13.setPrefHeight(25.0);
        rowConstraints13.setVgrow(Priority.NEVER);
        gridPane9.getRowConstraints().add(rowConstraints13);
        RowConstraints rowConstraints14 = new RowConstraints();
        rowConstraints14.setMinHeight(10.0);
        rowConstraints14.setPrefHeight(25.0);
        rowConstraints14.setVgrow(Priority.NEVER);
        gridPane9.getRowConstraints().add(rowConstraints14);
        RowConstraints rowConstraints15 = new RowConstraints();
        rowConstraints15.setMinHeight(10.0);
        rowConstraints15.setPrefHeight(25.0);
        rowConstraints15.setVgrow(Priority.NEVER);
        gridPane9.getRowConstraints().add(rowConstraints15);
        vBox5.getChildren().add(gridPane9);
        Insets insets8 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox5.setPadding(insets8);
        titledPane3.setContent(vBox5);
        anchorPane3.getChildren().add(titledPane3);
        initialize(null, bundle);
        return anchorPane3;
    }

}
