package com.abner.playground.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {
	
	
	public static void main(String[] args) {
		//testExecutorService();
		testCompletionService();
	}
	
	private static void testExecutorService() {
		int threadNum = 5;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		List<Future<String>> futures = new ArrayList<Future<String>>();
		
		for(int i=0; i<5;i++){
			Future<String> future = executor.submit(new Task(i));
			futures.add(future);
		}
		
		while(threadNum > 0) {
			for(Future<String> future : futures) {
				String result = null;
				try {
					result = future.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				if(null != result) {
					futures.remove(future);
					threadNum --;
					System.out.println(result);
					break;//此处必须break，否则会抛出并发修改异常。（也可以通过将futureList声明为CopyOnWriteArrayList类型解决）
				}
			}
		}
		
	}
	
	//CompletionService的实现是维护一个保存Future对象的BlockingQueue
	private static void testCompletionService() {
		int threadNum = 5;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for(int i = 0;i<threadNum;i++ ){
			completionService.submit(new Task(i));
			
		}
		
		for(int i = 0;i<threadNum;i++ ){
			try {
					System.out.println(completionService.take().get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
		 }
		
	}
}

class Task implements Callable<String>{
	private int i;
	public Task(int i) {
		this.i = i;
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return Thread.currentThread().getName() + " finished task " + i;
	}
}
