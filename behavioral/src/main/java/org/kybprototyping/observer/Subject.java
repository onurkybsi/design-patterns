package org.kybprototyping.observer;

public interface Subject<T> {

	void attach(Observer<T> observer);

	void deattach(Observer<T> observer);

	void notify(T state);

}
