package com.digiarea.closure.model.visitor;

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

public class CloneVisitor<C> implements GenericVisitor<Node, C> {

	@Override
	public Node visit(Buildpath n, C ctx) throws Exception {
		Buildpath img = new Buildpath();
		return img;
	}

	@Override
	public Node visit(Check n, C ctx) throws Exception {
		Check img = new Check();
		return img;
	}

	@Override
	public Node visit(Checks n, C ctx) throws Exception {
		Checks img = new Checks();
		return img;
	}

	@Override
	public Node visit(CheckType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Closure n, C ctx) throws Exception {
		Closure img = new Closure();
		return img;
	}

	@Override
	public Node visit(ClosureGss n, C ctx) throws Exception {
		ClosureGss img = new ClosureGss();
		return img;
	}

	@Override
	public Node visit(ClosureJs n, C ctx) throws Exception {
		ClosureJs img = new ClosureJs();
		return img;
	}

	@Override
	public Node visit(ClosureSoy n, C ctx) throws Exception {
		ClosureSoy img = new ClosureSoy();
		return img;
	}

	@Override
	public Node visit(ExcludeInputFilter n, C ctx) throws Exception {
		ExcludeInputFilter img = new ExcludeInputFilter();
		return img;
	}

	@Override
	public Node visit(GssAtRule n, C ctx) throws Exception {
		GssAtRule img = new GssAtRule();
		return img;
	}

	@Override
	public Node visit(GssAtRules n, C ctx) throws Exception {
		GssAtRules img = new GssAtRules();
		return img;
	}

	@Override
	public Node visit(GssDefine n, C ctx) throws Exception {
		GssDefine img = new GssDefine();
		return img;
	}

	@Override
	public Node visit(GssDefines n, C ctx) throws Exception {
		GssDefines img = new GssDefines();
		return img;
	}

	@Override
	public Node visit(GssExcludedClass n, C ctx) throws Exception {
		GssExcludedClass img = new GssExcludedClass();
		return img;
	}

	@Override
	public Node visit(GssExcludedClasses n, C ctx) throws Exception {
		GssExcludedClasses img = new GssExcludedClasses();
		return img;
	}

	@Override
	public Node visit(GssInputOrientation n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssNonStandardFunction n, C ctx) throws Exception {
		GssNonStandardFunction img = new GssNonStandardFunction();
		return img;
	}

	@Override
	public Node visit(GssNonStandardFunctions n, C ctx) throws Exception {
		GssNonStandardFunctions img = new GssNonStandardFunctions();
		return img;
	}

	@Override
	public Node visit(GssOptimizationLevel n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssOutputFormat n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssOutputOrientation n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssOutputRenamingMapFormat n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssRenamingType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(GssUnrecognizeProperties n, C ctx) throws Exception {
		GssUnrecognizeProperties img = new GssUnrecognizeProperties();
		return img;
	}

	@Override
	public Node visit(GssUnrecognizeProperty n, C ctx) throws Exception {
		GssUnrecognizeProperty img = new GssUnrecognizeProperty();
		return img;
	}

	@Override
	public Node visit(GssVendor n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(IncludeInputFilter n, C ctx) throws Exception {
		IncludeInputFilter img = new IncludeInputFilter();
		return img;
	}

	@Override
	public Node visit(Info n, C ctx) throws Exception {
		Info img = new Info();
		return img;
	}

	@Override
	public Node visit(InputFilterPattern n, C ctx) throws Exception {
		InputFilterPattern img = new InputFilterPattern();
		return img;
	}

	@Override
	public Node visit(InputFilterType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsDefine n, C ctx) throws Exception {
		JsDefine img = new JsDefine();
		return img;
	}

	@Override
	public Node visit(JsDefines n, C ctx) throws Exception {
		JsDefines img = new JsDefines();
		return img;
	}

	@Override
	public Node visit(JsDefineType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsDoc n, C ctx) throws Exception {
		JsDoc img = new JsDoc();
		return img;
	}

	@Override
	public Node visit(JsDocs n, C ctx) throws Exception {
		JsDocs img = new JsDocs();
		return img;
	}

	@Override
	public Node visit(JsFunctionMap n, C ctx) throws Exception {
		JsFunctionMap img = new JsFunctionMap();
		return img;
	}

	@Override
	public Node visit(JsPropertyMap n, C ctx) throws Exception {
		JsPropertyMap img = new JsPropertyMap();
		return img;
	}

	@Override
	public Node visit(JsRenaming n, C ctx) throws Exception {
		JsRenaming img = new JsRenaming();
		return img;
	}

	@Override
	public Node visit(JsRenamingFunctionPolice n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsRenamingPropertyPolice n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsRenamingVariablePolice n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsSourceMapFormat n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(JsVariableMap n, C ctx) throws Exception {
		JsVariableMap img = new JsVariableMap();
		return img;
	}

	@Override
	public Node visit(LangType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Language n, C ctx) throws Exception {
		Language img = new Language();
		return img;
	}

	@Override
	public Node visit(Optimization n, C ctx) throws Exception {
		Optimization img = new Optimization();
		return img;
	}

	@Override
	public Node visit(Optimizations n, C ctx) throws Exception {
		Optimizations img = new Optimizations();
		return img;
	}

	@Override
	public Node visit(OptimizationType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Output n, C ctx) throws Exception {
		Output img = new Output();
		return img;
	}

	@Override
	public Node visit(SeverityType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(Source n, C ctx) throws Exception {
		Source img = new Source();
		return img;
	}

	@Override
	public Node visit(SourceEntity n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(SourceEntry n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(SoyCodeStyle n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(SoyCssSchemeType n, C ctx) throws Exception {
		return null;
	}

	@Override
	public Node visit(SoyLocale n, C ctx) throws Exception {
		SoyLocale img = new SoyLocale();
		return img;
	}

	@Override
	public Node visit(SoyLocales n, C ctx) throws Exception {
		SoyLocales img = new SoyLocales();
		return img;
	}

	@Override
	public Node visit(Warning n, C ctx) throws Exception {
		Warning img = new Warning();
		return img;
	}

	@Override
	public Node visit(Warnings n, C ctx) throws Exception {
		Warnings img = new Warnings();
		return img;
	}

	@Override
	public Node visit(WarningType n, C ctx) throws Exception {
		return null;
	}

	public CloneVisitor() {
		super();
	}

}
