package com.digiarea.closure.model.export;

public interface IClosureCLConstants {

	// CLOSURE COMPILER

	public static final String JS = "--js";
	public static final String JS_EXTERNS = "--externs";
	public static final String JS_OUTPUT = "--js_output_file";

	public static final String JS_VARIABLE_MAP_INPUT = "--variable_map_input_file";
	public static final String JS_VARIABLE_MAP_OUTPUT = "--variable_map_output_file";

	public static final String JS_PROPERTY_MAP_INPUT = "--property_map_input_file";
	public static final String JS_PROPERTY_MAP_OUTPUT = "--property_map_output_file";

	//TODO implement
	public static final String JS_OUTPUT_WRAPPER = "--output_wrapper";

	public static final String JS_SOURCE_MAP = "--create_source_map";
	public static final String JS_SOURCE_MAP_FORMAT = "--source_map_format";

	public static final String JS_ERROR = "--jscomp_error";
	public static final String JS_WARNING = "--jscomp_warning";
	public static final String JS_OFF = "--jscomp_off";

	public static final String JS_DEFINE = "--define";
	public static final String JS_CHARSET = "--charset";

	public static final String JS_GENERATE_EXPORTS = "--generate_exports";
	
	public static final String JS_FORMATTING = "--formatting";
	public static final String JS_FORMATTING_PRETTY_PRINT = "PRETTY_PRINT";
	public static final String JS_FORMATTING_PRINT_INPUT_DELIMITER = "PRINT_INPUT_DELIMITER";
	public static final String JS_FORMATTING_SINGLE_QUOTES = "SINGLE_QUOTES";

	public static final String JS_PROCESS_CLOSURE_PRIMITIVES = "--process_closure_primitives";
	public static final String JS_PROCESS_JQUERY_PRIMITIVES = "--process_jquery_primitives";
	public static final String JS_PROCESS_ANGULAR = "--angular_pass";

	public static final String JS_ACCEPT_CONST = "--accept_const_keyword";
	public static final String JS_LANGUAGE_IN = "--language_in";

	public static final String JS_TRANSLATION_FILE = "--translations_file";
	public static final String JS_TRANSLATION_PROJECT = "--translations_project";

	public static final String JS_EXTRA_ANNOTATIONS_NAME = "--extra_annotation_name";

	// TODO ?
	public static final String JS_FLAG_FILE = "--flagfile";
	public static final String JS_ONLY_CUSTOM_EXTERNS = "--use_only_custom_externs";
	public static final String JS_COMPILATION_LEVEL = "--compilation_level";
	public static final String JS_SUMMARY_LEVEL = "--summary_detail_level";
	public static final String JS_CREATE_MAP = "--create_name_map_files";
	public static final String JS_WARNING_LEVEL = "--warning_level";
	public static final String JS_MODULE_OUTPUT_PATH_PREFIX ="--module_output_path_prefix";
	public static final String JS_MODULE_WRAPPER ="--module_wrapper";
	public static final String JS_SUMMARY_DETAIL_LEVEL = "--summary_detail_level";
	public static final String JS_MODULE = "--module";
	public static final String JS_LOGGING_LEVEL = "--logging_level";
	public static final String JS_PRINT_PASS_GRAPH = "--print_pass_graph";
	public static final String JS_PRINT_AST = "--print_ast";
	public static final String JS_PRINT_TREE = "--print_tree";
	public static final String JS_THIRD_PARTY = "--third_party";
	public static final String JS_TYPE_OPTIMIZATION = "--use_types_for_optimization";
	public static final String JS_DEBUG = "--debug";
	public static final String JS_PROCESS_COMMONJS = "--process_common_js_modules";
	public static final String JS_PROCESS_COMMONJS_PATH = "--common_js_module_path_prefix";
	public static final String JS_PROCESS_COMMONJS_MODULE = "--common_js_entry_module";
	public static final String JS_PROCESS_AMD_MODULE = "--transform_amd_modules";
	public static final String JS_MANAGE_DEPENDENCIES = "--manage_closure_dependencies";
	public static final String JS_ONLY_CLOSURE_DEPENDENCIES = "--only_closure_dependencies";
	public static final String JS_CLOSURE_ENTRY_POINT = "--closure_entry_point";
	public static final String JS_OUTPUT_MANIFEST = "--output_manifest";
	public static final String JS_OUTPUT_MODULE = "--output_module_dependencies";
	public static final String JS_WARNINGS_WHITELIST = "--warnings_whitelist_file";
	public static final String JS_TRACER_MODE = "--tracer_mode";
	public static final String JS_VERSION ="--version";

}
