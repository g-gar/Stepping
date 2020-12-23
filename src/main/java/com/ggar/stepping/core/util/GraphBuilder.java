package com.ggar.stepping.core.util;

import org.jgrapht.Graph;

import java.util.Iterator;
import java.util.function.Consumer;

public class GraphBuilder<G extends Graph<N, E>, N, E> {

    private final G graph;
    private Iterator<N> iterator;

    public GraphBuilder(final G graph) {
        this.graph = graph;
    }

    public GraphBuilder<G, N, E> addRootNodes(final N... nodes) {
        for (N node : nodes) {
            GraphBuilder.this.addNode(node, true);
        }
        return this;
    }

    public GraphBuilder<G, N, E> addNodes(final N... nodes) {
        for (N node : nodes) {
            GraphBuilder.this.addNode(node);
        }
        return this;
    }

    public GraphBuilder<G, N, E> addNode(final N node) {
        GraphBuilder.this.addNode(node, false);
        return this;
    }

    public GraphBuilder<G, N, E> addNode(final N node, boolean isRoot) {
        if (GraphUtils.findRoots(this.graph).isEmpty() || isRoot) {
            this.graph.addVertex(node);
        } else {
            GraphBuilder.this.traverse(Iterators.LEAVES.apply(this.graph), leave -> GraphBuilder.this.addNode(leave, node));
        }
        return this;
    }

    public GraphBuilder<G, N, E> addNode(final N parent, final N child) {
        if (this.graph.containsVertex(parent)) {
            if (!this.graph.containsVertex(child)) {
                graph.addVertex(child);
            }
            this.graph.addEdge(parent, child);
        }
        return this;
    }

    public GraphBuilder<G, N, E> traverse(final Iterator<N> iterator, Consumer<N> consumer) {
        while (iterator.hasNext()) {
            consumer.accept(iterator.next());
        }
        return this;
    }

    public GraphBuilder<G, N, E> traverse(Consumer<N> consumer) {
        while (this.iterator.hasNext()) {
            consumer.accept(this.iterator.next());
        }
        return this;
    }

    public GraphBuilder<G, N, E> peek(Consumer<N> consumer) {
        GraphBuilder.this.traverse(Iterators.ALL_VERTICES.apply(this.graph), consumer);
        return this;
    }

    public GraphBuilder<G,N,E> setIterator(Iterator<N> iterator) {
        this.iterator = iterator;
        return this;
    }

    public G get() {
        return this.graph;
    }
}
