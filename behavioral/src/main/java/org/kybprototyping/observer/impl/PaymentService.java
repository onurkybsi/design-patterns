package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentService implements Observer<Event> {

	class Payment {

		private final UUID id;
		private final String reference;

		public Payment(UUID id, String reference) {
			this.id = id;
			this.reference = reference;
		}

		public UUID getId() {
			return id;
		}

		public String getReference() {
			return reference;
		}

	}

	private static final Logger logger = Logger.getLogger("PaymentService");
	private static final List<Payment> payments = new ArrayList<>();

	private final EventManager eventManager;

	public PaymentService(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	@Override
	public void receive(Event event) {
		if ("BasketFailed".equals(event.getType())) {
			var relatedPayment = payments.stream()
					.filter(p -> event.getReference().equals(p.getReference())).findFirst().orElseThrow();
			refund(relatedPayment);
		}
		if ("DeliveryFailed".equals(event.getType())) {
			var relatedPayment = payments.stream()
					.filter(p -> event.getReference().equals(p.getReference())).findFirst().orElseThrow();
			refund(relatedPayment);
		}
	}

	private void refund(Payment payment) {
		logger.log(Level.INFO, "Payment {} is being rolled back...", payment.getId());
		// Rollback operation...
		payments.removeIf(p -> payment.getReference().equals(p.getReference()));
	}

	public void receivePayment(UUID orderId) {
		try {
			logger.info("Payment receiving...");
			// do the external calls, db transactions etc.
			payments.add(new Payment(UUID.randomUUID(), orderId.toString()));
			eventManager.notify(new Event(orderId.toString(), "PaymentSuccessful", null));
		} catch (Exception e) {
			eventManager.notify(new Event(orderId.toString(), "PaymentFailed", e.getMessage()));
		}
	}

}
