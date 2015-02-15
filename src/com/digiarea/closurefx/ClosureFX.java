package com.digiarea.closurefx;

import java.util.List;
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
		checkCommandLine();

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
	
	private void checkCommandLine() {
		Option help = new Option("help", "print this message");
		Option closure = OptionBuilder
				.withArgName("path")
				.hasArg()
				.withDescription(
						"a path to the Build Configuration File (*.closure)")
				.create("closure");

		Options options = new Options();
		options.addOption(help);
		options.addOption(closure);

		// create the parser
		CommandLineParser parser = new BasicParser();
		List<String> params = getParameters().getRaw();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options,
					params.toArray(new String[params.size()]));
			if (line.hasOption(help.getOpt())) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Closure FX Builder", options);
				System.exit(0);
			}
			if (line.hasOption(closure.getOpt())) {
				ClosureFXCliOptions cliOptions = new ClosureFXCliOptions();
				cliOptions.setClosure(line.getOptionValue(closure.getOpt()));

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
		launch(args);
	}
	
}