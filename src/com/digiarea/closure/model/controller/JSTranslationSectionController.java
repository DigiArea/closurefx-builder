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
import javafx.scene.control.Button;
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
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSTranslationSectionController extends ClosureController implements Initializable {

    public JSTranslationSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextField controlTranslationsFile;

    @FXML
    private TextField controlTranslationsProject;

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
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSTranslationSection_Browse_Title, IConstants.JSTranslationSection_Browse_Desc, modelFacade.getDocument().getFile().getParentFile(), false, false, IConstants.EXTENSION_XLF);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSTranslationPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSTranslationSection_Browse_Title), bundle.getString(IConstants.JSTranslationSection_Browse_Desc), IConstants.EXTENSION__XLF);
        if (file != null) {
            modelFacade.setJSTranslationPath(file.getAbsolutePath(), true);
        }
    }

    public TextField getControlTranslationsFile() {
        return controlTranslationsFile;
    }

    public TextField getControlTranslationsProject() {
        return controlTranslationsProject;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane27 = new AnchorPane();
        anchorPane27.setId("AnchorPane");
        anchorPane27.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane27.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane27.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane27.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane24 = new TitledPane();
        titledPane24.setAnimated(false);
        titledPane24.setCollapsible(false);
        titledPane24.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane24.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane24.setText(bundle.getString("JSTranslationSection"));
        AnchorPane.setBottomAnchor(titledPane24, 0.0);
        AnchorPane.setLeftAnchor(titledPane24, 0.0);
        AnchorPane.setRightAnchor(titledPane24, 0.0);
        AnchorPane.setTopAnchor(titledPane24, 0.0);
        VBox vBox49 = new VBox();
        vBox49.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox49.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox49.setSpacing(5.0);
        Label label61 = new Label();
        label61.setText(bundle.getString("JSTranslationSection_Desc"));
        label61.setWrapText(true);
        vBox49.getChildren().add(label61);
        GridPane gridPane56 = new GridPane();
        gridPane56.setHgap(5.0);
        gridPane56.setMinHeight(Control.USE_PREF_SIZE);
        gridPane56.setMinWidth(Control.USE_PREF_SIZE);
        gridPane56.setVgap(5.0);
        VBox.setVgrow(gridPane56, Priority.NEVER);
        Label label62 = new Label();
        label62.setText(bundle.getString("JSTranslationSection_Project"));
        GridPane.setColumnIndex(label62, 0);
        GridPane.setHalignment(label62, HPos.RIGHT);
        GridPane.setRowIndex(label62, 0);
        gridPane56.getChildren().add(label62);
        Label label63 = new Label();
        label63.setText(bundle.getString("JSTranslationSection_File"));
        GridPane.setColumnIndex(label63, 0);
        GridPane.setHalignment(label63, HPos.RIGHT);
        GridPane.setRowIndex(label63, 1);
        gridPane56.getChildren().add(label63);
        controlTranslationsProject = new TextField();
        controlTranslationsProject.setMinWidth(Control.USE_COMPUTED_SIZE);
        controlTranslationsProject.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlTranslationsProject, 1);
        GridPane.setRowIndex(controlTranslationsProject, 0);
        gridPane56.getChildren().add(controlTranslationsProject);
        GridPane gridPane57 = new GridPane();
        gridPane57.setHgap(5.0);
        GridPane.setColumnIndex(gridPane57, 1);
        GridPane.setRowIndex(gridPane57, 1);
        controlTranslationsFile = new TextField();
        controlTranslationsFile.setMaxWidth(1.7976931348623157E308);
        controlTranslationsFile.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlTranslationsFile, 0);
        GridPane.setHgrow(controlTranslationsFile, Priority.ALWAYS);
        GridPane.setRowIndex(controlTranslationsFile, 0);
        gridPane57.getChildren().add(controlTranslationsFile);
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
        GridPane.setHgrow(btnBrowse, Priority.ALWAYS);
        GridPane.setRowIndex(btnBrowse, 0);
        gridPane57.getChildren().add(btnBrowse);
        Button button54 = new Button();
        button54.setMaxWidth(1.7976931348623157E308);
        button54.setMinWidth(Control.USE_COMPUTED_SIZE);
        button54.setMnemonicParsing(false);
        button54.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button54.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button54.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button54, 2);
        GridPane.setHgrow(button54, Priority.ALWAYS);
        GridPane.setRowIndex(button54, 0);
        gridPane57.getChildren().add(button54);
        ColumnConstraints columnConstraints124 = new ColumnConstraints();
        columnConstraints124.setHgrow(Priority.ALWAYS);
        columnConstraints124.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints124.setPercentWidth(-1.0);
        columnConstraints124.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane57.getColumnConstraints().add(columnConstraints124);
        ColumnConstraints columnConstraints125 = new ColumnConstraints();
        columnConstraints125.setHgrow(Priority.NEVER);
        columnConstraints125.setMaxWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints125.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints125.setPercentWidth(-1.0);
        columnConstraints125.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane57.getColumnConstraints().add(columnConstraints125);
        ColumnConstraints columnConstraints126 = new ColumnConstraints();
        columnConstraints126.setHgrow(Priority.NEVER);
        columnConstraints126.setMinWidth(Control.USE_PREF_SIZE);
        gridPane57.getColumnConstraints().add(columnConstraints126);
        RowConstraints rowConstraints112 = new RowConstraints();
        rowConstraints112.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints112.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints112.setVgrow(Priority.NEVER);
        gridPane57.getRowConstraints().add(rowConstraints112);
        gridPane56.getChildren().add(gridPane57);
        ColumnConstraints columnConstraints127 = new ColumnConstraints();
        columnConstraints127.setHgrow(Priority.NEVER);
        columnConstraints127.setMaxWidth(Control.USE_PREF_SIZE);
        columnConstraints127.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints127.setPercentWidth(-1.0);
        columnConstraints127.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane56.getColumnConstraints().add(columnConstraints127);
        ColumnConstraints columnConstraints128 = new ColumnConstraints();
        columnConstraints128.setHgrow(Priority.ALWAYS);
        columnConstraints128.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints128.setPercentWidth(-1.0);
        columnConstraints128.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane56.getColumnConstraints().add(columnConstraints128);
        RowConstraints rowConstraints113 = new RowConstraints();
        rowConstraints113.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints113.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints113.setVgrow(Priority.NEVER);
        gridPane56.getRowConstraints().add(rowConstraints113);
        RowConstraints rowConstraints114 = new RowConstraints();
        rowConstraints114.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints114.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints114.setVgrow(Priority.NEVER);
        gridPane56.getRowConstraints().add(rowConstraints114);
        vBox49.getChildren().add(gridPane56);
        Insets insets71 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox49.setPadding(insets71);
        titledPane24.setContent(vBox49);
        anchorPane27.getChildren().add(titledPane24);
        initialize(null, bundle);
        return anchorPane27;
    }

}
