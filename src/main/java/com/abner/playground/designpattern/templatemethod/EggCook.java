package com.abner.playground.designpattern.templatemethod;

public class EggCook extends AbstractCook{

	@Override
	protected void buyFood() {
		System.out.println("buy egg");
	}

	@Override
	protected void handleFood() {
		System.out.println("broke egg");
	}

}
