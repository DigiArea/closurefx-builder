package com.digiarea.closurefx;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.digiarea.closurefx.cli.ClosureFXCli;
import com.digiarea.closurefx.cli.ClosureFXCliOptions;

/**
 * 
 * @author norb
 */
public class ClosureFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle(IConstants.ClosureFX);
		stage.getIcons().add(ResourceUtils.CLOSURE_ICON);

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"ClosureFX.fxml"));
		loader.setResources(ResourceBundle.getBundle(
				"com.digiarea.closurefx.locale.editor", Locale.ENGLISH));

		AnchorPane root = (AnchorPane) loader.load();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				ResourceUtils.getStylesheets().toExternalForm());
		stage.setScene(scene);

		ClosureFXController controller = loader.getController();
		controller.setStage(stage);

		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Option help = new Option("help", "print this message");
		Option closure = OptionBuilder
				.withArgName("path")
				.hasArg()
				.withDescription(
						"a path to the Build Configuration File (*.closure) to run")
				.create("closure");
		Option js = new Option("js",
				"run Closure Compiler to compile JavaScript sources");
		Option soy = new Option("soy",
				"run Closure Templates to compile Soy Templates sources");
		Option gss = new Option("gss",
				"run Closure Stylesheets to compile GSS/CSS sources");

		Options options = new Options();
		options.addOption(help);
		options.addOption(closure);
		options.addOption(js);
		options.addOption(soy);
		options.addOption(gss);

		// create the parser
		CommandLineParser parser = new BasicParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);
			if (line.hasOption(help.getOpt())) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("ClosureFX Builder", options);
				return;
			}
			if (line.hasOption(closure.getOpt())) {
				ClosureFXCliOptions cliOptions = new ClosureFXCliOptions();
				cliOptions.setClosure(line.getOptionValue(closure.getOpt()));

				if (line.hasOption(js.getOpt())) {
					cliOptions.setJs(Boolean.valueOf(line.getOptionValue(js
							.getOpt())));
				}

				if (line.hasOption(soy.getOpt())) {
					cliOptions.setSoy(Boolean.valueOf(line.getOptionValue(soy
							.getOpt())));
				}

				if (line.hasOption(gss.getOpt())) {
					cliOptions.setGss(Boolean.valueOf(line.getOptionValue(gss
							.getOpt())));
				}

				ClosureFXCli runner = new ClosureFXCli(cliOptions,
						ResourceBundle.getBundle(
								"com.digiarea.closurefx.locale.editor",
								Locale.ENGLISH));
				runner.run();
				System.exit(0);
			}
		} catch (ParseException exp) {
			// oops, something went wrong
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}

		launch(args);
	}
}