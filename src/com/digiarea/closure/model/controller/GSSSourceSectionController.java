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
import java.io.File;
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
public class GSSSourceSectionController extends ClosureController implements Initializable {

    public GSSSourceSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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
            modelFacade.addSource(controller.getVariable().getPlaceholder(), SourceEntry.SOURCE, SourceEntity.GSS, true);
        }
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SourceSection_Add_Title, IConstants.SourceSection_Add_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.GSS, false);
            }
        }
    }

    @FXML
    private void handleAddExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.SourceSection_Add_Title));
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.GSS, true);
            lastFile = file;
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
        AnchorPane anchorPane10 = new AnchorPane();
        anchorPane10.setId("AnchorPane");
        anchorPane10.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane10.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane10.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane10.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane9 = new TitledPane();
        titledPane9.setAnimated(false);
        titledPane9.setCollapsible(false);
        titledPane9.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane9.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane9.setText(bundle.getString("SourceSection"));
        AnchorPane.setBottomAnchor(titledPane9, 0.0);
        AnchorPane.setLeftAnchor(titledPane9, 0.0);
        AnchorPane.setRightAnchor(titledPane9, 0.0);
        AnchorPane.setTopAnchor(titledPane9, 0.0);
        VBox vBox19 = new VBox();
        vBox19.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox19.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label20 = new Label();
        label20.setText(bundle.getString("SourceSection_Desc"));
        label20.setWrapText(true);
        vBox19.getChildren().add(label20);
        HBox hBox4 = new HBox();
        hBox4.setPrefHeight(100.0);
        hBox4.setPrefWidth(200.0);
        hBox4.setSpacing(5.0);
        VBox.setVgrow(hBox4, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(200.0);
        controlSource.setPrefWidth(200.0);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox4.getChildren().add(controlSource);
        GridPane gridPane26 = new GridPane();
        gridPane26.setId("GridPane");
        gridPane26.setMinWidth(Control.USE_PREF_SIZE);
        gridPane26.setVgap(5.0);
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
        gridPane26.getChildren().add(btnAdd);
        Button button20 = new Button();
        button20.setMaxWidth(1.7976931348623157E308);
        button20.setMinWidth(Control.USE_PREF_SIZE);
        button20.setMnemonicParsing(false);
        button20.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button20.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button20.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button20, 0);
        GridPane.setRowIndex(button20, 2);
        gridPane26.getChildren().add(button20);
        Button btnAdd = new Button();
        btnAdd.setId("btnAdd");
        btnAdd.setMaxWidth(1.7976931348623157E308);
        btnAdd.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd.setMnemonicParsing(false);
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalButtonAction(event);
            }
        });
        btnAdd.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd.setText(bundle.getString("SourceSection_Add_External"));
        GridPane.setColumnIndex(btnAdd, 0);
        GridPane.setRowIndex(btnAdd, 1);
        gridPane26.getChildren().add(btnAdd);
        ColumnConstraints columnConstraints52 = new ColumnConstraints();
        columnConstraints52.setHgrow(Priority.SOMETIMES);
        columnConstraints52.setMinWidth(10.0);
        gridPane26.getColumnConstraints().add(columnConstraints52);
        RowConstraints rowConstraints47 = new RowConstraints();
        rowConstraints47.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints47.setVgrow(Priority.NEVER);
        gridPane26.getRowConstraints().add(rowConstraints47);
        RowConstraints rowConstraints48 = new RowConstraints();
        rowConstraints48.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints48.setVgrow(Priority.NEVER);
        gridPane26.getRowConstraints().add(rowConstraints48);
        RowConstraints rowConstraints49 = new RowConstraints();
        rowConstraints49.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints49.setVgrow(Priority.NEVER);
        gridPane26.getRowConstraints().add(rowConstraints49);
        hBox4.getChildren().add(gridPane26);
        vBox19.getChildren().add(hBox4);
        Insets insets26 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox19.setPadding(insets26);
        titledPane9.setContent(vBox19);
        anchorPane10.getChildren().add(titledPane9);
        initialize(null, bundle);
        return anchorPane10;
    }

}
