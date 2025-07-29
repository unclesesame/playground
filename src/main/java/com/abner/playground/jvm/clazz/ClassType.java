package com.abner.playground.jvm.clazz;

import java.lang.annotation.ElementType;

//哪些类型可以由Class对象
public class ClassType {
    public static void main(String[] args) {

        //一般类
        Class<Object> objectClass = Object.class;

        //接口
        Class<Comparable> comparableClass = Comparable.class;

        //数组
        Class<String[]> aClass = String[].class;
        Class<int[][]> aClass1 = int[][].class;

        //枚举
        Class<ElementType> elementTypeClass = ElementType.class;

        //注解
        Class<Override> overrideClass = Override.class;

        //基本类型
        Class<Integer> integerClass = int.class;

        //Void
        Class<Void> voidClass = void.class;

        Class<Class> classClass = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> ca = a.getClass();
        Class<? extends int[]> cb = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(ca == cb);

    }
}
