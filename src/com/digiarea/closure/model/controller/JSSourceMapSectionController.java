package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import com.digiarea.closure.model.JsSourceMapFormat;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSSourceMapSectionController extends ClosureController implements Initializable {

    public JSSourceMapSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextField controlSourceMapFile;

    @FXML
    private ComboBox<JsSourceMapFormat> controlSourceMapFormat;

    @FXML
    private Button btnBrowse;

    public Button getBtnBrowse() {
        return btnBrowse;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlSourceMapFormat.setItems(FXCollections.observableArrayList(JsSourceMapFormat.values()));
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSSourceMapSection_Browse_Title, IConstants.JSSourceMapSection_Browse_Desc, modelFacade.getDocument().getFile().getParentFile(), false, false, IConstants.EXTENSION_JS);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSSourceMapPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSSourceMapSection_Browse_Title), bundle.getString(IConstants.JSSourceMapSection_Browse_Desc), IConstants.EXTENSION__JS);
        if (file != null) {
            modelFacade.setJSSourceMapPath(file.getAbsolutePath(), true);
        }
    }

    public TextField getControlSourceMapFile() {
        return controlSourceMapFile;
    }

    public ComboBox<JsSourceMapFormat> getControlSourceMapFormat() {
        return controlSourceMapFormat;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane25 = new AnchorPane();
        anchorPane25.setId("AnchorPane");
        anchorPane25.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane25.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane25.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane25.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane22 = new TitledPane();
        titledPane22.setAnimated(false);
        titledPane22.setCollapsible(false);
        titledPane22.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane22.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane22.setText(bundle.getString("JSSourceMapSection"));
        AnchorPane.setBottomAnchor(titledPane22, 0.0);
        AnchorPane.setLeftAnchor(titledPane22, 0.0);
        AnchorPane.setRightAnchor(titledPane22, 0.0);
        AnchorPane.setTopAnchor(titledPane22, 0.0);
        VBox vBox47 = new VBox();
        vBox47.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox47.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox47.setSpacing(5.0);
        Label label57 = new Label();
        label57.setText(bundle.getString("JSSourceMapSection_Desc"));
        label57.setWrapText(true);
        vBox47.getChildren().add(label57);
        GridPane gridPane53 = new GridPane();
        gridPane53.setHgap(5.0);
        gridPane53.setVgap(5.0);
        Label label58 = new Label();
        label58.setText(bundle.getString("JSSourceMapSection_Format"));
        GridPane.setColumnIndex(label58, 0);
        GridPane.setHalignment(label58, HPos.RIGHT);
        GridPane.setRowIndex(label58, 0);
        gridPane53.getChildren().add(label58);
        Label label59 = new Label();
        label59.setText(bundle.getString("JSSourceMapSection_File"));
        GridPane.setColumnIndex(label59, 0);
        GridPane.setHalignment(label59, HPos.RIGHT);
        GridPane.setRowIndex(label59, 1);
        gridPane53.getChildren().add(label59);
        GridPane gridPane54 = new GridPane();
        gridPane54.setHgap(5.0);
        GridPane.setColumnIndex(gridPane54, 1);
        GridPane.setRowIndex(gridPane54, 1);
        controlSourceMapFile = new TextField();
        controlSourceMapFile.setMaxWidth(1.7976931348623157E308);
        controlSourceMapFile.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlSourceMapFile, 0);
        GridPane.setHgrow(controlSourceMapFile, Priority.ALWAYS);
        GridPane.setRowIndex(controlSourceMapFile, 0);
        gridPane54.getChildren().add(controlSourceMapFile);
        btnBrowse = new Button();
        btnBrowse.setMaxWidth(1.7976931348623157E308);
        btnBrowse.setMinWidth(Control.USE_COMPUTED_SIZE);
        btnBrowse.setMnemonicParsing(false);
        btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseButtonAction(event);
            }
        });
        btnBrowse.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnBrowse.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnBrowse, 1);
        GridPane.setHgrow(btnBrowse, Priority.SOMETIMES);
        GridPane.setRowIndex(btnBrowse, 0);
        gridPane54.getChildren().add(btnBrowse);
        Button button52 = new Button();
        button52.setMaxWidth(1.7976931348623157E308);
        button52.setMinWidth(Control.USE_COMPUTED_SIZE);
        button52.setMnemonicParsing(false);
        button52.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button52.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button52.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button52, 2);
        GridPane.setHgrow(button52, Priority.SOMETIMES);
        GridPane.setRowIndex(button52, 0);
        gridPane54.getChildren().add(button52);
        ColumnConstraints columnConstraints118 = new ColumnConstraints();
        columnConstraints118.setHgrow(Priority.ALWAYS);
        columnConstraints118.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints118.setPercentWidth(-1.0);
        columnConstraints118.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane54.getColumnConstraints().add(columnConstraints118);
        ColumnConstraints columnConstraints119 = new ColumnConstraints();
        columnConstraints119.setHgrow(Priority.NEVER);
        columnConstraints119.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints119.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints119.setPercentWidth(-1.0);
        columnConstraints119.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane54.getColumnConstraints().add(columnConstraints119);
        ColumnConstraints columnConstraints120 = new ColumnConstraints();
        columnConstraints120.setHgrow(Priority.NEVER);
        columnConstraints120.setMinWidth(Control.USE_PREF_SIZE);
        gridPane54.getColumnConstraints().add(columnConstraints120);
        RowConstraints rowConstraints106 = new RowConstraints();
        rowConstraints106.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints106.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints106.setVgrow(Priority.NEVER);
        gridPane54.getRowConstraints().add(rowConstraints106);
        gridPane53.getChildren().add(gridPane54);
        controlSourceMapFormat = new ComboBox();
        controlSourceMapFormat.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlSourceMapFormat, 1);
        GridPane.setRowIndex(controlSourceMapFormat, 0);
        gridPane53.getChildren().add(controlSourceMapFormat);
        ColumnConstraints columnConstraints121 = new ColumnConstraints();
        columnConstraints121.setHgrow(Priority.NEVER);
        columnConstraints121.setMaxWidth(Control.USE_PREF_SIZE);
        columnConstraints121.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints121.setPercentWidth(-1.0);
        columnConstraints121.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane53.getColumnConstraints().add(columnConstraints121);
        ColumnConstraints columnConstraints122 = new ColumnConstraints();
        columnConstraints122.setHgrow(Priority.ALWAYS);
        columnConstraints122.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints122.setPercentWidth(-1.0);
        columnConstraints122.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane53.getColumnConstraints().add(columnConstraints122);
        RowConstraints rowConstraints107 = new RowConstraints();
        rowConstraints107.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints107.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints107.setVgrow(Priority.NEVER);
        gridPane53.getRowConstraints().add(rowConstraints107);
        RowConstraints rowConstraints108 = new RowConstraints();
        rowConstraints108.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints108.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints108.setVgrow(Priority.NEVER);
        gridPane53.getRowConstraints().add(rowConstraints108);
        vBox47.getChildren().add(gridPane53);
        Insets insets69 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox47.setPadding(insets69);
        titledPane22.setContent(vBox47);
        anchorPane25.getChildren().add(titledPane22);
        initialize(null, bundle);
        return anchorPane25;
    }

}
