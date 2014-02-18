package com.digiarea.closurefx.build.validation;

public class BasicStatusFormatter implements IStatusFormatter {

	@Override
	public String format(IStatus status) {
		StringBuffer buffer = new StringBuffer(getLabel(status));
		if (status.getMessage() != null) {
			buffer.append(status.getMessage());
		}
		if (status.getExceptionMessage() != null) {
			buffer.append(status.getExceptionMessage());
		}
		return buffer.toString();
	}

	protected String getLabel(IStatus status) {
		return "!" + status.getSeverity().name() + " ";
	}

}
