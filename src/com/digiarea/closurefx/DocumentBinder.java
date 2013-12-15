package com.digiarea.closurefx;

import java.io.File;

import com.digiarea.closure.model.controller.GSSLibrariesSectionController;
import com.digiarea.closure.model.controller.GSSOutputSectionController;
import com.digiarea.closure.model.controller.GSSRenamingSectionController;
import com.digiarea.closure.model.controller.GSSSourceSectionController;
import com.digiarea.closure.model.controller.JSExportsSectionController;
import com.digiarea.closure.model.controller.JSLibrariesSectionController;
import com.digiarea.closure.model.controller.JSOutputSectionController;
import com.digiarea.closure.model.controller.JSRenamingSectionController;
import com.digiarea.closure.model.controller.JSSourceMapSectionController;
import com.digiarea.closure.model.controller.JSSourceSectionController;
import com.digiarea.closure.model.controller.JSTranslationSectionController;
import com.digiarea.closure.model.controller.SOYLibrariesSectionController;
import com.digiarea.closure.model.controller.SOYLocalizationSectionController;
import com.digiarea.closure.model.controller.SOYOptionsSectionController;
import com.digiarea.closure.model.controller.SOYOutputSectionController;
import com.digiarea.closure.model.controller.SOYSourceSectionController;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

public class DocumentBinder {

	private Callback<Class<?>, Object> factory = null;
	private Document document;

	public DocumentBinder(Callback<Class<?>, Object> factory, Document document) {
		this.factory = factory;
		this.document = document;
	}

	public void bind() {
		JSSourceSectionController jSSourceSectionController = (JSSourceSectionController) factory
				.call(JSSourceSectionController.class);
		bindBuild(document.fileProperty(), jSSourceSectionController
				.getBtnAdd().disableProperty());
		JSLibrariesSectionController jsLibrariesSectionController = (JSLibrariesSectionController) factory
				.call(JSLibrariesSectionController.class);
		bindBuild(document.fileProperty(), jsLibrariesSectionController
				.getBtnFile().disableProperty());
		bindBuild(document.fileProperty(), jsLibrariesSectionController
				.getBtnFolder().disableProperty());
		JSExportsSectionController jsExportsSectionController = (JSExportsSectionController) factory
				.call(JSExportsSectionController.class);
		bindBuild(document.fileProperty(), jsExportsSectionController
				.getBtnBrowse().disableProperty());
		JSOutputSectionController jsOutputSectionController = (JSOutputSectionController) factory
				.call(JSOutputSectionController.class);
		bindBuild(document.fileProperty(), jsOutputSectionController
				.getBtnBrowse().disableProperty());

		JSRenamingSectionController jsRenamingSectionController = (JSRenamingSectionController) factory
				.call(JSRenamingSectionController.class);
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnFunctionInput().disableProperty());
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnFunctionOutput().disableProperty());
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnPropertyInput().disableProperty());
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnPropertyOutput().disableProperty());
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnVariableInput().disableProperty());
		bindBuild(document.fileProperty(), jsRenamingSectionController
				.getBtnVariableOutput().disableProperty());

		JSSourceMapSectionController jsSourceMapSectionController = (JSSourceMapSectionController) factory
				.call(JSSourceMapSectionController.class);
		bindBuild(document.fileProperty(), jsSourceMapSectionController
				.getBtnBrowse().disableProperty());

		JSTranslationSectionController jsTranslationSectionController = (JSTranslationSectionController) factory
				.call(JSTranslationSectionController.class);
		bindBuild(document.fileProperty(), jsTranslationSectionController
				.getBtnBrowse().disableProperty());

		GSSSourceSectionController gssSourceSectionController = (GSSSourceSectionController) factory
				.call(GSSSourceSectionController.class);
		bindBuild(document.fileProperty(), gssSourceSectionController
				.getBtnAdd().disableProperty());
		GSSLibrariesSectionController gssLibrariesSectionController = (GSSLibrariesSectionController) factory
				.call(GSSLibrariesSectionController.class);
		bindBuild(document.fileProperty(), gssLibrariesSectionController
				.getBtnFile().disableProperty());
		bindBuild(document.fileProperty(), gssLibrariesSectionController
				.getBtnFolder().disableProperty());

		GSSRenamingSectionController gssRenamingSectionController = (GSSRenamingSectionController) factory
				.call(GSSRenamingSectionController.class);
		bindBuild(document.fileProperty(), gssRenamingSectionController
				.getBtnBrowse().disableProperty());

		GSSOutputSectionController gssOutputSectionController = (GSSOutputSectionController) factory
				.call(GSSOutputSectionController.class);
		bindBuild(document.fileProperty(), gssOutputSectionController
				.getBtnBrowse().disableProperty());

		SOYSourceSectionController soySourceSectionController = (SOYSourceSectionController) factory
				.call(SOYSourceSectionController.class);
		bindBuild(document.fileProperty(), soySourceSectionController
				.getBtnAdd().disableProperty());
		SOYLibrariesSectionController soyLibrariesSectionController = (SOYLibrariesSectionController) factory
				.call(SOYLibrariesSectionController.class);
		bindBuild(document.fileProperty(), soyLibrariesSectionController
				.getBtnFile().disableProperty());
		bindBuild(document.fileProperty(), soyLibrariesSectionController
				.getBtnFolder().disableProperty());

		SOYOutputSectionController soyOutputSectionController = (SOYOutputSectionController) factory
				.call(SOYOutputSectionController.class);
		bindBuild(document.fileProperty(), soyOutputSectionController
				.getBtnBrowse().disableProperty());

		SOYLocalizationSectionController soyLocalizationSectionController = (SOYLocalizationSectionController) factory
				.call(SOYLocalizationSectionController.class);
		bindBuild(document.fileProperty(), soyLocalizationSectionController
				.getBtnBrowse().disableProperty());

		SOYOptionsSectionController soyOptionsSectionController = (SOYOptionsSectionController) factory
				.call(SOYOptionsSectionController.class);
		bindBuild(document.fileProperty(), soyOptionsSectionController
				.getBtnBrowse().disableProperty());
	}

	public static void bindBuild(final ObjectProperty<File> fileProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(fileProperty.get() == null);
		fileProperty.addListener(new ChangeListener<File>() {
			@Override
			public void changed(ObservableValue<? extends File> observable,
					File oldValue, File newValue) {
				disableProperty.set(fileProperty.get() == null);
			}
		});
	}

}
