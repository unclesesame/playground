package com.abner.playground.designpattern.proxy;

import java.util.Random;

public class Agent implements IStar{
	private IStar star;
	
	public Agent(IStar star) {
		this.star = star;
	}
	
	@Override
	public void sign() {
		Random random = new Random();
		if(random.nextBoolean()){
			System.out.println("agent approve sign");
			star.sign();
		} else{
			System.out.println("agent reject sign");
		}
	}
	
}
