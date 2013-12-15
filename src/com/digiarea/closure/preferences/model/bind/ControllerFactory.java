package com.digiarea.closure.preferences.model.bind;

import javafx.util.Callback;
import com.digiarea.closure.preferences.model.controller.PreferenceVariablesController;
import com.digiarea.closure.preferences.model.controller.PreferencesController;
import com.digiarea.closure.preferences.model.controller.PreferenceEditorsController;
import com.digiarea.closure.preferences.model.controller.PreferenceClosureController;
import com.digiarea.closure.preferences.model.bind.ModelFacade;
import java.util.ResourceBundle;

public class ControllerFactory implements Callback<Class<?>, Object> {

    @Override
    public Object call(Class<?> clazz) {
        if (clazz == PreferenceVariablesController.class) {
            if (preferenceVariablesController == null) {
                preferenceVariablesController = new PreferenceVariablesController(modelFacade, bundle);
            }
            return preferenceVariablesController;
        } else if (clazz == PreferencesController.class) {
            if (preferencesController == null) {
                preferencesController = new PreferencesController(modelFacade, bundle);
            }
            return preferencesController;
        } else if (clazz == PreferenceEditorsController.class) {
            if (preferenceEditorsController == null) {
                preferenceEditorsController = new PreferenceEditorsController(modelFacade, bundle);
            }
            return preferenceEditorsController;
        } else if (clazz == PreferenceClosureController.class) {
            if (preferenceClosureController == null) {
                preferenceClosureController = new PreferenceClosureController(modelFacade, bundle);
            }
            return preferenceClosureController;
        } else {
            return null;
        }
    }

    public ControllerFactory(ModelFacade modelFacade, ResourceBundle bundle) {
        modelFacade.setFactory(this);
        this.modelFacade = modelFacade;
        this.bundle = bundle;
    }

    private ModelFacade modelFacade = null;

    private ResourceBundle bundle = null;

    private PreferenceClosureController preferenceClosureController = null;

    private PreferenceEditorsController preferenceEditorsController = null;

    private PreferencesController preferencesController = null;

    private PreferenceVariablesController preferenceVariablesController = null;

}
