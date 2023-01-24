package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;

public class BasketService implements Observer<Transaction> {

  private static final String SERVICE_TO_WAIT = "PaymentService";

  private final TransactionManager transactionManager;

  public BasketService(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Transaction state) {
    if (SERVICE_TO_WAIT.equals(state.getService())) {
      updateBasket(state.getId());
    }
  }

  private void updateBasket(UUID id) {
    // basket is being updated...
  }

}
