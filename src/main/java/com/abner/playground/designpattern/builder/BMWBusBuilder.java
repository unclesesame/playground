package com.abner.playground.designpattern.builder;

public class BMWBusBuilder extends BusBuilder{

	public String buildWheel() {
		return "BMW bus wheel";
	}

	public String buildEngine() {
		return "BMW bus engine";
	}
	
}
