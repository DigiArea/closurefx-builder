package com.digiarea.closure.preferences.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import com.digiarea.closure.preferences.model.visitor.CloneVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;

public class Editors {

    private ListProperty<Editor> editors = new SimpleListProperty<Editor>(this, "editors", FXCollections.observableList(new ArrayList<Editor>()));

    public boolean addEditors(Editor editors) {
        boolean res = getEditors().add(editors);
        if (res) {
        }
        return res;
    }

    public boolean removeEditors(Editor editors) {
        boolean res = getEditors().remove(editors);
        if (res) {
        }
        return res;
    }

    public final List<Editor> getEditors() {
        return editors.get();
    }

    public final void setEditors(List<Editor> editors) {
        this.editors.addAll(editors);
    }

    public Editors() {
        super();
    }

    public Editors(List<Editor> editors) {
        super();
        this.editors.addAll(editors);
    }

    public ListProperty<Editor> editorsProperty() {
        return editors;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final Editors clone() throws CloneNotSupportedException {
        try {
            return (Editors) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
