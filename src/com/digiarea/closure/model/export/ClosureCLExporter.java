package com.digiarea.closure.model.export;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Language;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.compiler.JSBuildpathContainerResolver;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.digiarea.closurefx.cli.console.ICliConsole;
import com.digiarea.closurefx.utils.ClosurerUtils;
import com.digiarea.closurefx.utils.SourcePrinter;

public class ClosureCLExporter extends VoidVisitorAdapter<Void> {

	private OutputStream stream;
	private SourcePrinter printer;
	private IPathResolver pathResolver;
	private ICliConsole console;
	private ResourceBundle bundle;
	
	private String warningLevel;
	private String compilationLevel;

	public ClosureCLExporter(IPathResolver pathResolver, OutputStream stream, ICliConsole console, ResourceBundle bundle) {
		this.bundle = bundle;
		this.console = console;
		this.stream = stream;
		this.printer = new SourcePrinter(this.stream, null);
		this.pathResolver = pathResolver;
	}
	
	public void setWarningLevel(String warningLevel) {
		this.warningLevel = warningLevel;
	}
	
	public void setCompilationLevel(String compilationLevel) {
		this.compilationLevel = compilationLevel;
	}

	public OutputStream getStream() {
		return stream;
	}

	@Override
	public void visit(Buildpath n, Void ctx) throws Exception {
		if (n.getParent() instanceof ClosureJs) {
			JSBuildpathContainerResolver resolver = new JSBuildpathContainerResolver(
					n, pathResolver);
			resolver.getDependencies();
			resolver.resolve();
			for (File source : resolver.getSources()) {
				printOption(IClosureCLConstants.JS, quote(source.getAbsolutePath()),
						" ", true);
			}
			for (File source : resolver.getExterns()) {
				printOption(IClosureCLConstants.JS_EXTERNS,
						quote(source.getAbsolutePath()), " ", true);
			}
		} else {
			super.visit(n, ctx);
		}
	}

	@Override
	public void visit(ClosureJs n, Void ctx) throws Exception {
		// if (n.getInfo() != null) {
		// n.getInfo().accept(this, ctx);
		// }
		if (n.getBuildpath() != null) {
			n.getBuildpath().accept(this, ctx);
		}
		if (n.getOutput() != null) {
			n.getOutput().accept(this, ctx);
		}
//		if(warningLevel != null){
//			printOption(IClosureCLConstants.JS_WARNING_LEVEL,
//					warningLevel, " ", true);
//		}
//		
//		if(compilationLevel != null){
//			printOption(IClosureCLConstants.JS_COMPILATION_LEVEL,
//					compilationLevel, " ", true);
//		}
		if (n.getWarnings() != null) {
			n.getWarnings().accept(this, ctx);
		}
		// if (n.getChecks() != null) {
		// n.getChecks().accept(this, ctx);
		// }
		if (n.getOptimizations() != null) {
			n.getOptimizations().accept(this, ctx);
		}
		if (n.getJsDocs() != null) {
			n.getJsDocs().accept(this, ctx);
		}
		if (n.getLanguage() != null) {
			n.getLanguage().accept(this, ctx);
		}
		if (n.getJsDefines() != null) {
			n.getJsDefines().accept(this, ctx);
		}
		if (n.getRenaming() != null) {
			n.getRenaming().accept(this, ctx);
		}

		if (n.getTranslationsFile() != null
				&& !n.getTranslationsFile().isEmpty()) {
			printOption(IClosureCLConstants.JS_TRANSLATION_FILE,
					n.getTranslationsFile(), " ", true);
		}

		if (n.getTranslationsProject() != null
				&& !n.getTranslationsProject().isEmpty()) {
			printOption(IClosureCLConstants.JS_TRANSLATION_PROJECT,
					n.getTranslationsProject(), " ", true);
		}

		if (!n.isClosurePass() && !n.isJqueryPass()) {
			printOption(IClosureCLConstants.JS_THIRD_PARTY, true, " ", true);
		}
		
		printOption(IClosureCLConstants.JS_PROCESS_CLOSURE_PRIMITIVES,
				n.isClosurePass(), " ", true);
		printOption(IClosureCLConstants.JS_PROCESS_JQUERY_PRIMITIVES,
				n.isJqueryPass(), " ", true);

		printOption(IClosureCLConstants.JS_PROCESS_ANGULAR, n.isAngularPass(),
				" ", true);
		printOption(IClosureCLConstants.JS_ACCEPT_CONST,
				n.isAcceptConstKeyword(), " ", true);

		if (n.isPrettyPrint()) {
			printOption(IClosureCLConstants.JS_FORMATTING,
					IClosureCLConstants.JS_FORMATTING_PRETTY_PRINT, " ", true);
		}

		if (n.isPrintInputDelimeter()) {
			printOption(IClosureCLConstants.JS_FORMATTING,
					IClosureCLConstants.JS_FORMATTING_PRINT_INPUT_DELIMITER,
					" ", true);
		}

		if (n.isSingleQuotes()) {
			printOption(IClosureCLConstants.JS_FORMATTING,
					IClosureCLConstants.JS_FORMATTING_SINGLE_QUOTES, " ", true);
		}

		if (n.isGenerateExports()) {
			printOption(IClosureCLConstants.JS_GENERATE_EXPORTS,
					n.isGenerateExports(), " ", true);
		}

		if (n.getSourceMapFile() != null && !n.getSourceMapFile().isEmpty()) {
			printOption(IClosureCLConstants.JS_SOURCE_MAP,
					quote(pathResolver.toRealPath(n.getSourceMapFile())), " ", true);
			printOption(IClosureCLConstants.JS_SOURCE_MAP_FORMAT, ClosurerUtils
					.toSourceMapFormat(n.getSourceMapFormat()).name(), " ",
					true);
		}

		if (n.getCharset() != null && !n.getCharset().isEmpty()) {
			printOption(IClosureCLConstants.JS_CHARSET, n.getCharset(), " ",
					true);
		}
	}

