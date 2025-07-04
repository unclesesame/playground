package com.abner.playground.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher implements Subject{

    List<Observer> observerList = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }


    @Override
    public void notifyObservers(String message) {
        for(Observer observer: observerList){
            observer.update(message);
        }
    }
}
