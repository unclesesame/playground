package com.abner.playground.exception;


public class ExceptionClient {
	public static void main(String[] args) {
		
		ExceptionClient obj = new ExceptionClient();
		try {
			obj.checkedExceptionFunction1();
		} catch (MyCheckedException e) {
			e.printStackTrace();
		}
		obj.checkedExceptionFunction2();
		obj.uncheckedExceptionFunction();
		//interrupted
		System.out.println("Runtime exception occured...");
		
	}
	
	//throw unchecked exception 无需声明或捕获
	void uncheckedExceptionFunction() {
		throw new MyUnCheckedException("throw my unchecked exception");
	}
	
	//throw checked exception 需要声明或捕获
	void checkedExceptionFunction1() throws MyCheckedException {
		throw new MyCheckedException("thow my checked exception with claim");
	}
	
	//throw checked exception 需要声明或捕获
	void checkedExceptionFunction2()  {
		try {
			throw new MyCheckedException("thow my checked exception with try catch");
		} catch (MyCheckedException e) {
			e.printStackTrace();
		}
	}
}
