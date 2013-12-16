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
import com.digiarea.closure.model.providers.BuildpathCell;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSSourceSectionController extends ClosureController implements Initializable {

    public JSSourceSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
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

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SourceSection_Add_Title, IConstants.SourceSection_Add_Desc, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.addSource(controller.getSelectedFile().getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.JSC, false);
            }
        }
    }

    @FXML
    private void handleAddExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(lastFile, bundle.getString(IConstants.SourceSection_Add_Title));
        if (file != null) {
            modelFacade.addSource(file.getAbsolutePath(), SourceEntry.SOURCE, SourceEntity.JSC, true);
            lastFile = file;
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

    public Button getBtnAdd() {
        return btnAdd;
    }

    public ListView<Source> getControlSource() {
        return controlSource;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane26 = new AnchorPane();
        anchorPane26.setId("AnchorPane");
        anchorPane26.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane26.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane26.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane26.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane23 = new TitledPane();
        titledPane23.setAnimated(false);
        titledPane23.setCollapsible(false);
        titledPane23.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane23.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane23.setText(bundle.getString("SourceSection"));
        AnchorPane.setBottomAnchor(titledPane23, 0.0);
        AnchorPane.setLeftAnchor(titledPane23, 0.0);
        AnchorPane.setRightAnchor(titledPane23, 0.0);
        AnchorPane.setTopAnchor(titledPane23, 0.0);
        VBox vBox48 = new VBox();
        vBox48.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox48.setPrefWidth(Control.USE_COMPUTED_SIZE);
        Label label60 = new Label();
        label60.setText(bundle.getString("SourceSection_Desc"));
        label60.setWrapText(true);
        vBox48.getChildren().add(label60);
        HBox hBox11 = new HBox();
        hBox11.setPrefHeight(100.0);
        hBox11.setPrefWidth(200.0);
        hBox11.setSpacing(5.0);
        VBox.setVgrow(hBox11, Priority.ALWAYS);
        controlSource = new ListView();
        controlSource.setPrefHeight(Control.USE_COMPUTED_SIZE);
        controlSource.setPrefWidth(Control.USE_COMPUTED_SIZE);
        HBox.setHgrow(controlSource, Priority.ALWAYS);
        hBox11.getChildren().add(controlSource);
        GridPane gridPane55 = new GridPane();
        gridPane55.setId("GridPane");
        gridPane55.setMinWidth(Control.USE_PREF_SIZE);
        gridPane55.setVgap(5.0);
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
        gridPane55.getChildren().add(btnAdd);
        Button btnAdd3 = new Button();
        btnAdd3.setId("btnAdd");
        btnAdd3.setMaxWidth(1.7976931348623157E308);
        btnAdd3.setMinWidth(Control.USE_PREF_SIZE);
        btnAdd3.setMnemonicParsing(false);
        btnAdd3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddExternalButtonAction(event);
            }
        });
        btnAdd3.setPrefHeight(Control.USE_COMPUTED_SIZE);
        btnAdd3.setPrefWidth(Control.USE_COMPUTED_SIZE);
        btnAdd3.setText(bundle.getString("SourceSection_Add_External"));
        GridPane.setColumnIndex(btnAdd3, 0);
        GridPane.setRowIndex(btnAdd3, 1);
        gridPane55.getChildren().add(btnAdd3);
        Button button53 = new Button();
        button53.setMaxWidth(1.7976931348623157E308);
        button53.setMinWidth(Control.USE_PREF_SIZE);
        button53.setMnemonicParsing(false);
        button53.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button53.setPrefWidth(Control.USE_COMPUTED_SIZE);
        button53.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button53, 0);
        GridPane.setRowIndex(button53, 2);
        gridPane55.getChildren().add(button53);
        ColumnConstraints columnConstraints123 = new ColumnConstraints();
        columnConstraints123.setHgrow(Priority.SOMETIMES);
        columnConstraints123.setMinWidth(10.0);
        gridPane55.getColumnConstraints().add(columnConstraints123);
        RowConstraints rowConstraints109 = new RowConstraints();
        rowConstraints109.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints109.setVgrow(Priority.NEVER);
        gridPane55.getRowConstraints().add(rowConstraints109);
        RowConstraints rowConstraints110 = new RowConstraints();
        rowConstraints110.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints110.setVgrow(Priority.NEVER);
        gridPane55.getRowConstraints().add(rowConstraints110);
        RowConstraints rowConstraints111 = new RowConstraints();
        rowConstraints111.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints111.setVgrow(Priority.NEVER);
        gridPane55.getRowConstraints().add(rowConstraints111);
        hBox11.getChildren().add(gridPane55);
        vBox48.getChildren().add(hBox11);
        Insets insets70 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox48.setPadding(insets70);
        titledPane23.setContent(vBox48);
        anchorPane26.getChildren().add(titledPane23);
        initialize(null, bundle);
        return anchorPane26;
    }

}
