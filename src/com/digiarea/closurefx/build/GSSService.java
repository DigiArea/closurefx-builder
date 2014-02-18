package com.digiarea.closurefx.build;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.bind.ClosureModelManager;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.compiler.BuildpathManager;
import com.digiarea.closurefx.build.compiler.GSSBuildpathContainerResolver;
import com.digiarea.closurefx.build.compiler.GSSCompiler;
import com.digiarea.closurefx.build.compiler.GSSErrorManager;
import com.digiarea.closurefx.build.console.ConsoleManager;
import com.digiarea.closurefx.build.validation.BasicValidator;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;

public class GSSService extends Service<Closure> {

	private ProgressBar progressBar;
	private Closure closure;
	private BuildpathManager buildpathManager;
	private ConsoleManager consoleManager;
	private ResourceBundle bundle;
	private IPathResolver pathResolver;

	public GSSService(ProgressBar progressBar, Closure closure,
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

				if (ClosureModelManager.canBuild(closure.getClosureGss())) {
					try {
						BasicValidator validator = new BasicValidator(bundle,
								pathResolver);
						closure.getClosureGss().accept(validator, null);
						if (!validator.isValid()) {
							consoleManager
									.reportMessage(new Status(
											StatusType.ERROR,
											bundle.getString(IConstants.GSSConsole_Invalid),
											null));
						} else {
							GSSBuildpathContainerResolver resolver = new GSSBuildpathContainerResolver(
									closure.getClosureGss().getBuildpath(),
									pathResolver);
							resolver.resolve();
							GSSCompiler
									.compile(
											closure.getClosureGss(),
											buildpathManager.getGSSOptions(
													closure, pathResolver),
											new GSSErrorManager(consoleManager),
											resolver.getSources(), resolver
													.getExterns(), bundle,
											pathResolver);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					consoleManager.reportMessage(new Status(StatusType.WARNING,
							bundle.getString(IConstants.Console_NoResources),
							null));
				}

				updateProgress(1, 1);

				consoleManager.reportMessage(new Status(StatusType.INFO,
						"> Building complited", null));

				return getClosure();
			}
		};
		progressBar.progressProperty().bind(task.progressProperty());
		return task;
	}

	public static GSSService create(ProgressBar progressBar, Closure closure,
			BuildpathManager buildpathManager, ConsoleManager consoleManager,
			IPathResolver pathResolver) {
		return new GSSService(progressBar, closure, buildpathManager,
				consoleManager, pathResolver);
	}

}
