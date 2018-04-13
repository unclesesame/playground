package com.abner.playground.designpattern.factorymethod;

//工厂方法模式：
//定义一个用于创建对象的接口，由子类来决定实例化哪个类。使类的实例化延迟到子类
public class FactoryMethodClient {
	public static void main(String[] args) {
		
		//简单工厂
		Car benz = CarFactory.buildCar("Benz");
		benz.run();
		Car bmw = CarFactory.buildCar("BMW");
		bmw.run();
		
		//工厂方法
		ICarFactory factory = new BenzFactory();
		Car benzCar = factory.buildCar();
		benzCar.run();
		
		//抽象工厂 支持增加产品族， 不支持增加产品
	}
}
