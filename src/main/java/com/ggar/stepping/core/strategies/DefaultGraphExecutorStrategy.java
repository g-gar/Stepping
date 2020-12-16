package com.ggar.stepping.core.strategies;

import com.ggar.stepping.core.GraphExecutorStrategy;
import com.ggar.stepping.core.util.GraphUtils;
import org.jgrapht.Graph;

import java.util.List;
import java.util.Set;


// TODO
public class DefaultGraphExecutorStrategy<G extends Graph<V,E>, V, E> implements GraphExecutorStrategy<G> {
    @Override
    public <R> List<R> execute(G graph) {
        List<V> leaves = GraphUtils.findLeaves(graph);
        G temp;
        for (V leaf : leaves) {
            Set<E> incoming = GraphUtils.getIncomingEdges(graph, leaf);
            Set<E> outgoing = GraphUtils.getOutgoingEdges(graph, leaf);
            temp = GraphUtils.removeNode(graph, leaf);
        }
        return null;
    }


}
