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
public class GSSOutputSectionController extends ClosureController implements Initializable {

    public GSSOutputSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextField controlPath;

    @FXML
    private TextField controlFile;

    @FXML
    private Button btnBrowse;

    private File lastFile;

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
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.Output_Dialog, IConstants.Output_Dialog, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addGSSOutput(controller.getSelectedFile().getAbsolutePath(), false);
                lastFile = controller.getSelectedFile();
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.Output_Dialog));
        if (file != null) {
            modelFacade.addGSSOutput(file.getAbsolutePath(), true);
            lastFile = file;
        }
    }

    public TextField getControlPath() {
        return controlPath;
    }

    public TextField getControlFile() {
        return controlFile;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane7 = new AnchorPane();
        anchorPane7.setId("AnchorPane");
        anchorPane7.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane7.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane7.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane7 = new TitledPane();
        titledPane7.setAnimated(false);
        titledPane7.setCollapsible(false);
        titledPane7.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane7.setText(bundle.getString("OutputSection"));
        AnchorPane.setBottomAnchor(titledPane7, 0.0);
        AnchorPane.setLeftAnchor(titledPane7, 0.0);
        AnchorPane.setRightAnchor(titledPane7, 0.0);
        AnchorPane.setTopAnchor(titledPane7, 0.0);
        VBox vBox9 = new VBox();
        vBox9.setId("VBox");
        vBox9.setAlignment(Pos.TOP_LEFT);
        vBox9.setSpacing(6.0);
        Label label12 = new Label();
        label12.setText(bundle.getString("OutputSection_Desc"));
        vBox9.getChildren().add(label12);
        GridPane gridPane16 = new GridPane();
        gridPane16.setHgap(5.0);
        gridPane16.setVgap(5.0);
        Label label13 = new Label();
        label13.setText(bundle.getString("OutputSection_File"));
        GridPane.setColumnIndex(label13, 0);
        GridPane.setHalignment(label13, HPos.RIGHT);
        GridPane.setRowIndex(label13, 0);
        gridPane16.getChildren().add(label13);
        Label label14 = new Label();
        label14.setText(bundle.getString("OutputSection_Folder"));
        GridPane.setColumnIndex(label14, 0);
        GridPane.setHalignment(label14, HPos.RIGHT);
        GridPane.setRowIndex(label14, 1);
        gridPane16.getChildren().add(label14);
        controlFile = new TextField();
        controlFile.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlFile, 1);
        GridPane.setRowIndex(controlFile, 0);
        gridPane16.getChildren().add(controlFile);
        GridPane gridPane17 = new GridPane();
        gridPane17.setHgap(5.0);
        GridPane.setColumnIndex(gridPane17, 1);
        GridPane.setRowIndex(gridPane17, 1);
        controlPath = new TextField();
        controlPath.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlPath, 0);
        GridPane.setRowIndex(controlPath, 0);
        gridPane17.getChildren().add(controlPath);
        btnBrowse = new Button();
        btnBrowse.setMnemonicParsing(false);
        btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseButtonAction(event);
            }
        });
        btnBrowse.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnBrowse, 1);
        GridPane.setRowIndex(btnBrowse, 0);
        gridPane17.getChildren().add(btnBrowse);
        Button button16 = new Button();
        button16.setMnemonicParsing(false);
        button16.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button16.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button16, 2);
        GridPane.setRowIndex(button16, 0);
        gridPane17.getChildren().add(button16);
        ColumnConstraints columnConstraints25 = new ColumnConstraints();
        columnConstraints25.setHgrow(Priority.ALWAYS);
        columnConstraints25.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints25.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane17.getColumnConstraints().add(columnConstraints25);
        ColumnConstraints columnConstraints26 = new ColumnConstraints();
        columnConstraints26.setHgrow(Priority.NEVER);
        columnConstraints26.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints26.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane17.getColumnConstraints().add(columnConstraints26);
        ColumnConstraints columnConstraints27 = new ColumnConstraints();
        columnConstraints27.setHgrow(Priority.NEVER);
        columnConstraints27.setMinWidth(Control.USE_PREF_SIZE);
        gridPane17.getColumnConstraints().add(columnConstraints27);
        RowConstraints rowConstraints32 = new RowConstraints();
        rowConstraints32.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints32.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints32.setVgrow(Priority.NEVER);
        gridPane17.getRowConstraints().add(rowConstraints32);
        gridPane16.getChildren().add(gridPane17);
        ColumnConstraints columnConstraints28 = new ColumnConstraints();
        columnConstraints28.setHgrow(Priority.NEVER);
        columnConstraints28.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints28.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane16.getColumnConstraints().add(columnConstraints28);
        ColumnConstraints columnConstraints29 = new ColumnConstraints();
        columnConstraints29.setHgrow(Priority.ALWAYS);
        columnConstraints29.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints29.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane16.getColumnConstraints().add(columnConstraints29);
        RowConstraints rowConstraints33 = new RowConstraints();
        rowConstraints33.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints33.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints33.setVgrow(Priority.NEVER);
        gridPane16.getRowConstraints().add(rowConstraints33);
        RowConstraints rowConstraints34 = new RowConstraints();
        rowConstraints34.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints34.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints34.setVgrow(Priority.NEVER);
        gridPane16.getRowConstraints().add(rowConstraints34);
        vBox9.getChildren().add(gridPane16);
        Insets insets17 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox9.setPadding(insets17);
        titledPane7.setContent(vBox9);
        anchorPane7.getChildren().add(titledPane7);
        initialize(null, bundle);
        return anchorPane7;
    }

}
