package com.abner.playground.designpattern.abstractfactory;

public class BYDCarFactory implements CarFactory{

	public ICar createSUVCar() {
		return new BYDSUVCar();
	}

	public ICar createSportCar() {
		return new BYDSportCar();
	}
	
}
