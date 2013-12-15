package com.digiarea.closure.preferences.model;

import com.digiarea.closure.preferences.model.Variable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import java.util.List;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.CloneVisitor;

public class Variables {

    private ListProperty<Variable> variables = new SimpleListProperty<Variable>(this, "variables", FXCollections.observableList(new ArrayList<Variable>()));

    public boolean addVariables(Variable variables) {
        boolean res = getVariables().add(variables);
        if (res) {
        }
        return res;
    }

    public boolean removeVariables(Variable variables) {
        boolean res = getVariables().remove(variables);
        if (res) {
        }
        return res;
    }

    public final List<Variable> getVariables() {
        return variables.get();
    }

    public final void setVariables(List<Variable> variables) {
        this.variables.addAll(variables);
    }

    public Variables() {
        super();
    }

    public Variables(List<Variable> variables) {
        super();
        this.variables.addAll(variables);
    }

    public ListProperty<Variable> variablesProperty() {
        return variables;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final Variables clone() throws CloneNotSupportedException {
        try {
            return (Variables) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
