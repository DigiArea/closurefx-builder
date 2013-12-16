package com.digiarea.closure.preferences.model;

import com.digiarea.closure.preferences.model.visitor.GenericVisitor;
import com.digiarea.closure.preferences.model.visitor.VoidVisitor;

public enum OperatingSystemFamily {

    LINUX, 
    WINDOWS, 
    UNIX, 
    DEC_OS, 
    MAC;

    public <C> void accept(VoidVisitor<C> v, C ctx) throws Exception {
        v.visit(this, ctx);
    }

    public <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception {
        return v.visit(this, ctx);
    }

}
