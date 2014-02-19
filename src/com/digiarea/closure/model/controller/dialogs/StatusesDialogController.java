package com.digiarea.closure.model.controller.dialogs;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.digiarea.closurefx.ResourceUtils;
import com.digiarea.closurefx.build.validation.IStatus;

/**
 * FXML Controller class
 * 
 * @author daginno
 */
public class StatusesDialogController implements Initializable {

	private Stage stage;

	@FXML
	private ListView<IStatus> controlList;

	@FXML
	private Label controlDesc;

	private ObservableList<IStatus> statuses;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.statuses = FXCollections.observableArrayList();
		controlList.setItems(statuses);
		controlList
				.setCellFactory(new Callback<ListView<IStatus>, ListCell<IStatus>>() {

					@Override
					public ListCell<IStatus> call(ListView<IStatus> list) {
						return new StatusesDialogController.StatusCell();
					}
				});
	}

	@FXML
	private void handleOkButtonAction(ActionEvent event) {
		stage.close();
	}

	public void setDescription(String desc) {
		this.controlDesc.setText(desc);
	}

	public void setStatuses(List<IStatus> statuses) {
		this.statuses.addAll(statuses);
	}

	static class StatusCell extends ListCell<IStatus> {

		public void updateItem(IStatus item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
			}
			if (item != null) {
				switch (item.getSeverity()) {
				case ERROR:
					setGraphic(new ImageView(ResourceUtils.MARK_ERROR));
					break;
				case WARNING:
					setGraphic(new ImageView(ResourceUtils.MARK_WARNING));
					break;
				default:
					setGraphic(new ImageView(ResourceUtils.MARK_OFF));
					break;
				}
				setText(item.getMessage());
			}
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
