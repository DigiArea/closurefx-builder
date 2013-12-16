package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SOYPageController extends ClosureController implements Initializable {

    public SOYPageController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TabPane tabs;

    @FXML
    private Tab controlConsole;

    @FXML
    private CheckBox controlBuild;

    @FXML
    private SplitMenuButton btnRun;

    @FXML
    private Label labelMessageError;

    @FXML
    private Label labelMessageWarning;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleRunButton(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
        selectionModel.select(controlConsole);
        modelFacade.getSoyConsole().start();
    }

    @FXML
    private void handleRunAllButton(ActionEvent event) {
        SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
        selectionModel.select(controlConsole);
        modelFacade.getJsConsole().start();
        modelFacade.getSoyConsole().start();
        modelFacade.getGssConsole().start();
    }

    public TabPane getTabs() {
        return tabs;
    }

    public CheckBox getBtnActivate() {
        return controlBuild;
    }

    public SplitMenuButton getBtnRun() {
        return btnRun;
    }

    public CheckBox getControlBuild() {
        return controlBuild;
    }

    public Label getLabelMessageError() {
        return labelMessageError;
    }

    public Label getLabelMessageWarning() {
        return labelMessageWarning;
    }

    public Tab getControlConsole() {
        return controlConsole;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane35 = new AnchorPane();
        anchorPane35.setId("AnchorPane");
        anchorPane35.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane35.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane35.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane35.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox vBox60 = new VBox();
        vBox60.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBox60.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox60.setPrefWidth(Control.USE_COMPUTED_SIZE);
        AnchorPane.setBottomAnchor(vBox60, 0.0);
        AnchorPane.setLeftAnchor(vBox60, 0.0);
        AnchorPane.setRightAnchor(vBox60, 0.0);
        AnchorPane.setTopAnchor(vBox60, 0.0);
        GridPane sOYTitleBox = new GridPane();
        sOYTitleBox.setId("SOYTitleBox");
        sOYTitleBox.setDisable(false);
        sOYTitleBox.setHgap(5.0);
        sOYTitleBox.setPrefHeight(40.0);
        sOYTitleBox.setPrefWidth(Control.USE_COMPUTED_SIZE);
        labelMessageError = new Label();
        labelMessageError.setId("labelMessage");
        labelMessageError.setText("");
        labelMessageError.setVisible(false);
        labelMessageError.setWrapText(true);
        GridPane.setColumnIndex(labelMessageError, 1);
        GridPane.setRowIndex(labelMessageError, 0);
        Tooltip tooltip21 = new Tooltip();
        tooltip21.setText("Empty Tooltip");
        labelMessageError.setTooltip(tooltip21);
        sOYTitleBox.getChildren().add(labelMessageError);
        labelMessageWarning = new Label();
        labelMessageWarning.setId("labelMessage");
        labelMessageWarning.setText("");
        labelMessageWarning.setVisible(false);
        labelMessageWarning.setWrapText(true);
        GridPane.setColumnIndex(labelMessageWarning, 2);
        GridPane.setRowIndex(labelMessageWarning, 0);
        Tooltip tooltip22 = new Tooltip();
        tooltip22.setText("Empty Tooltip");
        labelMessageWarning.setTooltip(tooltip22);
        sOYTitleBox.getChildren().add(labelMessageWarning);
        Label sOYTitle = new Label();
        sOYTitle.setId("SOYTitle");
        sOYTitle.setText(bundle.getString("SOYPage"));
        GridPane.setColumnIndex(sOYTitle, 0);
        GridPane.setRowIndex(sOYTitle, 0);
        ImageView imageView38 = new ImageView();
        imageView38.setFitHeight(16.0);
        imageView38.setFitWidth(16.0);
        imageView38.setMouseTransparent(true);
        imageView38.setPickOnBounds(true);
        imageView38.setPreserveRatio(true);
        Image image38 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/closure-white.png").openStream());
        imageView38.setImage(image38);
        sOYTitle.setGraphic(imageView38);
        sOYTitleBox.getChildren().add(sOYTitle);
        controlBuild = new CheckBox();
        controlBuild.setId("btnActivate");
        controlBuild.setAlignment(Pos.CENTER_RIGHT);
        controlBuild.setContentDisplay(ContentDisplay.RIGHT);
        controlBuild.setDisable(false);
        controlBuild.setMnemonicParsing(false);
        controlBuild.getStyleClass().add("activate_checkbox");
        controlBuild.setText(bundle.getString("ClosureActivate_Templates"));
        GridPane.setColumnIndex(controlBuild, 4);
        GridPane.setRowIndex(controlBuild, 0);
        sOYTitleBox.getChildren().add(controlBuild);
        btnRun = new SplitMenuButton();
        btnRun.setContentDisplay(ContentDisplay.LEFT);
        btnRun.setDisable(false);
        btnRun.setMnemonicParsing(false);
        btnRun.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRunButton(event);
            }
        });
        btnRun.getStyleClass().add("activate_split-menu-button");
        btnRun.setText(bundle.getString("ClosureMenu_RunTemplates"));
        GridPane.setColumnIndex(btnRun, 3);
        GridPane.setRowIndex(btnRun, 0);
        ImageView imageView39 = new ImageView();
        imageView39.setFitHeight(16.0);
        imageView39.setFitWidth(16.0);
        imageView39.setMouseTransparent(true);
        imageView39.setPickOnBounds(true);
        imageView39.setPreserveRatio(true);
        Image image39 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch-blue.png").openStream());
        imageView39.setImage(image39);
        btnRun.setGraphic(imageView39);
        MenuItem menuItem2 = new MenuItem();
        menuItem2.setMnemonicParsing(false);
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRunAllButton(event);
            }
        });
        menuItem2.setText(bundle.getString("ClosureMenu_RunConf"));
        ImageView imageView40 = new ImageView();
        imageView40.setFitHeight(16.0);
        imageView40.setFitWidth(16.0);
        imageView40.setPickOnBounds(true);
        imageView40.setPreserveRatio(true);
        Image image40 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView40.setImage(image40);
        menuItem2.setGraphic(imageView40);
        btnRun.getItems().add(menuItem2);
        sOYTitleBox.getChildren().add(btnRun);
        ColumnConstraints columnConstraints158 = new ColumnConstraints();
        columnConstraints158.setHgrow(Priority.NEVER);
        columnConstraints158.setMinWidth(Control.USE_PREF_SIZE);
        sOYTitleBox.getColumnConstraints().add(columnConstraints158);
        ColumnConstraints columnConstraints159 = new ColumnConstraints();
        columnConstraints159.setHgrow(Priority.NEVER);
        columnConstraints159.setMinWidth(Control.USE_PREF_SIZE);
        sOYTitleBox.getColumnConstraints().add(columnConstraints159);
        ColumnConstraints columnConstraints160 = new ColumnConstraints();
        columnConstraints160.setHgrow(Priority.ALWAYS);
        columnConstraints160.setMinWidth(Control.USE_PREF_SIZE);
        sOYTitleBox.getColumnConstraints().add(columnConstraints160);
        ColumnConstraints columnConstraints161 = new ColumnConstraints();
        columnConstraints161.setHgrow(Priority.NEVER);
        columnConstraints161.setMinWidth(Control.USE_PREF_SIZE);
        sOYTitleBox.getColumnConstraints().add(columnConstraints161);
        ColumnConstraints columnConstraints162 = new ColumnConstraints();
        columnConstraints162.setHgrow(Priority.NEVER);
        columnConstraints162.setMinWidth(Control.USE_PREF_SIZE);
        sOYTitleBox.getColumnConstraints().add(columnConstraints162);
        Insets insets93 = new Insets(0.0, 10.0, 0.0, 10.0);
        sOYTitleBox.setPadding(insets93);
        RowConstraints rowConstraints141 = new RowConstraints();
        rowConstraints141.setMinHeight(40.0);
        rowConstraints141.setVgrow(Priority.SOMETIMES);
        sOYTitleBox.getRowConstraints().add(rowConstraints141);
        vBox60.getChildren().add(sOYTitleBox);
        tabs = new TabPane();
        tabs.setDisable(false);
        tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabs, Priority.ALWAYS);
        Tab tab8 = new Tab();
        tab8.setClosable(false);
        tab8.setText(bundle.getString("CompilerPage_Basic"));
        ScrollPane scrollPane10 = new ScrollPane();
        scrollPane10.setId("ScrollPane");
        scrollPane10.setFitToHeight(true);
        scrollPane10.setFitToWidth(true);
        scrollPane10.setPrefViewportHeight(400.0);
        scrollPane10.setPrefViewportWidth(600.0);
        GridPane gridPane71 = new GridPane();
        gridPane71.setId("GridPane");
        gridPane71.setAlignment(Pos.TOP_CENTER);
        gridPane71.setDisable(false);
        gridPane71.setFocusTraversable(false);
        gridPane71.setGridLinesVisible(false);
        gridPane71.setHgap(5.0);
        gridPane71.setMinHeight(Control.USE_PREF_SIZE);
        gridPane71.setMinWidth(Control.USE_PREF_SIZE);
        gridPane71.setVisible(true);
        VBox vBox61 = new VBox();
        vBox61.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox61.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox61.setSpacing(6.0);
        GridPane.setColumnIndex(vBox61, 0);
        GridPane.setHgrow(vBox61, Priority.ALWAYS);
        GridPane.setRowIndex(vBox61, 0);
        GridPane.setValignment(vBox61, VPos.TOP);
        GridPane.setVgrow(vBox61, Priority.ALWAYS);
        AnchorPane info = ((SOYInfoSectionController) modelFacade.getFactory().call(SOYInfoSectionController.class)).create();
        VBox.setVgrow(info, Priority.NEVER);
        vBox61.getChildren().add(info);
        AnchorPane localization = ((SOYLocalizationSectionController) modelFacade.getFactory().call(SOYLocalizationSectionController.class)).create();
        VBox.setVgrow(localization, Priority.ALWAYS);
        vBox61.getChildren().add(localization);
        Insets insets94 = new Insets(10.0, 0.0, 10.0, 10.0);
        vBox61.setPadding(insets94);
        gridPane71.getChildren().add(vBox61);
        VBox vBox62 = new VBox();
        vBox62.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox62.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox62.setSpacing(6.0);
        GridPane.setColumnIndex(vBox62, 1);
        GridPane.setHgrow(vBox62, Priority.ALWAYS);
        GridPane.setRowIndex(vBox62, 0);
        GridPane.setValignment(vBox62, VPos.TOP);
        GridPane.setVgrow(vBox62, Priority.ALWAYS);
        AnchorPane codeStyle = ((SOYStyleSectionController) modelFacade.getFactory().call(SOYStyleSectionController.class)).create();
        VBox.setVgrow(codeStyle, Priority.NEVER);
        vBox62.getChildren().add(codeStyle);
        AnchorPane options = ((SOYOptionsSectionController) modelFacade.getFactory().call(SOYOptionsSectionController.class)).create();
        VBox.setVgrow(options, Priority.SOMETIMES);
        vBox62.getChildren().add(options);
        Insets insets95 = new Insets(10.0, 10.0, 10.0, 0.0);
        vBox62.setPadding(insets95);
        gridPane71.getChildren().add(vBox62);
        ColumnConstraints columnConstraints163 = new ColumnConstraints();
        columnConstraints163.setHgrow(Priority.ALWAYS);
        columnConstraints163.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints163.setPercentWidth(50.0);
        gridPane71.getColumnConstraints().add(columnConstraints163);
        ColumnConstraints columnConstraints164 = new ColumnConstraints();
        columnConstraints164.setHgrow(Priority.ALWAYS);
        columnConstraints164.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints164.setPercentWidth(50.0);
        gridPane71.getColumnConstraints().add(columnConstraints164);
        RowConstraints rowConstraints142 = new RowConstraints();
        rowConstraints142.setMinHeight(10.0);
        rowConstraints142.setValignment(VPos.TOP);
        rowConstraints142.setVgrow(Priority.SOMETIMES);
        gridPane71.getRowConstraints().add(rowConstraints142);
        scrollPane10.setContent(gridPane71);
        tab8.setContent(scrollPane10);
        tabs.getTabs().add(tab8);
        Tab tab9 = new Tab();
        tab9.setClosable(false);
        tab9.setText(bundle.getString("CompilerPage_BuildPath"));
        ScrollPane scrollPane11 = new ScrollPane();
        scrollPane11.setId("ScrollPane");
        scrollPane11.setFitToHeight(true);
        scrollPane11.setFitToWidth(true);
        scrollPane11.setPrefViewportHeight(400.0);
        scrollPane11.setPrefViewportWidth(600.0);
        GridPane gridPane72 = new GridPane();
        gridPane72.setId("GridPane");
        gridPane72.setHgap(5.0);
        gridPane72.setMinHeight(Control.USE_PREF_SIZE);
        gridPane72.setMinWidth(Control.USE_PREF_SIZE);
        VBox basicTabRight = new VBox();
        basicTabRight.setId("basicTabRight");
        basicTabRight.setPrefHeight(Control.USE_COMPUTED_SIZE);
        basicTabRight.setPrefWidth(Control.USE_COMPUTED_SIZE);
        basicTabRight.setSpacing(6.0);
        GridPane.setColumnIndex(basicTabRight, 0);
        GridPane.setHgrow(basicTabRight, Priority.ALWAYS);
        GridPane.setRowIndex(basicTabRight, 0);
        GridPane.setValignment(basicTabRight, VPos.TOP);
        GridPane.setVgrow(basicTabRight, Priority.ALWAYS);
        AnchorPane source = ((SOYSourceSectionController) modelFacade.getFactory().call(SOYSourceSectionController.class)).create();
        VBox.setVgrow(source, Priority.ALWAYS);
        basicTabRight.getChildren().add(source);
        AnchorPane order = ((SOYOrderSectionController) modelFacade.getFactory().call(SOYOrderSectionController.class)).create();
        VBox.setVgrow(order, Priority.ALWAYS);
        basicTabRight.getChildren().add(order);
        Insets insets96 = new Insets(10.0, 0.0, 10.0, 10.0);
        basicTabRight.setPadding(insets96);
        gridPane72.getChildren().add(basicTabRight);
        VBox basicTabLeft = new VBox();
        basicTabLeft.setId("basicTabLeft");
        basicTabLeft.setPrefHeight(Control.USE_COMPUTED_SIZE);
        basicTabLeft.setPrefWidth(Control.USE_COMPUTED_SIZE);
        basicTabLeft.setSpacing(6.0);
        GridPane.setColumnIndex(basicTabLeft, 1);
        GridPane.setHgrow(basicTabLeft, Priority.ALWAYS);
        GridPane.setRowIndex(basicTabLeft, 0);
        GridPane.setValignment(basicTabLeft, VPos.TOP);
        GridPane.setVgrow(basicTabLeft, Priority.ALWAYS);
        AnchorPane include9 = ((SOYLibrariesSectionController) modelFacade.getFactory().call(SOYLibrariesSectionController.class)).create();
        VBox.setVgrow(include9, Priority.ALWAYS);
        basicTabLeft.getChildren().add(include9);
        AnchorPane output = ((SOYOutputSectionController) modelFacade.getFactory().call(SOYOutputSectionController.class)).create();
        VBox.setVgrow(output, Priority.NEVER);
        basicTabLeft.getChildren().add(output);
        Insets insets97 = new Insets(10.0, 10.0, 10.0, 0.0);
        basicTabLeft.setPadding(insets97);
        gridPane72.getChildren().add(basicTabLeft);
        ColumnConstraints columnConstraints165 = new ColumnConstraints();
        columnConstraints165.setHgrow(Priority.ALWAYS);
        columnConstraints165.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints165.setPercentWidth(50.0);
        gridPane72.getColumnConstraints().add(columnConstraints165);
        ColumnConstraints columnConstraints166 = new ColumnConstraints();
        columnConstraints166.setHgrow(Priority.ALWAYS);
        columnConstraints166.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints166.setPercentWidth(50.0);
        gridPane72.getColumnConstraints().add(columnConstraints166);
        RowConstraints rowConstraints143 = new RowConstraints();
        rowConstraints143.setMinHeight(10.0);
        rowConstraints143.setVgrow(Priority.SOMETIMES);
        gridPane72.getRowConstraints().add(rowConstraints143);
        scrollPane11.setContent(gridPane72);
        tab9.setContent(scrollPane11);
        tabs.getTabs().add(tab9);
        controlConsole = new Tab();
        controlConsole.setText(bundle.getString("CompilerPage_Console"));
        ScrollPane scrollPane12 = new ScrollPane();
        scrollPane12.setId("ScrollPane");
        scrollPane12.setFitToHeight(true);
        scrollPane12.setFitToWidth(true);
        scrollPane12.setPrefViewportHeight(400.0);
        scrollPane12.setPrefViewportWidth(600.0);
        VBox vBox63 = new VBox();
        vBox63.setId("VBox");
        vBox63.setAlignment(Pos.CENTER);
        vBox63.setMinHeight(Control.USE_PREF_SIZE);
        vBox63.setMinWidth(Control.USE_PREF_SIZE);
        vBox63.setSpacing(5.0);
        HBox include10 = ((SOYConsoleSectionController) modelFacade.getFactory().call(SOYConsoleSectionController.class)).create();
        VBox.setVgrow(include10, Priority.ALWAYS);
        vBox63.getChildren().add(include10);
        Insets insets98 = new Insets(0.0, 0.0, 0.0, 0.0);
        vBox63.setPadding(insets98);
        scrollPane12.setContent(vBox63);
        controlConsole.setContent(scrollPane12);
        tabs.getTabs().add(controlConsole);
        vBox60.getChildren().add(tabs);
        anchorPane35.getChildren().add(vBox60);
        initialize(null, bundle);
        return anchorPane35;
    }

}
