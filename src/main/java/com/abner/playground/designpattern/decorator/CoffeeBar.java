package com.abner.playground.designpattern.decorator;

/**
 * client fo decorator pattern
 */
public class CoffeeBar {
    public static void main(String[] args) {
        AbstractDrink drink = new LongBlack();
        System.out.println("Only long black cost: " + drink.cost());

        drink = new Milk(drink);
        System.out.println("long black and milk cost: " + drink.cost());

        drink = new Chocolate(drink);
        System.out.println("long black with milk and chocolate cost: " + drink.cost());
    }
}
