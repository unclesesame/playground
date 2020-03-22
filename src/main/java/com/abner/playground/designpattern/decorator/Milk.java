package com.abner.playground.designpattern.decorator;

public class Milk extends DrinkDecorator {
    public Milk(AbstractDrink drink) {
        super(drink);
        setDescription("牛掰");
        setPrice(2.0d);
    }
}
