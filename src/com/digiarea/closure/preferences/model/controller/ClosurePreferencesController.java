package com.digiarea.closure.preferences.model.controller;

import com.digiarea.closure.preferences.model.bind.ModelFacade;
import java.util.ResourceBundle;

public abstract class ClosurePreferencesController {

    protected ModelFacade modelFacade;

    protected ResourceBundle bundle;

    public ClosurePreferencesController(ModelFacade modelFacade, ResourceBundle bundle) {
        this.modelFacade = modelFacade;
        this.bundle = bundle;
    }

}
