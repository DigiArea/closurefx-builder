package com.digiarea.closure.model.visitor;

import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Source;
import java.util.List;
import java.util.ArrayList;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.Info;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.GssDefines;
import com.digiarea.closure.model.GssNonStandardFunctions;
import com.digiarea.closure.model.GssUnrecognizeProperties;
import com.digiarea.closure.model.GssAtRules;
import com.digiarea.closure.model.GssExcludedClasses;
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.JsDocs;
import com.digiarea.closure.model.Language;
import com.digiarea.closure.model.JsDefines;
import com.digiarea.closure.model.JsRenaming;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closure.model.ExcludeInputFilter;
import com.digiarea.closure.model.InputFilterPattern;
import com.digiarea.closure.model.GssAtRule;
import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssOptimizationLevel;
import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.IncludeInputFilter;
import com.digiarea.closure.model.InputFilterType;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDefineType;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsFunctionMap;
import com.digiarea.closure.model.JsPropertyMap;
import com.digiarea.closure.model.JsVariableMap;
import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.JsSourceMapFormat;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SoyCodeStyle;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.WarningType;

public class CloneVisitor implements GenericVisitor<Object, Object> {

    @Override
    public Object visit(Buildpath n, Object ctx) throws Exception {
        Buildpath img = new Buildpath();
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
    public Object visit(Check n, Object ctx) throws Exception {
        Check img = new Check();
        img.setCheck(n.isCheck());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(Checks n, Object ctx) throws Exception {
        Checks img = new Checks();
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
    public Object visit(CheckType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(Closure n, Object ctx) throws Exception {
        Closure img = new Closure();
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
    public Object visit(ClosureGss n, Object ctx) throws Exception {
        ClosureGss img = new ClosureGss();
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
    public Object visit(ClosureJs n, Object ctx) throws Exception {
        ClosureJs img = new ClosureJs();
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
    public Object visit(ClosureSoy n, Object ctx) throws Exception {
        ClosureSoy img = new ClosureSoy();
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
    public Object visit(ExcludeInputFilter n, Object ctx) throws Exception {
        ExcludeInputFilter img = new ExcludeInputFilter();
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
    public Object visit(GssAtRule n, Object ctx) throws Exception {
        GssAtRule img = new GssAtRule();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(GssAtRules n, Object ctx) throws Exception {
        GssAtRules img = new GssAtRules();
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
    public Object visit(GssDefine n, Object ctx) throws Exception {
        GssDefine img = new GssDefine();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(GssDefines n, Object ctx) throws Exception {
        GssDefines img = new GssDefines();
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
    public Object visit(GssExcludedClass n, Object ctx) throws Exception {
        GssExcludedClass img = new GssExcludedClass();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(GssExcludedClasses n, Object ctx) throws Exception {
        GssExcludedClasses img = new GssExcludedClasses();
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
    public Object visit(GssInputOrientation n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssNonStandardFunction n, Object ctx) throws Exception {
        GssNonStandardFunction img = new GssNonStandardFunction();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(GssNonStandardFunctions n, Object ctx) throws Exception {
        GssNonStandardFunctions img = new GssNonStandardFunctions();
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
    public Object visit(GssOptimizationLevel n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssOutputFormat n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssOutputOrientation n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssOutputRenamingMapFormat n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssRenamingType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(GssUnrecognizeProperties n, Object ctx) throws Exception {
        GssUnrecognizeProperties img = new GssUnrecognizeProperties();
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
    public Object visit(GssUnrecognizeProperty n, Object ctx) throws Exception {
        GssUnrecognizeProperty img = new GssUnrecognizeProperty();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(GssVendor n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(IncludeInputFilter n, Object ctx) throws Exception {
        IncludeInputFilter img = new IncludeInputFilter();
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
    public Object visit(Info n, Object ctx) throws Exception {
        Info img = new Info();
        img.setId(n.getId());
        img.setVersion(n.getVersion());
        img.setName(n.getName());
        img.setVendor(n.getVendor());
        return img;
    }

    @Override
    public Object visit(InputFilterPattern n, Object ctx) throws Exception {
        InputFilterPattern img = new InputFilterPattern();
        img.setValue(n.getValue());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(InputFilterType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsDefine n, Object ctx) throws Exception {
        JsDefine img = new JsDefine();
        img.setName(n.getName());
        img.setValue(n.getValue());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(JsDefines n, Object ctx) throws Exception {
        JsDefines img = new JsDefines();
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
    public Object visit(JsDefineType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsDoc n, Object ctx) throws Exception {
        JsDoc img = new JsDoc();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(JsDocs n, Object ctx) throws Exception {
        JsDocs img = new JsDocs();
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
    public Object visit(JsFunctionMap n, Object ctx) throws Exception {
        JsFunctionMap img = new JsFunctionMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Object visit(JsPropertyMap n, Object ctx) throws Exception {
        JsPropertyMap img = new JsPropertyMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Object visit(JsRenaming n, Object ctx) throws Exception {
        JsRenaming img = new JsRenaming();
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
    public Object visit(JsRenamingFunctionPolice n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsRenamingPropertyPolice n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsRenamingVariablePolice n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsSourceMapFormat n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(JsVariableMap n, Object ctx) throws Exception {
        JsVariableMap img = new JsVariableMap();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Object visit(LangType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(Language n, Object ctx) throws Exception {
        Language img = new Language();
        img.setInput(n.getInput());
        img.setOutput(n.getOutput());
        return img;
    }

    @Override
    public Object visit(Optimization n, Object ctx) throws Exception {
        Optimization img = new Optimization();
        img.setOptimize(n.isOptimize());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(Optimizations n, Object ctx) throws Exception {
        Optimizations img = new Optimizations();
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
    public Object visit(OptimizationType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(Output n, Object ctx) throws Exception {
        Output img = new Output();
        img.setPath(n.getPath());
        img.setFile(n.getFile());
        return img;
    }

    @Override
    public Object visit(SeverityType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(Source n, Object ctx) throws Exception {
        Source img = new Source();
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
    public Object visit(SourceEntity n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(SourceEntry n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(SoyCodeStyle n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(SoyCssSchemeType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(SoyLocale n, Object ctx) throws Exception {
        SoyLocale img = new SoyLocale();
        img.setValue(n.getValue());
        return img;
    }

    @Override
    public Object visit(SoyLocales n, Object ctx) throws Exception {
        SoyLocales img = new SoyLocales();
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
    public Object visit(Warning n, Object ctx) throws Exception {
        Warning img = new Warning();
        img.setSeverity(n.getSeverity());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(Warnings n, Object ctx) throws Exception {
        Warnings img = new Warnings();
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
    public Object visit(WarningType n, Object ctx) throws Exception {
        return null;
    }

    public CloneVisitor() {
        super();
    }

}
