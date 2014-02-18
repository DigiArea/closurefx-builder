package com.digiarea.closurefx.build.console;

import com.digiarea.closurefx.build.validation.Status;

public class ClosureStatus extends Status {

	private int line;
	private int column;
	private String resource;

	public ClosureStatus(StatusType severity, String message, int line,
			String resource) {
		this(severity, message, null, line, 0, resource);
	}

	public ClosureStatus(StatusType severity, String message,
			Throwable exception, int line, int column, String resource) {
		super(severity, message, exception);
		this.line = line;
		this.column = column;
		this.resource = resource;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String getResource() {
		return resource;
	}

}
