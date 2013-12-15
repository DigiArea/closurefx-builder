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
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSInfoSectionController extends ClosureController implements Initializable {

    public JSInfoSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
        AnchorPane anchorPane18 = new AnchorPane();
        anchorPane18.setId("AnchorPane");
        anchorPane18.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane18.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane18.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane18.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane16 = new TitledPane();
        titledPane16.setAnimated(true);
        titledPane16.setCollapsible(true);
        titledPane16.setExpanded(false);
        titledPane16.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane16.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane16.setText(bundle.getString("InfoSection"));
        AnchorPane.setBottomAnchor(titledPane16, 0.0);
        AnchorPane.setLeftAnchor(titledPane16, 0.0);
        AnchorPane.setRightAnchor(titledPane16, 0.0);
        AnchorPane.setTopAnchor(titledPane16, 0.0);
        VBox vBox29 = new VBox();
        vBox29.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox29.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label30 = new Label();
        label30.setText(bundle.getString("InfoSection_Desc"));
        label30.setWrapText(true);
        vBox29.getChildren().add(label30);
        GridPane gridPane39 = new GridPane();
        gridPane39.setMinHeight(Control.USE_PREF_SIZE);
        gridPane39.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane39.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(gridPane39, Priority.NEVER);
        Label label31 = new Label();
        label31.setText(bundle.getString("InfoSection_Id"));
        GridPane.setColumnIndex(label31, 0);
        GridPane.setRowIndex(label31, 0);
        gridPane39.getChildren().add(label31);
        Label label32 = new Label();
        label32.setText(bundle.getString("InfoSection_Name"));
        GridPane.setColumnIndex(label32, 0);
        GridPane.setRowIndex(label32, 1);
        gridPane39.getChildren().add(label32);
        Label label33 = new Label();
        label33.setText(bundle.getString("InfoSection_Version"));
        GridPane.setColumnIndex(label33, 0);
        GridPane.setRowIndex(label33, 2);
        gridPane39.getChildren().add(label33);
        Label label34 = new Label();
        label34.setText(bundle.getString("InfoSection_Vendor"));
        GridPane.setColumnIndex(label34, 0);
        GridPane.setRowIndex(label34, 3);
        gridPane39.getChildren().add(label34);
        controlId = new TextField();
        controlId.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlId, 1);
        GridPane.setRowIndex(controlId, 0);
        gridPane39.getChildren().add(controlId);
        controlName = new TextField();
        controlName.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlName, 1);
        GridPane.setRowIndex(controlName, 1);
        gridPane39.getChildren().add(controlName);
        controlVersion = new TextField();
        controlVersion.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVersion, 1);
        GridPane.setRowIndex(controlVersion, 2);
        gridPane39.getChildren().add(controlVersion);
        controlVendor = new TextField();
        controlVendor.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlVendor, 1);
        GridPane.setRowIndex(controlVendor, 3);
        gridPane39.getChildren().add(controlVendor);
        ColumnConstraints columnConstraints80 = new ColumnConstraints();
        columnConstraints80.setHgrow(Priority.SOMETIMES);
        columnConstraints80.setMaxWidth(50.0);
        columnConstraints80.setMinWidth(50.0);
        columnConstraints80.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane39.getColumnConstraints().add(columnConstraints80);
        ColumnConstraints columnConstraints81 = new ColumnConstraints();
        columnConstraints81.setHgrow(Priority.SOMETIMES);
        columnConstraints81.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints81.setMinWidth(10.0);
        columnConstraints81.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane39.getColumnConstraints().add(columnConstraints81);
        RowConstraints rowConstraints70 = new RowConstraints();
        rowConstraints70.setMinHeight(10.0);
        rowConstraints70.setPrefHeight(25.0);
        rowConstraints70.setVgrow(Priority.NEVER);
        gridPane39.getRowConstraints().add(rowConstraints70);
        RowConstraints rowConstraints71 = new RowConstraints();
        rowConstraints71.setMinHeight(10.0);
        rowConstraints71.setPrefHeight(25.0);
        rowConstraints71.setVgrow(Priority.NEVER);
        gridPane39.getRowConstraints().add(rowConstraints71);
        RowConstraints rowConstraints72 = new RowConstraints();
        rowConstraints72.setMinHeight(10.0);
        rowConstraints72.setPrefHeight(25.0);
        rowConstraints72.setVgrow(Priority.NEVER);
        gridPane39.getRowConstraints().add(rowConstraints72);
        RowConstraints rowConstraints73 = new RowConstraints();
        rowConstraints73.setMinHeight(10.0);
        rowConstraints73.setPrefHeight(25.0);
        rowConstraints73.setVgrow(Priority.NEVER);
        gridPane39.getRowConstraints().add(rowConstraints73);
        vBox29.getChildren().add(gridPane39);
        Insets insets43 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox29.setPadding(insets43);
        titledPane16.setContent(vBox29);
        anchorPane18.getChildren().add(titledPane16);
        initialize(null, bundle);
        return anchorPane18;
    }

}
