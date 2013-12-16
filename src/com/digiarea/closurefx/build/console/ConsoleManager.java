package com.digiarea.closurefx.build.console;

import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Service;

import com.digiarea.closure.core.IPathResolver;
import com.digiarea.closure.model.Closure;
import com.digiarea.closure.model.controller.IConsole;
import com.digiarea.closurefx.build.validation.Status;

public abstract class ConsoleManager {

	protected IConsole console;
	protected Service<?> service;
	protected Closure closure;
	protected ResourceBundle resourceBundle;
	protected IPathResolver pathResolver;

	public ConsoleManager(IConsole console, Closure closure,
			ResourceBundle resourceBundle, IPathResolver pathResolver) {
		this.console = console;
		this.closure = closure;
		this.resourceBundle = resourceBundle;
		this.pathResolver = pathResolver;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void clearMessages() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				console.getConsole().clear();
			}
		});
	}

	public void clearErrors() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				console.getProblems().clear();
			}
		});
	}

	public void reportError(final ClosureStatus error) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				console.addError(error);
			}
		});
	}

	public void reportErrors(final List<ClosureStatus> error) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				console.addErrors(error);
			}
		});
	}

	public void reportMessage(final Status status) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				console.addMessage(status);
			}
		});
	}

	public abstract boolean start();

	public boolean stop() {
		return service.cancel();
	}

}
