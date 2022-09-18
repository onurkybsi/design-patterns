package org.kybprototyping.lab.utils;

public class Assertions {
  private Assertions() {
    throw new UnsupportedOperationException();
  }

  public static void notNull(Object value, String message) {
    if (value == null) {
      throw new IllegalArgumentException(message);
    }
  }
}
