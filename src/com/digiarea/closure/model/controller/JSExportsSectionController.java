package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.text.TextAlignment;

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
public class JSExportsSectionController extends ClosureController implements Initializable {

    public JSExportsSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private CheckBox controlGenerateExports;

    @FXML
    private CheckBox controlExternExports;

    @FXML
    private TextField controlExternExportsPath;

    @FXML
    private Button btnBrowse;

    @FXML
    private Button btnExternalBrowse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSExportsSection_Select, IConstants.JSExportsSection_Select, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSExternExportPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(IConstants.JSExportsSection_Select, null, (String[]) null);
        if (file != null) {
            modelFacade.setJSExternExportPath(file.getAbsolutePath(), true);
        }
    }

    public CheckBox getControlGenerateExports() {
        return controlGenerateExports;
    }

    public CheckBox getControlExternExports() {
        return controlExternExports;
    }

    public TextField getControlExternExportsPath() {
        return controlExternExportsPath;
    }

    public Button getBtnBrowse() {
        return btnBrowse;
    }

    public Button getBtnExternalBrowse() {
        return btnExternalBrowse;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane15 = new AnchorPane();
        anchorPane15.setId("AnchorPane");
        anchorPane15.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane15.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane15.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane15.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane14 = new TitledPane();
        titledPane14.setAnimated(false);
        titledPane14.setCollapsible(false);
        titledPane14.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane14.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane14.setText(bundle.getString("JSExportsSection"));
        AnchorPane.setBottomAnchor(titledPane14, 0.0);
        AnchorPane.setLeftAnchor(titledPane14, 0.0);
        AnchorPane.setRightAnchor(titledPane14, 0.0);
        AnchorPane.setTopAnchor(titledPane14, 0.0);
        VBox vBox25 = new VBox();
        vBox25.setId("VBox");
        vBox25.setAlignment(Pos.CENTER_LEFT);
        vBox25.setSpacing(5.0);
        Label label25 = new Label();
        label25.setAlignment(Pos.CENTER_LEFT);
        label25.setText(bundle.getString("JSExportsSection_Desc"));
        label25.setWrapText(true);
        vBox25.getChildren().add(label25);
        GridPane gridPane36 = new GridPane();
        gridPane36.setHgap(5.0);
        gridPane36.setPrefHeight(Control.USE_COMPUTED_SIZE);
        gridPane36.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane36.setVgap(5.0);
        controlGenerateExports = new CheckBox();
        controlGenerateExports.setId("btnGenerateExport");
        controlGenerateExports.setMnemonicParsing(false);
        controlGenerateExports.setText(bundle.getString("JSExportsSection_GenerateExports"));
        GridPane.setColumnIndex(controlGenerateExports, 0);
        GridPane.setRowIndex(controlGenerateExports, 0);
        gridPane36.getChildren().add(controlGenerateExports);
        controlExternExports = new CheckBox();
        controlExternExports.setId("btnExternExport");
        controlExternExports.setMnemonicParsing(false);
        controlExternExports.setText(bundle.getString("JSExportsSection_ExternExports"));
        GridPane.setColumnIndex(controlExternExports, 0);
        GridPane.setHgrow(controlExternExports, Priority.NEVER);
        GridPane.setRowIndex(controlExternExports, 1);
        gridPane36.getChildren().add(controlExternExports);
        controlExternExportsPath = new TextField();
        controlExternExportsPath.setId("txtExternExport");
        controlExternExportsPath.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlExternExportsPath, 1);
        GridPane.setHgrow(controlExternExportsPath, Priority.ALWAYS);
        GridPane.setRowIndex(controlExternExportsPath, 1);
        GridPane.setVgrow(controlExternExportsPath, Priority.NEVER);
        gridPane36.getChildren().add(controlExternExportsPath);
        btnBrowse = new Button();
        btnBrowse.setContentDisplay(ContentDisplay.CENTER);
        btnBrowse.setMaxWidth(1.7976931348623157E308);
        btnBrowse.setMnemonicParsing(false);
        btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseButtonAction(event);
            }
        });
        btnBrowse.setText(bundle.getString("Button_Browse"));
        btnBrowse.setTextAlignment(TextAlignment.LEFT);
        GridPane.setColumnIndex(btnBrowse, 2);
        GridPane.setHalignment(btnBrowse, HPos.CENTER);
        GridPane.setHgrow(btnBrowse, Priority.NEVER);
        GridPane.setRowIndex(btnBrowse, 1);
        Insets insets38 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnBrowse, insets38);
        gridPane36.getChildren().add(btnBrowse);
        btnExternalBrowse = new Button();
        btnExternalBrowse.setId("btnBrowse");
        btnExternalBrowse.setContentDisplay(ContentDisplay.CENTER);
        btnExternalBrowse.setMaxWidth(1.7976931348623157E308);
        btnExternalBrowse.setMnemonicParsing(false);
        btnExternalBrowse.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        btnExternalBrowse.setText(bundle.getString("OutputSection_Browse_External"));
        btnExternalBrowse.setTextAlignment(TextAlignment.LEFT);
        GridPane.setColumnIndex(btnExternalBrowse, 3);
        GridPane.setHalignment(btnExternalBrowse, HPos.CENTER);
        GridPane.setHgrow(btnExternalBrowse, Priority.NEVER);
        GridPane.setRowIndex(btnExternalBrowse, 1);
        Insets insets39 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnExternalBrowse, insets39);
        gridPane36.getChildren().add(btnExternalBrowse);
        ColumnConstraints columnConstraints71 = new ColumnConstraints();
        columnConstraints71.setHgrow(Priority.NEVER);
        columnConstraints71.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints71.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane36.getColumnConstraints().add(columnConstraints71);
        ColumnConstraints columnConstraints72 = new ColumnConstraints();
        columnConstraints72.setHgrow(Priority.ALWAYS);
        columnConstraints72.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints72.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane36.getColumnConstraints().add(columnConstraints72);
        ColumnConstraints columnConstraints73 = new ColumnConstraints();
        columnConstraints73.setHalignment(HPos.LEFT);
        columnConstraints73.setHgrow(Priority.NEVER);
        columnConstraints73.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints73.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane36.getColumnConstraints().add(columnConstraints73);
        ColumnConstraints columnConstraints74 = new ColumnConstraints();
        columnConstraints74.setHalignment(HPos.LEFT);
        columnConstraints74.setHgrow(Priority.NEVER);
        columnConstraints74.setMinWidth(Control.USE_PREF_SIZE);
        gridPane36.getColumnConstraints().add(columnConstraints74);
        RowConstraints rowConstraints63 = new RowConstraints();
        rowConstraints63.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints63.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints63.setVgrow(Priority.NEVER);
        gridPane36.getRowConstraints().add(rowConstraints63);
        RowConstraints rowConstraints64 = new RowConstraints();
        rowConstraints64.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints64.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints64.setVgrow(Priority.NEVER);
        gridPane36.getRowConstraints().add(rowConstraints64);
        vBox25.getChildren().add(gridPane36);
        Insets insets40 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox25.setPadding(insets40);
        titledPane14.setContent(vBox25);
        anchorPane15.getChildren().add(titledPane14);
        initialize(null, bundle);
        return anchorPane15;
    }

}
