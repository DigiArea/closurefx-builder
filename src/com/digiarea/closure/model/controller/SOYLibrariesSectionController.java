package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
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
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closure.model.controller.dialogs.SelectVariableDialogController;
import com.digiarea.closure.model.providers.BuildpathCell;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYLibrariesSectionController extends ClosureController implements Initializable {

    public SOYLibrariesSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<Source> controlSource;

    @FXML
    private Button btnFile;

    @FXML
    private Button btnFolder;

    public Button getBtnFile() {
        return btnFile;
    }

    public Button getBtnFolder() {
        return btnFolder;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlSource.setCellFactory(new Callback<ListView<Source>, ListCell<Source>>() {

            @Override
            public ListCell<Source> call(ListView<Source> list) {
                return new BuildpathCell(bundle, modelFacade.getDocument().getPathResolver(), SourceEntry.CONTAINER, SourceEntry.FILE, SourceEntry.LIBRARY, SourceEntry.VARIABLE);
            }
        });
    }

    @FXML
    private void handleAddVariableButtonAction(ActionEvent event) {
        SelectVariableDialogController controller = DialogFactory.getSelectVariableDialog(bundle, bundle.getString(IConstants.PreferencesVariables_Select));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addSource(controller.getVariable().getPlaceholder(), SourceEntry.VARIABLE, SourceEntity.SOY, true);
        }
    }

    @FXML
    private void handleAddExternalLibraryButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(null, bundle.getString(IConstants.SOYLibrariesSection_AddFolder_Title));
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.SOY, true);
        }
    }

    @FXML
    private void handleAddLibraryButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SOYLibrariesSection_AddFolder_Title, IConstants.SOYLibrariesSection_AddFolder_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.SOY, false);
            }
        }
    }

    @FXML
    private void handleAddExternalFileButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.SOYLibrariesSection_AddFile_Title), bundle.getString(IConstants.SOYLibrariesSection_AddFile_Desc), IConstants.EXTENSION__SOY);
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.FILE, SourceEntity.SOY, true);
        }
    }

    @FXML
    private void handleAddFileButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SOYLibrariesSection_AddFile_Title, IConstants.SOYLibrariesSection_AddFile_Desc, modelFacade.getDocument().getFile().getParentFile(), false, false, IConstants.EXTENSION_SOY);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.FILE, SourceEntity.SOY, false);
            }
        }
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<Source> sources = controlSource.getSelectionModel().getSelectedItems();
        if (sources != null && !sources.isEmpty()) {
            for (Source source : sources) {
                modelFacade.removeSource(source, SourceEntity.SOY);
            }
        }
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane30 = new AnchorPane();
        anchorPane30.setId("AnchorPane");
        anchorPane30.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane30.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane30.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane30.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane27 = new TitledPane();
        titledPane27.setAnimated(false);
        titledPane27.setCollapsible(false);
        titledPane27.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane27.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane27.setText(bundle.getString("LibrariesSection"));
        AnchorPane.setBottomAnchor(titledPane27, 0.0);
        AnchorPane.setLeftAnchor(titledPane27, 0.0);
        AnchorPane.setRightAnchor(titledPane27, 0.0);
        AnchorPane.setTopAnchor(titledPane27, 0.0);
        VBox vBox55 = new VBox();
        vBox55.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox55.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label70 = new Label();
        label70.setText(bundle.getString("LibrariesSection_Desc"));
        label70.setWrapText(true);
        vBox55.getChildren().add(label70);
        HBox hBox13 = new HBox();
        hBox13.setPrefHeight(100.0);
        hBox13.setPrefWidth(200.0);
        hBox13.setSpacing(5.0);
        VBox.setVgrow(hBox13, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox13.getChildren().add(controlSource);
        GridPane gridPane62 = new GridPane();
        gridPane62.setId("GridPane");
        gridPane62.setMinWidth(Control.USE_PREF_SIZE);
        gridPane62.setVgap(5.0);
        btnFolder = new Button();
        btnFolder.setMaxWidth(1.7976931348623157E308);
        btnFolder.setMinWidth(Control.USE_PREF_SIZE);
        btnFolder.setMnemonicParsing(false);
        btnFolder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddLibraryButtonAction(event);
            }
        });
        btnFolder.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnFolder.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnFolder.setText(bundle.getString("SourceSection_Folder"));
        GridPane.setColumnIndex(btnFolder, 0);
        GridPane.setRowIndex(btnFolder, 0);
        gridPane62.getChildren().add(btnFolder);
        btnFile = new Button();
        btnFile.setMaxWidth(1.7976931348623157E308);
        btnFile.setMinWidth(Control.USE_PREF_SIZE);
        btnFile.setMnemonicParsing(false);
        btnFile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddFileButtonAction(event);
            }
        });
        btnFile.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnFile.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnFile.setText(bundle.getString("SourceSection_File"));
        GridPane.setColumnIndex(btnFile, 0);
        GridPane.setRowIndex(btnFile, 2);
        Insets insets79 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnFile, insets79);
        gridPane62.getChildren().add(btnFile);
        Button button57 = new Button();
        button57.setMaxWidth(1.7976931348623157E308);
        button57.setMinWidth(Control.USE_PREF_SIZE);
        button57.setMnemonicParsing(false);
        button57.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button57.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button57.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button57, 0);
        GridPane.setRowIndex(button57, 5);
        Insets insets80 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button57, insets80);
        gridPane62.getChildren().add(button57);
        Button button58 = new Button();
        button58.setMaxWidth(1.7976931348623157E308);
        button58.setMinWidth(Control.USE_PREF_SIZE);
        button58.setMnemonicParsing(false);
        button58.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddVariableButtonAction(event);
            }
        });
        button58.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button58.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button58.setText(bundle.getString("Button_AddVariables"));
        GridPane.setColumnIndex(button58, 0);
        GridPane.setRowIndex(button58, 4);
        Insets insets81 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button58, insets81);
        gridPane62.getChildren().add(button58);
        Button button59 = new Button();
        button59.setMaxWidth(1.7976931348623157E308);
        button59.setMinWidth(Control.USE_PREF_SIZE);
        button59.setMnemonicParsing(false);
        button59.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalLibraryButtonAction(event);
            }
        });
        button59.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button59.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button59.setText(bundle.getString("SourceSection_Folder_External"));
        GridPane.setColumnIndex(button59, 0);
        GridPane.setRowIndex(button59, 1);
        gridPane62.getChildren().add(button59);
        Button button60 = new Button();
        button60.setMaxWidth(1.7976931348623157E308);
        button60.setMinWidth(Control.USE_PREF_SIZE);
        button60.setMnemonicParsing(false);
        button60.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalFileButtonAction(event);
            }
        });
        button60.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button60.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button60.setText(bundle.getString("SourceSection_File_External"));
        GridPane.setColumnIndex(button60, 0);
        GridPane.setRowIndex(button60, 3);
        gridPane62.getChildren().add(button60);
        ColumnConstraints columnConstraints139 = new ColumnConstraints();
        columnConstraints139.setHgrow(Priority.SOMETIMES);
        columnConstraints139.setMinWidth(10.0);
        gridPane62.getColumnConstraints().add(columnConstraints139);
        RowConstraints rowConstraints122 = new RowConstraints();
        rowConstraints122.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints122.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints122);
        RowConstraints rowConstraints123 = new RowConstraints();
        rowConstraints123.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints123.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints123);
        RowConstraints rowConstraints124 = new RowConstraints();
        rowConstraints124.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints124.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints124);
        RowConstraints rowConstraints125 = new RowConstraints();
        rowConstraints125.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints125.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints125);
        RowConstraints rowConstraints126 = new RowConstraints();
        rowConstraints126.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints126.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints126);
        RowConstraints rowConstraints127 = new RowConstraints();
        rowConstraints127.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints127.setVgrow(Priority.NEVER);
        gridPane62.getRowConstraints().add(rowConstraints127);
        hBox13.getChildren().add(gridPane62);
        vBox55.getChildren().add(hBox13);
        Insets insets82 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox55.setPadding(insets82);
        titledPane27.setContent(vBox55);
        anchorPane30.getChildren().add(titledPane27);
        initialize(null, bundle);
        return anchorPane30;
    }

}
