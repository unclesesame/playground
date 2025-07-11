package com.abner.playground.implementation;

//面试题，实现一个死锁的代码
public class DeadLockSample {
    public static void main(String[] args){
        final Object lock1 =  new Object();
        final Object lock2 =  new Object();

        Thread thread1 = new Thread(()-> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){}
            }
        });

        Thread thread2 = new Thread(()-> {
            synchronized (lock2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){}
            }
        });

        thread1.start();
        thread2.start();

    }
}
