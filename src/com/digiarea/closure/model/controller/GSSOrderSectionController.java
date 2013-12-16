package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.providers.BuildpathCell;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSOrderSectionController extends ClosureController implements Initializable {

    public GSSOrderSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<Source> controlSource;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlSource.setCellFactory(new Callback<ListView<Source>, ListCell<Source>>() {

            @Override
            public ListCell<Source> call(ListView<Source> list) {
                return new BuildpathCell(bundle, modelFacade.getDocument().getPathResolver());
            }
        });
    }

    @FXML
    private void handleUpButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceUp(source, SourceEntity.GSS);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleDownButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceDown(source, SourceEntity.GSS);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleTopButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceTop(source, SourceEntity.GSS);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleBottomButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceBottom(source, SourceEntity.GSS);
        controlSource.getSelectionModel().select(source);
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane6 = new AnchorPane();
        anchorPane6.setId("AnchorPane");
        anchorPane6.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane6.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane6 = new TitledPane();
        titledPane6.setAnimated(false);
        titledPane6.setCollapsible(false);
        titledPane6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane6.setText(bundle.getString("GSSOrderSection"));
        AnchorPane.setBottomAnchor(titledPane6, 0.0);
        AnchorPane.setLeftAnchor(titledPane6, 0.0);
        AnchorPane.setRightAnchor(titledPane6, 0.0);
        AnchorPane.setTopAnchor(titledPane6, 0.0);
        VBox vBox8 = new VBox();
        vBox8.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox8.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label11 = new Label();
        label11.setText(bundle.getString("GSSOrderSection_Desc"));
        label11.setWrapText(true);
        vBox8.getChildren().add(label11);
        HBox hBox3 = new HBox();
        hBox3.setPrefHeight(100.0);
        hBox3.setPrefWidth(200.0);
        hBox3.setSpacing(5.0);
        VBox.setVgrow(hBox3, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox3.getChildren().add(controlSource);
        GridPane gridPane15 = new GridPane();
        gridPane15.setId("GridPane");
        gridPane15.setMinWidth(Control.USE_PREF_SIZE);
        gridPane15.setVgap(5.0);
        Button button12 = new Button();
        button12.setMaxWidth(1.7976931348623157E308);
        button12.setMinWidth(Control.USE_PREF_SIZE);
        button12.setMnemonicParsing(false);
        button12.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleUpButtonAction(event);
            }
        });
        button12.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button12.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button12.setText(bundle.getString("Button_Up"));
        GridPane.setColumnIndex(button12, 0);
        GridPane.setRowIndex(button12, 0);
        gridPane15.getChildren().add(button12);
        Button button13 = new Button();
        button13.setMaxWidth(1.7976931348623157E308);
        button13.setMinWidth(Control.USE_PREF_SIZE);
        button13.setMnemonicParsing(false);
        button13.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleDownButtonAction(event);
            }
        });
        button13.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button13.setText(bundle.getString("Button_Down"));
        GridPane.setColumnIndex(button13, 0);
        GridPane.setRowIndex(button13, 1);
        gridPane15.getChildren().add(button13);
        Button button14 = new Button();
        button14.setMaxWidth(1.7976931348623157E308);
        button14.setMnemonicParsing(false);
        button14.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleTopButtonAction(event);
            }
        });
        button14.setText(bundle.getString("Button_Top"));
        GridPane.setColumnIndex(button14, 0);
        GridPane.setRowIndex(button14, 2);
        Insets insets15 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button14, insets15);
        gridPane15.getChildren().add(button14);
        Button button15 = new Button();
        button15.setMaxWidth(1.7976931348623157E308);
        button15.setMnemonicParsing(false);
        button15.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBottomButtonAction(event);
            }
        });
        button15.setText(bundle.getString("Button_Bottom"));
        GridPane.setColumnIndex(button15, 0);
        GridPane.setRowIndex(button15, 3);
        gridPane15.getChildren().add(button15);
        ColumnConstraints columnConstraints24 = new ColumnConstraints();
        columnConstraints24.setHgrow(Priority.SOMETIMES);
        columnConstraints24.setMinWidth(10.0);
        gridPane15.getColumnConstraints().add(columnConstraints24);
        RowConstraints rowConstraints28 = new RowConstraints();
        rowConstraints28.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints28.setVgrow(Priority.NEVER);
        gridPane15.getRowConstraints().add(rowConstraints28);
        RowConstraints rowConstraints29 = new RowConstraints();
        rowConstraints29.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints29.setVgrow(Priority.NEVER);
        gridPane15.getRowConstraints().add(rowConstraints29);
        RowConstraints rowConstraints30 = new RowConstraints();
        rowConstraints30.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints30.setVgrow(Priority.NEVER);
        gridPane15.getRowConstraints().add(rowConstraints30);
        RowConstraints rowConstraints31 = new RowConstraints();
        rowConstraints31.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints31.setVgrow(Priority.NEVER);
        gridPane15.getRowConstraints().add(rowConstraints31);
        hBox3.getChildren().add(gridPane15);
        vBox8.getChildren().add(hBox3);
        Insets insets16 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox8.setPadding(insets16);
        titledPane6.setContent(vBox8);
        anchorPane6.getChildren().add(titledPane6);
        initialize(null, bundle);
        return anchorPane6;
    }

}
