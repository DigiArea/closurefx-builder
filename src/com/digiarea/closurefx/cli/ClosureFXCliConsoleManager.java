package com.digiarea.closurefx.cli;

import java.util.ResourceBundle;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.controller.IConsole;
import com.digiarea.closurefx.build.console.ConsoleManager;

public class ClosureFXCliConsoleManager extends ConsoleManager {

	public ClosureFXCliConsoleManager(IConsole console, Closure closure,
			ResourceBundle resourceBundle, IPathResolver pathResolver) {
		super(console, closure, resourceBundle, pathResolver);
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

}
