package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;
import java.util.HashSet;

public class EventManager implements Subject<Event> {

  private static final HashSet<Observer<Event>> OBSERVERS = new HashSet<>();

  @Override
  public void subscribe(Observer<Event> observer) {
    if (!OBSERVERS.contains(observer)) {
      OBSERVERS.add(observer);
    }
  }

  @Override
  public void unsubscribe(Observer<Event> observer) {
    if (OBSERVERS.contains(observer)) {
      OBSERVERS.remove(observer);
    }
  }

  @Override
  public void notify(Event event) {
    validateEvent(event);
    OBSERVERS.forEach(o -> o.receive(event));
  }

  private void validateEvent(Event event) {
    if (event == null) {
      throw new IllegalArgumentException("event cannot be null!");
    }
    if (event.getId() == null) {
      throw new IllegalArgumentException("event.id cannot be null!");
    }
    if (event.getType() == null) {
      throw new IllegalArgumentException("event.type cannot be null!");
    }
  }

}
