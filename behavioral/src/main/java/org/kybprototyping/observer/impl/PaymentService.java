package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;

public class PaymentService implements Observer<Transaction> {

  private static final String SERVICE_TO_WAIT = "OrderService";

  private final TransactionManager transactionManager;

  public PaymentService(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Transaction state) {
    if (SERVICE_TO_WAIT.equals(state.getService())) {
      receivePayment(state.getId());
    }
  }

  private void receivePayment(UUID orderId) {
    // payment is being received

    // payment is successful, then:
    transactionManager.notify(new Transaction(orderId, this.getClass().getSimpleName()));
  }

}
