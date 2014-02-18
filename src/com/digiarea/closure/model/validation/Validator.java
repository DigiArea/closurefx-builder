package com.digiarea.closure.model.validation;

import java.io.File;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.util.Callback;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.ExcludeInputFilter;
import com.digiarea.closure.model.GssAtRule;
import com.digiarea.closure.model.GssAtRules;
import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.GssDefines;
import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssExcludedClasses;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssNonStandardFunctions;
import com.digiarea.closure.model.GssOptimizationLevel;
import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.GssUnrecognizeProperties;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.IncludeInputFilter;
import com.digiarea.closure.model.Info;
import com.digiarea.closure.model.InputFilterPattern;
import com.digiarea.closure.model.InputFilterType;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;
import com.digiarea.closure.model.JsDefines;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsDocs;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsRenaming;
import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.JsSourceMapFormat;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Language;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SoyCodeStyle;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.WarningType;
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.controller.GSSLibrariesSectionController;
import com.digiarea.closure.model.controller.GSSOutputSectionController;
import com.digiarea.closure.model.controller.GSSPageController;
import com.digiarea.closure.model.controller.GSSRenamingSectionController;
import com.digiarea.closure.model.controller.GSSSourceSectionController;
import com.digiarea.closure.model.controller.JSExportsSectionController;
import com.digiarea.closure.model.controller.JSLanguageSectionController;
import com.digiarea.closure.model.controller.JSLibrariesSectionController;
import com.digiarea.closure.model.controller.JSOutputSectionController;
import com.digiarea.closure.model.controller.JSPageController;
import com.digiarea.closure.model.controller.JSRenamingSectionController;
import com.digiarea.closure.model.controller.JSSourceMapSectionController;
import com.digiarea.closure.model.controller.JSSourceSectionController;
import com.digiarea.closure.model.controller.JSTranslationSectionController;
import com.digiarea.closure.model.controller.SOYLibrariesSectionController;
import com.digiarea.closure.model.controller.SOYLocalizationSectionController;
import com.digiarea.closure.model.controller.SOYOutputSectionController;
import com.digiarea.closure.model.controller.SOYPageController;
import com.digiarea.closure.model.controller.SOYSourceSectionController;
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;

public class Validator extends VoidVisitorAdapter<Object> {

	private ResourceBundle bundle;
	private Callback<Class<?>, Object> factory;
	private IMessageManager manager;

	public static String JSCategoryWarning = "JSCategoryWarning";
	public static String JSCategoryError = "JSCategoryError";

	public static String GSSCategoryWarning = "GSSCategoryWarning";
	public static String GSSCategoryError = "GSSCategoryError";

	public static String SOYCategoryWarning = "SOYCategoryWarning";
	public static String SOYCategoryError = "SOYCategoryError";

	public static String JSValidator_Source_Empty = "JSValidator_Source_Empty";
	public static String JSValidator_Source_Invalid = "JSValidator_Source_Invalid";

	public static String JSValidator_Output_EmptyPath = "JSValidator_Output_EmptyPath";
	public static String JSValidator_Output_EmptyFile = "JSValidator_Output_EmptyFile";

	public static String SOYValidator_Source_Empty = "SOYValidator_Source_Empty";
	public static String SOYValidator_Source_Invalid = "SOYValidator_Source_Invalid";

	public static String GSSValidator_Source_Empty = "GSSValidator_Source_Empty";
	public static String GSSValidator_Source_Invalid = "GSSValidator_Source_Invalid";
	public static String GSSValidator_Output_EmptyPath = "GSSValidator_Output_EmptyPath";
	public static String GSSValidator_Output_EmptyFile = "GSSValidator_Output_EmptyPath";

