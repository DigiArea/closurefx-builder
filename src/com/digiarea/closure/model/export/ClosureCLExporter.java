package com.digiarea.closure.model.export;

import java.io.ByteArrayOutputStream;

import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;
import com.digiarea.closurefx.utils.SourcePrinter;

public class ClosureCLExporter extends VoidVisitorAdapter<Void> {

	private ByteArrayOutputStream stream;
	private SourcePrinter printer;

	@Override
	public void visit(JsFunctionMap n, Void ctx) throws Exception {
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
		printer.print("-" + getWarningSeverityFlag(n.getSeverity()) + "=");
		switch (n.getType()) {
		case ACCESS_CONTROLS:
			printer.print("accessControls");
			break;
		case AGGRESSIVE_VAR_CHECK:
			throw new UnsupportedOperationException("AGGRESSIVE_VAR_CHECK is unsupported! Implement me!");
			//break;
		case AMBIGUOUS_FUNCTION_DECL:
			printer.print("ambiguousFunctionDecl");
			break;
		case BROKEN_REQUIRES_LEVEL:
			throw new UnsupportedOperationException("BROKEN_REQUIRES_LEVEL is unsupported! Implement me!");
			//break;
		case CHECK_GLOBAL_NAMES_LEVEL:
			printer.print("checkEventfulObjectDisposal");
			break;
		case CHECK_GLOBAL_THIS_LEVEL:
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
		case DEPRECATED:
			printer.print("deprecated");
			break;
		case DUPLICATE_MESSAGES:
			printer.print("duplicateMessage");
			break;
		case DUPLICATE_VARS:
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
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
			printer.print("checkEventfulObjectDisposal");
			break;
		default:
			break;
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

}
