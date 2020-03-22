package com.abner.playground.designpattern.decorator;

public class Coffee extends AbstractDrink{

    @Override
    protected Double cost() {
        return super.getPrice();
    }
}
