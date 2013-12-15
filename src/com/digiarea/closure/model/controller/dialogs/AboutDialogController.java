package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import com.digiarea.closurefx.IConstants;
import com.google.javascript.jscomp.Compiler;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class AboutDialogController implements Initializable {

	@FXML
	private TextArea controlAbout;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		controlAbout.setText(MessageFormat.format(
				rb.getString(IConstants.About_Desc),
				Compiler.getReleaseVersion(), Compiler.getReleaseDate()));
	}
}
