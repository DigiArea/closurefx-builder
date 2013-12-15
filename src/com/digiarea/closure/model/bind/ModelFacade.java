package com.digiarea.closure.model.bind;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

import javafx.util.Callback;

import com.digiarea.closure.model.Buildpath;
import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.GssDefine;
import com.digiarea.closure.model.GssExcludedClass;
import com.digiarea.closure.model.GssNonStandardFunction;
import com.digiarea.closure.model.GssUnrecognizeProperty;
import com.digiarea.closure.model.JsDefine;
import com.digiarea.closure.model.JsDoc;
import com.digiarea.closure.model.JsRenaming;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.Output;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.Source;
import com.digiarea.closure.model.SourceEntity;
import com.digiarea.closure.model.SourceEntry;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.WarningType;
import com.digiarea.closure.model.Warnings;
import com.digiarea.closure.model.validation.Validator;
import com.digiarea.closurefx.Document;
import com.digiarea.closurefx.build.console.GSSConsoleManager;
import com.digiarea.closurefx.build.console.JSCConsoleManager;
import com.digiarea.closurefx.build.console.SOYConsoleManager;

public class ModelFacade {

	protected Closure closure;
	protected Document document;
	private Callback<Class<?>, Object> factory;

	protected JSCConsoleManager jsConsole;
	protected SOYConsoleManager soyConsole;
	protected GSSConsoleManager gssConsole;

	public ModelFacade(Closure closure) {
		this.closure = closure;
	}

	public Closure getClosure() {
		return closure;
	}

	public void setJsConsole(JSCConsoleManager console) {
		this.jsConsole = console;
	}

	public JSCConsoleManager getJsConsole() {
		return jsConsole;
	}

	public void setGssConsole(GSSConsoleManager gssConsole) {
		this.gssConsole = gssConsole;
	}

	public GSSConsoleManager getGssConsole() {
		return gssConsole;
	}

	public void setSoyConsole(SOYConsoleManager soyConsole) {
		this.soyConsole = soyConsole;
	}

	public SOYConsoleManager getSoyConsole() {
		return soyConsole;
	}

	public Callback<Class<?>, Object> getFactory() {
		return factory;
	}

	public void setFactory(Callback<Class<?>, Object> factory) {
		this.factory = factory;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getFilePath() {
		return document.getPath();
	}

	public Document getDocument() {
		return document;
	}

	private String getPath(String path, boolean isExternal) {
		if (document.getFile() != null && !isExternal) {
			return document.getPathResolver().toRelativePath(path);
		}
		return path;
	}

	public void addSource(String absolutePath, SourceEntry entry,
			SourceEntity entity, boolean isExternal) {
		addSource(absolutePath, entry, entity, isExternal, true, true);
	}

	public void addSource(String absolutePath, SourceEntry entry,
			SourceEntity entity, boolean isExternal, boolean includeClosure,
			boolean includeSimple) {
		Source source = new Source();
		source.setEntityKind(entity);
		source.setEntryKind(entry);
		source.setPath(getPath(absolutePath, isExternal));
		source.setIncludeClosure(includeClosure);
		source.setIncludeSimple(includeSimple);
		if (!hasSourse(source, entity)) {
			getBuildpath(entity).addSource(source);
		}
	}

	public void addJSSourceClosure(String name, boolean isExternal,
			boolean includeClosure, boolean includeSimple) {
		Buildpath buildpath = getBuildpath(SourceEntity.JSC);
		Source source = null;
		for (Source source2 : buildpath.getSource()) {
			if (source2.getEntryKind() == SourceEntry.CLOSURE) {
				source = source2;
				break;
			}
		}
		if (source == null) {
			source = new Source();
		}

		source.setEntityKind(SourceEntity.JSC);
		source.setEntryKind(SourceEntry.CLOSURE);
		source.setPath(getPath(name, isExternal));
		source.setIncludeClosure(includeClosure);
		source.setIncludeSimple(includeSimple);

		int index = buildpath.getSource().indexOf(source);
		buildpath.getSource().remove(source);
		if (index == -1) {
			buildpath.getSource().add(source);
		} else {
			buildpath.getSource().add(index, source);
		}
	}

	public void removeSource(Source source, SourceEntity entity) {
		getBuildpath(entity).removeSource(source);
	}

	public boolean hasSourse(Source source, SourceEntity entity) {
		return hasSource(source, getBuildpath(entity).getSource());
	}

	private boolean hasSource(Source source, List<Source> sources) {
		for (Source input : sources) {
			if (input.getPath().equals(source.getPath())) {
				return true;
			}
		}
		return false;
	}

	// MOVERS

	public void moveSourceUp(Source source, SourceEntity entity) {
		List<Source> sources = getBuildpath(entity).getSource();
		int index = sources.indexOf(source);
		if (index != -1 && index != 0) {
			Collections.swap(sources, index, index - 1);
		}
	}

	public void moveSourceDown(Source source, SourceEntity entity) {
		List<Source> sources = getBuildpath(entity).getSource();
		int index = sources.indexOf(source);
		if (index != -1 && index != sources.size() - 1) {
			Collections.swap(sources, index, index + 1);
		}
	}

	public void moveSourceTop(Source source, SourceEntity entity) {
		List<Source> sources = getBuildpath(entity).getSource();
		int index = sources.indexOf(source);
		if (index != -1 && index != 0) {
			Collections.swap(sources, index, 0);
		}
	}

	public void moveSourceBottom(Source source, SourceEntity entity) {
		List<Source> sources = getBuildpath(entity).getSource();
		int index = sources.indexOf(source);
		int size = sources.size();
		if (index != -1 && index != size - 1) {
			Collections.swap(sources, index, size - 1);
		}
	}

	private Buildpath getBuildpath(SourceEntity entity) {
		switch (entity) {
		case GSS:
			return closure.getClosureGss().getBuildpath();
		case JSC:
			return closure.getClosureJs().getBuildpath();
		case SOY:
			return closure.getClosureSoy().getBuildpath();
		}
		return null;
	}

	public void setJSExternExportPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().setExternExportsPath(
				getPath(absolutePath, isExternal));
	}

