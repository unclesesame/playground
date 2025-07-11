package com.abner.playground.javacore.jdk;

public class ImplementA implements InterfaceA{
    @Override
    public void hello() {
        InterfaceA.super.hello();
    }
}
