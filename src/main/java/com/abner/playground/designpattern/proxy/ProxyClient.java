package com.abner.playground.designpattern.proxy;

public class ProxyClient {
	public static void main(String[] args) {
		IStar michale = new Singer();
		IStar agent = new Agent(michale);
		agent.sign();
	}
}
