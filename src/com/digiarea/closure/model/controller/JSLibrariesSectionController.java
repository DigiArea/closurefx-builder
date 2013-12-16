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
import com.digiarea.closure.model.controller.dialogs.SelectClosureDialogController;
import com.digiarea.closure.model.controller.dialogs.SelectVariableDialogController;
import com.digiarea.closure.model.providers.BuildpathCell;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSLibrariesSectionController extends ClosureController implements Initializable {

    public JSLibrariesSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
                return new BuildpathCell(bundle, modelFacade.getDocument().getPathResolver(), SourceEntry.CONTAINER, SourceEntry.FILE, SourceEntry.LIBRARY, SourceEntry.VARIABLE, SourceEntry.CLOSURE);
            }
        });
    }

    @FXML
    private void handleAddVariableButtonAction(ActionEvent event) {
        SelectVariableDialogController controller = DialogFactory.getSelectVariableDialog(bundle, bundle.getString(IConstants.PreferencesVariables_Select));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addSource(controller.getVariable().getPlaceholder(), SourceEntry.VARIABLE, SourceEntity.JSC, true, true, true);
        }
    }

    @FXML
    private void handleAddClosureButtonAction(ActionEvent event) {
        SelectClosureDialogController controller = DialogFactory.getSelectClosureDialog(bundle, bundle.getString(IConstants.PreferencesClosure_Select));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addJSSourceClosure(controller.getClosureLibrary().getPlaceholder(), true, true, false);
        }
    }

    @FXML
    private void handleAddLibraryButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSLibrariesSection_AddFolder_Title, IConstants.JSLibrariesSection_AddFolder_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.JSC, false, true, true);
            }
        }
    }

    @FXML
    private void handleAddExternalLibraryButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(null, IConstants.JSLibrariesSection_AddFolder_Title);
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.LIBRARY, SourceEntity.JSC, true, true, true);
        }
    }

    @FXML
    private void handleAddExternalFileButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSLibrariesSection_AddFile_Title), bundle.getString(IConstants.JSLibrariesSection_AddFile_Desc), IConstants.EXTENSION__JS);
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.FILE, SourceEntity.JSC, true, true, true);
        }
    }

    @FXML
    private void handleAddFileButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSLibrariesSection_AddFile_Title, IConstants.JSLibrariesSection_AddFile_Desc, modelFacade.getDocument().getFile().getParentFile(), false, false, IConstants.EXTENSION_JS);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.FILE, SourceEntity.JSC, false, true, true);
            }
        }
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<Source> sources = controlSource.getSelectionModel().getSelectedItems();
        if (sources != null && !sources.isEmpty()) {
            for (Source source : sources) {
                modelFacade.removeSource(source, SourceEntity.JSC);
            }
        }
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane20 = new AnchorPane();
        anchorPane20.setId("AnchorPane");
        anchorPane20.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane20.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane20.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane20.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane18 = new TitledPane();
        titledPane18.setAnimated(false);
        titledPane18.setCollapsible(false);
        titledPane18.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane18.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane18.setText(bundle.getString("LibrariesSection"));
        AnchorPane.setBottomAnchor(titledPane18, 0.0);
        AnchorPane.setLeftAnchor(titledPane18, 0.0);
        AnchorPane.setRightAnchor(titledPane18, 0.0);
        AnchorPane.setTopAnchor(titledPane18, 0.0);
        VBox vBox31 = new VBox();
        vBox31.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox31.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox31.setSpacing(5.0);
        Label label39 = new Label();
        label39.setText(bundle.getString("LibrariesSection_Desc"));
        label39.setWrapText(true);
        vBox31.getChildren().add(label39);
        HBox hBox8 = new HBox();
        hBox8.setPrefHeight(100.0);
        hBox8.setPrefWidth(200.0);
        hBox8.setSpacing(5.0);
        VBox.setVgrow(hBox8, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox8.getChildren().add(controlSource);
        GridPane gridPane42 = new GridPane();
        gridPane42.setId("GridPane");
        gridPane42.setMinWidth(Control.USE_PREF_SIZE);
        gridPane42.setVgap(5.0);
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
        GridPane.setRowIndex(btnFile, 3);
        Insets insets45 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnFile, insets45);
        gridPane42.getChildren().add(btnFile);
        btnFolder = new Button();
        btnFolder.setId("btnAdd");
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
        GridPane.setRowIndex(btnFolder, 1);
        Insets insets46 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnFolder, insets46);
        gridPane42.getChildren().add(btnFolder);
        Button button38 = new Button();
        button38.setMaxWidth(1.7976931348623157E308);
        button38.setMinWidth(Control.USE_PREF_SIZE);
        button38.setMnemonicParsing(false);
        button38.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button38.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button38.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button38, 0);
        GridPane.setRowIndex(button38, 6);
        Insets insets47 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button38, insets47);
        gridPane42.getChildren().add(button38);
        Button button39 = new Button();
        button39.setMaxWidth(1.7976931348623157E308);
        button39.setMinWidth(Control.USE_PREF_SIZE);
        button39.setMnemonicParsing(false);
        button39.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddVariableButtonAction(event);
            }
        });
        button39.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button39.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button39.setText(bundle.getString("Button_AddVariables"));
        GridPane.setColumnIndex(button39, 0);
        GridPane.setRowIndex(button39, 5);
        Insets insets48 = new Insets(10.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button39, insets48);
        gridPane42.getChildren().add(button39);
        Button btnAdd1 = new Button();
        btnAdd1.setId("btnAdd");
        btnAdd1.setMaxWidth(1.7976931348623157E308);
        btnAdd1.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd1.setMnemonicParsing(false);
        btnAdd1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalLibraryButtonAction(event);
            }
        });
        btnAdd1.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd1.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd1.setText(bundle.getString("SourceSection_Folder_External"));
        GridPane.setColumnIndex(btnAdd1, 0);
        GridPane.setRowIndex(btnAdd1, 2);
        gridPane42.getChildren().add(btnAdd1);
        Button button40 = new Button();
        button40.setMaxWidth(1.7976931348623157E308);
        button40.setMinWidth(Control.USE_PREF_SIZE);
        button40.setMnemonicParsing(false);
        button40.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalFileButtonAction(event);
            }
        });
        button40.setPrefHeight(Control.USE_COMPUTED_SIZE);
        button40.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button40.setText(bundle.getString("SourceSection_File_External"));
        GridPane.setColumnIndex(button40, 0);
        GridPane.setRowIndex(button40, 4);
        Insets insets49 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button40, insets49);
        gridPane42.getChildren().add(button40);
        Button btnAdd2 = new Button();
        btnAdd2.setId("btnAdd");
        btnAdd2.setMaxWidth(1.7976931348623157E308);
        btnAdd2.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd2.setMnemonicParsing(false);
        btnAdd2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddClosureButtonAction(event);
            }
        });
        btnAdd2.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd2.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd2.setText(bundle.getString("SourceSection_Closure"));
        GridPane.setColumnIndex(btnAdd2, 0);
        GridPane.setRowIndex(btnAdd2, 0);
        gridPane42.getChildren().add(btnAdd2);
        ColumnConstraints columnConstraints86 = new ColumnConstraints();
        columnConstraints86.setHgrow(Priority.SOMETIMES);
        columnConstraints86.setMinWidth(10.0);
        gridPane42.getColumnConstraints().add(columnConstraints86);
        RowConstraints rowConstraints78 = new RowConstraints();
        rowConstraints78.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints78.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints78);
        RowConstraints rowConstraints79 = new RowConstraints();
        rowConstraints79.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints79.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints79);
        RowConstraints rowConstraints80 = new RowConstraints();
        rowConstraints80.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints80.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints80);
        RowConstraints rowConstraints81 = new RowConstraints();
        rowConstraints81.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints81.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints81);
        RowConstraints rowConstraints82 = new RowConstraints();
        rowConstraints82.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints82.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints82);
        RowConstraints rowConstraints83 = new RowConstraints();
        rowConstraints83.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints83.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints83);
        RowConstraints rowConstraints84 = new RowConstraints();
        rowConstraints84.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints84.setVgrow(Priority.NEVER);
        gridPane42.getRowConstraints().add(rowConstraints84);
        hBox8.getChildren().add(gridPane42);
        vBox31.getChildren().add(hBox8);
        Insets insets50 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox31.setPadding(insets50);
        titledPane18.setContent(vBox31);
        anchorPane20.getChildren().add(titledPane18);
        initialize(null, bundle);
        return anchorPane20;
    }

}
