package com.ggar.stepping.core;

import org.jgrapht.Graph;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractStepGraphExecutor<G extends Graph>{

    protected final ThreadPoolExecutor executor;

    public AbstractStepGraphExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public abstract <R> List<R> execute(G graph);

    public <R> Future<List<R>> submit(final G graph) {
        return this.executor.submit(() -> AbstractStepGraphExecutor.this.execute(graph));
    }

    synchronized public void stop() {
        try {
            this.executor.shutdown();
            this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
