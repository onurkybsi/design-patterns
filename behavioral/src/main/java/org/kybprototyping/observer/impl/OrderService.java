package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.UUID;

public class OrderService implements Observer<Transaction> {

  private final TransactionManager transactionManager;

  public OrderService(TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public void receive(Transaction state) {
    // might be part of different kind of transaction
  }

  public void order() {
    // order is being saved to database

    UUID orderId = UUID.randomUUID();

    this.transactionManager.notify(new Transaction(orderId, this.getClass().getSimpleName()));
  }

}
