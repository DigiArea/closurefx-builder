package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
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
public class JSFormattingSectionController extends ClosureController implements Initializable {

    public JSFormattingSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private CheckBox controlPrettyPrint;

    @FXML
    private CheckBox controlPrintInputDelimeter;

    @FXML
    private CheckBox controlSingleQuotes;

    @FXML
    private CheckBox controlLineBreaks;

    @FXML
    private CheckBox controlLineBreaksAggressive;

    @FXML
    private TextField controlInputDelimiter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public CheckBox getControlPrettyPrint() {
        return controlPrettyPrint;
    }

    public CheckBox getControlPrintInputDelimeter() {
        return controlPrintInputDelimeter;
    }

    public CheckBox getControlSingleQuotes() {
        return controlSingleQuotes;
    }

    public CheckBox getControlLineBreaks() {
        return controlLineBreaks;
    }

    public CheckBox getControlLineBreaksAggressive() {
        return controlLineBreaksAggressive;
    }

    public TextField getControlInputDelimiter() {
        return controlInputDelimiter;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane16 = new AnchorPane();
        anchorPane16.setId("AnchorPane");
        anchorPane16.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane16.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane16.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane16.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane15 = new TitledPane();
        titledPane15.setAnimated(false);
        titledPane15.setCollapsible(false);
        titledPane15.setMinWidth(Control.USE_COMPUTED_SIZE);
        titledPane15.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane15.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane15.setText(bundle.getString("JSFormattingSection"));
        AnchorPane.setBottomAnchor(titledPane15, 0.0);
        AnchorPane.setLeftAnchor(titledPane15, 0.0);
        AnchorPane.setRightAnchor(titledPane15, 0.0);
        AnchorPane.setTopAnchor(titledPane15, 0.0);
        VBox vBox26 = new VBox();
        vBox26.setId("VBox");
        vBox26.setAlignment(Pos.TOP_LEFT);
        vBox26.setSpacing(5.0);
        Label label26 = new Label();
        label26.setText(bundle.getString("JSFormattingSection_Desc"));
        label26.setWrapText(true);
        vBox26.getChildren().add(label26);
        VBox vBox27 = new VBox();
        vBox27.setId("VBox");
        vBox27.setAlignment(Pos.CENTER_LEFT);
        vBox27.setSpacing(5.0);
        GridPane gridPane37 = new GridPane();
        gridPane37.setId("GridPane");
        gridPane37.setHgap(5.0);
        gridPane37.setMinHeight(Control.USE_COMPUTED_SIZE);
        gridPane37.setMinWidth(Control.USE_COMPUTED_SIZE);
        gridPane37.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane37.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlPrintInputDelimeter = new CheckBox();
        controlPrintInputDelimeter.setId("btnPrettyPrint");
        controlPrintInputDelimeter.setMnemonicParsing(false);
        controlPrintInputDelimeter.setText(bundle.getString("JSFormattingSection_InputDelimiter"));
        GridPane.setColumnIndex(controlPrintInputDelimeter, 1);
        GridPane.setRowIndex(controlPrintInputDelimeter, 0);
        gridPane37.getChildren().add(controlPrintInputDelimeter);
        controlInputDelimiter = new TextField();
        controlInputDelimiter.setId("txtInputDelimiter");
        controlInputDelimiter.setAlignment(Pos.CENTER_LEFT);
        controlInputDelimiter.setEditable(true);
        controlInputDelimiter.setMaxWidth(Control.USE_COMPUTED_SIZE);
        controlInputDelimiter.setMinWidth(100.0);
        controlInputDelimiter.setPrefHeight(Control.USE_COMPUTED_SIZE);
        controlInputDelimiter.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlInputDelimiter, 2);
        GridPane.setRowIndex(controlInputDelimiter, 0);
        gridPane37.getChildren().add(controlInputDelimiter);
        controlPrettyPrint = new CheckBox();
        controlPrettyPrint.setId("btnPrettyPrint");
        controlPrettyPrint.setMnemonicParsing(false);
        controlPrettyPrint.setText(bundle.getString("JSFormattingSection_PrettyPrint"));
        GridPane.setColumnIndex(controlPrettyPrint, 0);
        GridPane.setRowIndex(controlPrettyPrint, 0);
        gridPane37.getChildren().add(controlPrettyPrint);
        ColumnConstraints columnConstraints75 = new ColumnConstraints();
        columnConstraints75.setHgrow(Priority.NEVER);
        columnConstraints75.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints75.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints75.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane37.getColumnConstraints().add(columnConstraints75);
        ColumnConstraints columnConstraints76 = new ColumnConstraints();
        columnConstraints76.setHgrow(Priority.NEVER);
        columnConstraints76.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints76.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints76.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane37.getColumnConstraints().add(columnConstraints76);
        ColumnConstraints columnConstraints77 = new ColumnConstraints();
        columnConstraints77.setHgrow(Priority.SOMETIMES);
        columnConstraints77.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints77.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints77.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane37.getColumnConstraints().add(columnConstraints77);
        RowConstraints rowConstraints65 = new RowConstraints();
        rowConstraints65.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints65.setVgrow(Priority.NEVER);
        gridPane37.getRowConstraints().add(rowConstraints65);
        vBox27.getChildren().add(gridPane37);
        controlSingleQuotes = new CheckBox();
        controlSingleQuotes.setId("btnLineBreaks");
        controlSingleQuotes.setMnemonicParsing(false);
        controlSingleQuotes.setText(bundle.getString("JSFormattingSection_SingleQuotes"));
        vBox27.getChildren().add(controlSingleQuotes);
        controlLineBreaks = new CheckBox();
        controlLineBreaks.setId("btnLineBreaks");
        controlLineBreaks.setMnemonicParsing(false);
        controlLineBreaks.setText(bundle.getString("JSFormattingSection_LineBreaks"));
        vBox27.getChildren().add(controlLineBreaks);
        controlLineBreaksAggressive = new CheckBox();
        controlLineBreaksAggressive.setId("btnAggressiveLineBreaks");
        controlLineBreaksAggressive.setMnemonicParsing(false);
        controlLineBreaksAggressive.setText(bundle.getString("JSFormattingSection_AggressiveLineBreaks"));
        vBox27.getChildren().add(controlLineBreaksAggressive);
        vBox26.getChildren().add(vBox27);
        Insets insets41 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox26.setPadding(insets41);
        titledPane15.setContent(vBox26);
        anchorPane16.getChildren().add(titledPane15);
        initialize(null, bundle);
        return anchorPane16;
    }

}
