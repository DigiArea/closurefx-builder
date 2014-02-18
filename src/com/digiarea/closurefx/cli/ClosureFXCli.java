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
		if (ClosureModelManager.canBuild(closure.getClosureJs())) {
			try {
				BasicValidator validator = new BasicValidator(bundle,
						pathResolver);
				closure.getClosureJs().accept(validator, null);
				if (!validator.isValid()) {
					System.err.print(bundle
							.getString(IConstants.JSConsole_Invalid));
				} else {
					JSBuildpathContainerResolver resolver = new JSBuildpathContainerResolver(
							closure.getClosureJs().getBuildpath(), pathResolver);
					resolver.getDependencies();
					resolver.resolve();
					JSCCompilerCli compiler = new JSCCompilerCli(
							closure.getClosureJs(), options,
							resolver.getSources(), resolver.getExterns(),
							pathResolver);
					JSCPrintStreamErrorManager manager = new JSCPrintStreamErrorManager(
							System.out);
					compiler.setErrorManager(manager);
					compiler.setResourceBundle(bundle);
					com.google.javascript.jscomp.Compiler
							.setLoggingLevel(Level.SEVERE);
					compiler.build();
					// final error reporting
					manager.generateReport();
				}
			} catch (CircularDependencyException e) {
				System.err.print(MessageFormat.format(
						bundle.getString(IConstants.JSConsole_CicleDeps),
						e.getLocalizedMessage()));
			} catch (Exception e1) {
				System.err.print(e1.getMessage());
				e1.printStackTrace();
			}
		} else {
			System.err.print(bundle.getString(IConstants.Console_NoResources));
		}
	}

}
