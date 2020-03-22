package com.abner.playground.designpattern.decorator;

public abstract class AbstractDrink {

    private String description;
    private double price = 0d;

    protected String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    abstract protected Double cost();
}
