package com.digiarea.closurefx.cli;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.PathResolverFactory;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.bind.ClosureModelManager;
import com.digiarea.closurefx.ClosureSerializerFactory;
import com.digiarea.closurefx.IClosureSerializer;
import com.digiarea.closurefx.IConstants;
import com.digiarea.closurefx.build.Closurer;
import com.digiarea.closurefx.build.compiler.JSBuildpathContainerResolver;
import com.digiarea.closurefx.build.validation.BasicValidator;
import com.digiarea.closurefx.build.validation.ClosureStatusFormatter;
import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.Status;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.cli.compiler.JSCCompilerCli;
import com.digiarea.closurefx.cli.compiler.JSCPrintStreamErrorManager;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;

public class ClosureFXCli {

	private ClosureFXCliOptions options;
	private IPathResolver pathResolver;
	private ResourceBundle bundle;
	private IClosureSerializer serializer = ClosureSerializerFactory
			.getSerializer();

	public ClosureFXCli(ClosureFXCliOptions options, ResourceBundle bundle) {
		this.options = options;
		this.pathResolver = PathResolverFactory.getResolver(this.options
				.getClosure());
		this.bundle = bundle;
	}

	public void run() {
		try {
			Closure closure = serializer.read(options.getClosure());
			Closurer closurer = new Closurer(pathResolver);
			closure.accept(closurer, null);

			if (options.isJs()) {
				runJS(closure, closurer.getJsOptions());
			}

		} catch (Exception e) {
			System.err.print("Failed to read " + options.getClosure() + ": "
					+ e.getLocalizedMessage());
		}
	}

	private void runJS(Closure closure, CompilerOptions options) {
		JSCPrintStreamErrorManager manager = new JSCPrintStreamErrorManager(
				System.out, new ClosureStatusFormatter());

		if (ClosureModelManager.canBuild(closure.getClosureJs())) {
			try {
				BasicValidator validator = new BasicValidator(bundle,
						pathResolver);
				closure.getClosureJs().accept(validator, null);
				if (!validator.isValid()) {
					manager.reportMessage(new Status(StatusType.ERROR, bundle
							.getString(IConstants.JSConsole_Invalid), null));
					reportJSErrors(validator, manager);
				} else {
					JSBuildpathContainerResolver resolver = new JSBuildpathContainerResolver(
							closure.getClosureJs().getBuildpath(), pathResolver);
					resolver.getDependencies();
					resolver.resolve();
					JSCCompilerCli compiler = new JSCCompilerCli(
							closure.getClosureJs(), options,
							resolver.getSources(), resolver.getExterns(),
							pathResolver);

					compiler.setErrorManager(manager);
					compiler.setResourceBundle(bundle);
					com.google.javascript.jscomp.Compiler
							.setLoggingLevel(Level.SEVERE);
					compiler.build();
				}
			} catch (CircularDependencyException e) {
				manager.reportMessage(new Status(StatusType.ERROR, bundle
						.getString(IConstants.JSConsole_CicleDeps), e));
			} catch (Exception e1) {
				manager.reportMessage(new Status(StatusType.ERROR, "", e1));
			}
		} else {
			manager.reportMessage(new Status(StatusType.ERROR, bundle
					.getString(IConstants.Console_NoResources), null));
		}
	}
	
	private void reportJSErrors(BasicValidator validator, JSCPrintStreamErrorManager manager) {
		for (IStatus status : validator.getErrors()) {
			manager.reportMessage(status);
		}
		for (IStatus status : validator.getWarnings()) {
			manager.reportMessage(status);
		}
	}

}
