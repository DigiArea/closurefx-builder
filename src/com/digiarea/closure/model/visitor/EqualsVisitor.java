package com.digiarea.closure.model.visitor;

import java.util.List;

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
import com.digiarea.closure.model.Node;
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

@SuppressWarnings("unchecked")
public class EqualsVisitor implements GenericVisitor<Boolean, Node> {

    private static final EqualsVisitor SINGLETON = new EqualsVisitor();

    public static boolean equals(Node n1, Node n2) throws Exception {
        return SINGLETON.nodeEquals(n1, n2);
    }

    protected <T extends Node> boolean nodeEquals(T n1, T n2) throws Exception {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        if (n1.getClass() != n2.getClass()) {
            return false;
        }
        return n1.accept(this, n2).booleanValue();
    }

    protected <T extends Node> boolean nodesEquals(List<T> nodes1, List<T> nodes2) throws Exception {
        if (nodes1 == null) {
            if (nodes2 == null) {
                return true;
            }
            return false;
        } else if (nodes2 == null) {
            return false;
        }
        if (nodes1.size() != nodes2.size()) {
            return false;
        }
        for (int i = 0; i < nodes1.size(); i++) {
            if (!nodeEquals(nodes1.get(i), nodes2.get(i))) {
                return false;
            }
        }
        return true;
    }

