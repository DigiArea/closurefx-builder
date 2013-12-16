package com.digiarea.closure.model.controller;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import com.digiarea.closurefx.build.console.ClosureStatus;
import com.digiarea.closurefx.build.validation.Status;

public interface IConsole {

	public ProgressBar getProgressBar();

	public ObservableList<ClosureStatus> getProblems();

	public Label getWarningLabel();

	public Label getErrorLabel();

	public ObservableList<Status> getConsole();

	public void addError(ClosureStatus error);

	public void addErrors(List<ClosureStatus> error);

	public void addMessage(Status error);

}
