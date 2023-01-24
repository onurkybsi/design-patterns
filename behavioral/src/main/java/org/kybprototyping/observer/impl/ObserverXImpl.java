package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;

public class ObserverXImpl implements Observer {

  @Override
  public void update(Subject theChangedSubject) {
    System.out.println(String.format("I'm %s aware whether the state has changed!", this.getClass().getSimpleName()));
  }

}
