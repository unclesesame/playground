package com.abner.playground.javacore.thread;


public class RunProduceConsume {
	public static void main(String[] args) throws InterruptedException {
		
		PCService service = new ProduceConsumerService2();
		ConsumeThread consumeThread1 = new ConsumeThread(service);
		consumeThread1.setName("consumeThread1");
		ProduceThread produceThread1 = new ProduceThread(service);
		produceThread1.setName("produceThread1");
		
		ConsumeThread consumeThread2 = new ConsumeThread(service);
		consumeThread2.setName("consumeThread2");
		ProduceThread produceThread2 = new ProduceThread(service);
		produceThread2.setName("produceThread2");
		
		ConsumeThread consumeThread3 = new ConsumeThread(service);
		consumeThread3.setName("consumeThread3");
		ProduceThread produceThread3 = new ProduceThread(service);
		produceThread3.setName("produceThread3");
		
		Thread.sleep(100);
		
		consumeThread1.start();
		produceThread1.start();
		consumeThread2.start();
		produceThread2.start();
		consumeThread3.start();
		produceThread3.start();
	}
	
	
}


class ConsumeThread extends Thread {
	private PCService service;
	
	public ConsumeThread(PCService service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		for(int i=0;i < 10; i++){
			service.consume();
		}
	}
	
}


class ProduceThread extends Thread {
	private PCService service;
	
	public ProduceThread(PCService service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		for(int i=0;i < 10; i++){
			service.produce();
		}
	}
	
}