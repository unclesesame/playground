package com.abner.playground.designpattern.strategy;

public class StrategyClient {
	public static void main(String[] args) {
		int a = 3;
		int b = 2;
		System.out.println(Calculator.ADD.exec(a, b));
		System.out.println(Calculator.SUB.exec(a, b));
	}
}
