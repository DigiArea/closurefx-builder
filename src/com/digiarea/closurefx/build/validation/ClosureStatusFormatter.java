package com.digiarea.closurefx.build.validation;

import com.digiarea.closurefx.build.console.ClosureStatus;

public class ClosureStatusFormatter extends BasicStatusFormatter {

	@Override
	public String format(IStatus status) {
		if (status instanceof ClosureStatus) {
			ClosureStatus closureStatus = (ClosureStatus) status;
			StringBuffer buffer = new StringBuffer(getLabel(status));
			if (closureStatus.getResource() != null) {
				buffer.append(closureStatus.getResource());
			}
			buffer.append(" line: " + closureStatus.getLine());
			buffer.append(" column: " + closureStatus.getColumn());
			if (status.getMessage() != null) {
				buffer.append("\n" + status.getMessage());
			}
			if (status.getExceptionMessage() != null) {
				buffer.append("\n" + status.getExceptionMessage());
			}
			return buffer.toString();
		} else {
			return super.format(status);
		}
	}

}
