package com.ggar.stepping.core;

public interface Step<R, I extends StepInfo> {

    R execute();
    I getStepInfo();
    StepState getState();

}
