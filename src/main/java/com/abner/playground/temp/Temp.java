package com.abner.playground.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class People{
   String name;
   Integer age;

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
}


public class Temp {

    public static void main(String[] args) {

        People people1 = new People("Abner", 18);
        People people2 = new People("Tom", 20);
        People people3 = new People("Jimmy", 22);
        People people4 = new People("Harry", 26);


        Map<String, People> peopleMap = new HashMap<String, People>();
        peopleMap.put(people1.getName(), people1);
        peopleMap.put(people2.getName(), people2);
        peopleMap.put(people3.getName(), people3);
        peopleMap.put(people4.getName(), people4);

        Temp temp = new Temp();
        peopleMap.forEach((key, value) ->{
            Integer age = temp.findAgeByName(peopleMap, key);
            System.out.println(age);
        });
    }


    private Integer findAgeByName(Map<String, People> peopleMap, String name){
        return peopleMap.get(name).getAge();
    }


}
