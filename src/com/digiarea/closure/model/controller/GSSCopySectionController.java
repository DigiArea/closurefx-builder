package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSCopySectionController extends ClosureController implements Initializable {

    public GSSCopySectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextArea controlCopyrightNotice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public TextArea getControlCopyrightNotice() {
        return controlCopyrightNotice;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("AnchorPane");
        anchorPane.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane = new TitledPane();
        titledPane.setAnimated(false);
        titledPane.setCollapsible(false);
        titledPane.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane.setText(bundle.getString("GSSCopySection"));
        AnchorPane.setBottomAnchor(titledPane, 0.0);
        AnchorPane.setLeftAnchor(titledPane, 0.0);
        AnchorPane.setRightAnchor(titledPane, 0.0);
        AnchorPane.setTopAnchor(titledPane, 0.0);
        VBox vBox3 = new VBox();
        vBox3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox3.setSpacing(5.0);
        controlCopyrightNotice = new TextArea();
        controlCopyrightNotice.setMinHeight(50.0);
        controlCopyrightNotice.setMinWidth(100.0);
        controlCopyrightNotice.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlCopyrightNotice.setPromptText(bundle.getString("GSSCopySection_Desc"));
        controlCopyrightNotice.setWrapText(true);
        VBox.setVgrow(controlCopyrightNotice, Priority.ALWAYS);
        vBox3.getChildren().add(controlCopyrightNotice);
        Insets insets5 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox3.setPadding(insets5);
        titledPane.setContent(vBox3);
        anchorPane.getChildren().add(titledPane);
        initialize(null, bundle);
        return anchorPane;
    }

}
