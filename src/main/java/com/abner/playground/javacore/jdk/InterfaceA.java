package com.abner.playground.javacore.jdk;

public interface InterfaceA {
    default void hello(){
        System.out.println("Hello interface");
    }
}
