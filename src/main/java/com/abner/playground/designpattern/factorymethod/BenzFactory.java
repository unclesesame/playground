package com.abner.playground.designpattern.factorymethod;

public class BenzFactory implements ICarFactory{

	@Override
	public Car buildCar() {
		return new Benz();
	}

}
