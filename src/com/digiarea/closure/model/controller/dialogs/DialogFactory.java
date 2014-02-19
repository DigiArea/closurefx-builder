package com.digiarea.closure.model.controller.dialogs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.digiarea.closure.model.entity.IPlaceholder;
import com.digiarea.closurefx.Document;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.validation.IStatus;

public class DialogFactory {
	
	public static ExportCLIDialogController getExportCLIDialog(ResourceBundle bundle,
			String title, String desc, Document document) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(title));
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("ExportCLIDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			ExportCLIDialogController controller = (ExportCLIDialogController) fxmlLoader
					.getController();
			controller.setBundle(bundle);
			controller.setDocument(document);
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static FolderDialogController getFolderDialog(ResourceBundle bundle,
			String title, String desc, File file, boolean allowFolders,
			boolean foldersOnly, String... extensions) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(title));
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("FolderDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			FolderDialogController controller = (FolderDialogController) fxmlLoader
					.getController();
			controller.setDesc(bundle.getString(desc));
			controller.setAllowFolders(allowFolders);
			controller.setFoldersOnly(foldersOnly);
			controller.setExtensions(extensions);
			controller.setInitialFile(file);
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void getAboutDialog(ResourceBundle bundle, String title) {
		Stage stage = new Stage();
		stage.setTitle(title);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UTILITY);
		stage.setResizable(false);
		stage.centerOnScreen();
		try {
			GridPane dialog = (GridPane) FXMLLoader
					.load(DialogFactory.class.getResource("AboutDialog.fxml"),
							bundle);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param placeholders
	 *            Placeholders model
	 * @param title
	 *            Stage title
	 * @param icon
	 *            Stage icon
	 * @param labelTitle
	 *            Dialog title
	 * @param labelDesc
	 *            Dialog icon
	 * @return controller for the dialog or null if FXML loading failed.
	 */
	public static Object getPlaceholderDialog(
			List<? extends IPlaceholder> placeholders, String title,
			Image icon, String labelTitle, String labelDesc) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(icon);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("PlaceholderDialog.fxml"));
			HBox dialog = (HBox) fxmlLoader.load();
			PlaceholderDialogController controller = (PlaceholderDialogController) fxmlLoader
					.getController();
			controller.setLabelTitle(labelTitle);
			controller.setLabelDesc(labelDesc);
			controller.setPlaceholders(placeholders);
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param title
	 *            Stage title
	 * @param icon
	 *            Stage icon
	 * @param labelTitle
	 *            Dialog title
	 * @param labelDesc
	 *            Dialog icon
	 * @return controller for the dialog or null if FXML loading failed.
	 */
	public static Object getLocaleDialog(String title, Image icon,
			String labelTitle, String labelDesc) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(icon);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("LocaleDialog.fxml"));
			HBox dialog = (HBox) fxmlLoader.load();
			LocaleDialogController controller = (LocaleDialogController) fxmlLoader
					.getController();
			controller.setLabelTitle(labelTitle);
			controller.setLabelDesc(labelDesc);
			controller.setLocales(Arrays.asList(SimpleDateFormat
					.getAvailableLocales()));
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param title
	 *            Stage title
	 * @param icon
	 *            Stage icon
	 * @param labelTitle
	 *            Dialog title
	 * @param labelDesc
	 *            Dialog icon
	 * @return controller for the dialog or null if FXML loading failed.
	 */
	public static Object getCharsetDialog(String title, Image icon,
			String labelTitle, String labelDesc) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(icon);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("CharsetDialog.fxml"));
			HBox dialog = (HBox) fxmlLoader.load();
			CharsetDialogController controller = (CharsetDialogController) fxmlLoader
					.getController();
			controller.setLabelTitle(labelTitle);
			controller.setLabelDesc(labelDesc);
			controller.setCharsets(getCharsets());
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<Charset> getCharsets() {
		List<Charset> cahrs = new ArrayList<Charset>();
		SortedMap<String, Charset> charsets = Charset.availableCharsets();
		Set<String> names = charsets.keySet();
		for (Iterator<String> e = names.iterator(); e.hasNext();) {
			String name = (String) e.next();
			cahrs.add((Charset) charsets.get(name));
		}
		return cahrs;
	}

	/**
	 * @return controller for the dialog or null if FXML loading failed.
	 */
	public static Object getSaveDialog(ResourceBundle bundle, String file) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(bundle.getString(IConstants.SaveDialog_Title));
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("SaveDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			SaveDialogController controller = (SaveDialogController) fxmlLoader
					.getController();
			controller.setStage(stage);
			controller.setBundle(bundle);
			controller.setFile(file);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return controller for the dialog or null if FXML loading failed.
	 */
	public static StatusDialogController getStatusDialog(ResourceBundle bundle,
			IStatus status, String title) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("StatusDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			StatusDialogController controller = (StatusDialogController) fxmlLoader
					.getController();
			controller.setStage(stage);
			controller.setStatus(status);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SelectClosureDialogController getSelectClosureDialog(
			ResourceBundle bundle, String title) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class.getResource("SelectClosureDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			SelectClosureDialogController controller = (SelectClosureDialogController) fxmlLoader
					.getController();
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SelectVariableDialogController getSelectVariableDialog(
			ResourceBundle bundle, String title) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);
		stage.setTitle(title);
		stage.centerOnScreen();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(
					DialogFactory.class
							.getResource("SelectVariableDialog.fxml"));
			fxmlLoader.setResources(bundle);
			VBox dialog = (VBox) fxmlLoader.load();
			SelectVariableDialogController controller = (SelectVariableDialogController) fxmlLoader
					.getController();
			controller.setStage(stage);
			Scene dialogScene = new Scene(dialog);
			dialogScene.getStylesheets().add(
					ResourceUtils.getStylesheets().toExternalForm());
			stage.setScene(dialogScene);
			stage.showAndWait();
			return controller;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
