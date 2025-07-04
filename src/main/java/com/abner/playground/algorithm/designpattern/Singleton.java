package com.abner.playground.algorithm.designpattern;

public class Singleton {

    //禁止对象引用赋值和对象初始化之间的重排序
    //线程A 执行到 instance = new Singleton(); 这一行，JVM 可能会先分配内存，然后将 instance 指向这块内存，但此时对象还未初始化完成。
    //线程B 恰好在此时也执行到 if (instance == null) 的判断，由于 instance 已经非空，线程B 就会认为单例对象已经创建，直接返回一个未完全初始化的对象。
    private static volatile Singleton instance;//防止指令重排

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
