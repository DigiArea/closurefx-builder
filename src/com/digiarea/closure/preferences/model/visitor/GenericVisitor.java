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

public interface GenericVisitor<R, C> {

    public R visit(ClosureLibraries n, C ctx) throws Exception;

    public R visit(ClosureLibrary n, C ctx) throws Exception;

    public R visit(Editor n, C ctx) throws Exception;

    public R visit(Editors n, C ctx) throws Exception;

    public R visit(EditorType n, C ctx) throws Exception;

    public R visit(OperatingSystem n, C ctx) throws Exception;

    public R visit(OperatingSystemFamily n, C ctx) throws Exception;

    public R visit(Preferences n, C ctx) throws Exception;

    public R visit(Variable n, C ctx) throws Exception;

    public R visit(Variables n, C ctx) throws Exception;

}
