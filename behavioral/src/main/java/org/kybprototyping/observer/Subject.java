package org.kybprototyping.observer;

public interface Subject<T> {

  void register(Observer<T> observer);

  void unregister(Observer<T> observer);

  void notify(T state);

}
