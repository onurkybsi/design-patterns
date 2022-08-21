package org.kybprototyping.singleton;

/**
 * Represents a class which has only one instance in the whole application scope
 */
public class SingleInstanceClass {
  private static SingleInstanceClass instance = null;

  private SingleInstanceClass() {
  }

  /**
   * Represents the global access point of the single instance of this class
   * {@link SingleInstanceClass}
   * 
   * @return single instance of the class {@link SingleInstanceClass}
   */
  public static SingleInstanceClass getInstance() {
    if (instance == null) {
      // thanks to synchronized block we can be sure of thread safety
      synchronized (SingleInstanceClass.class) {
        instance = new SingleInstanceClass();
      }
    }
    return instance;
  }
}