    protected boolean objEquals(Object n1, Object n2) {
        if (n1 == n2) {
            return true;
        }
        if (n1 == null) {
            if (n2 == null) {
                return true;
            }
            return false;
        } else if (n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    @Override
    public Boolean visit(GssRenamingType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(LangType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssExcludedClasses n, Node ctx) throws Exception {
        GssExcludedClasses x = (GssExcludedClasses) ctx;
        if (!nodesEquals(n.getGssExcludedClass(), x.getGssExcludedClass())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOutputOrientation n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Warning n, Node ctx) throws Exception {
        Warning x = (Warning) ctx;
        if (n.getSeverity() != x.getSeverity()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssAtRule n, Node ctx) throws Exception {
        GssAtRule x = (GssAtRule) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssUnrecognizeProperties n, Node ctx) throws Exception {
        GssUnrecognizeProperties x = (GssUnrecognizeProperties) ctx;
        if (!nodesEquals(n.getGssUnrecognizeProperty(), x.getGssUnrecognizeProperty())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(InputFilterType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SourceEntry n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDefineType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Optimizations n, Node ctx) throws Exception {
        Optimizations x = (Optimizations) ctx;
        if (!nodesEquals(n.getOptimization(), x.getOptimization())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SoyCodeStyle n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SourceEntity n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(ClosureJs n, Node ctx) throws Exception {
        ClosureJs x = (ClosureJs) ctx;
        if (!nodeEquals(n.getInfo(), x.getInfo())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBuildpath(), x.getBuildpath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getOutput(), x.getOutput())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutputWrapper(), x.getOutputWrapper())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getWarnings(), x.getWarnings())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getChecks(), x.getChecks())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getOptimizations(), x.getOptimizations())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getJsDocs(), x.getJsDocs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getLanguage(), x.getLanguage())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getJsDefines(), x.getJsDefines())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getRenaming(), x.getRenaming())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isBuild(), x.isBuild())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAcceptConstKeyword(), x.isAcceptConstKeyword())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isClosureStyle(), x.isClosureStyle())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isClosurePass(), x.isClosurePass())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isJqueryPass(), x.isJqueryPass())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAngularPass(), x.isAngularPass())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getCharset(), x.getCharset())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isManageClosureDependencies(), x.isManageClosureDependencies())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isExternExports(), x.isExternExports())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isGenerateExports(), x.isGenerateExports())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getExternExportsPath(), x.getExternExportsPath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getTranslationsFile(), x.getTranslationsFile())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getTranslationsProject(), x.getTranslationsProject())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getSourceMapFile(), x.getSourceMapFile())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getSourceMapFormat() != x.getSourceMapFormat()) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isPrintInputDelimeter(), x.isPrintInputDelimeter())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getInputDelimiter(), x.getInputDelimiter())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isLineBreaks(), x.isLineBreaks())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isLineBreaksAggressive(), x.isLineBreaksAggressive())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isSingleQuotes(), x.isSingleQuotes())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isPrettyPrint(), x.isPrettyPrint())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isSkipAllPasses(), x.isSkipAllPasses())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isFunctionsOnly(), x.isFunctionsOnly())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isDebug(), x.isDebug())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isDevmode(), x.isDevmode())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssVendor n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsFunctionMap n, Node ctx) throws Exception {
        JsFunctionMap x = (JsFunctionMap) ctx;
        if (!objEquals(n.getInput(), x.getInput())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutput(), x.getOutput())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsDoc n, Node ctx) throws Exception {
        JsDoc x = (JsDoc) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClosureGss n, Node ctx) throws Exception {
        ClosureGss x = (ClosureGss) ctx;
        if (!nodeEquals(n.getInfo(), x.getInfo())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBuildpath(), x.getBuildpath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getOutput(), x.getOutput())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getCopyrightNotice(), x.getCopyrightNotice())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getGssDefines(), x.getGssDefines())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getGssNonStandardFunctions(), x.getGssNonStandardFunctions())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getGssUnrecognizeProperties(), x.getGssUnrecognizeProperties())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getGssAtRules(), x.getGssAtRules())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getGssExcludedClasses(), x.getGssExcludedClasses())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isBuild(), x.isBuild())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAllowUnrecognizedFunctions(), x.isAllowUnrecognizedFunctions())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAllowUnrecognizedProperties(), x.isAllowUnrecognizedProperties())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getCssRenamingPrefix(), x.getCssRenamingPrefix())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutputRenamingMap(), x.getOutputRenamingMap())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getRenamingType() != x.getRenamingType()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getVendor() != x.getVendor()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOutputRenamingMapFormat() != x.getOutputRenamingMapFormat()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOptimizationLevel() != x.getOptimizationLevel()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOutputFormat() != x.getOutputFormat()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getInputOrientation() != x.getInputOrientation()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOutputOrientation() != x.getOutputOrientation()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Check n, Node ctx) throws Exception {
        Check x = (Check) ctx;
        if (!objEquals(n.isCheck(), x.isCheck())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsSourceMapFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDocs n, Node ctx) throws Exception {
        JsDocs x = (JsDocs) ctx;
        if (!nodesEquals(n.getJsDoc(), x.getJsDoc())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssDefine n, Node ctx) throws Exception {
        GssDefine x = (GssDefine) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssNonStandardFunctions n, Node ctx) throws Exception {
        GssNonStandardFunctions x = (GssNonStandardFunctions) ctx;
        if (!nodesEquals(n.getGssNonStandardFunction(), x.getGssNonStandardFunction())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ClosureSoy n, Node ctx) throws Exception {
        ClosureSoy x = (ClosureSoy) ctx;
        if (!nodeEquals(n.getInfo(), x.getInfo())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getBuildpath(), x.getBuildpath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getSoyLocales(), x.getSoyLocales())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getCssScheme() != x.getCssScheme()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getCodeStyle() != x.getCodeStyle()) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isBuild(), x.isBuild())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAllowExternalCalls(), x.isAllowExternalCalls())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAllowDeprecatedSyntax(), x.isAllowDeprecatedSyntax())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isUsingIjData(), x.isUsingIjData())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isGenerateJsDoc(), x.isGenerateJsDoc())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isProvideRequireSoyNamespaces(), x.isProvideRequireSoyNamespaces())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isProvideRequireJsFunctions(), x.isProvideRequireJsFunctions())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isDeclareTopLevelNamespace(), x.isDeclareTopLevelNamespace())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isGenerateGoogMessagesDefs(), x.isGenerateGoogMessagesDefs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isGoogMessagesExternal(), x.isGoogMessagesExternal())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isRightToLeftDir(), x.isRightToLeftDir())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isRightToLeftDirGoog(), x.isRightToLeftDirGoog())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getGlobalsPath(), x.getGlobalsPath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getMessagesPath(), x.getMessagesPath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutputPath(), x.getOutputPath())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOutputFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SoyLocale n, Node ctx) throws Exception {
        SoyLocale x = (SoyLocale) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(ExcludeInputFilter n, Node ctx) throws Exception {
        ExcludeInputFilter x = (ExcludeInputFilter) ctx;
        if (!nodesEquals(n.getPattern(), x.getPattern())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsPropertyMap n, Node ctx) throws Exception {
        JsPropertyMap x = (JsPropertyMap) ctx;
        if (!objEquals(n.getInput(), x.getInput())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutput(), x.getOutput())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssInputOrientation n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssOutputRenamingMapFormat n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Checks n, Node ctx) throws Exception {
        Checks x = (Checks) ctx;
        if (!nodesEquals(n.getCheck(), x.getCheck())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SoyCssSchemeType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(IncludeInputFilter n, Node ctx) throws Exception {
        IncludeInputFilter x = (IncludeInputFilter) ctx;
        if (!nodesEquals(n.getPattern(), x.getPattern())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssDefines n, Node ctx) throws Exception {
        GssDefines x = (GssDefines) ctx;
        if (!nodesEquals(n.getGssDefine(), x.getGssDefine())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Optimization n, Node ctx) throws Exception {
        Optimization x = (Optimization) ctx;
        if (!objEquals(n.isOptimize(), x.isOptimize())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Buildpath n, Node ctx) throws Exception {
        Buildpath x = (Buildpath) ctx;
        if (!nodesEquals(n.getSource(), x.getSource())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingVariablePolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(InputFilterPattern n, Node ctx) throws Exception {
        InputFilterPattern x = (InputFilterPattern) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(WarningType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Warnings n, Node ctx) throws Exception {
        Warnings x = (Warnings) ctx;
        if (!nodesEquals(n.getWarning(), x.getWarning())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenaming n, Node ctx) throws Exception {
        JsRenaming x = (JsRenaming) ctx;
        if (!nodeEquals(n.getVariableMap(), x.getVariableMap())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getFunctionMap(), x.getFunctionMap())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getPropertyMap(), x.getPropertyMap())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getVariablePolice() != x.getVariablePolice()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getFunctionPolice() != x.getFunctionPolice()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getPropertyPolice() != x.getPropertyPolice()) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getPrefix(), x.getPrefix())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getPrefixNamespace(), x.getPrefixNamespace())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isDevirtualizePrototypeMethods(), x.isDevirtualizePrototypeMethods())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isGeneratePseudoNames(), x.isGeneratePseudoNames())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isShadowVariables(), x.isShadowVariables())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isPropertyAffinity(), x.isPropertyAffinity())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isDisambiguateProperties(), x.isDisambiguateProperties())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isAmbiguateProperties(), x.isAmbiguateProperties())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isExportTestFunctions(), x.isExportTestFunctions())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isRenameLabels(), x.isRenameLabels())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(OptimizationType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(SoyLocales n, Node ctx) throws Exception {
        SoyLocales x = (SoyLocales) ctx;
        if (!nodesEquals(n.getSoyLocale(), x.getSoyLocale())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssAtRules n, Node ctx) throws Exception {
        GssAtRules x = (GssAtRules) ctx;
        if (!nodesEquals(n.getGssAtRule(), x.getGssAtRule())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingPropertyPolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(JsDefines n, Node ctx) throws Exception {
        JsDefines x = (JsDefines) ctx;
        if (!nodesEquals(n.getJsDefine(), x.getJsDefine())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(SeverityType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(GssUnrecognizeProperty n, Node ctx) throws Exception {
        GssUnrecognizeProperty x = (GssUnrecognizeProperty) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Language n, Node ctx) throws Exception {
        Language x = (Language) ctx;
        if (n.getInput() != x.getInput()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getOutput() != x.getOutput()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssOptimizationLevel n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(CheckType n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Source n, Node ctx) throws Exception {
        Source x = (Source) ctx;
        if (!nodeEquals(n.getExcluded(), x.getExcluded())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getIncluded(), x.getIncluded())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isExtern(), x.isExtern())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getPath(), x.getPath())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getEntryKind() != x.getEntryKind()) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getEntityKind() != x.getEntityKind()) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isIncludeClosure(), x.isIncludeClosure())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.isIncludeSimple(), x.isIncludeSimple())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Info n, Node ctx) throws Exception {
        Info x = (Info) ctx;
        if (!objEquals(n.getId(), x.getId())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getVersion(), x.getVersion())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getVendor(), x.getVendor())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssNonStandardFunction n, Node ctx) throws Exception {
        GssNonStandardFunction x = (GssNonStandardFunction) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(Closure n, Node ctx) throws Exception {
        Closure x = (Closure) ctx;
        if (!nodeEquals(n.getClosureJs(), x.getClosureJs())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getClosureGss(), x.getClosureGss())) {
            return java.lang.Boolean.FALSE;
        }
        if (!nodeEquals(n.getClosureSoy(), x.getClosureSoy())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(GssExcludedClass n, Node ctx) throws Exception {
        GssExcludedClass x = (GssExcludedClass) ctx;
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsDefine n, Node ctx) throws Exception {
        JsDefine x = (JsDefine) ctx;
        if (!objEquals(n.getName(), x.getName())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getValue(), x.getValue())) {
            return java.lang.Boolean.FALSE;
        }
        if (n.getType() != x.getType()) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsRenamingFunctionPolice n, Node ctx) throws Exception {
        return java.lang.Boolean.FALSE;
    }

    @Override
    public Boolean visit(Output n, Node ctx) throws Exception {
        Output x = (Output) ctx;
        if (!objEquals(n.getPath(), x.getPath())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getFile(), x.getFile())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    @Override
    public Boolean visit(JsVariableMap n, Node ctx) throws Exception {
        JsVariableMap x = (JsVariableMap) ctx;
        if (!objEquals(n.getInput(), x.getInput())) {
            return java.lang.Boolean.FALSE;
        }
        if (!objEquals(n.getOutput(), x.getOutput())) {
            return java.lang.Boolean.FALSE;
        }
        return java.lang.Boolean.TRUE;
    }

    public EqualsVisitor() {
        super();
    }

}
