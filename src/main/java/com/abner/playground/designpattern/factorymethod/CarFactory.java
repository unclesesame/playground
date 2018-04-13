package com.abner.playground.designpattern.factorymethod;

public class CarFactory {
	
	//简单工厂， 不满足开闭原则
	public static Car buildCar(String type) {
		if("Benz".equals(type)) {
			return new Benz();
		}else if("BMW".equals(type)) {
			return new BMW();
		}
		return null;
	}
}
