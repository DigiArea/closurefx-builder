package com.digiarea.closure.preferences.model.controller;

import com.digiarea.closure.preferences.model.controller.ClosurePreferencesController;
import javafx.fxml.Initializable;
import com.digiarea.closure.preferences.model.bind.ModelFacade;
import java.util.ResourceBundle;
import com.digiarea.closure.preferences.model.ClosureLibrary;
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
import com.digiarea.closure.preferences.model.controller.dialogs.AddClosureLibraryDialogController;
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
public class PreferenceClosureController extends ClosurePreferencesController implements Initializable {

    public PreferenceClosureController(ModelFacade modelFacade, ResourceBundle bundle) {
        super(modelFacade, bundle);
    }

    @FXML
    private TableView<ClosureLibrary> controlClosure;

    @FXML
    private TableColumn<ClosureLibrary, String> controlName;

    @FXML
    private TableColumn<ClosureLibrary, String> controlPath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controlClosure.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        controlPath.setCellFactory(new Callback<TableColumn<ClosureLibrary, String>, TableCell<ClosureLibrary, String>>() {

            @Override
            public TableCell<ClosureLibrary, String> call(TableColumn<ClosureLibrary, String> param) {
                return new PreferenceClosureController.PathCell();
            }
        });
        controlPath.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureLibrary, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureLibrary, String> p) {
                return p.getValue().pathProperty();
            }
        });
        controlName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClosureLibrary, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClosureLibrary, String> p) {
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
        modelFacade.saveClosureLibraries();
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        AddClosureLibraryDialogController controller = PreferencesFactory.getAddClosureLibraryDialog(bundle, modelFacade.getPrefs());
        if (controller.getStatus().getSeverity() == StatusType.OK) {
            modelFacade.addClosureLibrary(controller.getName(), controller.getPath());
        }
    }

    @FXML
    private void handleRemovedButtonAction(ActionEvent event) {
        ObservableList<ClosureLibrary> sources = controlClosure.getSelectionModel().getSelectedItems();
        if (sources != null && !sources.isEmpty()) {
            for (ClosureLibrary source : sources) {
                modelFacade.removeClosureLibrary(source);
            }
        }
    }

    public class PathCell extends TableCell<ClosureLibrary, String> {

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

    public TableView<ClosureLibrary> getControlClosure() {
        return controlClosure;
    }

    public TableColumn<ClosureLibrary, String> getControlName() {
        return controlName;
    }

    public TableColumn<ClosureLibrary, String> getControlPath() {
        return controlPath;
    }

}
