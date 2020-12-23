package com.ggar.stepping.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class AbstractStep<T, R, I extends StepInfo<T, R>> implements Step<R, I>, Callable<R> {

    protected final List<I> history;
    protected final I info;

    public AbstractStep() {
        this((I) new AbstractStepInfo<T, R>() {});
    }

    public AbstractStep(I info) {
        this.info = info;
        this.info.setState(StepState.CREATED);
        this.history = new ArrayList<>();
    }

    @Override
    public R execute() {
        R result = null;

        try {
            this.info.setState(StepState.STARTED);
            result = this.call();
            this.info.setOutput(result);
            this.info.setState(StepState.FINISHED);
        } catch (Exception e) {
            this.info.setException(e);
            this.info.setState(StepState.ERROR);
        } finally {
            /*this.history.add(this.info.copy());
            this.info.setState(StepState.CREATED);
            this.info.setInput(null);
            this.info.setOutput(null);*/
        }

        return result;
    }

    @Override
    public I getStepInfo() {
        return this.info;
    }
}
