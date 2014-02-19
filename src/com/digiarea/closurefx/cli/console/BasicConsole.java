package com.digiarea.closurefx.cli.console;

import java.util.List;
import java.util.Map;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.IStatusFormatter;

public class BasicConsole extends AbstractCliConsole {

	public BasicConsole(Map<StatusType, List<IStatus>> errors,
			IStatusFormatter formatter) {
		super(errors, formatter);
	}

	@Override
	public void generateReport() {
	}

	public Map<StatusType, List<IStatus>> getErrors() {
		return errors;
	}

}
