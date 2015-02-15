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
public class GSSPageController extends ClosureController implements Initializable {

    public GSSPageController(ModelFacade modelFacade, ResourceBundle bundle) {
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
        modelFacade.getGssConsole().start();
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
        AnchorPane anchorPane8 = new AnchorPane();
        anchorPane8.setId("AnchorPane");
        anchorPane8.setMinHeight(Control.USE_PREF_SIZE);
        anchorPane8.setMinWidth(Control.USE_PREF_SIZE);
        anchorPane8.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane8.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox vBox10 = new VBox();
        vBox10.setMinWidth(Control.USE_COMPUTED_SIZE);
        vBox10.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox10.setPrefWidth(Control.USE_COMPUTED_SIZE);
        AnchorPane.setBottomAnchor(vBox10, 0.0);
        AnchorPane.setLeftAnchor(vBox10, 0.0);
        AnchorPane.setRightAnchor(vBox10, 0.0);
        AnchorPane.setTopAnchor(vBox10, 0.0);
        GridPane gSSTitleBox = new GridPane();
        gSSTitleBox.setId("GSSTitleBox");
        gSSTitleBox.setDisable(false);
        gSSTitleBox.setHgap(5.0);
        gSSTitleBox.setPrefHeight(40.0);
        gSSTitleBox.setPrefWidth(Control.USE_COMPUTED_SIZE);
        labelMessageWarning = new Label();
        labelMessageWarning.setId("labelMessage");
        labelMessageWarning.setText("");
        labelMessageWarning.setVisible(false);
        labelMessageWarning.setWrapText(true);
        GridPane.setColumnIndex(labelMessageWarning, 2);
        GridPane.setRowIndex(labelMessageWarning, 0);
        Tooltip tooltip2 = new Tooltip();
        tooltip2.setText("Empty Tooltip");
        labelMessageWarning.setTooltip(tooltip2);
        gSSTitleBox.getChildren().add(labelMessageWarning);
        labelMessageError = new Label();
        labelMessageError.setId("labelMessage");
        labelMessageError.setText("");
        labelMessageError.setVisible(false);
        labelMessageError.setWrapText(true);
        GridPane.setColumnIndex(labelMessageError, 1);
        GridPane.setRowIndex(labelMessageError, 0);
        Tooltip tooltip3 = new Tooltip();
        tooltip3.setText("Empty Tooltip");
        labelMessageError.setTooltip(tooltip3);
        gSSTitleBox.getChildren().add(labelMessageError);
        Label gSSTitle = new Label();
        gSSTitle.setId("GSSTitle");
        gSSTitle.setText(bundle.getString("GSSPage"));
        GridPane.setColumnIndex(gSSTitle, 0);
        GridPane.setRowIndex(gSSTitle, 0);
        ImageView imageView7 = new ImageView();
        imageView7.setFitHeight(16.0);
        imageView7.setFitWidth(16.0);
        imageView7.setMouseTransparent(true);
        imageView7.setPickOnBounds(true);
        imageView7.setPreserveRatio(true);
        Image image7 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/closure-white.png").openStream());
        imageView7.setImage(image7);
        gSSTitle.setGraphic(imageView7);
        gSSTitleBox.getChildren().add(gSSTitle);
        controlBuild = new CheckBox();
        controlBuild.setId("btnActivate");
        controlBuild.setAlignment(Pos.CENTER_RIGHT);
        controlBuild.setContentDisplay(ContentDisplay.RIGHT);
        controlBuild.setDisable(false);
        controlBuild.setMnemonicParsing(false);
        controlBuild.getStyleClass().add("activate_checkbox");
        controlBuild.setText(bundle.getString("ClosureActivate_Stylesheets"));
        GridPane.setColumnIndex(controlBuild, 4);
        GridPane.setRowIndex(controlBuild, 0);
        gSSTitleBox.getChildren().add(controlBuild);
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
        btnRun.setText(bundle.getString("ClosureMenu_RunStylesheets"));
        GridPane.setColumnIndex(btnRun, 3);
        GridPane.setRowIndex(btnRun, 0);
        ImageView imageView8 = new ImageView();
        imageView8.setFitHeight(16.0);
        imageView8.setFitWidth(16.0);
        imageView8.setMouseTransparent(true);
        imageView8.setPickOnBounds(true);
        imageView8.setPreserveRatio(true);
        Image image8 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch-red.png").openStream());
        imageView8.setImage(image8);
        btnRun.setGraphic(imageView8);
        MenuItem menuItem = new MenuItem();
        menuItem.setMnemonicParsing(false);
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleRunAllButton(event);
            }
        });
        menuItem.setText(bundle.getString("ClosureMenu_RunConf"));
        ImageView imageView9 = new ImageView();
        imageView9.setFitHeight(16.0);
        imageView9.setFitWidth(16.0);
        imageView9.setPickOnBounds(true);
        imageView9.setPreserveRatio(true);
        Image image9 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/launch.png").openStream());
        imageView9.setImage(image9);
        menuItem.setGraphic(imageView9);
        btnRun.getItems().add(menuItem);
        gSSTitleBox.getChildren().add(btnRun);
        ColumnConstraints columnConstraints30 = new ColumnConstraints();
        columnConstraints30.setHgrow(Priority.NEVER);
        columnConstraints30.setMinWidth(Control.USE_PREF_SIZE);
        gSSTitleBox.getColumnConstraints().add(columnConstraints30);
        ColumnConstraints columnConstraints31 = new ColumnConstraints();
        columnConstraints31.setHgrow(Priority.NEVER);
        columnConstraints31.setMinWidth(Control.USE_PREF_SIZE);
        gSSTitleBox.getColumnConstraints().add(columnConstraints31);
        ColumnConstraints columnConstraints32 = new ColumnConstraints();
        columnConstraints32.setHgrow(Priority.ALWAYS);
        columnConstraints32.setMinWidth(Control.USE_PREF_SIZE);
        gSSTitleBox.getColumnConstraints().add(columnConstraints32);
        ColumnConstraints columnConstraints33 = new ColumnConstraints();
        columnConstraints33.setHgrow(Priority.NEVER);
        columnConstraints33.setMinWidth(Control.USE_PREF_SIZE);
        gSSTitleBox.getColumnConstraints().add(columnConstraints33);
        ColumnConstraints columnConstraints34 = new ColumnConstraints();
        columnConstraints34.setHgrow(Priority.NEVER);
        columnConstraints34.setMinWidth(Control.USE_PREF_SIZE);
        gSSTitleBox.getColumnConstraints().add(columnConstraints34);
        Insets insets18 = new Insets(0.0, 10.0, 0.0, 10.0);
        gSSTitleBox.setPadding(insets18);
        RowConstraints rowConstraints35 = new RowConstraints();
        rowConstraints35.setMinHeight(40.0);
        rowConstraints35.setVgrow(Priority.SOMETIMES);
        gSSTitleBox.getRowConstraints().add(rowConstraints35);
        vBox10.getChildren().add(gSSTitleBox);
        tabs = new TabPane();
        tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabs, Priority.ALWAYS);
        Tab tab = new Tab();
        tab.setClosable(false);
        tab.setText(bundle.getString("CompilerPage_Basic"));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("ScrollPane");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportHeight(400.0);
        scrollPane.setPrefViewportWidth(600.0);
        GridPane gridPane18 = new GridPane();
        gridPane18.setId("GridPane");
        gridPane18.setHgap(6.0);
        gridPane18.setMinHeight(Control.USE_PREF_SIZE);
        gridPane18.setMinWidth(Control.USE_PREF_SIZE);
        VBox vBox11 = new VBox();
        vBox11.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox11.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox11.setSpacing(6.0);
        GridPane.setColumnIndex(vBox11, 0);
        GridPane.setRowIndex(vBox11, 0);
        GridPane.setValignment(vBox11, VPos.TOP);
        AnchorPane info = ((GSSInfoSectionController) modelFacade.getFactory().call(GSSInfoSectionController.class)).create();
        VBox.setVgrow(info, Priority.NEVER);
        vBox11.getChildren().add(info);
        AnchorPane copyright = ((GSSCopySectionController) modelFacade.getFactory().call(GSSCopySectionController.class)).create();
        VBox.setVgrow(copyright, Priority.ALWAYS);
        vBox11.getChildren().add(copyright);
        Insets insets19 = new Insets(10.0, 0.0, 10.0, 10.0);
        vBox11.setPadding(insets19);
        gridPane18.getChildren().add(vBox11);
        VBox vBox12 = new VBox();
        vBox12.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox12.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox12.setSpacing(6.0);
        GridPane.setColumnIndex(vBox12, 1);
        GridPane.setRowIndex(vBox12, 0);
        GridPane.setValignment(vBox12, VPos.TOP);
        AnchorPane vendor = ((GSSVendorSectionController) modelFacade.getFactory().call(GSSVendorSectionController.class)).create();
        vendor.setMinHeight(Control.USE_PREF_SIZE);
        vendor.setMinWidth(Control.USE_PREF_SIZE);
        VBox.setVgrow(vendor, Priority.NEVER);
        vBox12.getChildren().add(vendor);
        AnchorPane formatting = ((GSSFormattingSectionController) modelFacade.getFactory().call(GSSFormattingSectionController.class)).create();
        VBox.setVgrow(formatting, Priority.NEVER);
        vBox12.getChildren().add(formatting);
        AnchorPane defines = ((GSSDefinesSectionController) modelFacade.getFactory().call(GSSDefinesSectionController.class)).create();
        VBox.setVgrow(defines, Priority.ALWAYS);
        vBox12.getChildren().add(defines);
        Insets insets20 = new Insets(10.0, 10.0, 10.0, 0.0);
        vBox12.setPadding(insets20);
        gridPane18.getChildren().add(vBox12);
        ColumnConstraints columnConstraints35 = new ColumnConstraints();
        columnConstraints35.setHgrow(Priority.ALWAYS);
        columnConstraints35.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints35.setPercentWidth(50.0);
        gridPane18.getColumnConstraints().add(columnConstraints35);
        ColumnConstraints columnConstraints36 = new ColumnConstraints();
        columnConstraints36.setHgrow(Priority.ALWAYS);
        columnConstraints36.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints36.setPercentWidth(50.0);
        gridPane18.getColumnConstraints().add(columnConstraints36);
        RowConstraints rowConstraints36 = new RowConstraints();
        rowConstraints36.setMinHeight(10.0);
        rowConstraints36.setVgrow(Priority.SOMETIMES);
        gridPane18.getRowConstraints().add(rowConstraints36);
        scrollPane.setContent(gridPane18);
        tab.setContent(scrollPane);
        tabs.getTabs().add(tab);
        Tab tab1 = new Tab();
        tab1.setClosable(false);
        tab1.setText(bundle.getString("CompilerPage_BuildPath"));
        ScrollPane scrollPane1 = new ScrollPane();
        scrollPane1.setId("ScrollPane");
        scrollPane1.setFitToHeight(true);
        scrollPane1.setFitToWidth(true);
        scrollPane1.setPrefViewportHeight(400.0);
        scrollPane1.setPrefViewportWidth(600.0);
        GridPane gridPane19 = new GridPane();
        gridPane19.setHgap(6.0);
        gridPane19.setMinHeight(Control.USE_PREF_SIZE);
        gridPane19.setMinWidth(Control.USE_PREF_SIZE);
        VBox vBox13 = new VBox();
        vBox13.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox13.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox13.setSpacing(6.0);
        GridPane.setColumnIndex(vBox13, 0);
        GridPane.setHgrow(vBox13, Priority.ALWAYS);
        GridPane.setRowIndex(vBox13, 0);
        GridPane.setValignment(vBox13, VPos.TOP);
        GridPane.setVgrow(vBox13, Priority.ALWAYS);
        AnchorPane source = ((GSSSourceSectionController) modelFacade.getFactory().call(GSSSourceSectionController.class)).create();
        VBox.setVgrow(source, Priority.ALWAYS);
        vBox13.getChildren().add(source);
        AnchorPane order = ((GSSOrderSectionController) modelFacade.getFactory().call(GSSOrderSectionController.class)).create();
        VBox.setVgrow(order, Priority.ALWAYS);
        vBox13.getChildren().add(order);
        gridPane19.getChildren().add(vBox13);
        VBox vBox14 = new VBox();
        vBox14.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox14.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox14.setSpacing(6.0);
        GridPane.setColumnIndex(vBox14, 1);
        GridPane.setHgrow(vBox14, Priority.ALWAYS);
        GridPane.setRowIndex(vBox14, 0);
        GridPane.setValignment(vBox14, VPos.TOP);
        GridPane.setVgrow(vBox14, Priority.ALWAYS);
        AnchorPane include2 = ((GSSLibrariesSectionController) modelFacade.getFactory().call(GSSLibrariesSectionController.class)).create();
        VBox.setVgrow(include2, Priority.ALWAYS);
        vBox14.getChildren().add(include2);
        AnchorPane output = ((GSSOutputSectionController) modelFacade.getFactory().call(GSSOutputSectionController.class)).create();
        VBox.setVgrow(output, Priority.NEVER);
        vBox14.getChildren().add(output);
        gridPane19.getChildren().add(vBox14);
        ColumnConstraints columnConstraints37 = new ColumnConstraints();
        columnConstraints37.setHgrow(Priority.ALWAYS);
        columnConstraints37.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints37.setPercentWidth(50.0);
        columnConstraints37.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane19.getColumnConstraints().add(columnConstraints37);
        ColumnConstraints columnConstraints38 = new ColumnConstraints();
        columnConstraints38.setHgrow(Priority.ALWAYS);
        columnConstraints38.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints38.setPercentWidth(50.0);
        columnConstraints38.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane19.getColumnConstraints().add(columnConstraints38);
        Insets insets21 = new Insets(10.0, 10.0, 10.0, 10.0);
        gridPane19.setPadding(insets21);
        RowConstraints rowConstraints37 = new RowConstraints();
        rowConstraints37.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints37.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints37.setVgrow(Priority.ALWAYS);
        gridPane19.getRowConstraints().add(rowConstraints37);
        scrollPane1.setContent(gridPane19);
        tab1.setContent(scrollPane1);
        tabs.getTabs().add(tab1);
        Tab tab2 = new Tab();
        tab2.setText(bundle.getString("CompilerPage_Advanced"));
        ScrollPane scrollPane2 = new ScrollPane();
        scrollPane2.setId("ScrollPane");
        scrollPane2.setFitToHeight(true);
        scrollPane2.setFitToWidth(true);
        scrollPane2.setPrefViewportHeight(400.0);
        scrollPane2.setPrefViewportWidth(600.0);
        GridPane gridPane20 = new GridPane();
        gridPane20.setId("GridPane");
        gridPane20.setMinHeight(Control.USE_PREF_SIZE);
        gridPane20.setMinWidth(Control.USE_PREF_SIZE);
        VBox vBox15 = new VBox();
        vBox15.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox15.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox15.setSpacing(5.0);
        GridPane.setColumnIndex(vBox15, 0);
        GridPane.setRowIndex(vBox15, 0);
        AnchorPane include3 = ((GSSRenamingSectionController) modelFacade.getFactory().call(GSSRenamingSectionController.class)).create();
        VBox.setVgrow(include3, Priority.ALWAYS);
        vBox15.getChildren().add(include3);
        Insets insets22 = new Insets(10.0, 5.0, 10.0, 10.0);
        vBox15.setPadding(insets22);
        gridPane20.getChildren().add(vBox15);
        VBox vBox16 = new VBox();
        vBox16.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox16.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox16.setSpacing(5.0);
        GridPane.setColumnIndex(vBox16, 1);
        GridPane.setRowIndex(vBox16, 0);
        AnchorPane linting = ((GSSLintingSectionController) modelFacade.getFactory().call(GSSLintingSectionController.class)).create();
        VBox.setVgrow(linting, Priority.ALWAYS);
        vBox16.getChildren().add(linting);
        Insets insets23 = new Insets(10.0, 10.0, 10.0, 5.0);
        vBox16.setPadding(insets23);
        gridPane20.getChildren().add(vBox16);
        ColumnConstraints columnConstraints39 = new ColumnConstraints();
        columnConstraints39.setHgrow(Priority.ALWAYS);
        columnConstraints39.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints39.setPercentWidth(50.0);
        gridPane20.getColumnConstraints().add(columnConstraints39);
        ColumnConstraints columnConstraints40 = new ColumnConstraints();
        columnConstraints40.setHgrow(Priority.ALWAYS);
        columnConstraints40.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints40.setPercentWidth(50.0);
        gridPane20.getColumnConstraints().add(columnConstraints40);
        RowConstraints rowConstraints38 = new RowConstraints();
        rowConstraints38.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints38.setVgrow(Priority.ALWAYS);
        gridPane20.getRowConstraints().add(rowConstraints38);
        scrollPane2.setContent(gridPane20);
        tab2.setContent(scrollPane2);
        tabs.getTabs().add(tab2);
        controlConsole = new Tab();
        controlConsole.setText(bundle.getString("CompilerPage_Console"));
        ScrollPane scrollPane3 = new ScrollPane();
        scrollPane3.setId("ScrollPane");
        scrollPane3.setFitToHeight(true);
        scrollPane3.setFitToWidth(true);
        scrollPane3.setPrefViewportHeight(400.0);
        scrollPane3.setPrefViewportWidth(600.0);
        VBox vBox17 = new VBox();
        vBox17.setId("VBox");
        vBox17.setAlignment(Pos.CENTER);
        vBox17.setMinHeight(Control.USE_PREF_SIZE);
        vBox17.setMinWidth(Control.USE_PREF_SIZE);
        vBox17.setSpacing(5.0);
        HBox include4 = ((GSSConsoleSectionController) modelFacade.getFactory().call(GSSConsoleSectionController.class)).create();
        VBox.setVgrow(include4, Priority.ALWAYS);
        vBox17.getChildren().add(include4);
        Insets insets24 = new Insets(0.0, 0.0, 0.0, 0.0);
        vBox17.setPadding(insets24);
        scrollPane3.setContent(vBox17);
        controlConsole.setContent(scrollPane3);
        tabs.getTabs().add(controlConsole);
        vBox10.getChildren().add(tabs);
        anchorPane8.getChildren().add(vBox10);
        initialize(null, bundle);
        return anchorPane8;
    }

}
