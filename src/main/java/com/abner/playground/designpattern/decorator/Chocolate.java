package com.abner.playground.designpattern.decorator;

public class Chocolate extends DrinkDecorator {

    public Chocolate(AbstractDrink drink) {
        super(drink);
        setDescription("巧克力");
        setPrice(1.5d);
    }
}
