package com.digiarea.closure.model.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import com.digiarea.closure.model.controller.IConsole;
import com.digiarea.closure.model.export.ExportCLIConsole;
import com.digiarea.closure.model.providers.ConsoleCellFactory;
import com.digiarea.closurefx.Document;
import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class ExportCLIDialogController implements Initializable, IConsole {

	@FXML
	private ProgressBar progressBar;

	private ObservableList<Status> messages;

	private ResourceBundle bundle;
	private Document document;
	private Stage stage;

	@FXML
	private Label controlMessage;
	@FXML
	private TextField controlFile;
	@FXML
	private ListView<Status> controlDetails;

	private IStatus status = Status.CANCEL_STATUS;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		messages = FXCollections.observableArrayList();
		controlDetails.setItems(messages);
		controlDetails.setCellFactory(new ConsoleCellFactory());
	}

	@FXML
	private void handleStartButton(ActionEvent event) {
		if (controlFile.getText() != null && !controlFile.getText().isEmpty()) {
			File file = new File(controlFile.getText());
			if (file.exists()) {
				ExportCLIConsole console = new ExportCLIConsole(this,
						document.getClosure(), bundle,
						document.getPathResolver());
				console.setFile(file);
				console.setName(document.getName());
				console.start();
			}
		}
	}

	@FXML
	private void handleBrowseButtonAction(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("JavaFX Projects");
		File file = chooser.showDialog(stage);
		controlFile.setText(file.getAbsolutePath());
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public IStatus getStatus() {
		return status;
	}

	@Override
	public ProgressBar getProgressBar() {
		return progressBar;
	}

	@Override
	public void addErrors(List<ClosureStatus> error) {
		messages.addAll(new ArrayList<ClosureStatus>(error));
	}

	public void addError(ClosureStatus error) {
		messages.add(error);
	}

	public void addMessage(Status error) {
		messages.add(error);
	}

	public Label getErrorLabel() {
		return null;
	}

	public Label getWarningLabel() {
		return null;
	}

	public ObservableList<Status> getConsole() {
		return messages;
	}

	public ObservableList<ClosureStatus> getProblems() {
		return null;
	}

	@Override
	public void report(IStatus status) {
		messages.add((Status) status);
	}

	@Override
	public void generateReport() {
	}

}
