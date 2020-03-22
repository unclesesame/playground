package com.abner.playground.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class University extends OrganizationComponent{
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponentList.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponentList.add(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("===="+getName()+"====");
        for(OrganizationComponent organizationComponent: organizationComponentList){
            organizationComponent.print();
        }
    }

}
