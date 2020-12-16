package com.ggar.stepping.core.executors;

import com.ggar.stepping.core.AbstractStepGraphExecutor;
import org.jgrapht.Graph;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractCachedStepGraphExecutor<G extends Graph> extends AbstractStepGraphExecutor<G> {

    protected AbstractCachedStepGraphExecutor() {
        super((ThreadPoolExecutor) Executors.newCachedThreadPool());
    }

}