	@Override
	public void visit(Optimizations n, Void ctx) throws Exception {
		console.report(new Status(StatusType.WARNING, bundle
				.getString(IConstants.ClosureCLExporter_Optimization), null));
	}

	@Override
	public void visit(Output n, Void ctx) throws Exception {
		if (n.getParent() instanceof ClosureJs) {
			printOption(
					IClosureCLConstants.JS_OUTPUT,
					quote(new Path(pathResolver.toRealPath(n.getPath())).append(
							n.getFile()).toString()), " ", true);
		} else {
			super.visit(n, ctx);
		}
	}

	private String getWarningSeverityFlag(SeverityType type) {
		switch (type) {
		case OFF:
			return IClosureCLConstants.JS_OFF;
		case WARNING:
			return IClosureCLConstants.JS_WARNING;
		default:
			return IClosureCLConstants.JS_ERROR;
		}
	}

	@Override
	public void visit(Warning n, Void ctx) throws Exception {
		String value = null;
		switch (n.getType()) {
		case ACCESS_CONTROLS:
			value = "accessControls";
			break;
		case AMBIGUOUS_FUNCTION_DECL:
			value = "ambiguousFunctionDecl";
			break;
		case CHECK_MISSING_RETURN:
			value = "missingReturn";
			break;
		case CHECK_PROVIDES:
			value = "missingProvide";
			break;
		case CHECK_REGEXP:
			value = "checkRegExp";
			break;
		case CHECK_REQUIRES:
			value = "missingRequire";
			break;
		case CHECK_STRUCT_DICT_INHERITENCE:
			value = "checkStructDictInheritance";
			break;
		case CHECK_TYPES:
			value = "checkTypes";
			break;
		case CHECK_USELESS_CODE:
			value = "uselessCode";
			break;
		case CHECK_VARS:
			value = "checkVars";
			break;
		case CONST:
			value = "const";
			break;
		case CONSTANT_PROPERTY:
			value = "constantProperty";
			break;
		case DEPRECATED:
			value = "deprecated";
			break;
		case DUPLICATE_MESSAGES:
			value = "duplicateMessage";
			break;
		case ES_5_STRICT:
			value = "es5Strict";
			break;
		case EXTERNS_VALIDATION:
			value = "externsValidation";
			break;
		case FILEOVERVIEW_JSDOC:
			value = "fileoverviewTags";
			break;
		case GLOBAL_THIS:
			value = "globalThis";
			break;
		case INTERNET_EXPLORER_CHECKS:
			value = "internetExplorerChecks";
			break;
		case INVALID_CASTS:
			value = "invalidCasts";
			break;
		case MISPLACED_TYPE_ANNOTATION:
			value = "misplacedTypeAnnotation";
			break;
		case MISSING_PROPERTIES:
			value = "missingProperties";
			break;
		case NON_STANDARD_JS_DOCS:
			value = "nonStandardJsDocs";
			break;
		case REPORT_UNKNOWN_TYPES:
			value = "reportUnknownTypes";
			break;
		case STRICT_MODULE_DEP_CHECK:
			value = "strictModuleDepCheck";
			break;
		case SUSPICIOUS_CODE:
			value = "suspiciousCode";
			break;
		case TYPE_INVALIDATION:
			value = "typeInvalidation";
			break;
		case UNDEFINED_NAMES:
			value = "undefinedNames";
			break;
		case UNDEFINED_VARS:
			value = "undefinedVars";
			break;
		case UNKNOWN_DEFINES:
			value = "unknownDefines";
			break;

		case VISIBILITY:
			value = "visibility";
			break;
		case CHECK_EVENTFUL_OBJECT_DISPOSAL:
			value = "checkEventfulObjectDisposal";
			break;
		case ES3:
			value = "es3";
			break;
		case UNNECESSARY_CASTS:
		case VIOLATED_MODULE_DEP:
		case TWEAKS:
		case REPORT_MISSING_OVERRIDE:
		case DUPLICATE_VARS:
		case DEBUGGER_STATEMENT_PRESENT:
		case CHECK_UNREACHABLE_CODE:
		case CHECK_GLOBAL_NAMES_LEVEL:
		case CHECK_GLOBAL_THIS_LEVEL:
		case BROKEN_REQUIRES_LEVEL:
		case AGGRESSIVE_VAR_CHECK:
			console.report(new Status(StatusType.WARNING, MessageFormat.format(
					bundle.getString(IConstants.ClosureCLExporter_Warning), n
							.getType().name()), null));
			break;
		default:
			console.report(new Status(StatusType.ERROR, "Strange option: "
					+ n.getType().name(), null));
			break;
		}

		if (value != null) {
			printOption(getWarningSeverityFlag(n.getSeverity()), value, "=",
					true);
		}
	}

