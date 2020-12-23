package com.ggar.stepping.core.executors;

import com.ggar.stepping.core.GraphExecutorStrategy;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class GraphExecutor<G extends Graph<V,E>, V, E extends DefaultEdge> extends AbstractCachedGraphExecutor<G> {

    private final GraphExecutorStrategy<G, G> strategy;

    public GraphExecutor(GraphExecutorStrategy<G, G> strategy) {
        super();
        this.strategy = strategy;
    }

    @Override
    public <R> G execute(G graph) {
        return strategy.execute(graph);
    }
}