package org.kybprototyping.observer.impl;

import java.util.UUID;

public class Event {

  private final UUID id;

  private final String type;

  public Event(UUID id, String type) {
    this.id = id;
    this.type = type;
  }

  public UUID getId() {
    return id;
  }

  public String getType() {
    return this.type;
  }

}
