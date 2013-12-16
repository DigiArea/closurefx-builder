package com.digiarea.closure.model;

import com.digiarea.closure.model.visitor.VoidVisitor;
import com.digiarea.closure.model.visitor.GenericVisitor;
import com.digiarea.closure.model.visitor.CloneVisitor;
import com.digiarea.closure.model.visitor.EqualsVisitor;

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

    private static final CloneVisitor<Void> CLONE = new CloneVisitor<Void>();

    @Override
    public final Node clone() throws CloneNotSupportedException {
        try {
            return accept(CLONE, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public final boolean equals(Object obj) {
        try {
            return EqualsVisitor.equals(this, (Node) obj);
        } catch (Exception e) {
            return false;
        }
    }

}
