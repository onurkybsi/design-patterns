package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;
import java.util.HashSet;

public class TransactionManager implements Subject<Transaction> {

  private static final HashSet<Observer<Transaction>> OBSERVERS = new HashSet<>();

  @Override
  public void register(Observer<Transaction> observer) {
    if (!OBSERVERS.contains(observer)) {
      OBSERVERS.add(observer);
    }
  }

  @Override
  public void unregister(Observer<Transaction> observer) {
    if (OBSERVERS.contains(observer)) {
      OBSERVERS.remove(observer);
    }
  }

  @Override
  public void notify(Transaction transaction) {
    validateTransaction(transaction);
    OBSERVERS.forEach(o -> o.receive(transaction));
  }

  private void validateTransaction(Transaction transaction) {
    if (transaction == null) {
      throw new IllegalArgumentException("transaction cannot be null!");
    }
    if (transaction.getId() == null) {
      throw new IllegalArgumentException("transaction.id cannot be null!");
    }
    if (OBSERVERS.stream()
        .noneMatch(o -> o.getClass().getSimpleName().equalsIgnoreCase(transaction.getService()))) {
      throw new IllegalArgumentException(
          "transaction.service is not one of the existing services!");
    }
  }

}
