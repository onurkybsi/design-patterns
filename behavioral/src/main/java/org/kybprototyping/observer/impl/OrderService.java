package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService implements Observer<Event> {

  private static final List<UUID> ORDERS = new ArrayList<>();

  private final EventManager transactionManager;

  public OrderService(EventManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Event state) {
    //
  }

  public void saveOrder(UUID id) {
    try {
      // order is being saved...
      ORDERS.add(id);
    } catch (Exception e) {
      transactionManager.notify(new Event(id, "OrderCouldNotBeSaved"));
    }
  }

}
