package com.digiarea.closurefx.cli.compiler;

import java.io.PrintStream;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.IStatusFormatter;
import com.digiarea.closurefx.cli.console.PrintStreamConsole;
import com.google.common.css.compiler.ast.GssError;

/**
 * dummy impl
 * 
 * @author daginno
 * 
 */
public class GSSPrintStreamErrorManager extends PrintStreamConsole implements
		com.google.common.css.compiler.ast.ErrorManager {

	public GSSPrintStreamErrorManager(PrintStream stream,
			IStatusFormatter formatter) {
		super(stream, formatter);
	}

	@Override
	public boolean hasErrors() {
		return errors.get(StatusType.ERROR) != null
				&& !errors.get(StatusType.ERROR).isEmpty();
	}

	@Override
	public void report(GssError error) {
		if (error != null) {
			report(new ClosureStatus(StatusType.ERROR, error.getMessage(),
					error.getLocation().getLineNumber(), error.getLocation()
							.getSourceCode().getFileName()));
		}
	}

	@Override
	public void reportWarning(GssError error) {
		if (error != null) {
			report(new ClosureStatus(StatusType.WARNING, error.getMessage(),
					error.getLocation().getLineNumber(), error.getLocation()
							.getSourceCode().getFileName()));
		}
	}

}
