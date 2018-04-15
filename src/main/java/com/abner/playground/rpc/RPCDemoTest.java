package com.abner.playground.rpc;

import java.net.InetSocketAddress;

public class RPCDemoTest {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					Server serviceCenter = new ServiceCenter(8088);
					serviceCenter.register(HelloService.class, HelloServiceImpl.class);
					serviceCenter.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
		System.out.println(service.sayHi("Abner"));
		System.out.println(service.sayHi("Laura"));
	}
}
