package com.abner.playground.designpattern.strategy;
//策略枚举
public enum Calculator {
	ADD("+") {
		public int exec(int a, int b) {
			return a + b;
		}
	},
	SUB("-") {
		public int exec(int a, int b) {
			return a - b;
		}
	};
	String value = "";
	private Calculator(String value) {
		this.value = value;
	}
	public abstract int exec(int a, int b);
}
