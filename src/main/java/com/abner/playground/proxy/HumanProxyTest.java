package com.abner.playground.proxy;


public class HumanProxyTest {
	public static void main(String[] args) {
		Human human = new HumanImpl();
		HumanProxy proxy = new HumanProxy();
		Human human2 = (Human) proxy.bind(human);
		human2.speak();
		
	}
}
