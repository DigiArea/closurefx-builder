package com.digiarea.closurefx.build;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.digiarea.closure.core.IPathResolver;
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
import com.digiarea.closure.model.visitor.VoidVisitorAdapter;
import com.google.common.css.JobDescription.InputOrientation;
import com.google.common.css.JobDescription.OptimizeStrategy;
import com.google.common.css.JobDescription.OutputFormat;
import com.google.common.css.JobDescription.OutputOrientation;
import com.google.common.css.JobDescriptionBuilder;
import com.google.common.css.OutputRenamingMapFormat;
import com.google.common.css.Vendor;
import com.google.common.css.compiler.commandline.RenamingType;
import com.google.javascript.jscomp.AnonymousFunctionNamingPolicy;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.ClosureCodingConvention;
import com.google.javascript.jscomp.CodingConvention;
import com.google.javascript.jscomp.CodingConventions;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.DependencyOptions;
import com.google.javascript.jscomp.DiagnosticGroup;
import com.google.javascript.jscomp.DiagnosticGroups;
import com.google.javascript.jscomp.JqueryCodingConvention;
import com.google.javascript.jscomp.PropertyRenamingPolicy;
import com.google.javascript.jscomp.SourceMap;
import com.google.javascript.jscomp.VariableMap;
import com.google.javascript.jscomp.VariableRenamingPolicy;
import com.google.javascript.jscomp.XtbMessageBundle;
import com.google.template.soy.jssrc.SoyJsSrcOptions;
import com.google.template.soy.jssrc.SoyJsSrcOptions.CodeStyle;

/**
 * 
 * Maps @see Closure model.
 * 
 * @author daginno
 * 
 */
public class Closurer extends VoidVisitorAdapter<Object> {

