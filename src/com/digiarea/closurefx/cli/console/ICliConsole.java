package com.digiarea.closurefx.cli.console;

import java.util.Map;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;

public interface ICliConsole {

	public void report(IStatus status);
	
	public void generateReport();

}
