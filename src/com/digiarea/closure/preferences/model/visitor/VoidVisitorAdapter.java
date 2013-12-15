package com.digiarea.closure.preferences.model.visitor;

import com.digiarea.closure.preferences.model.visitor.VoidVisitor;
import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.EditorType;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;
import com.digiarea.closure.preferences.model.Preferences;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;

public class VoidVisitorAdapter<C> implements VoidVisitor<C> {

    @Override
    public void visit(ClosureLibraries n, C ctx) throws Exception {
        if (n.getLibraries() != null) {
            for (ClosureLibrary item : n.getLibraries()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
    }

    @Override
    public void visit(ClosureLibrary n, C ctx) throws Exception {
    }

    @Override
    public void visit(Editor n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
    }

    @Override
    public void visit(Editors n, C ctx) throws Exception {
        if (n.getEditors() != null) {
            for (Editor item : n.getEditors()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
    }

    @Override
    public void visit(EditorType n, C ctx) throws Exception {
    }

    @Override
    public void visit(OperatingSystem n, C ctx) throws Exception {
    }

    @Override
    public void visit(OperatingSystemFamily n, C ctx) throws Exception {
    }

    @Override
    public void visit(Preferences n, C ctx) throws Exception {
        if (n.getEditors() != null) {
            n.getEditors().accept(this, ctx);
        }
        if (n.getVariables() != null) {
            n.getVariables().accept(this, ctx);
        }
        if (n.getClosureLibraries() != null) {
            n.getClosureLibraries().accept(this, ctx);
        }
    }

    @Override
    public void visit(Variable n, C ctx) throws Exception {
    }

    @Override
    public void visit(Variables n, C ctx) throws Exception {
        if (n.getVariables() != null) {
            for (Variable item : n.getVariables()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
    }

    public VoidVisitorAdapter() {
        super();
    }

}
