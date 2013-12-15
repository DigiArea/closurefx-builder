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
import com.digiarea.closure.model.Source;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import java.net.URL;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import com.digiarea.closure.model.providers.BuildpathCell;
import com.digiarea.closure.model.SourceEntry;
import javafx.event.ActionEvent;
import com.digiarea.closure.model.controller.dialogs.SelectVariableDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import java.io.File;
import com.digiarea.closure.model.controller.UIUtils;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.event.EventHandler;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class GSSLibrariesSectionController extends ClosureController implements Initializable {

    public GSSLibrariesSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
            modelFacade.addSource(controller.getVariable().getPlaceholder(), SourceEntry.VARIABLE, SourceEntity.GSS, true);
        }
    }

    @FXML
    private void handleAddLibraryButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.GSSLibrariesSection_AddFolder_Title, IConstants.GSSLibrariesSection_AddFolder_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.GSS, false);
            }
        }
    }

    @FXML
    private void handleAddExternalLibraryButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(null, bundle.getString(IConstants.GSSLibrariesSection_AddFolder_Title));
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.GSS, true);
        }
    }

    @FXML
    private void handleAddFileButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.GSSLibrariesSection_AddFile_Title, IConstants.GSSLibrariesSection_AddFile_Desc, modelFacade.getDocument().getFile().getParentFile(), false, false, IConstants.EXTENSION_CSS, IConstants.EXTENSION_GSS);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.FILE, SourceEntity.GSS, false);
            }
        }
    }

    @FXML
    private void handleAddExternalFileButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.GSSLibrariesSection_AddFile_Title), bundle.getString(IConstants.GSSLibrariesSection_AddFile_Desc), IConstants.EXTENSION__CSS, IConstants.EXTENSION__GSS);
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.FILE, SourceEntity.GSS, false);
        }
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<Source> sources = controlSource.getSelectionModel().getSelectedItems();
        if (sources != null && !sources.isEmpty()) {
            for (Source source : sources) {
                modelFacade.removeSource(source, SourceEntity.GSS);
            }
        }
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane4 = new AnchorPane();
        anchorPane4.setId("AnchorPane");
        anchorPane4.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane4.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane4.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane4 = new TitledPane();
        titledPane4.setAnimated(false);
        titledPane4.setCollapsible(false);
        titledPane4.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane4.setText(bundle.getString("LibrariesSection"));
        AnchorPane.setBottomAnchor(titledPane4, 0.0);
        AnchorPane.setLeftAnchor(titledPane4, 0.0);
        AnchorPane.setRightAnchor(titledPane4, 0.0);
        AnchorPane.setTopAnchor(titledPane4, 0.0);
        VBox vBox6 = new VBox();
        vBox6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label9 = new Label();
        label9.setText(bundle.getString("LibrariesSection_Desc"));
        label9.setWrapText(true);
        vBox6.getChildren().add(label9);
        HBox hBox2 = new HBox();
        hBox2.setPrefHeight(100.0);
        hBox2.setPrefWidth(200.0);
        hBox2.setSpacing(5.0);
        VBox.setVgrow(hBox2, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox2.getChildren().add(controlSource);
        GridPane gridPane10 = new GridPane();
        gridPane10.setId("GridPane");
        gridPane10.setMinWidth(Control.USE_PREF_SIZE);
        gridPane10.setVgap(5.0);
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
        gridPane10.getChildren().add(btnFolder);
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
        Insets insets9 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnFile, insets9);
        gridPane10.getChildren().add(btnFile);
        Button button4 = new Button();
        button4.setMaxWidth(1.7976931348623157E308);
        button4.setMinWidth(Control.USE_PREF_SIZE);
        button4.setMnemonicParsing(false);
        button4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button4.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button4, 0);
        GridPane.setRowIndex(button4, 5);
        Insets insets10 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button4, insets10);
        gridPane10.getChildren().add(button4);
        Button button5 = new Button();
        button5.setMaxWidth(1.7976931348623157E308);
        button5.setMinWidth(Control.USE_PREF_SIZE);
        button5.setMnemonicParsing(false);
        button5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddVariableButtonAction(event);
            }
        });
        button5.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button5.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button5.setText(bundle.getString("Button_AddVariables"));
        GridPane.setColumnIndex(button5, 0);
        GridPane.setRowIndex(button5, 4);
        Insets insets11 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button5, insets11);
        gridPane10.getChildren().add(button5);
        Button button6 = new Button();
        button6.setMaxWidth(1.7976931348623157E308);
        button6.setMinWidth(Control.USE_PREF_SIZE);
        button6.setMnemonicParsing(false);
        button6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalLibraryButtonAction(event);
            }
        });
        button6.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button6.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button6.setText(bundle.getString("SourceSection_Folder_External"));
        GridPane.setColumnIndex(button6, 0);
        GridPane.setRowIndex(button6, 1);
        gridPane10.getChildren().add(button6);
        Button button7 = new Button();
        button7.setMaxWidth(1.7976931348623157E308);
        button7.setMinWidth(Control.USE_PREF_SIZE);
        button7.setMnemonicParsing(false);
        button7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalFileButtonAction(event);
            }
        });
        button7.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button7.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button7.setText(bundle.getString("SourceSection_File_External"));
        GridPane.setColumnIndex(button7, 0);
        GridPane.setRowIndex(button7, 3);
        Insets insets12 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button7, insets12);
        gridPane10.getChildren().add(button7);
        ColumnConstraints columnConstraints17 = new ColumnConstraints();
        columnConstraints17.setHgrow(Priority.SOMETIMES);
        columnConstraints17.setMinWidth(10.0);
        gridPane10.getColumnConstraints().add(columnConstraints17);
        RowConstraints rowConstraints16 = new RowConstraints();
        rowConstraints16.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints16.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints16);
        RowConstraints rowConstraints17 = new RowConstraints();
        rowConstraints17.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints17.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints17);
        RowConstraints rowConstraints18 = new RowConstraints();
        rowConstraints18.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints18.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints18);
        RowConstraints rowConstraints19 = new RowConstraints();
        rowConstraints19.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints19.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints19);
        RowConstraints rowConstraints20 = new RowConstraints();
        rowConstraints20.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints20.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints20);
        RowConstraints rowConstraints21 = new RowConstraints();
        rowConstraints21.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints21.setVgrow(Priority.NEVER);
        gridPane10.getRowConstraints().add(rowConstraints21);
        hBox2.getChildren().add(gridPane10);
        vBox6.getChildren().add(hBox2);
        Insets insets13 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox6.setPadding(insets13);
        titledPane4.setContent(vBox6);
        anchorPane4.getChildren().add(titledPane4);
        initialize(null, bundle);
        return anchorPane4;
    }

}
