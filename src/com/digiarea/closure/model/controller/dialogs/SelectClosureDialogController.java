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

import com.digiarea.closure.preferences.model.ClosureLibrary;
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
public class SelectClosureDialogController implements Initializable {

	private Stage stage;

	@FXML
	private ListView<ClosureLibrary> controlLibraries;

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
		controlLibraries
				.setCellFactory(new Callback<ListView<ClosureLibrary>, ListCell<ClosureLibrary>>() {

					@Override
					public ListCell<ClosureLibrary> call(
							ListView<ClosureLibrary> list) {
						return new ClosureLibraryCell();
					}
				});
		loadClosureLibrarys();
	}

	private void loadClosureLibrarys() {
		PreferencesSerializer serializer = new PreferencesSerializer();
		controlLibraries.setItems(serializer.readLibraries()
				.librariesProperty());
	}

	@FXML
	private void handleClosureLibraryButtonAction(ActionEvent event) {
		PreferencesController controller = PreferencesFactory
				.getPreferenceDialog(bundle);
		controller.selectClosuresPage();
		loadClosureLibrarys();
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

	public ClosureLibrary getClosureLibrary() {
		return controlLibraries.getSelectionModel().getSelectedItem();
	}

	public class ClosureLibraryCell extends ListCell<ClosureLibrary> implements
			IConstants {

		public void updateItem(ClosureLibrary item, boolean empty) {
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
