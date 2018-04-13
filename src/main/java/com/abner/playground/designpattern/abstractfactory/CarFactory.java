package com.abner.playground.designpattern.abstractfactory;

public interface CarFactory {
	ICar createSUVCar();
	ICar createSportCar();
}
