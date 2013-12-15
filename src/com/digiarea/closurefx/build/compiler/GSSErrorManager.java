package com.digiarea.closurefx.build.compiler;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.console.ConsoleManager;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.google.common.css.compiler.ast.BasicErrorManager;
import com.google.common.css.compiler.ast.GssError;

public class GSSErrorManager extends BasicErrorManager {

	private ConsoleManager consoleManager;

	public GSSErrorManager(ConsoleManager consoleManager) {
		this.consoleManager = consoleManager;
	}

	@Override
	public void print(String arg0) {
	}

	@Override
	public void report(GssError error) {
		if (error != null) {
			consoleManager.reportError(new ClosureStatus(StatusType.ERROR,
					error.getMessage(), error.getLocation().getLineNumber(),
					error.getLocation().getSourceCode().getFileName()));
		}
	}

	@Override
	public int getErrorCount() {
		return 0;
	}

	public ConsoleManager getConsoleManager() {
		return consoleManager;
	}

}
