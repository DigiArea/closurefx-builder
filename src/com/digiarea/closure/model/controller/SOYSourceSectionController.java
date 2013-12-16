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
public class SOYSourceSectionController extends ClosureController implements Initializable {

    public SOYSourceSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<Source> controlSource;

    @FXML
    private Button btnAdd;

    private File lastFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlSource.setCellFactory(new Callback<ListView<Source>, ListCell<Source>>() {

            @Override
            public ListCell<Source> call(ListView<Source> list) {
                return new BuildpathCell(bundle, modelFacade.getDocument().getPathResolver(), SourceEntry.SOURCE);
            }
        });
    }

    public Button getBtnAdd() {
        return btnAdd;
    }

    @FXML
    private void handleAddVariableButtonAction(ActionEvent event) {
        SelectVariableDialogController controller = DialogFactory.getSelectVariableDialog(bundle, bundle.getString(IConstants.PreferencesVariables_Select));
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addSource(controller.getVariable().getPlaceholder(), SourceEntry.SOURCE, SourceEntity.SOY, true);
        }
    }

    @FXML
    private void handleAddExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.SourceSection_Add_Title));
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.SOY, true);
            lastFile = file;
        }
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SourceSection_Add_Title, IConstants.SourceSection_Add_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.SOY, false);
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
        AnchorPane anchorPane36 = new AnchorPane();
        anchorPane36.setId("AnchorPane");
        anchorPane36.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane36.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane36.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane36.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane32 = new TitledPane();
        titledPane32.setAnimated(false);
        titledPane32.setCollapsible(false);
        titledPane32.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane32.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane32.setText(bundle.getString("SourceSection"));
        AnchorPane.setBottomAnchor(titledPane32, 0.0);
        AnchorPane.setLeftAnchor(titledPane32, 0.0);
        AnchorPane.setRightAnchor(titledPane32, 0.0);
        AnchorPane.setTopAnchor(titledPane32, 0.0);
        VBox vBox64 = new VBox();
        vBox64.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox64.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label79 = new Label();
        label79.setText(bundle.getString("SourceSection_Desc"));
        label79.setWrapText(true);
        vBox64.getChildren().add(label79);
        HBox hBox15 = new HBox();
        hBox15.setPrefHeight(100.0);
        hBox15.setPrefWidth(200.0);
        hBox15.setSpacing(5.0);
        VBox.setVgrow(hBox15, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox15.getChildren().add(controlSource);
        GridPane gridPane73 = new GridPane();
        gridPane73.setId("GridPane");
        gridPane73.setMinWidth(Control.USE_PREF_SIZE);
        gridPane73.setVgap(5.0);
        Button btnAdd4 = new Button();
        btnAdd4.setId("btnAdd");
        btnAdd4.setMaxWidth(1.7976931348623157E308);
        btnAdd4.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd4.setMnemonicParsing(false);
        btnAdd4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalButtonAction(event);
            }
        });
        btnAdd4.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd4.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd4.setText(bundle.getString("SourceSection_Add_External"));
        GridPane.setColumnIndex(btnAdd4, 0);
        GridPane.setRowIndex(btnAdd4, 1);
        gridPane73.getChildren().add(btnAdd4);
        Button button72 = new Button();
        button72.setMaxWidth(1.7976931348623157E308);
        button72.setMinWidth(Control.USE_PREF_SIZE);
        button72.setMnemonicParsing(false);
        button72.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button72.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button72.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button72, 0);
        GridPane.setRowIndex(button72, 2);
        gridPane73.getChildren().add(button72);
        btnAdd = new Button();
        btnAdd.setMaxWidth(1.7976931348623157E308);
        btnAdd.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd.setMnemonicParsing(false);
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        btnAdd.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd.setText(bundle.getString("Button_AddDotted"));
        GridPane.setColumnIndex(btnAdd, 0);
        GridPane.setRowIndex(btnAdd, 0);
        gridPane73.getChildren().add(btnAdd);
        ColumnConstraints columnConstraints167 = new ColumnConstraints();
        columnConstraints167.setHgrow(Priority.SOMETIMES);
        columnConstraints167.setMinWidth(10.0);
        gridPane73.getColumnConstraints().add(columnConstraints167);
        RowConstraints rowConstraints144 = new RowConstraints();
        rowConstraints144.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints144.setVgrow(Priority.NEVER);
        gridPane73.getRowConstraints().add(rowConstraints144);
        RowConstraints rowConstraints145 = new RowConstraints();
        rowConstraints145.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints145.setVgrow(Priority.NEVER);
        gridPane73.getRowConstraints().add(rowConstraints145);
        RowConstraints rowConstraints146 = new RowConstraints();
        rowConstraints146.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints146.setVgrow(Priority.NEVER);
        gridPane73.getRowConstraints().add(rowConstraints146);
        hBox15.getChildren().add(gridPane73);
        vBox64.getChildren().add(hBox15);
        Insets insets99 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox64.setPadding(insets99);
        titledPane32.setContent(vBox64);
        anchorPane36.getChildren().add(titledPane32);
        initialize(null, bundle);
        return anchorPane36;
    }

}
