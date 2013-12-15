package com.digiarea.closure.help.providers;

import com.digiarea.closure.help.model.HelpTopic;
import com.digiarea.closurefx.IConstants;

public class HelpProvider implements IHelpProvider {

	@Override
	public HelpTopic getHelp() {

		HelpTopic help = new HelpTopic("Closure FX Builder", getClass()
				.getResource(IConstants.HELP_HTML + "/index.html"));

		HelpTopic faq = new HelpTopic("FAQ", getClass().getResource(
				IConstants.HELP_HTML + "/faq.html"));
		HelpTopic info = new HelpTopic("More Info", getClass().getResource(
				IConstants.HELP_HTML + "/info.html"));
		HelpTopic legal = new HelpTopic("Legal", getClass().getResource(
				IConstants.HELP_HTML + "/legal.html"));
		HelpTopic license = new HelpTopic("License", getClass().getResource(
				IConstants.HELP_HTML + "/license.html"));
		HelpTopic closure = new HelpTopic("About Closure FX Builder",
				getClass().getResource(IConstants.HELP_HTML + "/closure.html"));

		HelpTopic general = new HelpTopic("General", getClass().getResource(
				IConstants.HELP_HTML + "/index.html"));
		general.setRoot(true);

		HelpTopic editorIntegration = new HelpTopic(
				"Integration with Editors and IDEs", getClass().getResource(
						IConstants.HELP_GENERAL + "/editors-integration.html"));
		general.setRoot(true);

		general.getChildren().add(editorIntegration);

		HelpTopic compiler = new HelpTopic("Closure Compiler", getClass()
				.getResource(IConstants.HELP_COMPILER + "/index.html"));
		compiler.setRoot(true);

		HelpTopic compilerGettingStarted = new HelpTopic("Getting Started",
				getClass().getResource(
						IConstants.HELP_COMPILER + "/closure-compiler-gs.html"));

		// compiler
		HelpTopic compilerWarnings = new HelpTopic("Compilation Warnings",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-warnings.html"));
		HelpTopic compilerSources = new HelpTopic("Adding JavaScript Sources",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-gs-sources.html"));
		HelpTopic compilerLibrary = new HelpTopic(
				"Configure Compiler for Closure Library", getClass()
						.getResource(
								IConstants.HELP_COMPILER
										+ "/closure-compiler-gs-library.html"));
		HelpTopic compilerNew = new HelpTopic(
				"Create your first Build Configuration", getClass()
						.getResource(
								IConstants.HELP_COMPILER
										+ "/closure-compiler-gs-new.html"));
		HelpTopic compilerRunning = new HelpTopic("Running Closure Compiler",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-gs-running.html"));
		HelpTopic compilerOptions = new HelpTopic(
				"Configure Compiler's Options", getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-gs-options.html"));
		HelpTopic compilerConsole = new HelpTopic("Debugging your code",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-gs-console.html"));
		HelpTopic compilerProblems = new HelpTopic(
				"Identifying problems in your configuration", getClass()
						.getResource(
								IConstants.HELP_COMPILER
										+ "/closure-compiler-gs-problems.html"));
		HelpTopic compilerVariables = new HelpTopic("Configure Variables",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-buildpath-variables.html"));
		HelpTopic compilerClosure = new HelpTopic("Configure Closure Library",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-buildpath-closure.html"));
		HelpTopic compilerBuildpath = new HelpTopic("Configure Buildpath",
				getClass().getResource(
						IConstants.HELP_COMPILER
								+ "/closure-compiler-buildpath.html"));
		compilerBuildpath.getChildren().add(compilerClosure);
		compilerBuildpath.getChildren().add(compilerVariables);

		compilerGettingStarted.getChildren().add(compilerNew);
		compilerGettingStarted.getChildren().add(compilerSources);
		compilerGettingStarted.getChildren().add(compilerOptions);
		compilerGettingStarted.getChildren().add(compilerProblems);
		compilerGettingStarted.getChildren().add(compilerRunning);
		compilerGettingStarted.getChildren().add(compilerConsole);

		compiler.getChildren().add(compilerGettingStarted);
		compiler.getChildren().add(compilerLibrary);
		compiler.getChildren().add(compilerWarnings);
		compiler.getChildren().add(compilerBuildpath);

		// compiler

		// soy
		HelpTopic soy = new HelpTopic("Closure Templates", getClass()
				.getResource(IConstants.HELP_TEMPLATES + "/index.html"));
		soy.setRoot(true);

		HelpTopic soyGettingStarted = new HelpTopic("Getting Started",
				getClass().getResource(
						IConstants.HELP_TEMPLATES + "/closure-soy-gs.html"));

		HelpTopic soySources = new HelpTopic("Adding Stylesheets Sources",
				getClass().getResource(
						IConstants.HELP_TEMPLATES
								+ "/closure-soy-gs-sources.html"));
		HelpTopic soyNew = new HelpTopic(
				"Create your first Build Configuration", getClass()
						.getResource(
								IConstants.HELP_TEMPLATES
										+ "/closure-soy-gs-new.html"));
		HelpTopic soyRunning = new HelpTopic("Running Closure Templates",
				getClass().getResource(
						IConstants.HELP_TEMPLATES
								+ "/closure-soy-gs-running.html"));
//		HelpTopic soyOptions = new HelpTopic("Configure Compiler's Options",
//				getClass().getResource(
//						IConstants.HELP_TEMPLATES
//								+ "/closure-soy-gs-options.html"));
		HelpTopic soyConsole = new HelpTopic("Debugging your code", getClass()
				.getResource(
						IConstants.HELP_TEMPLATES
								+ "/closure-soy-gs-console.html"));
		HelpTopic soyProblems = new HelpTopic(
				"Identifying problems in your configuration", getClass()
						.getResource(
								IConstants.HELP_TEMPLATES
										+ "/closure-soy-gs-problems.html"));
		HelpTopic soyVariables = new HelpTopic("Configure Variables",
				getClass().getResource(
						IConstants.HELP_TEMPLATES
								+ "/closure-soy-buildpath-variables.html"));
		HelpTopic soyBuildpath = new HelpTopic("Configure Buildpath",
				getClass().getResource(
						IConstants.HELP_TEMPLATES
								+ "/closure-soy-buildpath.html"));
		soyBuildpath.getChildren().add(soyVariables);

		soyGettingStarted.getChildren().add(soyNew);
		soyGettingStarted.getChildren().add(soySources);
		//soyGettingStarted.getChildren().add(soyOptions);
		soyGettingStarted.getChildren().add(soyProblems);
		soyGettingStarted.getChildren().add(soyRunning);
		soyGettingStarted.getChildren().add(soyConsole);

		soy.getChildren().add(soyGettingStarted);
		soy.getChildren().add(soyBuildpath);
		// soy

		// gss
		HelpTopic gss = new HelpTopic("Closure Stylesheets", getClass()
				.getResource(IConstants.HELP_STYLESHEETS + "/index.html"));
		gss.setRoot(true);

		HelpTopic gssGettingStarted = new HelpTopic("Getting Started",
				getClass().getResource(
						IConstants.HELP_STYLESHEETS + "/closure-gss-gs.html"));

		HelpTopic gssSources = new HelpTopic("Adding Stylesheets Sources",
				getClass().getResource(
						IConstants.HELP_STYLESHEETS
								+ "/closure-gss-gs-sources.html"));
		HelpTopic gssNew = new HelpTopic(
				"Create your first Build Configuration", getClass()
						.getResource(
								IConstants.HELP_STYLESHEETS
										+ "/closure-gss-gs-new.html"));
		HelpTopic gssRunning = new HelpTopic("Running Closure Stylesheets",
				getClass().getResource(
						IConstants.HELP_STYLESHEETS
								+ "/closure-gss-gs-running.html"));
//		HelpTopic gssOptions = new HelpTopic("Configure Compiler's Options",
//				getClass().getResource(
//						IConstants.HELP_STYLESHEETS
//								+ "/closure-gss-gs-options.html"));
		HelpTopic gssConsole = new HelpTopic("Debugging your code", getClass()
				.getResource(
						IConstants.HELP_STYLESHEETS
								+ "/closure-gss-gs-console.html"));
		HelpTopic gssProblems = new HelpTopic(
				"Identifying problems in your configuration", getClass()
						.getResource(
								IConstants.HELP_STYLESHEETS
										+ "/closure-gss-gs-problems.html"));
		HelpTopic gssVariables = new HelpTopic("Configure Variables",
				getClass().getResource(
						IConstants.HELP_STYLESHEETS
								+ "/closure-gss-buildpath-variables.html"));
		HelpTopic gssBuildpath = new HelpTopic("Configure Buildpath",
				getClass().getResource(
						IConstants.HELP_STYLESHEETS
								+ "/closure-gss-buildpath.html"));
		gssBuildpath.getChildren().add(gssVariables);

		gssGettingStarted.getChildren().add(gssNew);
		gssGettingStarted.getChildren().add(gssSources);
		//gssGettingStarted.getChildren().add(gssOptions);
		gssGettingStarted.getChildren().add(gssProblems);
		gssGettingStarted.getChildren().add(gssRunning);
		gssGettingStarted.getChildren().add(gssConsole);

		gss.getChildren().add(gssGettingStarted);
		gss.getChildren().add(gssBuildpath);
		// gss

		help.getChildren().add(general);
		help.getChildren().add(compiler);
		help.getChildren().add(soy);
		help.getChildren().add(gss);
		help.getChildren().add(faq);
		help.getChildren().add(info);
		help.getChildren().add(legal);
		help.getChildren().add(license);
		help.getChildren().add(closure);

		return help;
	}

}
