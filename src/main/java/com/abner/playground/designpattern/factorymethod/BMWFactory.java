package com.abner.playground.designpattern.factorymethod;

public class BMWFactory implements ICarFactory{

	@Override
	public Car buildCar() {
		return new BMW();
	}
	
}
