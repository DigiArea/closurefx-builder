package com.digiarea.closure.preferences.model.visitor;

import com.digiarea.closure.preferences.model.ClosureLibraries;
import com.digiarea.closure.preferences.model.ClosureLibrary;
import com.digiarea.closure.preferences.model.Editor;
import com.digiarea.closure.preferences.model.EditorType;
import com.digiarea.closure.preferences.model.Editors;
import com.digiarea.closure.preferences.model.OperatingSystem;
import com.digiarea.closure.preferences.model.OperatingSystemFamily;
import com.digiarea.closure.preferences.model.Preferences;
import com.digiarea.closure.preferences.model.Variable;
import com.digiarea.closure.preferences.model.Variables;

public class GenericVisitorAdapter<R, C> implements GenericVisitor<R, C> {

    @Override
    public R visit(ClosureLibraries n, C ctx) throws Exception {
        if (n.getLibraries() != null) {
            for (ClosureLibrary item : n.getLibraries()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(ClosureLibrary n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Editor n, C ctx) throws Exception {
        if (n.getType() != null) {
            n.getType().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Editors n, C ctx) throws Exception {
        if (n.getEditors() != null) {
            for (Editor item : n.getEditors()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    @Override
    public R visit(EditorType n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(OperatingSystem n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(OperatingSystemFamily n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Preferences n, C ctx) throws Exception {
        if (n.getEditors() != null) {
            n.getEditors().accept(this, ctx);
        }
        if (n.getVariables() != null) {
            n.getVariables().accept(this, ctx);
        }
        if (n.getClosureLibraries() != null) {
            n.getClosureLibraries().accept(this, ctx);
        }
        return null;
    }

    @Override
    public R visit(Variable n, C ctx) throws Exception {
        return null;
    }

    @Override
    public R visit(Variables n, C ctx) throws Exception {
        if (n.getVariables() != null) {
            for (Variable item : n.getVariables()) {
                if (item != null) {
                    item.accept(this, ctx);
                }
            }
        }
        return null;
    }

    public GenericVisitorAdapter() {
        super();
    }

}
