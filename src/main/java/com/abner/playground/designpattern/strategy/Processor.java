package com.abner.playground.designpattern.strategy;

public interface Processor {

    default String name() {
        return getClass().getSimpleName();
    }

    Object process(Object input);
}
