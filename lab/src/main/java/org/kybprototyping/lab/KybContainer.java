package org.kybprototyping.lab;

import java.util.HashSet;

public class KybContainer {
  private static final HashSet<InstanceInfo<?>> instanceInfos = initializeContainer();

  private KybContainer() {
    throw new UnsupportedOperationException();
  }

  public static <T> T getByType(Class<T> classInstanceOfType) {
    return null;
  }

  private static HashSet<InstanceInfo<?>> initializeContainer() {
    return null;
  }
}
