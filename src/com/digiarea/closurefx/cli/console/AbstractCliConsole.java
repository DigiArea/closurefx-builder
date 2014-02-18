package com.digiarea.closurefx.cli.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.IStatusFormatter;

public abstract class AbstractCliConsole implements ICliConsole {

	protected Map<StatusType, List<IStatus>> errors;
	protected IStatusFormatter formatter;

	public AbstractCliConsole(Map<StatusType, List<IStatus>> errors,
			IStatusFormatter formatter) {
		this.errors = errors;
		this.formatter = formatter;
	}

	@Override
	public void report(IStatus status) {
		List<IStatus> statuses = errors.get(status.getSeverity());
		if (statuses == null) {
			statuses = new ArrayList<IStatus>();
			errors.put(status.getSeverity(), statuses);
		}
		statuses.add(status);
	}
	
}
