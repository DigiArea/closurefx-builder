package com.digiarea.closure.model.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.core.Path;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.controller.IConsole;
import com.digiarea.closurefx.build.console.ConsoleManager;

public class ExportCLIConsole extends ConsoleManager {

	private File file;
	private String name;

	public ExportCLIConsole(IConsole console, Closure closure,
			ResourceBundle resourceBundle, IPathResolver pathResolver) {
		super(console, closure, resourceBundle, pathResolver);
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean start() {
		clearMessages();

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			ClosureCLExporter exporter = new ClosureCLExporter(pathResolver,
					new FileOutputStream(new Path(file.getAbsolutePath())
							.append(name).addFileExtension("cli").toString()), console,
					resourceBundle);
			closure.accept(exporter, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void clearErrors() {
	}

}
