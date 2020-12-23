package com.ggar.stepping.core.executors;

import com.ggar.stepping.core.AbstractGraphExecutor;
import org.jgrapht.Graph;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractCachedGraphExecutor<G extends Graph> extends AbstractGraphExecutor<G> {

    protected AbstractCachedGraphExecutor() {
        super((ThreadPoolExecutor) Executors.newCachedThreadPool());
    }

}
