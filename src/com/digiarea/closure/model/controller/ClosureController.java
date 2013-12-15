package com.digiarea.closure.model.controller;

import com.digiarea.closure.model.bind.ModelFacade;
import java.util.ResourceBundle;

public abstract class ClosureController {

    protected ModelFacade modelFacade;

    protected ResourceBundle bundle;

    public ClosureController(ModelFacade modelFacade, ResourceBundle bundle) {
        this.modelFacade = modelFacade;
        this.bundle = bundle;
    }

}
