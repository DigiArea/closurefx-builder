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
public class SOYInfoSectionController extends ClosureController implements Initializable {

    public SOYInfoSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
        AnchorPane anchorPane29 = new AnchorPane();
        anchorPane29.setId("AnchorPane");
        anchorPane29.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane29.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane29.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane29.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane26 = new TitledPane();
        titledPane26.setAnimated(true);
        titledPane26.setCollapsible(true);
        titledPane26.setExpanded(false);
        titledPane26.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane26.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane26.setText(bundle.getString("InfoSection"));
        AnchorPane.setBottomAnchor(titledPane26, 0.0);
        AnchorPane.setLeftAnchor(titledPane26, 0.0);
        AnchorPane.setRightAnchor(titledPane26, 0.0);
        AnchorPane.setTopAnchor(titledPane26, 0.0);
        VBox vBox54 = new VBox();
        vBox54.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox54.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label65 = new Label();
        label65.setText(bundle.getString("InfoSection_Desc"));
        label65.setWrapText(true);
        vBox54.getChildren().add(label65);
        GridPane gridPane61 = new GridPane();
        gridPane61.setMinHeight(Control.USE_PREF_SIZE);
        gridPane61.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane61.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(gridPane61, Priority.NEVER);
        Label label66 = new Label();
        label66.setText(bundle.getString("InfoSection_Id"));
        GridPane.setColumnIndex(label66, 0);
        GridPane.setRowIndex(label66, 0);
        gridPane61.getChildren().add(label66);
        Label label67 = new Label();
        label67.setText(bundle.getString("InfoSection_Name"));
        GridPane.setColumnIndex(label67, 0);
        GridPane.setRowIndex(label67, 1);
        gridPane61.getChildren().add(label67);
        Label label68 = new Label();
        label68.setText(bundle.getString("InfoSection_Version"));
        GridPane.setColumnIndex(label68, 0);
        GridPane.setRowIndex(label68, 2);
        gridPane61.getChildren().add(label68);
        Label label69 = new Label();
        label69.setText(bundle.getString("InfoSection_Vendor"));
        GridPane.setColumnIndex(label69, 0);
        GridPane.setRowIndex(label69, 3);
        gridPane61.getChildren().add(label69);
        controlId = new TextField();
        controlId.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlId, 1);
        GridPane.setRowIndex(controlId, 0);
        gridPane61.getChildren().add(controlId);
        controlName = new TextField();
        controlName.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlName, 1);
        GridPane.setRowIndex(controlName, 1);
        gridPane61.getChildren().add(controlName);
        controlVersion = new TextField();
        controlVersion.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVersion, 1);
        GridPane.setRowIndex(controlVersion, 2);
        gridPane61.getChildren().add(controlVersion);
        controlVendor = new TextField();
        controlVendor.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVendor, 1);
        GridPane.setRowIndex(controlVendor, 3);
        gridPane61.getChildren().add(controlVendor);
        ColumnConstraints columnConstraints137 = new ColumnConstraints();
        columnConstraints137.setHgrow(Priority.SOMETIMES);
        columnConstraints137.setMaxWidth(50.0);
        columnConstraints137.setMinWidth(50.0);
        columnConstraints137.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane61.getColumnConstraints().add(columnConstraints137);
        ColumnConstraints columnConstraints138 = new ColumnConstraints();
        columnConstraints138.setHgrow(Priority.SOMETIMES);
        columnConstraints138.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints138.setMinWidth(10.0);
        columnConstraints138.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane61.getColumnConstraints().add(columnConstraints138);
        RowConstraints rowConstraints118 = new RowConstraints();
        rowConstraints118.setMinHeight(10.0);
        rowConstraints118.setPrefHeight(25.0);
        rowConstraints118.setVgrow(Priority.NEVER);
        gridPane61.getRowConstraints().add(rowConstraints118);
        RowConstraints rowConstraints119 = new RowConstraints();
        rowConstraints119.setMinHeight(10.0);
        rowConstraints119.setPrefHeight(25.0);
        rowConstraints119.setVgrow(Priority.NEVER);
        gridPane61.getRowConstraints().add(rowConstraints119);
        RowConstraints rowConstraints120 = new RowConstraints();
        rowConstraints120.setMinHeight(10.0);
        rowConstraints120.setPrefHeight(25.0);
        rowConstraints120.setVgrow(Priority.NEVER);
        gridPane61.getRowConstraints().add(rowConstraints120);
        RowConstraints rowConstraints121 = new RowConstraints();
        rowConstraints121.setMinHeight(10.0);
        rowConstraints121.setPrefHeight(25.0);
        rowConstraints121.setVgrow(Priority.NEVER);
        gridPane61.getRowConstraints().add(rowConstraints121);
        vBox54.getChildren().add(gridPane61);
        Insets insets78 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox54.setPadding(insets78);
        titledPane26.setContent(vBox54);
        anchorPane29.getChildren().add(titledPane26);
        initialize(null, bundle);
        return anchorPane29;
    }

}
