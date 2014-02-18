package com.digiarea.closure.model.bind;

import java.nio.charset.Charset;

import com.digiarea.closure.model.Check;
import com.digiarea.closure.model.CheckType;
import com.digiarea.closure.model.Checks;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.GssInputOrientation;
import com.digiarea.closure.model.GssOptimizationLevel;
import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssOutputOrientation;
import com.digiarea.closure.model.GssOutputRenamingMapFormat;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.GssVendor;
import com.digiarea.closure.model.Info;
import com.digiarea.closure.model.JsRenaming;
import com.digiarea.closure.model.JsRenamingFunctionPolice;
import com.digiarea.closure.model.JsRenamingPropertyPolice;
import com.digiarea.closure.model.JsRenamingVariablePolice;
import com.digiarea.closure.model.JsSourceMapFormat;
import com.digiarea.closure.model.LangType;
import com.digiarea.closure.model.Optimization;
import com.digiarea.closure.model.OptimizationType;
import com.digiarea.closure.model.Optimizations;
import com.digiarea.closure.model.SeverityType;
import com.digiarea.closure.model.SoyCodeStyle;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.Warning;
import com.digiarea.closure.model.WarningType;
import com.digiarea.closure.model.Warnings;
import com.google.common.base.Charsets;

public class ClosureModelManager {

	public static JsSourceMapFormat getDefaultJsSourceMapFormat() {
		return JsSourceMapFormat.DEFAULT;
	}

	public static GssRenamingType getDefaultGssRenamingType() {
		return GssRenamingType.NONE;
	}

	public static GssVendor getDefaultGssVendor() {
		return GssVendor.NONE;

	}

	public static GssOutputRenamingMapFormat getDefaultGssOutputRenamingMapFormat() {
		return GssOutputRenamingMapFormat.JSON;
	}

	public static GssOptimizationLevel getDefaultGssOptimizationLevel() {
		return GssOptimizationLevel.SAVE;
	}

	public static GssOutputFormat getDefaultGssOutputFormat() {
		return GssOutputFormat.COMPRESSED;
	}

	public static GssInputOrientation getDefaultGssInputOrientation() {
		return GssInputOrientation.LTR;
	}

	public static GssOutputOrientation getDefaultGssOutputOrientation() {
		return GssOutputOrientation.NOCHANGE;
	}

	public static SoyCssSchemeType getDefaultSoyCssSchemeType() {
		return SoyCssSchemeType.LITERAL;
	}

	public static SoyCodeStyle getDefaultSoyCodeStyle() {
		return SoyCodeStyle.CONCAT;
	}

	public static JsRenamingVariablePolice getDefaultJsRenamingVariablePolice() {
		return JsRenamingVariablePolice.OFF;
	}

	public static JsRenamingFunctionPolice getDefaultJsRenamingFunctionPolice() {
		return JsRenamingFunctionPolice.OFF;
	}

	public static JsRenamingPropertyPolice getDefaultJsRenamingPropertyPolice() {
		return JsRenamingPropertyPolice.OFF;
	}

	public static LangType getDefaultInputLangType() {
		return LangType.ECMASCRIPT_3;
	}

	public static LangType getDefaultOutputLangType() {
		return LangType.ECMASCRIPT_3;
	}

	public static String getDefaultJSInputDelimiter() {
		return "Input %num%";//$NON-NLS-1$
	}

	public static String getDefaultJSOutputWrapper() {
		return ""; //$NON-NLS-1$
	}

	public static Checks getDefaultChecks() {
		Checks checks = new Checks();
		for (CheckType type : CheckType.values()) {
			Check opt = new Check();
			opt.setCheck(false);
			opt.setType(type);
			checks.addCheck(opt);
		}
		return checks;
	}

	public static Closure getDefaultClosure() {
		Closure closure = new Closure();
		closure.getClosureJs().setBuild(true);
		ClosureJs closureJs = closure.getClosureJs();
		closureJs.setClosurePass(true);
		closureJs.setClosureStyle(true);

		JsRenaming renaming = new JsRenaming();
		renaming.setRenameLabels(true);

		closureJs.setRenaming(renaming);
		return closure;
	}

	public static Charset getDefaultCharset() {
		return Charsets.UTF_8;
	}

	public static Optimizations getDefaultOptimizations() {
		Optimizations optimizations = new Optimizations();
		for (OptimizationType type : OptimizationType.values()) {
			Optimization opt = new Optimization();
			opt.setOptimize(false);
			opt.setType(type);
			optimizations.addOptimization(opt);
		}
		return optimizations;
	}

	public static Warnings getDefaultWarnings() {
		Warnings warnings = new Warnings();
		for (WarningType type : WarningType.values()) {
			Warning warning = new Warning();
			warning.setSeverity(SeverityType.OFF);
			warning.setType(type);
			warnings.addWarning(warning);
		}
		return warnings;
	}

	public static Warnings synchWarnings(Warnings warnings) {
		Warnings defaultWarnings = getDefaultWarnings();
		if (warnings != null && warnings.getWarning().size() != 0) {
			for (Warning war : warnings.getWarning()) {
				for (Warning cat : defaultWarnings.getWarning()) {
					if (war.getType().equals(cat.getType())) {
						cat.setSeverity(war.getSeverity());
						break;
					}
				}
			}
		}
		return defaultWarnings;
	}

	public static Checks synchChecks(Checks checks) {
		Checks defaultChecks = getDefaultChecks();
		if (checks != null && checks.getCheck().size() != 0) {
			for (Check opt : checks.getCheck()) {
				for (Check cat : defaultChecks.getCheck()) {
					if (opt.getType().equals(cat.getType())) {
						cat.setCheck(opt.isCheck());
						break;
					}
				}
			}
		}
		return defaultChecks;
	}

	public static Optimizations synchOptimizations(Optimizations optimizations) {
		Optimizations defaultOptimizations = getDefaultOptimizations();
		if (optimizations != null
				&& optimizations.getOptimization().size() != 0) {
			for (Optimization opt : optimizations.getOptimization()) {
				for (Optimization cat : defaultOptimizations.getOptimization()) {
					if (opt.getType().equals(cat.getType())) {
						cat.setOptimize(opt.isOptimize());
						break;
					}
				}
			}
		}
		return defaultOptimizations;
	}

	public static Info getDefaultJSInfo() {
		Info n = new Info();
		n.setId("app.jsc");
		n.setName("JavaScript Application");
		n.setVersion("1.0.0");
		return n;
	}

	public static Info getDefaultSOYInfo() {
		Info n = new Info();
		n.setId("app.soy");
		n.setName("Templates Sources");
		n.setVersion("1.0.0");
		return n;
	}

	public static Info getDefaultGSSInfo() {
		Info n = new Info();
		n.setId("app.gss");
		n.setName("Stylesheets Sources");
		n.setVersion("1.0.0");
		return n;
	}

	public static boolean canBuild(ClosureJs closureJs) {
		return closureJs.isBuild() && closureJs.getBuildpath() != null
				&& closureJs.getBuildpath().getSource() != null
				&& !closureJs.getBuildpath().getSource().isEmpty();
	}

	public static boolean canBuild(ClosureGss closureJs) {
		return closureJs.isBuild() && closureJs.getBuildpath() != null
				&& closureJs.getBuildpath().getSource() != null
				&& !closureJs.getBuildpath().getSource().isEmpty();
	}

	public static boolean canBuild(ClosureSoy closureJs) {
		return closureJs.isBuild() && closureJs.getBuildpath() != null
				&& closureJs.getBuildpath().getSource() != null
				&& !closureJs.getBuildpath().getSource().isEmpty();
	}

}
