package com.abner.playground.reflect;

public class ReflectTest {
	public static void main(String[] args) throws ClassNotFoundException {
		Class klass1 =  new Person().getClass();
		Class klass2 = Class.forName("com.abner.playground.reflect.Person");
		Class klass3 = Person.class;
		
		System.out.println(klass3.getName());
		System.out.println(klass3.getDeclaredFields()[0]);
	}
}


class Person{
	private String name;
}