package com.digiarea.closurefx.build.compiler;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.console.ConsoleManager;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.google.template.soy.base.SoySyntaxException;

public class SOYErrorManager {

	private ConsoleManager consoleManager;

	public SOYErrorManager(ConsoleManager consoleManager) {
		this.consoleManager = consoleManager;
	}

	public void report(SoySyntaxException error) {
		if (error != null) {
			consoleManager.reportError(new ClosureStatus(StatusType.ERROR,
					error.getOriginalMessage(), error.getSourceLocation()
							.getLineNumber(), error.getSourceLocation()
							.getFilePath()));
		}
	}

	public ConsoleManager getConsoleManager() {
		return consoleManager;
	}

}
