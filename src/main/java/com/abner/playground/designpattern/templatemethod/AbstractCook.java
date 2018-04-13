package com.abner.playground.designpattern.templatemethod;

public abstract class AbstractCook implements ICook{
	
	//1.模板方法 （对基本方法调用）
	public void cook(){
		buyFood();
		handleFood();
	}
	
	//2.基本方法
	abstract protected void buyFood();
	abstract protected void handleFood();
}
