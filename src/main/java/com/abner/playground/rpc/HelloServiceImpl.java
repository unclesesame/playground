package com.abner.playground.rpc;

public class HelloServiceImpl implements HelloService{

	@Override
	public String sayHi(String name) {
		return "Hi " + name ;
	}

}
