package com.digiarea.closurefx.build.validation;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.bind.PreferencesSerializer;
import com.digiarea.closurefx.utils.ValidationUtils;

public class BasicValidator extends VoidVisitorAdapter<Object> {

	public static String JSValidator_Source_Empty = "JSValidator_Source_Empty";
	public static String JSValidator_Source_Invalid = "JSValidator_Source_Invalid";

	public static String JSValidator_Output_EmptyPath = "JSValidator_Output_EmptyPath";
	public static String JSValidator_Output_EmptyFile = "JSValidator_Output_EmptyFile";

	public static String SOYValidator_Source_Empty = "SOYValidator_Source_Empty";
	public static String SOYValidator_Source_Invalid = "SOYValidator_Source_Invalid";

	public static String GSSValidator_Source_Empty = "GSSValidator_Source_Empty";
	public static String GSSValidator_Source_Invalid = "GSSValidator_Source_Invalid";

	public static String JSValidator_Source_Exist = "JSValidator_Source_Exist";
	public static String SOYValidator_Source_Exist = "SOYValidator_Source_Exist";
	public static String GSSValidator_Source_Exist = "GSSValidator_Source_Exist";
	public static String JSValidator_Closure_Exist = "JSValidator_Closure_Exist";
	public static String JSValidator_Variable_Exist = "JSValidator_Variable_Exist";
	public static String GSSValidator_Variable_Exist = "GSSValidator_Variable_Exist";
	public static String SOYValidator_Variable_Exist = "SOYValidator_Variable_Exist";

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

	}

	private void validate(Buildpath n, Object ctx) throws Exception {
		if (ctx instanceof ClosureJs) {
			if (n.getSource() == null || n.getSource().isEmpty()) {
				warnings.add(ValidationUtils.createWarningStatus(bundle
						.getString(JSValidator_Source_Empty)));
			} else {
				for (Source source : n.getSource()) {
					super.visit(source, ctx);
				}
			}
		} else if (ctx instanceof ClosureSoy) {
			if (n.getSource() == null || n.getSource().isEmpty()) {
				warnings.add(ValidationUtils.createWarningStatus(bundle
						.getString(SOYValidator_Source_Empty)));
			} else {
				for (Source source : n.getSource()) {
					super.visit(source, ctx);
				}
			}
		} else if (ctx instanceof ClosureGss) {
			if (n.getSource() == null || n.getSource().isEmpty()) {
				warnings.add(ValidationUtils.createWarningStatus(bundle
						.getString(GSSValidator_Source_Empty)));
			} else {
				for (Source source : n.getSource()) {
					super.visit(source, ctx);
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
	}

	private void validate(ClosureGss n, Object ctx) {
		if (n.getRenamingType() != GssRenamingType.NONE) {
			if (n.getOutputRenamingMap() == null
					|| n.getOutputRenamingMap().isEmpty()) {
				warnings.add(ValidationUtils.createWarningStatus(bundle
						.getString(JSValidator_RenamingMap_Empty)));
			} else if (!new File(pathResolver.toRealPath(n
					.getOutputRenamingMap())).exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_RenamingMap_Exist)));
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

	}

	private void validateExternExports(ClosureJs n, Object ctx) {
		if (n.isExternExports()) {
			if (n.getExternExportsPath() == null
					|| n.getExternExportsPath().isEmpty()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_ExternExports_Empty)));
			}
		}
	}

	private void validateCharset(ClosureJs n, Object ctx) {
		if (n.getCharset() != null && !n.getCharset().isEmpty()) {
			if (!Charset.isSupported(n.getCharset())) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_Charset)));
			}
		}
	}

	private void validateSourceMap(ClosureJs n, Object ctx) {
		if (n.getSourceMapFile() != null && !n.getSourceMapFile().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getSourceMapFile()))
					.exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_SourceMap_Exist)));
			}
		}
	}

	private void validateTranslationFile(ClosureJs n, Object ctx) {
		if (n.getTranslationsFile() != null
				&& !n.getTranslationsFile().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getTranslationsFile()))
					.exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_TranslationFile_Exist)));
			}
		}
	}

	@Override
	public void visit(final ClosureSoy n, final Object ctx) throws Exception {
		super.visit(n, n);

		validateSoyOuputPath(n, ctx);
		validateSoyLocales(n, ctx);

	}

	private void validateSoyOuputPath(ClosureSoy n, Object ctx) {
		if (n.getOutputPath() == null || n.getOutputPath().isEmpty()) {
			errors.add(ValidationUtils.createErrorStatus(bundle
					.getString(SOYValidator_Ouput_Empty)));
		}
	}

	private void validateSoyLocales(ClosureSoy n, Object ctx) {
		if (n.getMessagesPath() == null || n.getMessagesPath().isEmpty()) {
			if (n.getSoyLocales() != null) {
				if (!n.getSoyLocales().getSoyLocale().isEmpty()) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(SOYValidator_MessageFile_Empty)));
				}
			}
		} else if (!new File(pathResolver.toRealPath(n.getMessagesPath()))
				.exists()) {
			errors.add(ValidationUtils.createErrorStatus(bundle
					.getString(SOYValidator_MessageFile_Exist)));
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
	public void visit(JsVariableMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsVariableRenaming(n, ctx);
	}

	@Override
	public void visit(JsFunctionMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsFunctionRenaming(n, ctx);
	}

	@Override
	public void visit(JsPropertyMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		validateJsPropertyRenaming(n, ctx);
	}

	@Override
	public void visit(final JsRenaming n, final Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	private void validateJsFunctionRenaming(JsFunctionMap n, Object ctx) {
		if (n != null && n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_FunctionRenamingInput_Exist)));
			}
		}
	}

	private void validateJsPropertyRenaming(JsPropertyMap n, Object ctx) {
		if (n != null && n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_PropertyRenamingInput_Exist)));
			}
		}
	}

	private void validateJsVariableRenaming(JsVariableMap n, Object ctx) {
		if (n != null && n.getInput() != null && !n.getInput().isEmpty()) {
			if (!new File(pathResolver.toRealPath(n.getInput())).exists()) {
				errors.add(ValidationUtils.createErrorStatus(bundle
						.getString(JSValidator_VariableRenamingInput_Exist)));
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

		validateOutputPath(n.getPath());
		validateOutputFile(n.getFile());

	}

	private void validateOutputPath(String n) {
		if (n == null || n.isEmpty()) {
			errors.add(ValidationUtils.createErrorStatus(bundle
					.getString(JSValidator_Output_EmptyPath)));
		}
	}

	private void validateOutputFile(String n) {
		if (n == null || n.isEmpty()) {
			errors.add(ValidationUtils.createErrorStatus(bundle
					.getString(JSValidator_Output_EmptyFile)));
		}
	}

	@Override
	public void visit(SeverityType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(final Source n, final Object ctx) throws Exception {
		validate(n, ctx);
	}

	private void validate(Source n, Object ctx) {
		if (ctx instanceof ClosureJs) {
			if (n.getEntryKind() == SourceEntry.CLOSURE) {
				ClosureLibrary library = new PreferencesSerializer()
						.readLibrary(n.getPath());
				if (library == null) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(JSValidator_Closure_Exist)));
				} else {
					if (!new File(pathResolver.toRealPath(n.getPath()))
							.exists()) {
						errors.add(ValidationUtils.createErrorStatus(bundle
								.getString(JSValidator_Source_Exist)));
					}
				}
			} else if (n.getEntryKind() == SourceEntry.VARIABLE) {
				Variable library = new PreferencesSerializer().readVariable(n
						.getPath());
				if (library == null) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(JSValidator_Variable_Exist)));
				} else {
					if (!new File(pathResolver.toRealPath(n.getPath()))
							.exists()) {
						errors.add(ValidationUtils.createErrorStatus(bundle
								.getString(JSValidator_Source_Exist)));
					}
				}
			} else {
				if (!new File(pathResolver.toRealPath(n.getPath())).exists()) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(JSValidator_Source_Exist)));
				}
			}
		} else if (ctx instanceof ClosureSoy) {
			if (n.getEntryKind() == SourceEntry.VARIABLE) {
				Variable library = new PreferencesSerializer().readVariable(n
						.getPath());
				if (library == null) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(SOYValidator_Variable_Exist)));
				} else {
					if (!new File(pathResolver.toRealPath(n.getPath()))
							.exists()) {
						errors.add(ValidationUtils.createErrorStatus(bundle
								.getString(SOYValidator_Source_Exist)));
					}
				}
			} else {
				if (!new File(pathResolver.toRealPath(n.getPath())).exists()) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(SOYValidator_Source_Exist)));
				}
			}
		} else if (ctx instanceof ClosureGss) {
			if (n.getEntryKind() == SourceEntry.VARIABLE) {
				Variable library = new PreferencesSerializer().readVariable(n
						.getPath());
				if (library == null) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(GSSValidator_Variable_Exist)));
				} else {
					if (!new File(pathResolver.toRealPath(n.getPath()))
							.exists()) {
						errors.add(ValidationUtils.createErrorStatus(bundle
								.getString(GSSValidator_Source_Exist)));
					}
				}
			} else {
				if (!new File(pathResolver.toRealPath(n.getPath())).exists()) {
					errors.add(ValidationUtils.createErrorStatus(bundle
							.getString(GSSValidator_Source_Exist)));
				}
			}
		}
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

	private ResourceBundle bundle;

	private List<IStatus> warnings = new ArrayList<IStatus>();
	private List<IStatus> errors = new ArrayList<IStatus>();

	public BasicValidator(ResourceBundle bundle, IPathResolver pathResolver) {
		this.pathResolver = pathResolver;
		this.bundle = bundle;
	}

	public boolean isValid() {
		return errors.isEmpty();
	}

	public List<IStatus> getWarnings() {
		return warnings;
	}

	public List<IStatus> getErrors() {
		return errors;
	}
}