	public static String JSValidator_ExternExports_Empty = "JSValidator_ExternExports_Empty";
	public static String JSValidator_ExternExports_Exist = "JSValidator_ExternExports_Exist";
	public static String JSValidator_Charset = "JSValidator_Charset";
	public static String JSValidator_VariableRenamingInput_Exist = "JSValidator_VariableRenamingInput_Exist";
	public static String JSValidator_PropertyRenamingInput_Exist = "JSValidator_PropertyRenamingInput_Exist";
	public static String JSValidator_FunctionRenamingInput_Exist = "JSValidator_FunctionRenamingInput_Exist";
	public static String JSValidator_SourceMap_Exist = "JSValidator_SourceMap_Exist";
	public static String JSValidator_TranslationFile_Exist = "JSValidator_TranslationFile_Exist";
	public static String JSValidator_RenamingMap_Empty = "JSValidator_RenamingMap_Empty";
	public static String JSValidator_RenamingMap_Exist = "JSValidator_RenamingMap_Exist";
	public static String SOYValidator_MessageFile_Empty = "SOYValidator_MessageFile_Empty";
	public static String SOYValidator_MessageFile_Exist = "SOYValidator_MessageFile_Exist";
	public static String SOYValidator_Ouput_Empty = "SOYValidator_Ouput_Empty";

