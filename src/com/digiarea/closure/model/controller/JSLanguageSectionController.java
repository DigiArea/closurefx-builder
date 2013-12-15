package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import javafx.scene.control.CheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.digiarea.closure.model.LangType;
import javafx.scene.control.ComboBox;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import javafx.event.ActionEvent;
import com.digiarea.closure.model.controller.dialogs.CharsetDialogController;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closure.model.providers.LabelProviders;
import javafx.scene.control.Control;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.geometry.HPos;
import javafx.event.EventHandler;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSLanguageSectionController extends ClosureController implements Initializable {

    public JSLanguageSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private CheckBox controlAngularPass;

    @FXML
    private CheckBox controlJqueryPass;

    @FXML
    private CheckBox controlClosurePass;

    @FXML
    private CheckBox controlClosureStyle;

    @FXML
    private CheckBox controlAcceptConstKeyword;

    @FXML
    private TextField controlCharset;

    @FXML
    private ComboBox<LangType> controlInputLanguage;

    @FXML
    private ComboBox<LangType> controlOutputLanguage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlInputLanguage.setItems(FXCollections.observableArrayList(LangType.values()));
        controlInputLanguage.setButtonCell(new JSLanguageSectionController.LanguageCellFactory());
        controlInputLanguage.setCellFactory(new Callback<ListView<LangType>, ListCell<LangType>>() {

            @Override
            public ListCell<LangType> call(ListView<LangType> p) {
                return new JSLanguageSectionController.LanguageCellFactory();
            }
        });
        controlOutputLanguage.setItems(FXCollections.observableArrayList(LangType.values()));
        controlOutputLanguage.setButtonCell(new JSLanguageSectionController.LanguageCellFactory());
        controlOutputLanguage.setCellFactory(new Callback<ListView<LangType>, ListCell<LangType>>() {

            @Override
            public ListCell<LangType> call(ListView<LangType> p) {
                return new JSLanguageSectionController.LanguageCellFactory();
            }
        });
    }

    @FXML
    private void handleCharsetButtonAction(ActionEvent event) {
        CharsetDialogController controller = (CharsetDialogController) DialogFactory.getCharsetDialog("Select Placeholder", ResourceUtils.CLOSURE_ICON, "Select placeholder:", "Placeholder description:");
        if (controller.getCharset() != null) {
            modelFacade.setJSCharset(controller.getCharset());
        }
    }

    public CheckBox getControlAngularPass() {
        return controlAngularPass;
    }

    public CheckBox getControlJqueryPass() {
        return controlJqueryPass;
    }

    public CheckBox getControlClosurePass() {
        return controlClosurePass;
    }

    public CheckBox getControlClosureStyle() {
        return controlClosureStyle;
    }

    public CheckBox getControlAcceptConstKeyword() {
        return controlAcceptConstKeyword;
    }

    public TextField getControlCharset() {
        return controlCharset;
    }

    private class LanguageCellFactory extends ListCell<LangType> {

        @Override
        protected void updateItem(LangType item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (item != null) {
                    setText(LabelProviders.getLanguageLabel(item));
                }
            }
        }

    }

    public ComboBox<LangType> getControlInputLanguage() {
        return controlInputLanguage;
    }

    public ComboBox<LangType> getControlOutputLanguage() {
        return controlOutputLanguage;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane19 = new AnchorPane();
        anchorPane19.setId("AnchorPane");
        anchorPane19.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane19.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane19.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane19.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane17 = new TitledPane();
        titledPane17.setAnimated(false);
        titledPane17.setCollapsible(false);
        titledPane17.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane17.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane17.setText(bundle.getString("JSLanguageSection"));
        AnchorPane.setBottomAnchor(titledPane17, 0.0);
        AnchorPane.setLeftAnchor(titledPane17, 0.0);
        AnchorPane.setRightAnchor(titledPane17, 0.0);
        AnchorPane.setTopAnchor(titledPane17, 0.0);
        VBox vBox30 = new VBox();
        vBox30.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox30.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox30.setSpacing(5.0);
        Label label35 = new Label();
        label35.setText(bundle.getString("JSLanguageSection_Desc"));
        label35.setWrapText(true);
        vBox30.getChildren().add(label35);
        GridPane gridPane40 = new GridPane();
        gridPane40.setAlignment(Pos.TOP_LEFT);
        gridPane40.setHgap(5.0);
        gridPane40.setVgap(5.0);
        Label label36 = new Label();
        label36.setContentDisplay(ContentDisplay.TOP);
        label36.setText(bundle.getString("JSLanguageSection_Input"));
        GridPane.setColumnIndex(label36, 0);
        GridPane.setHalignment(label36, HPos.RIGHT);
        GridPane.setRowIndex(label36, 0);
        gridPane40.getChildren().add(label36);
        Label label37 = new Label();
        label37.setText(bundle.getString("JSLanguageSection_Output"));
        GridPane.setColumnIndex(label37, 0);
        GridPane.setHalignment(label37, HPos.RIGHT);
        GridPane.setRowIndex(label37, 1);
        gridPane40.getChildren().add(label37);
        GridPane gridPane41 = new GridPane();
        gridPane41.setHgap(5.0);
        GridPane.setColumnIndex(gridPane41, 1);
        GridPane.setRowIndex(gridPane41, 2);
        controlCharset = new TextField();
        controlCharset.setPrefWidth(200.0);
        GridPane.setColumnIndex(controlCharset, 0);
        GridPane.setRowIndex(controlCharset, 0);
        gridPane41.getChildren().add(controlCharset);
        Button button37 = new Button();
        button37.setMnemonicParsing(false);
        button37.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleCharsetButtonAction(event);
            }
        });
        button37.setText(bundle.getString("Button_Browse"));
        GridPane.setColumnIndex(button37, 1);
        GridPane.setRowIndex(button37, 0);
        gridPane41.getChildren().add(button37);
        ColumnConstraints columnConstraints82 = new ColumnConstraints();
        columnConstraints82.setHgrow(Priority.ALWAYS);
        columnConstraints82.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints82.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane41.getColumnConstraints().add(columnConstraints82);
        ColumnConstraints columnConstraints83 = new ColumnConstraints();
        columnConstraints83.setHgrow(Priority.NEVER);
        columnConstraints83.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints83.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane41.getColumnConstraints().add(columnConstraints83);
        RowConstraints rowConstraints74 = new RowConstraints();
        rowConstraints74.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints74.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints74.setVgrow(Priority.NEVER);
        gridPane41.getRowConstraints().add(rowConstraints74);
        gridPane40.getChildren().add(gridPane41);
        Label label38 = new Label();
        label38.setAlignment(Pos.CENTER_LEFT);
        label38.setText(bundle.getString("JSLanguageSection_Charset"));
        GridPane.setColumnIndex(label38, 0);
        GridPane.setHalignment(label38, HPos.RIGHT);
        GridPane.setRowIndex(label38, 2);
        gridPane40.getChildren().add(label38);
        controlInputLanguage = new ComboBox();
        controlInputLanguage.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlInputLanguage, 1);
        GridPane.setRowIndex(controlInputLanguage, 0);
        gridPane40.getChildren().add(controlInputLanguage);
        controlOutputLanguage = new ComboBox();
        controlOutputLanguage.setMaxWidth(1.7976931348623157E308);
        GridPane.setColumnIndex(controlOutputLanguage, 1);
        GridPane.setRowIndex(controlOutputLanguage, 1);
        gridPane40.getChildren().add(controlOutputLanguage);
        ColumnConstraints columnConstraints84 = new ColumnConstraints();
        columnConstraints84.setHgrow(Priority.NEVER);
        columnConstraints84.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints84.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane40.getColumnConstraints().add(columnConstraints84);
        ColumnConstraints columnConstraints85 = new ColumnConstraints();
        columnConstraints85.setHgrow(Priority.ALWAYS);
        columnConstraints85.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints85.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane40.getColumnConstraints().add(columnConstraints85);
        RowConstraints rowConstraints75 = new RowConstraints();
        rowConstraints75.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints75.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints75.setVgrow(Priority.SOMETIMES);
        gridPane40.getRowConstraints().add(rowConstraints75);
        RowConstraints rowConstraints76 = new RowConstraints();
        rowConstraints76.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints76.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints76.setVgrow(Priority.SOMETIMES);
        gridPane40.getRowConstraints().add(rowConstraints76);
        RowConstraints rowConstraints77 = new RowConstraints();
        rowConstraints77.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints77.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints77.setVgrow(Priority.SOMETIMES);
        gridPane40.getRowConstraints().add(rowConstraints77);
        vBox30.getChildren().add(gridPane40);
        controlAcceptConstKeyword = new CheckBox();
        controlAcceptConstKeyword.setMnemonicParsing(false);
        controlAcceptConstKeyword.setText(bundle.getString("JSLanguageSection_Const"));
        vBox30.getChildren().add(controlAcceptConstKeyword);
        controlClosureStyle = new CheckBox();
        controlClosureStyle.setMnemonicParsing(false);
        controlClosureStyle.setText(bundle.getString("JSLanguageSection_ClosureStyle"));
        vBox30.getChildren().add(controlClosureStyle);
        controlClosurePass = new CheckBox();
        controlClosurePass.setMnemonicParsing(false);
        controlClosurePass.setText(bundle.getString("JSLanguageSection_ClosurePrimitives"));
        vBox30.getChildren().add(controlClosurePass);
        controlJqueryPass = new CheckBox();
        controlJqueryPass.setMnemonicParsing(false);
        controlJqueryPass.setText(bundle.getString("JSLanguageSection_JQueryPrimitives"));
        vBox30.getChildren().add(controlJqueryPass);
        controlAngularPass = new CheckBox();
        controlAngularPass.setMnemonicParsing(false);
        controlAngularPass.setText(bundle.getString("JSLanguageSection_AngularPrimitives"));
        vBox30.getChildren().add(controlAngularPass);
        Insets insets44 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox30.setPadding(insets44);
        titledPane17.setContent(vBox30);
        anchorPane19.getChildren().add(titledPane17);
        initialize(null, bundle);
        return anchorPane19;
    }

}
