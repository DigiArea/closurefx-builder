package com.digiarea.closure.model.providers;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.ResourceUtils;

public class BuildpathCell extends ListCell<Source> implements IConstants {

	private List<SourceEntry> entry;
	private IPathResolver pathResolver;

	private ResourceBundle bundle;

	public BuildpathCell(ResourceBundle bundle, IPathResolver pathResolver,
			SourceEntry... entry) {
		this.entry = Arrays.asList(entry);
		this.bundle = bundle;
		this.pathResolver = pathResolver;
	}

	public void updateItem(Source item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
			setText(null);
		}
		if (item != null) {
			ObservableList<String> classes = getStyleClass();
			classes.removeAll(CSS_BUILDPATH_LIBRARY,
					CSS_BUILDPATH_LIBRARY_ERROR, CSS_BUILDPATH_LIBRARY_EXTERN,
					CSS_BUILDPATH_GSS, CSS_BUILDPATH_GSS_ERROR,
					CSS_BUILDPATH_GSS_EXTERN, CSS_BUILDPATH_JS,
					CSS_BUILDPATH_JS_ERROR, CSS_BUILDPATH_JS_EXTERN,
					CSS_BUILDPATH_SOY, CSS_BUILDPATH_SOY_ERROR,
					CSS_BUILDPATH_SOY_EXTERN, CSS_BUILDPATH_PROJECT,
					CSS_BUILDPATH_PACKAGE_FOLDER,
					CSS_BUILDPATH_PACKAGE_FOLDER_ERROR,
					CSS_BUILDPATH_PACKAGE_FOLDER_EXTERN,
					CSS_BUILDPATH_VARIABLE, CSS_BUILDPATH_VARIABLE_ERROR,
					CSS_BUILDPATH_CLOSURE, CSS_BUILDPATH_CLOSURE_ERROR,
					CSS_BUILDPATH_VARIABLE, CSS_BUILDPATH_VARIABLE_ERROR);
			if (entry != null && !entry.isEmpty()
					&& !entry.contains(item.getEntryKind())) {
				setPrefHeight(0);
				setPrefWidth(0);
				setVisible(false);
				setText(null);
				setGraphic(null);
				setTooltip(null);
			} else {
				File realFile = new File(
						pathResolver.toRealPath(item.getPath()));
				boolean exist = realFile.exists();
				if (!exist) {
					setTooltip(new Tooltip(
							bundle.getString(BUNDLE_JS_SOURCE_EXIST)));
				}
				setPrefHeight(getMinHeight());
				setPrefWidth(getMaxWidth());
				setVisible(true);

				String tooltip = "[" + realFile.getAbsolutePath() + "]";

				HBox line = new HBox();
				line.setSpacing(5);
				line.setAlignment(Pos.CENTER_LEFT);

				Label nameLabel = new Label(new File(item.getPath()).getName());
				line.getChildren().add(nameLabel);

				Label label = new Label(item.getPath());
				label.setTextFill(Color.GRAY);
				line.getChildren().add(label);

				if (item.isExtern()) {
					switch (item.getEntityKind()) {
					case GSS:
						break;
					case SOY:
					case JSC:
						tooltip = tooltip + " [" + "extern" + "]";
						line.getChildren().add(
								new ImageView(ResourceUtils.DECORATOR_EXTERN));
						break;
					}
				}

				if (item.isIncludeClosure()) {
					switch (item.getEntityKind()) {
					case GSS:
					case SOY:
						break;
					case JSC:
						tooltip = tooltip + " ["
								+ "included Closure-based files" + "]";
						line.getChildren().add(
								new ImageView(ResourceUtils.DECORATOR_CLOSURE));
						break;
					}
				}

				if (item.isIncludeSimple()) {
					switch (item.getEntityKind()) {
					case GSS:
					case SOY:
						break;
					case JSC:
						tooltip = tooltip + " [" + "included JavaScript files"
								+ "]";
						line.getChildren().add(
								new ImageView(ResourceUtils.DECORATOR_JS));
						break;
					}
				}

				setTooltip(new Tooltip(tooltip));
				setGraphic(line);

				switch (item.getEntryKind()) {
				case CONTAINER:
					if (!exist) {
						classes.add(CSS_BUILDPATH_LIBRARY_ERROR);
					} else {
						classes.add(CSS_BUILDPATH_LIBRARY);
					}
					break;
				case FILE:
					switch (item.getEntityKind()) {
					case GSS:
						if (!exist) {
							classes.add(CSS_BUILDPATH_GSS_ERROR);
						} else {
							classes.add(CSS_BUILDPATH_GSS);
						}
						break;
					case JSC:
						if (!exist) {
							classes.add(CSS_BUILDPATH_JS_ERROR);
						} else {
							classes.add(CSS_BUILDPATH_JS);
						}
						break;
					case SOY:
						if (!exist) {
							classes.add(CSS_BUILDPATH_SOY_ERROR);
						} else {
							classes.add(CSS_BUILDPATH_SOY);
						}
						break;
					}
					break;
				case LIBRARY:
					if (!exist) {
						classes.add(CSS_BUILDPATH_LIBRARY_ERROR);
					} else {
						classes.add(CSS_BUILDPATH_LIBRARY);
					}
					break;
				case PROJECT:
					classes.add(CSS_BUILDPATH_PROJECT);
					label.setText("- not supported");
					setTooltip(new Tooltip("Projects are not supported by Closure FX Builder."));
					label.setTextFill(Color.RED);
					label.setFont(Font.font("Arial", FontPosture.ITALIC, 11));
					break;
				case SOURCE:
					if (!exist) {
						classes.add(CSS_BUILDPATH_PACKAGE_FOLDER_ERROR);
					} else {
						classes.add(CSS_BUILDPATH_PACKAGE_FOLDER);
					}
					break;
				case VARIABLE:
					Variable variable = new PreferencesSerializer()
							.readVariable(item.getPath());
					if (variable == null) {
						classes.add(CSS_BUILDPATH_VARIABLE_ERROR);
						setTooltip(new Tooltip(
								bundle.getString(JSValidator_Variable_Exist)));
						label.setText("???");
					} else {
						label.setText(realFile.getAbsolutePath());
						if (!exist) {
							classes.add(CSS_BUILDPATH_VARIABLE_ERROR);
						} else {
							classes.add(CSS_BUILDPATH_VARIABLE);
						}
					}
					break;
				case CLOSURE:
					ClosureLibrary library = new PreferencesSerializer()
							.readLibrary(item.getPath());
					if (library == null) {
						classes.add(CSS_BUILDPATH_CLOSURE_ERROR);
						setTooltip(new Tooltip(
								bundle.getString(BUNDLE_JS_CLOSURE_EXIST)));
						label.setText("???");
					} else {
						label.setText(realFile.getAbsolutePath());
						if (!exist) {
							classes.add(CSS_BUILDPATH_CLOSURE_ERROR);
						} else {
							classes.add(CSS_BUILDPATH_CLOSURE);
						}
					}
					break;
				}
			}
		}
	}
}
