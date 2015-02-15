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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closure.model.providers.LabelProviders;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSRenamingSectionController extends ClosureController implements Initializable {

    public JSRenamingSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private CheckBox controlRenameLabels;

    @FXML
    private CheckBox controlExportTestFunctions;

    @FXML
    private CheckBox controlPropertyAffinity;

    @FXML
    private CheckBox controlShadowVariables;

    @FXML
    private CheckBox controlGeneratePseudoNames;

    @FXML
    private CheckBox controlDevirtualizePrototypeMethods;

    @FXML
    private ComboBox<JsRenamingPropertyPolice> controlPropertyPolice;

    @FXML
    private ComboBox<JsRenamingFunctionPolice> controlFunctionPolice;

    @FXML
    private ComboBox<JsRenamingVariablePolice> controlVariablePolice;

    @FXML
    private TextField controlVariableInput;

    @FXML
    private TextField controlVariableOutput;

    @FXML
    private TextField controlFunctionInput;

    @FXML
    private TextField controlFunctionOutput;

    @FXML
    private TextField controlPropertyInput;

    @FXML
    private TextField controlPropertyOutput;

    @FXML
    private CheckBox controlDisambiguateProperties;

    @FXML
    private CheckBox controlAmbiguateProperties;

    @FXML
    private Button btnVariableInput;

    @FXML
    private Button btnVariableOutput;

    @FXML
    private Button btnFunctionOutput;

    @FXML
    private Button btnFunctionInput;

    @FXML
    private Button btnPropertyInput;

    @FXML
    private Button btnPropertyOutput;

    public Button getBtnVariableInput() {
        return btnVariableInput;
    }

    public Button getBtnVariableOutput() {
        return btnVariableOutput;
    }

    public Button getBtnFunctionOutput() {
        return btnFunctionOutput;
    }

    public Button getBtnFunctionInput() {
        return btnFunctionInput;
    }

    public Button getBtnPropertyInput() {
        return btnPropertyInput;
    }

    public Button getBtnPropertyOutput() {
        return btnPropertyOutput;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlPropertyPolice.setItems(FXCollections.observableArrayList(JsRenamingPropertyPolice.values()));
        controlPropertyPolice.setButtonCell(new JSRenamingSectionController.PropertyCell());
        controlPropertyPolice.setCellFactory(new JSRenamingSectionController.PropertyCellFactory());
        controlFunctionPolice.setItems(FXCollections.observableArrayList(JsRenamingFunctionPolice.values()));
        controlFunctionPolice.setButtonCell(new JSRenamingSectionController.FunctionCell());
        controlFunctionPolice.setCellFactory(new JSRenamingSectionController.FunctionCellFactory());
        controlVariablePolice.setItems(FXCollections.observableArrayList(JsRenamingVariablePolice.values()));
        controlVariablePolice.setButtonCell(new JSRenamingSectionController.VariableCell());
        controlVariablePolice.setCellFactory(new JSRenamingSectionController.VariableCellFactory());
    }

    @FXML
    private void handleVariableInputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_InputFile, IConstants.JSRenamingSection_Select_InputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSVariableInputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleVariableInputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_InputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSVariableInputPath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handleVariableOutputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_OutputFile, IConstants.JSRenamingSection_Select_OutputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSVariableOutputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleVariableOutputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_OutputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSVariableOutputPath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handlePropertyInputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_InputFile, IConstants.JSRenamingSection_Select_InputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSPropertyInputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handlePropertyInputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_InputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSPropertyInputPath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handlePropertyOutputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_OutputFile, IConstants.JSRenamingSection_Select_OutputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSPropertyOutputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handlePropertyOutputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_OutputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSPropertyOutputPath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handleFunctionInputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_InputFile, IConstants.JSRenamingSection_Select_InputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSFunctionInputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleFunctionInputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_InputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSFunctionInputPath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handleFunctionOutputButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.JSRenamingSection_Select_OutputFile, IConstants.JSRenamingSection_Select_OutputFile, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setJSFunctionOutputPath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleFunctionOutputExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.JSRenamingSection_Select_OutputFile), null, (String[]) null);
        if (file != null) {
            modelFacade.setJSFunctionOutputPath(file.getAbsolutePath(), true);
        }
    }

    public CheckBox getControlRenameLabels() {
        return controlRenameLabels;
    }

    public CheckBox getControlExportTestFunctions() {
        return controlExportTestFunctions;
    }

    public CheckBox getControlPropertyAffinity() {
        return controlPropertyAffinity;
    }

    public CheckBox getControlShadowVariables() {
        return controlShadowVariables;
    }

    public CheckBox getControlGeneratePseudoNames() {
        return controlGeneratePseudoNames;
    }

    public ComboBox<JsRenamingPropertyPolice> getControlPropertyPolice() {
        return controlPropertyPolice;
    }

    public ComboBox<JsRenamingFunctionPolice> getControlFunctionPolice() {
        return controlFunctionPolice;
    }

    public ComboBox<JsRenamingVariablePolice> getControlVariablePolice() {
        return controlVariablePolice;
    }

    public TextField getControlVariableInput() {
        return controlVariableInput;
    }

    public TextField getControlVariableOutput() {
        return controlVariableOutput;
    }

    public TextField getControlFunctionInput() {
        return controlFunctionInput;
    }

    public TextField getControlFunctionOutput() {
        return controlFunctionOutput;
    }

    public TextField getControlPropertyInput() {
        return controlPropertyInput;
    }

    public TextField getControlPropertyOutput() {
        return controlPropertyOutput;
    }

    public CheckBox getControlDisambiguateProperties() {
        return controlDisambiguateProperties;
    }

    public CheckBox getControlAmbiguateProperties() {
        return controlAmbiguateProperties;
    }

    public CheckBox getControlDevirtualizePrototypeMethods() {
        return controlDevirtualizePrototypeMethods;
    }

    private class VariableCellFactory implements Callback<ListView<JsRenamingVariablePolice>, ListCell<JsRenamingVariablePolice>> {

        @Override
        public ListCell<JsRenamingVariablePolice> call(ListView<JsRenamingVariablePolice> param) {
            return new com.digiarea.closure.model.controller.JSRenamingSectionController.VariableCell();
        }

    }

    private class VariableCell extends ListCell<JsRenamingVariablePolice> {

        @Override
        protected void updateItem(JsRenamingVariablePolice item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (item != null) {
                    setText(LabelProviders.getRenamingVariableLabel(item));
                }
            }
        }

    }

    private class PropertyCellFactory implements Callback<ListView<JsRenamingPropertyPolice>, ListCell<JsRenamingPropertyPolice>> {

        @Override
        public ListCell<JsRenamingPropertyPolice> call(ListView<JsRenamingPropertyPolice> param) {
            return new com.digiarea.closure.model.controller.JSRenamingSectionController.PropertyCell();
        }

    }

    private class PropertyCell extends ListCell<JsRenamingPropertyPolice> {

        @Override
        protected void updateItem(JsRenamingPropertyPolice item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (item != null) {
                    setText(LabelProviders.getRenamingPropertyLabel(item));
                }
            }
        }

    }

    private class FunctionCellFactory implements Callback<ListView<JsRenamingFunctionPolice>, ListCell<JsRenamingFunctionPolice>> {

        @Override
        public ListCell<JsRenamingFunctionPolice> call(ListView<JsRenamingFunctionPolice> param) {
            return new com.digiarea.closure.model.controller.JSRenamingSectionController.FunctionCell();
        }

    }

    private class FunctionCell extends ListCell<JsRenamingFunctionPolice> {

        @Override
        protected void updateItem(JsRenamingFunctionPolice item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (item != null) {
                    setText(LabelProviders.getRenamingFunctionLabel(item));
                }
            }
        }

    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane24 = new AnchorPane();
        anchorPane24.setId("AnchorPane");
        anchorPane24.setMaxHeight(Control.USE_COMPUTED_SIZE);
        anchorPane24.setMaxWidth(Control.USE_COMPUTED_SIZE);
        anchorPane24.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane24.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane24.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane24.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane21 = new TitledPane();
        titledPane21.setAnimated(false);
        titledPane21.setCollapsible(false);
        titledPane21.setMaxHeight(Control.USE_COMPUTED_SIZE);
        titledPane21.setMaxWidth(Control.USE_COMPUTED_SIZE);
        titledPane21.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane21.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane21.setText(bundle.getString("JSRenamingSection"));
        AnchorPane.setBottomAnchor(titledPane21, 0.0);
        AnchorPane.setLeftAnchor(titledPane21, 0.0);
        AnchorPane.setRightAnchor(titledPane21, 0.0);
        AnchorPane.setTopAnchor(titledPane21, 0.0);
        VBox vBox43 = new VBox();
        vBox43.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox43.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox43.setSpacing(5.0);
        Label label44 = new Label();
        label44.setMinHeight(Control.USE_PREF_SIZE);
        label44.setText(bundle.getString("JSRenamingSection_Desc"));
        label44.setWrapText(true);
        VBox.setVgrow(label44, Priority.NEVER);
        vBox43.getChildren().add(label44);
        controlRenameLabels = new CheckBox();
        controlRenameLabels.setAlignment(Pos.CENTER_LEFT);
        controlRenameLabels.setContentDisplay(ContentDisplay.RIGHT);
        controlRenameLabels.setMnemonicParsing(false);
        controlRenameLabels.setText(bundle.getString("JSRenamingSection_RenameLabels"));
        controlRenameLabels.setTextOverrun(OverrunStyle.ELLIPSIS);
        controlRenameLabels.setWrapText(true);
        vBox43.getChildren().add(controlRenameLabels);
        controlDevirtualizePrototypeMethods = new CheckBox();
        controlDevirtualizePrototypeMethods.setId("devirtualizePrototypeMethods");
        controlDevirtualizePrototypeMethods.setMnemonicParsing(false);
        controlDevirtualizePrototypeMethods.setText(bundle.getString("JSRenamingSection_DevirtualizePrototype"));
        controlDevirtualizePrototypeMethods.setWrapText(true);
        vBox43.getChildren().add(controlDevirtualizePrototypeMethods);
        controlGeneratePseudoNames = new CheckBox();
        controlGeneratePseudoNames.setMaxWidth(1.7976931348623157E308);
        controlGeneratePseudoNames.setMnemonicParsing(false);
        controlGeneratePseudoNames.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlGeneratePseudoNames.setText(bundle.getString("JSRenamingSection_PseudoNames"));
        controlGeneratePseudoNames.setWrapText(true);
        VBox.setVgrow(controlGeneratePseudoNames, Priority.ALWAYS);
        vBox43.getChildren().add(controlGeneratePseudoNames);
        controlExportTestFunctions = new CheckBox();
        controlExportTestFunctions.setMnemonicParsing(false);
        controlExportTestFunctions.setText(bundle.getString("JSRenamingSection_ExportTest"));
        controlExportTestFunctions.setWrapText(true);
        vBox43.getChildren().add(controlExportTestFunctions);
        Label label45 = new Label();
        label45.setText(bundle.getString("JSRenamingSection_Variables"));
        vBox43.getChildren().add(label45);
        VBox vBox44 = new VBox();
        vBox44.setMinHeight(Control.USE_PREF_SIZE);
        vBox44.setMinWidth(Control.USE_PREF_SIZE);
        vBox44.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox44.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(vBox44, Priority.NEVER);
        GridPane gridPane50 = new GridPane();
        gridPane50.setHgap(5.0);
        gridPane50.setVgap(5.0);
        Label label46 = new Label();
        label46.setText(bundle.getString("JSRenamingSection_RenamingPolice"));
        GridPane.setColumnIndex(label46, 0);
        GridPane.setRowIndex(label46, 0);
        gridPane50.getChildren().add(label46);
        Label label47 = new Label();
        label47.setText(bundle.getString("JSRenamingSection_InputFile"));
        GridPane.setColumnIndex(label47, 0);
        GridPane.setRowIndex(label47, 1);
        gridPane50.getChildren().add(label47);
        Label label48 = new Label();
        label48.setContentDisplay(ContentDisplay.RIGHT);
        label48.setGraphicTextGap(0.0);
        label48.setText(bundle.getString("JSRenamingSection_OutputFile"));
        GridPane.setColumnIndex(label48, 0);
        GridPane.setRowIndex(label48, 2);
        gridPane50.getChildren().add(label48);
        controlVariableInput = new TextField();
        controlVariableInput.setMinWidth(100.0);
        controlVariableInput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlVariableInput, 1);
        GridPane.setHgrow(controlVariableInput, Priority.ALWAYS);
        GridPane.setRowIndex(controlVariableInput, 1);
        gridPane50.getChildren().add(controlVariableInput);
        controlVariablePolice = new ComboBox();
        controlVariablePolice.setMaxWidth(1.7976931348623157E308);
        controlVariablePolice.setMinWidth(100.0);
        controlVariablePolice.setPrefWidth(100.0);
        GridPane.setColumnIndex(controlVariablePolice, 1);
        GridPane.setHalignment(controlVariablePolice, HPos.CENTER);
        GridPane.setHgrow(controlVariablePolice, Priority.ALWAYS);
        GridPane.setRowIndex(controlVariablePolice, 0);
        gridPane50.getChildren().add(controlVariablePolice);
        controlVariableOutput = new TextField();
        controlVariableOutput.setMinWidth(100.0);
        controlVariableOutput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlVariableOutput, 1);
        GridPane.setHgrow(controlVariableOutput, Priority.ALWAYS);
        GridPane.setRowIndex(controlVariableOutput, 2);
        gridPane50.getChildren().add(controlVariableOutput);
        btnVariableInput = new Button();
        btnVariableInput.setMaxWidth(1.7976931348623157E308);
        btnVariableInput.setMnemonicParsing(false);
        btnVariableInput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleVariableInputButtonAction(event);
            }
        });
        btnVariableInput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnVariableInput, 2);
        GridPane.setHgrow(btnVariableInput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnVariableInput, 1);
        gridPane50.getChildren().add(btnVariableInput);
        btnVariableOutput = new Button();
        btnVariableOutput.setMaxWidth(1.7976931348623157E308);
        btnVariableOutput.setMnemonicParsing(false);
        btnVariableOutput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleVariableOutputButtonAction(event);
            }
        });
        btnVariableOutput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnVariableOutput, 2);
        GridPane.setHgrow(btnVariableOutput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnVariableOutput, 2);
        gridPane50.getChildren().add(btnVariableOutput);
        Button button46 = new Button();
        button46.setMaxWidth(1.7976931348623157E308);
        button46.setMnemonicParsing(false);
        button46.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleVariableInputExternalButtonAction(event);
            }
        });
        button46.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button46, 3);
        GridPane.setHgrow(button46, Priority.SOMETIMES);
        GridPane.setRowIndex(button46, 1);
        gridPane50.getChildren().add(button46);
        Button button47 = new Button();
        button47.setMaxWidth(1.7976931348623157E308);
        button47.setMnemonicParsing(false);
        button47.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleVariableOutputExternalButtonAction(event);
            }
        });
        button47.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button47, 3);
        GridPane.setHgrow(button47, Priority.SOMETIMES);
        GridPane.setRowIndex(button47, 2);
        gridPane50.getChildren().add(button47);
        ColumnConstraints columnConstraints106 = new ColumnConstraints();
        columnConstraints106.setHgrow(Priority.NEVER);
        columnConstraints106.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints106.setPercentWidth(-1.0);
        columnConstraints106.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane50.getColumnConstraints().add(columnConstraints106);
        ColumnConstraints columnConstraints107 = new ColumnConstraints();
        columnConstraints107.setHgrow(Priority.ALWAYS);
        columnConstraints107.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints107.setPercentWidth(-1.0);
        columnConstraints107.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane50.getColumnConstraints().add(columnConstraints107);
        ColumnConstraints columnConstraints108 = new ColumnConstraints();
        columnConstraints108.setHgrow(Priority.NEVER);
        columnConstraints108.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints108.setPercentWidth(-1.0);
        columnConstraints108.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane50.getColumnConstraints().add(columnConstraints108);
        ColumnConstraints columnConstraints109 = new ColumnConstraints();
        columnConstraints109.setHgrow(Priority.NEVER);
        columnConstraints109.setMinWidth(Control.USE_PREF_SIZE);
        gridPane50.getColumnConstraints().add(columnConstraints109);
        Insets insets63 = new Insets(0.0, 0.0, 0.0, 0.0);
        gridPane50.setPadding(insets63);
        RowConstraints rowConstraints97 = new RowConstraints();
        rowConstraints97.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints97.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints97.setVgrow(Priority.NEVER);
        gridPane50.getRowConstraints().add(rowConstraints97);
        RowConstraints rowConstraints98 = new RowConstraints();
        rowConstraints98.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints98.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints98.setMaxHeight(Control.USE_PREF_SIZE);
        rowConstraints98.setVgrow(Priority.NEVER);
        gridPane50.getRowConstraints().add(rowConstraints98);
        RowConstraints rowConstraints99 = new RowConstraints();
        rowConstraints99.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints99.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints99.setMaxHeight(Control.USE_PREF_SIZE);
        rowConstraints99.setVgrow(Priority.NEVER);
        gridPane50.getRowConstraints().add(rowConstraints99);
        vBox44.getChildren().add(gridPane50);
        controlShadowVariables = new CheckBox();
        controlShadowVariables.setMnemonicParsing(false);
        controlShadowVariables.setText(bundle.getString("JSRenamingSection_VariablesShadow"));
        controlShadowVariables.setWrapText(true);
        vBox44.getChildren().add(controlShadowVariables);
        Insets insets64 = new Insets(5.0, 5.0, 5.0, 5.0);
        vBox44.setPadding(insets64);
        vBox43.getChildren().add(vBox44);
        Label label49 = new Label();
        label49.setText(bundle.getString("JSRenamingSection_Properties"));
        vBox43.getChildren().add(label49);
        VBox vBox45 = new VBox();
        vBox45.setMinHeight(Control.USE_PREF_SIZE);
        vBox45.setMinWidth(Control.USE_PREF_SIZE);
        vBox45.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox45.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox45.setSpacing(5.0);
        VBox.setVgrow(vBox45, Priority.NEVER);
        GridPane gridPane51 = new GridPane();
        gridPane51.setHgap(5.0);
        gridPane51.setVgap(5.0);
        Label label50 = new Label();
        label50.setText(bundle.getString("JSRenamingSection_RenamingPolice"));
        GridPane.setColumnIndex(label50, 0);
        GridPane.setRowIndex(label50, 0);
        gridPane51.getChildren().add(label50);
        Label label51 = new Label();
        label51.setText(bundle.getString("JSRenamingSection_InputFile"));
        GridPane.setColumnIndex(label51, 0);
        GridPane.setRowIndex(label51, 1);
        gridPane51.getChildren().add(label51);
        Label label52 = new Label();
        label52.setText(bundle.getString("JSRenamingSection_OutputFile"));
        GridPane.setColumnIndex(label52, 0);
        GridPane.setRowIndex(label52, 2);
        gridPane51.getChildren().add(label52);
        controlPropertyInput = new TextField();
        controlPropertyInput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlPropertyInput, 1);
        GridPane.setHgrow(controlPropertyInput, Priority.ALWAYS);
        GridPane.setRowIndex(controlPropertyInput, 1);
        gridPane51.getChildren().add(controlPropertyInput);
        controlPropertyOutput = new TextField();
        controlPropertyOutput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlPropertyOutput, 1);
        GridPane.setHgrow(controlPropertyOutput, Priority.ALWAYS);
        GridPane.setRowIndex(controlPropertyOutput, 2);
        gridPane51.getChildren().add(controlPropertyOutput);
        controlPropertyPolice = new ComboBox();
        controlPropertyPolice.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlPropertyPolice, 1);
        GridPane.setHalignment(controlPropertyPolice, HPos.CENTER);
        GridPane.setHgrow(controlPropertyPolice, Priority.ALWAYS);
        GridPane.setRowIndex(controlPropertyPolice, 0);
        gridPane51.getChildren().add(controlPropertyPolice);
        btnFunctionInput = new Button();
        btnFunctionInput.setId("btnFunctionOutput");
        btnFunctionInput.setMaxWidth(1.7976931348623157E308);
        btnFunctionInput.setMnemonicParsing(false);
        btnFunctionInput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePropertyInputButtonAction(event);
            }
        });
        btnFunctionInput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnFunctionInput, 2);
        GridPane.setHgrow(btnFunctionInput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnFunctionInput, 1);
        gridPane51.getChildren().add(btnFunctionInput);
        btnFunctionOutput = new Button();
        btnFunctionOutput.setMaxWidth(1.7976931348623157E308);
        btnFunctionOutput.setMnemonicParsing(false);
        btnFunctionOutput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePropertyOutputButtonAction(event);
            }
        });
        btnFunctionOutput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnFunctionOutput, 2);
        GridPane.setHgrow(btnFunctionOutput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnFunctionOutput, 2);
        gridPane51.getChildren().add(btnFunctionOutput);
        Button button48 = new Button();
        button48.setMaxWidth(1.7976931348623157E308);
        button48.setMnemonicParsing(false);
        button48.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePropertyInputExternalButtonAction(event);
            }
        });
        button48.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button48, 3);
        GridPane.setHgrow(button48, Priority.SOMETIMES);
        GridPane.setRowIndex(button48, 1);
        gridPane51.getChildren().add(button48);
        Button button49 = new Button();
        button49.setMaxWidth(1.7976931348623157E308);
        button49.setMnemonicParsing(false);
        button49.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePropertyOutputExternalButtonAction(event);
            }
        });
        button49.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button49, 3);
        GridPane.setHgrow(button49, Priority.SOMETIMES);
        GridPane.setRowIndex(button49, 2);
        gridPane51.getChildren().add(button49);
        ColumnConstraints columnConstraints110 = new ColumnConstraints();
        columnConstraints110.setHgrow(Priority.NEVER);
        columnConstraints110.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints110.setPercentWidth(-1.0);
        columnConstraints110.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane51.getColumnConstraints().add(columnConstraints110);
        ColumnConstraints columnConstraints111 = new ColumnConstraints();
        columnConstraints111.setHgrow(Priority.ALWAYS);
        columnConstraints111.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints111.setPercentWidth(-1.0);
        columnConstraints111.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane51.getColumnConstraints().add(columnConstraints111);
        ColumnConstraints columnConstraints112 = new ColumnConstraints();
        columnConstraints112.setHgrow(Priority.NEVER);
        columnConstraints112.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints112.setPercentWidth(-1.0);
        columnConstraints112.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane51.getColumnConstraints().add(columnConstraints112);
        ColumnConstraints columnConstraints113 = new ColumnConstraints();
        columnConstraints113.setHgrow(Priority.NEVER);
        columnConstraints113.setMinWidth(Control.USE_PREF_SIZE);
        gridPane51.getColumnConstraints().add(columnConstraints113);
        Insets insets65 = new Insets(0.0, 0.0, 0.0, 0.0);
        gridPane51.setPadding(insets65);
        RowConstraints rowConstraints100 = new RowConstraints();
        rowConstraints100.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints100.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints100.setVgrow(Priority.NEVER);
        gridPane51.getRowConstraints().add(rowConstraints100);
        RowConstraints rowConstraints101 = new RowConstraints();
        rowConstraints101.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints101.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints101.setMaxHeight(Control.USE_PREF_SIZE);
        rowConstraints101.setVgrow(Priority.NEVER);
        gridPane51.getRowConstraints().add(rowConstraints101);
        RowConstraints rowConstraints102 = new RowConstraints();
        rowConstraints102.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints102.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints102.setMaxHeight(Control.USE_PREF_SIZE);
        rowConstraints102.setVgrow(Priority.NEVER);
        gridPane51.getRowConstraints().add(rowConstraints102);
        vBox45.getChildren().add(gridPane51);
        controlPropertyAffinity = new CheckBox();
        controlPropertyAffinity.setContentDisplay(ContentDisplay.RIGHT);
        controlPropertyAffinity.setDisable(false);
        controlPropertyAffinity.setFocusTraversable(true);
        controlPropertyAffinity.setGraphicTextGap(0.0);
        controlPropertyAffinity.setMaxWidth(1.7976931348623157E308);
        controlPropertyAffinity.setMnemonicParsing(false);
        controlPropertyAffinity.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlPropertyAffinity.setText(bundle.getString("JSRenamingSection_PropertiesAffinity"));
        controlPropertyAffinity.setVisible(true);
        controlPropertyAffinity.setWrapText(true);
        VBox.setVgrow(controlPropertyAffinity, Priority.ALWAYS);
        vBox45.getChildren().add(controlPropertyAffinity);
        controlDisambiguateProperties = new CheckBox();
        controlDisambiguateProperties.setMaxWidth(1.7976931348623157E308);
        controlDisambiguateProperties.setMnemonicParsing(false);
        controlDisambiguateProperties.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlDisambiguateProperties.setText(bundle.getString("JSRenamingSection_PropertiesDisambiguate"));
        controlDisambiguateProperties.setWrapText(true);
        VBox.setVgrow(controlDisambiguateProperties, Priority.ALWAYS);
        vBox45.getChildren().add(controlDisambiguateProperties);
        controlAmbiguateProperties = new CheckBox();
        controlAmbiguateProperties.setMaxWidth(1.7976931348623157E308);
        controlAmbiguateProperties.setMnemonicParsing(false);
        controlAmbiguateProperties.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlAmbiguateProperties.setText(bundle.getString("JSRenamingSection_PropertiesAmbiguate"));
        controlAmbiguateProperties.setWrapText(true);
        VBox.setVgrow(controlAmbiguateProperties, Priority.ALWAYS);
        vBox45.getChildren().add(controlAmbiguateProperties);
        Insets insets66 = new Insets(5.0, 5.0, 5.0, 5.0);
        vBox45.setPadding(insets66);
        vBox43.getChildren().add(vBox45);
        Label label53 = new Label();
        label53.setText(bundle.getString("JSRenamingSection_Functions"));
        vBox43.getChildren().add(label53);
        VBox vBox46 = new VBox();
        vBox46.setMinHeight(Control.USE_PREF_SIZE);
        vBox46.setMinWidth(Control.USE_PREF_SIZE);
        vBox46.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox46.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(vBox46, Priority.NEVER);
        GridPane gridPane52 = new GridPane();
        gridPane52.setHgap(5.0);
        gridPane52.setVgap(5.0);
        Label label54 = new Label();
        label54.setText(bundle.getString("JSRenamingSection_RenamingPolice"));
        GridPane.setColumnIndex(label54, 0);
        GridPane.setRowIndex(label54, 0);
        gridPane52.getChildren().add(label54);
        Label label55 = new Label();
        label55.setText(bundle.getString("JSRenamingSection_InputFile"));
        GridPane.setColumnIndex(label55, 0);
        GridPane.setRowIndex(label55, 1);
        gridPane52.getChildren().add(label55);
        Label label56 = new Label();
        label56.setText(bundle.getString("JSRenamingSection_OutputFile"));
        GridPane.setColumnIndex(label56, 0);
        GridPane.setRowIndex(label56, 2);
        gridPane52.getChildren().add(label56);
        controlFunctionInput = new TextField();
        controlFunctionInput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlFunctionInput, 1);
        GridPane.setHgrow(controlFunctionInput, Priority.ALWAYS);
        GridPane.setRowIndex(controlFunctionInput, 1);
        gridPane52.getChildren().add(controlFunctionInput);
        controlFunctionOutput = new TextField();
        controlFunctionOutput.setPrefWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(controlFunctionOutput, 1);
        GridPane.setHgrow(controlFunctionOutput, Priority.ALWAYS);
        GridPane.setRowIndex(controlFunctionOutput, 2);
        gridPane52.getChildren().add(controlFunctionOutput);
        controlFunctionPolice = new ComboBox();
        controlFunctionPolice.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlFunctionPolice, 1);
        GridPane.setHalignment(controlFunctionPolice, HPos.CENTER);
        GridPane.setHgrow(controlFunctionPolice, Priority.ALWAYS);
        GridPane.setRowIndex(controlFunctionPolice, 0);
        gridPane52.getChildren().add(controlFunctionPolice);
        btnPropertyInput = new Button();
        btnPropertyInput.setId("btnFunctionInput");
        btnPropertyInput.setMaxWidth(1.7976931348623157E308);
        btnPropertyInput.setMnemonicParsing(false);
        btnPropertyInput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleFunctionInputButtonAction(event);
            }
        });
        btnPropertyInput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnPropertyInput, 2);
        GridPane.setHgrow(btnPropertyInput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnPropertyInput, 1);
        gridPane52.getChildren().add(btnPropertyInput);
        btnPropertyOutput = new Button();
        btnPropertyOutput.setMaxWidth(1.7976931348623157E308);
        btnPropertyOutput.setMnemonicParsing(false);
        btnPropertyOutput.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleFunctionOutputButtonAction(event);
            }
        });
        btnPropertyOutput.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(btnPropertyOutput, 2);
        GridPane.setHgrow(btnPropertyOutput, Priority.SOMETIMES);
        GridPane.setRowIndex(btnPropertyOutput, 2);
        gridPane52.getChildren().add(btnPropertyOutput);
        Button button50 = new Button();
        button50.setMaxWidth(1.7976931348623157E308);
        button50.setMnemonicParsing(false);
        button50.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleFunctionInputExternalButtonAction(event);
            }
        });
        button50.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button50, 3);
        GridPane.setHgrow(button50, Priority.SOMETIMES);
        GridPane.setRowIndex(button50, 1);
        gridPane52.getChildren().add(button50);
        Button button51 = new Button();
        button51.setMaxWidth(1.7976931348623157E308);
        button51.setMnemonicParsing(false);
        button51.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleFunctionOutputExternalButtonAction(event);
            }
        });
        button51.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button51, 3);
        GridPane.setHgrow(button51, Priority.SOMETIMES);
        GridPane.setRowIndex(button51, 2);
        gridPane52.getChildren().add(button51);
        ColumnConstraints columnConstraints114 = new ColumnConstraints();
        columnConstraints114.setHgrow(Priority.NEVER);
        columnConstraints114.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints114.setPercentWidth(-1.0);
        columnConstraints114.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane52.getColumnConstraints().add(columnConstraints114);
        ColumnConstraints columnConstraints115 = new ColumnConstraints();
        columnConstraints115.setHgrow(Priority.ALWAYS);
        columnConstraints115.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints115.setPercentWidth(-1.0);
        columnConstraints115.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane52.getColumnConstraints().add(columnConstraints115);
        ColumnConstraints columnConstraints116 = new ColumnConstraints();
        columnConstraints116.setHgrow(Priority.NEVER);
        columnConstraints116.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints116.setPercentWidth(-1.0);
        columnConstraints116.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane52.getColumnConstraints().add(columnConstraints116);
        ColumnConstraints columnConstraints117 = new ColumnConstraints();
        columnConstraints117.setHgrow(Priority.NEVER);
        columnConstraints117.setMinWidth(Control.USE_PREF_SIZE);
        gridPane52.getColumnConstraints().add(columnConstraints117);
        Insets insets67 = new Insets(5.0, 5.0, 5.0, 5.0);
        gridPane52.setPadding(insets67);
        RowConstraints rowConstraints103 = new RowConstraints();
        rowConstraints103.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints103.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints103.setVgrow(Priority.NEVER);
        gridPane52.getRowConstraints().add(rowConstraints103);
        RowConstraints rowConstraints104 = new RowConstraints();
        rowConstraints104.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints104.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints104.setVgrow(Priority.NEVER);
        gridPane52.getRowConstraints().add(rowConstraints104);
        RowConstraints rowConstraints105 = new RowConstraints();
        rowConstraints105.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints105.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints105.setVgrow(Priority.NEVER);
        gridPane52.getRowConstraints().add(rowConstraints105);
        vBox46.getChildren().add(gridPane52);
        vBox43.getChildren().add(vBox46);
        Insets insets68 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox43.setPadding(insets68);
        titledPane21.setContent(vBox43);
        anchorPane24.getChildren().add(titledPane21);
        initialize(null, bundle);
        return anchorPane24;
    }

}
