package org.kybprototyping;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.impl.ObserverXImpl;
import org.kybprototyping.observer.impl.SubjectImpl;

public class Main {
  public static void main(String[] args) {
    SubjectImpl s = new SubjectImpl();

    Observer ox = new ObserverXImpl();
    Observer oy = new ObserverXImpl();

    s.attach(ox);
    s.attach(oy);

    s.setStateValue("state_1");
    s.notifyObservers();
    s.setStateValue("state_2");
    s.notifyObservers();
  }
}
