package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This component might be representation for the logic which might be implemented in Delivery
 * microservice in a e-commerce system.
 * </p>
 * <p>
 * Delivery Service should be called to arrange a delivery for the created product. However, when an
 * error occurred in one of the other services, it should cancel the arranged delivery. In order to
 * be aware of the cases that the delivery should be canceled, the service keeps to track the events
 * which are occurred in the system by being attached to the related {@link Subject}
 * </p>
 */
public class DeliveryService implements Observer<Event> {

	class Delivery {

		private final UUID id;
		private final String reference;
		private Boolean canceled = Boolean.TRUE;

		Delivery(UUID id, String reference) {
			this.id = id;
			this.reference = reference;
		}

		public UUID getId() {
			return id;
		}

		public String getReference() {
			return reference;
		}

		public Boolean getCanceled() {
			return canceled;
		}

		public void setCanceled(Boolean canceled) {
			this.canceled = canceled;
		}

	}

	private static final Logger logger = Logger.getLogger("ProductService");

	private final List<Delivery> deliveries = new ArrayList<>();
	private final EventManager eventManager;

	public DeliveryService(EventManager eventManager) {
		eventManager.attach(this);
		this.eventManager = eventManager;
	}

	@Override
	public void update(Event event) {
		// if an order is created
		// the product should be attached to the order decreasing the stock of it
		if ("OrderCreated".equals(event.getType())) {
			createDeliveryForOrder(event.getReference());
		}

		// if payment fails in Payment Service,
		// the delivery should be canceled
		if ("PaymentFailed".equals(event.getType())) {
			cancelDelivery(event.getReference());
			return;
		}

		// if stock update fails in Product Service,
		// the delivery should be canceled
		if ("StockUpdateFailed".equals(event.getType())) {
			cancelDelivery(event.getReference());
		}
	}

	private void createDeliveryForOrder(String reference) {
		logger.log(Level.INFO, "Delivery is being created for the order {}", reference);
		try {
			deliveries.add(new Delivery(UUID.randomUUID(), reference));
		} catch (Exception e) {
			eventManager.notify(new Event(reference, "DeliveryFailed", e.getMessage()));
		}
	}

	private void cancelDelivery(String reference) {
		logger.log(Level.INFO, "Order({}) is being canceled...", reference);

		var relatedDelivery = deliveries.stream().filter(d -> d.getReference().equals(reference))
				.findFirst().orElseThrow();
		relatedDelivery.setCanceled(true);
	}

}
