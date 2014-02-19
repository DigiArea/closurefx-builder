package com.digiarea.closure.model.export;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Language;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;
import com.digiarea.closurefx.build.compiler.JSBuildpathContainerResolver;
import com.digiarea.closurefx.utils.ClosurerUtils;
import com.digiarea.closurefx.utils.SourcePrinter;

public class ClosureCLExporter extends VoidVisitorAdapter<Void> {

	private ByteArrayOutputStream stream;
	private SourcePrinter printer;
	
	private IPathResolver pathResolver;
	
	@Override
	public void visit(Buildpath n, Void ctx) throws Exception {
		if(n.getParent() instanceof ClosureJs){
			JSBuildpathContainerResolver resolver = new JSBuildpathContainerResolver(
					n,
					pathResolver);
			resolver.getDependencies();
			resolver.resolve();
			for (File source : resolver.getSources()) {
				printer.print(IClosureCLConstants.JS + " "
						+ source.getAbsolutePath());
			}
			for (File source : resolver.getExterns()) {
				printer.print(IClosureCLConstants.JS_EXTERNS + " "
						+ source.getAbsolutePath());
			}
		}else{
			super.visit(n, ctx);
		}
	}
	
	@Override
	public void visit(ClosureJs n, Void ctx) throws Exception {
		super.visit(n, ctx);
		
		if(n.getTranslationsFile() != null && !n.getTranslationsFile().isEmpty()){
			printer.print(IClosureCLConstants.JS_TRANSLATION_FILE + " "
					+ n.getTranslationsFile());
		}
		
		if(n.getTranslationsProject() != null && !n.getTranslationsProject().isEmpty()){
			printer.print(IClosureCLConstants.JS_TRANSLATION_PROJECT + " "
					+ n.getTranslationsProject());
		}

		printer.print(IClosureCLConstants.JS_PROCESS_CLOSURE_PRIMITIVES + " "
				+ n.isClosurePass());
		printer.print(IClosureCLConstants.JS_PROCESS_JQUERY_PRIMITIVES + " "
				+ n.isJqueryPass());
		printer.print(IClosureCLConstants.JS_PROCESS_ANGULAR + " "
				+ n.isAngularPass());

		printer.print(IClosureCLConstants.JS_ACCEPT_CONST + " "
				+ n.isAcceptConstKeyword());

		if (n.isPrettyPrint()) {
			printer.print(IClosureCLConstants.JS_FORMATTING + " "
					+ IClosureCLConstants.JS_FORMATTING_PRETTY_PRINT);
		}

		if (n.isPrintInputDelimeter()) {
			printer.print(IClosureCLConstants.JS_FORMATTING + " "
					+ IClosureCLConstants.JS_FORMATTING_PRINT_INPUT_DELIMITER);
		}

		if (n.isSingleQuotes()) {
			printer.print(IClosureCLConstants.JS_FORMATTING + " "
					+ IClosureCLConstants.JS_FORMATTING_SINGLE_QUOTES);
		}

		if (n.isGenerateExports()) {
			printer.print(IClosureCLConstants.JS_GENERATE_EXPORTS + " "
					+ n.isGenerateExports());
		}

		if (n.getSourceMapFile() != null && !n.getSourceMapFile().isEmpty()) {
			printer.print(IClosureCLConstants.JS_SOURCE_MAP + " "
					+ n.getSourceMapFile());
			printer.print(IClosureCLConstants.JS_SOURCE_MAP_FORMAT
					+ " "
					+ ClosurerUtils.toSourceMapFormat(n.getSourceMapFormat())
							.name());
		}

		if (n.getCharset() != null && !n.getCharset().isEmpty()) {
			printer.print(IClosureCLConstants.JS_CHARSET + " " + n.getCharset());
		}
	}

	@Override
	public void visit(Output n, Void ctx) throws Exception {
		if (n.getParent() instanceof ClosureJs) {
			printer.print(IClosureCLConstants.JS_OUTPUT + " "
					+ new Path(n.getPath()).append(n.getFile()).toString());
		} else {
			super.visit(n, ctx);
		}
	}

