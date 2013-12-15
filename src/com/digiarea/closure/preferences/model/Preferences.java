package com.digiarea.closure.preferences.model;

import com.digiarea.closure.preferences.model.Editors;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.digiarea.closure.preferences.model.Variables;
import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.CloneVisitor;

public class Preferences {

    private ObjectProperty<Editors> editors = new SimpleObjectProperty<Editors>(this, "editors");

    private ObjectProperty<Variables> variables = new SimpleObjectProperty<Variables>(this, "variables");

    private ObjectProperty<ClosureLibraries> closureLibraries = new SimpleObjectProperty<ClosureLibraries>(this, "closureLibraries");

    public final Editors getEditors() {
        return editors.get();
    }

    public final void setEditors(Editors editors) {
        this.editors.set(editors);
    }

    public final Variables getVariables() {
        return variables.get();
    }

    public final void setVariables(Variables variables) {
        this.variables.set(variables);
    }

    public final ClosureLibraries getClosureLibraries() {
        return closureLibraries.get();
    }

    public final void setClosureLibraries(ClosureLibraries closureLibraries) {
        this.closureLibraries.set(closureLibraries);
    }

    public Preferences() {
        super();
    }

    public Preferences(Editors editors, Variables variables, ClosureLibraries closureLibraries) {
        super();
        this.editors.set(editors);
        this.variables.set(variables);
        this.closureLibraries.set(closureLibraries);
    }

    public ObjectProperty<Editors> editorsProperty() {
        return editors;
    }

    public ObjectProperty<Variables> variablesProperty() {
        return variables;
    }

    public ObjectProperty<ClosureLibraries> closureLibrariesProperty() {
        return closureLibraries;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final Preferences clone() throws CloneNotSupportedException {
        try {
            return (Preferences) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
