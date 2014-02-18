package com.digiarea.closurefx.cli.console;

import com.digiarea.closurefx.build.validation.IStatus;

public interface ICliConsole {

	public void report(IStatus status);
	
	public void generateReport();

}
