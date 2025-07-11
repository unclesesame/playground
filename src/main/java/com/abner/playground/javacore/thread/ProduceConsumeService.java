package com.abner.playground.javacore.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProduceConsumeService implements PCService{
	private Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	private boolean hasValue = false;
	
	public void produce() {
		try{
			lock.lock();
			while(hasValue == true){
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "is producing");
			hasValue = true;
			condition.signalAll();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
	
	public void consume() {
		try{
			lock.lock();
			while(hasValue == false){
				condition.await();
			}
			System.out.println(Thread.currentThread().getName() + "is Consuming");
			hasValue = false;
			condition.signalAll();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			lock.unlock();
		}
	}
	
}
