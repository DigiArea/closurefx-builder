package com.digiarea.closure.model.providers;

import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.WarningType;

public class LabelProviders {

	public static String getRenamingFunctionLabel(JsRenamingFunctionPolice type) {
		switch (type) {
		case MAPPED:
			return "Generates short unique names with mapping from them back";
		case OFF:
			return "Don't rename anonymous functions";
		case UNMAPPED:
			return "Generates names that are based on the left-hand side of the assignment";
		}
		return null;
	}

	public static String getRenamingVariableLabel(JsRenamingVariablePolice type) {
		switch (type) {
		case ALL:
			return "Rename all variables and functions unless they are exported or externed";
		case LOCAL:
			return "Rename local variables only";
		case OFF:
			return "Don't rename variables";
		}
		return null;
	}

	public static String getRenamingPropertyLabel(JsRenamingPropertyPolice type) {
		switch (type) {
		case AGGRESSIVE_HEURISTIC:
			return "Rename properties more heuristically";
		case ALL_UNQUOTED:
			return "Rename all properties that aren't explicitly quoted and aren't externally defined (achieves better compaction)";
		case HEURISTIC:
			return "Rename properties heuristically";
		case OFF:
			return "Don't rename properties";
		default:
			break;
		}
		return null;
	}

	public static String getLanguageLabel(LangType type) {
		switch (type) {
		case ECMASCRIPT_3:
			return "JavaScript 3";
		case ECMASCRIPT_5:
			return "JavaScript 5";
		case ECMASCRIPT_5_STRICT:
			return "JavaScript 5 Strict";
		}
		return null;
	}

	public static String getOptimizationlabel(OptimizationType type) {
		switch (type) {
		case ALIAS_ALL_STRINGS:
			return "alias all strings.";
		case ALIAS_EXTERNALS:
			return "alias externals.";
		case ALIAS_KEYWORDS:
			return "alias keywords.";
		case ASSUME_CLOSURES_ONLY_CAPTURE_PREFERENCES:
			return "assume closures only capture preferences.";
		case ASSUME_STRICT_THIS:
			return "assume strict this.";
		case COALESCE_VARIABLE_NAMES:
			return "coalesce variable names.";
		case COLLAPSE_ANONYMOUS_FUNCTIONS:
			return "collapse anonymous functions.";
		case COLLAPSE_OBJECT_LITERALS:
			return "collapse object literals.";
		case COLLAPSE_PROPERTIES:
			return "collapse properties.";
		case COLLAPSE_PROPERTIES_ON_EXTERN_TYPES:
			return "collapse properties on extern types.";
		case COLLAPSE_VARIABLE_DECLARATIONS:
			return "collapse variable declarations.";
		case CONVERT_TO_DOTTED_PROPERTIES:
			return "convert to dotted properties.";
		case CROSS_MODULE_CODE_MOTION:
			return "cross module code motion.";
		case CROSS_MODULE_METHOD_MOTION:
			return "cross module method motion.";
		case DEAD_ASSIGNMENT_ELIMINATION:
			return "dead assignment elimination.";
		case EXTRACT_PROTOTYPE_MEMBER_DECLARATION:
			return "extract prototype member declaration.";
		case FOLD_CONSTANTS:
			return "fold constants.";
		case GROUP_VARIABLE_DECLARATIONS:
			return "group variable declarations.";
		case INLINE_CONSTANT_VAR:
			return "inline constant variables.";
		case INLINE_FUNCTION:
			return "inline functions.";
		case INLINE_GETTERS:
			return "inline getters.";
		case INLINE_LOCAL_FUNCTION:
			return "inline local functions.";
		case INLINE_LOCAL_VARIABLES:
			return "inline local variables.";
		case INLINE_PROPERTIES:
			return "inline properties.";
		case INLINE_VARIABLES:
			return "inline variables.";
		case OPTIMIZE_PARAMETERS:
			return "optimize parameters.";
		case OPTIMIZE_RETURNS:
			return "optimize returns.";
		case OUTPUT_JS_STRING_USAGE:
			return "output JavaScript string usage.";
		case REMOVE_DEAD_CODE:
			return "remove dead code.";
		case REMOVE_UNUSED_CLASS_PROPERTIES:
			return "remove unused class properties.";
		case REMOVE_UNUSED_LOCAL_VARS:
			return "remove unused local variables.";
		case REMOVE_UNUSED_PROTOTYPE_PROPERTIES:
			return "remove unused prototype properties.";
		case REMOVE_UNUSED_PROTOTYPE_PROPERTIES_IN_EXTERNS:
			return "remove unused prototype properties in externs.";
		case REMOVE_UNUSED_VARS:
			return "remove unused variables.";
		case REWRITE_FUNCTIONS_EXPRESSIONS:
			return "rewrite functions expressions.";
		case SMART_NAME_REMOVAL:
			return "smart name removal.";
		case OPTIMIZE_ARGUMENTS_ARRAY:
			return "optimize arguments array.";
		case OPTIMIZE_CALLS:
			return "optimize calls.";
		}
		return "unknown";
	}

