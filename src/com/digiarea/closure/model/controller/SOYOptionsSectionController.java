package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import com.digiarea.closure.model.SoyCssSchemeType;
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
public class SOYOptionsSectionController extends ClosureController implements Initializable {

    public SOYOptionsSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ComboBox<SoyCssSchemeType> controlCssScheme;

    @FXML
    private CheckBox controlAllowExternalCalls;

    @FXML
    private CheckBox controlUsingIjData;

    @FXML
    private CheckBox controlGenerateJsDoc;

    @FXML
    private CheckBox controlProvideRequireSoyNamespaces;

    @FXML
    private CheckBox controlDeclareTopLevelNamespace;

    @FXML
    private CheckBox controlGenerateGoogMessagesDefs;

    @FXML
    private CheckBox controlGoogMessagesExternal;

    @FXML
    private CheckBox controlRightToLeftDir;

    @FXML
    private CheckBox controlRightToLeftDirGoog;

    @FXML
    private TextField controlGlobalsPath;

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
        controlCssScheme.setItems(FXCollections.observableArrayList(SoyCssSchemeType.values()));
        controlCssScheme.setButtonCell(new SOYOptionsSectionController.SchemeCellFactory());
        controlCssScheme.setCellFactory(new Callback<ListView<SoyCssSchemeType>, ListCell<SoyCssSchemeType>>() {

            @Override
            public ListCell<SoyCssSchemeType> call(ListView<SoyCssSchemeType> p) {
                return new SOYOptionsSectionController.SchemeCellFactory();
            }
        });
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SOYOptionsSection_BrowseTitle, IConstants.SOYOptionsSection_BrowseTitle, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setSOYGlobalsPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.SOYOptionsSection_BrowseTitle), null, (String[]) null);
        if (file != null) {
            modelFacade.setSOYGlobalsPath(file.getAbsolutePath(), true);
        }
    }

    public ComboBox<SoyCssSchemeType> getControlCssScheme() {
        return controlCssScheme;
    }

    public CheckBox getControlAllowExternalCalls() {
        return controlAllowExternalCalls;
    }

    public CheckBox getControlUsingIjData() {
        return controlUsingIjData;
    }

    public CheckBox getControlGenerateJsDoc() {
        return controlGenerateJsDoc;
    }

    public CheckBox getControlProvideRequireSoyNamespaces() {
        return controlProvideRequireSoyNamespaces;
    }

    public CheckBox getControlDeclareTopLevelNamespace() {
        return controlDeclareTopLevelNamespace;
    }

    public CheckBox getControlGenerateGoogMessagesDefs() {
        return controlGenerateGoogMessagesDefs;
    }

    public CheckBox getControlGoogMessagesExternal() {
        return controlGoogMessagesExternal;
    }

    public CheckBox getControlRightToLeftDir() {
        return controlRightToLeftDir;
    }

    public CheckBox getControlRightToLeftDirGoog() {
        return controlRightToLeftDirGoog;
    }

    public TextField getControlGlobalsPath() {
        return controlGlobalsPath;
    }

    private class SchemeCellFactory extends ListCell<SoyCssSchemeType> {

        @Override
        protected void updateItem(SoyCssSchemeType item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (item != null) {
                    setText(item.name());
                }
            }
        }

    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane32 = new AnchorPane();
        anchorPane32.setId("AnchorPane");
        anchorPane32.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane32.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane32.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane32.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane29 = new TitledPane();
        titledPane29.setAnimated(false);
        titledPane29.setCollapsible(false);
        titledPane29.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane29.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane29.setText(bundle.getString("SOYOptionsSection"));
        AnchorPane.setBottomAnchor(titledPane29, 0.0);
        AnchorPane.setLeftAnchor(titledPane29, 0.0);
        AnchorPane.setRightAnchor(titledPane29, 0.0);
        AnchorPane.setTopAnchor(titledPane29, 0.0);
        VBox vBox57 = new VBox();
        vBox57.setPrefHeight(200.0);
        vBox57.setPrefWidth(100.0);
        vBox57.setSpacing(5.0);
        Label label74 = new Label();
        label74.setText(bundle.getString("SOYOptionsSection_Desc"));
        vBox57.getChildren().add(label74);
        GridPane gridPane67 = new GridPane();
        gridPane67.setAlignment(Pos.TOP_LEFT);
        gridPane67.setHgap(5.0);
        gridPane67.setMinHeight(Control.USE_PREF_SIZE);
        gridPane67.setMinWidth(Control.USE_PREF_SIZE);
        VBox.setVgrow(gridPane67, Priority.NEVER);
        controlGlobalsPath = new TextField();
        controlGlobalsPath.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlGlobalsPath, 1);
        GridPane.setRowIndex(controlGlobalsPath, 0);
        gridPane67.getChildren().add(controlGlobalsPath);
        Label label75 = new Label();
        label75.setContentDisplay(ContentDisplay.LEFT);
        label75.setText(bundle.getString("SOYOptionsSection_GlobalsFile"));
        label75.setTextAlignment(TextAlignment.LEFT);
        GridPane.setColumnIndex(label75, 0);
        GridPane.setHalignment(label75, HPos.RIGHT);
        GridPane.setRowIndex(label75, 0);
        gridPane67.getChildren().add(label75);
        btnBrowse = new Button();
        btnBrowse.setMaxWidth(1.7976931348623157E308);
        btnBrowse.setMnemonicParsing(false);
        btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseButtonAction(event);
            }
        });
        btnBrowse.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnBrowse, 2);
        GridPane.setRowIndex(btnBrowse, 0);
        GridPane.setValignment(btnBrowse, VPos.CENTER);
        Insets insets85 = new Insets(-2.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(btnBrowse, insets85);
        gridPane67.getChildren().add(btnBrowse);
        Button button65 = new Button();
        button65.setMaxWidth(1.7976931348623157E308);
        button65.setMnemonicParsing(false);
        button65.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button65.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button65, 3);
        GridPane.setRowIndex(button65, 0);
        GridPane.setValignment(button65, VPos.CENTER);
        Insets insets86 = new Insets(-2.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(button65, insets86);
        gridPane67.getChildren().add(button65);
        ColumnConstraints columnConstraints148 = new ColumnConstraints();
        columnConstraints148.setHgrow(Priority.NEVER);
        columnConstraints148.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints148.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane67.getColumnConstraints().add(columnConstraints148);
        ColumnConstraints columnConstraints149 = new ColumnConstraints();
        columnConstraints149.setHgrow(Priority.SOMETIMES);
        columnConstraints149.setMinWidth(10.0);
        columnConstraints149.setPrefWidth(100.0);
        gridPane67.getColumnConstraints().add(columnConstraints149);
        ColumnConstraints columnConstraints150 = new ColumnConstraints();
        columnConstraints150.setHgrow(Priority.NEVER);
        columnConstraints150.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints150.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane67.getColumnConstraints().add(columnConstraints150);
        ColumnConstraints columnConstraints151 = new ColumnConstraints();
        columnConstraints151.setHgrow(Priority.NEVER);
        gridPane67.getColumnConstraints().add(columnConstraints151);
        RowConstraints rowConstraints134 = new RowConstraints();
        rowConstraints134.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints134.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints134.setVgrow(Priority.NEVER);
        gridPane67.getRowConstraints().add(rowConstraints134);
        vBox57.getChildren().add(gridPane67);
        GridPane gridPane68 = new GridPane();
        gridPane68.setAlignment(Pos.TOP_LEFT);
        gridPane68.setHgap(5.0);
        gridPane68.setMinHeight(Control.USE_PREF_SIZE);
        gridPane68.setMinWidth(Control.USE_PREF_SIZE);
        VBox.setVgrow(gridPane68, Priority.NEVER);
        controlCssScheme = new ComboBox();
        controlCssScheme.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlCssScheme, 1);
        GridPane.setRowIndex(controlCssScheme, 0);
        gridPane68.getChildren().add(controlCssScheme);
        Label label76 = new Label();
        label76.setText(bundle.getString("SOYOptionsSection_Css"));
        GridPane.setColumnIndex(label76, 0);
        GridPane.setHalignment(label76, HPos.RIGHT);
        GridPane.setRowIndex(label76, 0);
        gridPane68.getChildren().add(label76);
        ColumnConstraints columnConstraints152 = new ColumnConstraints();
        columnConstraints152.setHgrow(Priority.NEVER);
        columnConstraints152.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints152.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane68.getColumnConstraints().add(columnConstraints152);
        ColumnConstraints columnConstraints153 = new ColumnConstraints();
        columnConstraints153.setHgrow(Priority.SOMETIMES);
        columnConstraints153.setMinWidth(10.0);
        columnConstraints153.setPrefWidth(100.0);
        gridPane68.getColumnConstraints().add(columnConstraints153);
        RowConstraints rowConstraints135 = new RowConstraints();
        rowConstraints135.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints135.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints135.setVgrow(Priority.NEVER);
        gridPane68.getRowConstraints().add(rowConstraints135);
        vBox57.getChildren().add(gridPane68);
        controlProvideRequireSoyNamespaces = new CheckBox();
        controlProvideRequireSoyNamespaces.setMnemonicParsing(false);
        controlProvideRequireSoyNamespaces.setText(bundle.getString("SOYOptionsSection_Check_GoogProvide"));
        controlProvideRequireSoyNamespaces.setWrapText(true);
        vBox57.getChildren().add(controlProvideRequireSoyNamespaces);
        controlDeclareTopLevelNamespace = new CheckBox();
        controlDeclareTopLevelNamespace.setMnemonicParsing(false);
        controlDeclareTopLevelNamespace.setText(bundle.getString("SOYOptionsSection_Check_Namespace"));
        vBox57.getChildren().add(controlDeclareTopLevelNamespace);
        controlGenerateJsDoc = new CheckBox();
        controlGenerateJsDoc.setMnemonicParsing(false);
        controlGenerateJsDoc.setText(bundle.getString("SOYOptionsSection_Check_Docs"));
        vBox57.getChildren().add(controlGenerateJsDoc);
        controlGenerateGoogMessagesDefs = new CheckBox();
        controlGenerateGoogMessagesDefs.setMnemonicParsing(false);
        controlGenerateGoogMessagesDefs.setText(bundle.getString("SOYOptionsSection_Check_Msg"));
        Insets insets87 = new Insets(10.0, 0.0, 0.0, 0.0);
        VBox.setMargin(controlGenerateGoogMessagesDefs, insets87);
        vBox57.getChildren().add(controlGenerateGoogMessagesDefs);
        controlGoogMessagesExternal = new CheckBox();
        controlGoogMessagesExternal.setMnemonicParsing(false);
        controlGoogMessagesExternal.setText(bundle.getString("SOYOptionsSection_Check_MsgExternal"));
        vBox57.getChildren().add(controlGoogMessagesExternal);
        controlRightToLeftDir = new CheckBox();
        controlRightToLeftDir.setMnemonicParsing(false);
        controlRightToLeftDir.setText(bundle.getString("SOYOptionsSection_Check_Rtl"));
        vBox57.getChildren().add(controlRightToLeftDir);
        controlRightToLeftDirGoog = new CheckBox();
        controlRightToLeftDirGoog.setMnemonicParsing(false);
        controlRightToLeftDirGoog.setText(bundle.getString("SOYOptionsSection_Check_RtlGoog"));
        vBox57.getChildren().add(controlRightToLeftDirGoog);
        controlUsingIjData = new CheckBox();
        controlUsingIjData.setMnemonicParsing(false);
        controlUsingIjData.setText(bundle.getString("SOYOptionsSection_Check_Injected"));
        Insets insets88 = new Insets(10.0, 0.0, 0.0, 0.0);
        VBox.setMargin(controlUsingIjData, insets88);
        vBox57.getChildren().add(controlUsingIjData);
        controlAllowExternalCalls = new CheckBox();
        controlAllowExternalCalls.setMnemonicParsing(false);
        controlAllowExternalCalls.setText(bundle.getString("SOYOptionsSection_Check_ExternalCalls"));
        vBox57.getChildren().add(controlAllowExternalCalls);
        Insets insets89 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox57.setPadding(insets89);
        titledPane29.setContent(vBox57);
        anchorPane32.getChildren().add(titledPane29);
        initialize(null, bundle);
        return anchorPane32;
    }

}
