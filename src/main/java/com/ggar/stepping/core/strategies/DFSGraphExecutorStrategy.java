package com.ggar.stepping.core.strategies;

import com.ggar.stepping.core.GraphExecutorStrategy;
import com.ggar.stepping.core.Step;
import com.ggar.stepping.core.StepInfo;
import com.ggar.stepping.core.util.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;
import java.util.stream.Collectors;

public class DFSGraphExecutorStrategy<G extends Graph<V, E>, V extends Step<?, StepInfo>, E extends DefaultEdge, R> implements GraphExecutorStrategy<G, G> {

    @Override
    public G execute(final G graph) {
        GraphUtils.findRoots(graph)
                .stream()
                .limit(1)
                .map(root -> {
                    R result = null;
                    V temp = null;
                    Iterator<V> iterator = new DepthFirstIterator<>(graph, root);
                    // TODO: end if iterator.next() returns another root node
                    // TODO: add support for 1..N steps
                    while (iterator.hasNext()) {
                        temp = iterator.next();
                        temp.getStepInfo().setInput(result);
                        result = (R) temp.execute();
                    }
                    return result;
                })
                .collect(Collectors.toList());
        return graph;
    }

}
