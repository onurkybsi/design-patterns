package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * <p>
 * This component might be representation for the logic which might be implemented in Order
 * microservice in a e-commerce system.
 * </p>
 * <p>
 * Order Service should be called to create an order in the system. However, when an error occurred
 * during the order processing in one of the other services, the created order should be set as
 * failed in the Order Service data repository.
 * </p>
 * <p>
 * At this point, we use <i>Observer</i> design pattern. Why ? You might say why we don't call Order
 * Service {@code setOrderFailed} API directly. That's the simplest way, right ? :) Yes it is, but
 * we should get the point which is the fact we have multiple services need to be notified for these
 * failures. For example, there are couple of services which needs to be aware the case
 * <i>"PaymentFailed"</i>. <b>There is a one to many relationship here.</b> Payment Service doesn't
 * need to know the services which needs the information <i>"PaymentFailed"</i>. It doesn't need to
 * be aware of the services which might need this information in the future. It's not dependent on
 * any service. These are the advantages of <i>Observer</i> design pattern that we might want to
 * take.
 * </p>
 */
public class OrderService implements Observer<Event> {

	class Order {

		private UUID id;
		private Boolean isSuccessful;
		private String errorMessage;

		Order(UUID orderId) {
			this.id = orderId;
			this.isSuccessful = true;
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

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
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
			Order failedOrder = orders.stream()
					.filter(o -> event.getReference().equals(o.getId().toString())).findFirst().orElseThrow();
			failedOrder.setIsSuccessful(false);
			failedOrder.setErrorMessage("PaymentFailed");
		}

		if ("StockUpdateFailed".equals(event.getType())) {
			Order failedOrder = orders.stream()
					.filter(o -> event.getReference().equals(o.getId().toString())).findFirst().orElseThrow();
			failedOrder.setIsSuccessful(false);
			failedOrder.setErrorMessage("StockUpdateFailed");
		}

		if ("DeliveryFailed".equals(event.getType())) {
			Order failedOrder = orders.stream()
					.filter(o -> event.getReference().equals(o.getId().toString())).findFirst().orElseThrow();
			failedOrder.setIsSuccessful(false);
			failedOrder.setErrorMessage("DeliveryFailed");
		}
	}

	public void createOrder(String productId) {
		UUID orderId = UUID.randomUUID();

		try {
			logger.info("Order is being created...");
			// create order entity in the data repository
			orders.add(new Order(orderId));
			// notify the others for the created order
			var orderCreatedEvent = new Event(orderId.toString(), "OrderCreated", null);
			orderCreatedEvent.addMetadata("productId", productId);
			eventManager.notify(orderCreatedEvent);
		} catch (Exception e) {
			// here is an important point
			// if we're not successful to notify the other services regarding an order was created,
			// we should not keep an entity on Order Service in order to have a consistent system
			orders.removeIf(o -> orderId.equals(o.getId()));
			// logging stuff might be done here
			throw new OrderException(String.format("Order couldn't be created: %s", e.getMessage()));
		}
	}

}
