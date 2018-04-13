package com.abner.playground.designpattern.builder;

public class Bus implements IBus{

	private String wheel;
	private String engine;
	
	public Bus(String wheel, String engine){
		this.wheel = wheel;
		this.engine = engine;
	}
	
	@Override
	public String getWheel() {
		return wheel;
	}

	@Override
	public String getEngine() {
		return engine;
	}

	@Override
	public String toString() {
		return "Bus [wheel=" + wheel + ", engine=" + engine + "]";
	}
}
