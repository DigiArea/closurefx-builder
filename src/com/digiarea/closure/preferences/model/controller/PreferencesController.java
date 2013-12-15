package com.digiarea.closure.preferences.model.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import com.digiarea.closure.preferences.model.bind.ModelFacade;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class PreferencesController extends ClosurePreferencesController
		implements Initializable {

	@FXML
	private TabPane tabs;

	@FXML
	private Tab closures;

	@FXML
	private Tab variables;

	@FXML
	private Tab editors;

	public PreferencesController(ModelFacade modelFacade, ResourceBundle bundle) {
		super(modelFacade, bundle);
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	public TabPane getTabs() {
		return tabs;
	}

	public void selectClosuresPage() {
		tabs.getSelectionModel().select(closures);
	}

	public void selectEditorsPage() {
		tabs.getSelectionModel().select(editors);
	}

	public void selectVariablesPage() {
		tabs.getSelectionModel().select(variables);
	}

}
