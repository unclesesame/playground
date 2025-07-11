package com.abner.playground.javacore.thread;
//with synchronized
public class ProduceConsumerService2 implements PCService{
	private boolean hasValue = false;
	
	public void produce() {
		synchronized (this) {
			while(hasValue == true){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "is producing");
			hasValue = true;
			this.notifyAll();
		}
	}
	
	public void consume() {
		synchronized (this) {
			while(hasValue == false){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "is consuming");
			hasValue = false;
			this.notifyAll();
		}
	}
}
