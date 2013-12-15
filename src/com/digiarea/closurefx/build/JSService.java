package com.digiarea.closurefx.build;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.bind.ClosureModelManager;
import com.digiarea.closure.model.controller.dialogs.DialogFactory;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.compiler.BuildpathManager;
import com.digiarea.closurefx.build.compiler.JSBuildpathContainerResolver;
import com.digiarea.closurefx.build.compiler.JSCCompiler;
import com.digiarea.closurefx.build.compiler.JSCErrorManager;
import com.digiarea.closurefx.build.console.ConsoleManager;
import com.digiarea.closurefx.build.console.JSCConsoleManager;
import com.digiarea.closurefx.build.validation.BasicValidator;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;

public class JSService extends Service<Closure> {

	private ProgressBar progressBar;
	private Closure closure;
	private BuildpathManager buildpathManager;
	private ConsoleManager consoleManager;
	private ResourceBundle bundle;
	private IPathResolver pathResolver;

	public JSService(ProgressBar progressBar, Closure closure,
			BuildpathManager buildpathManager, ConsoleManager consoleManager,
			IPathResolver pathResolver) {
		this.progressBar = progressBar;
		this.closure = closure;
		this.buildpathManager = buildpathManager;
		this.consoleManager = consoleManager;
		this.bundle = consoleManager.getResourceBundle();
		this.pathResolver = pathResolver;
	}

	public void setClosure(Closure closure) {
		this.closure = closure;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public Closure getClosure() {
		return closure;
	}

	@Override
	protected Task<Closure> createTask() {
		Task<Closure> task = new Task<Closure>() {
			protected Closure call() throws IOException, MalformedURLException {

				consoleManager.reportMessage(new Status(StatusType.INFO, bundle
						.getString(IConstants.Console_Start), null));

				if (isCancelled()) {
					return null;
				}

				if (ClosureModelManager.canBuild(closure.getClosureJs())) {
					try {
						BasicValidator validator = new BasicValidator(bundle,
								pathResolver);
						closure.getClosureJs().accept(validator, null);
						if (!validator.isValid()) {
							consoleManager
									.reportMessage(new Status(
											StatusType.ERROR,
											bundle.getString(IConstants.JSConsole_Invalid),
											null));
						} else {
							CompilerOptions options = buildpathManager
									.getJSCOptions(closure, pathResolver);
							// options.setIdeMode(true);
							JSBuildpathContainerResolver resolver = new JSBuildpathContainerResolver(
									closure.getClosureJs().getBuildpath(),
									pathResolver);
							resolver.getDependencies();
							resolver.resolve();
							JSCCompiler compiler = new JSCCompiler(
									closure.getClosureJs(), options,
									resolver.getSources(),
									resolver.getExterns(), pathResolver);
							compiler.setErrorManager(new JSCErrorManager(
									consoleManager));
							compiler.setResourceBundle(bundle);
							com.google.javascript.jscomp.Compiler
									.setLoggingLevel(Level.SEVERE);
							compiler.build();
							if (IConstants.IS_TRIAL) {
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										DialogFactory.getStatusDialog(
												consoleManager
														.getResourceBundle(),
												new Status(
														StatusType.WARNING,
														bundle.getString(IConstants.StatusDialog_Desc_Trial),
														null),
												bundle.getString(IConstants.StatusDialog_Title_Trial));
									}
								});
							}
						}
					} catch (CircularDependencyException e) {
						consoleManager
								.reportMessage(new Status(
										StatusType.ERROR,
										MessageFormat.format(
												bundle.getString(IConstants.JSConsole_CicleDeps),
												e.getLocalizedMessage()), null));
					} catch (Exception e1) {
						consoleManager
								.reportMessage(new Status(StatusType.ERROR, e1
										.getLocalizedMessage(), e1));
					}
				} else {
					consoleManager.reportMessage(new Status(StatusType.WARNING,
							bundle.getString(IConstants.Console_NoResources),
							null));
				}

				updateProgress(1, 1);

				consoleManager.reportMessage(new Status(StatusType.INFO, bundle
						.getString(IConstants.Console_Finish), null));

				return getClosure();
			}
		};
		progressBar.progressProperty().bind(task.progressProperty());
		return task;
	}

	public static JSService create(ProgressBar progressBar, Closure closure,
			BuildpathManager buildpathManager,
			JSCConsoleManager consoleManager, IPathResolver pathResolver) {
		return new JSService(progressBar, closure, buildpathManager,
				consoleManager, pathResolver);
	}

}
