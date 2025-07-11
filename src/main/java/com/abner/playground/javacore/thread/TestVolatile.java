package com.abner.playground.javacore.thread;

public class TestVolatile extends Thread{
	private volatile boolean isRunning = false;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	
	public void run(){
		System.out.println("Step into run method");
		while(isRunning == true){
			
		}
		
		System.out.println("thread stopped");
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestVolatile tv =new TestVolatile();
		tv.start();
		Thread.sleep(3000);
		tv.setRunning(false);
		System.out.println("isRunning == false");
		Thread.sleep(1000);
		
	}
}
