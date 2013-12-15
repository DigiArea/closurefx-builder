package com.digiarea.closure.model.controller.dialogs;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class FolderDialogController implements Initializable {

	private Filewalker filewalker;
	private IStatus status = Status.CANCEL_STATUS;

	private Stage stage;
	private File initialFile;
	private List<String> extensions = new ArrayList<String>();
	private boolean allowFolders = true;
	private boolean foldersOnly = false;

	@FXML
	private TreeView<File> controlTree;
	@FXML
	private Label controlStatus;
	@FXML
	private Button controlOk;
	@FXML
	private Button controlNew;
	private TreeItem<File> root;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		filewalker = new Filewalker();
		controlTree.setShowRoot(false);
		controlTree.setEditable(true);
		controlTree
				.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {
					@Override
					public TreeCell<File> call(TreeView<File> p) {
						return new FolderCell();
					}
				});
		controlTree.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<TreeItem<File>>() {
					@Override
					public void changed(
							ObservableValue<? extends TreeItem<File>> paramObservableValue,
							TreeItem<File> paramT1, TreeItem<File> paramT2) {
						if (paramT2 != null) {
							controlOk.setDisable(paramT2.getValue()
									.isDirectory() && !allowFolders);
						}
					}
				});
	}

	public void setDesc(String desc) {
		controlStatus.setText(desc);
	}

	public void setFoldersOnly(boolean foldersOnly) {
		this.foldersOnly = foldersOnly;
	}

	public void setAllowFolders(boolean allowFolders) {
		this.allowFolders = allowFolders;
	}

	public void setExtensions(String... extensions) {
		if (extensions != null) {
			this.extensions = Arrays.asList(extensions);
		}
	}

	private class FolderCell extends TreeCell<File> {

		private TextField textField;

		public FolderCell() {
		}

		@Override
		public void startEdit() {
			if (!isEditable()) {
				return;
			}
			if (!isEmpty()) {
				super.startEdit();
				createTextField();
				setText(null);
				setGraphic(textField);
				textField.selectAll();
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setText(getString());
			setGraphic(getGraphics());
		}

		@Override
		public void updateItem(File item, boolean empty) {
			super.updateItem(item, empty);
			if (isEmpty() || item == null) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (textField != null) {
						textField.setText(getString());
					}
					setText(null);
					setGraphic(textField);
				} else {
					setText(getString());
					setGraphic(getGraphics());
				}
			}
		}

		private void createTextField() {
			textField = new TextField(getString());
			textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()
					* 2);
			textField.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> arg0,
								Boolean arg1, Boolean arg2) {
							File parentFile = getItem().getParentFile();
							if (!getItem().getName()
									.equals(textField.getText())) {
								File newFile = new File(parentFile, textField
										.getText());
								if (getItem().renameTo(newFile)) {
									commitEdit(newFile);
								} else {
									commitEdit(getItem());
								}
							}
						}
					});
		}

		private ImageView getGraphics() {
			if (getItem().isDirectory()) {
				return new ImageView(ResourceUtils.SIMPLE_FOLDER);
			} else {
				if (getItem().getAbsolutePath().endsWith(
						IConstants.EXTENSION_JS)) {
					return new ImageView(ResourceUtils.BUILDPATH_JS);
				} else if (getItem().getAbsolutePath().endsWith(
						IConstants.EXTENSION_CSS)
						|| getItem().getAbsolutePath().endsWith(
								IConstants.EXTENSION_GSS)) {
					return new ImageView(ResourceUtils.BUILDPATH_GSS);
				} else if (getItem().getAbsolutePath().endsWith(
						IConstants.EXTENSION_SOY)) {
					return new ImageView(ResourceUtils.BUILDPATH_SOY);
				}

			}
			return new ImageView(ResourceUtils.SIMPLE_FILE);
		}

		private String getString() {
			return new com.digiarea.closure.core.Path(getItem()
					.getAbsolutePath()).lastSegment();
		}

	}

	@FXML
	private void handleNewButtonAction(ActionEvent event) {
		TreeItem<File> selectedItem = controlTree.getSelectionModel()
				.getSelectedItem();
		if (selectedItem != null) {
			File file = selectedItem.getValue();
			if (file != null) {
				if (!file.isDirectory()) {
					file = file.getParentFile();
				}
				File newFile = new File(file, "NewFolder");
				TreeItem<File> newFileTree = new TreeItem<File>(newFile);
				if (!newFile.isDirectory() || !newFile.exists()) {
					newFile.mkdir();
					selectedItem.getChildren().add(newFileTree);
					controlTree.getSelectionModel().select(newFileTree);
					controlTree.edit(newFileTree);
				} else {
					controlTree.getSelectionModel().select(newFileTree);
				}
			}
		}
	}

	@FXML
	private void handleRefreshButtonAction(ActionEvent event) {
		load();
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

	public void setInitialFile(File initialFile) {
		this.initialFile = initialFile;
		load();
	}

	private void load() {
		root = new TreeItem<File>(this.initialFile);
		filewalker.walk(this.initialFile.toPath(), root);
		controlTree.setRoot(root);
	}

	public File getSelectedFile() {
		if (controlTree.getSelectionModel().getSelectedItem() != null) {
			return controlTree.getSelectionModel().getSelectedItem().getValue();
		}
		return null;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public IStatus getStatus() {
		return status;
	}

	private class Filewalker {

		public Filewalker() {
		}

		public void walk(Path path, TreeItem<File> tree) {
			File root = new File(path.toString());
			File[] list = root.listFiles();

			for (File f : list) {
				TreeItem<File> currentRoot = new TreeItem<File>(f);
				if (f.isDirectory()) {
					tree.getChildren().add(currentRoot);
					walk(f.toPath(), currentRoot);
				} else {
					if (!foldersOnly
							&& (extensions.isEmpty() || extensions
									.contains(new com.digiarea.closure.core.Path(
											f.getAbsolutePath())
											.getFileExtension()))) {
						tree.getChildren().add(currentRoot);
					}
				}
			}
		}
	}

}
