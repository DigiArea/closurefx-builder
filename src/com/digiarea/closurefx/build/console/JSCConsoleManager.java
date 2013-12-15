package com.digiarea.closurefx.build.console;

import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.controller.IConsole;
import com.digiarea.closurefx.build.JSService;
import com.digiarea.closurefx.build.compiler.BuildpathManager;

public class JSCConsoleManager extends ConsoleManager {

	public JSCConsoleManager(IConsole console, Closure closure,
			ResourceBundle resourceBundle, IPathResolver pathResolver) {
		super(console, closure, resourceBundle, pathResolver);
	}

	public boolean start() {
		clearErrors();
		clearMessages();
		if (service == null) {
			BuildpathManager buildpathManager = new BuildpathManager();
			service = JSService.create(console.getProgressBar(), closure,
					buildpathManager, this, pathResolver);
		}
		if (!service.isRunning()) {
			service.reset();
			service.start();
			return true;
		} else {
			return false;
		}
	}

}
