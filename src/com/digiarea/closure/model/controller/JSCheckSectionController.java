package com.digiarea.closure.model.controller;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import com.digiarea.closure.model.controller.ClosureController;
import javafx.fxml.Initializable;
import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.FXML;
import com.digiarea.closure.model.Check;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import com.digiarea.closure.model.Optimization;
import javafx.scene.control.ToggleButton;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import com.digiarea.closure.model.providers.LabelProviders;
import javafx.scene.control.Tooltip;
import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSCheckSectionController extends ClosureController implements Initializable {

    public JSCheckSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private TableView<Check> controlCheck;

    @FXML
    private TableColumn<Check, Boolean> conrolCheckBox;

    @FXML
    private TableColumn<Check, String> conrolCheckDescription;

    @FXML
    private TableView<Optimization> controlOptimization;

    @FXML
    private TableColumn<Optimization, Boolean> conrolOptimizationBox;

    @FXML
    private TableColumn<Optimization, String> conrolOptimizationDescription;

    @FXML
    private ToggleButton controlSkipAllPasses;

    @FXML
    private ToggleButton controlFunctionsOnly;

    @FXML
    private ToggleButton controlPerformCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conrolCheckBox.setCellValueFactory(new PropertyValueFactory<Check, Boolean>("check"));
        conrolCheckBox.setCellFactory(new Callback<TableColumn<Check, Boolean>, TableCell<Check, Boolean>>() {

            @Override
            public TableCell<Check, Boolean> call(TableColumn<Check, Boolean> param) {
                TableCell<Check, Boolean> cell = new TableCell<Check, Boolean>() {

                    private CheckBox box;

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        if (item != null) {
                            if (box == null) {
                                createCheckBox();
                            }
                            box.setSelected(item);
                            setGraphic(box);
                        }
                    }

                    private void createCheckBox() {
                        box = new CheckBox();
                        box.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        box.selectedProperty().addListener(new ChangeListener<Boolean>() {

                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                ((Check) getTableRow().getItem()).setCheck(newValue);
                                commitEdit(newValue);
                            }
                        });
                    }
                };
                return cell;
            }
        });
        conrolCheckDescription.setCellFactory(new JSCheckSectionController.TextCellFactory());
        conrolCheckDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Check, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Check, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(LabelProviders.getCheckLabel(p.getValue().getType()));
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        conrolOptimizationBox.setCellValueFactory(new PropertyValueFactory<Optimization, Boolean>("optimize"));
        conrolOptimizationBox.setCellFactory(new Callback<TableColumn<Optimization, Boolean>, TableCell<Optimization, Boolean>>() {

            @Override
            public TableCell<Optimization, Boolean> call(TableColumn<Optimization, Boolean> param) {
                TableCell<Optimization, Boolean> cell = new TableCell<Optimization, Boolean>() {

                    private CheckBox box;

                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        if (item != null) {
                            if (box == null) {
                                createCheckBox();
                            }
                            box.setSelected(item);
                            setGraphic(box);
                        }
                    }

                    private void createCheckBox() {
                        box = new CheckBox();
                        box.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        box.selectedProperty().addListener(new ChangeListener<Boolean>() {

                            @Override
                            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                                ((Optimization) getTableRow().getItem()).setOptimize(newValue);
                                commitEdit(newValue);
                            }
                        });
                    }
                };
                return cell;
            }
        });
        conrolOptimizationDescription.setCellFactory(new JSCheckSectionController.OptimizationTextCellFactory());
        conrolOptimizationDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Optimization, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Optimization, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(LabelProviders.getOptimizationlabel(p.getValue().getType()));
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
    }

    public class OptimizationTextCellFactory implements Callback<TableColumn<Optimization, String>, TableCell<Optimization, String>> {

        @Override
        public TableCell<Optimization, String> call(TableColumn<Optimization, String> arg0) {
            TableCell<Optimization, String> cell = new TableCell<Optimization, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        setText(item);
                        setTooltip(new Tooltip(item));
                    }
                }
            };
            return cell;
        }

    }

    public class TextCellFactory implements Callback<TableColumn<Check, String>, TableCell<Check, String>> {

        @Override
        public TableCell<Check, String> call(TableColumn<Check, String> arg0) {
            TableCell<Check, String> cell = new TableCell<Check, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    if (item != null) {
                        setText(item);
                        setTooltip(new Tooltip(item));
                    }
                }
            };
            return cell;
        }

    }

    @FXML
    private void handleCheckAllChecksAction(ActionEvent event) {
        modelFacade.applyJSCheck(true);
    }

    @FXML
    private void handleUncheckAllChecksAction(ActionEvent event) {
        modelFacade.applyJSCheck(false);
    }

    @FXML
    private void handleCheckAllOptimizationsAction(ActionEvent event) {
        modelFacade.applyJSOptimization(true);
    }

    @FXML
    private void handleUncheckAllOptimizationsAction(ActionEvent event) {
        modelFacade.applyJSOptimization(false);
    }

    public TableView<Check> getControlCheck() {
        return controlCheck;
    }

    public TableView<Optimization> getControlOptimization() {
        return controlOptimization;
    }

    public ToggleButton getControlSkipAllPasses() {
        return controlSkipAllPasses;
    }

    public ToggleButton getControlFunctionsOnly() {
        return controlFunctionsOnly;
    }

    public TableColumn<Check, Boolean> getConrolCheckBox() {
        return conrolCheckBox;
    }

    public TableColumn<Check, String> getConrolCheckDescription() {
        return conrolCheckDescription;
    }

    public TableColumn<Optimization, Boolean> getConrolOptimizationBox() {
        return conrolOptimizationBox;
    }

    public TableColumn<Optimization, String> getConrolOptimizationDescription() {
        return conrolOptimizationDescription;
    }

    public ToggleButton getControlPerformCheck() {
        return controlPerformCheck;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane12 = new AnchorPane();
        anchorPane12.setId("AnchorPane");
        anchorPane12.setMinHeight(Control.USE_COMPUTED_SIZE);
        anchorPane12.setMinWidth(Control.USE_COMPUTED_SIZE);
        anchorPane12.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane12.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane11 = new TitledPane();
        titledPane11.setAnimated(false);
        titledPane11.setCollapsible(false);
        titledPane11.setFocusTraversable(true);
        titledPane11.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane11.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane11.setText(bundle.getString("JSChecksSection"));
        AnchorPane.setBottomAnchor(titledPane11, 0.0);
        AnchorPane.setLeftAnchor(titledPane11, 0.0);
        AnchorPane.setRightAnchor(titledPane11, 0.0);
        AnchorPane.setTopAnchor(titledPane11, 0.0);
        VBox vBox20 = new VBox();
        vBox20.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox20.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox20.setSpacing(6.0);
        Label label22 = new Label();
        label22.setText(bundle.getString("JSChecksSection_Desc"));
        vBox20.getChildren().add(label22);
        GridPane gridPane28 = new GridPane();
        gridPane28.setId("GridPane");
        gridPane28.setHgap(5.0);
        VBox.setVgrow(gridPane28, Priority.NEVER);
        controlSkipAllPasses = new ToggleButton();
        controlSkipAllPasses.setMaxWidth(1.7976931348623157E308);
        controlSkipAllPasses.setMnemonicParsing(false);
        controlSkipAllPasses.setText(bundle.getString("JSChecksSection_SkipAllPasses"));
        GridPane.setColumnIndex(controlSkipAllPasses, 0);
        GridPane.setRowIndex(controlSkipAllPasses, 0);
        toggleGroup = new ToggleGroup();
        controlSkipAllPasses.setToggleGroup(toggleGroup);
        gridPane28.getChildren().add(controlSkipAllPasses);
        controlFunctionsOnly = new ToggleButton();
        controlFunctionsOnly.setMaxWidth(1.7976931348623157E308);
        controlFunctionsOnly.setMnemonicParsing(false);
        controlFunctionsOnly.setText(bundle.getString("JSChecksSection_FunctionsOnly"));
        controlFunctionsOnly.setToggleGroup(toggleGroup);
        GridPane.setColumnIndex(controlFunctionsOnly, 1);
        GridPane.setRowIndex(controlFunctionsOnly, 0);
        gridPane28.getChildren().add(controlFunctionsOnly);
        controlPerformCheck = new ToggleButton();
        controlPerformCheck.setMaxWidth(1.7976931348623157E308);
        controlPerformCheck.setMnemonicParsing(false);
        controlPerformCheck.setText(bundle.getString("JSChecksSection_PerformChecks"));
        controlPerformCheck.setToggleGroup(toggleGroup);
        GridPane.setColumnIndex(controlPerformCheck, 2);
        GridPane.setRowIndex(controlPerformCheck, 0);
        gridPane28.getChildren().add(controlPerformCheck);
        ColumnConstraints columnConstraints54 = new ColumnConstraints();
        columnConstraints54.setHgrow(Priority.ALWAYS);
        columnConstraints54.setMinWidth(10.0);
        gridPane28.getColumnConstraints().add(columnConstraints54);
        ColumnConstraints columnConstraints55 = new ColumnConstraints();
        columnConstraints55.setHgrow(Priority.ALWAYS);
        columnConstraints55.setMinWidth(10.0);
        gridPane28.getColumnConstraints().add(columnConstraints55);
        ColumnConstraints columnConstraints56 = new ColumnConstraints();
        columnConstraints56.setHgrow(Priority.ALWAYS);
        columnConstraints56.setMinWidth(10.0);
        gridPane28.getColumnConstraints().add(columnConstraints56);
        RowConstraints rowConstraints52 = new RowConstraints();
        rowConstraints52.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints52.setVgrow(Priority.NEVER);
        gridPane28.getRowConstraints().add(rowConstraints52);
        vBox20.getChildren().add(gridPane28);
        GridPane gridPane29 = new GridPane();
        gridPane29.setId("GridPane");
        gridPane29.setHgap(5.0);
        VBox.setVgrow(gridPane29, Priority.NEVER);
        Button button21 = new Button();
        button21.setAlignment(Pos.CENTER_RIGHT);
        button21.setContentDisplay(ContentDisplay.RIGHT);
        button21.setMnemonicParsing(false);
        button21.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleUncheckAllChecksAction(event);
            }
        });
        button21.setText("");
        GridPane.setColumnIndex(button21, 2);
        GridPane.setHalignment(button21, HPos.RIGHT);
        GridPane.setRowIndex(button21, 0);
        ImageView imageView10 = new ImageView();
        imageView10.setFitHeight(16.0);
        imageView10.setFitWidth(16.0);
        imageView10.setMouseTransparent(true);
        imageView10.setPickOnBounds(true);
        imageView10.setPreserveRatio(true);
        Image image10 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/editor-uncheck.png").openStream());
        imageView10.setImage(image10);
        button21.setGraphic(imageView10);
        Tooltip tooltip4 = new Tooltip();
        tooltip4.setText(bundle.getString("JSChecksSection_UncheckAll"));
        button21.setTooltip(tooltip4);
        gridPane29.getChildren().add(button21);
        Button button22 = new Button();
        button22.setAlignment(Pos.CENTER_RIGHT);
        button22.setContentDisplay(ContentDisplay.RIGHT);
        button22.setMnemonicParsing(false);
        button22.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleCheckAllChecksAction(event);
            }
        });
        button22.setText("");
        GridPane.setColumnIndex(button22, 1);
        GridPane.setHalignment(button22, HPos.RIGHT);
        GridPane.setRowIndex(button22, 0);
        ImageView imageView11 = new ImageView();
        imageView11.setFitHeight(16.0);
        imageView11.setFitWidth(16.0);
        imageView11.setMouseTransparent(true);
        imageView11.setPickOnBounds(true);
        imageView11.setPreserveRatio(true);
        Image image11 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/editor-check.png").openStream());
        imageView11.setImage(image11);
        button22.setGraphic(imageView11);
        Tooltip tooltip5 = new Tooltip();
        tooltip5.setText(bundle.getString("JSChecksSection_CheckAll"));
        button22.setTooltip(tooltip5);
        gridPane29.getChildren().add(button22);
        ColumnConstraints columnConstraints57 = new ColumnConstraints();
        columnConstraints57.setHalignment(HPos.RIGHT);
        columnConstraints57.setHgrow(Priority.ALWAYS);
        columnConstraints57.setMinWidth(Control.USE_PREF_SIZE);
        gridPane29.getColumnConstraints().add(columnConstraints57);
        ColumnConstraints columnConstraints58 = new ColumnConstraints();
        columnConstraints58.setHalignment(HPos.RIGHT);
        columnConstraints58.setHgrow(Priority.NEVER);
        columnConstraints58.setMinWidth(Control.USE_PREF_SIZE);
        gridPane29.getColumnConstraints().add(columnConstraints58);
        ColumnConstraints columnConstraints59 = new ColumnConstraints();
        columnConstraints59.setHalignment(HPos.RIGHT);
        columnConstraints59.setHgrow(Priority.NEVER);
        columnConstraints59.setMinWidth(Control.USE_PREF_SIZE);
        gridPane29.getColumnConstraints().add(columnConstraints59);
        RowConstraints rowConstraints53 = new RowConstraints();
        rowConstraints53.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints53.setValignment(VPos.CENTER);
        rowConstraints53.setVgrow(Priority.NEVER);
        gridPane29.getRowConstraints().add(rowConstraints53);
        vBox20.getChildren().add(gridPane29);
        controlCheck = new TableView();
        controlCheck.setMaxHeight(Control.USE_COMPUTED_SIZE);
        controlCheck.setMinHeight(Control.USE_COMPUTED_SIZE);
        controlCheck.setPrefHeight(100.0);
        controlCheck.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlCheck.setTableMenuButtonVisible(false);
        VBox.setVgrow(controlCheck, Priority.ALWAYS);
        conrolCheckBox = new TableColumn();
        conrolCheckBox.setMaxWidth(25.0);
        conrolCheckBox.setMinWidth(25.0);
        conrolCheckBox.setPrefWidth(25.0);
        conrolCheckBox.setText(".");
        controlCheck.getColumns().add(conrolCheckBox);
        conrolCheckDescription = new TableColumn();
        conrolCheckDescription.setMinWidth(100.0);
        conrolCheckDescription.setPrefWidth(400.0);
        conrolCheckDescription.setText(bundle.getString("JSChecksSection_Column_Checks"));
        conrolCheckDescription.setVisible(true);
        controlCheck.getColumns().add(conrolCheckDescription);
        vBox20.getChildren().add(controlCheck);
        GridPane gridPane30 = new GridPane();
        gridPane30.setId("GridPane");
        gridPane30.setHgap(5.0);
        VBox.setVgrow(gridPane30, Priority.NEVER);
        Button button23 = new Button();
        button23.setAlignment(Pos.CENTER_RIGHT);
        button23.setContentDisplay(ContentDisplay.RIGHT);
        button23.setMnemonicParsing(false);
        button23.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleUncheckAllOptimizationsAction(event);
            }
        });
        button23.setText("");
        GridPane.setColumnIndex(button23, 2);
        GridPane.setHalignment(button23, HPos.RIGHT);
        GridPane.setRowIndex(button23, 0);
        ImageView imageView12 = new ImageView();
        imageView12.setFitHeight(16.0);
        imageView12.setFitWidth(16.0);
        imageView12.setMouseTransparent(true);
        imageView12.setPickOnBounds(true);
        imageView12.setPreserveRatio(true);
        Image image12 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/editor-uncheck.png").openStream());
        imageView12.setImage(image12);
        button23.setGraphic(imageView12);
        Tooltip tooltip6 = new Tooltip();
        tooltip6.setText(bundle.getString("JSChecksSection_UncheckAll"));
        button23.setTooltip(tooltip6);
        gridPane30.getChildren().add(button23);
        Button button24 = new Button();
        button24.setAlignment(Pos.CENTER_RIGHT);
        button24.setContentDisplay(ContentDisplay.RIGHT);
        button24.setMnemonicParsing(false);
        button24.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleCheckAllOptimizationsAction(event);
            }
        });
        button24.setText("");
        GridPane.setColumnIndex(button24, 1);
        GridPane.setHalignment(button24, HPos.RIGHT);
        GridPane.setRowIndex(button24, 0);
        ImageView imageView13 = new ImageView();
        imageView13.setFitHeight(16.0);
        imageView13.setFitWidth(16.0);
        imageView13.setMouseTransparent(true);
        imageView13.setPickOnBounds(true);
        imageView13.setPreserveRatio(true);
        Image image13 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/editor-check.png").openStream());
        imageView13.setImage(image13);
        button24.setGraphic(imageView13);
        Tooltip tooltip7 = new Tooltip();
        tooltip7.setText(bundle.getString("JSChecksSection_CheckAll"));
        button24.setTooltip(tooltip7);
        gridPane30.getChildren().add(button24);
        ColumnConstraints columnConstraints60 = new ColumnConstraints();
        columnConstraints60.setHalignment(HPos.RIGHT);
        columnConstraints60.setHgrow(Priority.ALWAYS);
        columnConstraints60.setMinWidth(Control.USE_PREF_SIZE);
        gridPane30.getColumnConstraints().add(columnConstraints60);
        ColumnConstraints columnConstraints61 = new ColumnConstraints();
        columnConstraints61.setHalignment(HPos.RIGHT);
        columnConstraints61.setHgrow(Priority.NEVER);
        columnConstraints61.setMinWidth(Control.USE_PREF_SIZE);
        gridPane30.getColumnConstraints().add(columnConstraints61);
        ColumnConstraints columnConstraints62 = new ColumnConstraints();
        columnConstraints62.setHalignment(HPos.RIGHT);
        columnConstraints62.setHgrow(Priority.NEVER);
        columnConstraints62.setMinWidth(Control.USE_PREF_SIZE);
        gridPane30.getColumnConstraints().add(columnConstraints62);
        RowConstraints rowConstraints54 = new RowConstraints();
        rowConstraints54.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints54.setValignment(VPos.CENTER);
        rowConstraints54.setVgrow(Priority.NEVER);
        gridPane30.getRowConstraints().add(rowConstraints54);
        vBox20.getChildren().add(gridPane30);
        controlOptimization = new TableView();
        controlOptimization.setMaxHeight(Control.USE_COMPUTED_SIZE);
        controlOptimization.setMinHeight(Control.USE_COMPUTED_SIZE);
        controlOptimization.setPrefHeight(100.0);
        controlOptimization.setPrefWidth(Control.USE_COMPUTED_SIZE);
        VBox.setVgrow(controlOptimization, Priority.ALWAYS);
        conrolOptimizationBox = new TableColumn();
        conrolOptimizationBox.setMaxWidth(25.0);
        conrolOptimizationBox.setMinWidth(25.0);
        conrolOptimizationBox.setPrefWidth(25.0);
        conrolOptimizationBox.setText(".");
        controlOptimization.getColumns().add(conrolOptimizationBox);
        conrolOptimizationDescription = new TableColumn();
        conrolOptimizationDescription.setMinWidth(100.0);
        conrolOptimizationDescription.setPrefWidth(400.0);
        conrolOptimizationDescription.setText(bundle.getString("JSChecksSection_Column_Optimizations"));
        controlOptimization.getColumns().add(conrolOptimizationDescription);
        vBox20.getChildren().add(controlOptimization);
        Insets insets28 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox20.setPadding(insets28);
        titledPane11.setContent(vBox20);
        anchorPane12.getChildren().add(titledPane11);
        initialize(null, bundle);
        return anchorPane12;
    }

}
