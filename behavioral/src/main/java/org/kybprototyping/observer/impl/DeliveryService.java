package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;
import java.util.logging.Logger;

public class DeliveryService implements Observer<Event> {

  private static final Logger logger = Logger.getLogger("DeliveryService");

  private final EventManager transactionManager;

  public DeliveryService(EventManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Event state) {}

  private void saveDelivery(UUID id) {}

}
