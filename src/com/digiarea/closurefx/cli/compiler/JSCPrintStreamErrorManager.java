package com.digiarea.closurefx.cli.compiler;

import java.io.PrintStream;

import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;
import com.google.javascript.jscomp.MessageFormatter;
import com.google.javascript.jscomp.PrintStreamErrorManager;

/**
 * dummy impl
 * 
 * @author daginno
 *
 */
public class JSCPrintStreamErrorManager extends PrintStreamErrorManager {
	
	private PrintStream stream;

	public JSCPrintStreamErrorManager(MessageFormatter formatter,
			PrintStream stream) {
		super(formatter, stream);
		this.stream = stream;
	}

	public JSCPrintStreamErrorManager(PrintStream stream) {
		super(stream);
		this.stream = stream;
	}
	
	public void reportMessage(Status status) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getLabel(status.getSeverity()));
		buffer.append(status.getMessage());
		stream.println(buffer.toString());
	}
	
	private String getLabel(StatusType type) {
		switch (type) {
		case ERROR:
			return "!ERROR ";
		case WARNING:
			return "!WARNING ";
		default:
			return "!INFO ";
		}
	}
	
}