	@Override
	public void visit(Check n, Void ctx) throws Exception {
		switch (n.getType()) {
		case CHAIN_CALLS:
			break;
		case CHECK_CAJA:
			break;
		case CHECK_CONTROL_SCTRUCTURES:
			break;
		case CHECK_SUSPICIOUS_CODE:
			break;
		case CHECK_SYMBOLS:
			break;
		case CHECK_TYPES:
			break;
		case COMPUTE_FUNCTION_SIDE_EFFECTS:
			break;
		case TIGHTEN_TYPES:
			break;
		default:
			break;
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
		printer.print(getWarningSeverityFlag(n.getSeverity()) + "=");
		switch (n.getType()) {
		case ACCESS_CONTROLS:
			printer.print("accessControls");
			break;
		case AGGRESSIVE_VAR_CHECK:
			throw new UnsupportedOperationException(
					"AGGRESSIVE_VAR_CHECK is unsupported! Implement me!");
			// break;
		case AMBIGUOUS_FUNCTION_DECL:
			printer.print("ambiguousFunctionDecl");
			break;
		case BROKEN_REQUIRES_LEVEL:
			throw new UnsupportedOperationException(
					"BROKEN_REQUIRES_LEVEL is unsupported! Implement me!");
			// break;
		case CHECK_GLOBAL_NAMES_LEVEL:
			throw new UnsupportedOperationException(
					"CHECK_GLOBAL_NAMES_LEVEL is unsupported! Implement me!");
			// break;
		case CHECK_GLOBAL_THIS_LEVEL:
			throw new UnsupportedOperationException(
					"CHECK_GLOBAL_THIS_LEVEL is unsupported! Implement me!");
			// break;
		case CHECK_MISSING_RETURN:
			printer.print("missingReturn");
			break;
		case CHECK_PROVIDES:
			printer.print("missingProvide");
			break;
		case CHECK_REGEXP:
			printer.print("checkRegExp");
			break;
		case CHECK_REQUIRES:
			printer.print("missingRequire");
			break;
		case CHECK_STRUCT_DICT_INHERITENCE:
			printer.print("checkStructDictInheritance");
			break;
		case CHECK_TYPES:
			printer.print("checkTypes");
			break;
		case CHECK_UNREACHABLE_CODE:
			throw new UnsupportedOperationException(
					"CHECK_UNREACHABLE_CODE is unsupported! Implement me!");
			// break;
		case CHECK_USELESS_CODE:
			printer.print("uselessCode");
			break;
		case CHECK_VARS:
			printer.print("checkVars");
			break;
		case CONST:
			printer.print("const");
			break;
		case CONSTANT_PROPERTY:
			printer.print("constantProperty");
			break;
		case DEBUGGER_STATEMENT_PRESENT:
			throw new UnsupportedOperationException(
					"DEBUGGER_STATEMENT_PRESENT is unsupported! Implement me!");
			// break;
		case DEPRECATED:
			printer.print("deprecated");
			break;
		case DUPLICATE_MESSAGES:
			printer.print("duplicateMessage");
			break;
		case DUPLICATE_VARS:
			throw new UnsupportedOperationException(
					"DUPLICATE_VARS is unsupported! Implement me!");
			// break;
		case ES_5_STRICT:
			printer.print("es5Strict");
			break;
		case EXTERNS_VALIDATION:
			printer.print("externsValidation");
			break;
		case FILEOVERVIEW_JSDOC:
			printer.print("fileoverviewTags");
			break;
		case GLOBAL_THIS:
			printer.print("globalThis");
			break;
		case INTERNET_EXPLORER_CHECKS:
			printer.print("internetExplorerChecks");
			break;
		case INVALID_CASTS:
			printer.print("invalidCasts");
			break;
		case MISPLACED_TYPE_ANNOTATION:
			printer.print("misplacedTypeAnnotation");
			break;
		case MISSING_PROPERTIES:
			printer.print("missingProperties");
			break;
		case NON_STANDARD_JS_DOCS:
			printer.print("nonStandardJsDocs");
			break;
		case REPORT_MISSING_OVERRIDE:
			throw new UnsupportedOperationException(
					"REPORT_MISSING_OVERRIDE is unsupported! Implement me!");
			// break;
		case REPORT_UNKNOWN_TYPES:
			printer.print("reportUnknownTypes");
			break;
		case STRICT_MODULE_DEP_CHECK:
			printer.print("strictModuleDepCheck");
			break;
		case SUSPICIOUS_CODE:
			printer.print("suspiciousCode");
			break;
		case TWEAKS:
			throw new UnsupportedOperationException(
					"TWEAKS is unsupported! Implement me!");
			// break;
		case TYPE_INVALIDATION:
			printer.print("typeInvalidation");
			break;
		case UNDEFINED_NAMES:
			printer.print("undefinedNames");
			break;
		case UNDEFINED_VARS:
			printer.print("undefinedVars");
			break;
		case UNKNOWN_DEFINES:
			printer.print("unknownDefines");
			break;
		case VIOLATED_MODULE_DEP:
			throw new UnsupportedOperationException(
					"VIOLATED_MODULE_DEP is unsupported! Implement me!");
			// break;
		case VISIBILITY:
			printer.print("visibility");
			break;
		case CHECK_EVENTFUL_OBJECT_DISPOSAL:
			printer.print("checkEventfulObjectDisposal");
			break;
		case ES3:
			printer.print("es3");
			break;
		case UNNECESSARY_CASTS:
			throw new UnsupportedOperationException(
					"UNNECESSARY_CASTS is unsupported! Implement me!");
			// break;
		default:
			break;
		}
	}

	@Override
	public void visit(JsDoc n, Void ctx) throws Exception {
		printer.print(IClosureCLConstants.JS_EXTRA_ANNOTATIONS_NAME + " " + n.getValue());
	}

	@Override
	public void visit(Language n, Void ctx) throws Exception {
		if (n.getInput() != null) {
			printer.print(IClosureCLConstants.JS_LANGUAGE_IN + " "
					+ getJSLanguageFlag(n.getInput()));
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
		printer.print(IClosureCLConstants.JS_DEFINE + " ");
		printer.print(n.getName() + "=");
		if (n.getType() == JsDefineType.STRING) {
			printer.print("'" + n.getValue() + "'");
		} else {
			printer.print(n.getValue());
		}
	}

	@Override
	public void visit(JsVariableMap n, Void ctx) throws Exception {
		printer.print(IClosureCLConstants.JS_VARIABLE_MAP_INPUT + " ");
		printer.print(n.getInput());
		printer.print(IClosureCLConstants.JS_VARIABLE_MAP_OUTPUT + " ");
		printer.print(n.getOutput());
	}

	@Override
	public void visit(JsPropertyMap n, Void ctx) throws Exception {
		printer.print(IClosureCLConstants.JS_PROPERTY_MAP_INPUT + " ");
		printer.print(n.getInput());
		printer.print(IClosureCLConstants.JS_PROPERTY_MAP_OUTPUT + " ");
		printer.print(n.getOutput());
	}

	@Override
	public void visit(JsFunctionMap n, Void ctx) throws Exception {
		System.out.println("JsFunctionMap is not supported");
	}

}
