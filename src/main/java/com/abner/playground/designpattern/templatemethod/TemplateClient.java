package com.abner.playground.designpattern.templatemethod;

public class TemplateClient {
	public static void main(String[] args) {
		
		ICook fish = new FishCook();
		fish.cook();
		
		ICook egg = new EggCook();
		egg.cook();
	}
	
}
