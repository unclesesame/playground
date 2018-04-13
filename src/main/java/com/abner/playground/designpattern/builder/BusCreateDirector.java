package com.abner.playground.designpattern.builder;

//导演类，对builder进行封装， 负责具体的工艺流程
public class BusCreateDirector {
	private BMWBusBuilder bmwBusBuilder = new BMWBusBuilder();
	private BenzBusBuilder benzBusBuilder = new BenzBusBuilder();
	
	public IBus createBMWBus() {
		return bmwBusBuilder.buildBus();
	}
	
	public IBus createBenzBus() {
		return benzBusBuilder.buildBus();
	}
}
