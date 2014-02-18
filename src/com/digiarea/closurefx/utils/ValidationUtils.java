package com.digiarea.closurefx.utils;

import com.digiarea.closurefx.build.validation.IStatus;
import com.digiarea.closurefx.build.validation.IStatus.StatusType;
import com.digiarea.closurefx.build.validation.Status;

public class ValidationUtils {

	/**
	 * Create a status associated with this plugin.
	 * 
	 * @param severity
	 * @param aCode
	 * @param aMessage
	 * @param exception
	 * @return A status configured with this plugin's id and the given
	 *         parameters.
	 */
	public static IStatus createStatus(StatusType severity, String aMessage,
			Throwable exception) {
		return new Status(severity,
				aMessage != null ? aMessage : "No message.", exception); //$NON-NLS-1$
	}

	/**
	 * 
	 * @param aCode
	 * @param aMessage
	 * @param exception
	 * @return A status configured with this plugin's id and the given
	 *         parameters.
	 */
	public static IStatus createErrorStatus(String aMessage, Throwable exception) {
		return createStatus(StatusType.ERROR, aMessage, exception);
	}

	/**
	 * 
	 * @param aMessage
	 * @return A status configured with this plugin's id and the given
	 *         parameters.
	 */
	public static IStatus createErrorStatus(String aMessage) {
		return createStatus(StatusType.ERROR, aMessage, null);
	}

	/**
	 * 
	 * @param aMessage
	 * @return A status configured with this plugin's id and the given
	 *         parameters.
	 */
	public static IStatus createInfoStatus(String aMessage) {
		return createStatus(StatusType.INFO, aMessage, null);
	}

	/**
	 * 
	 * @param aMessage
	 * @return A status configured with this plugin's id and the given
	 *         parameters.
	 */
	public static IStatus createWarningStatus(String aMessage) {
		return createStatus(StatusType.WARNING, aMessage, null);
	}
}
