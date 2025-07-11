package com.abner.playground.javacore.jvm;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        ClassLoader extensionClassLoader = appClassLoader.getParent();
        System.out.println(extensionClassLoader);//sun.misc.Launcher$ExtClassLoader@7a81197d

        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null, cannot get bootstrapClassLoader

        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        System.out.println(classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2,it's appClassLoader

        ClassLoader classLoaderForCore = String.class.getClassLoader();
        System.out.println(classLoaderForCore);//null, bootstrapClassLoader for Java Core class.



    }
}