	@Override
	public void visit(Buildpath n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Check n, Object ctx) throws Exception {
		super.visit(n, ctx);

		if (n.getType() != null) {
			switch (n.getType()) {
			case CHAIN_CALLS:
				jsOptions.setChainCalls(n.isCheck());
				break;
			case CHECK_CAJA:
				jsOptions.setCheckCaja(n.isCheck());
				break;
			case CHECK_CONTROL_SCTRUCTURES:
				jsOptions.setCheckControlStructures(n.isCheck());
				break;
			case CHECK_SUSPICIOUS_CODE:
				jsOptions.setCheckSuspiciousCode(n.isCheck());
				break;
			case CHECK_SYMBOLS:
				jsOptions.setCheckSymbols(n.isCheck());
				break;
			case CHECK_TYPES:
				jsOptions.setCheckTypes(n.isCheck());
				break;
			case COMPUTE_FUNCTION_SIDE_EFFECTS:
				jsOptions.setComputeFunctionSideEffects(n.isCheck());
				break;
			case TIGHTEN_TYPES:
				jsOptions.setTightenTypes(n.isCheck());
				break;
			}
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(Checks n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(CheckType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Closure n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(ClosureGss n, Object ctx) throws Exception {
		super.visit(n, ctx);

		gssOptions.setAllowWebkitKeyframes(true);
		gssOptions.setEliminateDeadStyles(true);
		gssOptions.setProcessDependencies(true);
		gssOptions.setSimplifyCss(true);

		gssOptions.setAllowUnrecognizedFunctions(n
				.isAllowUnrecognizedFunctions());

		gssOptions.setAllowUnrecognizedProperties(n
				.isAllowUnrecognizedProperties());

		gssOptions.setCopyrightNotice(n.getCopyrightNotice());

		String prefix = n.getCssRenamingPrefix();
		if (prefix != null) {
			gssOptions.setCssRenamingPrefix(n.getCssRenamingPrefix());
		}

		gssOptions.setOptimizeStrategy(OptimizeStrategy.SAFE);
	}

	@Override
	public void visit(ClosureJs n, Object ctx) throws Exception {
		// we manage optimization first because other options can modify
		// skipAllPasses to be evaluated
		// we skip all passes when it is true or checks is empty list
		if (!n.isDevmode() && n.isSkipAllPasses()) {
			jsOptions.setSkipAllPasses(n.isSkipAllPasses());
		} else if (!n.isDevmode() && n.isFunctionsOnly()) {
			jsOptions.setNameAnonymousFunctionsOnly(n.isFunctionsOnly());
		} else {
			if ((n.getChecks() == null || n.getChecks().getCheck().isEmpty()
					&& (n.getOptimizations() == null || n.getOptimizations()
							.getOptimization().isEmpty()))) {
				jsOptions.setSkipAllPasses(true);
			} else {
				if (n.getChecks() != null) {
					n.getChecks().accept(this, ctx);
				}
				if (n.getOptimizations() != null) {
					n.getOptimizations().accept(this, ctx);
				}
			}
		}

		// we need to turn on checks
		if (n.isClosureStyle() || n.isClosurePass() || n.isJqueryPass()) {
			jsOptions.setSkipAllPasses(false);
			jsOptions.setNameAnonymousFunctionsOnly(false);
		}

		jsOptions.setAcceptConstKeyword(n.isAcceptConstKeyword());
		jsOptions.setCodingConvention(mapCodeStyle(n.isClosureStyle(),
				n.isJqueryPass()));
		jsOptions.setInputDelimiter("//" + n.getInputDelimiter());
		jsOptions.setLineBreak(n.isLineBreaksAggressive());

		DependencyOptions dependencyOptions = new DependencyOptions();
		dependencyOptions.setDependencySorting(true);
		dependencyOptions.setDependencyPruning(false);
		jsOptions.setDependencyOptions(dependencyOptions);

		jsOptions.setPreferLineBreakAtEndOfFile(n.isLineBreaks());
		jsOptions.setPreferSingleQuotes(n.isSingleQuotes());
		jsOptions.setPrettyPrint(n.isPrettyPrint());
		jsOptions.setPrintInputDelimiter(n.isPrintInputDelimeter());

		jsOptions.jqueryPass = n.isJqueryPass();
		jsOptions.closurePass = n.isClosurePass();

		jsOptions.setGenerateExports(n.isGenerateExports());
		if (n.isExternExports()) {
			jsOptions.setExternExports(n.isExternExports());
			jsOptions.setExternExportsPath(n.getExternExportsPath());
		}

		if (n.getSourceMapFile() != null && !n.getSourceMapFile().isEmpty()) {
			jsOptions.setSourceMapOutputPath(pathResolver.toRealPath(n
					.getSourceMapFile()));
		}

		if (n.getCharset() != null && !n.getCharset().isEmpty()) {
			jsOptions.setOutputCharset(n.getCharset());
		}

		if (n.getTranslationsFile() != null
				&& !n.getTranslationsFile().isEmpty()) {
			try {
				jsOptions.setMessageBundle(new XtbMessageBundle(
						new FileInputStream(pathResolver.toRealPath(n
								.getTranslationsFile())), n
								.getTranslationsProject()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (n.getInfo() != null) {
			n.getInfo().accept(this, ctx);
		}
		if (n.getBuildpath() != null) {
			n.getBuildpath().accept(this, ctx);
		}
		if (n.getOutput() != null) {
			n.getOutput().accept(this, ctx);
		}
		if (n.getWarnings() != null) {
			n.getWarnings().accept(this, ctx);
		}
		if (n.getJsDocs() != null) {
			n.getJsDocs().accept(this, ctx);
		}
		if (n.getLanguage() != null) {
			n.getLanguage().accept(this, ctx);
		}
		if (n.getJsDefines() != null) {
			n.getJsDefines().accept(this, ctx);
		}
		if (n.getRenaming() != null) {
			n.getRenaming().accept(this, ctx);
		}
		if (n.getSourceMapFormat() != null) {
			n.getSourceMapFormat().accept(this, ctx);
		}
	}

	private CodingConvention mapCodeStyle(boolean closurestyle, boolean jquery) {
		if (!closurestyle) {
			return CodingConventions.getDefault();
		} else if (jquery) {
			return new JqueryCodingConvention();
		} else {
			return new ClosureCodingConvention();
		}
	}

	@Override
	public void visit(ClosureSoy n, Object ctx) throws Exception {
		super.visit(n, ctx);

		soyOptions.setGoogMsgsAreExternal(n.isGoogMessagesExternal());
		soyOptions.setIsUsingIjData(n.isUsingIjData());
		soyOptions.setShouldAllowDeprecatedSyntax(n.isAllowDeprecatedSyntax());
		soyOptions.setShouldDeclareTopLevelNamespaces(n
				.isDeclareTopLevelNamespace());
		soyOptions.setShouldGenerateGoogMsgDefs(n.isGenerateGoogMessagesDefs());
		soyOptions.setShouldGenerateJsdoc(n.isGenerateJsDoc());
		soyOptions.setShouldProvideRequireJsFunctions(n
				.isProvideRequireJsFunctions());
		soyOptions.setShouldProvideRequireSoyNamespaces(n
				.isProvideRequireSoyNamespaces());
		soyOptions.setUseGoogIsRtlForBidiGlobalDir(n.isRightToLeftDirGoog());
		if (n.isRightToLeftDirGoog()) {
			soyOptions.setBidiGlobalDir(0);
		} else {
			soyOptions.setBidiGlobalDir(mapBigiGlobalDir(n.isRightToLeftDir()));
		}
	}

	private int mapBigiGlobalDir(boolean dir) {
		if (dir) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public void visit(ExcludeInputFilter n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssAtRule n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssAtRules n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssDefine n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssDefines n, Object ctx) throws Exception {
		super.visit(n, ctx);
		if (n.getGssDefine() != null) {
			List<String> list = new ArrayList<String>();
			for (GssDefine item : n.getGssDefine()) {
				if (item != null) {
					list.add(item.getValue());
				}
			}
			gssOptions.setTrueConditionNames(list);
		}
	}

	@Override
	public void visit(GssExcludedClass n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssExcludedClasses n, Object ctx) throws Exception {
		super.visit(n, ctx);

		if (n.getGssExcludedClass() != null) {
			List<String> clazzes = new ArrayList<String>();
			for (GssExcludedClass item : n.getGssExcludedClass()) {
				if (item != null) {
					clazzes.add(item.getValue());
				}
			}
			gssOptions.setExcludedClassesFromRenaming(clazzes);
		}
	}

	@Override
	public void visit(GssInputOrientation n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case DEFAULT:
			gssOptions.setInputOrientation(InputOrientation.LTR);
			break;
		case LTR:
			gssOptions.setInputOrientation(InputOrientation.LTR);
			break;
		case RTL:
			gssOptions.setInputOrientation(InputOrientation.RTL);
			break;
		}
	}

	@Override
	public void visit(GssNonStandardFunction n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssNonStandardFunctions n, Object ctx) throws Exception {
		super.visit(n, ctx);

		List<String> funcs = new ArrayList<String>();
		if (n.getGssNonStandardFunction() != null) {
			for (GssNonStandardFunction item : n.getGssNonStandardFunction()) {
				if (item != null) {
					funcs.add(item.getValue());
				}
			}
		}

		gssOptions.setAllowedNonStandardFunctions(funcs);
	}

	@Override
	public void visit(GssOptimizationLevel n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssOutputFormat n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case COMPRESSED:
			gssOptions.setOutputFormat(OutputFormat.COMPRESSED);
			break;
		case DEBUG:
			gssOptions.setOutputFormat(OutputFormat.DEBUG);
			break;
		case PRETTY_PRINTED:
			gssOptions.setOutputFormat(OutputFormat.PRETTY_PRINTED);
			break;
		}
	}

	@Override
	public void visit(GssOutputOrientation n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case DEFAULT:
			gssOptions.setOutputOrientation(OutputOrientation.LTR);
			break;
		case LTR:
			gssOptions.setOutputOrientation(OutputOrientation.LTR);
			break;
		case RTL:
			gssOptions.setOutputOrientation(OutputOrientation.RTL);
			break;
		case NOCHANGE:
			gssOptions.setOutputOrientation(OutputOrientation.NOCHANGE);
			break;
		}
	}

	@Override
	public void visit(GssOutputRenamingMapFormat n, Object ctx)
			throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case CLOSURE_COMPILED:
			gssOptions
					.setOutputRenamingMapFormat(OutputRenamingMapFormat.CLOSURE_COMPILED);
			break;
		case CLOSURE_COMPILED_SPLIT_HYPHENS:
			gssOptions
					.setOutputRenamingMapFormat(OutputRenamingMapFormat.CLOSURE_COMPILED_SPLIT_HYPHENS);
			break;
		case CLOSURE_UNCOMPILED:
			gssOptions
					.setOutputRenamingMapFormat(OutputRenamingMapFormat.CLOSURE_UNCOMPILED);
			break;
		case JSCOMP_VARIABLE_MAP:
			gssOptions
					.setOutputRenamingMapFormat(OutputRenamingMapFormat.JSCOMP_VARIABLE_MAP);
			break;
		case JSON:
			gssOptions.setOutputRenamingMapFormat(OutputRenamingMapFormat.JSON);
			break;
		case PROPERTIES:
			gssOptions
					.setOutputRenamingMapFormat(OutputRenamingMapFormat.PROPERTIES);
			break;
		}
	}

	@Override
	public void visit(GssRenamingType n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case CLOSURE:
			gssOptions.setCssSubstitutionMapProvider(RenamingType.CLOSURE
					.getCssSubstitutionMapProvider());
			break;
		case DEBUG:
			gssOptions.setCssSubstitutionMapProvider(RenamingType.DEBUG
					.getCssSubstitutionMapProvider());
			break;
		case NONE:
			gssOptions.setCssSubstitutionMapProvider(RenamingType.NONE
					.getCssSubstitutionMapProvider());
			break;
		}
	}

	@Override
	public void visit(GssUnrecognizeProperties n, Object ctx) throws Exception {
		super.visit(n, ctx);

		List<String> props = new ArrayList<String>();
		if (n.getGssUnrecognizeProperty() != null) {
			for (GssUnrecognizeProperty item : n.getGssUnrecognizeProperty()) {
				if (item != null) {
					props.add(item.getValue());
				}
			}
		}

		gssOptions.setAllowedUnrecognizedProperties(props);
	}

	@Override
	public void visit(GssUnrecognizeProperty n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(GssVendor n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case KONQUEROR:
			gssOptions.setVendor(Vendor.KONQUEROR);
			break;
		case MICROSOFT:
			gssOptions.setVendor(Vendor.MICROSOFT);
			break;
		case MOZILLA:
			gssOptions.setVendor(Vendor.MOZILLA);
			break;
		case OPERA:
			gssOptions.setVendor(Vendor.OPERA);
			break;
		case WEBKIT:
			gssOptions.setVendor(Vendor.WEBKIT);
			break;
		case NONE:
			gssOptions.setVendor(null);
			break;
		}
	}

	@Override
	public void visit(IncludeInputFilter n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Info n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(InputFilterPattern n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(InputFilterType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDefine n, Object ctx) throws Exception {
		super.visit(n, ctx);
		if (n.getType() == JsDefineType.INT) {
			jsOptions.setDefineToNumberLiteral(n.getName(),
					Integer.parseInt(n.getValue()));
		} else if (n.getType() == JsDefineType.DOUBLE) {
			jsOptions.setDefineToDoubleLiteral(n.getName(),
					Float.parseFloat(n.getValue()));
		} else if (n.getType() == JsDefineType.BOOLEAN) {
			jsOptions.setDefineToBooleanLiteral(n.getName(),
					Boolean.parseBoolean(n.getValue()));
		} else if (n.getType() == JsDefineType.STRING) {
			jsOptions.setDefineToStringLiteral(n.getName(), n.getValue());
		}
	}

	@Override
	public void visit(JsDefines n, Object ctx) throws Exception {
		// to enable defines, we need to activate passes!!!
		jsOptions.setSkipAllPasses(false);
		jsOptions.setNameAnonymousFunctionsOnly(false);
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDefineType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDoc n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(JsDocs n, Object ctx) throws Exception {
		super.visit(n, ctx);

		if (n.getJsDoc() != null) {
			List<String> strings = new ArrayList<String>();
			for (JsDoc item : n.getJsDoc()) {
				if (item != null) {
					strings.add(item.getValue());
				}
			}
			jsOptions.setExtraAnnotationNames(new HashSet<String>(strings));
		}
	}

	@Override
	public void visit(JsVariableMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		String input = pathResolver.toRealPath(n.getInput());
		if (input != null && !input.isEmpty()) {
			jsOptions.setInputVariableMap(VariableMap.load(input));
		}
	}

	@Override
	public void visit(JsFunctionMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		String input = pathResolver.toRealPath(n.getInput());
		if (input != null && !input.isEmpty()) {
			jsOptions.setInputAnonymousFunctionNamingMap(VariableMap
					.load(input));
		}
	}

	@Override
	public void visit(JsPropertyMap n, Object ctx) throws Exception {
		super.visit(n, ctx);
		String input = pathResolver.toRealPath(n.getInput());
		if (input != null && !input.isEmpty()) {
			jsOptions.setInputPropertyMap(VariableMap.load(input));
		}
	}

	@Override
	public void visit(JsRenaming n, Object ctx) throws Exception {
		super.visit(n, ctx);

		if (n.getPrefix() != null && !n.getPrefix().isEmpty()) {
			jsOptions.setRenamePrefix(n.getPrefix());
		}
		if (n.getPrefixNamespace() != null && !n.getPrefixNamespace().isEmpty()) {
			jsOptions.setRenamePrefixNamespace(n.getPrefixNamespace());
		}

		jsOptions.setDevirtualizePrototypeMethods(n
				.isDevirtualizePrototypeMethods());
		jsOptions.setGeneratePseudoNames(n.isGeneratePseudoNames());
		jsOptions.setShadowVariables(n.isShadowVariables());
		jsOptions.setPropertyAffinity(n.isPropertyAffinity());
		jsOptions.setDisambiguateProperties(n.isDisambiguateProperties());
		jsOptions.setAmbiguateProperties(n.isAmbiguateProperties());
		jsOptions.setExportTestFunctions(n.isExportTestFunctions());
		jsOptions.setLabelRenaming(n.isRenameLabels());

		// jsOptions.setRenamingPolicy(VariableRenamingPolicy.LOCAL,
		// PropertyRenamingPolicy.OFF);

		// ReplaceIdGenerators is on by default, but should run in simple mode.
		// jsOptions.setReplaceIdGenerators(false);

		// Does not call applyBasicCompilationOptions(options) because the call
		// to
		// skipAllCompilerPasses() cannot be easily undone.
		// jsOptions.closurePass = true;

		// jsOptions.setShadowVariables(true);
		// jsOptions.setInlineVariables(Reach.LOCAL_ONLY);
		// jsOptions.flowSensitiveInlineVariables = true;
		// jsOptions.setInlineFunctions(Reach.LOCAL_ONLY);
		// jsOptions.checkGlobalThisLevel = CheckLevel.OFF;
		// jsOptions.foldConstants = true;
		// jsOptions.coalesceVariableNames = true;
		// jsOptions.deadAssignmentElimination = true;
		// jsOptions.collapseVariableDeclarations = true;
		// jsOptions.convertToDottedProperties = true;
		// jsOptions.labelRenaming = true;
		// jsOptions.removeDeadCode = true;
		// jsOptions.optimizeArgumentsArray = true;
		// jsOptions.setRemoveUnusedVariables(Reach.LOCAL_ONLY);
		// jsOptions.setCollapseObjectLiterals(true);
		// jsOptions.setProtectHiddenSideEffects(true);

	}

	@Override
	public void visit(JsRenamingFunctionPolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case MAPPED:
			jsOptions
					.setAnonymousFunctionNaming(AnonymousFunctionNamingPolicy.MAPPED);
			break;
		case OFF:
			jsOptions
					.setAnonymousFunctionNaming(AnonymousFunctionNamingPolicy.OFF);
			break;
		case UNMAPPED:
			jsOptions
					.setAnonymousFunctionNaming(AnonymousFunctionNamingPolicy.UNMAPPED);
			break;
		}
	}

	@Override
	public void visit(JsRenamingPropertyPolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case AGGRESSIVE_HEURISTIC:
			jsOptions
					.setPropertyRenaming(PropertyRenamingPolicy.AGGRESSIVE_HEURISTIC);
			break;
		case ALL_UNQUOTED:
			jsOptions.setPropertyRenaming(PropertyRenamingPolicy.ALL_UNQUOTED);
			break;
		case HEURISTIC:
			jsOptions.setPropertyRenaming(PropertyRenamingPolicy.HEURISTIC);
			break;
		case OFF:
			jsOptions.setPropertyRenaming(PropertyRenamingPolicy.OFF);
			break;
		}
	}

	@Override
	public void visit(JsRenamingVariablePolice n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case ALL:
			jsOptions.setVariableRenaming(VariableRenamingPolicy.ALL);
			break;
		case LOCAL:
			jsOptions.setVariableRenaming(VariableRenamingPolicy.LOCAL);
			break;
		case OFF:
			jsOptions.setVariableRenaming(VariableRenamingPolicy.OFF);
			break;

		}
	}

	@Override
	public void visit(JsSourceMapFormat n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case DEFAULT:
			jsOptions.setSourceMapFormat(SourceMap.Format.DEFAULT);
			break;
		case V_1:
			jsOptions.setSourceMapFormat(SourceMap.Format.V1);
			break;
		case V_2:
			jsOptions.setSourceMapFormat(SourceMap.Format.V2);
			break;
		case V_3:
			jsOptions.setSourceMapFormat(SourceMap.Format.V3);
			break;
		}
	}

	@Override
	public void visit(LangType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Language n, Object ctx) throws Exception {
		super.visit(n, ctx);
		if (n.getInput() != null) {
			jsOptions.setLanguageIn(mapLanguage(n.getInput()));
		}
		if (n.getOutput() != null) {
			jsOptions.setLanguageOut(mapLanguage(n.getOutput()));
		}
	}

	public static LanguageMode mapLanguage(LangType language) {
		switch (language) {
		case ECMASCRIPT_3:
			return LanguageMode.ECMASCRIPT3;
		case ECMASCRIPT_5:
			return LanguageMode.ECMASCRIPT5;
		case ECMASCRIPT_5_STRICT:
			return LanguageMode.ECMASCRIPT5_STRICT;
		default:
			return LanguageMode.ECMASCRIPT3;
		}
	}

	@Override
	public void visit(Optimization n, Object ctx) throws Exception {
		super.visit(n, ctx);
		if (n.getType() != null) {
			switch (n.getType()) {
			case ALIAS_ALL_STRINGS:
				jsOptions.setAliasAllStrings(n.isOptimize());
				break;
			case ALIAS_EXTERNALS:
				jsOptions.setAliasExternals(n.isOptimize());
				break;
			case ALIAS_KEYWORDS:
				jsOptions.setAliasKeywords(n.isOptimize());
				break;
			case ASSUME_STRICT_THIS:
				jsOptions.setAssumeStrictThis(n.isOptimize());
				break;
			case COALESCE_VARIABLE_NAMES:
				jsOptions.setCoalesceVariableNames(n.isOptimize());
				break;
			case COLLAPSE_ANONYMOUS_FUNCTIONS:
				jsOptions.setCollapseAnonymousFunctions(n.isOptimize());
				break;
			case COLLAPSE_OBJECT_LITERALS:
				jsOptions.setCollapseObjectLiterals(n.isOptimize());
				break;
			case COLLAPSE_PROPERTIES:
				jsOptions.setCollapseProperties(n.isOptimize());
				break;
			case COLLAPSE_PROPERTIES_ON_EXTERN_TYPES:
				jsOptions.setCollapsePropertiesOnExternTypes(n.isOptimize());
				break;
			case COLLAPSE_VARIABLE_DECLARATIONS:
				jsOptions.setCollapseVariableDeclarations(n.isOptimize());
				break;
			case CONVERT_TO_DOTTED_PROPERTIES:
				jsOptions.setConvertToDottedProperties(n.isOptimize());
				break;
			case CROSS_MODULE_CODE_MOTION:
				jsOptions.setCrossModuleCodeMotion(n.isOptimize());
				break;
			case CROSS_MODULE_METHOD_MOTION:
				jsOptions.setCrossModuleMethodMotion(n.isOptimize());
				break;
			case DEAD_ASSIGNMENT_ELIMINATION:
				jsOptions.setDeadAssignmentElimination(n.isOptimize());
				break;
			case EXTRACT_PROTOTYPE_MEMBER_DECLARATION:
				jsOptions.setExtractPrototypeMemberDeclarations(n.isOptimize());
				break;
			case FOLD_CONSTANTS:
				jsOptions.setFoldConstants(n.isOptimize());
				break;
			case GROUP_VARIABLE_DECLARATIONS:
				jsOptions.setGroupVariableDeclarations(n.isOptimize());
				break;
			case INLINE_CONSTANT_VAR:
				jsOptions.setInlineConstantVars(n.isOptimize());
				break;
			case INLINE_FUNCTION:
				jsOptions.setInlineFunctions(n.isOptimize());
				break;
			case INLINE_GETTERS:
				jsOptions.setInlineGetters(n.isOptimize());
				break;
			case INLINE_LOCAL_FUNCTION:
				jsOptions.setInlineLocalFunctions(n.isOptimize());
				break;
			case INLINE_LOCAL_VARIABLES:
				jsOptions.setInlineLocalVariables(n.isOptimize());
				break;
			case INLINE_PROPERTIES:
				jsOptions.setInlineProperties(n.isOptimize());
				break;
			case INLINE_VARIABLES:
				jsOptions.setInlineVariables(n.isOptimize());
				break;
			case OPTIMIZE_PARAMETERS:
				jsOptions.setOptimizeParameters(n.isOptimize());
				break;
			case OPTIMIZE_RETURNS:
				jsOptions.setOptimizeReturns(n.isOptimize());
				break;
			case OUTPUT_JS_STRING_USAGE:
				jsOptions.setOutputJsStringUsage(n.isOptimize());
				break;
			case REMOVE_DEAD_CODE:
				jsOptions.setRemoveDeadCode(n.isOptimize());
				break;
			case REMOVE_UNUSED_CLASS_PROPERTIES:
				jsOptions.setRemoveUnusedClassProperties(n.isOptimize());
				break;
			case REMOVE_UNUSED_LOCAL_VARS:
				jsOptions.setRemoveUnusedLocalVars(n.isOptimize());
				break;
			case REMOVE_UNUSED_PROTOTYPE_PROPERTIES:
				jsOptions.setRemoveUnusedPrototypeProperties(n.isOptimize());
				break;
			case REMOVE_UNUSED_PROTOTYPE_PROPERTIES_IN_EXTERNS:
				jsOptions.setRemoveUnusedPrototypePropertiesInExterns(n
						.isOptimize());
				break;
			case REMOVE_UNUSED_VARS:
				jsOptions.setRemoveUnusedVars(n.isOptimize());
				break;
			case REWRITE_FUNCTIONS_EXPRESSIONS:
				jsOptions.setRewriteFunctionExpressions(n.isOptimize());
				break;
			case SMART_NAME_REMOVAL:
				jsOptions.setSmartNameRemoval(n.isOptimize());
				break;
			case ASSUME_CLOSURES_ONLY_CAPTURE_PREFERENCES:
				break;
			case OPTIMIZE_ARGUMENTS_ARRAY:
				jsOptions.setOptimizeArgumentsArray(n.isOptimize());
				break;
			case OPTIMIZE_CALLS:
				jsOptions.setOptimizeCalls(n.isOptimize());
				break;
			}
			n.getType().accept(this, ctx);
		}
	}

	@Override
	public void visit(Optimizations n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(OptimizationType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Output n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SeverityType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Source n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SourceEntity n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SourceEntry n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyCodeStyle n, Object ctx) throws Exception {
		super.visit(n, ctx);
		switch (n) {
		case CONCAT:
			soyOptions.setCodeStyle(CodeStyle.CONCAT);
			break;
		case STRINGBUILDER:
			soyOptions.setCodeStyle(CodeStyle.STRINGBUILDER);
			break;
		}
	}

	@Override
	public void visit(SoyCssSchemeType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyLocale n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(SoyLocales n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(Warning n, Object ctx) throws Exception {
		super.visit(n, ctx);
		if (n.getSeverity() != null && n.getType() != null) {
			switch (n.getType()) {
			case AGGRESSIVE_VAR_CHECK:
				jsOptions
						.setAggressiveVarCheck(mapSeverityType(n.getSeverity()));
				break;
			case BROKEN_REQUIRES_LEVEL:
				jsOptions.setBrokenClosureRequiresLevel(mapSeverityType(n
						.getSeverity()));
				break;
			case CHECK_GLOBAL_NAMES_LEVEL:
				jsOptions.setCheckGlobalNamesLevel(mapSeverityType(n
						.getSeverity()));
				break;
			case CHECK_GLOBAL_THIS_LEVEL:
				jsOptions.setCheckGlobalThisLevel(mapSeverityType(n
						.getSeverity()));
				break;
			// case CHECK_MISSING_GET_CSS_NAME_LEVEL:
			// jsOptions.setCheckMissingGetCssNameLevel(mapSeverityType(n
			// .getSeverity()));
			// break;
			case CHECK_MISSING_RETURN:
				jsOptions
						.setCheckMissingReturn(mapSeverityType(n.getSeverity()));
				break;
			case CHECK_REQUIRES:
				jsOptions.setCheckRequires(mapSeverityType(n.getSeverity()));
				break;
			case CHECK_UNREACHABLE_CODE:
				jsOptions.setCheckUnreachableCode(mapSeverityType(n
						.getSeverity()));
				break;
			case REPORT_MISSING_OVERRIDE:
				jsOptions.setReportMissingOverride(mapSeverityType(n
						.getSeverity()));
				break;
			case REPORT_UNKNOWN_TYPES:
				jsOptions
						.setReportUnknownTypes(mapSeverityType(n.getSeverity()));
				break;
			default:
				DiagnosticGroup group = mapWarningType(n.getType());
				CheckLevel level = mapSeverityType(n.getSeverity());
				if (group != null && level != null) {
					jsOptions.setWarningLevel(group, level);
				}
				break;
			}
		}

		if (n.getSeverity() != null) {
			n.getSeverity().accept(this, ctx);
		}
		if (n.getType() != null) {
			n.getType().accept(this, ctx);
		}
	}

	private DiagnosticGroup mapWarningType(WarningType warningType) {
		if (warningType != null) {
			switch (warningType) {
			case ACCESS_CONTROLS:
				return DiagnosticGroups.ACCESS_CONTROLS;
			case AMBIGUOUS_FUNCTION_DECL:
				return DiagnosticGroups.AMBIGUOUS_FUNCTION_DECL;
			case CHECK_PROVIDES:
				return DiagnosticGroups.CHECK_PROVIDES;
			case CHECK_REGEXP:
				return DiagnosticGroups.CHECK_REGEXP;
			case CHECK_TYPES:
				return DiagnosticGroups.CHECK_TYPES;
			case CHECK_VARS:
				return DiagnosticGroups.CHECK_VARIABLES;
			case CONST:
				return DiagnosticGroups.CONST;
			case CONSTANT_PROPERTY:
				return DiagnosticGroups.CONSTANT_PROPERTY;
			case DEBUGGER_STATEMENT_PRESENT:
				return DiagnosticGroups.DEBUGGER_STATEMENT_PRESENT;
			case DEPRECATED:
				return DiagnosticGroups.DEPRECATED;
			case ES_5_STRICT:
				return DiagnosticGroups.ES5_STRICT;
			case EXTERNS_VALIDATION:
				return DiagnosticGroups.EXTERNS_VALIDATION;
			case FILEOVERVIEW_JSDOC:
				return DiagnosticGroups.FILEOVERVIEW_JSDOC;
			case GLOBAL_THIS:
				return DiagnosticGroups.GLOBAL_THIS;
			case INTERNET_EXPLORER_CHECKS:
				return DiagnosticGroups.INTERNET_EXPLORER_CHECKS;
			case INVALID_CASTS:
				return DiagnosticGroups.INVALID_CASTS;
			case MISSING_PROPERTIES:
				return DiagnosticGroups.MISSING_PROPERTIES;
			case NON_STANDARD_JS_DOCS:
				return DiagnosticGroups.NON_STANDARD_JSDOC;
			case STRICT_MODULE_DEP_CHECK:
				return DiagnosticGroups.STRICT_MODULE_DEP_CHECK;
			case SUSPICIOUS_CODE:
				return DiagnosticGroups.SUSPICIOUS_CODE;
			case UNDEFINED_NAMES:
				return DiagnosticGroups.UNDEFINED_NAMES;
			case UNDEFINED_VARS:
				return DiagnosticGroups.UNDEFINED_VARIABLES;
			case UNKNOWN_DEFINES:
				return DiagnosticGroups.UNKNOWN_DEFINES;
			case CHECK_USELESS_CODE:
				return DiagnosticGroups.CHECK_USELESS_CODE;
			case VISIBILITY:
				return DiagnosticGroups.VISIBILITY;
			case CHECK_STRUCT_DICT_INHERITENCE:
				// TODO in the source only
				// return DiagnosticGroups.CHECK_STRUCT_DICT_INHERITENCE;
				break;
			case DUPLICATE_MESSAGES:
				return DiagnosticGroups.DUPLICATE_MESSAGE;
			case DUPLICATE_VARS:
				return DiagnosticGroups.DUPLICATE_VARS;
			case MISPLACED_TYPE_ANNOTATION:
				return DiagnosticGroups.MISPLACED_TYPE_ANNOTATION;
			case TWEAKS:
				return DiagnosticGroups.TWEAKS;
			case TYPE_INVALIDATION:
				return DiagnosticGroups.TYPE_INVALIDATION;
			case VIOLATED_MODULE_DEP:
				return DiagnosticGroups.VIOLATED_MODULE_DEP;
			case AGGRESSIVE_VAR_CHECK:
			case BROKEN_REQUIRES_LEVEL:
			case CHECK_GLOBAL_NAMES_LEVEL:
			case CHECK_GLOBAL_THIS_LEVEL:
				// case CHECK_MISSING_GET_CSS_NAME_LEVEL:
			case CHECK_MISSING_RETURN:
			case CHECK_REQUIRES:
			case CHECK_UNREACHABLE_CODE:
			case REPORT_MISSING_OVERRIDE:
			case REPORT_UNKNOWN_TYPES:
				// options are setted by separated methods
				break;
			}
		}
		return null;
	}

	private CheckLevel mapSeverityType(SeverityType severityType) {
		if (severityType != null) {
			switch (severityType) {
			case ERROR:
				return CheckLevel.ERROR;
			case OFF:
				return CheckLevel.OFF;
			case WARNING:
				return CheckLevel.WARNING;
			default:
				return CheckLevel.OFF;
			}
		}
		return null;
	}

	@Override
	public void visit(Warnings n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	@Override
	public void visit(WarningType n, Object ctx) throws Exception {
		super.visit(n, ctx);
	}

	private IPathResolver pathResolver;
	private CompilerOptions jsOptions;
	private JobDescriptionBuilder gssOptions;
	private SoyJsSrcOptions soyOptions;

	public Closurer(IPathResolver pathResolver) {
		this(new CompilerOptions(), new JobDescriptionBuilder(),
				new SoyJsSrcOptions(), pathResolver);
	}

	public Closurer(CompilerOptions jsOptions,
			JobDescriptionBuilder gssOptions, SoyJsSrcOptions soyOptions,
			IPathResolver pathResolver) {
		this.jsOptions = jsOptions;
		this.gssOptions = gssOptions;
		this.soyOptions = soyOptions;
		this.pathResolver = pathResolver;
	}

	public CompilerOptions getJsOptions() {
		return jsOptions;
	}

	public SoyJsSrcOptions getSoyOptions() {
		return soyOptions;
	}

	public JobDescriptionBuilder getGssOptions() {
		return gssOptions;
	}

}
