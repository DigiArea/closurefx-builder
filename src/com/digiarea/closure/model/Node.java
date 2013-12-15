package com.digiarea.closure.model;

import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.CloneVisitor;

public abstract class Node {

    protected Node parent = null;

    public final Node getParent() {
        return parent;
    }

    public final void setParent(Node parent) {
        this.parent = parent;
    }

    public Node() {
        super();
    }

    public abstract <C> void accept(VoidVisitor<C> v, C ctx) throws Exception;

    public abstract <R, C> R accept(GenericVisitor<R, C> v, C ctx) throws Exception;

    @Override
    public final Node clone() throws CloneNotSupportedException {
        try {
            return (Node) accept(new CloneVisitor(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
