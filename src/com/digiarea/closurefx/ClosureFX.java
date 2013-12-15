package com.digiarea.closurefx;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author norb
 */
public class ClosureFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(IConstants.ClosureFX);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"ClosureFX.fxml"));
		loader.setResources(ResourceBundle.getBundle(
				"com.digiarea.closurefx.locale.editor", Locale.ENGLISH));

		AnchorPane root = (AnchorPane) loader.load();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				ResourceUtils.getStylesheets().toExternalForm());
		stage.setScene(scene);

		ClosureFXController controller = loader.getController();
		controller.setStage(stage);

		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}