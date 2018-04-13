package com.abner.playground.designpattern.proxy;

public class Singer implements IStar{

	@Override
	public void sign() {
		System.out.println("Singer sign --> 'Michale Jaskson'");
	}

}
