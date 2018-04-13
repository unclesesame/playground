package com.abner.playground.designpattern.proxy;

public class ProxyClient {
	public static void main(String[] args) {
		IStar machale = new Singer();
		IStar agent = new Agent(machale);
		agent.sign();
	}
}
