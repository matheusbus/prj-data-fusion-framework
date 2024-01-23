/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core.entity.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public abstract class INFEntityDefinitionSubject {

    private List<INFEntityDefinitionObserver> observers = new ArrayList<>();

    public void addObserver(INFEntityDefinitionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(INFEntityDefinitionObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String entityName) {
        for (INFEntityDefinitionObserver observer : observers) {
            observer.onCollectionDefinitionCreated(entityName);
        }
    }

}
