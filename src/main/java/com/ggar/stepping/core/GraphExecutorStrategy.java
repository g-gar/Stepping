package com.ggar.stepping.core;

import org.jgrapht.Graph;

import java.util.List;

public interface GraphExecutorStrategy<G extends Graph> {

    <R> List<R> execute(G graph);

}
