package com.abner.playground.designpattern.prototype;


//原型模式常用语初始化需要消耗很多资源的场景，取代new的方式以提高性能。
public class PrototypeClient {
	public static void main(String[] args) {
		Thing thing = new Thing();
		thing.setStr("I am a new object");
		thing.setValue("I am a string in the list");
		
		Thing cloneThing = thing.clone();
		System.out.println(cloneThing.getStr());
		cloneThing.setValue("insert a new string in clone thing");
		
		System.out.println(thing.getValue());
		System.out.println(thing.getValue().hashCode());
		System.out.println(cloneThing.getValue().hashCode()); //两个list hashCode值相同则证明进行了深copy
		
		System.out.println(thing.hashCode());
		System.out.println(cloneThing.hashCode());
	}
}
