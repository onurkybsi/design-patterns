package org.kybprototyping.observer;

public interface Observer<T> {

  void receive(T state);

}
