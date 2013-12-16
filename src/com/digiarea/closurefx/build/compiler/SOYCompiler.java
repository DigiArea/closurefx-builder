package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.ClosureSoy;
import com.digiarea.closure.model.SoyCssSchemeType;
import com.digiarea.closure.model.SoyLocale;
import com.digiarea.closure.model.SoyLocales;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.google.template.soy.SoyFileSet;
import com.google.template.soy.base.SoyFileKind;
import com.google.template.soy.base.SoySyntaxException;
import com.google.template.soy.jssrc.SoyJsSrcOptions;
import com.google.template.soy.shared.SoyGeneralOptions.CssHandlingScheme;

/**
 * Executable for compiling a set of Soy files into corresponding JS source
 * files.
 */
public class SOYCompiler {

	private IPathResolver pathResolver;

	private ClosureSoy closureSoy;
	private SoyJsSrcOptions options;
	private LinkedHashSet<File> sources;
	private LinkedHashSet<File> externs;

	private String outputPath;
	private String globalPath;
	private String messagePath;

	private SOYErrorManager errorManager;
	private ResourceBundle resourceBundle;

	public SOYCompiler(ClosureSoy closureSoy, SoyJsSrcOptions options,
			LinkedHashSet<File> sources, LinkedHashSet<File> externs,
			IPathResolver pathResolver) {
		this.closureSoy = closureSoy;
		this.options = options;
		this.sources = sources;
		this.externs = externs;
		this.pathResolver = pathResolver;
	}

	public void setErrorManager(SOYErrorManager errorManager) {
		this.errorManager = errorManager;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	public String getGlobalPath() {
		globalPath = pathResolver.toRealPath(closureSoy.getGlobalsPath());
		return globalPath;
	}

	public String getOutputPath() {
		outputPath = pathResolver.toRealPath(closureSoy.getOutputPath());
		return outputPath;
	}

	public String getMessagePath() {
		messagePath = pathResolver.toRealPath(closureSoy.getMessagesPath());
		return messagePath;
	}

	public void compile() {

		try {

			SoyFileSet.Builder sfsBuilder = new SoyFileSet.Builder();
			for (File iFile : sources) {
				sfsBuilder.addWithKind(iFile, SoyFileKind.SRC);
			}
			for (File iFile : externs) {
				sfsBuilder.addWithKind(iFile, SoyFileKind.DEP);
			}

			if (closureSoy.isAllowExternalCalls()) {
				sfsBuilder.setAllowExternalCalls(closureSoy
						.isAllowExternalCalls());
			} else {
				sfsBuilder.setAllowExternalCalls(false);
			}

			if (closureSoy.getCssScheme() != null) {
				sfsBuilder.setCssHandlingScheme(getCssSchemeType(closureSoy
						.getCssScheme()));
			}

			if (getGlobalPath() != null) {
				try {
					sfsBuilder.setCompileTimeGlobals(new File(getGlobalPath()));
				} catch (IOException e) {
					errorManager
							.getConsoleManager()
							.reportMessage(
									new Status(
											StatusType.ERROR,
											resourceBundle
													.getString(IConstants.SOYConsole_GlobalsFile),
											null));
				}
			}

			SoyFileSet sfs = sfsBuilder.build();
			Method method = Magic.getDeclaredMethod(SoyFileSet.class,
					"compileToJsSrcFiles", String.class, String.class,
					SoyJsSrcOptions.class, List.class, String.class);

			if (getOutputPath() != null) {
				try {
					if (IConstants.IS_TRIAL) {
						method.invoke(sfs, "", "", options,
								getSoyLocales(closureSoy.getSoyLocales()),
								getMessagePath());
					} else {
						method.invoke(sfs, getOutputPath(), "", options,
								getSoyLocales(closureSoy.getSoyLocales()),
								getMessagePath());
					}
				} catch (IllegalArgumentException e) {
					errorManager.getConsoleManager().reportMessage(
							new Status(StatusType.ERROR, "", e));
				} catch (IllegalAccessException e) {
					errorManager.getConsoleManager().reportMessage(
							new Status(StatusType.ERROR, "", e));
				} catch (InvocationTargetException e) {
					if (e.getTargetException() instanceof SoySyntaxException) {
						errorManager.report((SoySyntaxException) e
								.getTargetException());
					} else {
						errorManager.getConsoleManager().reportMessage(
								new Status(StatusType.ERROR, "", e));
					}
				}
			}
		} catch (Exception e) {
			if (e instanceof InterruptedException
					|| (e.getCause() != null && e.getCause() instanceof InterruptedException)) {
				errorManager.getConsoleManager().reportMessage(
						new Status(StatusType.ERROR, resourceBundle
								.getString(IConstants.Console_Interrupted),
								null));
			} else {
				errorManager.getConsoleManager().reportMessage(
						new Status(StatusType.ERROR, "", e));
			}
		}
	}

	private List<String> getSoyLocales(SoyLocales locales) {
		List<String> strings = new ArrayList<String>();
		if (locales != null) {
			for (SoyLocale locale : locales.getSoyLocale()) {
				strings.add(locale.getValue());
			}
		}
		return strings;
	}

	private CssHandlingScheme getCssSchemeType(SoyCssSchemeType type) {
		switch (type) {
		case GOOG:
			return CssHandlingScheme.LITERAL;
		case LITERAL:
			return CssHandlingScheme.LITERAL;
		case REFERENCE:
			return CssHandlingScheme.REFERENCE;
		}
		return null;
	}

}
