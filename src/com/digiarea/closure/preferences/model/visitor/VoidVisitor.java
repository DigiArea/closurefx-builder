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

public interface VoidVisitor<C> {

    public void visit(ClosureLibraries n, C ctx) throws Exception;

    public void visit(ClosureLibrary n, C ctx) throws Exception;

    public void visit(Editor n, C ctx) throws Exception;

    public void visit(Editors n, C ctx) throws Exception;

    public void visit(EditorType n, C ctx) throws Exception;

    public void visit(OperatingSystem n, C ctx) throws Exception;

    public void visit(OperatingSystemFamily n, C ctx) throws Exception;

    public void visit(Preferences n, C ctx) throws Exception;

    public void visit(Variable n, C ctx) throws Exception;

    public void visit(Variables n, C ctx) throws Exception;

}
