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
import javafx.scene.control.ToggleButton;
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
 * Compiler Tab Controller class.
 * 
 * @author daginno
 */
public class JSPageController extends ClosureController implements
		Initializable {

	public JSPageController(ModelFacade modelFacade, ResourceBundle bundle) {
		super(modelFacade, bundle);
	}

	@FXML
	private TabPane tabs;

	@FXML
	private Tab controlConsole;

	@FXML
	private CheckBox controlBuild;

	@FXML
	private ToggleButton controlDevMode;

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
		modelFacade.getJsConsole().start();
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

	public SplitMenuButton getBtnRun() {
		return btnRun;
	}

	public CheckBox getControlBuild() {
		return controlBuild;
	}

	public ToggleButton getControlDevMode() {
		return controlDevMode;
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
		AnchorPane anchorPane23 = new AnchorPane();
		anchorPane23.setId("AnchorPane");
		anchorPane23.setPrefHeight(Control.USE_COMPUTED_SIZE);
		anchorPane23.setPrefWidth(Control.USE_COMPUTED_SIZE);
		VBox vBox34 = new VBox();
		vBox34.setDisable(false);
		vBox34.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox34.setPrefWidth(Control.USE_COMPUTED_SIZE);
		AnchorPane.setBottomAnchor(vBox34, 0.0);
		AnchorPane.setLeftAnchor(vBox34, 0.0);
		AnchorPane.setRightAnchor(vBox34, 0.0);
		AnchorPane.setTopAnchor(vBox34, 0.0);
		GridPane compilerTitleBox = new GridPane();
		compilerTitleBox.setId("CompilerTitleBox");
		compilerTitleBox.setDisable(false);
		compilerTitleBox.setHgap(5.0);
		compilerTitleBox.setPrefHeight(40.0);
		VBox.setVgrow(compilerTitleBox, Priority.NEVER);
		Label compilerTitle = new Label();
		compilerTitle.setId("CompilerTitle");
		compilerTitle.setAlignment(Pos.CENTER_LEFT);
		compilerTitle.setContentDisplay(ContentDisplay.LEFT);
		compilerTitle.setGraphicTextGap(4.0);
		compilerTitle.setText(bundle.getString("CompilerPage"));
		compilerTitle.setUnderline(false);
		compilerTitle.setWrapText(false);
		GridPane.setColumnIndex(compilerTitle, 0);
		GridPane.setRowIndex(compilerTitle, 0);
		ImageView imageView27 = new ImageView();
		imageView27.setFitHeight(16.0);
		imageView27.setFitWidth(16.0);
		imageView27.setMouseTransparent(true);
		imageView27.setPickOnBounds(true);
		imageView27.setPreserveRatio(true);
		imageView27.setSmooth(true);
		Image image27 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/closure-white.png")
				.openStream());
		imageView27.setImage(image27);
		compilerTitle.setGraphic(imageView27);
		compilerTitleBox.getChildren().add(compilerTitle);
		labelMessageWarning = new Label();
		labelMessageWarning.setId("labelMessage");
		labelMessageWarning.setText("");
		labelMessageWarning.setVisible(false);
		labelMessageWarning.setWrapText(true);
		GridPane.setColumnIndex(labelMessageWarning, 2);
		GridPane.setHgrow(labelMessageWarning, Priority.NEVER);
		GridPane.setRowIndex(labelMessageWarning, 0);
		GridPane.setVgrow(labelMessageWarning, Priority.NEVER);
		Tooltip tooltip13 = new Tooltip();
		tooltip13.setText("Empty Tooltip");
		labelMessageWarning.setTooltip(tooltip13);
		compilerTitleBox.getChildren().add(labelMessageWarning);
		labelMessageError = new Label();
		labelMessageError.setId("labelMessage");
		labelMessageError.setText("");
		labelMessageError.setVisible(false);
		labelMessageError.setWrapText(true);
		GridPane.setColumnIndex(labelMessageError, 1);
		GridPane.setHgrow(labelMessageError, Priority.NEVER);
		GridPane.setRowIndex(labelMessageError, 0);
		GridPane.setVgrow(labelMessageError, Priority.NEVER);
		Tooltip tooltip14 = new Tooltip();
		tooltip14.setText("Empty Tooltip");
		labelMessageError.setTooltip(tooltip14);
		compilerTitleBox.getChildren().add(labelMessageError);
		ImageView imageView30 = new ImageView();
		imageView30.setFitHeight(16.0);
		imageView30.setFitWidth(16.0);
		imageView30.setMouseTransparent(true);
		imageView30.setPickOnBounds(true);
		imageView30.setPreserveRatio(true);
		imageView30.setSmooth(true);
		Image image30 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/devmode.png").openStream());
		imageView30.setImage(image30);
		controlDevMode = new ToggleButton();
		controlDevMode.setId("devMode");
		controlDevMode
				.setTooltip(new Tooltip(bundle.getString("JSCPage_DevMode")));
		controlDevMode.setGraphic(imageView30);
		controlDevMode.setAlignment(Pos.CENTER_RIGHT);
		// controlDevMode.setContentDisplay(ContentDisplay.RIGHT);
		controlDevMode.setDisable(false);
		controlDevMode.setMnemonicParsing(false);
		controlDevMode.getStyleClass().add("activate_devmode");
		// controlDevMode.setText(bundle.getString("ClosureActivate_Compiler"));
		GridPane.setColumnIndex(controlDevMode, 3);
		GridPane.setRowIndex(controlDevMode, 0);
		compilerTitleBox.getChildren().add(controlDevMode);
		controlBuild = new CheckBox();
		controlBuild.setId("btnActivate");
		controlBuild.setAlignment(Pos.CENTER_RIGHT);
		controlBuild.setContentDisplay(ContentDisplay.RIGHT);
		controlBuild.setDisable(false);
		controlBuild.setMnemonicParsing(false);
		controlBuild.getStyleClass().add("activate_checkbox");
		controlBuild.setText(bundle.getString("ClosureActivate_Compiler"));
		GridPane.setColumnIndex(controlBuild, 5);
		GridPane.setRowIndex(controlBuild, 0);
		compilerTitleBox.getChildren().add(controlBuild);
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
		btnRun.setText(bundle.getString("ClosureMenu_RunCompiler"));
		GridPane.setColumnIndex(btnRun, 4);
		GridPane.setRowIndex(btnRun, 0);
		ImageView imageView28 = new ImageView();
		imageView28.setFitHeight(16.0);
		imageView28.setFitWidth(16.0);
		imageView28.setMouseTransparent(true);
		imageView28.setPickOnBounds(true);
		imageView28.setPreserveRatio(true);
		Image image28 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/launch-green.png")
				.openStream());
		imageView28.setImage(image28);
		btnRun.setGraphic(imageView28);
		MenuItem menuItem1 = new MenuItem();
		menuItem1.setMnemonicParsing(false);
		menuItem1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				handleRunAllButton(event);
			}
		});
		menuItem1.setText(bundle.getString("ClosureMenu_RunConf"));
		ImageView imageView29 = new ImageView();
		imageView29.setFitHeight(16.0);
		imageView29.setFitWidth(16.0);
		imageView29.setPickOnBounds(true);
		imageView29.setPreserveRatio(true);
		Image image29 = new Image(getClass().getResource(
				"/com/digiarea/closurefx/resources/launch.png").openStream());
		imageView29.setImage(image29);
		menuItem1.setGraphic(imageView29);
		btnRun.getItems().add(menuItem1);
		compilerTitleBox.getChildren().add(btnRun);
		ColumnConstraints columnConstraints93 = new ColumnConstraints();
		columnConstraints93.setHgrow(Priority.NEVER);
		columnConstraints93.setMinWidth(Control.USE_PREF_SIZE);
		compilerTitleBox.getColumnConstraints().add(columnConstraints93);
		ColumnConstraints columnConstraints94 = new ColumnConstraints();
		columnConstraints94.setHgrow(Priority.NEVER);
		columnConstraints94.setMinWidth(Control.USE_PREF_SIZE);
		compilerTitleBox.getColumnConstraints().add(columnConstraints94);
		ColumnConstraints columnConstraints95 = new ColumnConstraints();
		columnConstraints95.setHgrow(Priority.ALWAYS);
		columnConstraints95.setMinWidth(Control.USE_PREF_SIZE);
		compilerTitleBox.getColumnConstraints().add(columnConstraints95);
		ColumnConstraints columnConstraints96 = new ColumnConstraints();
		columnConstraints96.setHgrow(Priority.NEVER);
		columnConstraints96.setMinWidth(Control.USE_PREF_SIZE);
		compilerTitleBox.getColumnConstraints().add(columnConstraints96);
		ColumnConstraints columnConstraints97 = new ColumnConstraints();
		columnConstraints97.setHgrow(Priority.NEVER);
		columnConstraints97.setMinWidth(Control.USE_PREF_SIZE);
		compilerTitleBox.getColumnConstraints().add(columnConstraints97);
		Insets insets54 = new Insets(0.0, 10.0, 0.0, 10.0);
		compilerTitleBox.setPadding(insets54);
		RowConstraints rowConstraints92 = new RowConstraints();
		rowConstraints92.setMinHeight(40.0);
		rowConstraints92.setVgrow(Priority.ALWAYS);
		compilerTitleBox.getRowConstraints().add(rowConstraints92);
		vBox34.getChildren().add(compilerTitleBox);
		tabs = new TabPane();
		tabs.setDisable(false);
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		VBox.setVgrow(tabs, Priority.ALWAYS);
		Tab tab3 = new Tab();
		tab3.setClosable(false);
		tab3.setText(bundle.getString("CompilerPage_Basic"));
		ScrollPane scrollPane4 = new ScrollPane();
		scrollPane4.setId("ScrollPane");
		scrollPane4.setFitToHeight(true);
		scrollPane4.setFitToWidth(true);
		scrollPane4.setPrefViewportHeight(400.0);
		scrollPane4.setPrefViewportWidth(600.0);
		GridPane gridPane46 = new GridPane();
		gridPane46.setId("GridPane");
		gridPane46.setHgap(6.0);
		gridPane46.setMinHeight(Control.USE_PREF_SIZE);
		gridPane46.setMinWidth(Control.USE_PREF_SIZE);
		VBox vBox35 = new VBox();
		vBox35.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox35.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox35.setSpacing(6.0);
		GridPane.setColumnIndex(vBox35, 0);
		GridPane.setHgrow(vBox35, Priority.ALWAYS);
		GridPane.setRowIndex(vBox35, 0);
		GridPane.setValignment(vBox35, VPos.TOP);
		GridPane.setVgrow(vBox35, Priority.ALWAYS);
		AnchorPane info = ((JSInfoSectionController) modelFacade.getFactory()
				.call(JSInfoSectionController.class)).create();
		VBox.setVgrow(info, Priority.NEVER);
		vBox35.getChildren().add(info);
		AnchorPane include5 = ((JSHelperSectionController) modelFacade
				.getFactory().call(JSHelperSectionController.class)).create();
		VBox.setVgrow(include5, Priority.NEVER);
		vBox35.getChildren().add(include5);
		Insets insets55 = new Insets(10.0, 0.0, 10.0, 10.0);
		vBox35.setPadding(insets55);
		gridPane46.getChildren().add(vBox35);
		VBox vBox36 = new VBox();
		vBox36.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox36.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox36.setSpacing(6.0);
		GridPane.setColumnIndex(vBox36, 1);
		GridPane.setHgrow(vBox36, Priority.ALWAYS);
		GridPane.setRowIndex(vBox36, 0);
		GridPane.setValignment(vBox36, VPos.TOP);
		GridPane.setVgrow(vBox36, Priority.ALWAYS);
		AnchorPane exports = ((JSExportsSectionController) modelFacade
				.getFactory().call(JSExportsSectionController.class)).create();
		VBox.setVgrow(exports, Priority.NEVER);
		vBox36.getChildren().add(exports);
		AnchorPane formatting = ((JSFormattingSectionController) modelFacade
				.getFactory().call(JSFormattingSectionController.class))
				.create();
		vBox36.getChildren().add(formatting);
		AnchorPane include6 = ((JSLanguageSectionController) modelFacade
				.getFactory().call(JSLanguageSectionController.class)).create();
		VBox.setVgrow(include6, Priority.ALWAYS);
		vBox36.getChildren().add(include6);
		Insets insets56 = new Insets(10.0, 10.0, 10.0, 0.0);
		vBox36.setPadding(insets56);
		gridPane46.getChildren().add(vBox36);
		ColumnConstraints columnConstraints98 = new ColumnConstraints();
		columnConstraints98.setHgrow(Priority.ALWAYS);
		columnConstraints98.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints98.setPercentWidth(50.0);
		gridPane46.getColumnConstraints().add(columnConstraints98);
		ColumnConstraints columnConstraints99 = new ColumnConstraints();
		columnConstraints99.setHgrow(Priority.ALWAYS);
		columnConstraints99.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints99.setPercentWidth(50.0);
		gridPane46.getColumnConstraints().add(columnConstraints99);
		RowConstraints rowConstraints93 = new RowConstraints();
		rowConstraints93.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints93.setVgrow(Priority.ALWAYS);
		gridPane46.getRowConstraints().add(rowConstraints93);
		scrollPane4.setContent(gridPane46);
		tab3.setContent(scrollPane4);
		tabs.getTabs().add(tab3);
		Tab tab4 = new Tab();
		tab4.setClosable(false);
		tab4.setText(bundle.getString("CompilerPage_BuildPath"));
		ScrollPane scrollPane5 = new ScrollPane();
		scrollPane5.setId("ScrollPane");
		scrollPane5.setFitToHeight(true);
		scrollPane5.setFitToWidth(true);
		scrollPane5.setPrefViewportHeight(400.0);
		scrollPane5.setPrefViewportWidth(600.0);
		GridPane gridPane47 = new GridPane();
		gridPane47.setHgap(6.0);
		gridPane47.setMinHeight(Control.USE_PREF_SIZE);
		VBox vBox37 = new VBox();
		vBox37.setPrefHeight(200.0);
		vBox37.setPrefWidth(100.0);
		vBox37.setSpacing(6.0);
		GridPane.setColumnIndex(vBox37, 0);
		GridPane.setHgrow(vBox37, Priority.ALWAYS);
		GridPane.setRowIndex(vBox37, 0);
		GridPane.setValignment(vBox37, VPos.TOP);
		GridPane.setVgrow(vBox37, Priority.ALWAYS);
		AnchorPane source = ((JSSourceSectionController) modelFacade
				.getFactory().call(JSSourceSectionController.class)).create();
		VBox.setVgrow(source, Priority.ALWAYS);
		vBox37.getChildren().add(source);
		AnchorPane order = ((JSOrderSectionController) modelFacade.getFactory()
				.call(JSOrderSectionController.class)).create();
		VBox.setVgrow(order, Priority.ALWAYS);
		vBox37.getChildren().add(order);
		gridPane47.getChildren().add(vBox37);
		VBox vBox38 = new VBox();
		vBox38.setPrefHeight(200.0);
		vBox38.setPrefWidth(100.0);
		vBox38.setSpacing(6.0);
		GridPane.setColumnIndex(vBox38, 1);
		GridPane.setHgrow(vBox38, Priority.ALWAYS);
		GridPane.setRowIndex(vBox38, 0);
		GridPane.setValignment(vBox38, VPos.TOP);
		GridPane.setVgrow(vBox38, Priority.ALWAYS);
		AnchorPane include7 = ((JSLibrariesSectionController) modelFacade
				.getFactory().call(JSLibrariesSectionController.class))
				.create();
		VBox.setVgrow(include7, Priority.ALWAYS);
		vBox38.getChildren().add(include7);
		AnchorPane output = ((JSOutputSectionController) modelFacade
				.getFactory().call(JSOutputSectionController.class)).create();
		VBox.setVgrow(output, Priority.NEVER);
		vBox38.getChildren().add(output);
		gridPane47.getChildren().add(vBox38);
		ColumnConstraints columnConstraints100 = new ColumnConstraints();
		columnConstraints100.setHgrow(Priority.ALWAYS);
		columnConstraints100.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints100.setPercentWidth(50.0);
		columnConstraints100.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane47.getColumnConstraints().add(columnConstraints100);
		ColumnConstraints columnConstraints101 = new ColumnConstraints();
		columnConstraints101.setHgrow(Priority.ALWAYS);
		columnConstraints101.setMinWidth(Control.USE_PREF_SIZE);
		columnConstraints101.setPercentWidth(50.0);
		columnConstraints101.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane47.getColumnConstraints().add(columnConstraints101);
		Insets insets57 = new Insets(10.0, 10.0, 10.0, 10.0);
		gridPane47.setPadding(insets57);
		RowConstraints rowConstraints94 = new RowConstraints();
		rowConstraints94.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints94.setPrefHeight(Control.USE_COMPUTED_SIZE);
		rowConstraints94.setVgrow(Priority.ALWAYS);
		gridPane47.getRowConstraints().add(rowConstraints94);
		scrollPane5.setContent(gridPane47);
		tab4.setContent(scrollPane5);
		tabs.getTabs().add(tab4);
		Tab tab5 = new Tab();
		tab5.setText(bundle.getString("CompilerPage_Checks"));
		ScrollPane scrollPane6 = new ScrollPane();
		scrollPane6.setFitToHeight(true);
		scrollPane6.setFitToWidth(true);
		scrollPane6.setMaxHeight(Control.USE_COMPUTED_SIZE);
		scrollPane6.setMaxWidth(Control.USE_COMPUTED_SIZE);
		scrollPane6.setPrefHeight(Control.USE_COMPUTED_SIZE);
		scrollPane6.setPrefViewportHeight(400.0);
		scrollPane6.setPrefViewportWidth(600.0);
		scrollPane6.setPrefWidth(Control.USE_COMPUTED_SIZE);
		GridPane gridPane48 = new GridPane();
		gridPane48.setId("GridPane");
		gridPane48.setGridLinesVisible(false);
		gridPane48.setHgap(5.0);
		gridPane48.setMinHeight(Control.USE_PREF_SIZE);
		gridPane48.setMinWidth(Control.USE_PREF_SIZE);
		gridPane48.setPrefWidth(Control.USE_COMPUTED_SIZE);
		gridPane48.setVgap(0.0);
		AnchorPane warnings = ((JSWarningsSectionController) modelFacade
				.getFactory().call(JSWarningsSectionController.class)).create();
		warnings.setMaxHeight(Control.USE_COMPUTED_SIZE);
		warnings.setMinHeight(Control.USE_COMPUTED_SIZE);
		warnings.setMinWidth(Control.USE_COMPUTED_SIZE);
		GridPane.setColumnIndex(warnings, 0);
		GridPane.setHgrow(warnings, Priority.ALWAYS);
		GridPane.setRowIndex(warnings, 0);
		GridPane.setVgrow(warnings, Priority.ALWAYS);
		gridPane48.getChildren().add(warnings);
		AnchorPane checks = ((JSCheckSectionController) modelFacade
				.getFactory().call(JSCheckSectionController.class)).create();
		checks.setMaxHeight(Control.USE_COMPUTED_SIZE);
		checks.setMinHeight(Control.USE_COMPUTED_SIZE);
		checks.setMinWidth(Control.USE_COMPUTED_SIZE);
		GridPane.setColumnIndex(checks, 1);
		GridPane.setHgrow(checks, Priority.ALWAYS);
		GridPane.setRowIndex(checks, 0);
		GridPane.setVgrow(checks, Priority.ALWAYS);
		gridPane48.getChildren().add(checks);
		ColumnConstraints columnConstraints102 = new ColumnConstraints();
		columnConstraints102.setFillWidth(true);
		columnConstraints102.setHgrow(Priority.ALWAYS);
		columnConstraints102.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints102.setPercentWidth(50.0);
		gridPane48.getColumnConstraints().add(columnConstraints102);
		ColumnConstraints columnConstraints103 = new ColumnConstraints();
		columnConstraints103.setHgrow(Priority.ALWAYS);
		columnConstraints103.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints103.setPercentWidth(50.0);
		gridPane48.getColumnConstraints().add(columnConstraints103);
		Insets insets58 = new Insets(10.0, 10.0, 10.0, 10.0);
		gridPane48.setPadding(insets58);
		RowConstraints rowConstraints95 = new RowConstraints();
		rowConstraints95.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints95.setPercentHeight(-1.0);
		rowConstraints95.setValignment(VPos.TOP);
		rowConstraints95.setVgrow(Priority.ALWAYS);
		gridPane48.getRowConstraints().add(rowConstraints95);
		scrollPane6.setContent(gridPane48);
		tab5.setContent(scrollPane6);
		tabs.getTabs().add(tab5);
		Tab tab6 = new Tab();
		tab6.setClosable(false);
		tab6.setText(bundle.getString("CompilerPage_Renaming"));
		ScrollPane scrollPane7 = new ScrollPane();
		scrollPane7.setId("ScrollPane");
		scrollPane7.setFitToHeight(true);
		scrollPane7.setFitToWidth(true);
		scrollPane7.setHmin(0.0);
		scrollPane7.setMinHeight(Control.USE_COMPUTED_SIZE);
		scrollPane7.setMinWidth(Control.USE_COMPUTED_SIZE);
		scrollPane7.setPannable(false);
		scrollPane7.setPrefViewportHeight(400.0);
		scrollPane7.setPrefViewportWidth(600.0);
		scrollPane7.setVmin(0.0);
		VBox vBox39 = new VBox();
		vBox39.setMaxHeight(Control.USE_COMPUTED_SIZE);
		vBox39.setMinHeight(Control.USE_COMPUTED_SIZE);
		vBox39.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox39.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox39.setSpacing(6.0);
		AnchorPane renaming = ((JSRenamingSectionController) modelFacade
				.getFactory().call(JSRenamingSectionController.class)).create();
		renaming.setMaxHeight(1.7976931348623157E308);
		VBox.setVgrow(renaming, Priority.ALWAYS);
		vBox39.getChildren().add(renaming);
		Insets insets59 = new Insets(10.0, 10.0, 10.0, 10.0);
		vBox39.setPadding(insets59);
		scrollPane7.setContent(vBox39);
		tab6.setContent(scrollPane7);
		tabs.getTabs().add(tab6);
		Tab tab7 = new Tab();
		tab7.setText(bundle.getString("CompilerPage_Advanced"));
		ScrollPane scrollPane8 = new ScrollPane();
		scrollPane8.setId("ScrollPane");
		scrollPane8.setFitToHeight(true);
		scrollPane8.setFitToWidth(true);
		scrollPane8.setHmin(0.0);
		scrollPane8.setMinHeight(Control.USE_COMPUTED_SIZE);
		scrollPane8.setMinWidth(Control.USE_COMPUTED_SIZE);
		scrollPane8.setPrefHeight(Control.USE_COMPUTED_SIZE);
		scrollPane8.setPrefViewportHeight(400.0);
		scrollPane8.setPrefViewportWidth(600.0);
		scrollPane8.setPrefWidth(Control.USE_COMPUTED_SIZE);
		scrollPane8.setVmin(0.0);
		GridPane gridPane49 = new GridPane();
		gridPane49.setId("GridPane");
		gridPane49.setGridLinesVisible(false);
		gridPane49.setHgap(6.0);
		gridPane49.setMaxHeight(Control.USE_COMPUTED_SIZE);
		gridPane49.setMinHeight(Control.USE_PREF_SIZE);
		gridPane49.setMinWidth(Control.USE_PREF_SIZE);
		gridPane49.setPrefHeight(Control.USE_COMPUTED_SIZE);
		gridPane49.setVgap(0.0);
		VBox vBox40 = new VBox();
		vBox40.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox40.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox40.setSpacing(5.0);
		GridPane.setColumnIndex(vBox40, 0);
		GridPane.setHgrow(vBox40, Priority.ALWAYS);
		GridPane.setRowIndex(vBox40, 0);
		GridPane.setValignment(vBox40, VPos.TOP);
		GridPane.setVgrow(vBox40, Priority.ALWAYS);
		AnchorPane sourceMap = ((JSSourceMapSectionController) modelFacade
				.getFactory().call(JSSourceMapSectionController.class))
				.create();
		vBox40.getChildren().add(sourceMap);
		AnchorPane defines = ((JSDefinesSectionController) modelFacade
				.getFactory().call(JSDefinesSectionController.class)).create();
		defines.setMinHeight(Control.USE_PREF_SIZE);
		VBox.setVgrow(defines, Priority.ALWAYS);
		vBox40.getChildren().add(defines);
		Insets insets60 = new Insets(10.0, 0.0, 10.0, 10.0);
		vBox40.setPadding(insets60);
		gridPane49.getChildren().add(vBox40);
		VBox vBox41 = new VBox();
		vBox41.setPrefHeight(Control.USE_COMPUTED_SIZE);
		vBox41.setPrefWidth(Control.USE_COMPUTED_SIZE);
		vBox41.setSpacing(5.0);
		GridPane.setColumnIndex(vBox41, 1);
		GridPane.setHgrow(vBox41, Priority.ALWAYS);
		GridPane.setRowIndex(vBox41, 0);
		GridPane.setValignment(vBox41, VPos.TOP);
		GridPane.setVgrow(vBox41, Priority.ALWAYS);
		AnchorPane translation = ((JSTranslationSectionController) modelFacade
				.getFactory().call(JSTranslationSectionController.class))
				.create();
		vBox41.getChildren().add(translation);
		AnchorPane docs = ((JSDocsSectionController) modelFacade.getFactory()
				.call(JSDocsSectionController.class)).create();
		VBox.setVgrow(docs, Priority.ALWAYS);
		vBox41.getChildren().add(docs);
		Insets insets61 = new Insets(10.0, 10.0, 10.0, 0.0);
		vBox41.setPadding(insets61);
		gridPane49.getChildren().add(vBox41);
		ColumnConstraints columnConstraints104 = new ColumnConstraints();
		columnConstraints104.setHgrow(Priority.ALWAYS);
		columnConstraints104.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints104.setPercentWidth(50.0);
		gridPane49.getColumnConstraints().add(columnConstraints104);
		ColumnConstraints columnConstraints105 = new ColumnConstraints();
		columnConstraints105.setHgrow(Priority.ALWAYS);
		columnConstraints105.setMinWidth(Control.USE_COMPUTED_SIZE);
		columnConstraints105.setPercentWidth(50.0);
		gridPane49.getColumnConstraints().add(columnConstraints105);
		RowConstraints rowConstraints96 = new RowConstraints();
		rowConstraints96.setMinHeight(Control.USE_PREF_SIZE);
		rowConstraints96.setVgrow(Priority.ALWAYS);
		gridPane49.getRowConstraints().add(rowConstraints96);
		scrollPane8.setContent(gridPane49);
		tab7.setContent(scrollPane8);
		tabs.getTabs().add(tab7);
		controlConsole = new Tab();
		controlConsole.setText(bundle.getString("CompilerPage_Console"));
		ScrollPane scrollPane9 = new ScrollPane();
		scrollPane9.setId("ScrollPane");
		scrollPane9.setFitToHeight(true);
		scrollPane9.setFitToWidth(true);
		scrollPane9.setPrefViewportHeight(400.0);
		scrollPane9.setPrefViewportWidth(600.0);
		VBox vBox42 = new VBox();
		vBox42.setId("VBox");
		vBox42.setAlignment(Pos.CENTER);
		vBox42.setMinHeight(Control.USE_PREF_SIZE);
		vBox42.setMinWidth(Control.USE_PREF_SIZE);
		vBox42.setSpacing(5.0);
		HBox include8 = ((JSConsoleSectionController) modelFacade.getFactory()
				.call(JSConsoleSectionController.class)).create();
		VBox.setVgrow(include8, Priority.ALWAYS);
		vBox42.getChildren().add(include8);
		Insets insets62 = new Insets(0.0, 0.0, 0.0, 0.0);
		vBox42.setPadding(insets62);
		scrollPane9.setContent(vBox42);
		controlConsole.setContent(scrollPane9);
		tabs.getTabs().add(controlConsole);
		vBox34.getChildren().add(tabs);
		anchorPane23.getChildren().add(vBox34);
		initialize(null, bundle);
		return anchorPane23;
	}

}
