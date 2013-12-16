package com.digiarea.closure.model.visitor;

import com.digiarea.closure.model.Node;
import com.digiarea.closure.model.visitor.VoidVisitor;
import java.util.List;

public class VoidPipeline<C> implements Runnable {

    private Node node = null;

    private List<VoidVisitor<C>> visitors = null;

    private C context = null;

    public VoidPipeline() {
        super();
    }

    public VoidPipeline(Node node, List<VoidVisitor<C>> visitors, C context) {
        super();
        this.node = node;
        this.visitors = visitors;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            for (VoidVisitor<C> visitor : visitors) {
                node.accept(visitor, context);
            }
        } catch (final Exception e) {
            throw new Error(e.getMessage());
        }
    }

}
