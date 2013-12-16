package com.digiarea.closure.model.controller;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import javafx.util.Callback;

import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.controller.dialogs.FolderDialogController;
import com.digiarea.closure.model.controller.dialogs.LocaleDialogController;
import com.digiarea.closure.model.controller.dialogs.PlaceholderDialogController;
import com.digiarea.closure.model.entity.MessagePlaceholder;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYLocalizationSectionController extends ClosureController implements Initializable {

    public SOYLocalizationSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ListView<SoyLocale> controlSoyLocale;

    @FXML
    private TextField controlMessagesPath;

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
        controlSoyLocale.setCellFactory(new SOYLocalizationSectionController.LocaleCellFactory());
    }

    @FXML
    private void handlePlaceholderButtonAction(ActionEvent event) {
        PlaceholderDialogController controller = (PlaceholderDialogController) DialogFactory.getPlaceholderDialog(Arrays.asList(MessagePlaceholder.values()), "Select Placeholder", ResourceUtils.CLOSURE_ICON, "Select placeholder:", "Placeholder description:");
        if (controller.getPlaceholder() != null) {
            modelFacade.setSOYAddMessagePlaceholder(controller.getPlaceholder().getValue(), controlMessagesPath.getCaretPosition());
        }
    }

    @FXML
    private void handleBrowseButtonAction(ActionEvent event) {
        FolderDialogController controller = DialogFactory.getFolderDialog(bundle, IConstants.SOYLocalizationSection_BrowseTitle, IConstants.SOYLocalizationSection_BrowseTitle, modelFacade.getDocument().getFile().getParentFile(), true, true, (String[]) null);
        if (controller != null && controller.getStatus().getSeverity() != StatusType.CANCEL) {
            if (controller.getSelectedFile() != null) {
                modelFacade.setSOYMessagePath(controller.getSelectedFile().getAbsolutePath(), false);
            }
        }
    }

    @FXML
    private void handleBrowseExternalButtonAction(ActionEvent event) {
        File file = UIUtils.chooseFolder(null, bundle.getString(IConstants.SOYLocalizationSection_BrowseTitle));
        if (file != null) {
            modelFacade.setSOYMessagePath(file.getAbsolutePath(), true);
        }
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        LocaleDialogController controller = (LocaleDialogController) DialogFactory.getLocaleDialog("Select Locale", ResourceUtils.CLOSURE_ICON, "Select locale:", "Locale description:");
        if (controller.getLocale() != null) {
            SoyLocale locale = new SoyLocale();
            locale.setValue(controller.getLocale().toString());
            modelFacade.addSOYLocale(locale);
        }
    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        ObservableList<SoyLocale> locales = controlSoyLocale.getSelectionModel().getSelectedItems();
        if (locales != null && !locales.isEmpty()) {
            for (SoyLocale locale : locales) {
                modelFacade.removeSOYLocale(locale);
            }
        }
    }

    public ListView<SoyLocale> getControlSoyLocale() {
        return controlSoyLocale;
    }

    public TextField getControlMessagesPath() {
        return controlMessagesPath;
    }

    private class LocaleCellFactory implements Callback<ListView<SoyLocale>, ListCell<SoyLocale>> {

        @Override
        public ListCell<SoyLocale> call(ListView<SoyLocale> list) {
            return new com.digiarea.closure.model.controller.SOYLocalizationSectionController.LocaleListCell();
        }

    }

    private class LocaleListCell extends ListCell<SoyLocale> {

        @Override
        public void updateItem(SoyLocale item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setText(item.getValue());
            }
        }

    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane31 = new AnchorPane();
        anchorPane31.setId("AnchorPane");
        anchorPane31.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane31.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane31.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane31.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane28 = new TitledPane();
        titledPane28.setAnimated(false);
        titledPane28.setCollapsible(false);
        titledPane28.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane28.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane28.setText(bundle.getString("SOYLocalizationSection"));
        AnchorPane.setBottomAnchor(titledPane28, 0.0);
        AnchorPane.setLeftAnchor(titledPane28, 0.0);
        AnchorPane.setRightAnchor(titledPane28, 0.0);
        AnchorPane.setTopAnchor(titledPane28, 0.0);
        VBox vBox56 = new VBox();
        vBox56.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox56.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox56.setSpacing(5.0);
        Label label71 = new Label();
        label71.setMinHeight(Control.USE_PREF_SIZE);
        label71.setText(bundle.getString("SOYLocalizationSection_Desc"));
        label71.setWrapText(true);
        vBox56.getChildren().add(label71);
        GridPane gridPane63 = new GridPane();
        gridPane63.setHgap(5.0);
        gridPane63.setMinWidth(Control.USE_PREF_SIZE);
        Label label72 = new Label();
        label72.setText(bundle.getString("SOYLocalizationSection_MsgPath"));
        GridPane.setColumnIndex(label72, 0);
        GridPane.setRowIndex(label72, 0);
        gridPane63.getChildren().add(label72);
        controlMessagesPath = new TextField();
        controlMessagesPath.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlMessagesPath, 1);
        GridPane.setRowIndex(controlMessagesPath, 0);
        gridPane63.getChildren().add(controlMessagesPath);
        GridPane gridPane64 = new GridPane();
        gridPane64.setAlignment(Pos.CENTER_RIGHT);
        gridPane64.setHgap(5.0);
        GridPane.setColumnIndex(gridPane64, 1);
        GridPane.setRowIndex(gridPane64, 1);
        Button button61 = new Button();
        button61.setMnemonicParsing(false);
        button61.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handlePlaceholderButtonAction(event);
            }
        });
        button61.setText(bundle.getString("Button_Placeholders"));
        GridPane.setColumnIndex(button61, 0);
        GridPane.setRowIndex(button61, 0);
        gridPane64.getChildren().add(button61);
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
        gridPane64.getChildren().add(btnBrowse);
        Button button62 = new Button();
        button62.setMnemonicParsing(false);
        button62.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleBrowseExternalButtonAction(event);
            }
        });
        button62.setText(bundle.getString("OutputSection_Browse_External"));
        GridPane.setColumnIndex(button62, 2);
        GridPane.setRowIndex(button62, 0);
        gridPane64.getChildren().add(button62);
        ColumnConstraints columnConstraints140 = new ColumnConstraints();
        columnConstraints140.setHgrow(Priority.NEVER);
        columnConstraints140.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints140.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane64.getColumnConstraints().add(columnConstraints140);
        ColumnConstraints columnConstraints141 = new ColumnConstraints();
        columnConstraints141.setHalignment(HPos.LEFT);
        columnConstraints141.setHgrow(Priority.NEVER);
        columnConstraints141.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints141.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane64.getColumnConstraints().add(columnConstraints141);
        ColumnConstraints columnConstraints142 = new ColumnConstraints();
        columnConstraints142.setHalignment(HPos.LEFT);
        columnConstraints142.setHgrow(Priority.NEVER);
        columnConstraints142.setMinWidth(Control.USE_PREF_SIZE);
        gridPane64.getColumnConstraints().add(columnConstraints142);
        RowConstraints rowConstraints128 = new RowConstraints();
        rowConstraints128.setMinHeight(10.0);
        rowConstraints128.setPrefHeight(30.0);
        rowConstraints128.setVgrow(Priority.SOMETIMES);
        gridPane64.getRowConstraints().add(rowConstraints128);
        gridPane63.getChildren().add(gridPane64);
        ColumnConstraints columnConstraints143 = new ColumnConstraints();
        columnConstraints143.setHgrow(Priority.NEVER);
        columnConstraints143.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints143.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane63.getColumnConstraints().add(columnConstraints143);
        ColumnConstraints columnConstraints144 = new ColumnConstraints();
        columnConstraints144.setHgrow(Priority.ALWAYS);
        columnConstraints144.setMinWidth(10.0);
        columnConstraints144.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane63.getColumnConstraints().add(columnConstraints144);
        RowConstraints rowConstraints129 = new RowConstraints();
        rowConstraints129.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints129.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints129.setVgrow(Priority.NEVER);
        gridPane63.getRowConstraints().add(rowConstraints129);
        RowConstraints rowConstraints130 = new RowConstraints();
        rowConstraints130.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints130.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints130.setVgrow(Priority.NEVER);
        gridPane63.getRowConstraints().add(rowConstraints130);
        vBox56.getChildren().add(gridPane63);
        GridPane gridPane65 = new GridPane();
        gridPane65.setHgap(5.0);
        gridPane65.setMinWidth(Control.USE_PREF_SIZE);
        VBox.setVgrow(gridPane65, Priority.ALWAYS);
        Label label73 = new Label();
        label73.setText(bundle.getString("SOYLocalizationSection_Locales"));
        label73.setWrapText(true);
        GridPane.setColumnIndex(label73, 0);
        GridPane.setRowIndex(label73, 0);
        gridPane65.getChildren().add(label73);
        controlSoyLocale = new ListView();
        controlSoyLocale.setPrefHeight(50.0);
        controlSoyLocale.setPrefWidth(50.0);
        GridPane.setColumnIndex(controlSoyLocale, 0);
        GridPane.setHgrow(controlSoyLocale, Priority.ALWAYS);
        GridPane.setRowIndex(controlSoyLocale, 0);
        GridPane.setVgrow(controlSoyLocale, Priority.ALWAYS);
        gridPane65.getChildren().add(controlSoyLocale);
        GridPane gridPane66 = new GridPane();
        gridPane66.setVgap(5.0);
        GridPane.setColumnIndex(gridPane66, 1);
        GridPane.setRowIndex(gridPane66, 0);
        Button button63 = new Button();
        button63.setMaxWidth(1.7976931348623157E308);
        button63.setMnemonicParsing(false);
        button63.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleAddButtonAction(event);
            }
        });
        button63.setText(bundle.getString("Button_Add"));
        GridPane.setColumnIndex(button63, 0);
        GridPane.setRowIndex(button63, 0);
        gridPane66.getChildren().add(button63);
        Button button64 = new Button();
        button64.setMaxWidth(1.7976931348623157E308);
        button64.setMnemonicParsing(false);
        button64.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRemoveButtonAction(event);
            }
        });
        button64.setText(bundle.getString("Button_Remove"));
        GridPane.setColumnIndex(button64, 0);
        GridPane.setRowIndex(button64, 1);
        gridPane66.getChildren().add(button64);
        ColumnConstraints columnConstraints145 = new ColumnConstraints();
        columnConstraints145.setHgrow(Priority.NEVER);
        columnConstraints145.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints145.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane66.getColumnConstraints().add(columnConstraints145);
        RowConstraints rowConstraints131 = new RowConstraints();
        rowConstraints131.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints131.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints131.setVgrow(Priority.NEVER);
        gridPane66.getRowConstraints().add(rowConstraints131);
        RowConstraints rowConstraints132 = new RowConstraints();
        rowConstraints132.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints132.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints132.setVgrow(Priority.NEVER);
        gridPane66.getRowConstraints().add(rowConstraints132);
        Insets insets83 = new Insets(0.0, 0.0, 0.0, 0.0);
        GridPane.setMargin(gridPane66, insets83);
        gridPane65.getChildren().add(gridPane66);
        ColumnConstraints columnConstraints146 = new ColumnConstraints();
        columnConstraints146.setHgrow(Priority.SOMETIMES);
        columnConstraints146.setMinWidth(10.0);
        columnConstraints146.setPrefWidth(100.0);
        gridPane65.getColumnConstraints().add(columnConstraints146);
        ColumnConstraints columnConstraints147 = new ColumnConstraints();
        columnConstraints147.setHgrow(Priority.NEVER);
        columnConstraints147.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints147.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane65.getColumnConstraints().add(columnConstraints147);
        RowConstraints rowConstraints133 = new RowConstraints();
        rowConstraints133.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints133.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints133.setVgrow(Priority.ALWAYS);
        gridPane65.getRowConstraints().add(rowConstraints133);
        vBox56.getChildren().add(gridPane65);
        Insets insets84 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox56.setPadding(insets84);
        titledPane28.setContent(vBox56);
        anchorPane31.getChildren().add(titledPane28);
        initialize(null, bundle);
        return anchorPane31;
    }

}
