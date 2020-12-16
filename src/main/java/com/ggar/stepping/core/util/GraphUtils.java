package com.ggar.stepping.core.util;

import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphUtils {

    public static <E, G extends Graph<E, ?>> List<E> findRoots(G graph) {
        return graph.vertexSet().stream().filter(e -> graph.incomingEdgesOf(e).size() == 0).collect(Collectors.toList());
    }

    public static <E, G extends Graph<E, ?>> List<E> findLeaves(G graph) {
        return graph.vertexSet().stream().filter(e -> graph.outgoingEdgesOf(e).size() == 0).collect(Collectors.toList());
    }

    public static <E, G extends Graph<E, ?>> boolean isRoot(G graph, E node) {
        return graph.incomingEdgesOf(node).size() == 0;
    }

    public static <E, G extends Graph<E, ?>> boolean isLeave(G graph, E node) {
        return graph.outgoingEdgesOf(node).size() == 0;
    }

    public static <E, V, G extends Graph<V, E>> G removeNode(G graph, V node) {
        G temp = (G) ((AbstractBaseGraph<V, E>)graph).clone();
        GraphUtils.getIncomingEdges(temp, node).forEach(e -> temp.removeEdge(e));
        GraphUtils.getOutgoingEdges(temp, node).forEach(e -> temp.removeEdge(e));
        temp.removeVertex(node);
        return temp;
    }

    public static <V, E, G extends Graph<V, E>> Set<E> getIncomingEdges(G graph, V node) {
        return graph.incomingEdgesOf(node);
    }

    public static <V, E, G extends Graph<V, E>> Set<E> getOutgoingEdges(G graph, V node) {
        return graph.outgoingEdgesOf(node);
    }
}
