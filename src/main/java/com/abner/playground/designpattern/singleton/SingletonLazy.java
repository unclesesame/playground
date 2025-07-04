package com.abner.playground.designpattern.singleton;

public class SingletonLazy {
	
	private static volatile SingletonLazy instance;
	
	private SingletonLazy() {
		
	}
	
	//1 使用同步方法
	synchronized public static SingletonLazy getInstance() {
		if(instance == null){
			instance = new SingletonLazy();
		}
		return instance;
	}
	
	//2 如下方式使用同步代码块， 效率更高
	public static SingletonLazy getInstance2() {
		if (instance == null) {
			synchronized(SingletonLazy.class){
				if (instance == null) {
					instance =  new SingletonLazy();
				}
			}
		}
		return instance;
	}
}
