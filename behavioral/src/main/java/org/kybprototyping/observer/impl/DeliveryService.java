package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;

public class DeliveryService implements Observer<Transaction> {

  private static final String SERVICE_TO_WAIT = "PaymentService";

  private final TransactionManager transactionManager;

  public DeliveryService(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Transaction state) {
    if (SERVICE_TO_WAIT.equals(state.getService())) {
      saveDelivery(state.getId());
    }

    // maybe notification service ?
    this.transactionManager.notify(new Transaction(state.getId(), this.getClass().getSimpleName()));
  }

  private void saveDelivery(UUID id) {
    // new delivery operation is being saved...
  }

}
