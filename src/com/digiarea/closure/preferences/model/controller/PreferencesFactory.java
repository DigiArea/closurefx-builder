package com.digiarea.closure.preferences.model.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.digiarea.closure.preferences.model.Preferences;
import com.digiarea.closure.preferences.model.bind.Binder;
import com.digiarea.closure.preferences.model.bind.ControllerFactory;
import com.digiarea.closure.preferences.model.bind.ModelFacade;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closure.preferences.model.controller.dialogs.AddClosureLibraryDialogController;
import com.digiarea.closure.preferences.model.controller.dialogs.AddVariableDialogController;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;

public class PreferencesFactory {

	public static PreferencesController getPreferenceDialog(
			ResourceBundle bundle) {
		PreferencesSerializer serializer = new PreferencesSerializer();
		Preferences prefs = serializer.read();
		ModelFacade modelFacade = new ModelFacade();
		ControllerFactory factory = new ControllerFactory(modelFacade, bundle);
		modelFacade.setFactory(factory);
		modelFacade.setPrefs(prefs);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle("Closure FX Preferences");
		stage.centerOnScreen();
		try {
			FXMLLoader loader = new FXMLLoader(
					PreferencesFactory.class.getResource("Preferences.fxml"));
			loader.setResources(bundle);
			loader.setControllerFactory(factory);
			VBox editor = (VBox) loader.load();
			PreferencesController controller = (PreferencesController) loader
					.getController();
			Binder binder = new Binder(factory);
			prefs.accept(binder, null);
			Scene dialogScene = new Scene(editor);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static AddVariableDialogController getAddVariableDialog(
			ResourceBundle bundle, Preferences prefs) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(IConstants.PreferencesVariables_Add));
		stage.centerOnScreen();
		try {
			FXMLLoader loader = new FXMLLoader(
					PreferencesFactory.class
							.getResource(IConstants.PACKAGE_CLOSURE
									+ "/preferences/model/controller/dialogs/AddVariableDialog.fxml"));
			loader.setResources(bundle);
			VBox editor = (VBox) loader.load();
			AddVariableDialogController controller = (AddVariableDialogController) loader
					.getController();
			controller.setStage(stage);
			controller.setVariables(prefs.getVariables());
			Scene dialogScene = new Scene(editor);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static AddClosureLibraryDialogController getAddClosureLibraryDialog(
			ResourceBundle bundle, Preferences prefs) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(IConstants.PreferencesClosure_Add));
		stage.centerOnScreen();
		try {
			FXMLLoader loader = new FXMLLoader(
					PreferencesFactory.class
							.getResource(IConstants.PACKAGE_CLOSURE
									+ "/preferences/model/controller/dialogs/AddClosureLibraryDialog.fxml"));
			loader.setResources(bundle);
			VBox editor = (VBox) loader.load();
			AddClosureLibraryDialogController controller = (AddClosureLibraryDialogController) loader
					.getController();
			controller.setStage(stage);
			controller.setLibraries(prefs.getClosureLibraries());
			Scene dialogScene = new Scene(editor);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
