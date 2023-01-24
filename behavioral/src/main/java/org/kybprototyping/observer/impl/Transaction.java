package org.kybprototyping.observer.impl;

import java.util.UUID;

public class Transaction {

  private final UUID id;

  private final String service;

  public Transaction(UUID id, String service) {
    this.id = id;
    this.service = service;
  }

  public UUID getId() {
    return id;
  }

  public String getService() {
    return this.service;
  }

}
