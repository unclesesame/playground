package com.abner.playground.designpattern.singleton;

//确保类只有一个实例，自行实例化并向外部提供这个实例。
public class SingletonClient {
	public static void main(String[] args) {
		//饿汉式，没有线程安全问题
		System.out.println(SingletonStarve.getInstance());
		System.out.println(SingletonStarve.getInstance());
		
		
		//懒汉式， 有线程安全问题， 需要加锁
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println(SingletonLazy.getInstance() + " => "+ Thread.currentThread().getName());
				//System.out.println(SingletonLazy.getInstance2() + " => "+ Thread.currentThread().getName());
			}
		};
		Thread thread =  new Thread(runnable);
		thread.start();
		
		System.out.println(SingletonLazy.getInstance() + " => "+  Thread.currentThread().getName());
		//System.out.println(SingletonLazy.getInstance2() + " => "+  Thread.currentThread().getName());
	}
	
}
