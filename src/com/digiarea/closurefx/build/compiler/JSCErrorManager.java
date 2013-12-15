package com.digiarea.closurefx.build.compiler;

import com.digiarea.closurefx.build.console.ConsoleManager;
import com.digiarea.closurefx.build.console.JSCClosureStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.JSError;

public class JSCErrorManager implements ErrorManager {

	private ConsoleManager consoleManager;

	public JSCErrorManager(ConsoleManager consoleManager) {
		this.consoleManager = consoleManager;
	}

	public void report(CheckLevel level, JSError error) {
		if (error != null) {
			consoleManager.reportError(new JSCClosureStatus(mapSeverity(level),
					error.description, error.lineNumber, error.sourceName,
					error.getType()));
		}
	}

	private StatusType mapSeverity(CheckLevel level) {
		switch (level) {
		case ERROR:
			return StatusType.ERROR;
		case OFF:
			return StatusType.OFF;
		case WARNING:
			return StatusType.WARNING;
		default:
			return StatusType.DEFAULT;
		}
	}

	public ConsoleManager getConsoleManager() {
		return consoleManager;
	}

	public void setTypedPercent(double typedPercent) {
	}

	public double getTypedPercent() {
		return 0;
	}

	public void generateReport() {
	}

	public int getErrorCount() {
		return 0;
	}

	public JSError[] getErrors() {
		return new JSError[0];
	}

	public int getWarningCount() {
		return 0;
	}

	public JSError[] getWarnings() {
		return new JSError[0];
	}

}
