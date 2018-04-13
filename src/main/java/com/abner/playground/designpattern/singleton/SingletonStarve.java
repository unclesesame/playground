package com.abner.playground.designpattern.singleton;

public class SingletonStarve {
	private static SingletonStarve instance = new SingletonStarve();
	
	private SingletonStarve() {
		
	}
	
	public static SingletonStarve getInstance() {
		return instance;
	}
}
