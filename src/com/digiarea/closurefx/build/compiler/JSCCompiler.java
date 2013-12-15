package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.ClosureJs;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.google.common.io.Files;
import com.google.javascript.jscomp.CommandLineRunner;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;

public class JSCCompiler extends Compiler {

	private IPathResolver pathResolver;

	private ClosureJs closureJs;
	private CompilerOptions options;

	private List<File> sources;
	private List<File> externs;

	private JSCErrorManager errorManager;
	private ResourceBundle resourceBundle;

	public JSCCompiler(ClosureJs closureJs, CompilerOptions options,
			List<File> sources, List<File> externs, IPathResolver pathResolver) {
		this.closureJs = closureJs;
		this.options = options;
		this.sources = sources;
		this.externs = externs;
		this.pathResolver = pathResolver;
	}

	public void setErrorManager(JSCErrorManager errorManager) {
		super.setErrorManager(errorManager);
		this.errorManager = errorManager;
	}

	@Override
	public JSCErrorManager getErrorManager() {
		return errorManager;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}

	/**
	 * Compile Buildpath from ClosureJs.
	 * 
	 * @param delta
	 *            For incremental build. If delta contains JS files which are in
	 *            the buildpath, perform full build.
	 */
	public void build() {
		List<SourceFile> units = new ArrayList<SourceFile>();
		List<SourceFile> unitExterns = new ArrayList<SourceFile>();
		try {
			unitExterns.addAll(CommandLineRunner.getDefaultExterns());
		} catch (IOException e) {
			getErrorManager().getConsoleManager().reportMessage(
					new Status(StatusType.ERROR, resourceBundle
							.getString(IConstants.JSConsole_Externs), null));
		}

		for (File string : sources) {
			if (!string.exists()) {
				getErrorManager()
						.getConsoleManager()
						.reportMessage(
								new Status(
										StatusType.ERROR,
										MessageFormat.format(
												resourceBundle
														.getString(IConstants.JSConsole_SourceInvalid),
												string.getAbsolutePath()), null));
			} else {
				try {
					SourceFile jsFile = SourceFile.fromFile(string,
							getCharset(closureJs.getCharset()));
					units.add(jsFile);
				} catch (Exception e) {
					getErrorManager().getConsoleManager().reportMessage(
							new Status(StatusType.ERROR, string
									.getAbsolutePath(), e));
				}
			}
		}

		for (File string : externs) {
			if (!string.exists()) {
				getErrorManager()
						.getConsoleManager()
						.reportMessage(
								new Status(
										StatusType.ERROR,
										MessageFormat.format(
												resourceBundle
														.getString(IConstants.JSConsole_ExternInvalid),
												string.getAbsolutePath()), null));
			} else {
				SourceFile jsFile = SourceFile.fromFile(string,
						getCharset(closureJs.getCharset()));
				unitExterns.add(jsFile);
			}
		}

		build(units, unitExterns);
	}

	private Charset getCharset(String charset) {
		return Charset.forName(charset);
	}

	/**
	 * @param sources
	 * @param externs
	 */
	public void build(List<SourceFile> sources, List<SourceFile> externs) {
		try {
			Result result = compile(externs, sources, options);
			if (IConstants.IS_TRIAL) {
				return;
			}
			if (result.success && !isIdeMode()) {
				String realPath = pathResolver.toRealPath(new Path(closureJs
						.getOutput().getPath()).append(
						closureJs.getOutput().getFile()).toString());
				if (realPath != null) {
					File file = new File(realPath);
					if(file.exists()){
						file.delete();
					}
					try {
						Files.createParentDirs(file);
						Files.write(toSource().getBytes(), file);
						getErrorManager()
								.getConsoleManager()
								.reportMessage(
										new Status(
												StatusType.INFO,
												resourceBundle
														.getString(IConstants.JSConsole_Output),
												null));
					} catch (IOException e) {
						e.printStackTrace();
						getErrorManager()
								.getConsoleManager()
								.reportMessage(
										new Status(
												StatusType.ERROR,
												MessageFormat.format(
														resourceBundle
																.getString(IConstants.JSConsole_OutputFailed),
														realPath), null));
					}
				}
				if (result.externExport != null
						&& !result.externExport.isEmpty()
						&& closureJs.getExternExportsPath() != null
						&& !closureJs.getExternExportsPath().isEmpty()) {
					File file = new File(pathResolver.toRealPath(closureJs
							.getExternExportsPath()));
					try {
						Files.createParentDirs(file);
						Files.write(result.externExport.getBytes(), file);
					} catch (IOException e) {
						e.printStackTrace();
						getErrorManager()
								.getConsoleManager()
								.reportMessage(
										new Status(
												StatusType.ERROR,
												MessageFormat.format(
														resourceBundle
																.getString(IConstants.JSConsole_OutputExternsFailed),
														file.getAbsoluteFile()),
												null));
					}
				}
				if (closureJs.getRenaming() != null) {
					String variableOutput = closureJs.getRenaming()
							.getVariableMap().getOutput();
					if (result.variableMap != null && variableOutput != null
							&& !variableOutput.isEmpty()) {
						try {
							result.variableMap.save(pathResolver
									.toRealPath(variableOutput));
						} catch (IOException e) {
							e.printStackTrace();
							getErrorManager()
									.getConsoleManager()
									.reportMessage(
											new Status(
													StatusType.ERROR,
													MessageFormat.format(
															resourceBundle
																	.getString(IConstants.JSConsole_VariableMapFailed),
															variableOutput),
													null));
						}
					}
					String propertyOutput = closureJs.getRenaming()
							.getPropertyMap().getOutput();
					if (result.propertyMap != null && propertyOutput != null
							&& !propertyOutput.isEmpty()) {
						try {
							result.propertyMap.save(pathResolver
									.toRealPath(propertyOutput));
						} catch (IOException e) {
							e.printStackTrace();
							getErrorManager()
									.getConsoleManager()
									.reportMessage(
											new Status(
													StatusType.ERROR,
													MessageFormat.format(
															resourceBundle
																	.getString(IConstants.JSConsole_PropertyMapFailed),
															propertyOutput),
													null));
						}
					}
					String functionOutput = closureJs.getRenaming()
							.getFunctionMap().getOutput();
					if (result.namedAnonFunctionMap != null
							&& functionOutput != null
							&& !functionOutput.isEmpty()) {
						try {
							result.namedAnonFunctionMap.save(pathResolver
									.toRealPath(functionOutput));
						} catch (IOException e) {
							e.printStackTrace();
							getErrorManager()
									.getConsoleManager()
									.reportMessage(
											new Status(
													StatusType.ERROR,
													MessageFormat.format(
															resourceBundle
																	.getString(IConstants.JSConsole_FunctionMapFailed),
															functionOutput),
													null));
						}
					}
				}
			}
		} catch (Exception e) {
			if (e instanceof InterruptedException
					|| (e.getCause() != null && e.getCause() instanceof InterruptedException)) {
				getErrorManager().getConsoleManager().reportMessage(
						new Status(StatusType.ERROR, resourceBundle
								.getString(IConstants.Console_Interrupted),
								null));
			} else {
				getErrorManager().getConsoleManager().reportMessage(
						new Status(StatusType.ERROR, "", e));
			}
		}

	}
}
