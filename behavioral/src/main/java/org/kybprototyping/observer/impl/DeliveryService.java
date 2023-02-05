package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;

public class DeliveryService implements Observer<Event> {

	private final EventManager eventManager;

	public DeliveryService(EventManager eventManager) {
		eventManager.attach(this);
		this.eventManager = eventManager;
	}

	@Override
	public void update(Event state) {}

}
