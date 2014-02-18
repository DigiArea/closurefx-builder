package com.digiarea.closurefx.build.validation;

import java.util.List;

public interface IStatus {

	public enum StatusType {
		/**
		 * Status severity constant indicating this status represents the
		 * nominal case. This constant is also used as the status code
		 * representing the nominal case.
		 */
		OK, /**
		 * Status severity constant indicating this status represents a
		 * disagree.
		 */
		NO,
		/**
		 * Status type severity indicating this status is informational only.
		 */
		INFO,
		/**
		 * Status type severity indicating this status represents a warning.
		 */
		WARNING,
		/**
		 * Status type severity indicating this status represents an error.
		 */
		ERROR,
		/**
		 * Status type severity indicating this status represents a cancelation
		 */
		CANCEL, OFF, DEFAULT
	}

	/**
	 * Returns a list of status object immediately contained in this
	 * multi-status, or an empty list if this is not a multi-status.
	 * 
	 * @return an array of status objects
	 * @see #isMultiStatus()
	 */
	public List<IStatus> getChildren();

	/**
	 * Returns the relevant low-level exception, or <code>null</code> if none.
	 * For example, when an operation fails because of a network communications
	 * failure, this might return the <code>java.io.IOException</code>
	 * describing the exact nature of that failure.
	 * 
	 * @return the relevant low-level exception, or <code>null</code> if none
	 */
	public Throwable getException();

	/**
	 * Returns the message describing the outcome. The message is localized to
	 * the current locale.
	 * 
	 * @return a localized message
	 */
	public String getMessage();
	
	public String getExceptionMessage();

	/**
	 * Returns the severity. The severities are as follows (in descending
	 * order):
	 * <ul>
	 * <li><code>CANCEL</code> - cancelation occurred</li>
	 * <li><code>ERROR</code> - a serious error (most severe)</li>
	 * <li><code>WARNING</code> - a warning (less severe)</li>
	 * <li><code>INFO</code> - an informational ("fyi") message (least severe)</li>
	 * <li><code>OK</code> - everything is just fine</li>
	 * </ul>
	 * <p>
	 * The severity of a multi-status is defined to be the maximum severity of
	 * any of its children, or <code>OK</code> if it has no children.
	 * </p>
	 * 
	 * @return the severity: one of <code>OK</code>, <code>ERROR</code>,
	 *         <code>INFO</code>, <code>WARNING</code>, or <code>CANCEL</code>
	 * @see #matches(int)
	 */
	public StatusType getSeverity();

	/**
	 * Returns whether this status is a multi-status. A multi-status describes
	 * the outcome of an operation involving multiple operands.
	 * <p>
	 * The severity of a multi-status is derived from the severities of its
	 * children; a multi-status with no children is <code>OK</code> by
	 * definition. A multi-status carries a plug-in identifier, a status code, a
	 * message, and an optional exception. Clients may treat multi-status
	 * objects in a multi-status unaware way.
	 * </p>
	 * 
	 * @return <code>true</code> for a multi-status, <code>false</code>
	 *         otherwise
	 * @see #getChildren()
	 */
	public boolean isMultiStatus();

	/**
	 * Returns whether this status indicates everything is okay (neither info,
	 * warning, nor error).
	 * 
	 * @return <code>true</code> if this status has severity <code>OK</code>,
	 *         and <code>false</code> otherwise
	 */
	public boolean isOK();

}
