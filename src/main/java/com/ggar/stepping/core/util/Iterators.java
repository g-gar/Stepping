package com.ggar.stepping.core.util;

import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;
import java.util.function.Function;

public enum Iterators {

    LEAVES(graph -> GraphUtils.findLeaves(graph).iterator()),
    ROOTS(graph -> GraphUtils.findRoots(graph).iterator()),
    ALL_VERTICES(graph -> graph.vertexSet().iterator()),
    ALL_VERTICES_DFS(graph -> new DepthFirstIterator(graph, graph.vertexSet().stream().findFirst().get())),
    ALL_VERTICES_BFS(graph -> new BreadthFirstIterator(graph, graph.vertexSet().stream().findFirst().get()))
    ;

    protected final Function<Graph, Iterator> fn;

    private Iterators(Function<Graph, Iterator> fn) {
        this.fn = fn;
    }

    public <G extends Graph<N, E>, N, E> Iterator<N> apply(G graph) {
        return this.fn.apply(graph);
    }
}
