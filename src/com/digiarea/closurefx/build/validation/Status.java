package com.digiarea.closurefx.build.validation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import com.digiarea.closure.model.SeverityType;

public class Status implements IStatus {

	/**
	 * A standard OK status with an "ok" message.
	 */
	public static final IStatus OK_STATUS = new Status(StatusType.OK,
			"ok", null); //$NON-NLS-1$
	/**
	 * A standard CANCEL status with no message.
	 */
	public static final IStatus CANCEL_STATUS = new Status(StatusType.CANCEL,
			"", null); //$NON-NLS-1$

	/**
	 * A standard NO status with no message.
	 */
	public static final IStatus NO_STATUS = new Status(StatusType.NO, "", null); //$NON-NLS-1$

	/**
	 * A standard DEFAULT status with no message.
	 */
	public static final IStatus DEFAULT_STATUS = new Status(StatusType.DEFAULT,
			"", null); //$NON-NLS-1$

	private StatusType severity = StatusType.OK;

	/**
	 * Message, localized to the current locale.
	 */
	private String message;

	/**
	 * Wrapped exception, or <code>null</code> if none.
	 */
	private Throwable exception = null;

	/**
	 * Constant to avoid generating garbage.
	 */
	private List<IStatus> children;

	/**
	 * Creates a new status object. The created status has no children.
	 * 
	 * @param severity
	 *            the severity; one of <code>OK</code>, <code>ERROR</code>,
	 *            <code>INFO</code>, <code>WARNING</code>, or
	 *            <code>CANCEL</code>
	 * @param pluginId
	 *            the unique identifier of the relevant plug-in
	 * @param code
	 *            the plug-in-specific status code, or <code>OK</code>
	 * @param message
	 *            a human-readable message, localized to the current locale
	 * @param exception
	 *            a low-level exception, or <code>null</code> if not applicable
	 */
	public Status(StatusType severity, String message, Throwable exception) {
		setSeverity(severity);
		setMessage(message);
		setException(exception);
	}

	public List<IStatus> getChildren() {
		return children;
	}

	public Throwable getException() {
		return exception;
	}

	public String getExceptionMessage() {
		if (exception != null) {
			StringWriter errors = new StringWriter();
			exception.printStackTrace(new PrintWriter(errors));
			return errors.toString();
		}
		return "";
	}

	public String getMessage() {
		return message;
	}

	public StatusType getSeverity() {
		return severity;
	}

	public boolean isMultiStatus() {
		return true;
	}

	public boolean isOK() {
		return severity == StatusType.OK;
	}

	/**
	 * Sets the exception.
	 * 
	 * @param exception
	 *            a low-level exception, or <code>null</code> if not applicable
	 */
	protected void setException(Throwable exception) {
		this.exception = exception;
	}

	/**
	 * Sets the message. If null is passed, message is set to an empty string.
	 * 
	 * @param message
	 *            a human-readable message, localized to the current locale
	 */
	protected void setMessage(String message) {
		if (message == null)
			this.message = ""; //$NON-NLS-1$
		else
			this.message = message;
	}

	/**
	 * Sets the severity.
	 * 
	 * @param severity
	 *            the severity; {@link SeverityType}
	 */
	protected void setSeverity(StatusType severity) {
		this.severity = severity;
	}

	/**
	 * Returns a string representation of the status, suitable for debugging
	 * purposes only.
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Status "); //$NON-NLS-1$
		buf.append(severity.name()); //$NON-NLS-1$
		buf.append(": "); //$NON-NLS-1$
		buf.append(" code="); //$NON-NLS-1$
		buf.append(' ');
		buf.append(message);
		buf.append(' ');
		buf.append(exception);
		return buf.toString();
	}
}
