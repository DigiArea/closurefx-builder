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
			break;
		case AMBIGUOUS_FUNCTION_DECL:
			printer.print("ambiguousFunctionDecl");
			break;
		case BROKEN_REQUIRES_LEVEL:
			break;
		case CHECK_GLOBAL_NAMES_LEVEL:
			break;
		case CHECK_GLOBAL_THIS_LEVEL:
			break;
		case CHECK_MISSING_RETURN:
			break;
		case CHECK_PROVIDES:
			break;
		case CHECK_REGEXP:
			break;
		case CHECK_REQUIRES:
			break;
		case CHECK_STRUCT_DICT_INHERITENCE:
			break;
		case CHECK_TYPES:
			break;
		case CHECK_UNREACHABLE_CODE:
			break;
		case CHECK_USELESS_CODE:
			break;
		case CHECK_VARS:
			break;
		case CONST:
			break;
		case CONSTANT_PROPERTY:
			break;
		case DEBUGGER_STATEMENT_PRESENT:
			break;
		case DEPRECATED:
			break;
		case DUPLICATE_MESSAGES:
			break;
		case DUPLICATE_VARS:
			break;
		case ES_5_STRICT:
			break;
		case EXTERNS_VALIDATION:
			break;
		case FILEOVERVIEW_JSDOC:
			break;
		case GLOBAL_THIS:
			break;
		case INTERNET_EXPLORER_CHECKS:
			break;
		case INVALID_CASTS:
			break;
		case MISPLACED_TYPE_ANNOTATION:
			break;
		case MISSING_PROPERTIES:
			break;
		case NON_STANDARD_JS_DOCS:
			break;
		case REPORT_MISSING_OVERRIDE:
			break;
		case REPORT_UNKNOWN_TYPES:
			break;
		case STRICT_MODULE_DEP_CHECK:
			break;
		case SUSPICIOUS_CODE:
			break;
		case TWEAKS:
			break;
		case TYPE_INVALIDATION:
			break;
		case UNDEFINED_NAMES:
			break;
		case UNDEFINED_VARS:
			break;
		case UNKNOWN_DEFINES:
			break;
		case VIOLATED_MODULE_DEP:
			break;
		case VISIBILITY:
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
