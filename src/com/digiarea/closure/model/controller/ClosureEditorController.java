package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.control.Control;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import com.digiarea.closure.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class ClosureEditorController extends ClosureController implements Initializable {

    public ClosureEditorController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private Tab tabCompiler;

    @FXML
    private Tab tabTemplates;

    @FXML
    private Tab tabStylesheets;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Tab getTabCompiler() {
        return tabCompiler;
    }

    public Tab getTabTemplates() {
        return tabTemplates;
    }

    public Tab getTabStylesheets() {
        return tabStylesheets;
    }

    public TabPane create() throws Exception {
        TabPane tabPane = new TabPane();
        tabPane.setId("TabPane");
        tabPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        tabPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        tabPane.setPrefHeight(Control.USE_COMPUTED_SIZE);
        tabPane.setPrefWidth(Control.USE_COMPUTED_SIZE);
        tabPane.setSide(Side.BOTTOM);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabCompiler = new Tab();
        tabCompiler.setText(bundle.getString("ClosureEditor_Compiler"));
        GridPane gridPane = new GridPane();
        gridPane.setId("GridPane");
        gridPane.setMinHeight(Control.USE_COMPUTED_SIZE);
        gridPane.setMinWidth(Control.USE_COMPUTED_SIZE);
        AnchorPane compiler = ((JSPageController) modelFacade.getFactory().call(JSPageController.class)).create();
        compiler.setId("compiler");
        compiler.setMaxHeight(1.7976931348623157E308);
        compiler.setMinHeight(Control.USE_COMPUTED_SIZE);
        compiler.setMinWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(compiler, 0);
        GridPane.setHalignment(compiler, HPos.CENTER);
        GridPane.setHgrow(compiler, Priority.ALWAYS);
        GridPane.setRowIndex(compiler, 0);
        GridPane.setValignment(compiler, VPos.TOP);
        GridPane.setVgrow(compiler, Priority.ALWAYS);
        gridPane.getChildren().add(compiler);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setHgrow(Priority.SOMETIMES);
        columnConstraints.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints.setPercentWidth(0.0);
        gridPane.getColumnConstraints().add(columnConstraints);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints.setPercentHeight(0.0);
        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().add(rowConstraints);
        tabCompiler.setContent(gridPane);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(16.0);
        imageView.setFitWidth(16.0);
        imageView.setMouseTransparent(true);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        Image image = new Image(getClass().getResource("/com/digiarea/closurefx/resources/closure-green.png").openStream());
        imageView.setImage(image);
        tabCompiler.setGraphic(imageView);
        tabPane.getTabs().add(tabCompiler);
        tabTemplates = new Tab();
        tabTemplates.setText(bundle.getString("ClosureEditor_Templates"));
        GridPane gridPane1 = new GridPane();
        gridPane1.setId("GridPane");
        AnchorPane include = ((SOYPageController) modelFacade.getFactory().call(SOYPageController.class)).create();
        include.setMaxHeight(1.7976931348623157E308);
        include.setMinHeight(Control.USE_COMPUTED_SIZE);
        include.setMinWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(include, 0);
        GridPane.setHalignment(include, HPos.CENTER);
        GridPane.setHgrow(include, Priority.ALWAYS);
        GridPane.setRowIndex(include, 0);
        GridPane.setValignment(include, VPos.TOP);
        GridPane.setVgrow(include, Priority.ALWAYS);
        gridPane1.getChildren().add(include);
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setHalignment(HPos.LEFT);
        columnConstraints1.setHgrow(Priority.SOMETIMES);
        columnConstraints1.setMinWidth(Control.USE_COMPUTED_SIZE);
        gridPane1.getColumnConstraints().add(columnConstraints1);
        RowConstraints rowConstraints1 = new RowConstraints();
        rowConstraints1.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints1.setValignment(VPos.TOP);
        rowConstraints1.setVgrow(Priority.SOMETIMES);
        gridPane1.getRowConstraints().add(rowConstraints1);
        tabTemplates.setContent(gridPane1);
        ImageView imageView1 = new ImageView();
        imageView1.setFitHeight(16.0);
        imageView1.setFitWidth(16.0);
        imageView1.setMouseTransparent(true);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        Image image1 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/closure-blue.png").openStream());
        imageView1.setImage(image1);
        tabTemplates.setGraphic(imageView1);
        tabPane.getTabs().add(tabTemplates);
        tabStylesheets = new Tab();
        tabStylesheets.setText(bundle.getString("ClosureEditor_Stylesheets"));
        GridPane gridPane2 = new GridPane();
        gridPane2.setId("GridPane");
        AnchorPane include1 = ((GSSPageController) modelFacade.getFactory().call(GSSPageController.class)).create();
        include1.setMaxHeight(1.7976931348623157E308);
        include1.setMinHeight(Control.USE_COMPUTED_SIZE);
        include1.setMinWidth(Control.USE_COMPUTED_SIZE);
        GridPane.setColumnIndex(include1, 0);
        GridPane.setHalignment(include1, HPos.CENTER);
        GridPane.setHgrow(include1, Priority.ALWAYS);
        GridPane.setRowIndex(include1, 0);
        GridPane.setValignment(include1, VPos.TOP);
        GridPane.setVgrow(include1, Priority.ALWAYS);
        gridPane2.getChildren().add(include1);
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        columnConstraints2.setHalignment(HPos.LEFT);
        columnConstraints2.setHgrow(Priority.SOMETIMES);
        columnConstraints2.setMinWidth(Control.USE_COMPUTED_SIZE);
        columnConstraints2.setPercentWidth(0.0);
        gridPane2.getColumnConstraints().add(columnConstraints2);
        RowConstraints rowConstraints2 = new RowConstraints();
        rowConstraints2.setMinHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints2.setPercentHeight(0.0);
        rowConstraints2.setValignment(VPos.TOP);
        rowConstraints2.setVgrow(Priority.SOMETIMES);
        gridPane2.getRowConstraints().add(rowConstraints2);
        tabStylesheets.setContent(gridPane2);
        ImageView imageView2 = new ImageView();
        imageView2.setFitHeight(16.0);
        imageView2.setFitWidth(16.0);
        imageView2.setMouseTransparent(true);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        Image image2 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/closure-red.png").openStream());
        imageView2.setImage(image2);
        tabStylesheets.setGraphic(imageView2);
        tabPane.getTabs().add(tabStylesheets);
        initialize(null, bundle);
        return tabPane;
    }

}
