package com.digiarea.closure.preferences.model.visitor;

import java.util.ArrayList;
import java.util.List;

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

public class CloneVisitor implements GenericVisitor<Object, Object> {

    @Override
    public Object visit(ClosureLibraries n, Object ctx) throws Exception {
        ClosureLibraries img = new ClosureLibraries();
        if (n.getLibraries() != null) {
            List<ClosureLibrary> libraries = new ArrayList<ClosureLibrary>();
            for (ClosureLibrary item : n.getLibraries()) {
                if (item != null) {
                    libraries.add((ClosureLibrary) item.accept(this, ctx));
                }
            }
            img.setLibraries(libraries);
        }
        return img;
    }

    @Override
    public Object visit(ClosureLibrary n, Object ctx) throws Exception {
        ClosureLibrary img = new ClosureLibrary();
        img.setName(n.getName());
        img.setPath(n.getPath());
        return img;
    }

    @Override
    public Object visit(Editor n, Object ctx) throws Exception {
        Editor img = new Editor();
        img.setName(n.getName());
        img.setPath(n.getPath());
        img.setFile(n.getFile());
        img.setLine(n.getLine());
        img.setColumn(n.getColumn());
        img.setDefault(n.isDefault());
        img.setType(n.getType());
        return img;
    }

    @Override
    public Object visit(Editors n, Object ctx) throws Exception {
        Editors img = new Editors();
        if (n.getEditors() != null) {
            List<Editor> editors = new ArrayList<Editor>();
            for (Editor item : n.getEditors()) {
                if (item != null) {
                    editors.add((Editor) item.accept(this, ctx));
                }
            }
            img.setEditors(editors);
        }
        return img;
    }

    @Override
    public Object visit(EditorType n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(OperatingSystem n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(OperatingSystemFamily n, Object ctx) throws Exception {
        return null;
    }

    @Override
    public Object visit(Preferences n, Object ctx) throws Exception {
        Preferences img = new Preferences();
        if (n.getEditors() != null) {
            img.setEditors((Editors) n.getEditors().accept(this, ctx));
        }
        if (n.getVariables() != null) {
            img.setVariables((Variables) n.getVariables().accept(this, ctx));
        }
        if (n.getClosureLibraries() != null) {
            img.setClosureLibraries((ClosureLibraries) n.getClosureLibraries().accept(this, ctx));
        }
        return img;
    }

    @Override
    public Object visit(Variable n, Object ctx) throws Exception {
        Variable img = new Variable();
        img.setName(n.getName());
        img.setPath(n.getPath());
        return img;
    }

    @Override
    public Object visit(Variables n, Object ctx) throws Exception {
        Variables img = new Variables();
        if (n.getVariables() != null) {
            List<Variable> variables = new ArrayList<Variable>();
            for (Variable item : n.getVariables()) {
                if (item != null) {
                    variables.add((Variable) item.accept(this, ctx));
                }
            }
            img.setVariables(variables);
        }
        return img;
    }

    public CloneVisitor() {
        super();
    }

}
