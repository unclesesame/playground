package com.abner.playground.designpattern.builder;

public class BenzBusBuilder extends BusBuilder{

	public String buildWheel() {
		return "Benz bus wheel";
	}

	public String buildEngine() {
		return "Benz bus engine";
	}
	
}
