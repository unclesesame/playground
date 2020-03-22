package com.abner.playground.temp;

import java.util.ArrayList;
import java.util.List;

public class Temp {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("[MCCPD]");

        List<String> compentList = new ArrayList<>();
        compentList.add("[MCCPD]");

        compentList.removeAll(list);

        System.out.println(compentList.size());

    }



}
