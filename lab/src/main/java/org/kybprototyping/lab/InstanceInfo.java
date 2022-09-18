package org.kybprototyping.lab;

import org.kybprototyping.lab.utils.Assertions;

public class InstanceInfo<T> {
  private Class<T> classInstance;

  private InstanceInfo(Class<T> classInstance) {
    this.classInstance = classInstance;
  }

  public static <T> InstanceInfo<T> of(Class<T> classInstance) {
    Assertions.notNull(classInstance, "classInstance cannot be null!");
    return new InstanceInfo<>(classInstance);
  }
}
