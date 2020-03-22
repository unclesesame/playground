package com.abner.playground.designpattern.decorator;

import com.sun.xml.bind.v2.runtime.reflect.Lister;

/**
 * 装饰类:  继承加组合被装饰类
 */
public class DrinkDecorator extends AbstractDrink {
    private AbstractDrink drink;//包含被装饰的对象

    public DrinkDecorator(AbstractDrink drink) {
        this.drink = drink;
    }

    @Override
    protected Double cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription(){
        return super.getDescription() + " " + super.getPrice() + "&&" + drink.getDescription();
    }
}
