package com.digiarea.closure.model.controller;

import java.util.*;
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
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import com.digiarea.closure.model.controller.dialogs.PlaceholderDialogController;
import com.digiarea.closure.model.entity.OutputPlaceholder;
import java.util.Arrays;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closure.model.controller.UIUtils;
import javafx.scene.control.Control;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYOutputSectionController extends ClosureController implements Initializable {

    public SOYOutputSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TextField controlOutputPath;

    @FXML
    private Button btnBrowse;

    private File lastFile;

    private int lastCuret;

    public Button getBtnBrowse() {
        return btnBrowse;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlOutputPath.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                lastCuret = controlOutputPath.getCaretPosition();
            }
        });
        controlOutputPath.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                lastCuret = controlOutputPath.getCaretPosition();
            }
        });
    }

    @FXML
    private void handlePlaceholderButtonAction(ActionEvent event) {
        PlaceholderDialogController controller = (PlaceholderDialogController) DialogFactory.getPlaceholderDialog(Arrays.asList(OutputPlaceholder.values()), "Select Placeholder", ResourceUtils.CLOSURE_ICON, "Select placeholder:", "Placeholder description:");
        if (controller.getPlaceholder() != null) {
            modelFacade.addSOYOutputPlaceholder(controller.getPlaceholder().getValue(), lastCuret);
        }
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.Output_Dialog, IConstants.Output_Dialog, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setSOYOutput(controller.getSelectedFile().getAbsolutePath(), false);
                lastFile = controller.getSelectedFile();
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.Output_Dialog));
        if (file != null) {
            modelFacade.setSOYOutput(file.getAbsolutePath(), true);
            lastFile = file;
        }
    }

    public TextField getControlOutputPath() {
        return controlOutputPath;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane34 = new AnchorPane();
        anchorPane34.setId("AnchorPane");
        anchorPane34.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane34.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane34.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane34.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane31 = new TitledPane();
        titledPane31.setAnimated(false);
        titledPane31.setCollapsible(false);
        titledPane31.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane31.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane31.setText(bundle.getString("SOYOutputSection"));
        AnchorPane.setBottomAnchor(titledPane31, 0.0);
        AnchorPane.setLeftAnchor(titledPane31, 0.0);
        AnchorPane.setRightAnchor(titledPane31, 0.0);
        AnchorPane.setTopAnchor(titledPane31, 0.0);
        VBox vBox59 = new VBox();
        vBox59.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox59.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox59.setSpacing(5.0);
        Label label78 = new Label();
        label78.setText(bundle.getString("SOYOutputSection_Desc"));
        label78.setWrapText(true);
        vBox59.getChildren().add(label78);
        controlOutputPath = new TextField();
        controlOutputPath.setPrefWidth(200.0);
        vBox59.getChildren().add(controlOutputPath);
        GridPane gridPane70 = new GridPane();
        gridPane70.setAlignment(Pos.CENTER_RIGHT);
        gridPane70.setHgap(5.0);
        Button button70 = new Button();
        button70.setMinWidth(Control.USE_PREF_SIZE);
        button70.setMnemonicParsing(false);
        button70.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePlaceholderButtonAction(event);
            }
        });
        button70.setText(bundle.getString("Button_Placeholders"));
        GridPane.setColumnIndex(button70, 0);
        GridPane.setRowIndex(button70, 0);
        gridPane70.getChildren().add(button70);
        btnBrowse = new Button();
        btnBrowse.setMinWidth(Control.USE_PREF_SIZE);
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
        gridPane70.getChildren().add(btnBrowse);
        Button button71 = new Button();
        button71.setMinWidth(Control.USE_PREF_SIZE);
        button71.setMnemonicParsing(false);
        button71.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button71.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button71, 2);
        GridPane.setRowIndex(button71, 0);
        gridPane70.getChildren().add(button71);
        ColumnConstraints columnConstraints155 = new ColumnConstraints();
        columnConstraints155.setHgrow(Priority.NEVER);
        columnConstraints155.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints155.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane70.getColumnConstraints().add(columnConstraints155);
        ColumnConstraints columnConstraints156 = new ColumnConstraints();
        columnConstraints156.setHgrow(Priority.NEVER);
        columnConstraints156.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints156.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane70.getColumnConstraints().add(columnConstraints156);
        ColumnConstraints columnConstraints157 = new ColumnConstraints();
        columnConstraints157.setHgrow(Priority.NEVER);
        gridPane70.getColumnConstraints().add(columnConstraints157);
        RowConstraints rowConstraints140 = new RowConstraints();
        rowConstraints140.setMinHeight(10.0);
        rowConstraints140.setPrefHeight(30.0);
        rowConstraints140.setVgrow(Priority.SOMETIMES);
        gridPane70.getRowConstraints().add(rowConstraints140);
        vBox59.getChildren().add(gridPane70);
        Insets insets92 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox59.setPadding(insets92);
        titledPane31.setContent(vBox59);
        anchorPane34.getChildren().add(titledPane31);
        initialize(null, bundle);
        return anchorPane34;
    }

}
