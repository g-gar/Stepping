package com.ggar.stepping.core;

import java.io.Serializable;

public interface StepInfo<T, R> extends Serializable {

    boolean hasException();
    Exception getException();
    void setException(Exception exception);

    T setInput(T t);
    T getInput();

    R setOutput(R r);
    R getOutput();

    StepState setState();
    StepState setState(StepState state);

    //<S extends StepInfo<T,R>> Iterator<S> iterator();
    <S extends StepInfo<T,R>> S copy();
}
