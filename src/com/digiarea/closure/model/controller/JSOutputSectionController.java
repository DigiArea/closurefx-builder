package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import java.io.File;
import java.net.URL;
import javafx.event.ActionEvent;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closure.model.controller.UIUtils;
import javafx.scene.control.Control;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSOutputSectionController extends ClosureController implements Initializable {

    public JSOutputSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
                modelFacade.addJSOutput(controller.getSelectedFile().getAbsolutePath(), false);
                lastFile = controller.getSelectedFile();
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.Output_Dialog));
        if (file != null) {
            modelFacade.addJSOutput(file.getAbsolutePath(), true);
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
        AnchorPane anchorPane22 = new AnchorPane();
        anchorPane22.setId("AnchorPane");
        anchorPane22.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane22.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane22.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane22.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane20 = new TitledPane();
        titledPane20.setAnimated(false);
        titledPane20.setCollapsible(false);
        titledPane20.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane20.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane20.setText(bundle.getString("OutputSection"));
        AnchorPane.setBottomAnchor(titledPane20, 0.0);
        AnchorPane.setLeftAnchor(titledPane20, 0.0);
        AnchorPane.setRightAnchor(titledPane20, 0.0);
        AnchorPane.setTopAnchor(titledPane20, 0.0);
        VBox vBox33 = new VBox();
        vBox33.setId("VBox");
        vBox33.setAlignment(Pos.TOP_LEFT);
        vBox33.setSpacing(6.0);
        Label label41 = new Label();
        label41.setText(bundle.getString("OutputSection_Desc"));
        vBox33.getChildren().add(label41);
        GridPane gridPane44 = new GridPane();
        gridPane44.setHgap(5.0);
        gridPane44.setVgap(5.0);
        Label label42 = new Label();
        label42.setText(bundle.getString("OutputSection_File"));
        GridPane.setColumnIndex(label42, 0);
        GridPane.setHalignment(label42, HPos.RIGHT);
        GridPane.setRowIndex(label42, 0);
        gridPane44.getChildren().add(label42);
        Label label43 = new Label();
        label43.setText(bundle.getString("OutputSection_Folder"));
        GridPane.setColumnIndex(label43, 0);
        GridPane.setHalignment(label43, HPos.RIGHT);
        GridPane.setRowIndex(label43, 1);
        gridPane44.getChildren().add(label43);
        controlFile = new TextField();
        controlFile.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlFile, 1);
        GridPane.setRowIndex(controlFile, 0);
        gridPane44.getChildren().add(controlFile);
        GridPane gridPane45 = new GridPane();
        gridPane45.setHgap(5.0);
        GridPane.setColumnIndex(gridPane45, 1);
        GridPane.setRowIndex(gridPane45, 1);
        controlPath = new TextField();
        controlPath.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlPath, 0);
        GridPane.setRowIndex(controlPath, 0);
        gridPane45.getChildren().add(controlPath);
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
        gridPane45.getChildren().add(btnBrowse);
        Button button45 = new Button();
        button45.setMnemonicParsing(false);
        button45.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button45.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button45, 2);
        GridPane.setRowIndex(button45, 0);
        gridPane45.getChildren().add(button45);
        ColumnConstraints columnConstraints88 = new ColumnConstraints();
        columnConstraints88.setHgrow(Priority.ALWAYS);
        columnConstraints88.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints88.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane45.getColumnConstraints().add(columnConstraints88);
        ColumnConstraints columnConstraints89 = new ColumnConstraints();
        columnConstraints89.setHgrow(Priority.NEVER);
        columnConstraints89.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints89.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane45.getColumnConstraints().add(columnConstraints89);
        ColumnConstraints columnConstraints90 = new ColumnConstraints();
        columnConstraints90.setHgrow(Priority.NEVER);
        columnConstraints90.setMinWidth(Control.USE_PREF_SIZE);
        gridPane45.getColumnConstraints().add(columnConstraints90);
        RowConstraints rowConstraints89 = new RowConstraints();
        rowConstraints89.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints89.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints89.setVgrow(Priority.NEVER);
        gridPane45.getRowConstraints().add(rowConstraints89);
        gridPane44.getChildren().add(gridPane45);
        ColumnConstraints columnConstraints91 = new ColumnConstraints();
        columnConstraints91.setHgrow(Priority.NEVER);
        columnConstraints91.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints91.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane44.getColumnConstraints().add(columnConstraints91);
        ColumnConstraints columnConstraints92 = new ColumnConstraints();
        columnConstraints92.setHgrow(Priority.ALWAYS);
        columnConstraints92.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints92.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane44.getColumnConstraints().add(columnConstraints92);
        RowConstraints rowConstraints90 = new RowConstraints();
        rowConstraints90.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints90.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints90.setVgrow(Priority.NEVER);
        gridPane44.getRowConstraints().add(rowConstraints90);
        RowConstraints rowConstraints91 = new RowConstraints();
        rowConstraints91.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints91.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints91.setVgrow(Priority.NEVER);
        gridPane44.getRowConstraints().add(rowConstraints91);
        vBox33.getChildren().add(gridPane44);
        Insets insets53 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox33.setPadding(insets53);
        titledPane20.setContent(vBox33);
        anchorPane22.getChildren().add(titledPane20);
        initialize(null, bundle);
        return anchorPane22;
    }

}
