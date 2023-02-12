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
 * This component might be representation for the logic which might be implemented in Payment
 * microservice in a e-commerce system.
 * </p>
 * <p>
 * Payment Service should be called to receive the payment for an order. However, when an error
 * occurred in one of the other services, it should refund the received payment. In order to be
 * aware of the cases that refund should be made, the service keeps to track the events which are
 * occurred in the system by being attached to the related {@link Subject}
 * </p>
 */
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
		eventManager.attach(this);
		this.eventManager = eventManager;
	}

	@Override
	public void update(Event event) {
		// if stock update fails in Product Service,
		// the received payment should be refunded
		if ("StockUpdateFailed".equals(event.getType())) {
			var relatedPayment = payments.stream()
					.filter(p -> event.getReference().equals(p.getReference())).findFirst().orElseThrow();
			refund(relatedPayment);
		}

		// if delivery fails in Delivery Service,
		// the received payment should be refunded
		if ("DeliveryFailed".equals(event.getType())) {
			var relatedPayment = payments.stream()
					.filter(p -> event.getReference().equals(p.getReference())).findFirst().orElseThrow();
			refund(relatedPayment);
		}
	}

	public void receivePayment(UUID orderId) {
		try {
			logger.info("Payment receiving...");
			// the external calls, db transactions etc.
			payments.add(new Payment(UUID.randomUUID(), orderId.toString()));
			eventManager.notify(new Event(orderId.toString(), "PaymentSuccessful", null));
		} catch (Exception e) {
			eventManager.notify(new Event(orderId.toString(), "PaymentFailed", e.getMessage()));
		}
	}

	private void refund(Payment payment) {
		logger.log(Level.INFO, "Payment {} is being rolled back...", payment.getId());
		// rollback operations...
		payments.removeIf(p -> payment.getReference().equals(p.getReference()));
	}

}
