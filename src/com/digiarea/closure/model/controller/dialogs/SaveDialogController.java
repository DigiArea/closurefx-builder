package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class SaveDialogController implements Initializable {

	private ResourceBundle bundle;
	private Stage stage;
	private String file;

	@FXML
	private Label controlMessage;

	private IStatus status = Status.CANCEL_STATUS;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@FXML
	private void handleSaveButtonAction(ActionEvent event) {
		status = Status.OK_STATUS;
		stage.close();
	}

	@FXML
	private void handleDoNotSaveButtonAction(ActionEvent event) {
		status = Status.NO_STATUS;
		stage.close();
	}

	@FXML
	private void handleCancelButtonAction(ActionEvent event) {
		status = Status.CANCEL_STATUS;
		stage.close();
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setFile(String file) {
		this.file = file;
		controlMessage.setText(MessageFormat.format(
				bundle.getString(IConstants.SaveDialog_Desc), this.file));
	}

	public IStatus getStatus() {
		return status;
	}

}
