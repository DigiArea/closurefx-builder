package com.digiarea.closure.model.providers;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.Status;

public class ConsoleCellFactory implements
		Callback<ListView<Status>, ListCell<Status>> {

	@Override
	public ListCell<Status> call(ListView<Status> list) {
		return new ConsoleListCell();
	}

	public class ConsoleListCell extends ListCell<Status> {

		public ConsoleListCell() {
		}

		@Override
		public void updateItem(Status item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				Label label = new Label(item.getMessage());
				if (item.getException() != null) {
					label.setText(label.getText() + "\n"
							+ item.getExceptionMessage());
				}
				switch (item.getSeverity()) {
				case CANCEL:
					label.getStyleClass().add(IConstants.CSS_STATUS_OFF);
					break;
				case DEFAULT:
					label.getStyleClass().add(IConstants.CSS_STATUS_OFF);
					break;
				case ERROR:
					label.getStyleClass().add(IConstants.CSS_STATUS_ERROR);
					break;
				case INFO:
					label.getStyleClass().add(IConstants.CSS_STATUS_INFO);
					break;
				case OFF:
					label.getStyleClass().add(IConstants.CSS_STATUS_OFF);
					break;
				case OK:
					label.getStyleClass().add(IConstants.CSS_STATUS_OFF);
					break;
				case WARNING:
					label.getStyleClass().add(IConstants.CSS_STATUS_WARNING);
					break;
				case NO:
					label.getStyleClass().add(IConstants.CSS_STATUS_OFF);
					break;
				}
				setGraphic(label);
			}
		}

	}

}
