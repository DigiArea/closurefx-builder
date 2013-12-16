package com.digiarea.closure.model.visitor;

import java.util.List;

import com.digiarea.closure.model.Node;

public class GenericPipeline<C> implements Runnable {

    private Node node = null;

    private List<GenericVisitor<Node, C>> visitors = null;

    private C context = null;

    public GenericPipeline() {
        super();
    }

    public GenericPipeline(Node node, List<GenericVisitor<Node, C>> visitors, C context) {
        super();
        this.node = node;
        this.visitors = visitors;
        this.context = context;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public void run() {
        try {
            for (GenericVisitor<Node, C> visitor : visitors) {
                node = node.accept(visitor, context);
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
    }

}