	public void setJSCharset(Charset charset) {
		closure.getClosureJs().setCharset(charset.displayName());
	}

	public void setJSVariableInputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getVariableMap()
				.setInput(getPath(absolutePath, isExternal));

	}

	public void setJSVariableOutputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getVariableMap()
				.setOutput(getPath(absolutePath, isExternal));
	}

	public void setSourceExtern(SourceEntity entity, Source source,
			boolean isExtern) {
		if (source != null) {
			Buildpath buildpath = getBuildpath(entity);
			source.setExtern(isExtern);
			int index = buildpath.getSource().indexOf(source);
			buildpath.removeSource(source);
			source.setParent(buildpath);
			buildpath.getSource().add(index, source);
		}
	}

	public void setJSSourceIncludeClosure(SourceEntity entity, Source source,
			boolean isIncludeSource) {
		Buildpath buildpath = getBuildpath(entity);
		if (source != null) {
			source.setIncludeClosure(isIncludeSource);
			int index = buildpath.getSource().indexOf(source);
			buildpath.removeSource(source);
			source.setParent(buildpath);
			buildpath.getSource().add(index, source);
		}

	}

	public void setJSSourceIncludeSimple(SourceEntity entity, Source source,
			boolean isIncludeSimple) {
		Buildpath buildpath = getBuildpath(entity);
		if (source != null) {
			source.setIncludeSimple(isIncludeSimple);
			int index = buildpath.getSource().indexOf(source);
			buildpath.removeSource(source);
			source.setParent(buildpath);
			buildpath.getSource().add(index, source);
		}

	}

	public void setJSPropertyInputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getPropertyMap()
				.setInput(getPath(absolutePath, isExternal));
	}

	public void setJSPropertyOutputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getPropertyMap()
				.setOutput(getPath(absolutePath, isExternal));

	}

	public void setJSFunctionInputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getFunctionMap()
				.setInput(getPath(absolutePath, isExternal));
	}

	public void setJSFunctionOutputPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getRenaming().getFunctionMap()
				.setOutput(getPath(absolutePath, isExternal));

	}

	public void setJSSourceMapPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().setSourceMapFile(
				getPath(absolutePath, isExternal));

	}

	public void setJSTranslationPath(String absolutePath, boolean isExternal) {
		closure.getClosureJs().setTranslationsFile(
				getPath(absolutePath, isExternal));
	}

	public void addJSDoc(JsDoc doc) {
		closure.getClosureJs().getJsDocs().addJsDoc(doc);
	}

	public void removeJSDoc(JsDoc doc) {
		closure.getClosureJs().getJsDocs().removeJsDoc(doc);
	}

	public void addJSDefine(JsDefine define) {
		closure.getClosureJs().getJsDefines().addJsDefine(define);
	}

	public void removeJSDefine(JsDefine define) {
		closure.getClosureJs().getJsDefines().removeJsDefine(define);
	}

	public void addJSOutput(String absolutePath, boolean isExternal) {
		closure.getClosureJs().getOutput()
				.setPath(getPath(absolutePath, isExternal));
	}

	public void setSOYMessagePath(String absolutePath, boolean isExternal) {
		closure.getClosureSoy().setMessagesPath(
				getPath(absolutePath, isExternal));
	}

	public void setSOYAddMessagePlaceholder(String value, int caretPosition) {
		String string = closure.getClosureSoy().getMessagesPath();
		StringBuffer buf = new StringBuffer();
		if (string != null) {
			buf.append(string);
		}
		buf.insert(caretPosition, value);
		closure.getClosureSoy().setMessagesPath(buf.toString());
	}

	public void addSOYLocale(SoyLocale locale) {
		closure.getClosureSoy().getSoyLocales().addSoyLocale(locale);
	}

	public void removeSOYLocale(SoyLocale locale) {
		closure.getClosureSoy().getSoyLocales().removeSoyLocale(locale);
	}

	public void setSOYGlobalsPath(String absolutePath, boolean isExternal) {
		closure.getClosureSoy().setGlobalsPath(
				getPath(absolutePath, isExternal));
	}

	public void setSOYOutput(String absolutePath, boolean isExternal) {
		closure.getClosureSoy()
				.setOutputPath(getPath(absolutePath, isExternal));
	}

	public void addSOYOutputPlaceholder(String value, int caretPosition) {
		String string = closure.getClosureSoy().getOutputPath();
		StringBuffer buf = new StringBuffer();
		if (string != null) {
			buf.append(string);
		}
		buf.insert(caretPosition, value);
		closure.getClosureSoy().setOutputPath(buf.toString());
	}

	public void removeGSSDefine(GssDefine define) {
		closure.getClosureGss().getGssDefines().removeGssDefine(define);
	}

	public void assSOYDefine(GssDefine define) {
		closure.getClosureGss().getGssDefines().addGssDefine(define);
	}

	public void addGSSExcludedClass(GssExcludedClass clazz) {
		closure.getClosureGss().getGssExcludedClasses()
				.addGssExcludedClass(clazz);
	}

	public void removeGSSExcludedClass(GssExcludedClass clazz) {
		closure.getClosureGss().getGssExcludedClasses()
				.removeGssExcludedClass(clazz);
	}

	public void settGSSRenameFile(String absolutePath, boolean isExternal) {
		closure.getClosureGss().setOutputRenamingMap(
				getPath(absolutePath, isExternal));
	}

	public void removeSOYFunction(GssNonStandardFunction func) {
		closure.getClosureGss().getGssNonStandardFunctions()
				.removeGssNonStandardFunction(func);
	}

	public void removeSOYProperty(GssUnrecognizeProperty prop) {
		closure.getClosureGss().getGssUnrecognizeProperties()
				.removeGssUnrecognizeProperty(prop);
	}

	public void addSOYFunction(GssNonStandardFunction function) {
		closure.getClosureGss().getGssNonStandardFunctions()
				.addGssNonStandardFunction(function);
	}

	public void addSOYProperty(GssUnrecognizeProperty property) {
		closure.getClosureGss().getGssUnrecognizeProperties()
				.addGssUnrecognizeProperty(property);
	}

	public void applyJSWarningLevel(SeverityType type) {
		for (Warning warning : closure.getClosureJs().getWarnings()
				.getWarning()) {
			warning.setSeverity(type);
		}
	}

	public void applyJSWarningLevel(SeverityType severityType,
			WarningType warningType) {
		for (Warning warning : closure.getClosureJs().getWarnings()
				.getWarning()) {
			if (warning.getType() == warningType) {
				warning.setSeverity(severityType);
				break;
			}
		}
	}

	public void applyJSCheck(boolean b) {
		for (Check check : closure.getClosureJs().getChecks().getCheck()) {
			check.setCheck(b);
		}
	}

	public void applyJSCheck(CheckType type, boolean b) {
		for (Check check : closure.getClosureJs().getChecks().getCheck()) {
			if (check.getType() == type) {
				check.setCheck(b);
				break;
			}
		}
	}

	public void applyJSOptimization(boolean b) {
		for (Optimization optimization : closure.getClosureJs()
				.getOptimizations().getOptimization()) {
			optimization.setOptimize(b);
		}

	}

	public void applyJSOptimization(OptimizationType type, boolean b) {
		for (Optimization optimization : closure.getClosureJs()
				.getOptimizations().getOptimization()) {
			if (optimization.getType() == type) {
				optimization.setOptimize(b);
				break;
			}
		}

	}

	public void applyAdvancedOptions() {

		ClosureJs newCompiler = new ClosureJs();
		newCompiler.setBuild(true);

		try {
			Buildpath buildpath = (Buildpath) closure.getClosureJs()
					.getBuildpath().clone();
			newCompiler.setBuildpath(buildpath);
			Output output = (Output) closure.getClosureJs().getOutput().clone();
			newCompiler.setOutput(output);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		closure.setClosureJs(newCompiler);
		reinitialize(newCompiler);

		newCompiler.setManageClosureDependencies(true);
		newCompiler.setClosurePass(true);
		newCompiler.setSkipAllPasses(false);

		JsRenaming renaming = newCompiler.getRenaming();
		renaming.setRenameLabels(true);
		renaming.setVariablePolice(JsRenamingVariablePolice.ALL);
		renaming.setPropertyPolice(JsRenamingPropertyPolice.ALL_UNQUOTED);
		renaming.setShadowVariables(true);
		renaming.setDevirtualizePrototypeMethods(true);

		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.CHECK_GLOBAL_THIS_LEVEL);

		applyJSOptimization(OptimizationType.FOLD_CONSTANTS, true);
		applyJSOptimization(OptimizationType.COALESCE_VARIABLE_NAMES, true);
		applyJSOptimization(OptimizationType.DEAD_ASSIGNMENT_ELIMINATION, true);
		applyJSOptimization(OptimizationType.FOLD_CONSTANTS, true);
		applyJSOptimization(
				OptimizationType.EXTRACT_PROTOTYPE_MEMBER_DECLARATION, true);
		applyJSOptimization(OptimizationType.COLLAPSE_VARIABLE_DECLARATIONS,
				true);
		applyJSOptimization(OptimizationType.CONVERT_TO_DOTTED_PROPERTIES, true);
		applyJSOptimization(OptimizationType.REWRITE_FUNCTIONS_EXPRESSIONS,
				true);
		applyJSOptimization(OptimizationType.REMOVE_DEAD_CODE, true);
		applyJSOptimization(OptimizationType.OPTIMIZE_ARGUMENTS_ARRAY, true);
		applyJSOptimization(OptimizationType.COLLAPSE_OBJECT_LITERALS, true);
		// TODO options.protectHiddenSideEffects = true;
		// TODO options.removeClosureAsserts = true;
		applyJSOptimization(OptimizationType.ALIAS_KEYWORDS, true);
		// TODO options.reserveRawExports = true;
		applyJSOptimization(
				OptimizationType.REMOVE_UNUSED_PROTOTYPE_PROPERTIES, true);
		applyJSOptimization(
				OptimizationType.REMOVE_UNUSED_PROTOTYPE_PROPERTIES_IN_EXTERNS,
				true);
		applyJSOptimization(OptimizationType.COLLAPSE_ANONYMOUS_FUNCTIONS, true);
		applyJSOptimization(OptimizationType.COLLAPSE_PROPERTIES, true);
		applyJSOptimization(OptimizationType.REWRITE_FUNCTIONS_EXPRESSIONS,
				true);
		applyJSOptimization(OptimizationType.SMART_NAME_REMOVAL, true);
		applyJSOptimization(OptimizationType.INLINE_CONSTANT_VAR, true);
		applyJSOptimization(OptimizationType.INLINE_FUNCTION, true);
		applyJSOptimization(OptimizationType.INLINE_LOCAL_FUNCTION, true);
		applyJSOptimization(
				OptimizationType.ASSUME_CLOSURES_ONLY_CAPTURE_PREFERENCES,
				false);
		applyJSOptimization(OptimizationType.INLINE_GETTERS, true);
		applyJSOptimization(OptimizationType.INLINE_VARIABLES, true);
		applyJSOptimization(OptimizationType.INLINE_LOCAL_VARIABLES, true);
		applyJSOptimization(OptimizationType.REMOVE_UNUSED_VARS, true);
		applyJSOptimization(OptimizationType.REMOVE_UNUSED_LOCAL_VARS, true);
		applyJSOptimization(OptimizationType.CROSS_MODULE_CODE_MOTION, true);
		applyJSOptimization(OptimizationType.CROSS_MODULE_METHOD_MOTION, true);
		applyJSOptimization(OptimizationType.OPTIMIZE_PARAMETERS, true);
		applyJSOptimization(OptimizationType.OPTIMIZE_RETURNS, true);
		applyJSOptimization(OptimizationType.OPTIMIZE_CALLS, true);

		// TODO options.flowSensitiveInlineVariables = true;
		applyJSCheck(CheckType.COMPUTE_FUNCTION_SIDE_EFFECTS, true);

	}

	private void reinitialize(ClosureJs closureJs) {
		// reinitialize model
		try {
			// CHECKER
			Checker checker = new Checker();
			closureJs.accept(checker, null);
			// BINDING
			closureJs.accept(document.getBinder(), null);
			// VALIDATION
			Validator validator = document.getValidator();
			closureJs.accept(validator, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void applyWhitespacesOptions() {

		ClosureJs newCompiler = new ClosureJs();
		newCompiler.setBuild(true);
		try {
			Buildpath buildpath = (Buildpath) closure.getClosureJs()
					.getBuildpath().clone();
			newCompiler.setBuildpath(buildpath);
			Output output = (Output) closure.getClosureJs().getOutput().clone();
			newCompiler.setOutput(output);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		closure.setClosureJs(newCompiler);
		reinitialize(newCompiler);

		newCompiler.setSkipAllPasses(true);
	}

	public void applySimpleOptions() {

		ClosureJs newCompiler = new ClosureJs();
		newCompiler.setBuild(true);
		try {
			Buildpath buildpath = (Buildpath) closure.getClosureJs()
					.getBuildpath().clone();
			newCompiler.setBuildpath(buildpath);
			Output output = (Output) closure.getClosureJs().getOutput().clone();
			newCompiler.setOutput(output);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		closure.setClosureJs(newCompiler);
		reinitialize(newCompiler);

		// TODO options.replaceIdGenerators = false;
		newCompiler.setManageClosureDependencies(true);
		newCompiler.setClosurePass(true);
		newCompiler.setSkipAllPasses(false);

		JsRenaming renaming = newCompiler.getRenaming();
		renaming.setVariablePolice(JsRenamingVariablePolice.LOCAL);
		renaming.setPropertyPolice(JsRenamingPropertyPolice.OFF);
		renaming.setShadowVariables(true);
		renaming.setRenameLabels(true);

		applyJSWarningLevel(SeverityType.OFF,
				WarningType.CHECK_GLOBAL_THIS_LEVEL);

		applyJSOptimization(OptimizationType.INLINE_VARIABLES, false);
		applyJSOptimization(OptimizationType.INLINE_LOCAL_VARIABLES, true);
		applyJSOptimization(OptimizationType.FOLD_CONSTANTS, true);

		// TODO options.flowSensitiveInlineVariables = true;

		applyJSOptimization(OptimizationType.INLINE_FUNCTION, false);
		applyJSOptimization(OptimizationType.INLINE_LOCAL_FUNCTION, true);
		applyJSOptimization(
				OptimizationType.ASSUME_CLOSURES_ONLY_CAPTURE_PREFERENCES,
				false);
		applyJSOptimization(OptimizationType.COALESCE_VARIABLE_NAMES, true);
		applyJSOptimization(OptimizationType.DEAD_ASSIGNMENT_ELIMINATION, true);
		applyJSOptimization(OptimizationType.COLLAPSE_VARIABLE_DECLARATIONS,
				true);
		applyJSOptimization(OptimizationType.CONVERT_TO_DOTTED_PROPERTIES, true);
		applyJSOptimization(OptimizationType.REMOVE_DEAD_CODE, true);
		applyJSOptimization(OptimizationType.REMOVE_UNUSED_LOCAL_VARS, true);
		applyJSOptimization(OptimizationType.REMOVE_UNUSED_VARS, false);
		applyJSOptimization(OptimizationType.COLLAPSE_OBJECT_LITERALS, false);
		applyJSOptimization(OptimizationType.OPTIMIZE_ARGUMENTS_ARRAY, true);
		// TODO options.protectHiddenSideEffects = true;
	}

	public void applyQuietWarningLevel() {
		ClosureJs compiler = closure.getClosureJs();

		Warnings warnings = ClosureModelManager.synchWarnings(new Warnings());
		compiler.setWarnings(warnings);
		// BINDING
		try {
			warnings.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSWarningLevel(SeverityType.OFF, WarningType.CHECK_REQUIRES);
		applyJSWarningLevel(SeverityType.OFF, WarningType.CHECK_PROVIDES);
		// applyJSWarningLevel(SeverityType.OFF,
		// WarningType.CHECK_MISSING_GET_CSS_NAME_LEVEL);
		applyJSWarningLevel(SeverityType.OFF, WarningType.AGGRESSIVE_VAR_CHECK);
		applyJSWarningLevel(SeverityType.OFF, WarningType.CHECK_TYPES);
		applyJSWarningLevel(SeverityType.OFF,
				WarningType.CHECK_UNREACHABLE_CODE);
		applyJSWarningLevel(SeverityType.OFF, WarningType.CHECK_MISSING_RETURN);
		applyJSWarningLevel(SeverityType.OFF, WarningType.ACCESS_CONTROLS);
		applyJSWarningLevel(SeverityType.OFF, WarningType.CONST);
		applyJSWarningLevel(SeverityType.OFF, WarningType.CONSTANT_PROPERTY);
		applyJSWarningLevel(SeverityType.OFF,
				WarningType.CHECK_GLOBAL_NAMES_LEVEL);
		applyJSWarningLevel(SeverityType.OFF,
				WarningType.CHECK_GLOBAL_THIS_LEVEL);
		applyJSWarningLevel(SeverityType.OFF, WarningType.GLOBAL_THIS);
		applyJSWarningLevel(SeverityType.OFF, WarningType.ES_5_STRICT);
		applyJSWarningLevel(SeverityType.OFF, WarningType.NON_STANDARD_JS_DOCS);

		Checks checks = ClosureModelManager.synchChecks(new Checks());
		compiler.setChecks(checks);
		// BINDING
		try {
			checks.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSCheck(CheckType.CHECK_TYPES, false);
		applyJSCheck(CheckType.CHECK_SUSPICIOUS_CODE, false);
		applyJSCheck(CheckType.CHECK_CAJA, false);
	}

	public void applyDefaultWarningLevel() {
		ClosureJs compiler = closure.getClosureJs();
		compiler.setSkipAllPasses(false);
		compiler.setFunctionsOnly(false);

		Checks checks = ClosureModelManager.synchChecks(new Checks());
		compiler.setChecks(checks);
		// BINDING
		try {
			checks.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSCheck(CheckType.CHECK_SUSPICIOUS_CODE, true);
		applyJSCheck(CheckType.CHECK_SUSPICIOUS_CODE, true);
		applyJSCheck(CheckType.CHECK_CONTROL_SCTRUCTURES, true);

		Warnings warnings = ClosureModelManager.synchWarnings(new Warnings());
		compiler.setWarnings(warnings);
		// BINDING
		try {
			warnings.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.CHECK_UNREACHABLE_CODE);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.NON_STANDARD_JS_DOCS);
	}

	public void applyVerboseWarningLevel() {
		ClosureJs compiler = closure.getClosureJs();
		compiler.setSkipAllPasses(false);
		compiler.setFunctionsOnly(false);

		Checks checks = ClosureModelManager.synchChecks(new Checks());
		compiler.setChecks(checks);
		// BINDING
		try {
			checks.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSCheck(CheckType.CHECK_SUSPICIOUS_CODE, true);
		applyJSCheck(CheckType.CHECK_SYMBOLS, true);
		applyJSCheck(CheckType.CHECK_TYPES, true);

		Warnings warnings = ClosureModelManager.synchWarnings(new Warnings());
		compiler.setWarnings(warnings);
		// BINDING
		try {
			warnings.accept(document.getBinder(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.CHECK_GLOBAL_THIS_LEVEL);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.CHECK_MISSING_RETURN);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.CHECK_GLOBAL_NAMES_LEVEL);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.AGGRESSIVE_VAR_CHECK);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.MISSING_PROPERTIES);
		applyJSWarningLevel(SeverityType.WARNING, WarningType.DEPRECATED);
		applyJSWarningLevel(SeverityType.WARNING, WarningType.ES_5_STRICT);
		applyJSWarningLevel(SeverityType.WARNING,
				WarningType.NON_STANDARD_JS_DOCS);
	}

	public void addGSSOutput(String absolutePath, boolean isExternal) {
		closure.getClosureGss().getOutput()
				.setPath(getPath(absolutePath, isExternal));
	}

}
