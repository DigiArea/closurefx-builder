package com.digiarea.closure.model.bind;

import java.util.ArrayList;

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
import com.digiarea.closure.model.visitor.VoidVisitor;

public class Checker implements VoidVisitor<Object> {

	@Override
	public void visit(Buildpath n, Object ctx) throws Exception {
		if (n.getSource() != null) {
			for (Source item : n.getSource()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setSource(new ArrayList<Source>());
		}
	}

	@Override
	public void visit(Check n, Object ctx) throws Exception {
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(Checks n, Object ctx) throws Exception {
		if (n.getCheck() != null) {
			for (Check item : n.getCheck()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setCheck(new ArrayList<Check>());
		}
	}

	@Override
	public void visit(CheckType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(Closure n, Object ctx) throws Exception {
		if (n.getClosureJs() == null) {
			n.setClosureJs(new ClosureJs());
		}
		n.getClosureJs().accept(this, ctx);
		if (n.getClosureGss() == null) {
			n.setClosureGss(new ClosureGss());
		}
		n.getClosureGss().accept(this, ctx);
		if (n.getClosureSoy() == null) {
			n.setClosureSoy(new ClosureSoy());
		}
		n.getClosureSoy().accept(this, ctx);
	}

	@Override
	public void visit(ClosureGss n, Object ctx) throws Exception {
		if (n.getInfo() == null) {
			n.setInfo(ClosureModelManager.getDefaultGSSInfo());
		}
		n.getInfo().accept(this, ctx);

		if (n.getBuildpath() == null) {
			n.setBuildpath(new Buildpath());
		}
		n.getBuildpath().accept(this, ctx);
		if (n.getOutput() == null) {
			n.setOutput(new Output());
		}
		n.getOutput().accept(this, ctx);
		if (n.getGssDefines() == null) {
			n.setGssDefines(new GssDefines());
		}
		n.getGssDefines().accept(this, ctx);
		if (n.getGssNonStandardFunctions() == null) {
			n.setGssNonStandardFunctions(new GssNonStandardFunctions());
		}
		n.getGssNonStandardFunctions().accept(this, ctx);
		if (n.getGssUnrecognizeProperties() == null) {
			n.setGssUnrecognizeProperties(new GssUnrecognizeProperties());
		}
		n.getGssUnrecognizeProperties().accept(this, ctx);
		if (n.getGssAtRules() == null) {
			n.setGssAtRules(new GssAtRules());
		}
		n.getGssAtRules().accept(this, ctx);
		if (n.getGssExcludedClasses() == null) {
			n.setGssExcludedClasses(new GssExcludedClasses());
		}
		n.getGssExcludedClasses().accept(this, ctx);
		if (n.getRenamingType() == null) {
			n.setRenamingType(ClosureModelManager.getDefaultGssRenamingType());
		}
		n.getRenamingType().accept(this, ctx);
		if (n.getVendor() == null) {
			n.setVendor(ClosureModelManager.getDefaultGssVendor());
		}
		n.getVendor().accept(this, ctx);
		if (n.getOutputRenamingMapFormat() == null) {
			n.setOutputRenamingMapFormat(ClosureModelManager
					.getDefaultGssOutputRenamingMapFormat());
		}
		n.getOutputRenamingMapFormat().accept(this, ctx);
		if (n.getOptimizationLevel() == null) {
			n.setOptimizationLevel(ClosureModelManager
					.getDefaultGssOptimizationLevel());
		}
		n.getOptimizationLevel().accept(this, ctx);
		if (n.getOutputFormat() == null) {
			n.setOutputFormat(ClosureModelManager.getDefaultGssOutputFormat());
		}
		n.getOutputFormat().accept(this, ctx);
		if (n.getInputOrientation() == null) {
			n.setInputOrientation(ClosureModelManager
					.getDefaultGssInputOrientation());
		}
		n.getInputOrientation().accept(this, ctx);
		if (n.getOutputOrientation() == null) {
			n.setOutputOrientation(ClosureModelManager
					.getDefaultGssOutputOrientation());

		}
		n.getOutputOrientation().accept(this, ctx);
	}

	@Override
	public void visit(ClosureJs n, Object ctx) throws Exception {

		if (n.getCharset() == null || n.getCharset().isEmpty()) {
			n.setCharset(ClosureModelManager.getDefaultCharset().displayName());
		}

		if (n.getInfo() == null) {
			n.setInfo(ClosureModelManager.getDefaultJSInfo());
		}
		n.getInfo().accept(this, ctx);
		if (n.getBuildpath() == null) {
			n.setBuildpath(new Buildpath());
		}
		n.getBuildpath().accept(this, ctx);
		if (n.getOutput() == null) {
			n.setOutput(new Output());
		}
		n.getOutput().accept(this, ctx);

		n.setWarnings(ClosureModelManager.synchWarnings(n.getWarnings()));
		n.getWarnings().accept(this, ctx);

		n.setChecks(ClosureModelManager.synchChecks(n.getChecks()));
		n.getChecks().accept(this, ctx);

		n.setOptimizations(ClosureModelManager.synchOptimizations(n
				.getOptimizations()));
		n.getOptimizations().accept(this, ctx);

		if (n.getJsDocs() == null) {
			n.setJsDocs(new JsDocs());
		}
		n.getJsDocs().accept(this, ctx);
		if (n.getLanguage() == null) {
			n.setLanguage(new Language());
		}
		n.getLanguage().accept(this, ctx);
		if (n.getJsDefines() == null) {
			n.setJsDefines(new JsDefines());
		}
		n.getJsDefines().accept(this, ctx);
		if (n.getRenaming() == null) {
			n.setRenaming(new JsRenaming());
		}
		n.getRenaming().accept(this, ctx);
		if (n.getSourceMapFormat() == null) {
			n.setSourceMapFormat(ClosureModelManager
					.getDefaultJsSourceMapFormat());
		}
		n.getSourceMapFormat().accept(this, ctx);

		if (n.getInputDelimiter() == null) {
			n.setInputDelimiter(ClosureModelManager
					.getDefaultJSInputDelimiter());
		}

		if (n.getOutputWrapper() == null) {
			n.setOutputWrapper(ClosureModelManager.getDefaultJSOutputWrapper());
		}

	}

	@Override
	public void visit(ClosureSoy n, Object ctx) throws Exception {
		if (n.getInfo() == null) {
			n.setInfo(ClosureModelManager.getDefaultSOYInfo());
		}
		n.getInfo().accept(this, ctx);
		if (n.getBuildpath() == null) {
			n.setBuildpath(new Buildpath());
		}
		n.getBuildpath().accept(this, ctx);
		if (n.getSoyLocales() == null) {
			n.setSoyLocales(new SoyLocales());
		}
		n.getSoyLocales().accept(this, ctx);
		if (n.getCssScheme() == null) {
			n.setCssScheme(ClosureModelManager.getDefaultSoyCssSchemeType());
		}
		n.getCssScheme().accept(this, ctx);
		if (n.getCodeStyle() == null) {
			n.setCodeStyle(ClosureModelManager.getDefaultSoyCodeStyle());
		}
		n.getCodeStyle().accept(this, ctx);
	}

	@Override
	public void visit(ExcludeInputFilter n, Object ctx) throws Exception {
		if (n.getPattern() != null) {
			for (InputFilterPattern item : n.getPattern()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setPattern(new ArrayList<InputFilterPattern>());
		}
	}

	@Override
	public void visit(GssAtRule n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssAtRules n, Object ctx) throws Exception {
		if (n.getGssAtRule() != null) {
			for (GssAtRule item : n.getGssAtRule()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setGssAtRule(new ArrayList<GssAtRule>());
		}
	}

	@Override
	public void visit(GssDefine n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssDefines n, Object ctx) throws Exception {
		if (n.getGssDefine() != null) {
			for (GssDefine item : n.getGssDefine()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setGssDefine(new ArrayList<GssDefine>());
		}
	}

	@Override
	public void visit(GssExcludedClass n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssExcludedClasses n, Object ctx) throws Exception {
		if (n.getGssExcludedClass() != null) {
			for (GssExcludedClass item : n.getGssExcludedClass()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setGssExcludedClass(new ArrayList<GssExcludedClass>());
		}
	}

	@Override
	public void visit(GssInputOrientation n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssNonStandardFunction n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssNonStandardFunctions n, Object ctx) throws Exception {
		if (n.getGssNonStandardFunction() != null) {
			for (GssNonStandardFunction item : n.getGssNonStandardFunction()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setGssNonStandardFunction(new ArrayList<GssNonStandardFunction>());
		}
	}

	@Override
	public void visit(GssOptimizationLevel n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssOutputFormat n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssOutputOrientation n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssOutputRenamingMapFormat n, Object ctx)
			throws Exception {
	}

	@Override
	public void visit(GssRenamingType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssUnrecognizeProperties n, Object ctx) throws Exception {
		if (n.getGssUnrecognizeProperty() != null) {
			for (GssUnrecognizeProperty item : n.getGssUnrecognizeProperty()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setGssUnrecognizeProperty(new ArrayList<GssUnrecognizeProperty>());
		}
	}

	@Override
	public void visit(GssUnrecognizeProperty n, Object ctx) throws Exception {
	}

	@Override
	public void visit(GssVendor n, Object ctx) throws Exception {
	}

	@Override
	public void visit(IncludeInputFilter n, Object ctx) throws Exception {
		if (n.getPattern() != null) {
			for (InputFilterPattern item : n.getPattern()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setPattern(new ArrayList<InputFilterPattern>());
		}
	}

	@Override
	public void visit(Info n, Object ctx) throws Exception {
	}

	@Override
	public void visit(InputFilterPattern n, Object ctx) throws Exception {
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(InputFilterType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsDefine n, Object ctx) throws Exception {
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(JsDefines n, Object ctx) throws Exception {
		if (n.getJsDefine() != null) {
			for (JsDefine item : n.getJsDefine()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setJsDefine(new ArrayList<JsDefine>());
		}
	}

	@Override
	public void visit(JsDefineType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsDoc n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsDocs n, Object ctx) throws Exception {
		if (n.getJsDoc() != null) {
			for (JsDoc item : n.getJsDoc()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setJsDoc(new ArrayList<JsDoc>());
		}
	}

	@Override
	public void visit(JsFunctionMap n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsVariableMap n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsPropertyMap n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsRenaming n, Object ctx) throws Exception {
		if (n.getVariableMap() == null) {
			n.setVariableMap(new JsVariableMap());
		}
		n.getVariableMap().accept(this, ctx);
		if (n.getFunctionMap() == null) {
			n.setFunctionMap(new JsFunctionMap());
		}
		n.getFunctionMap().accept(this, ctx);
		if (n.getPropertyMap() == null) {
			n.setPropertyMap(new JsPropertyMap());
		}
		n.getPropertyMap().accept(this, ctx);
		if (n.getVariablePolice() == null) {
			n.setVariablePolice(ClosureModelManager
					.getDefaultJsRenamingVariablePolice());
		}
		n.getVariablePolice().accept(this, ctx);
		if (n.getFunctionPolice() == null) {
			n.setFunctionPolice(ClosureModelManager
					.getDefaultJsRenamingFunctionPolice());
		}
		n.getFunctionPolice().accept(this, ctx);
		if (n.getPropertyPolice() == null) {
			n.setPropertyPolice(ClosureModelManager
					.getDefaultJsRenamingPropertyPolice());
		}
		n.getPropertyPolice().accept(this, ctx);
	}

	@Override
	public void visit(JsRenamingFunctionPolice n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsRenamingPropertyPolice n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsRenamingVariablePolice n, Object ctx) throws Exception {
	}

	@Override
	public void visit(JsSourceMapFormat n, Object ctx) throws Exception {
	}

	@Override
	public void visit(LangType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(Language n, Object ctx) throws Exception {
		if (n.getInput() == null) {
			n.setInput(ClosureModelManager.getDefaultInputLangType());
		}
		n.getInput().accept(this, ctx);
		if (n.getOutput() == null) {
			n.setOutput(ClosureModelManager.getDefaultOutputLangType());

		}
		n.getOutput().accept(this, ctx);
	}

	@Override
	public void visit(Optimization n, Object ctx) throws Exception {
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(Optimizations n, Object ctx) throws Exception {
		if (n.getOptimization() != null) {
			for (Optimization item : n.getOptimization()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setOptimization(new ArrayList<Optimization>());
		}
	}

	@Override
	public void visit(OptimizationType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(Output n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SeverityType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(Source n, Object ctx) throws Exception {
		if (n.getExcluded() != null) {
			n.getExcluded().accept(this, ctx);
		}
		if (n.getIncluded() != null) {
			n.getIncluded().accept(this, ctx);
		}
		if (n.getEntryKind() != null) {
			n.getEntryKind().accept(this, ctx);
		}
		if (n.getEntityKind() != null) {
			n.getEntityKind().accept(this, ctx);
		}
	}

	@Override
	public void visit(SourceEntity n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SourceEntry n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SoyCodeStyle n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SoyCssSchemeType n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SoyLocale n, Object ctx) throws Exception {
	}

	@Override
	public void visit(SoyLocales n, Object ctx) throws Exception {
		if (n.getSoyLocale() != null) {
			for (SoyLocale item : n.getSoyLocale()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setSoyLocale(new ArrayList<SoyLocale>());
		}
	}

	@Override
	public void visit(Warning n, Object ctx) throws Exception {
		if (n.getSeverity() != null) {
			n.getSeverity().accept(this, ctx);
		}
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(Warnings n, Object ctx) throws Exception {
		if (n.getWarning() != null) {
			for (Warning item : n.getWarning()) {
				if (item != null) {
					item.accept(this, ctx);
				}
			}
		} else {
			n.setWarning(new ArrayList<Warning>());
		}
	}

	@Override
	public void visit(WarningType n, Object ctx) throws Exception {
	}

	public Checker() {
		super();
	}

}
