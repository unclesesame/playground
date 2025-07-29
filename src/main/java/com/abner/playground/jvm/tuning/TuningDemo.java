package com.abner.playground.jvm.tuning;

public class TuningDemo {

    public static void main(String[] args) {
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
