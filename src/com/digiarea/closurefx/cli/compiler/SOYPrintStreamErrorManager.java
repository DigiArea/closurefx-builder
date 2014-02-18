package com.digiarea.closurefx.cli.compiler;

import java.io.PrintStream;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.validation.IStatusFormatter;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.cli.console.PrintStreamConsole;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.JSError;
import com.google.template.soy.base.SoySyntaxException;

/**
 * dummy impl
 * 
 * @author daginno
 * 
 */
public class SOYPrintStreamErrorManager extends PrintStreamConsole {

	public SOYPrintStreamErrorManager(PrintStream stream,
			IStatusFormatter formatter) {
		super(stream, formatter);
	}

	public void report(SoySyntaxException error) {
		if (error != null) {
			report(new ClosureStatus(StatusType.ERROR,
					error.getOriginalMessage(), error.getSourceLocation()
							.getLineNumber(), error.getSourceLocation()
							.getFilePath()));
		}
	}

}
