package com.ggar.stepping.core;

public abstract class AbstractStepInfo<T, R> implements StepInfo<T, R> {

    protected T input;
    protected R output;
    protected Exception exception;

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
        return this.getOutput();
    }
}
