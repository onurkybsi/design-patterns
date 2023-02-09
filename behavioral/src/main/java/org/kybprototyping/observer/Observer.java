package org.kybprototyping.observer;

/**
 * Represents the interface which the observer components should support
 */
public interface Observer<T> {

	/**
	 * Updates the observer state by the change in the subject
	 * 
	 * @param state current state information of the observed subject
	 */
	void update(T state);

}
