package com.ggar.stepping.core;

import org.jgrapht.Graph;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractGraphExecutor<G extends Graph> {

    protected final ThreadPoolExecutor executor;

    public AbstractGraphExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public abstract <R> G execute(G graph);

    public <R> Future<G> submit(final G graph) {
        return this.executor.submit(() -> AbstractGraphExecutor.this.execute(graph));
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
