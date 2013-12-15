package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closure.model.GssVendor;
import javafx.scene.control.ComboBox;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.scene.control.Control;
import javafx.geometry.HPos;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSVendorSectionController extends ClosureController implements Initializable {

    public GSSVendorSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ComboBox<GssVendor> controlVendor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlVendor.setItems(FXCollections.observableArrayList(GssVendor.values()));
    }

    public ComboBox<GssVendor> getControlVendor() {
        return controlVendor;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane11 = new AnchorPane();
        anchorPane11.setId("AnchorPane");
        anchorPane11.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane11.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane11.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane11.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane10 = new TitledPane();
        titledPane10.setAnimated(false);
        titledPane10.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane10.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane10.setText(bundle.getString("GSSVendorSection"));
        AnchorPane.setBottomAnchor(titledPane10, 0.0);
        AnchorPane.setLeftAnchor(titledPane10, 0.0);
        AnchorPane.setRightAnchor(titledPane10, 0.0);
        AnchorPane.setTopAnchor(titledPane10, 0.0);
        GridPane gridPane27 = new GridPane();
        gridPane27.setId("GridPane");
        gridPane27.setMinHeight(Control.USE_PREF_SIZE);
        gridPane27.setMinWidth(Control.USE_PREF_SIZE);
        gridPane27.setVgap(5.0);
        Label label21 = new Label();
        label21.setMaxHeight(Control.USE_COMPUTED_SIZE);
        label21.setMaxWidth(1.7976931348623157E308);
        label21.setMinHeight(Control.USE_COMPUTED_SIZE);
        label21.setMinWidth(100.0);
        label21.setPrefWidth(100.0);
        label21.setText(bundle.getString("GSSVendorSection_Desc"));
        label21.setWrapText(true);
        GridPane.setColumnIndex(label21, 0);
        GridPane.setHalignment(label21, HPos.LEFT);
        GridPane.setRowIndex(label21, 0);
        GridPane.setVgrow(label21, Priority.NEVER);
        gridPane27.getChildren().add(label21);
        controlVendor = new ComboBox();
        controlVendor.setMaxWidth(1.7976931348623157E308);
        controlVendor.setMinWidth(100.0);
        controlVendor.setPrefWidth(100.0);
        GridPane.setColumnIndex(controlVendor, 0);
        GridPane.setRowIndex(controlVendor, 1);
        gridPane27.getChildren().add(controlVendor);
        ColumnConstraints columnConstraints53 = new ColumnConstraints();
        columnConstraints53.setHgrow(Priority.SOMETIMES);
        columnConstraints53.setMaxWidth(1.7976931348623157E308);
        columnConstraints53.setMinWidth(100.0);
        columnConstraints53.setPrefWidth(200.0);
        gridPane27.getColumnConstraints().add(columnConstraints53);
        Insets insets27 = new Insets(10.0, 10.0, 10.0, 10.0);
        gridPane27.setPadding(insets27);
        RowConstraints rowConstraints50 = new RowConstraints();
        rowConstraints50.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints50.setVgrow(Priority.NEVER);
        gridPane27.getRowConstraints().add(rowConstraints50);
        RowConstraints rowConstraints51 = new RowConstraints();
        rowConstraints51.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints51.setVgrow(Priority.NEVER);
        gridPane27.getRowConstraints().add(rowConstraints51);
        titledPane10.setContent(gridPane27);
        anchorPane11.getChildren().add(titledPane10);
        initialize(null, bundle);
        return anchorPane11;
    }

}
