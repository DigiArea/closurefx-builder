package com.digiarea.closurefx.build.compiler;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.ClosureGss;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.google.common.base.Charsets;
import com.google.common.css.ExitCodeHandler;
import com.google.common.css.JobDescription;
import com.google.common.css.JobDescriptionBuilder;
import com.google.common.css.SourceCode;
import com.google.common.css.compiler.ast.ErrorManager;
import com.google.common.css.compiler.commandline.DefaultCommandLineCompiler;
import com.google.common.io.Files;

public class GSSCompiler extends DefaultCommandLineCompiler {

	private static IPathResolver pathResolver;

	// private LinkedHashSet<File> externs;

	public GSSCompiler(JobDescription job, ExitCodeHandler exitCodeHandler,
			ErrorManager errorManager, LinkedHashSet<File> externs) {
		super(job, exitCodeHandler, errorManager);
		// this.externs = externs;
	}

	/**
	 * Compile inputlist from ClosureJs.
	 * 
	 * @param delta
	 *            For incremental build. If delta contains JS files which are on
	 *            build configuration, perform build.
	 */
	public static void compile(ClosureGss closureGss,
			JobDescriptionBuilder builder, GSSErrorManager errorManager,
			LinkedHashSet<File> sources, LinkedHashSet<File> externs,
			ResourceBundle resourceBundle, IPathResolver pathResolver) {

		try {

			List<SourceCode> units = new ArrayList<SourceCode>();
			for (File source : sources) {
				try {
					String str = Files.toString(source, Charsets.UTF_8);
					SourceCode gssSource = new SourceCode(
							source.getAbsolutePath(), str);
					units.add(gssSource);
				} catch (IOException e) {
					e.printStackTrace();
					errorManager
							.getConsoleManager()
							.reportMessage(
									new Status(
											StatusType.ERROR,
											MessageFormat.format(
													resourceBundle
															.getString(IConstants.JSConsole_FunctionMapFailed),
													source.getAbsoluteFile()),
											null));
				}
			}
			builder.setInputs(units);

			File renameFile = null;
			if (closureGss.getOutputRenamingMap() != null
					&& !closureGss.getOutputRenamingMap().isEmpty()) {
				String mapPath = pathResolver.toRealPath(closureGss
						.getOutputRenamingMap());
				renameFile = new File(mapPath);
			}

			GSSCompiler compiler = new GSSCompiler(builder.getJobDescription(),
					new GSSExitCodeHandler(), errorManager, externs);
			String output = compiler.execute(renameFile);

			if (IConstants.IS_TRIAL) {
				return;
			}

			String realPath = pathResolver.toRealPath(new Path(closureGss
					.getOutput().getPath()).append(
					closureGss.getOutput().getFile()).toString());
			;
			if (realPath != null && output != null) {
				File file = new File(realPath);
				try {
					Files.createParentDirs(file);
					Files.write(output.getBytes(), file);
				} catch (IOException e) {
					e.printStackTrace();
					errorManager
							.getConsoleManager()
							.reportMessage(
									new Status(
											StatusType.ERROR,
											MessageFormat.format(
													resourceBundle
															.getString(IConstants.GSSConsole_OutputFailed),
													realPath), null));
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

}