	public static String getCheckLabel(CheckType type) {
		switch (type) {
		case CHAIN_CALLS:
			return "Chains calls to functions that return this.";
		case CHECK_CAJA:
			return "Checks that the syntactic restrictions of Caja are met.";
		case CHECK_CONTROL_SCTRUCTURES:
			return "Checks for invalid control structure.";
		case CHECK_SUSPICIOUS_CODE:
			return "Checks for suspicious code.";
		case CHECK_SYMBOLS:
			return "Checks that all symbols are defined.";
		case CHECK_TYPES:
			return "Checks types on expressions.";
		case COMPUTE_FUNCTION_SIDE_EFFECTS:
			return "Use @nosideeffects annotations, function bodies and name graph to determine if calls have side effects.  Requires --check_types.";
		case TIGHTEN_TYPES:
			return "Tightens types based on a global analysis. Experimental.";
		default:
			return "unknown";
		}
	}

	public static String getWarningLabel(WarningType type) {
		switch (type) {
		case ACCESS_CONTROLS:
			return "accessControls";
		case AGGRESSIVE_VAR_CHECK:
			return "aggressiveVarCheck";
		case AMBIGUOUS_FUNCTION_DECL:
			return "ambiguousFunctionDecl";
		case BROKEN_REQUIRES_LEVEL:
			return "brokenClosureRequiresLevel";
		case CHECK_GLOBAL_NAMES_LEVEL:
			return "checkGlobalNamesLevel";
		case CHECK_GLOBAL_THIS_LEVEL:
			return "checkGlobalThisLevel";
		case CHECK_MISSING_RETURN:
			return "checkMissingReturn";
		case CHECK_PROVIDES:
			return "checkProvides";
		case CHECK_REGEXP:
			return "checkRegExp";
		case CHECK_REQUIRES:
			return "checkRequires";
		case CHECK_STRUCT_DICT_INHERITENCE:
			return "checkStructDictInheritence";
		case CHECK_TYPES:
			return "checkTypes";
		case CHECK_UNREACHABLE_CODE:
			return "checkUnreachableCode";
		case CHECK_USELESS_CODE:
			return "checkUselessCode";
		case CHECK_VARS:
			return "checkVars";
		case CONST:
			return "const";
		case CONSTANT_PROPERTY:
			return "constantProperty";
		case DEBUGGER_STATEMENT_PRESENT:
			return "checkDebuggerStatement";
		case DEPRECATED:
			return "deprecated";
		case DUPLICATE_MESSAGES:
			return "duplicateMessage";
		case DUPLICATE_VARS:
			return "duplicateVariables";
		case ES_5_STRICT:
			return "es5Sstrict";
		case EXTERNS_VALIDATION:
			return "externsValidation";
		case FILEOVERVIEW_JSDOC:
			return "fileoverviewTags";
		case GLOBAL_THIS:
			return "globalThis";
		case INTERNET_EXPLORER_CHECKS:
			return "internetExplorerChecks";
		case INVALID_CASTS:
			return "invalidCasts";
		case MISPLACED_TYPE_ANNOTATION:
			return "misplacedTypeAnnotation";
		case MISSING_PROPERTIES:
			return "missingProperties";
		case NON_STANDARD_JS_DOCS:
			return "nonStandardJsDocs";
		case REPORT_MISSING_OVERRIDE:
			return "reportMissingOverride";
		case REPORT_UNKNOWN_TYPES:
			return "reportUnknownTypes";
		case STRICT_MODULE_DEP_CHECK:
			return "strictModuleDepCheck";
		case SUSPICIOUS_CODE:
			return "suspiciousCode";
		case TWEAKS:
			return "tweakValidation";
		case TYPE_INVALIDATION:
			return "typeInvalidation";
		case UNDEFINED_NAMES:
			return "undefinedNames";
		case UNDEFINED_VARS:
			return "undefinedVars";
		case UNKNOWN_DEFINES:
			return "unknownDefines";
		case VIOLATED_MODULE_DEP:
			return "violatedModuleDependencies";
		case VISIBILITY:
			return "visibility";
		case CHECK_EVENTFUL_OBJECT_DISPOSAL:
			return "checkEventfulObjectDisposal";
		case ES3:
			return "es3";
		case UNNECESSARY_CASTS:
			break;
		default:
			break;
		}
		return null;
	}

