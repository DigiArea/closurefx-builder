package com.digiarea.closure.preferences.model.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.digiarea.closure.model.controller.UIUtils;
import com.digiarea.closure.model.entity.OutputPlaceholder;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class AddVariableDialogController implements Initializable {

	private ResourceBundle bundle;
	private Stage stage;
	private IStatus status = Status.CANCEL_STATUS;
	private File lastFile;
	private Variables variables;

	@FXML
	private TextField controlName;

	@FXML
	private TextField controlPath;

	@FXML
	private Label controlLabel;

	@FXML
	private Button controlOk;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bundle = rb;
		check();

		controlName.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(
					ObservableValue<? extends String> paramObservableValue,
					String paramT1, String paramT2) {
				check();
			}
		});
		controlPath.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(
					ObservableValue<? extends String> paramObservableValue,
					String paramT1, String paramT2) {
				check();
			}
		});
	}

	private void check() {

		controlLabel.getStyleClass().remove(IConstants.CSS_STATUS_ERROR);
		controlPath.getStyleClass().remove(IConstants.CSS_STATUS_ERROR);
		controlOk.setDisable(true);
		if (controlName.getText().isEmpty()) {
			controlLabel.setText(bundle
					.getString(IConstants.PreferencesVariables_NameEmpty));
			controlLabel.getStyleClass().add(IConstants.CSS_STATUS_ERROR);
		} else if (controlPath.getText().isEmpty()) {
			controlLabel.setText(bundle
					.getString(IConstants.PreferencesVariables_PathEmpty));
			controlLabel.getStyleClass().add(IConstants.CSS_STATUS_ERROR);
		} else {
			if (!new File(controlPath.getText()).exists()) {
				controlLabel
						.setText(bundle
								.getString(IConstants.PreferencesVariables_PathInvalid));
				controlPath.getStyleClass().add(IConstants.CSS_STATUS_ERROR);
				controlLabel.getStyleClass().add(IConstants.CSS_STATUS_ERROR);
			} else {
				boolean wrong = false;
				for (Variable var : variables.getVariables()) {
					if (var.getName().equals(controlName.getText())) {
						wrong = true;
					}
				}
				try {
					OutputPlaceholder.valueOf(controlName.getText());
					wrong = true;
				} catch (IllegalArgumentException e) {
					wrong = false;
				}

				if (wrong) {
					controlLabel
							.setText(bundle
									.getString(IConstants.PreferencesVariables_VariableDuplicate));
					controlLabel.getStyleClass().add(
							IConstants.CSS_STATUS_ERROR);
				} else {

					controlOk.setDisable(false);
					controlLabel.setText(bundle
							.getString(IConstants.PreferencesVariables_Label));
				}
			}
		}
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
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

	@FXML
	private void handleFolderButtonAction(ActionEvent event) {
		File file = UIUtils.chooseFolder(lastFile,
				bundle.getString(IConstants.SourceSection_Add_Title));
		if (file != null) {
			controlPath.setText(file.getAbsolutePath());
			lastFile = file;
		}
	}

	@FXML
	private void handleFileButtonAction(ActionEvent event) {
		File file = UIUtils.chooseFile(
				bundle.getString(IConstants.SourceSection_Add_Title), null,
				new String[] {});
		if (file != null) {
			controlPath.setText(file.getAbsolutePath());
		}
	}

	public String getName() {
		return controlName.getText();
	}

	public String getPath() {
		return controlPath.getText();
	}

	public IStatus getStatus() {
		return status;
	}

}
