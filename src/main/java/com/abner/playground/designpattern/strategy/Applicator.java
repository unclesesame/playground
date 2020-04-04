package com.abner.playground.designpattern.strategy;

import java.util.Arrays;
import java.util.Scanner;

//根据传入的参数类型从而具备不同行为的方法称为策略设计模式
public class Applicator {

    public static void apply(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static void main(String[] args) {
        String s = "We are such stuff as dreams are made on";
        apply(new UpCaseProcessor(), s);
        apply(new DownCaseProcessor(), s);
        apply(new SplitProcessor(), s);

    }
}


class UpCaseProcessor implements Processor {
    @Override
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class DownCaseProcessor implements Processor {
    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class SplitProcessor implements Processor {
    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}
