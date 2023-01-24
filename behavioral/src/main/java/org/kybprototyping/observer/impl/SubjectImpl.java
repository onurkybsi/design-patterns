package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;

import java.util.HashSet;

public class SubjectImpl implements Subject {

  private static final HashSet<Observer> OBSERVERS = new HashSet<>();

  private String stateValue = null;

  @Override
  public void attach(Observer observer) {
    OBSERVERS.add(observer);
  }

  @Override
  public void detach(Observer observer) {
    OBSERVERS.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : OBSERVERS) {
      observer.update(this);
    }
  }

  public String getStateValue() {
    return stateValue;
  }

  public void setStateValue(String stateValue) {
    this.stateValue = stateValue;
  }

}
