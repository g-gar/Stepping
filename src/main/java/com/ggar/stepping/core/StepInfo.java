package com.ggar.stepping.core;

public interface StepInfo<T, R> {

    boolean hasException();
    Exception getException();
    void setException(Exception exception);

    T setInput(T t);
    T getInput();

    R setOutput(R r);
    R getOutput();
}
