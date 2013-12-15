package com.digiarea.closure.preferences.model.bind;

import com.digiarea.closure.preferences.model.visitor.VoidVisitorAdapter;
import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.controller.PreferenceClosureController;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.controller.PreferenceEditorsController;
import com.digiarea.closure.preferences.model.Preferences;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;
import com.digiarea.closure.preferences.model.controller.PreferenceVariablesController;
import javafx.util.Callback;

public class Binder extends VoidVisitorAdapter<Object> {

    @Override
    public void visit(ClosureLibraries n, Object ctx) throws Exception {
        super.visit(n, ctx);
        PreferenceClosureController preferenceClosureController = (PreferenceClosureController) factory.call(PreferenceClosureController.class);
        preferenceClosureController.getControlClosure().itemsProperty().set(n.librariesProperty().get());
        n.librariesProperty().bindBidirectional(preferenceClosureController.getControlClosure().itemsProperty());
    }

    @Override
    public void visit(ClosureLibrary n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Editor n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Editors n, Object ctx) throws Exception {
        super.visit(n, ctx);
        PreferenceEditorsController preferenceEditorsController = (PreferenceEditorsController) factory.call(PreferenceEditorsController.class);
        preferenceEditorsController.getControlEditors().itemsProperty().set(n.editorsProperty().get());
        n.editorsProperty().bindBidirectional(preferenceEditorsController.getControlEditors().itemsProperty());
    }

    @Override
    public void visit(Preferences n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Variable n, Object ctx) throws Exception {
        super.visit(n, ctx);
    }

    @Override
    public void visit(Variables n, Object ctx) throws Exception {
        super.visit(n, ctx);
        PreferenceVariablesController preferenceVariablesController = (PreferenceVariablesController) factory.call(PreferenceVariablesController.class);
        preferenceVariablesController.getControlVariables().itemsProperty().set(n.variablesProperty().get());
        n.variablesProperty().bindBidirectional(preferenceVariablesController.getControlVariables().itemsProperty());
    }

    private Callback<Class<?>, Object> factory = null;

    public Binder(Callback<Class<?>, Object> factory) {
        this.factory = factory;
    }

    public Binder() {
        super();
    }

}
