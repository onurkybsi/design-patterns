package org.kybprototyping.observer;

/**
 * Represents the interface which the subject components should support
 * 
 * @author Onur Kayabasi
 */
public interface Subject<T> {

	/**
	 * Registers the given {@code observer} to the subject to be notified when the {@code <T>} state
	 * is changed.
	 * 
	 * @param observer observer to observe the changes in the subject
	 */
	void attach(Observer<T> observer);

	/**
	 * Unregisters the given {@code observer} from the subject. So, the {@code observer} is not
	 * notified anymore when the {@code <T>} state is changed.
	 * 
	 * @param observer registered observer which should not be notified anymore
	 */
	void deattach(Observer<T> observer);

	/**
	 * Notifies the registered observers that the state has been changed.
	 * 
	 * @param state new state
	 */
	void notify(T state);

}
