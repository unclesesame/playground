package com.abner.playground.designpattern.templatemethod;

public class FishCook extends AbstractCook{

	@Override
	protected void buyFood() {
		System.out.println("buy fish");
	}

	@Override
	protected void handleFood() {
		System.out.println("scratch fish");
	}
	
}
