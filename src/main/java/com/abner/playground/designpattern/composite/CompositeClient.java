package com.abner.playground.designpattern.composite;

public class CompositeClient {

    public static void main(String[] args) {
        OrganizationComponent university = new University("Tsinghua university", "清华大学");

        OrganizationComponent college1 = new College("Computer College","计算机学院");
        OrganizationComponent college2 = new College("college2","college2");

        OrganizationComponent department1 = new Department("software engineering","软件工程");
        OrganizationComponent department2 = new Department("computer science","计算机科学");
        college1.add(department1);
        college1.add(department2);

        OrganizationComponent department3 = new Department("department3","department3");
        OrganizationComponent department4 = new Department("department4","department4");
        college2.add(department3);
        college2.add(department4);

        university.add(college1);
        university.add(college2);

        university.print();
    }
}


