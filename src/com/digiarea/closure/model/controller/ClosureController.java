package com.digiarea.closure.model.controller;

import java.util.ResourceBundle;

import com.digiarea.closure.model.bind.ModelFacade;

public abstract class ClosureController {

    protected ModelFacade modelFacade;

    protected ResourceBundle bundle;

    public ClosureController(ModelFacade modelFacade, ResourceBundle bundle) {
        this.modelFacade = modelFacade;
        this.bundle = bundle;
    }

}
