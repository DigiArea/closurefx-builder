package com.digiarea.closure.help.model.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.digiarea.closurefx.ResourceUtils;

public class HelpFactory {

	public static HelpController getHelp(ResourceBundle bundle, String title) {
		return getHelp(bundle, title, null, null);
	}

	public static HelpController getHelp(ResourceBundle bundle, String title,
			URL url, String link) {
		Stage stage = new Stage();
		stage.initModality(Modality.NONE);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(title));
		stage.setFocused(true);
		stage.centerOnScreen();
		try {
			FXMLLoader loader = new FXMLLoader(
					HelpFactory.class.getResource("Help.fxml"), bundle);
			VBox dialog = (VBox) loader.load();
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			HelpController controller = loader.getController();
			controller.setStage(stage);
			controller.setInitialURL(url, link);
			stage.show();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
