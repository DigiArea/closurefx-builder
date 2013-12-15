package com.digiarea.closurefx.build.console;

import com.google.javascript.jscomp.DiagnosticType;

public class JSCClosureStatus extends ClosureStatus {

	private DiagnosticType diagnosticType;

	public JSCClosureStatus(StatusType severity, String message, int line,
			String resource, DiagnosticType diagnosticType) {
		super(severity, message, line, resource);
		this.diagnosticType = diagnosticType;
	}

	public JSCClosureStatus(StatusType severity, String message,
			Throwable exception, int line, int column, String resource,
			DiagnosticType diagnosticType) {
		super(severity, message, exception, line, column, resource);
		this.diagnosticType = diagnosticType;
	}

	public DiagnosticType getDiagnosticType() {
		return diagnosticType;
	}

}
