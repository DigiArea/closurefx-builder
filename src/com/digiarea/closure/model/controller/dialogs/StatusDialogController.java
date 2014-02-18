package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class StatusDialogController implements Initializable {

	private Stage stage;

	@FXML
	private ImageView controlIcon;

	@FXML
	private Label controlMessage;

	private IStatus status = Status.CANCEL_STATUS;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public void setStatus(IStatus status) {
		controlMessage.setText(status.getMessage());
		switch (status.getSeverity()) {
		default:
			controlIcon.setImage(ResourceUtils.STATUS_WARNING);
			break;
		}
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

}
