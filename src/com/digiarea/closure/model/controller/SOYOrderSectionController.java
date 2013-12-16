package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.providers.BuildpathCell;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYOrderSectionController extends ClosureController implements Initializable {

    public SOYOrderSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<Source> controlSource;

    @FXML
    private ToggleButton controlExtern;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlSource.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Source>() {

            @Override
            public void changed(ObservableValue<? extends Source> observable, Source oldValue, Source newValue) {
                if (controlSource.getSelectionModel().getSelectedItem() != null) {
                    controlExtern.setSelected(controlSource.selectionModelProperty().getValue().getSelectedItem().isExtern());
                    boolean isSource = controlSource.selectionModelProperty().getValue().getSelectedItem().getEntryKind() == SourceEntry.SOURCE;
                    controlExtern.setDisable(isSource);
                }
            }
        });
        controlSource.setCellFactory(new Callback<ListView<Source>, ListCell<Source>>() {

            @Override
            public ListCell<Source> call(ListView<Source> list) {
                return new BuildpathCell(bundle, modelFacade.getDocument().getPathResolver());
            }
        });
    }

    @FXML
    private void handleExternButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        if (source != null) {
            modelFacade.setSourceExtern(SourceEntity.SOY, controlSource.selectionModelProperty().getValue().getSelectedItem(), controlExtern.isSelected());
            controlSource.getSelectionModel().select(source);
        }
    }

    @FXML
    private void handleUpButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceUp(source, SourceEntity.SOY);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleDownButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceDown(source, SourceEntity.SOY);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleTopButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceTop(source, SourceEntity.SOY);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleBottomButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceBottom(source, SourceEntity.SOY);
        controlSource.getSelectionModel().select(source);
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane33 = new AnchorPane();
        anchorPane33.setId("AnchorPane");
        anchorPane33.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane33.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane33.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane33.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane30 = new TitledPane();
        titledPane30.setAnimated(false);
        titledPane30.setCollapsible(false);
        titledPane30.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane30.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane30.setText(bundle.getString("SOYOrderSection"));
        AnchorPane.setBottomAnchor(titledPane30, 0.0);
        AnchorPane.setLeftAnchor(titledPane30, 0.0);
        AnchorPane.setRightAnchor(titledPane30, 0.0);
        AnchorPane.setTopAnchor(titledPane30, 0.0);
        VBox vBox58 = new VBox();
        vBox58.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox58.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox58.setSpacing(5.0);
        Label label77 = new Label();
        label77.setText(bundle.getString("SOYOrderSection_Desc"));
        label77.setWrapText(true);
        vBox58.getChildren().add(label77);
        controlExtern = new ToggleButton();
        controlExtern.setMnemonicParsing(false);
        controlExtern.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleExternButtonAction(event);
            }
        });
        controlExtern.setText("");
        ImageView imageView37 = new ImageView();
        imageView37.setFitHeight(8.0);
        imageView37.setFitWidth(8.0);
        imageView37.setMouseTransparent(true);
        imageView37.setPickOnBounds(true);
        imageView37.setPreserveRatio(true);
        Image image37 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/decorator-extern.png").openStream());
        imageView37.setImage(image37);
        controlExtern.setGraphic(imageView37);
        Tooltip tooltip20 = new Tooltip();
        tooltip20.setText(bundle.getString("SOYOrderSection_Extern"));
        controlExtern.setTooltip(tooltip20);
        vBox58.getChildren().add(controlExtern);
        HBox hBox14 = new HBox();
        hBox14.setPrefHeight(100.0);
        hBox14.setPrefWidth(200.0);
        hBox14.setSpacing(5.0);
        VBox.setVgrow(hBox14, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox14.getChildren().add(controlSource);
        GridPane gridPane69 = new GridPane();
        gridPane69.setId("GridPane");
        gridPane69.setMinWidth(Control.USE_PREF_SIZE);
        gridPane69.setVgap(5.0);
        Button button66 = new Button();
        button66.setMaxWidth(1.7976931348623157E308);
        button66.setMinWidth(Control.USE_PREF_SIZE);
        button66.setMnemonicParsing(false);
        button66.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleUpButtonAction(event);
            }
        });
        button66.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button66.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button66.setText(bundle.getString("Button_Up"));
        GridPane.setColumnIndex(button66, 0);
        GridPane.setRowIndex(button66, 0);
        gridPane69.getChildren().add(button66);
        Button button67 = new Button();
        button67.setMaxWidth(1.7976931348623157E308);
        button67.setMinWidth(Control.USE_PREF_SIZE);
        button67.setMnemonicParsing(false);
        button67.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleDownButtonAction(event);
            }
        });
        button67.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button67.setText(bundle.getString("Button_Down"));
        GridPane.setColumnIndex(button67, 0);
        GridPane.setRowIndex(button67, 1);
        gridPane69.getChildren().add(button67);
        Button button68 = new Button();
        button68.setMaxWidth(1.7976931348623157E308);
        button68.setMnemonicParsing(false);
        button68.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleTopButtonAction(event);
            }
        });
        button68.setText(bundle.getString("Button_Top"));
        GridPane.setColumnIndex(button68, 0);
        GridPane.setRowIndex(button68, 2);
        Insets insets90 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button68, insets90);
        gridPane69.getChildren().add(button68);
        Button button69 = new Button();
        button69.setMaxWidth(1.7976931348623157E308);
        button69.setMnemonicParsing(false);
        button69.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBottomButtonAction(event);
            }
        });
        button69.setText(bundle.getString("Button_Bottom"));
        GridPane.setColumnIndex(button69, 0);
        GridPane.setRowIndex(button69, 3);
        gridPane69.getChildren().add(button69);
        ColumnConstraints columnConstraints154 = new ColumnConstraints();
        columnConstraints154.setHgrow(Priority.SOMETIMES);
        columnConstraints154.setMinWidth(10.0);
        gridPane69.getColumnConstraints().add(columnConstraints154);
        RowConstraints rowConstraints136 = new RowConstraints();
        rowConstraints136.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints136.setVgrow(Priority.NEVER);
        gridPane69.getRowConstraints().add(rowConstraints136);
        RowConstraints rowConstraints137 = new RowConstraints();
        rowConstraints137.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints137.setVgrow(Priority.NEVER);
        gridPane69.getRowConstraints().add(rowConstraints137);
        RowConstraints rowConstraints138 = new RowConstraints();
        rowConstraints138.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints138.setVgrow(Priority.NEVER);
        gridPane69.getRowConstraints().add(rowConstraints138);
        RowConstraints rowConstraints139 = new RowConstraints();
        rowConstraints139.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints139.setVgrow(Priority.NEVER);
        gridPane69.getRowConstraints().add(rowConstraints139);
        hBox14.getChildren().add(gridPane69);
        vBox58.getChildren().add(hBox14);
        Insets insets91 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox58.setPadding(insets91);
        titledPane30.setContent(vBox58);
        anchorPane33.getChildren().add(titledPane30);
        initialize(null, bundle);
        return anchorPane33;
    }

}
