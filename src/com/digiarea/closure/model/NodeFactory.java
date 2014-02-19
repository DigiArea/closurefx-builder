package com.digiarea.closure.model;

import java.util.List;

class NodeFactory {

    public static GssExcludedClasses GssExcludedClasses() {
        return new GssExcludedClasses();
    }

    public static GssExcludedClasses GssExcludedClasses(List<GssExcludedClass> gssExcludedClass) {
        return new GssExcludedClasses(gssExcludedClass);
    }

    public static Warning Warning() {
        return new Warning();
    }

    public static Warning Warning(SeverityType severity, WarningType type) {
        return new Warning(severity, type);
    }

    public static GssAtRule GssAtRule() {
        return new GssAtRule();
    }

    public static GssAtRule GssAtRule(String value) {
        return new GssAtRule(value);
    }

    public static GssUnrecognizeProperties GssUnrecognizeProperties() {
        return new GssUnrecognizeProperties();
    }

    public static GssUnrecognizeProperties GssUnrecognizeProperties(List<GssUnrecognizeProperty> gssUnrecognizeProperty) {
        return new GssUnrecognizeProperties(gssUnrecognizeProperty);
    }

    public static Optimizations Optimizations() {
        return new Optimizations();
    }

    public static Optimizations Optimizations(List<Optimization> optimization) {
        return new Optimizations(optimization);
    }

    public static ClosureJs ClosureJs() {
        return new ClosureJs();
    }

    public static ClosureJs ClosureJs(Info info, Buildpath buildpath, Output output, String outputWrapper, Warnings warnings, Checks checks, Optimizations optimizations, JsDocs jsDocs, Language language, JsDefines jsDefines, JsRenaming renaming, boolean build, boolean acceptConstKeyword, boolean closureStyle, boolean closurePass, boolean jqueryPass, boolean angularPass, String charset, boolean manageClosureDependencies, boolean externExports, boolean generateExports, String externExportsPath, String translationsFile, String translationsProject, String sourceMapFile, JsSourceMapFormat sourceMapFormat, boolean printInputDelimeter, String inputDelimiter, boolean lineBreaks, boolean lineBreaksAggressive, boolean singleQuotes, boolean prettyPrint, boolean skipAllPasses, boolean functionsOnly, boolean debug, boolean devmode) {
        return new ClosureJs(info, buildpath, output, outputWrapper, warnings, checks, optimizations, jsDocs, language, jsDefines, renaming, build, acceptConstKeyword, closureStyle, closurePass, jqueryPass, angularPass, charset, manageClosureDependencies, externExports, generateExports, externExportsPath, translationsFile, translationsProject, sourceMapFile, sourceMapFormat, printInputDelimeter, inputDelimiter, lineBreaks, lineBreaksAggressive, singleQuotes, prettyPrint, skipAllPasses, functionsOnly, debug, devmode);
    }

    public static JsFunctionMap JsFunctionMap() {
        return new JsFunctionMap();
    }

    public static JsFunctionMap JsFunctionMap(String input, String output) {
        return new JsFunctionMap(input, output);
    }

    public static JsDoc JsDoc() {
        return new JsDoc();
    }

    public static JsDoc JsDoc(String value) {
        return new JsDoc(value);
    }

    public static ClosureGss ClosureGss() {
        return new ClosureGss();
    }

    public static ClosureGss ClosureGss(Info info, Buildpath buildpath, Output output, String copyrightNotice, GssDefines gssDefines, GssNonStandardFunctions gssNonStandardFunctions, GssUnrecognizeProperties gssUnrecognizeProperties, GssAtRules gssAtRules, GssExcludedClasses gssExcludedClasses, boolean build, boolean allowUnrecognizedFunctions, boolean allowUnrecognizedProperties, String cssRenamingPrefix, String outputRenamingMap, GssRenamingType renamingType, GssVendor vendor, GssOutputRenamingMapFormat outputRenamingMapFormat, GssOptimizationLevel optimizationLevel, GssOutputFormat outputFormat, GssInputOrientation inputOrientation, GssOutputOrientation outputOrientation) {
        return new ClosureGss(info, buildpath, output, copyrightNotice, gssDefines, gssNonStandardFunctions, gssUnrecognizeProperties, gssAtRules, gssExcludedClasses, build, allowUnrecognizedFunctions, allowUnrecognizedProperties, cssRenamingPrefix, outputRenamingMap, renamingType, vendor, outputRenamingMapFormat, optimizationLevel, outputFormat, inputOrientation, outputOrientation);
    }

