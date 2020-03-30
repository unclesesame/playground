package com.abner.playground.jdk;

public class ImplementA implements InterfaceA{
    @Override
    public void hello() {
        InterfaceA.super.hello();
    }
}
