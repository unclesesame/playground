package com.abner.playground.openapi.validation;

import java.util.Map;

public class UserTest {

    public static void main(String[] args) {

        User user = new User();
        //user.setName("");
        user.setAge(101L);
       print(ValidationUtil.validate(user));
    }

    private static void print(Map<String,StringBuffer> errorMap) {
        if (errorMap != null) {
            for (Map.Entry<String, StringBuffer> m : errorMap.entrySet()) {
                System.out.println(m.getKey() + ":" + m.getValue().toString());
            }
        }
    }

}
