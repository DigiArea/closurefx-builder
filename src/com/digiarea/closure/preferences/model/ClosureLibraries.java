package com.digiarea.closure.preferences.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import com.digiarea.closure.preferences.model.visitor.CloneVisitor;
import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;

public class ClosureLibraries {

    private ListProperty<ClosureLibrary> libraries = new SimpleListProperty<ClosureLibrary>(this, "libraries", FXCollections.observableList(new ArrayList<ClosureLibrary>()));

    public boolean addLibraries(ClosureLibrary libraries) {
        boolean res = getLibraries().add(libraries);
        if (res) {
        }
        return res;
    }

    public boolean removeLibraries(ClosureLibrary libraries) {
        boolean res = getLibraries().remove(libraries);
        if (res) {
        }
        return res;
    }

    public final List<ClosureLibrary> getLibraries() {
        return libraries.get();
    }

    public final void setLibraries(List<ClosureLibrary> libraries) {
        this.libraries.addAll(libraries);
    }

    public ClosureLibraries() {
        super();
    }

    public ClosureLibraries(List<ClosureLibrary> libraries) {
        super();
        this.libraries.addAll(libraries);
    }

    public ListProperty<ClosureLibrary> librariesProperty() {
        return libraries;
    }

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

    @Override
    public final ClosureLibraries clone() throws CloneNotSupportedException {
        try {
            return (ClosureLibraries) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
