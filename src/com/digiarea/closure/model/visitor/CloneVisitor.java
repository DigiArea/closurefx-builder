package com.digiarea.closure.model.visitor;

import java.util.ArrayList;
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
import com.digiarea.closure.model.NodeFacade;
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
public class CloneVisitor<C> implements GenericVisitor<Node, C> {

    @Override
    public Node visit(GssRenamingType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(LangType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(GssExcludedClasses n, C ctx) throws Exception {
        GssExcludedClasses img = NodeFacade.GssExcludedClasses();
        if (n.getGssExcludedClass() != null) {
            List<GssExcludedClass> gssExcludedClass = new ArrayList<GssExcludedClass>();
            for (GssExcludedClass item : n.getGssExcludedClass()) {
                if (item != null) {
                    gssExcludedClass.add((GssExcludedClass) item.accept(this, ctx));
                }
            }
            img.setGssExcludedClass(gssExcludedClass);
        }
        return img;
    }

    @Override
    public Node visit(GssOutputOrientation n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Warning n, C ctx) throws Exception {
        Warning img = NodeFacade.Warning();
        img.setSeverity(n.getSeverity());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Node visit(GssAtRule n, C ctx) throws Exception {
        GssAtRule img = NodeFacade.GssAtRule();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(GssUnrecognizeProperties n, C ctx) throws Exception {
        GssUnrecognizeProperties img = NodeFacade.GssUnrecognizeProperties();
        if (n.getGssUnrecognizeProperty() != null) {
            List<GssUnrecognizeProperty> gssUnrecognizeProperty = new ArrayList<GssUnrecognizeProperty>();
            for (GssUnrecognizeProperty item : n.getGssUnrecognizeProperty()) {
                if (item != null) {
                    gssUnrecognizeProperty.add((GssUnrecognizeProperty) item.accept(this, ctx));
                }
            }
            img.setGssUnrecognizeProperty(gssUnrecognizeProperty);
        }
        return img;
    }

    @Override
    public Node visit(InputFilterType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(SourceEntry n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(JsDefineType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Optimizations n, C ctx) throws Exception {
        Optimizations img = NodeFacade.Optimizations();
        if (n.getOptimization() != null) {
            List<Optimization> optimization = new ArrayList<Optimization>();
            for (Optimization item : n.getOptimization()) {
                if (item != null) {
                    optimization.add((Optimization) item.accept(this, ctx));
                }
            }
            img.setOptimization(optimization);
        }
        return img;
    }

    @Override
    public Node visit(SoyCodeStyle n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(SourceEntity n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(ClosureJs n, C ctx) throws Exception {
        ClosureJs img = NodeFacade.ClosureJs();
        if (n.getInfo() != null) {
            img.setInfo((Info) n.getInfo().accept(this, ctx));
        }
        if (n.getBuildpath() != null) {
            img.setBuildpath((Buildpath) n.getBuildpath().accept(this, ctx));
        }
        if (n.getOutput() != null) {
            img.setOutput((Output) n.getOutput().accept(this, ctx));
        }
        img.setOutputWrapper(n.getOutputWrapper());
        if (n.getWarnings() != null) {
            img.setWarnings((Warnings) n.getWarnings().accept(this, ctx));
        }
        if (n.getChecks() != null) {
            img.setChecks((Checks) n.getChecks().accept(this, ctx));
        }
        if (n.getOptimizations() != null) {
            img.setOptimizations((Optimizations) n.getOptimizations().accept(this, ctx));
        }
        if (n.getJsDocs() != null) {
            img.setJsDocs((JsDocs) n.getJsDocs().accept(this, ctx));
        }
        if (n.getLanguage() != null) {
            img.setLanguage((Language) n.getLanguage().accept(this, ctx));
        }
        if (n.getJsDefines() != null) {
            img.setJsDefines((JsDefines) n.getJsDefines().accept(this, ctx));
        }
        if (n.getRenaming() != null) {
            img.setRenaming((JsRenaming) n.getRenaming().accept(this, ctx));
        }
        img.setBuild(n.isBuild());
        img.setAcceptConstKeyword(n.isAcceptConstKeyword());
        img.setClosureStyle(n.isClosureStyle());
        img.setClosurePass(n.isClosurePass());
        img.setJqueryPass(n.isJqueryPass());
        img.setAngularPass(n.isAngularPass());
        img.setCharset(n.getCharset());
        img.setManageClosureDependencies(n.isManageClosureDependencies());
        img.setExternExports(n.isExternExports());
        img.setGenerateExports(n.isGenerateExports());
        img.setExternExportsPath(n.getExternExportsPath());
        img.setTranslationsFile(n.getTranslationsFile());
        img.setTranslationsProject(n.getTranslationsProject());
        img.setSourceMapFile(n.getSourceMapFile());
        img.setSourceMapFormat(n.getSourceMapFormat());
        img.setPrintInputDelimeter(n.isPrintInputDelimeter());
        img.setInputDelimiter(n.getInputDelimiter());
        img.setLineBreaks(n.isLineBreaks());
        img.setLineBreaksAggressive(n.isLineBreaksAggressive());
        img.setSingleQuotes(n.isSingleQuotes());
        img.setPrettyPrint(n.isPrettyPrint());
        img.setSkipAllPasses(n.isSkipAllPasses());
        img.setFunctionsOnly(n.isFunctionsOnly());
        img.setDebug(n.isDebug());
        img.setDevmode(n.isDevmode());
        return img;
    }

    @Override
    public Node visit(GssVendor n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(JsFunctionMap n, C ctx) throws Exception {
        JsFunctionMap img = NodeFacade.JsFunctionMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Node visit(JsDoc n, C ctx) throws Exception {
        JsDoc img = NodeFacade.JsDoc();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(ClosureGss n, C ctx) throws Exception {
        ClosureGss img = NodeFacade.ClosureGss();
        if (n.getInfo() != null) {
            img.setInfo((Info) n.getInfo().accept(this, ctx));
        }
        if (n.getBuildpath() != null) {
            img.setBuildpath((Buildpath) n.getBuildpath().accept(this, ctx));
        }
        if (n.getOutput() != null) {
            img.setOutput((Output) n.getOutput().accept(this, ctx));
        }
        img.setCopyrightNotice(n.getCopyrightNotice());
        if (n.getGssDefines() != null) {
            img.setGssDefines((GssDefines) n.getGssDefines().accept(this, ctx));
        }
        if (n.getGssNonStandardFunctions() != null) {
            img.setGssNonStandardFunctions((GssNonStandardFunctions) n.getGssNonStandardFunctions().accept(this, ctx));
        }
        if (n.getGssUnrecognizeProperties() != null) {
            img.setGssUnrecognizeProperties((GssUnrecognizeProperties) n.getGssUnrecognizeProperties().accept(this, ctx));
        }
        if (n.getGssAtRules() != null) {
            img.setGssAtRules((GssAtRules) n.getGssAtRules().accept(this, ctx));
        }
        if (n.getGssExcludedClasses() != null) {
            img.setGssExcludedClasses((GssExcludedClasses) n.getGssExcludedClasses().accept(this, ctx));
        }
        img.setBuild(n.isBuild());
        img.setAllowUnrecognizedFunctions(n.isAllowUnrecognizedFunctions());
        img.setAllowUnrecognizedProperties(n.isAllowUnrecognizedProperties());
        img.setCssRenamingPrefix(n.getCssRenamingPrefix());
        img.setOutputRenamingMap(n.getOutputRenamingMap());
        img.setRenamingType(n.getRenamingType());
        img.setVendor(n.getVendor());
        img.setOutputRenamingMapFormat(n.getOutputRenamingMapFormat());
        img.setOptimizationLevel(n.getOptimizationLevel());
        img.setOutputFormat(n.getOutputFormat());
        img.setInputOrientation(n.getInputOrientation());
        img.setOutputOrientation(n.getOutputOrientation());
        return img;
    }

    @Override
    public Node visit(Check n, C ctx) throws Exception {
        Check img = NodeFacade.Check();
        img.setCheck(n.isCheck());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Node visit(JsSourceMapFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(JsDocs n, C ctx) throws Exception {
        JsDocs img = NodeFacade.JsDocs();
        if (n.getJsDoc() != null) {
            List<JsDoc> jsDoc = new ArrayList<JsDoc>();
            for (JsDoc item : n.getJsDoc()) {
                if (item != null) {
                    jsDoc.add((JsDoc) item.accept(this, ctx));
                }
            }
            img.setJsDoc(jsDoc);
        }
        return img;
    }

    @Override
    public Node visit(GssDefine n, C ctx) throws Exception {
        GssDefine img = NodeFacade.GssDefine();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(GssNonStandardFunctions n, C ctx) throws Exception {
        GssNonStandardFunctions img = NodeFacade.GssNonStandardFunctions();
        if (n.getGssNonStandardFunction() != null) {
            List<GssNonStandardFunction> gssNonStandardFunction = new ArrayList<GssNonStandardFunction>();
            for (GssNonStandardFunction item : n.getGssNonStandardFunction()) {
                if (item != null) {
                    gssNonStandardFunction.add((GssNonStandardFunction) item.accept(this, ctx));
                }
            }
            img.setGssNonStandardFunction(gssNonStandardFunction);
        }
        return img;
    }

    @Override
    public Node visit(ClosureSoy n, C ctx) throws Exception {
        ClosureSoy img = NodeFacade.ClosureSoy();
        if (n.getInfo() != null) {
            img.setInfo((Info) n.getInfo().accept(this, ctx));
        }
        if (n.getBuildpath() != null) {
            img.setBuildpath((Buildpath) n.getBuildpath().accept(this, ctx));
        }
        if (n.getSoyLocales() != null) {
            img.setSoyLocales((SoyLocales) n.getSoyLocales().accept(this, ctx));
        }
        img.setCssScheme(n.getCssScheme());
        img.setCodeStyle(n.getCodeStyle());
        img.setBuild(n.isBuild());
        img.setAllowExternalCalls(n.isAllowExternalCalls());
        img.setAllowDeprecatedSyntax(n.isAllowDeprecatedSyntax());
        img.setUsingIjData(n.isUsingIjData());
        img.setGenerateJsDoc(n.isGenerateJsDoc());
        img.setProvideRequireSoyNamespaces(n.isProvideRequireSoyNamespaces());
        img.setProvideRequireJsFunctions(n.isProvideRequireJsFunctions());
        img.setDeclareTopLevelNamespace(n.isDeclareTopLevelNamespace());
        img.setGenerateGoogMessagesDefs(n.isGenerateGoogMessagesDefs());
        img.setGoogMessagesExternal(n.isGoogMessagesExternal());
        img.setRightToLeftDir(n.isRightToLeftDir());
        img.setRightToLeftDirGoog(n.isRightToLeftDirGoog());
        img.setGlobalsPath(n.getGlobalsPath());
        img.setMessagesPath(n.getMessagesPath());
        img.setOutputPath(n.getOutputPath());
        return img;
    }

    @Override
    public Node visit(GssOutputFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(SoyLocale n, C ctx) throws Exception {
        SoyLocale img = NodeFacade.SoyLocale();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(ExcludeInputFilter n, C ctx) throws Exception {
        ExcludeInputFilter img = NodeFacade.ExcludeInputFilter();
        if (n.getPattern() != null) {
            List<InputFilterPattern> pattern = new ArrayList<InputFilterPattern>();
            for (InputFilterPattern item : n.getPattern()) {
                if (item != null) {
                    pattern.add((InputFilterPattern) item.accept(this, ctx));
                }
            }
            img.setPattern(pattern);
        }
        return img;
    }

    @Override
    public Node visit(JsPropertyMap n, C ctx) throws Exception {
        JsPropertyMap img = NodeFacade.JsPropertyMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Node visit(GssInputOrientation n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(GssOutputRenamingMapFormat n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Checks n, C ctx) throws Exception {
        Checks img = NodeFacade.Checks();
        if (n.getCheck() != null) {
            List<Check> check = new ArrayList<Check>();
            for (Check item : n.getCheck()) {
                if (item != null) {
                    check.add((Check) item.accept(this, ctx));
                }
            }
            img.setCheck(check);
        }
        return img;
    }

    @Override
    public Node visit(SoyCssSchemeType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(IncludeInputFilter n, C ctx) throws Exception {
        IncludeInputFilter img = NodeFacade.IncludeInputFilter();
        if (n.getPattern() != null) {
            List<InputFilterPattern> pattern = new ArrayList<InputFilterPattern>();
            for (InputFilterPattern item : n.getPattern()) {
                if (item != null) {
                    pattern.add((InputFilterPattern) item.accept(this, ctx));
                }
            }
            img.setPattern(pattern);
        }
        return img;
    }

    @Override
    public Node visit(GssDefines n, C ctx) throws Exception {
        GssDefines img = NodeFacade.GssDefines();
        if (n.getGssDefine() != null) {
            List<GssDefine> gssDefine = new ArrayList<GssDefine>();
            for (GssDefine item : n.getGssDefine()) {
                if (item != null) {
                    gssDefine.add((GssDefine) item.accept(this, ctx));
                }
            }
            img.setGssDefine(gssDefine);
        }
        return img;
    }

    @Override
    public Node visit(Optimization n, C ctx) throws Exception {
        Optimization img = NodeFacade.Optimization();
        img.setOptimize(n.isOptimize());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Node visit(Buildpath n, C ctx) throws Exception {
        Buildpath img = NodeFacade.Buildpath();
        if (n.getSource() != null) {
            List<Source> source = new ArrayList<Source>();
            for (Source item : n.getSource()) {
                if (item != null) {
                    source.add((Source) item.accept(this, ctx));
                }
            }
            img.setSource(source);
        }
        return img;
    }

    @Override
    public Node visit(JsRenamingVariablePolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(InputFilterPattern n, C ctx) throws Exception {
        InputFilterPattern img = NodeFacade.InputFilterPattern();
        img.setValue(n.getValue());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Node visit(WarningType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Warnings n, C ctx) throws Exception {
        Warnings img = NodeFacade.Warnings();
        if (n.getWarning() != null) {
            List<Warning> warning = new ArrayList<Warning>();
            for (Warning item : n.getWarning()) {
                if (item != null) {
                    warning.add((Warning) item.accept(this, ctx));
                }
            }
            img.setWarning(warning);
        }
        return img;
    }

    @Override
    public Node visit(JsRenaming n, C ctx) throws Exception {
        JsRenaming img = NodeFacade.JsRenaming();
        if (n.getVariableMap() != null) {
            img.setVariableMap((JsVariableMap) n.getVariableMap().accept(this, ctx));
        }
        if (n.getFunctionMap() != null) {
            img.setFunctionMap((JsFunctionMap) n.getFunctionMap().accept(this, ctx));
        }
        if (n.getPropertyMap() != null) {
            img.setPropertyMap((JsPropertyMap) n.getPropertyMap().accept(this, ctx));
        }
        img.setVariablePolice(n.getVariablePolice());
        img.setFunctionPolice(n.getFunctionPolice());
        img.setPropertyPolice(n.getPropertyPolice());
        img.setPrefix(n.getPrefix());
        img.setPrefixNamespace(n.getPrefixNamespace());
        img.setDevirtualizePrototypeMethods(n.isDevirtualizePrototypeMethods());
        img.setGeneratePseudoNames(n.isGeneratePseudoNames());
        img.setShadowVariables(n.isShadowVariables());
        img.setPropertyAffinity(n.isPropertyAffinity());
        img.setDisambiguateProperties(n.isDisambiguateProperties());
        img.setAmbiguateProperties(n.isAmbiguateProperties());
        img.setExportTestFunctions(n.isExportTestFunctions());
        img.setRenameLabels(n.isRenameLabels());
        return img;
    }

    @Override
    public Node visit(OptimizationType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(SoyLocales n, C ctx) throws Exception {
        SoyLocales img = NodeFacade.SoyLocales();
        if (n.getSoyLocale() != null) {
            List<SoyLocale> soyLocale = new ArrayList<SoyLocale>();
            for (SoyLocale item : n.getSoyLocale()) {
                if (item != null) {
                    soyLocale.add((SoyLocale) item.accept(this, ctx));
                }
            }
            img.setSoyLocale(soyLocale);
        }
        return img;
    }

    @Override
    public Node visit(GssAtRules n, C ctx) throws Exception {
        GssAtRules img = NodeFacade.GssAtRules();
        if (n.getGssAtRule() != null) {
            List<GssAtRule> gssAtRule = new ArrayList<GssAtRule>();
            for (GssAtRule item : n.getGssAtRule()) {
                if (item != null) {
                    gssAtRule.add((GssAtRule) item.accept(this, ctx));
                }
            }
            img.setGssAtRule(gssAtRule);
        }
        return img;
    }

    @Override
    public Node visit(JsRenamingPropertyPolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(JsDefines n, C ctx) throws Exception {
        JsDefines img = NodeFacade.JsDefines();
        if (n.getJsDefine() != null) {
            List<JsDefine> jsDefine = new ArrayList<JsDefine>();
            for (JsDefine item : n.getJsDefine()) {
                if (item != null) {
                    jsDefine.add((JsDefine) item.accept(this, ctx));
                }
            }
            img.setJsDefine(jsDefine);
        }
        return img;
    }

    @Override
    public Node visit(SeverityType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(GssUnrecognizeProperty n, C ctx) throws Exception {
        GssUnrecognizeProperty img = NodeFacade.GssUnrecognizeProperty();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(Language n, C ctx) throws Exception {
        Language img = NodeFacade.Language();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Node visit(GssOptimizationLevel n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(CheckType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Source n, C ctx) throws Exception {
        Source img = NodeFacade.Source();
        if (n.getExcluded() != null) {
            img.setExcluded((ExcludeInputFilter) n.getExcluded().accept(this, ctx));
        }
        if (n.getIncluded() != null) {
            img.setIncluded((IncludeInputFilter) n.getIncluded().accept(this, ctx));
        }
        img.setExtern(n.isExtern());
        img.setPath(n.getPath());
        img.setEntryKind(n.getEntryKind());
        img.setEntityKind(n.getEntityKind());
        img.setIncludeClosure(n.isIncludeClosure());
        img.setIncludeSimple(n.isIncludeSimple());
        return img;
    }

    @Override
    public Node visit(Info n, C ctx) throws Exception {
        Info img = NodeFacade.Info();
        img.setId(n.getId());
        img.setVersion(n.getVersion());
        img.setName(n.getName());
        img.setVendor(n.getVendor());
        return img;
    }

    @Override
    public Node visit(GssNonStandardFunction n, C ctx) throws Exception {
        GssNonStandardFunction img = NodeFacade.GssNonStandardFunction();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(Closure n, C ctx) throws Exception {
        Closure img = NodeFacade.Closure();
        if (n.getClosureJs() != null) {
            img.setClosureJs((ClosureJs) n.getClosureJs().accept(this, ctx));
        }
        if (n.getClosureGss() != null) {
            img.setClosureGss((ClosureGss) n.getClosureGss().accept(this, ctx));
        }
        if (n.getClosureSoy() != null) {
            img.setClosureSoy((ClosureSoy) n.getClosureSoy().accept(this, ctx));
        }
        return img;
    }

    @Override
    public Node visit(GssExcludedClass n, C ctx) throws Exception {
        GssExcludedClass img = NodeFacade.GssExcludedClass();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Node visit(JsDefine n, C ctx) throws Exception {
        JsDefine img = NodeFacade.JsDefine();
        img.setName(n.getName());
        img.setValue(n.getValue());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Node visit(JsRenamingFunctionPolice n, C ctx) throws Exception {
        return null;
    }

    @Override
    public Node visit(Output n, C ctx) throws Exception {
        Output img = NodeFacade.Output();
        img.setPath(n.getPath());
        img.setFile(n.getFile());
        return img;
    }

    @Override
    public Node visit(JsVariableMap n, C ctx) throws Exception {
        JsVariableMap img = NodeFacade.JsVariableMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    public CloneVisitor() {
        super();
    }

}
