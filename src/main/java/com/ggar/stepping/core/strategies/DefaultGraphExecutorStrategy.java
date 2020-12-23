package com.ggar.stepping.core.strategies;

import com.ggar.stepping.core.GraphExecutorStrategy;
import com.ggar.stepping.core.Step;
import com.ggar.stepping.core.StepInfo;
import com.ggar.stepping.core.util.GraphUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Splits the given graph into multiple graphs with only one possible path between the only root node and the only leaf nodes available
 * @param <G>
 * @param <V>
 * @param <E>
 */
public class DefaultGraphExecutorStrategy<G extends Graph<V,E>, V extends Step<?, StepInfo>, E> implements GraphExecutorStrategy<G, G> {

    @Override
    public G execute(G graph) {
        List<V> leaves = GraphUtils.findLeaves(graph);
        GraphUtils.findRoots(graph).stream()
            .map(root -> leaves.stream()
                    .map(leave -> DijkstraShortestPath.<V,E>findPathBetween(graph, root, leave))
                    .collect(Collectors.toList())
            )
            .flatMap(List::stream)
            .map(path -> {
                V previous = null;
                V current = null;
                Iterator<V> it = path.getVertexList().listIterator();
                while (it.hasNext()) {
                    current = it.next();
                    if (previous != null) {
                      current.getStepInfo().setInput(previous.getStepInfo().getOutput());
                    }
                    current.execute();
                    previous = current;
                }
                return current.getStepInfo().getOutput();
            })
            .collect(Collectors.toList());
        return graph;
    }

    /*public void execute(final V previous, final V current) {
        if (Arrays.asList(previous.getClass().getAnnotations()).contains(Generator.class)) {
            Iterator<StepInfo> it = previous.getStepInfo().iterator();
            while (it.hasNext()) {
                current.getStepInfo().setInput(it.next());
                current.execute();
            }
        }
    }*/
}
