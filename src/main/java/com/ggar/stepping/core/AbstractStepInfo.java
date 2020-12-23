package com.ggar.stepping.core;

import org.apache.commons.lang3.SerializationUtils;

import java.util.logging.Logger;

public abstract class AbstractStepInfo<T, R> implements StepInfo<T, R> {

    protected T input;
    protected R output;
    protected Exception exception;
    protected StepState state;

    @Override
    public boolean hasException() {
        return exception != null;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public T setInput(T input) {
        this.input = input;
        return this.input;
    }

    @Override
    public T getInput() {
        return this.input;
    }

    @Override
    public R setOutput(R output) {
        this.output = output;
        return this.output;
    }

    @Override
    public R getOutput() {
        return this.output;
    }

    @Override
    public StepState setState() {
        return StepState.valueOf(this.state.name());
    }

    @Override
    public StepState setState(StepState state) {
        try {
            this.state = StepState.valueOf(state.name());
        } catch (Exception e) {
            Logger.getGlobal().info(String.format("State [%s] not available\n", state));
        }
        return StepState.valueOf(this.state.name());
    }

    @Override
    public <S extends StepInfo<T, R>> S copy() {
        return (S) SerializationUtils.clone(this);
    }
}
