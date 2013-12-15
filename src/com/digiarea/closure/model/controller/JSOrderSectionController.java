package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closure.model.Source;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.digiarea.closure.model.SourceEntry;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import com.digiarea.closure.model.providers.BuildpathCell;
import javafx.event.ActionEvent;
import com.digiarea.closure.model.SourceEntity;
import javafx.scene.control.Control;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSOrderSectionController extends ClosureController implements Initializable {

    public JSOrderSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<Source> controlSource;

    @FXML
    private ToggleButton controlExtern;

    @FXML
    private ToggleButton controlClosureButton;

    @FXML
    private ToggleButton controlSimpleButton;

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
                    boolean isClosure = controlSource.selectionModelProperty().getValue().getSelectedItem().getEntryKind() == SourceEntry.CLOSURE;
                    controlExtern.setDisable(isSource);
                    controlClosureButton.setSelected(controlSource.selectionModelProperty().getValue().getSelectedItem().isIncludeClosure());
                    controlSimpleButton.setSelected(controlSource.selectionModelProperty().getValue().getSelectedItem().isIncludeSimple());
                    controlClosureButton.setDisable(isSource || isClosure);
                    controlSimpleButton.setDisable(isSource || isClosure);
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
    private void handleMarkClosureButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        if (source != null) {
            modelFacade.setJSSourceIncludeClosure(SourceEntity.JSC, controlSource.selectionModelProperty().getValue().getSelectedItem(), controlClosureButton.isSelected());
            controlSource.getSelectionModel().select(source);
        }
    }

    @FXML
    private void handleMarkSimpleButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        if (source != null) {
            modelFacade.setJSSourceIncludeSimple(SourceEntity.JSC, controlSource.selectionModelProperty().getValue().getSelectedItem(), controlSimpleButton.isSelected());
            controlSource.getSelectionModel().select(source);
        }
    }

    @FXML
    private void handleExternButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        if (source != null) {
            modelFacade.setSourceExtern(SourceEntity.JSC, controlSource.selectionModelProperty().getValue().getSelectedItem(), controlExtern.isSelected());
            controlSource.getSelectionModel().select(source);
        }
    }

    @FXML
    private void handleUpButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceUp(source, SourceEntity.JSC);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleDownButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceDown(source, SourceEntity.JSC);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleTopButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceTop(source, SourceEntity.JSC);
        controlSource.getSelectionModel().select(source);
    }

    @FXML
    private void handleBottomButtonAction(ActionEvent event) {
        Source source = controlSource.getSelectionModel().getSelectedItem();
        modelFacade.moveSourceBottom(source, SourceEntity.JSC);
        controlSource.getSelectionModel().select(source);
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public ToggleButton getControlExtern() {
        return controlExtern;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane21 = new AnchorPane();
        anchorPane21.setId("AnchorPane");
        anchorPane21.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane21.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane21.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane21.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane19 = new TitledPane();
        titledPane19.setAnimated(false);
        titledPane19.setCollapsible(false);
        titledPane19.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane19.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane19.setText(bundle.getString("OrderSection"));
        AnchorPane.setBottomAnchor(titledPane19, 0.0);
        AnchorPane.setLeftAnchor(titledPane19, 0.0);
        AnchorPane.setRightAnchor(titledPane19, 0.0);
        AnchorPane.setTopAnchor(titledPane19, 0.0);
        VBox vBox32 = new VBox();
        vBox32.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox32.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox32.setSpacing(5.0);
        Label label40 = new Label();
        label40.setText(bundle.getString("OrderSection_Desc"));
        label40.setWrapText(true);
        vBox32.getChildren().add(label40);
        HBox hBox9 = new HBox();
        hBox9.setPrefHeight(Control.USE_COMPUTED_SIZE);
        hBox9.setPrefWidth(Control.USE_COMPUTED_SIZE);
        hBox9.setSpacing(5.0);
        controlExtern = new ToggleButton();
        controlExtern.setMnemonicParsing(false);
        controlExtern.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleExternButtonAction(event);
            }
        });
        controlExtern.setText("");
        ImageView imageView24 = new ImageView();
        imageView24.setFitHeight(8.0);
        imageView24.setFitWidth(8.0);
        imageView24.setMouseTransparent(true);
        imageView24.setPickOnBounds(true);
        imageView24.setPreserveRatio(true);
        Image image24 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/decorator-extern.png").openStream());
        imageView24.setImage(image24);
        controlExtern.setGraphic(imageView24);
        Tooltip tooltip10 = new Tooltip();
        tooltip10.setText(bundle.getString("OrderSection_Extern"));
        controlExtern.setTooltip(tooltip10);
        hBox9.getChildren().add(controlExtern);
        controlClosureButton = new ToggleButton();
        controlClosureButton.setId("controlExtern");
        controlClosureButton.setMnemonicParsing(false);
        controlClosureButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleMarkClosureButtonAction(event);
            }
        });
        controlClosureButton.setText("");
        ImageView imageView25 = new ImageView();
        imageView25.setFitHeight(8.0);
        imageView25.setFitWidth(8.0);
        imageView25.setMouseTransparent(true);
        imageView25.setPickOnBounds(true);
        imageView25.setPreserveRatio(true);
        Image image25 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/decorator-closure.png").openStream());
        imageView25.setImage(image25);
        controlClosureButton.setGraphic(imageView25);
        Tooltip tooltip11 = new Tooltip();
        tooltip11.setText(bundle.getString("LibrariesSection_MarkClosure"));
        controlClosureButton.setTooltip(tooltip11);
        hBox9.getChildren().add(controlClosureButton);
        controlSimpleButton = new ToggleButton();
        controlSimpleButton.setId("controlExtern");
        controlSimpleButton.setMnemonicParsing(false);
        controlSimpleButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleMarkSimpleButtonAction(event);
            }
        });
        controlSimpleButton.setText("");
        ImageView imageView26 = new ImageView();
        imageView26.setFitHeight(8.0);
        imageView26.setFitWidth(8.0);
        imageView26.setMouseTransparent(true);
        imageView26.setPickOnBounds(true);
        imageView26.setPreserveRatio(true);
        Image image26 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/decorator-js.png").openStream());
        imageView26.setImage(image26);
        controlSimpleButton.setGraphic(imageView26);
        Tooltip tooltip12 = new Tooltip();
        tooltip12.setText(bundle.getString("LibrariesSection_MarkSimple"));
        controlSimpleButton.setTooltip(tooltip12);
        hBox9.getChildren().add(controlSimpleButton);
        vBox32.getChildren().add(hBox9);
        HBox hBox10 = new HBox();
        hBox10.setPrefHeight(100.0);
        hBox10.setPrefWidth(200.0);
        hBox10.setSpacing(5.0);
        VBox.setVgrow(hBox10, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox10.getChildren().add(controlSource);
        GridPane gridPane43 = new GridPane();
        gridPane43.setId("GridPane");
        gridPane43.setMinWidth(Control.USE_PREF_SIZE);
        gridPane43.setVgap(5.0);
        Button button41 = new Button();
        button41.setMaxWidth(1.7976931348623157E308);
        button41.setMinWidth(Control.USE_PREF_SIZE);
        button41.setMnemonicParsing(false);
        button41.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleUpButtonAction(event);
            }
        });
        button41.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button41.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button41.setText(bundle.getString("Button_Up"));
        GridPane.setColumnIndex(button41, 0);
        GridPane.setRowIndex(button41, 0);
        gridPane43.getChildren().add(button41);
        Button button42 = new Button();
        button42.setMaxWidth(1.7976931348623157E308);
        button42.setMinWidth(Control.USE_PREF_SIZE);
        button42.setMnemonicParsing(false);
        button42.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleDownButtonAction(event);
            }
        });
        button42.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button42.setText(bundle.getString("Button_Down"));
        GridPane.setColumnIndex(button42, 0);
        GridPane.setRowIndex(button42, 1);
        gridPane43.getChildren().add(button42);
        Button button43 = new Button();
        button43.setMaxWidth(1.7976931348623157E308);
        button43.setMnemonicParsing(false);
        button43.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleTopButtonAction(event);
            }
        });
        button43.setText(bundle.getString("Button_Top"));
        GridPane.setColumnIndex(button43, 0);
        GridPane.setRowIndex(button43, 2);
        Insets insets51 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button43, insets51);
        gridPane43.getChildren().add(button43);
        Button button44 = new Button();
        button44.setMaxWidth(1.7976931348623157E308);
        button44.setMnemonicParsing(false);
        button44.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBottomButtonAction(event);
            }
        });
        button44.setText(bundle.getString("Button_Bottom"));
        GridPane.setColumnIndex(button44, 0);
        GridPane.setRowIndex(button44, 3);
        gridPane43.getChildren().add(button44);
        ColumnConstraints columnConstraints87 = new ColumnConstraints();
        columnConstraints87.setHgrow(Priority.SOMETIMES);
        columnConstraints87.setMinWidth(10.0);
        gridPane43.getColumnConstraints().add(columnConstraints87);
        RowConstraints rowConstraints85 = new RowConstraints();
        rowConstraints85.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints85.setVgrow(Priority.NEVER);
        gridPane43.getRowConstraints().add(rowConstraints85);
        RowConstraints rowConstraints86 = new RowConstraints();
        rowConstraints86.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints86.setVgrow(Priority.NEVER);
        gridPane43.getRowConstraints().add(rowConstraints86);
        RowConstraints rowConstraints87 = new RowConstraints();
        rowConstraints87.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints87.setVgrow(Priority.NEVER);
        gridPane43.getRowConstraints().add(rowConstraints87);
        RowConstraints rowConstraints88 = new RowConstraints();
        rowConstraints88.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints88.setVgrow(Priority.NEVER);
        gridPane43.getRowConstraints().add(rowConstraints88);
        hBox10.getChildren().add(gridPane43);
        vBox32.getChildren().add(hBox10);
        Insets insets52 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox32.setPadding(insets52);
        titledPane19.setContent(vBox32);
        anchorPane21.getChildren().add(titledPane19);
        initialize(null, bundle);
        return anchorPane21;
    }

}
