package com.abner.playground.test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

   private final LinkedList<T> queue = new LinkedList<>();
   private final int capacity;
   private final ReentrantLock lock = new ReentrantLock();
   private final Condition notEmpty = lock.newCondition();
   private final Condition notFull = lock.newCondition();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }


    public void put(T element) throws InterruptedException {
        lock.lock();
        try{
            while (queue.size() == capacity ){
                notFull.await(); //Producer thread blocked and wait
            }
            queue.add(element);
            notEmpty.signal(); //wake up pending consumer
        }finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try{
            while (queue.isEmpty()){
                notEmpty.await();// Consumer thread blocked and wait
            }
            T element = queue.poll();
            notFull.signal(); //wake up pending consumer
            return element;
        }finally {
            lock.unlock();
        }
    }



    public boolean isEmpty(){
        lock.lock();
        try{
            return queue.isEmpty();
        }finally {
            lock.unlock();
        }
    }

    public boolean isFull(){
        lock.lock();
        try{
            return queue.size() == capacity;
        }finally {
            lock.unlock();
        }
    }

}
