package com.ggar.stepping.core;

import org.jgrapht.Graph;

public interface GraphExecutorStrategy<G extends Graph, R> {

    R execute(G graph);

}
