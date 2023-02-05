package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderService implements Observer<Event> {

	class Order {

		private UUID id;
		private Boolean isSuccessful;

		Order(UUID id, Boolean isSuccessful) {
			this.id = id;
			this.isSuccessful = isSuccessful;
		}

		public UUID getId() {
			return id;
		}

		public Boolean getIsSuccessful() {
			return isSuccessful;
		}

		public void setIsSuccessful(Boolean isSuccessful) {
			this.isSuccessful = isSuccessful;
		}

	}

	class OrderException extends RuntimeException {

		OrderException(String message) {
			super(message);
		}

	}

	private static final Logger logger = Logger.getLogger("OrderService");
	private static final List<Order> orders = new ArrayList<>();

	private final EventManager eventManager;

	public OrderService(EventManager eventManager) {
		eventManager.attach(this);
		this.eventManager = eventManager;
	}

	@Override
	public void update(Event event) {
		if ("PaymentFailed".equals(event.getType())) {
			// do what do you want to do in the payment failure case
			orders.removeIf(o -> event.getReference().equals(o.getId().toString()));
		}
	}

	public void saveOrder() {
		UUID orderId = UUID.randomUUID();

		try {
			logger.info("Order is being saved...");
			orders.add(new Order(orderId, true));
			eventManager.notify(new Event(orderId.toString(), "OrderSaved", null));
		} catch (Exception e) {
			orders.removeIf(o -> orderId.equals(o.getId()));
			// logging stuff
			throw new OrderException(String.format("Order couldn't be saved: %s", e.getMessage()));
		}
	}

}
