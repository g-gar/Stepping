package com.ggar.stepping.core;

import java.util.concurrent.Callable;

public abstract class AbstractStep<T, R, I extends StepInfo<T, R>> implements Step<R, I>, Callable<R> {

    protected I info;
    protected StepState state;

    public AbstractStep() {
        this.state = StepState.CREATED;
    }

    @Override
    public R execute() {
        R result = null;

        try {
            this.state = StepState.STARTED;
            result = this.call();
            this.info.setOutput(result);
            this.state = StepState.FINISHED;
        } catch (Exception e) {
            e.printStackTrace();
            this.state = StepState.ERROR;
        }

        return result;
    }

    @Override
    public I getStepInfo() {
        return this.info;
    }

    @Override
    public StepState getState() {
        return StepState.valueOf(this.state.name());
    }
}
