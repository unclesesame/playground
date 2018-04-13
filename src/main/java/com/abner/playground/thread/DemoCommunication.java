package com.abner.playground.thread;

public class DemoCommunication {
	public static void main(String[] args) throws InterruptedException{
		final Business business = new Business();
		Thread thread = new Thread(new Runnable() {
				public void run() {
					for(int i=1; i <=20; i++){
						try {
							business.subThread(i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		thread.start();
		
		
		for(int i=1; i<=20; i++){
			business.mainThread(i);
		}
	}
	
}

class Business{
	
	private boolean isSubThread = true; 
	
	public synchronized void subThread(int loop) throws InterruptedException{
		
		if(!isSubThread){
			this.wait();
		}
		
		for(int i=1; i<=5; i++){
			System.out.println("sub thread sequence of " + i + ", loop of " + loop);
		}
		isSubThread = false;
		this.notify();
	}
	
	public synchronized void mainThread(int loop) throws InterruptedException{
		if(isSubThread){
			this.wait();
		}
		for(int i=1; i<=10; i++){
			System.out.println("main sequence of " + i + ", loop of " + loop);
		}
		isSubThread = true;
		this.notify();
	}
}