	public static String getWarningDescription(WarningType type) {
		switch (type) {
		case ACCESS_CONTROLS:
			return "Warnings when @deprecated, @private, or @protected are violated.";
		case AGGRESSIVE_VAR_CHECK:
			return "Warnings when the compiler sees suspicious variable definitions and undefined variables.";
		case AMBIGUOUS_FUNCTION_DECL:
			return "Warnings about ambiguous definitions of functions.";
		case BROKEN_REQUIRES_LEVEL:
			return "Warnings about bad Closure require calls.";
		case CHECK_GLOBAL_NAMES_LEVEL:
			return "Checks the integrity of references to qualified global names.(e.g. \"a.b\").";
		case CHECK_GLOBAL_THIS_LEVEL:
			return "Checks for certain uses of the {@code this} keyword that are considered unsafe.";
		case CHECK_MISSING_RETURN:
			return "Checks for missing return statements.";
		case CHECK_PROVIDES:
			return "Checks for missing goog.provides() calls.";
		case CHECK_REGEXP:
			return "Checks for regular expression literal problems.";
		case CHECK_REQUIRES:
			return "Checks for missing goog.require() calls.";
		case CHECK_STRUCT_DICT_INHERITENCE:
			return "Checks for struct dict inheritance.";
		case CHECK_TYPES:
			return "Performs type-checking.";
		case CHECK_UNREACHABLE_CODE:
			return "Checks for unreachable code.";
		case CHECK_USELESS_CODE:
			return "Checks useless code.";
		case CHECK_VARS:
			return "Warnings when vars are not declared.";
		case CONST:
			return "Warnings when @const annotated properties or variables are reassigned or deleted.";
		case CONSTANT_PROPERTY:
			return "Warnings when @const annotated properties are reassigned or deleted.";
		case DEBUGGER_STATEMENT_PRESENT:
			return "Checks for the use of the debugger statement.";
		case DEPRECATED:
			return "Warnings when non-deprecated code accesses code that's marked @deprecated.";
		case DUPLICATE_MESSAGES:
			return "Warnings when the compiler sees duplicate message.";
		case DUPLICATE_VARS:
			return "Warnings when the compiler sees duplicate variables.";
		case ES_5_STRICT:
			return "Warnings about code that is invalid in EcmaScript 5 Strict mode.";
		case EXTERNS_VALIDATION:
			return "Warnings about malformed externs files.";
		case FILEOVERVIEW_JSDOC:
			return "Warnings about duplicate @fileoverview tags.";
		case GLOBAL_THIS:
			return "Warnings about improper use of the global this.";
		case INTERNET_EXPLORER_CHECKS:
			return "Warnings about syntax errors on Internet Explorer, like trailing commas.";
		case INVALID_CASTS:
			return "Warnings about invalid type casts.";
		case MISPLACED_TYPE_ANNOTATION:
			return "Warnings about misplaced type annotation.";
		case MISSING_PROPERTIES:
			return "Warnings about whether a property will ever be defined on an object. Part of type-checking.";
		case NON_STANDARD_JS_DOCS:
			return "Warnings when JSDoc has annotations that the compiler thinks you misspelled.";
		case REPORT_MISSING_OVERRIDE:
			return "Warnings when a property is missing the @override annotation, but it overrides a base class property.";
		case REPORT_UNKNOWN_TYPES:
			return "Warninga about every node whose type could not be determined.";
		case STRICT_MODULE_DEP_CHECK:
			return "Warnings about all references potentially violating module dependencies.";
		case SUSPICIOUS_CODE:
			return "Warning about things like missing missing semicolons and comparisons to NaN.";
		case TWEAKS:
			return "Performs tweak validation.";
		case TYPE_INVALIDATION:
			return "Warnings about type invalidation.";
		case UNDEFINED_NAMES:
			return "Warnings when properties of global names are undefined.";
		case UNDEFINED_VARS:
			return "Warnings when variables are undefined.";
		case UNKNOWN_DEFINES:
			return "Warnings when unknown @define values are specified.";
		case VIOLATED_MODULE_DEP:
			return "Warnings about violated module dependencies.";
		case VISIBILITY:
			return "Warnings when @private and @protected are violated.";
		case CHECK_EVENTFUL_OBJECT_DISPOSAL:
			return "Check to ensure there exists a path to dispose of each eventful object created.";
		case ES3:
			return "Checks ES3-specifix stuff such as treling comas and keywords in names of properties.";
		case UNNECESSARY_CASTS:
			return "Warnings when unnecessary type cast is found.";
		default:
			break;
		}
		return null;
	}

}
