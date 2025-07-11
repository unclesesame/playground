package com.abner.playground.javacore.jdk;

public class Main {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        for(Level level: Level.values()){
            System.out.println(level.ordinal());
        }
    }


    enum Level{
        LOW,MIDDLE,HIGH
    }
}
