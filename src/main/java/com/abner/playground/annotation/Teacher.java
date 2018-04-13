package com.abner.playground.annotation;

public class Teacher extends Man{
	@Column(index = 0)
	private String name;
	
	@Column(index = 1)
	private Integer age;
	
	@Column(index = 2, header = "class")
	private String course;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	
}
