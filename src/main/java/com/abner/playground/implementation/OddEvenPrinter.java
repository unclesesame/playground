package com.abner.playground.implementation;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.springframework.ui.context.Theme;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenPrinter{

    private final int limit;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition printOdd = lock.newCondition();
    private final Condition printEven = lock.newCondition();
    private boolean isOddTurn = true;
    private int currentNum = 1;

    public OddEvenPrinter(int limit) {
        this.limit = limit;
    }

   private void printOddWithLock() {
        while(currentNum <= limit){
            lock.lock();
            try{
                if (isOddTurn){
                    System.out.println(Thread.currentThread().getName() + ": " + currentNum++);
                    isOddTurn = false;
                    printEven.signal();
                }else {
                    printOdd.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
   }

    private void printEvenWithLock() {
        while(currentNum <= limit){
            lock.lock();
            try{
                if (!isOddTurn){
                    System.out.println(Thread.currentThread().getName() + ": " + currentNum++);
                    isOddTurn = true;
                    printOdd.signal();
                }else {
                    printEven.await();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        OddEvenPrinter oddEvenPrinter = new OddEvenPrinter(100);

        Thread oddThead = new Thread(oddEvenPrinter::printOddWithLock, "Odd-Thread");
        Thread evenThead = new Thread(oddEvenPrinter::printEvenWithLock, "Even-Thread");
        oddThead.start();
        evenThead.start();
    }
}
