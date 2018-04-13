package com.abner.playground.designpattern.abstractfactory;

public class AbstractFactoryClient {
	public static void main(String[] args) {
		CarFactory audiFactory = new AudiCarFactory();
		ICar audiSportCar = audiFactory.createSportCar();
		audiSportCar.run();
	}
}
