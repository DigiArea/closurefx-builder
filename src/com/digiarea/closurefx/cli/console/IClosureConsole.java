package com.digiarea.closurefx.cli.console;

import com.digiarea.closurefx.build.validation.IStatus;

/**
 * Intended for Closure visitors to report statuses
 * 
 * @author daginno
 * 
 */
public interface IClosureConsole {

	public void report(IStatus status);

	public void generateReport();

}
