package com.ggar.stepping.core.executors;

import com.ggar.stepping.core.GraphExecutorStrategy;
import org.jgrapht.Graph;

import java.util.List;

public class StepGraphExecutor<G extends Graph, R> extends AbstractCachedStepGraphExecutor<G> {

    private final GraphExecutorStrategy<G> strategy;

    public StepGraphExecutor(GraphExecutorStrategy<G> strategy) {
        super();
        this.strategy = strategy;
    }

    @Override
    public <R> List<R> execute(G graph) {
        return strategy.execute(graph);
    }

}