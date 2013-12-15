package com.digiarea.closurefx;

import java.net.URL;

import javafx.scene.image.Image;

public class ResourceUtils {

	public static final Image SIMPLE_FILE = new Image(
			ResourceUtils.class.getResourceAsStream("resources/file.png"));

	public static final Image SIMPLE_FOLDER = new Image(
			ResourceUtils.class.getResourceAsStream("resources/folder.png"));

	public static final Image HELP_HELP = new Image(
			ResourceUtils.class.getResourceAsStream("resources/help.png"));

	public static final Image HELP_BOOK = new Image(
			ResourceUtils.class.getResourceAsStream("resources/book.png"));

	/*
	 * Build Path Icons for JavaScript, Soy and Gss
	 */
	public static final Image BUILDPATH_PROJECT = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-project.png"));

	public static final Image BUILDPATH_PACKAGE_FOLDER = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-packagefolder.gif"));

	public static final Image BUILDPATH_LIBRARY = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-library.png"));

	public static final Image BUILDPATH_ARCHIVE = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-archive.png"));

	public static final Image BUILDPATH_JS = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-js.png"));

	public static final Image BUILDPATH_GSS = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-gss.png"));

	public static final Image BUILDPATH_SOY = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/buildpath-soy.png"));

	public static final Image CLOSURE_ICON = new Image(
			ResourceUtils.class.getResourceAsStream("resources/closure.png"));

	public static final Image MARK_ERROR = new Image(
			ResourceUtils.class.getResourceAsStream("resources/mark-error.png"));

	public static final Image MARK_WARNING = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/mark-exclamation.png"));

	public static final Image MARK_OFF = new Image(
			ResourceUtils.class.getResourceAsStream("resources/mark-off.png"));

	public static final Image MARK_DEFAULT = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/mark-default.png"));

	public static final Image DECORATOR_ERROR = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-error.png"));

	public static final Image DECORATOR_WARNING = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-warning.png"));

	public static final Image DECORATOR_INFO = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-info.png"));

	public static final Image DECORATOR_CLOSURE = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-closure.png"));

	public static final Image DECORATOR_EXTERN = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-extern.png"));

	public static final Image DECORATOR_JS = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/decorator-js.png"));

	public static final Image BUTTON_CLOSE = new Image(
			ResourceUtils.class.getResourceAsStream("resources/close.png"));

	public static final Image STATUS_WARNING = new Image(
			ResourceUtils.class
					.getResourceAsStream("resources/status-warning.png"));

	public static URL getStylesheets() {
		return ResourceUtils.class.getResource(IConstants.PACKAGE_THEMES
				+ "/default.css");
	}

}
