package com.digiarea.closure.model.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closure.preferences.model.controller.PreferencesController;
import com.digiarea.closure.preferences.model.controller.PreferencesFactory;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SelectVariableDialogController implements Initializable {

	private Stage stage;

	@FXML
	private ListView<Variable> controlVariables;

	@FXML
	private Label controlMessage;

	private IStatus status = Status.CANCEL_STATUS;
	private ResourceBundle bundle;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bundle = rb;
		controlVariables
				.setCellFactory(new Callback<ListView<Variable>, ListCell<Variable>>() {

					@Override
					public ListCell<Variable> call(ListView<Variable> list) {
						return new VariableCell();
					}
				});
		loadVariables();
	}

	private void loadVariables() {
		PreferencesSerializer serializer = new PreferencesSerializer();
		controlVariables.setItems(serializer.readVariables()
				.variablesProperty());
	}

	@FXML
	private void handleVariableButtonAction(ActionEvent event) {
		PreferencesController controller = PreferencesFactory
				.getPreferenceDialog(bundle);
		controller.selectVariablesPage();
		loadVariables();
	}

	@FXML
	private void handleOkButtonAction(ActionEvent event) {
		status = Status.OK_STATUS;
		stage.close();
	}

	@FXML
	private void handleCancelButtonAction(ActionEvent event) {
		status = Status.CANCEL_STATUS;
		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public IStatus getStatus() {
		return status;
	}

	public Variable getVariable() {
		return controlVariables.getSelectionModel().getSelectedItem();
	}

	public class VariableCell extends ListCell<Variable> implements IConstants {

		public void updateItem(Variable item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
			}
			if (item != null) {
				if (!new File(item.getPath()).exists()) {
					setPrefHeight(0);
					setPrefWidth(0);
					setVisible(false);
					setText(null);
					setGraphic(null);
				} else {
					setPrefHeight(getMinHeight());
					setPrefWidth(getMaxWidth());
					setVisible(true);
					setText(item.getName());
					Label label = new Label(item.getPath());
					label.setTextFill(Color.GRAY);
					setGraphic(label);
					setContentDisplay(ContentDisplay.RIGHT);
				}
			}
		}

	}

}
