package com.abner.playground.jdk;

public interface InterfaceA {
    default void hello(){
        System.out.println("Hello interface");
    }
}
