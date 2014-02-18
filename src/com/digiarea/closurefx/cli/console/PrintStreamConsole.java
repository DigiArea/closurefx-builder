package com.digiarea.closurefx.cli.console;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.IStatusFormatter;

public class PrintStreamConsole extends AbstractCliConsole {

	private PrintStream stream;
	private boolean isLazy = false;

	public PrintStreamConsole(PrintStream stream, IStatusFormatter formatter) {
		super(new HashMap<StatusType, List<IStatus>>(), formatter);
		this.stream = stream;
	}

	public void reportMessage(IStatus status) {
		stream.println(formatter.format(status));
	}

	@Override
	public void generateReport() {
		for (StatusType type : errors.keySet()) {
			List<IStatus> status = errors.get(type);
			for (IStatus iStatus : status) {
				stream.println(formatter.format(iStatus));
			}
		}
	}

	public void setLazy(boolean isLazy) {
		this.isLazy = isLazy;
	}

	public boolean isLazy() {
		return isLazy;
	}

}
