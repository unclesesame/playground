package com.abner.playground.designpattern.abstractfactory;

public class AudiCarFactory implements CarFactory{

	public ICar createSUVCar() {
		return new AudiSUVCar();
	}

	public ICar createSportCar() {
		return new AudiSportCar();
	}
	
}
