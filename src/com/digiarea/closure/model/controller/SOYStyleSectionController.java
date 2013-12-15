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
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import java.net.URL;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYStyleSectionController extends ClosureController implements Initializable {

    public SOYStyleSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ToggleGroup styleGroup;

    @FXML
    private RadioButton controlCodeStyleBuilder;

    @FXML
    private RadioButton controlCodeStyleConcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public RadioButton getControlCodeStyleBuilder() {
        return controlCodeStyleBuilder;
    }

    public RadioButton getControlCodeStyleConcat() {
        return controlCodeStyleConcat;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane37 = new AnchorPane();
        anchorPane37.setId("AnchorPane");
        anchorPane37.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane37.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane37.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane37.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane33 = new TitledPane();
        titledPane33.setAnimated(false);
        titledPane33.setCollapsible(false);
        titledPane33.setExpanded(true);
        titledPane33.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane33.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane33.setText(bundle.getString("SOYStyleSection"));
        AnchorPane.setBottomAnchor(titledPane33, 0.0);
        AnchorPane.setLeftAnchor(titledPane33, 0.0);
        AnchorPane.setRightAnchor(titledPane33, 0.0);
        AnchorPane.setTopAnchor(titledPane33, 0.0);
        VBox vBox65 = new VBox();
        vBox65.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox65.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label80 = new Label();
        label80.setMinWidth(100.0);
        label80.setText(bundle.getString("SOYStyleSection_Desc"));
        label80.setWrapText(true);
        vBox65.getChildren().add(label80);
        GridPane gridPane74 = new GridPane();
        gridPane74.setHgap(5.0);
        controlCodeStyleBuilder = new RadioButton();
        controlCodeStyleBuilder.setMnemonicParsing(false);
        controlCodeStyleBuilder.setText(bundle.getString("SOYStyleSection_String"));
        GridPane.setColumnIndex(controlCodeStyleBuilder, 0);
        GridPane.setRowIndex(controlCodeStyleBuilder, 0);
        styleGroup = new ToggleGroup();
        controlCodeStyleBuilder.setToggleGroup(styleGroup);
        gridPane74.getChildren().add(controlCodeStyleBuilder);
        controlCodeStyleConcat = new RadioButton();
        controlCodeStyleConcat.setMnemonicParsing(false);
        controlCodeStyleConcat.setText(bundle.getString("SOYStyleSection_Concatenation"));
        controlCodeStyleConcat.setToggleGroup(styleGroup);
        GridPane.setColumnIndex(controlCodeStyleConcat, 1);
        GridPane.setRowIndex(controlCodeStyleConcat, 0);
        gridPane74.getChildren().add(controlCodeStyleConcat);
        ColumnConstraints columnConstraints168 = new ColumnConstraints();
        columnConstraints168.setHgrow(Priority.NEVER);
        columnConstraints168.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints168.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane74.getColumnConstraints().add(columnConstraints168);
        ColumnConstraints columnConstraints169 = new ColumnConstraints();
        columnConstraints169.setHgrow(Priority.NEVER);
        columnConstraints169.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints169.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane74.getColumnConstraints().add(columnConstraints169);
        RowConstraints rowConstraints147 = new RowConstraints();
        rowConstraints147.setMinHeight(10.0);
        rowConstraints147.setPrefHeight(30.0);
        rowConstraints147.setVgrow(Priority.SOMETIMES);
        gridPane74.getRowConstraints().add(rowConstraints147);
        vBox65.getChildren().add(gridPane74);
        Insets insets100 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox65.setPadding(insets100);
        titledPane33.setContent(vBox65);
        anchorPane37.getChildren().add(titledPane33);
        initialize(null, bundle);
        return anchorPane37;
    }

}
