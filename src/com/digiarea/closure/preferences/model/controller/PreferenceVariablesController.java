package com.digiarea.closure.preferences.model.controller;

import com.digiarea.closure.preferences.model.controller.ClosurePreferencesController;
import javafx.fxml.Initializable;
import com.digiarea.closure.preferences.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closure.preferences.model.Variable;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import java.net.URL;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import com.digiarea.closure.preferences.model.controller.dialogs.AddVariableDialogController;
import com.digiarea.closure.preferences.model.controller.PreferencesFactory;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import javafx.collections.ObservableList;
import java.io.File;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class PreferenceVariablesController extends ClosurePreferencesController implements Initializable {

    public PreferenceVariablesController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TableView<Variable> controlVariables;

    @FXML
    private TableColumn<Variable, String> controlName;

    @FXML
    private TableColumn<Variable, String> controlPath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlVariables.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        controlPath.setCellFactory(new Callback<TableColumn<Variable, String>, TableCell<Variable, String>>() {

            @Override
            public TableCell<Variable, String> call(TableColumn<Variable, String> param) {
                return new PreferenceVariablesController.PathCell();
            }
        });
        controlPath.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Variable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Variable, String> p) {
                return p.getValue().pathProperty();
            }
        });
        controlName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Variable, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Variable, String> p) {
                if (p.getValue() != null) {
                    return p.getValue().nameProperty();
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
    }

    @FXML
    private void handleApplyButtonAction(ActionEvent event) {
        modelFacade.saveVariables();
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        AddVariableDialogController controller = PreferencesFactory.getAddVariableDialog(bundle, modelFacade.getPrefs());
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addVariable(controller.getName(), controller.getPath());
        }
    }

    @FXML
    private void handleRemovedButtonAction(ActionEvent event) {
        ObservableList<Variable> sources = controlVariables.getSelectionModel().getSelectedItems();
        if (sources != null && !sources.isEmpty()) {
            for (Variable source : sources) {
                modelFacade.removeVariable(source);
            }
        }
    }

    public class PathCell extends TableCell<Variable, String> {

        public PathCell() {
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                if (!new File(item).exists()) {
                    setText("<invalid path> " + item);
                    setTooltip(new Tooltip(item));
                    setTextFill(Color.RED);
                    setFont(Font.font("Arial", FontPosture.ITALIC, 11));
                } else {
                    setText(item);
                    setTooltip(new Tooltip(item));
                    setTextFill(Color.BLACK);
                    setFont(Font.font("Arial", FontPosture.REGULAR, 11));
                }
            }
        }

    }

    public TableView<Variable> getControlVariables() {
        return controlVariables;
    }

    public TableColumn<Variable, String> getControlName() {
        return controlName;
    }

    public TableColumn<Variable, String> getControlPath() {
        return controlPath;
    }

}
