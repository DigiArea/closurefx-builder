package com.digiarea.closure.model.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.bind.ModelFacade;
import com.digiarea.closure.model.providers.LabelProviders;
import com.digiarea.closurefx.ResourceUtils;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class JSWarningsSectionController extends ClosureController implements Initializable {

    public JSWarningsSectionController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TableView<Warning> controlWarning;

    @FXML
    private TableColumn<Warning, String> controlName;

    @FXML
    private TableColumn<Warning, SeverityType> controlLevel;

    @FXML
    private TableColumn<Warning, String> controlDescription;

    private ObservableList<SeverityType> severityTypes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        severityTypes = FXCollections.observableList(Arrays.asList(SeverityType.values()));
        controlWarning.setEditable(true);
        controlName.setCellFactory(new JSWarningsSectionController.TextCellFactory());
        controlName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warning, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Warning, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(LabelProviders.getWarningLabel(p.getValue().getType()));
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        controlLevel.setCellValueFactory(new PropertyValueFactory<Warning, SeverityType>("severity"));
        controlLevel.setCellFactory(new JSWarningsSectionController.WarningCellFactory());
        controlDescription.setCellFactory(new JSWarningsSectionController.TextCellFactory());
        controlDescription.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warning, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Warning, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(LabelProviders.getWarningDescription(p.getValue().getType()));
                } else {
                    return new SimpleStringProperty("<no description>");
                }
            }
        });
    }

    @FXML
    private void handleWarningButtonAction(ActionEvent event) {
        modelFacade.applyJSWarningLevel(SeverityType.WARNING);
    }

    @FXML
    private void handleErrorButtonAction(ActionEvent event) {
        modelFacade.applyJSWarningLevel(SeverityType.ERROR);
    }

    @FXML
    private void handleOffButtonAction(ActionEvent event) {
        modelFacade.applyJSWarningLevel(SeverityType.OFF);
    }

    public TableView<Warning> getControlWarning() {
        return controlWarning;
    }

    private class LevelComboBoxCellFactory extends ListCell<SeverityType> {

        @Override
        protected void updateItem(SeverityType item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.name());
                switch(item) {
                    case ERROR:
                        setGraphic(new ImageView(ResourceUtils.MARK_ERROR));
                        break;
                    case OFF:
                        setGraphic(new ImageView(ResourceUtils.MARK_OFF));
                        break;
                    case WARNING:
                        setGraphic(new ImageView(ResourceUtils.MARK_WARNING));
                        break;
                }
            }
        }

    }

    public class TextCellFactory implements Callback<TableColumn<Warning, String>, TableCell<Warning, String>> {

        @Override
        public TableCell<Warning, String> call(TableColumn<Warning, String> arg0) {
            TableCell<Warning, String> cell = new TableCell<Warning, String>() {

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

    private class WarningCellFactory implements Callback<TableColumn<Warning, SeverityType>, TableCell<Warning, SeverityType>> {

        @Override
        public TableCell<Warning, SeverityType> call(TableColumn<Warning, SeverityType> arg0) {
            TableCell<Warning, SeverityType> cell = new TableCell<Warning, SeverityType>() {

                private ComboBox<SeverityType> choice;

                @Override
                public void updateItem(SeverityType item, boolean empty) {
                    if (item != null) {
                        if (choice == null) {
                            createChoice();
                        }
                        choice.getSelectionModel().select(severityTypes.indexOf(item));
                        setGraphic(choice);
                    }
                }

                private void createChoice() {
                    choice = new ComboBox<SeverityType>(severityTypes);
                    choice.getStyleClass().add("invisible-combo-box-base");
                    choice.setButtonCell(new com.digiarea.closure.model.controller.JSWarningsSectionController.LevelComboBoxCellFactory());
                    choice.setCellFactory(new Callback<ListView<SeverityType>, ListCell<SeverityType>>() {

                        @Override
                        public ListCell<SeverityType> call(ListView<SeverityType> param) {
                            return new com.digiarea.closure.model.controller.JSWarningsSectionController.LevelComboBoxCellFactory();
                        }
                    });
                    choice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SeverityType>() {

                        @Override
                        public void changed(ObservableValue<? extends SeverityType> observable, SeverityType oldValue, SeverityType newValue) {
                            ((Warning) getTableRow().getItem()).setSeverity(newValue);
                            commitEdit(newValue);
                        }
                    });
                }
            };
            return cell;
        }

    }

    public TableColumn<Warning, String> getControlName() {
        return controlName;
    }

    public TableColumn<Warning, SeverityType> getControlLevel() {
        return controlLevel;
    }

    public TableColumn<Warning, String> getControlDescription() {
        return controlDescription;
    }

    public AnchorPane create() throws Exception {
        AnchorPane anchorPane28 = new AnchorPane();
        anchorPane28.setId("AnchorPane");
        anchorPane28.setMinHeight(Control.USE_COMPUTED_SIZE);
        anchorPane28.setMinWidth(Control.USE_COMPUTED_SIZE);
        anchorPane28.setPrefHeight(Control.USE_COMPUTED_SIZE);
        anchorPane28.setPrefWidth(Control.USE_COMPUTED_SIZE);
        TitledPane titledPane25 = new TitledPane();
        titledPane25.setAnimated(false);
        titledPane25.setCollapsible(false);
        titledPane25.setPrefHeight(Control.USE_COMPUTED_SIZE);
        titledPane25.setPrefWidth(Control.USE_COMPUTED_SIZE);
        titledPane25.setText(bundle.getString("JSWarningsSection"));
        AnchorPane.setBottomAnchor(titledPane25, 0.0);
        AnchorPane.setLeftAnchor(titledPane25, 0.0);
        AnchorPane.setRightAnchor(titledPane25, 0.0);
        AnchorPane.setTopAnchor(titledPane25, 0.0);
        VBox vBox50 = new VBox();
        vBox50.setPrefHeight(Control.USE_COMPUTED_SIZE);
        vBox50.setPrefWidth(Control.USE_COMPUTED_SIZE);
        vBox50.setSpacing(6.0);
        Label label64 = new Label();
        label64.setText(bundle.getString("JSWarningsSection_Desc"));
        vBox50.getChildren().add(label64);
        GridPane gridPane58 = new GridPane();
        gridPane58.setHgap(5.0);
        VBox.setVgrow(gridPane58, Priority.NEVER);
        Button btnError = new Button();
        btnError.setId("btnError");
        btnError.setMnemonicParsing(false);
        btnError.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleErrorButtonAction(event);
            }
        });
        btnError.setText("");
        GridPane.setColumnIndex(btnError, 2);
        GridPane.setRowIndex(btnError, 0);
        ImageView imageView30 = new ImageView();
        imageView30.setFitHeight(16.0);
        imageView30.setFitWidth(16.0);
        imageView30.setMouseTransparent(true);
        imageView30.setPickOnBounds(true);
        imageView30.setPreserveRatio(true);
        Image image30 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-error.png").openStream());
        imageView30.setImage(image30);
        btnError.setGraphic(imageView30);
        Tooltip tooltip15 = new Tooltip();
        tooltip15.setText(bundle.getString("JSWarningsSection_Column_Tooltip_Error"));
        btnError.setTooltip(tooltip15);
        gridPane58.getChildren().add(btnError);
        Button btnDefault = new Button();
        btnDefault.setId("btnDefault");
        btnDefault.setMnemonicParsing(false);
        btnDefault.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleOffButtonAction(event);
            }
        });
        btnDefault.setText("");
        GridPane.setColumnIndex(btnDefault, 0);
        GridPane.setRowIndex(btnDefault, 0);
        ImageView imageView31 = new ImageView();
        imageView31.setFitHeight(16.0);
        imageView31.setFitWidth(16.0);
        imageView31.setMouseTransparent(true);
        imageView31.setPickOnBounds(true);
        imageView31.setPreserveRatio(true);
        Image image31 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-off.png").openStream());
        imageView31.setImage(image31);
        btnDefault.setGraphic(imageView31);
        Tooltip tooltip16 = new Tooltip();
        tooltip16.setText(bundle.getString("JSWarningsSection_Column_Tooltip_Off"));
        btnDefault.setTooltip(tooltip16);
        gridPane58.getChildren().add(btnDefault);
        Button btnWarning = new Button();
        btnWarning.setId("btnWarning");
        btnWarning.setMnemonicParsing(false);
        btnWarning.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                handleWarningButtonAction(event);
            }
        });
        btnWarning.setText("");
        GridPane.setColumnIndex(btnWarning, 1);
        GridPane.setRowIndex(btnWarning, 0);
        ImageView imageView32 = new ImageView();
        imageView32.setFitHeight(16.0);
        imageView32.setFitWidth(16.0);
        imageView32.setMouseTransparent(true);
        imageView32.setPickOnBounds(true);
        imageView32.setPreserveRatio(true);
        Image image32 = new Image(getClass().getResource("/com/digiarea/closurefx/resources/mark-exclamation.png").openStream());
        imageView32.setImage(image32);
        btnWarning.setGraphic(imageView32);
        Tooltip tooltip17 = new Tooltip();
        tooltip17.setText(bundle.getString("JSWarningsSection_Column_Tooltip_Warning"));
        btnWarning.setTooltip(tooltip17);
        gridPane58.getChildren().add(btnWarning);
        ColumnConstraints columnConstraints129 = new ColumnConstraints();
        columnConstraints129.setHalignment(HPos.RIGHT);
        columnConstraints129.setHgrow(Priority.ALWAYS);
        columnConstraints129.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints129.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane58.getColumnConstraints().add(columnConstraints129);
        ColumnConstraints columnConstraints130 = new ColumnConstraints();
        columnConstraints130.setHgrow(Priority.NEVER);
        columnConstraints130.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints130.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane58.getColumnConstraints().add(columnConstraints130);
        ColumnConstraints columnConstraints131 = new ColumnConstraints();
        columnConstraints131.setHgrow(Priority.NEVER);
        columnConstraints131.setMinWidth(Control.USE_PREF_SIZE);
        columnConstraints131.setPrefWidth(Control.USE_COMPUTED_SIZE);
        gridPane58.getColumnConstraints().add(columnConstraints131);
        RowConstraints rowConstraints115 = new RowConstraints();
        rowConstraints115.setMinHeight(Control.USE_PREF_SIZE);
        rowConstraints115.setPrefHeight(Control.USE_COMPUTED_SIZE);
        rowConstraints115.setVgrow(Priority.NEVER);
        gridPane58.getRowConstraints().add(rowConstraints115);
        vBox50.getChildren().add(gridPane58);
        controlWarning = new TableView();
        controlWarning.setEditable(false);
        controlWarning.setMaxHeight(Control.USE_COMPUTED_SIZE);
        controlWarning.setMinHeight(Control.USE_PREF_SIZE);
        controlWarning.setMinWidth(Control.USE_PREF_SIZE);
        controlWarning.setPrefHeight(100.0);
        controlWarning.setPrefWidth(Control.USE_COMPUTED_SIZE);
        controlWarning.setTableMenuButtonVisible(false);
        VBox.setVgrow(controlWarning, Priority.ALWAYS);
        controlName = new TableColumn();
        controlName.setEditable(false);
        controlName.setMinWidth(150.0);
        controlName.setPrefWidth(150.0);
        controlName.setText(bundle.getString("JSWarningsSection_Column_Name"));
        controlWarning.getColumns().add(controlName);
        controlLevel = new TableColumn();
        controlLevel.setMinWidth(100.0);
        controlLevel.setPrefWidth(100.0);
        controlLevel.setText(bundle.getString("JSWarningsSection_Column_Level"));
        controlWarning.getColumns().add(controlLevel);
        controlDescription = new TableColumn();
        controlDescription.setEditable(false);
        controlDescription.setMinWidth(300.0);
        controlDescription.setPrefWidth(300.0);
        controlDescription.setText(bundle.getString("JSWarningsSection_Column_Description"));
        controlWarning.getColumns().add(controlDescription);
        vBox50.getChildren().add(controlWarning);
        Insets insets72 = new Insets(10.0, 10.0, 10.0, 10.0);
        vBox50.setPadding(insets72);
        titledPane25.setContent(vBox50);
        anchorPane28.getChildren().add(titledPane25);
        initialize(null, bundle);
        return anchorPane28;
    }

}
