package com.abner.playground.designpattern.prototype;

import java.util.ArrayList;

//原型模式 实现Cloneable接口并重写clone方法  
//clone只拷贝本对象，其内部的数组和引用对象不copy， 原始类型如int、long、char以及String等都会copy
public class Thing implements Cloneable{
	
	private ArrayList<String> list = new ArrayList<String>();//注意不要加final关键字
	private String str; 
	
	@SuppressWarnings("unchecked")
	@Override
	public Thing clone() {
		Thing thing = null;
		try {
			thing = (Thing)super.clone();
			thing.list = (ArrayList<String>)this.list.clone(); //深copy 对引用类型特别处理  
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return thing;
	}

	public ArrayList<String> getValue() {
		return list;
	}

	public void setValue(String value) {
		this.list.add(value);
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
}
