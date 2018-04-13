package com.abner.playground.thread;

public class Demo01 implements Runnable{
	
	public static void main(String[] args) {
		
		Demo01 demo01 = new Demo01();
		Thread thread = new Thread(demo01,"thread01");
		thread.start();
		
		for(int i=0; i<500;i++){
			System.out.println("main running : "+i);
		}
		
	}

	public void run() {
		for(int i=0; i<1000;i++){
			System.out.println("thread is running..." + i);
		}
	}
}
