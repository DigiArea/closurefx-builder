package com.digiarea.closurefx.cli.compiler;

import java.io.PrintStream;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.validation.IStatusFormatter;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.cli.console.PrintStreamConsole;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.JSError;

/**
 * dummy impl
 * 
 * @author daginno
 * 
 */
public class JSCPrintStreamErrorManager extends PrintStreamConsole implements
		ErrorManager {

	public JSCPrintStreamErrorManager(PrintStream errorStream,
			PrintStream messageStream, IStatusFormatter formatter) {
		super(errorStream, messageStream, formatter);
	}

	@Override
	public int getErrorCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSError[] getErrors() {
		return new JSError[]{};
	}

	@Override
	public double getTypedPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWarningCount() {
		return 0;
	}

	@Override
	public JSError[] getWarnings() {
		return new JSError[]{};
	}

	@Override
	public void report(CheckLevel arg0, JSError arg1) {
		report(new ClosureStatus(getSeverity(arg0), arg1.description, null,
				arg1.getLineNumber(), arg1.getCharno(), arg1.sourceName));
	}

	private StatusType getSeverity(CheckLevel arg0) {
		switch (arg0) {
		case ERROR:
			return StatusType.ERROR;
		case WARNING:
			return StatusType.WARNING;
		default:
			return StatusType.INFO;
		}
	}

	@Override
	public void setTypedPercent(double arg0) {
		// TODO Auto-generated method stub

	}

}
