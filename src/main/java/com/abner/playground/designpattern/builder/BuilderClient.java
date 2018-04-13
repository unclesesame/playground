package com.abner.playground.designpattern.builder;

//和工厂方法比较， 从更细粒度的装配角度去产生对象
public class BuilderClient {
	
	public static void main(String[] args) {
		BusCreateDirector director = new BusCreateDirector();
		IBus benzBus = director.createBenzBus();
		System.out.println(benzBus);
		
		IBus bmwBus = director.createBMWBus();
		System.out.println(bmwBus);
	}
	
}