	@Override
	public void visit(JsDoc n, Void ctx) throws Exception {
		printOption(IClosureCLConstants.JS_EXTRA_ANNOTATIONS_NAME,
				n.getValue(), " ", true);
	}

	@Override
	public void visit(Language n, Void ctx) throws Exception {
		if (n.getInput() != null) {
			printOption(IClosureCLConstants.JS_LANGUAGE_IN,
					getJSLanguageFlag(n.getInput()), " ", true);
		}
		if (n.getOutput() != null) {
			console.report(new Status(StatusType.WARNING, bundle
					.getString(IConstants.ClosureCLExporter_LanguageOut), null));
		}
	}

	private String getJSLanguageFlag(LangType type) {
		switch (type) {
		case ECMASCRIPT_3:
			return "ECMASCRIPT3";
		case ECMASCRIPT_5:
			return "ECMASCRIPT5";
		case ECMASCRIPT_5_STRICT:
			return "ECMASCRIPT5_STRICT";
		default:
			return "ECMASCRIPT3";
		}
	}

	@Override
	public void visit(JsDefine n, Void ctx) throws Exception {
		String value = n.getName() + "=";
		if (n.getType() == JsDefineType.STRING) {
			value = value + "'" + n.getValue() + "'";
		} else {
			value = value + n.getValue();
		}

		printOption(IClosureCLConstants.JS_DEFINE, value, " ", true);
	}

	@Override
	public void visit(JsVariableMap n, Void ctx) throws Exception {
		if (n.getInput() != null && !n.getInput().isEmpty()) {
			printOption(IClosureCLConstants.JS_VARIABLE_MAP_INPUT,
					quote(pathResolver.toRealPath(n.getInput())), " ", true);
		}
		if (n.getOutput() != null && !n.getOutput().isEmpty()) {
			printOption(IClosureCLConstants.JS_VARIABLE_MAP_OUTPUT,
					quote(pathResolver.toRealPath(n.getOutput())), " ", true);
		}
	}

	@Override
	public void visit(JsPropertyMap n, Void ctx) throws Exception {
		if (n.getInput() != null && !n.getInput().isEmpty()) {
			printOption(IClosureCLConstants.JS_PROPERTY_MAP_INPUT,
					quote(pathResolver.toRealPath(n.getInput())), " ", true);
		}
		if (n.getOutput() != null && !n.getOutput().isEmpty()) {
			printOption(IClosureCLConstants.JS_PROPERTY_MAP_OUTPUT,
					quote(pathResolver.toRealPath(n.getOutput())), " ", true);
		}
	}

	@Override
	public void visit(JsFunctionMap n, Void ctx) throws Exception {
		console.report(new Status(StatusType.WARNING, bundle
				.getString(IConstants.ClosureCLExporter_JSFunctionMap), null));
	}

	private void printOption(String key, Object value, String delimiter,
			boolean newLine) throws IOException {
		printer.print(key + delimiter + value);
		printer.print(" ");
	}
	
	private String quote(Object string) {
		return "\"" + string + "\"";
	}

}
