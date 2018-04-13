package com.abner.playground.designpattern.builder;

//建造者模式常搭配模板方法模式使用
public abstract class BusBuilder {
	
	public Bus buildBus() {
		return new Bus(buildWheel(), buildEngine());
	}
	
	protected abstract String buildWheel();
	protected abstract String buildEngine();
}
