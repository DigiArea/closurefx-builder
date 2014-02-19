package com.digiarea.closure.model.controller.dialogs;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.export.ClosureCLExporter;
import com.digiarea.closurefx.Document;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;
import com.digiarea.closurefx.cli.console.BasicConsole;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class ExportCLIDialogController implements Initializable {

	private ResourceBundle bundle;
	private Document document;
	private Stage stage;

	@FXML
	private TextField controlFile;
	@FXML
	private ListView<Status> controlDetails;

	@FXML
	private ToggleButton controlWhite;
	@FXML
	private ToggleButton controlSimple;
	@FXML
	private ToggleButton controlAdvanced;
	@FXML
	private ToggleButton controlDefault;
	@FXML
	private ToggleButton controlQuite;
	@FXML
	private ToggleButton controlVerbose;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	private String getCompilationLevel() {
		if (controlWhite.isPressed()) {
			return "WHITESPACE_ONLY";
		} else if (controlSimple.isPressed()) {
			return "SIMPLE_OPTIMIZATIONS";
		} else if (controlAdvanced.isPressed()) {
			return "ADVANCED_OPTIMIZATIONS";
		}
		return null;
	}

	private String getWarningLevel() {
		if (controlDefault.isPressed()) {
			return "DEFAULT";
		} else if (controlQuite.isPressed()) {
			return "QUIET";
		} else if (controlVerbose.isPressed()) {
			return "VERBOSE";
		}
		return null;
	}

	@FXML
	private void handleStartButton(ActionEvent event) {
		if (controlFile.getText() != null && !controlFile.getText().isEmpty()) {
			File file = new File(controlFile.getText());
			if (file.exists()) {
				File newFile = new File(new Path(file.getAbsolutePath())
						.append(document.getName()).addFileExtension("cli")
						.toString());
				try {
					if (!newFile.exists()) {
						newFile.createNewFile();
					}
					BasicConsole console = new BasicConsole(
							new HashMap<IStatus.StatusType, List<IStatus>>(),
							null);
					ClosureCLExporter exporter = new ClosureCLExporter(
							document.getPathResolver(), new FileOutputStream(
									newFile), console, bundle);
					exporter.setWarningLevel(getWarningLevel());
					exporter.setCompilationLevel(getCompilationLevel());
					document.getClosure().accept(exporter, null);
					List<IStatus> statuses = new ArrayList<IStatus>();
					for (List<IStatus> iStatus : console.getErrors().values()) {
						statuses.addAll(iStatus);
					}
					DialogFactory.getStatusesDialog(bundle,
							IConstants.ExportCLIDialog_Result,
							IConstants.ExportCLIDialog_Result_Desc, statuses);
					stage.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	private void handleBrowseButtonAction(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle(bundle.getString(IConstants.ExportDialog_CLI));
		chooser.setInitialDirectory(document.getFile().getParentFile());
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

}
