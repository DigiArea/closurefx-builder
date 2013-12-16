package com.digiarea.closure.preferences.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.digiarea.closure.preferences.model.visitor.CloneVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;

public class Variable {

    private StringProperty name = new SimpleStringProperty(this, "name");

    private StringProperty path = new SimpleStringProperty(this, "path");

    public String getPlaceholder() {
        return "{" + name.getValue() + "}";
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public final String getPath() {
        return path.get();
    }

    public final void setPath(String path) {
        this.path.set(path);
    }

    public Variable() {
        super();
    }

    public Variable(String name, String path) {
        super();
        this.name.set(name);
        this.path.set(path);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty pathProperty() {
        return path;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final Variable clone() throws CloneNotSupportedException {
        try {
            return (Variable) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
