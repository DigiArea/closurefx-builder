package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
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
public class GSSRenamingSectionController extends ClosureController implements Initializable {

    public GSSRenamingSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ToggleGroup renameGroup;

    @FXML
    private TextField controlCssRenamingPrefix;

    @FXML
    private TextField controlOutputRenamingMap;

    @FXML
    private ComboBox<GssOutputRenamingMapFormat> controlOutputRenamingMapFormat;

    @FXML
    private ListView<GssExcludedClass> controlGssExcludedClass;

    @FXML
    private RadioButton controlNone;

    @FXML
    private RadioButton controlDebug;

    @FXML
    private RadioButton controlClosure;

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
        controlOutputRenamingMapFormat.setItems(FXCollections.observableArrayList(GssOutputRenamingMapFormat.values()));
        controlGssExcludedClass.setEditable(true);
        controlGssExcludedClass.setCellFactory(new GSSRenamingSectionController.ExcludedClassCellFactory());
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        GssExcludedClass clazz = new GssExcludedClass();
        clazz.setValue("class");
        modelFacade.addGSSExcludedClass(clazz);
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<GssExcludedClass> classes = controlGssExcludedClass.getSelectionModel().getSelectedItems();
        if (classes != null && !classes.isEmpty()) {
            for (GssExcludedClass clazz : classes) {
                modelFacade.removeGSSExcludedClass(clazz);
            }
        }
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.GSSRenamingSection_BrowseTitle, IConstants.GSSRenamingSection_BrowseTitle, modelFacade.getDocument().getFile().getParentFile(), false, false, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.settGSSRenameFile(controller.getSelectedFile().getAbsolutePath(), true);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFile(bundle.getString(IConstants.GSSRenamingSection_BrowseTitle), null, (String[]) null);
        if (file != null) {
            modelFacade.settGSSRenameFile(file.getAbsolutePath(), true);
        }
    }

    public TextField getControlCssRenamingPrefix() {
        return controlCssRenamingPrefix;
    }

    public TextField getControlOutputRenamingMap() {
        return controlOutputRenamingMap;
    }

    public ComboBox<GssOutputRenamingMapFormat> getControlOutputRenamingMapFormat() {
        return controlOutputRenamingMapFormat;
    }

    public RadioButton getControlNone() {
        return controlNone;
    }

    public RadioButton getControlDebug() {
        return controlDebug;
    }

    public RadioButton getControlClosure() {
        return controlClosure;
    }

    private class ExcludedClassCellFactory implements Callback<ListView<GssExcludedClass>, ListCell<GssExcludedClass>> {

        @Override
        public ListCell<GssExcludedClass> call(ListView<GssExcludedClass> list) {
            return new com.digiarea.closure.model.controller.GSSRenamingSectionController.ExcludedClassListCell();
        }

    }

    private class ExcludedClassListCell extends ListCell<GssExcludedClass> {

        private TextField textField;

        public ExcludedClassListCell() {
        }

        @Override
        public void startEdit() {
            if (!isEditable() || !getListView().isEditable()) {
                return;
            }
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().getValue());
            setGraphic(null);
        }

        @Override
        public void updateItem(GssExcludedClass item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        getItem().setValue(textField.getText());
                        commitEdit(getItem());
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().getValue();
        }

    }

    public ListView<GssExcludedClass> getControlGssExcludedClass() {
        return controlGssExcludedClass;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane9 = new AnchorPane();
        anchorPane9.setId("AnchorPane");
        anchorPane9.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane9.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane9.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane9.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane8 = new TitledPane();
        titledPane8.setAnimated(false);
        titledPane8.setCollapsible(false);
        titledPane8.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane8.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane8.setText(bundle.getString("GSSRenamingSection"));
        AnchorPane.setBottomAnchor(titledPane8, 0.0);
        AnchorPane.setLeftAnchor(titledPane8, 0.0);
        AnchorPane.setRightAnchor(titledPane8, 0.0);
        AnchorPane.setTopAnchor(titledPane8, 0.0);
        VBox vBox18 = new VBox();
        vBox18.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox18.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox18.setSpacing(5.0);
        Label label15 = new Label();
        label15.setMinHeight(Control.USE_PREF_SIZE);
        label15.setText(bundle.getString("GSSRenamingSection_Desc"));
        label15.setWrapText(true);
        VBox.setVgrow(label15, Priority.NEVER);
        vBox18.getChildren().add(label15);
        GridPane gridPane21 = new GridPane();
        gridPane21.setHgap(5.0);
        controlNone = new RadioButton();
        controlNone.setMnemonicParsing(false);
        controlNone.setText(bundle.getString("GSSRenamingSection_None"));
        GridPane.setColumnIndex(controlNone, 0);
        GridPane.setRowIndex(controlNone, 0);
        renameGroup = new ToggleGroup();
        controlNone.setToggleGroup(renameGroup);
        gridPane21.getChildren().add(controlNone);
        controlDebug = new RadioButton();
        controlDebug.setMnemonicParsing(false);
        controlDebug.setText(bundle.getString("GSSRenamingSection_Debug"));
        controlDebug.setToggleGroup(renameGroup);
        GridPane.setColumnIndex(controlDebug, 1);
        GridPane.setRowIndex(controlDebug, 0);
        gridPane21.getChildren().add(controlDebug);
        controlClosure = new RadioButton();
        controlClosure.setMnemonicParsing(false);
        controlClosure.setText(bundle.getString("GSSRenamingSection_Closure"));
        controlClosure.setToggleGroup(renameGroup);
        GridPane.setColumnIndex(controlClosure, 2);
        GridPane.setRowIndex(controlClosure, 0);
        gridPane21.getChildren().add(controlClosure);
        ColumnConstraints columnConstraints41 = new ColumnConstraints();
        columnConstraints41.setHgrow(Priority.NEVER);
        columnConstraints41.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints41.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane21.getColumnConstraints().add(columnConstraints41);
        ColumnConstraints columnConstraints42 = new ColumnConstraints();
        columnConstraints42.setHgrow(Priority.NEVER);
        columnConstraints42.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints42.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane21.getColumnConstraints().add(columnConstraints42);
        ColumnConstraints columnConstraints43 = new ColumnConstraints();
        columnConstraints43.setHgrow(Priority.NEVER);
        columnConstraints43.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints43.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane21.getColumnConstraints().add(columnConstraints43);
        RowConstraints rowConstraints39 = new RowConstraints();
        rowConstraints39.setMinHeight(10.0);
        rowConstraints39.setPrefHeight(30.0);
        rowConstraints39.setVgrow(Priority.SOMETIMES);
        gridPane21.getRowConstraints().add(rowConstraints39);
        vBox18.getChildren().add(gridPane21);
        GridPane gridPane22 = new GridPane();
        gridPane22.setHgap(5.0);
        gridPane22.setVgap(5.0);
        Label label16 = new Label();
        label16.setText(bundle.getString("GSSRenamingSection_RenamePrefix"));
        GridPane.setColumnIndex(label16, 0);
        GridPane.setHalignment(label16, HPos.RIGHT);
        GridPane.setRowIndex(label16, 0);
        gridPane22.getChildren().add(label16);
        Label label17 = new Label();
        label17.setText(bundle.getString("GSSRenamingSection_RenameFormat"));
        GridPane.setColumnIndex(label17, 0);
        GridPane.setHalignment(label17, HPos.RIGHT);
        GridPane.setRowIndex(label17, 1);
        gridPane22.getChildren().add(label17);
        Label label18 = new Label();
        label18.setText(bundle.getString("GSSRenamingSection_RenameFile"));
        GridPane.setColumnIndex(label18, 0);
        GridPane.setHalignment(label18, HPos.RIGHT);
        GridPane.setRowIndex(label18, 2);
        gridPane22.getChildren().add(label18);
        controlCssRenamingPrefix = new TextField();
        controlCssRenamingPrefix.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlCssRenamingPrefix, 1);
        GridPane.setRowIndex(controlCssRenamingPrefix, 0);
        gridPane22.getChildren().add(controlCssRenamingPrefix);
        controlOutputRenamingMapFormat = new ComboBox();
        controlOutputRenamingMapFormat.setId("controlRenamingType");
        controlOutputRenamingMapFormat.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlOutputRenamingMapFormat, 1);
        GridPane.setRowIndex(controlOutputRenamingMapFormat, 1);
        gridPane22.getChildren().add(controlOutputRenamingMapFormat);
        GridPane gridPane23 = new GridPane();
        gridPane23.setHgap(5.0);
        GridPane.setColumnIndex(gridPane23, 1);
        GridPane.setRowIndex(gridPane23, 2);
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
        gridPane23.getChildren().add(btnBrowse);
        controlOutputRenamingMap = new TextField();
        controlOutputRenamingMap.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlOutputRenamingMap, 0);
        GridPane.setRowIndex(controlOutputRenamingMap, 0);
        gridPane23.getChildren().add(controlOutputRenamingMap);
        Button button17 = new Button();
        button17.setMnemonicParsing(false);
        button17.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button17.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button17, 2);
        GridPane.setRowIndex(button17, 0);
        gridPane23.getChildren().add(button17);
        ColumnConstraints columnConstraints44 = new ColumnConstraints();
        columnConstraints44.setHgrow(Priority.ALWAYS);
        columnConstraints44.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints44.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane23.getColumnConstraints().add(columnConstraints44);
        ColumnConstraints columnConstraints45 = new ColumnConstraints();
        columnConstraints45.setHgrow(Priority.NEVER);
        columnConstraints45.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints45.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane23.getColumnConstraints().add(columnConstraints45);
        ColumnConstraints columnConstraints46 = new ColumnConstraints();
        columnConstraints46.setHgrow(Priority.NEVER);
        columnConstraints46.setMinWidth(Control.USE_PREF_SIZE);
        gridPane23.getColumnConstraints().add(columnConstraints46);
        RowConstraints rowConstraints40 = new RowConstraints();
        rowConstraints40.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints40.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints40.setVgrow(Priority.NEVER);
        gridPane23.getRowConstraints().add(rowConstraints40);
        gridPane22.getChildren().add(gridPane23);
        ColumnConstraints columnConstraints47 = new ColumnConstraints();
        columnConstraints47.setHgrow(Priority.NEVER);
        columnConstraints47.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints47.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane22.getColumnConstraints().add(columnConstraints47);
        ColumnConstraints columnConstraints48 = new ColumnConstraints();
        columnConstraints48.setHgrow(Priority.ALWAYS);
        columnConstraints48.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints48.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane22.getColumnConstraints().add(columnConstraints48);
        RowConstraints rowConstraints41 = new RowConstraints();
        rowConstraints41.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints41.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints41.setVgrow(Priority.NEVER);
        gridPane22.getRowConstraints().add(rowConstraints41);
        RowConstraints rowConstraints42 = new RowConstraints();
        rowConstraints42.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints42.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints42.setVgrow(Priority.NEVER);
        gridPane22.getRowConstraints().add(rowConstraints42);
        RowConstraints rowConstraints43 = new RowConstraints();
        rowConstraints43.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints43.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints43.setVgrow(Priority.NEVER);
        gridPane22.getRowConstraints().add(rowConstraints43);
        vBox18.getChildren().add(gridPane22);
        Label label19 = new Label();
        label19.setText(bundle.getString("GSSRenamingSection_Exclude"));
        label19.setWrapText(true);
        vBox18.getChildren().add(label19);
        GridPane gridPane24 = new GridPane();
        gridPane24.setHgap(5.0);
        VBox.setVgrow(gridPane24, Priority.ALWAYS);
        controlGssExcludedClass = new ListView();
        controlGssExcludedClass.setPrefHeight(200.0);
        controlGssExcludedClass.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlGssExcludedClass, 0);
        GridPane.setRowIndex(controlGssExcludedClass, 0);
        gridPane24.getChildren().add(controlGssExcludedClass);
        GridPane gridPane25 = new GridPane();
        gridPane25.setVgap(5.0);
        GridPane.setColumnIndex(gridPane25, 1);
        GridPane.setRowIndex(gridPane25, 0);
        Button button18 = new Button();
        button18.setMaxWidth(1.7976931348623157E308);
        button18.setMnemonicParsing(false);
        button18.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        button18.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button18, 0);
        GridPane.setRowIndex(button18, 0);
        gridPane25.getChildren().add(button18);
        Button button19 = new Button();
        button19.setMaxWidth(1.7976931348623157E308);
        button19.setMnemonicParsing(false);
        button19.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button19.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button19, 0);
        GridPane.setRowIndex(button19, 1);
        gridPane25.getChildren().add(button19);
        ColumnConstraints columnConstraints49 = new ColumnConstraints();
        columnConstraints49.setHgrow(Priority.NEVER);
        columnConstraints49.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints49.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane25.getColumnConstraints().add(columnConstraints49);
        RowConstraints rowConstraints44 = new RowConstraints();
        rowConstraints44.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints44.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints44.setVgrow(Priority.NEVER);
        gridPane25.getRowConstraints().add(rowConstraints44);
        RowConstraints rowConstraints45 = new RowConstraints();
        rowConstraints45.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints45.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints45.setVgrow(Priority.NEVER);
        gridPane25.getRowConstraints().add(rowConstraints45);
        gridPane24.getChildren().add(gridPane25);
        ColumnConstraints columnConstraints50 = new ColumnConstraints();
        columnConstraints50.setHgrow(Priority.ALWAYS);
        columnConstraints50.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints50.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane24.getColumnConstraints().add(columnConstraints50);
        ColumnConstraints columnConstraints51 = new ColumnConstraints();
        columnConstraints51.setHgrow(Priority.NEVER);
        columnConstraints51.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints51.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane24.getColumnConstraints().add(columnConstraints51);
        RowConstraints rowConstraints46 = new RowConstraints();
        rowConstraints46.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints46.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints46.setVgrow(Priority.ALWAYS);
        gridPane24.getRowConstraints().add(rowConstraints46);
        vBox18.getChildren().add(gridPane24);
        Insets insets25 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox18.setPadding(insets25);
        titledPane8.setContent(vBox18);
        anchorPane9.getChildren().add(titledPane8);
        initialize(null, bundle);
        return anchorPane9;
    }

}
