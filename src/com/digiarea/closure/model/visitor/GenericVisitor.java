package com.digiarea.closure.model.visitor;

import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.CheckType;
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
import com.digiarea.closure.model.JsDefines;
import com.digiarea.closure.model.JsDefineType;
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
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.OptimizationType;
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
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.WarningType;

public interface GenericVisitor<R, C> {

    public R visit(Buildpath n, C ctx) throws Exception;

    public R visit(Check n, C ctx) throws Exception;

    public R visit(Checks n, C ctx) throws Exception;

    public R visit(CheckType n, C ctx) throws Exception;

    public R visit(Closure n, C ctx) throws Exception;

    public R visit(ClosureGss n, C ctx) throws Exception;

    public R visit(ClosureJs n, C ctx) throws Exception;

    public R visit(ClosureSoy n, C ctx) throws Exception;

    public R visit(ExcludeInputFilter n, C ctx) throws Exception;

    public R visit(GssAtRule n, C ctx) throws Exception;

    public R visit(GssAtRules n, C ctx) throws Exception;

    public R visit(GssDefine n, C ctx) throws Exception;

    public R visit(GssDefines n, C ctx) throws Exception;

    public R visit(GssExcludedClass n, C ctx) throws Exception;

    public R visit(GssExcludedClasses n, C ctx) throws Exception;

    public R visit(GssInputOrientation n, C ctx) throws Exception;

    public R visit(GssNonStandardFunction n, C ctx) throws Exception;

    public R visit(GssNonStandardFunctions n, C ctx) throws Exception;

    public R visit(GssOptimizationLevel n, C ctx) throws Exception;

    public R visit(GssOutputFormat n, C ctx) throws Exception;

    public R visit(GssOutputOrientation n, C ctx) throws Exception;

    public R visit(GssOutputRenamingMapFormat n, C ctx) throws Exception;

    public R visit(GssRenamingType n, C ctx) throws Exception;

    public R visit(GssUnrecognizeProperties n, C ctx) throws Exception;

    public R visit(GssUnrecognizeProperty n, C ctx) throws Exception;

    public R visit(GssVendor n, C ctx) throws Exception;

    public R visit(IncludeInputFilter n, C ctx) throws Exception;

    public R visit(Info n, C ctx) throws Exception;

    public R visit(InputFilterPattern n, C ctx) throws Exception;

    public R visit(InputFilterType n, C ctx) throws Exception;

    public R visit(JsDefine n, C ctx) throws Exception;

    public R visit(JsDefines n, C ctx) throws Exception;

    public R visit(JsDefineType n, C ctx) throws Exception;

    public R visit(JsDoc n, C ctx) throws Exception;

    public R visit(JsDocs n, C ctx) throws Exception;

    public R visit(JsFunctionMap n, C ctx) throws Exception;

    public R visit(JsPropertyMap n, C ctx) throws Exception;

    public R visit(JsRenaming n, C ctx) throws Exception;

    public R visit(JsRenamingFunctionPolice n, C ctx) throws Exception;

    public R visit(JsRenamingPropertyPolice n, C ctx) throws Exception;

    public R visit(JsRenamingVariablePolice n, C ctx) throws Exception;

    public R visit(JsSourceMapFormat n, C ctx) throws Exception;

    public R visit(JsVariableMap n, C ctx) throws Exception;

    public R visit(LangType n, C ctx) throws Exception;

    public R visit(Language n, C ctx) throws Exception;

    public R visit(Optimization n, C ctx) throws Exception;

    public R visit(Optimizations n, C ctx) throws Exception;

    public R visit(OptimizationType n, C ctx) throws Exception;

    public R visit(Output n, C ctx) throws Exception;

    public R visit(SeverityType n, C ctx) throws Exception;

    public R visit(Source n, C ctx) throws Exception;

    public R visit(SourceEntity n, C ctx) throws Exception;

    public R visit(SourceEntry n, C ctx) throws Exception;

    public R visit(SoyCodeStyle n, C ctx) throws Exception;

    public R visit(SoyCssSchemeType n, C ctx) throws Exception;

    public R visit(SoyLocale n, C ctx) throws Exception;

    public R visit(SoyLocales n, C ctx) throws Exception;

    public R visit(Warning n, C ctx) throws Exception;

    public R visit(Warnings n, C ctx) throws Exception;

    public R visit(WarningType n, C ctx) throws Exception;

}
