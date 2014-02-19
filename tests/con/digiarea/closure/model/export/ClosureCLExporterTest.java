package con.digiarea.closure.model.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import com.digiarea.closure.core.PathResolverFactory;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.export.ClosureCLExporter;
import com.digiarea.closure.model.zippy.ZippyClosureSerializer;
import com.digiarea.closurefx.build.validation.ClosureStatusFormatter;
import com.digiarea.closurefx.cli.console.PrintStreamConsole;

public class ClosureCLExporterTest {

	public static void main(String[] args) {
		File file = new File("/home/daginno/temp/ClosureTest/build.closure");

		ZippyClosureSerializer closureSerializer = new ZippyClosureSerializer();
		try {
			File output = new File("/home/daginno/temp/ClosureTest/closure.txt");
			if (!output.exists()) {
				output.createNewFile();
			}
			PrintStreamConsole console = new PrintStreamConsole(System.err,
					System.out, new ClosureStatusFormatter());
			ResourceBundle bundle = ResourceBundle.getBundle(
					"com.digiarea.closurefx.locale.editor", Locale.ENGLISH);

			FileOutputStream stream = new FileOutputStream(output);
			ClosureCLExporter exporter = new ClosureCLExporter(
					PathResolverFactory.getResolver(file), stream, console,
					bundle);
			Closure closure = closureSerializer.read(file.getAbsolutePath());
			closure.accept(exporter, null);

			console.generateReport();
			// System.out.println(stream.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
