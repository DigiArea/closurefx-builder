package com.digiarea.closurefx;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import com.digiarea.closurefx.build.validation.IStatus;

public class TooltipManager {

	private VBox parent;

	public TooltipManager(VBox parent) {
		this.parent = parent;
	}

	public void addTooltip(IStatus status) {
		HBox box = create(status);
		if (parent != null) {
			parent.getChildren().add(box);
		}
	}

	private HBox create(IStatus status) {
		final HBox box = new HBox();
		box.setSpacing(5);
		box.setAlignment(Pos.CENTER_LEFT);
		switch (status.getSeverity()) {
		case CANCEL:
			break;
		case DEFAULT:
			break;
		case ERROR:
			box.getStyleClass().add(IConstants.CSS_ERROR_BOX);
			break;
		case INFO:
			box.getStyleClass().add(IConstants.CSS_INFO_BOX);
			break;
		case NO:
			break;
		case OFF:
			break;
		case OK:
			break;
		case WARNING:
			box.getStyleClass().add(IConstants.CSS_WARNING_BOX);
			break;
		default:
			break;
		}
		// final Button remove = new Button();
		// remove.setGraphic(new ImageView(ResourceUtils.BUTTON_CLOSE));
		// remove.getStyleClass().add(IConstants.CSS_INVISIBLE_BUTTON);
		final Label msg = new Label(status.getMessage());
		// remove.setOnAction(new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent event) {
		// disappear(box);
		// }
		// });
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				if (box != null) {
					disappear(box);
				}
			}
		}, 2000);
		// box.getChildren().add(remove);
		box.getChildren().add(msg);
		return box;
	}

	private void disappear(final Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(500), node);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (parent != null) {
					parent.getChildren().remove(node);
				}
			}
		});
	}

}
