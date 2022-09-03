package org.kybprototyping.singleton;

/**
 * Represents a class which has only one instance in the whole application scope
 */
public class SingleInstanceClass {
  private static SingleInstanceClass instance = null;

  private SingleInstanceClass() throws IllegalAccessException {
    // To prevent AccessibleObject.setAccessible()
    if (instance != null) {
      throw new IllegalAccessException("This class is supposed to be singleton!");
    }
  }

  /**
   * Represents the global access point of the single instance of this class
   * {@link SingleInstanceClass}
   * 
   * @return single instance of the class {@link SingleInstanceClass}
   */
  @SuppressWarnings("java:S112")
  public static SingleInstanceClass getInstance() {
    if (instance == null) {
      // thanks to synchronized block we can be sure of thread safety
      synchronized (SingleInstanceClass.class) {
        try {
          instance = new SingleInstanceClass();
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Unexpected exception occurred!", e);
        }
      }
    }
    return instance;
  }
}
