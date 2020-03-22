package com.abner.playground.designpattern.composite;

public class Department extends OrganizationComponent {

    public Department(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void print() {
        System.out.println("===="+getName()+"====");
    }
}
