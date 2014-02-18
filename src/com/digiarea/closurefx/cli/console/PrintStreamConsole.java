package com.digiarea.closurefx.cli.console;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.IStatusFormatter;

public class PrintStreamConsole extends AbstractCliConsole {

	private PrintStream errorStream;
	private PrintStream messageStream;
	private boolean isLazy = false;

	public PrintStreamConsole(PrintStream errorStream,
			PrintStream messageStream, IStatusFormatter formatter) {
		super(new HashMap<StatusType, List<IStatus>>(), formatter);
		this.errorStream = errorStream;
		this.messageStream = messageStream;
	}

	public void reportMessage(IStatus status) {
		switch (status.getSeverity()) {
		case CANCEL:
		case NO:
		case DEFAULT:
		case ERROR:
			errorStream.println(formatter.format(status));
			break;
		case WARNING:
		case INFO:
		case OFF:
		case OK:
			messageStream.println(formatter.format(status));
			break;
		}

	}

	@Override
	public void generateReport() {
		for (StatusType type : errors.keySet()) {
			List<IStatus> status = errors.get(type);
			for (IStatus iStatus : status) {
				reportMessage(iStatus);
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
