package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;
import java.util.logging.Logger;

public class PaymentService implements Observer<Event> {

  private static final Logger logger = Logger.getLogger("PaymentService");

  private final EventManager transactionManager;

  public PaymentService(EventManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Event state) {
    if ("OrderCouldNotBeSaved".equals(state.getType())) {
      refund(state.getId());
    }
  }

  private void refund(UUID id) {}

  public void receivePayment(UUID orderId) {
    try {
      logger.info("Payment receiving...");
      transactionManager.notify(new Event(orderId, "PaymentReceived"));
    } catch (Exception e) {
      transactionManager.notify(new Event(orderId, "PaymentCouldNotBeReceived"));
    }
  }

}