	@Override
	public void visit(final Buildpath n, final Object ctx) throws Exception {
		super.visit(n, ctx);
		validate(n, ctx);

		n.sourceProperty().addListener(new ListChangeListener<Source>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Source> c) {
				validate(n, ctx);
			}
		});
	}

	private void validate(Buildpath n, Object ctx) {
		if (ctx instanceof ClosureJs) {
			JSSourceSectionController jSSourceSectionController = (JSSourceSectionController) factory
					.call(JSSourceSectionController.class);
			JSLibrariesSectionController jsLibrariesSectionController = (JSLibrariesSectionController) factory
					.call(JSLibrariesSectionController.class);
			manager.removeMessages(jSSourceSectionController.getControlSource());
			manager.removeMessages(jsLibrariesSectionController
					.getControlSource());
			if (n.getSource() == null || n.getSource().isEmpty()) {
				manager.addMessage(JSCategoryWarning, JSValidator_Source_Empty,
						bundle.getString(JSValidator_Source_Empty), null,
						IMessageProvider.WARNING,
						jSSourceSectionController.getControlSource());
			} else {
				for (Source source : n.getSource()) {
					if (!validate(source, ctx)) {
						switch (source.getEntryKind()) {
						case CONTAINER:
						case FILE:
						case LIBRARY:
						case PROJECT:
						case VARIABLE:
						case CLOSURE:
							manager.addMessage(
									JSCategoryError,
									JSValidator_Source_Invalid,
									bundle.getString(JSValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									jsLibrariesSectionController
											.getControlSource());
							break;
						case SOURCE:
							manager.addMessage(
									JSCategoryError,
									JSValidator_Source_Invalid,
									bundle.getString(JSValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									jSSourceSectionController
											.getControlSource());
							break;
						}
					}
				}
			}
		} else if (ctx instanceof ClosureSoy) {
			SOYSourceSectionController sOYSSourceSectionController = (SOYSourceSectionController) factory
					.call(SOYSourceSectionController.class);
			SOYLibrariesSectionController soyLibrariesSectionController = (SOYLibrariesSectionController) factory
					.call(SOYLibrariesSectionController.class);
			manager.removeMessages(sOYSSourceSectionController
					.getControlSource());
			manager.removeMessages(soyLibrariesSectionController
					.getControlSource());
			if (n.getSource() == null || n.getSource().isEmpty()) {
				manager.addMessage(SOYCategoryWarning,
						SOYValidator_Source_Empty,
						bundle.getString(SOYValidator_Source_Empty), null,
						IMessageProvider.WARNING,
						sOYSSourceSectionController.getControlSource());
			} else {
				for (Source source : n.getSource()) {
					if (!validate(source, ctx)) {
						switch (source.getEntryKind()) {
						case CONTAINER:
						case FILE:
						case LIBRARY:
						case PROJECT:
						case VARIABLE:
						case CLOSURE:
							manager.addMessage(
									SOYCategoryWarning,
									SOYValidator_Source_Invalid,
									bundle.getString(SOYValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									soyLibrariesSectionController
											.getControlSource());
							break;
						case SOURCE:
							manager.addMessage(
									SOYCategoryWarning,
									SOYValidator_Source_Invalid,
									bundle.getString(SOYValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									sOYSSourceSectionController
											.getControlSource());
							break;
						}
					}
				}
			}
		} else if (ctx instanceof ClosureGss) {
			GSSSourceSectionController gSSSSourceSectionController = (GSSSourceSectionController) factory
					.call(GSSSourceSectionController.class);
			GSSLibrariesSectionController gssLibrariesSectionController = (GSSLibrariesSectionController) factory
					.call(GSSLibrariesSectionController.class);
			manager.removeMessages(gSSSSourceSectionController
					.getControlSource());
			manager.removeMessages(gssLibrariesSectionController
					.getControlSource());
			if (n.getSource() == null || n.getSource().isEmpty()) {
				manager.addMessage(GSSCategoryWarning,
						GSSValidator_Source_Empty,
						bundle.getString(GSSValidator_Source_Empty), null,
						IMessageProvider.WARNING,
						gSSSSourceSectionController.getControlSource());
			} else {
				for (Source source : n.getSource()) {
					if (!validate(source, ctx)) {
						switch (source.getEntryKind()) {
						case CONTAINER:
						case FILE:
						case LIBRARY:
						case PROJECT:
						case VARIABLE:
						case CLOSURE:
							manager.addMessage(
									GSSCategoryError,
									GSSValidator_Source_Invalid,
									bundle.getString(GSSValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									gssLibrariesSectionController
											.getControlSource());
							break;
						case SOURCE:
							manager.addMessage(
									GSSCategoryError,
									GSSValidator_Source_Invalid,
									bundle.getString(GSSValidator_Source_Invalid),
									null, IMessageProvider.ERROR,
									gSSSSourceSectionController
											.getControlSource());
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void visit(Check n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Checks n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(CheckType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Closure n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(final ClosureGss n, final Object ctx) throws Exception {
		super.visit(n, n);
		validate(n, ctx);

		n.outputRenamingMapFormatProperty().addListener(
				new ChangeListener<GssOutputRenamingMapFormat>() {
					@Override
					public void changed(
							ObservableValue<? extends GssOutputRenamingMapFormat> observable,
							GssOutputRenamingMapFormat oldValue,
							GssOutputRenamingMapFormat newValue) {
						validate(n, ctx);
					}
				});

	}

	private void validate(ClosureGss n, Object ctx) {
		GSSRenamingSectionController gssRenamingSectionController = (GSSRenamingSectionController) factory
				.call(GSSRenamingSectionController.class);
		manager.removeMessages(gssRenamingSectionController
				.getControlOutputRenamingMap());
		if (n.getRenamingType() != GssRenamingType.NONE) {
			if (n.getOutputRenamingMap() == null
					|| n.getOutputRenamingMap().isEmpty()) {
				manager.addMessage(JSCategoryError,
						JSValidator_RenamingMap_Empty, bundle
								.getString(JSValidator_RenamingMap_Empty),
						null, IMessageProvider.ERROR,
						gssRenamingSectionController
								.getControlOutputRenamingMap());
			} else if (!new File(pathResolver.toRealPath(n
					.getOutputRenamingMap())).exists()) {
				manager.addMessage(JSCategoryError,
						JSValidator_RenamingMap_Exist, bundle
								.getString(JSValidator_RenamingMap_Exist),
						null, IMessageProvider.ERROR,
						gssRenamingSectionController
								.getControlOutputRenamingMap());
			}
		}

	}

	@Override
	public void visit(final ClosureJs n, final Object ctx) throws Exception {
		super.visit(n, n);

		validateExternExports(n, ctx);
		validateCharset(n, ctx);
		validateSourceMap(n, ctx);
		validateTranslationFile(n, ctx);

		n.externExportsProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				validateExternExports(n, ctx);
			}
		});

		n.externExportsPathProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateExternExports(n, ctx);
			}
		});

		n.charsetProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateCharset(n, ctx);
			}
		});

		n.sourceMapFileProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateSourceMap(n, ctx);
			}
		});

		n.translationsFileProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateTranslationFile(n, ctx);
			}
		});

	}

	private void validateExternExports(ClosureJs n, Object ctx) {
		JSExportsSectionController exportsSectionController = (JSExportsSectionController) factory
				.call(JSExportsSectionController.class);
		manager.removeMessages(exportsSectionController
				.getControlExternExportsPath());
		if (n.isExternExports()) {
			if (n.getExternExportsPath() == null
					|| n.getExternExportsPath().isEmpty()) {
				manager.addMessage(JSCategoryError,
						JSValidator_ExternExports_Empty,
						bundle.getString(JSValidator_ExternExports_Empty),
						null, IMessageProvider.ERROR,
						exportsSectionController.getControlExternExportsPath());
			}
		}
	}

	private void validateCharset(ClosureJs n, Object ctx) {
		JSLanguageSectionController languageSectionController = (JSLanguageSectionController) factory
				.call(JSLanguageSectionController.class);
		manager.removeMessages(languageSectionController.getControlCharset());
		if (n.getCharset() != null && !n.getCharset().isEmpty()) {
			if (!Charset.isSupported(n.getCharset())) {
				manager.addMessage(
						JSCategoryError,
						JSValidator_Charset,
						MessageFormat.format(
								bundle.getString(JSValidator_Charset),
								n.getCharset()), null, IMessageProvider.ERROR,
						languageSectionController.getControlCharset());
			}
		}
	}

	private void validateSourceMap(ClosureJs n, Object ctx) {
		JSSourceMapSectionController sourceMapSectionController = (JSSourceMapSectionController) factory
				.call(JSSourceMapSectionController.class);
		manager.removeMessages(sourceMapSectionController
				.getControlSourceMapFile());
		if (n.getSourceMapFile() != null && !n.getSourceMapFile().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getSourceMapFile()))
					.exists()) {
				manager.addMessage(JSCategoryError,
						JSValidator_SourceMap_Exist,
						bundle.getString(JSValidator_SourceMap_Exist), null,
						IMessageProvider.ERROR,
						sourceMapSectionController.getControlSourceMapFile());
			}
		}
	}

	private void validateTranslationFile(ClosureJs n, Object ctx) {
		JSTranslationSectionController translationSectionController = (JSTranslationSectionController) factory
				.call(JSTranslationSectionController.class);
		manager.removeMessages(translationSectionController
				.getControlTranslationsFile());
		if (n.getTranslationsFile() != null
				&& !n.getTranslationsFile().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getTranslationsFile()))
					.exists()) {
				manager.addMessage(JSCategoryError,
						JSValidator_TranslationFile_Exist, bundle
								.getString(JSValidator_TranslationFile_Exist),
						null, IMessageProvider.ERROR,
						translationSectionController
								.getControlTranslationsFile());
			}
		}
	}

	@Override
	public void visit(final ClosureSoy n, final Object ctx) throws Exception {
		super.visit(n, n);

		validateSoyOuputPath(n, ctx);
		validateSoyLocales(n, ctx);

		n.outputPathProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateSoyOuputPath(n, ctx);
			}
		});

		n.messagesPathProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateSoyLocales(n, ctx);
			}
		});

	}

	private void validateSoyOuputPath(ClosureSoy n, Object ctx) {
		SOYOutputSectionController outputSectionController = (SOYOutputSectionController) factory
				.call(SOYOutputSectionController.class);
		manager.removeMessages(outputSectionController.getControlOutputPath());
		if (n.getOutputPath() == null || n.getOutputPath().isEmpty()) {
			manager.addMessage(SOYCategoryError, SOYValidator_Ouput_Empty,
					bundle.getString(SOYValidator_Ouput_Empty), null,
					IMessageProvider.ERROR,
					outputSectionController.getControlOutputPath());
		}
	}

	private void validateSoyLocales(ClosureSoy n, Object ctx) {
		SOYLocalizationSectionController outputSectionController = (SOYLocalizationSectionController) factory
				.call(SOYLocalizationSectionController.class);
		manager.removeMessages(outputSectionController.getControlMessagesPath());
		if (n.getMessagesPath() == null || n.getMessagesPath().isEmpty()) {
			if (n.getSoyLocales() != null) {
				if (!n.getSoyLocales().getSoyLocale().isEmpty()) {
					manager.addMessage(SOYCategoryError,
							SOYValidator_MessageFile_Empty,
							bundle.getString(SOYValidator_MessageFile_Empty),
							null, IMessageProvider.ERROR,
							outputSectionController.getControlMessagesPath());
				}
			}
		} else if (!new File(pathResolver.toRealPath(n.getMessagesPath()))
				.exists()) {
			manager.addMessage(SOYCategoryError,
					SOYValidator_MessageFile_Exist,
					bundle.getString(SOYValidator_MessageFile_Exist), null,
					IMessageProvider.ERROR,
					outputSectionController.getControlMessagesPath());
		}
	}

	@Override
	public void visit(ExcludeInputFilter n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssAtRule n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssAtRules n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssDefine n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssDefines n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssExcludedClass n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssExcludedClasses n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssInputOrientation n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssNonStandardFunction n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssNonStandardFunctions n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssOptimizationLevel n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssOutputFormat n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssOutputOrientation n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssOutputRenamingMapFormat n, Object ctx)
			throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssRenamingType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssUnrecognizeProperties n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssUnrecognizeProperty n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssVendor n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(IncludeInputFilter n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Info n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(InputFilterPattern n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(InputFilterType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDefine n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDefines n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDefineType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDoc n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDocs n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(final JsVariableMap n, final Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsVariableRenaming(n, ctx);
		n.inputProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateJsVariableRenaming(n, ctx);
			}
		});
	}

	@Override
	public void visit(final JsFunctionMap n, final Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsFunctionRenaming(n, ctx);
		n.inputProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateJsFunctionRenaming(n, ctx);
			}
		});
	}

	@Override
	public void visit(final JsPropertyMap n, final Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsPropertyRenaming(n, ctx);
		n.inputProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateJsPropertyRenaming(n, ctx);
			}
		});
	}

	@Override
	public void visit(final JsRenaming n, final Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	private void validateJsFunctionRenaming(JsFunctionMap n, Object ctx) {
		JSRenamingSectionController renamingSectionController = (JSRenamingSectionController) factory
				.call(JSRenamingSectionController.class);
		manager.removeMessages(renamingSectionController
				.getControlFunctionInput());
		if (n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				manager.addMessage(
						JSCategoryError,
						JSValidator_FunctionRenamingInput_Exist,
						bundle.getString(JSValidator_FunctionRenamingInput_Exist),
						null, IMessageProvider.ERROR, renamingSectionController
								.getControlFunctionInput());
			}
		}
	}

	private void validateJsPropertyRenaming(JsPropertyMap n, Object ctx) {
		JSRenamingSectionController renamingSectionController = (JSRenamingSectionController) factory
				.call(JSRenamingSectionController.class);
		manager.removeMessages(renamingSectionController
				.getControlPropertyInput());
		if (n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				manager.addMessage(
						JSCategoryError,
						JSValidator_PropertyRenamingInput_Exist,
						bundle.getString(JSValidator_PropertyRenamingInput_Exist),
						null, IMessageProvider.ERROR, renamingSectionController
								.getControlPropertyInput());
			}
		}
	}

	private void validateJsVariableRenaming(JsVariableMap n, Object ctx) {
		JSRenamingSectionController renamingSectionController = (JSRenamingSectionController) factory
				.call(JSRenamingSectionController.class);
		manager.removeMessages(renamingSectionController
				.getControlVariableInput());
		if (n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				manager.addMessage(
						JSCategoryError,
						JSValidator_VariableRenamingInput_Exist,
						bundle.getString(JSValidator_VariableRenamingInput_Exist),
						null, IMessageProvider.ERROR, renamingSectionController
								.getControlVariableInput());
			}
		}
	}

	@Override
	public void visit(JsRenamingFunctionPolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsRenamingPropertyPolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsRenamingVariablePolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsSourceMapFormat n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(LangType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Language n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Optimization n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Optimizations n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(OptimizationType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(final Output n, final Object ctx) throws Exception {
		super.visit(n, ctx);
		validateOutput(n, ctx);
		n.pathProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateOutput(n, ctx);
			}
		});

		n.fileProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				validateOutput(n, ctx);
			}
		});
	}

	private void validateOutput(Output n, Object ctx) {
		if (ctx instanceof ClosureJs) {
			JSOutputSectionController outputSectionController = (JSOutputSectionController) factory
					.call(JSOutputSectionController.class);
			manager.removeMessages(outputSectionController.getControlFile());
			manager.removeMessages(outputSectionController.getControlPath());
			if (n.getFile() == null || n.getFile().isEmpty()) {
				manager.addMessage(JSCategoryError,
						JSValidator_Output_EmptyFile,
						bundle.getString(JSValidator_Output_EmptyFile), null,
						IMessageProvider.ERROR,
						outputSectionController.getControlFile());
			}

			if (n.getPath() == null || n.getPath().isEmpty()) {
				manager.addMessage(JSCategoryError,
						JSValidator_Output_EmptyPath,
						bundle.getString(JSValidator_Output_EmptyPath), null,
						IMessageProvider.ERROR,
						outputSectionController.getControlPath());
			}
		} else if (ctx instanceof ClosureGss) {
			GSSOutputSectionController outputSectionController = (GSSOutputSectionController) factory
					.call(GSSOutputSectionController.class);
			manager.removeMessages(outputSectionController.getControlFile());
			manager.removeMessages(outputSectionController.getControlPath());
			if (n.getFile() == null || n.getFile().isEmpty()) {
				manager.addMessage(GSSCategoryError,
						GSSValidator_Output_EmptyFile,
						bundle.getString(GSSValidator_Output_EmptyFile), null,
						IMessageProvider.ERROR,
						outputSectionController.getControlFile());
			}

			if (n.getPath() == null || n.getPath().isEmpty()) {
				manager.addMessage(GSSCategoryError,
						GSSValidator_Output_EmptyPath,
						bundle.getString(GSSValidator_Output_EmptyPath), null,
						IMessageProvider.ERROR,
						outputSectionController.getControlPath());
			}
		}
	}

	@Override
	public void visit(SeverityType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(final Source n, final Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	private boolean validate(Source n, Object ctx) {
		if (n.getPath() != null) {
			if (!new File(pathResolver.toRealPath(n.getPath())).exists()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void visit(SourceEntity n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SourceEntry n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyCodeStyle n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyCssSchemeType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyLocale n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyLocales n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Warning n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Warnings n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(WarningType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	private IPathResolver pathResolver;

	public Validator() {
	}

	public Validator(ResourceBundle bundle, Callback<Class<?>, Object> factory,
			IMessageManager manager, IPathResolver pathResolver) {
		this.bundle = bundle;
		this.factory = factory;
		this.manager = manager;
		this.pathResolver = pathResolver;

		JSPageController jsPageController = (JSPageController) factory
				.call(JSPageController.class);
		MessageCategory jsCategoryError = new MessageCategory(
				jsPageController.getLabelMessageError(), JSCategoryError);
		MessageCategory jsCategoryWarning = new MessageCategory(
				jsPageController.getLabelMessageWarning(), JSCategoryWarning);

		SOYPageController soyPageController = (SOYPageController) factory
				.call(SOYPageController.class);
		MessageCategory soyCategoryWarning = new MessageCategory(
				soyPageController.getLabelMessageWarning(), SOYCategoryWarning);
		MessageCategory soyCategoryError = new MessageCategory(
				soyPageController.getLabelMessageError(), SOYCategoryError);

		GSSPageController gssPageController = (GSSPageController) factory
				.call(GSSPageController.class);
		MessageCategory gssCategoryWarning = new MessageCategory(
				gssPageController.getLabelMessageWarning(), GSSCategoryWarning);
		MessageCategory gssCategoryError = new MessageCategory(
				gssPageController.getLabelMessageError(), GSSCategoryError);

		this.manager.getCategories().add(jsCategoryError);
		this.manager.getCategories().add(jsCategoryWarning);
		this.manager.getCategories().add(soyCategoryWarning);
		this.manager.getCategories().add(soyCategoryError);
		this.manager.getCategories().add(gssCategoryWarning);
		this.manager.getCategories().add(gssCategoryError);

	}
}
