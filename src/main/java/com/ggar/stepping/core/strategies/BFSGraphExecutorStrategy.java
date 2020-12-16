package com.ggar.stepping.core.strategies;

import com.ggar.stepping.core.GraphExecutorStrategy;
import com.ggar.stepping.core.Step;
import com.ggar.stepping.core.StepInfo;
import com.ggar.stepping.core.util.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BFSGraphExecutorStrategy<G extends Graph<V, E>, V extends Step<?, StepInfo>, E, R> implements GraphExecutorStrategy<G> {
    @Override
    public <R> List<R> execute(final G graph) {
        return GraphUtils.findRoots(graph).stream().map(root -> {
            R result = null;
            V temp = null;
            Iterator<V> iterator = new DepthFirstIterator<>(graph, root);
            while (iterator.hasNext()) {
                temp = iterator.next();
                temp.getStepInfo().setInput(result);
                result = (R) temp.execute();
            }
            return result;
        }).collect(Collectors.toList());
    }
}
