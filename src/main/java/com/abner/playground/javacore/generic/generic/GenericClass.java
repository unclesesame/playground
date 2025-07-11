package com.abner.playground.javacore.generic.generic;

public class GenericClass {
    public static void main(String[] args) {
        Class<? extends Number> clazz = int.class;
        clazz = double.class;
        clazz = Number.class;
        clazz = Integer.class;
        clazz = Integer.TYPE; //int.class is same with Integer.TYPE
        System.out.println(clazz);
    }
}
