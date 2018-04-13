package com.abner.playground.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();
	}
}