    public static Check Check() {
        return new Check();
    }

    public static Check Check(boolean check, CheckType type) {
        return new Check(check, type);
    }

    public static JsDocs JsDocs() {
        return new JsDocs();
    }

    public static JsDocs JsDocs(List<JsDoc> jsDoc) {
        return new JsDocs(jsDoc);
    }

    public static GssDefine GssDefine() {
        return new GssDefine();
    }

    public static GssDefine GssDefine(String value) {
        return new GssDefine(value);
    }

    public static GssNonStandardFunctions GssNonStandardFunctions() {
        return new GssNonStandardFunctions();
    }

    public static GssNonStandardFunctions GssNonStandardFunctions(List<GssNonStandardFunction> gssNonStandardFunction) {
        return new GssNonStandardFunctions(gssNonStandardFunction);
    }

    public static ClosureSoy ClosureSoy() {
        return new ClosureSoy();
    }

    public static ClosureSoy ClosureSoy(Info info, Buildpath buildpath, SoyLocales soyLocales, SoyCssSchemeType cssScheme, SoyCodeStyle codeStyle, boolean build, boolean allowExternalCalls, boolean allowDeprecatedSyntax, boolean usingIjData, boolean generateJsDoc, boolean provideRequireSoyNamespaces, boolean provideRequireJsFunctions, boolean declareTopLevelNamespace, boolean generateGoogMessagesDefs, boolean googMessagesExternal, boolean rightToLeftDir, boolean rightToLeftDirGoog, String globalsPath, String messagesPath, String outputPath) {
        return new ClosureSoy(info, buildpath, soyLocales, cssScheme, codeStyle, build, allowExternalCalls, allowDeprecatedSyntax, usingIjData, generateJsDoc, provideRequireSoyNamespaces, provideRequireJsFunctions, declareTopLevelNamespace, generateGoogMessagesDefs, googMessagesExternal, rightToLeftDir, rightToLeftDirGoog, globalsPath, messagesPath, outputPath);
    }

    public static SoyLocale SoyLocale() {
        return new SoyLocale();
    }

    public static SoyLocale SoyLocale(String value) {
        return new SoyLocale(value);
    }

    public static ExcludeInputFilter ExcludeInputFilter() {
        return new ExcludeInputFilter();
    }

    public static ExcludeInputFilter ExcludeInputFilter(List<InputFilterPattern> pattern) {
        return new ExcludeInputFilter(pattern);
    }

    public static JsPropertyMap JsPropertyMap() {
        return new JsPropertyMap();
    }

    public static JsPropertyMap JsPropertyMap(String input, String output) {
        return new JsPropertyMap(input, output);
    }

    public static Checks Checks() {
        return new Checks();
    }

    public static Checks Checks(List<Check> check) {
        return new Checks(check);
    }

    public static IncludeInputFilter IncludeInputFilter() {
        return new IncludeInputFilter();
    }

    public static IncludeInputFilter IncludeInputFilter(List<InputFilterPattern> pattern) {
        return new IncludeInputFilter(pattern);
    }

    public static GssDefines GssDefines() {
        return new GssDefines();
    }

    public static GssDefines GssDefines(List<GssDefine> gssDefine) {
        return new GssDefines(gssDefine);
    }

    public static Optimization Optimization() {
        return new Optimization();
    }

    public static Optimization Optimization(boolean optimize, OptimizationType type) {
        return new Optimization(optimize, type);
    }

    public static Buildpath Buildpath() {
        return new Buildpath();
    }

    public static Buildpath Buildpath(List<Source> source) {
        return new Buildpath(source);
    }

    public static InputFilterPattern InputFilterPattern() {
        return new InputFilterPattern();
    }

    public static InputFilterPattern InputFilterPattern(String value, InputFilterType type) {
        return new InputFilterPattern(value, type);
    }

    public static Warnings Warnings() {
        return new Warnings();
    }

    public static Warnings Warnings(List<Warning> warning) {
        return new Warnings(warning);
    }

    public static JsRenaming JsRenaming() {
        return new JsRenaming();
    }

    public static JsRenaming JsRenaming(JsVariableMap variableMap, JsFunctionMap functionMap, JsPropertyMap propertyMap, JsRenamingVariablePolice variablePolice, JsRenamingFunctionPolice functionPolice, JsRenamingPropertyPolice propertyPolice, String prefix, String prefixNamespace, boolean devirtualizePrototypeMethods, boolean generatePseudoNames, boolean shadowVariables, boolean propertyAffinity, boolean disambiguateProperties, boolean ambiguateProperties, boolean exportTestFunctions, boolean renameLabels) {
        return new JsRenaming(variableMap, functionMap, propertyMap, variablePolice, functionPolice, propertyPolice, prefix, prefixNamespace, devirtualizePrototypeMethods, generatePseudoNames, shadowVariables, propertyAffinity, disambiguateProperties, ambiguateProperties, exportTestFunctions, renameLabels);
    }

    public static SoyLocales SoyLocales() {
        return new SoyLocales();
    }

    public static SoyLocales SoyLocales(List<SoyLocale> soyLocale) {
        return new SoyLocales(soyLocale);
    }

    public static GssAtRules GssAtRules() {
        return new GssAtRules();
    }

    public static GssAtRules GssAtRules(List<GssAtRule> gssAtRule) {
        return new GssAtRules(gssAtRule);
    }

    public static JsDefines JsDefines() {
        return new JsDefines();
    }

    public static JsDefines JsDefines(List<JsDefine> jsDefine) {
        return new JsDefines(jsDefine);
    }

    public static GssUnrecognizeProperty GssUnrecognizeProperty() {
        return new GssUnrecognizeProperty();
    }

    public static GssUnrecognizeProperty GssUnrecognizeProperty(String value) {
        return new GssUnrecognizeProperty(value);
    }

    public static Language Language() {
        return new Language();
    }

    public static Language Language(LangType input, LangType output) {
        return new Language(input, output);
    }

    public static Source Source() {
        return new Source();
    }

    public static Source Source(ExcludeInputFilter excluded, IncludeInputFilter included, boolean extern, String path, SourceEntry entryKind, SourceEntity entityKind, boolean includeClosure, boolean includeSimple) {
        return new Source(excluded, included, extern, path, entryKind, entityKind, includeClosure, includeSimple);
    }

    public static Info Info() {
        return new Info();
    }

    public static Info Info(String id, String version, String name, String vendor) {
        return new Info(id, version, name, vendor);
    }

    public static GssNonStandardFunction GssNonStandardFunction() {
        return new GssNonStandardFunction();
    }

    public static GssNonStandardFunction GssNonStandardFunction(String value) {
        return new GssNonStandardFunction(value);
    }

    public static Closure Closure() {
        return new Closure();
    }

    public static Closure Closure(ClosureJs closureJs, ClosureGss closureGss, ClosureSoy closureSoy) {
        return new Closure(closureJs, closureGss, closureSoy);
    }

    public static GssExcludedClass GssExcludedClass() {
        return new GssExcludedClass();
    }

    public static GssExcludedClass GssExcludedClass(String value) {
        return new GssExcludedClass(value);
    }

    public static JsDefine JsDefine() {
        return new JsDefine();
    }

    public static JsDefine JsDefine(String name, String value, JsDefineType type) {
        return new JsDefine(name, value, type);
    }

    public static Output Output() {
        return new Output();
    }

    public static Output Output(String path, String file) {
        return new Output(path, file);
    }

    public static JsVariableMap JsVariableMap() {
        return new JsVariableMap();
    }

    public static JsVariableMap JsVariableMap(String input, String output) {
        return new JsVariableMap(input, output);
    }

}
