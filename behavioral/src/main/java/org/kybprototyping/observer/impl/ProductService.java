package org.kybprototyping.observer.impl;

import org.kybprototyping.observer.Observer;
import org.kybprototyping.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This component might be representation for the logic which might be implemented in Product
 * microservice in a e-commerce system.
 * </p>
 * <p>
 * Product Service should be called to update the stock information of the products. However, when
 * an error occurred in one of the other services, it should not update the stock information. In
 * order to be aware of the cases that the stock update should be rolled back, the service keeps to
 * track the events which are occurred in the system by being attached to the related
 * {@link Subject}
 * </p>
 */
public class ProductService implements Observer<Event> {

	class Product {
		private final UUID id;
		private final List<String> references = new ArrayList<>();

		private Integer stock;

		Product(UUID id, Integer initialStock) {
			this.id = id;
			this.stock = initialStock;
		}

		public UUID getId() {
			return id;
		}

		public List<String> getReferences() {
			return references;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}

	}

	private static final Logger logger = Logger.getLogger("ProductService");

	private final List<Product> products;
	private final EventManager eventManager;

	public ProductService(EventManager eventManager) {
		this.products = new ArrayList<>();
		Product productX = new Product(UUID.randomUUID(), 10);
		products.add(productX);

		eventManager.attach(this);
		this.eventManager = eventManager;
	}

	@Override
	public void update(Event event) {
		// if an order is created
		// the product should be attached to the order decreasing the stock of it
		if ("OrderCreated".equals(event.getType())) {
			attachOrderReferenceToProduct(event);
			return;
		}

		// if payment fails in Payment Service,
		// the stock should be increased
		if ("PaymentFailed".equals(event.getType())) {
			increaseStock(event.getReference());
			return;
		}

		// if delivery fails in Delivery Service,
		// the stock should be increased
		if ("DeliveryFailed".equals(event.getType())) {
			increaseStock(event.getReference());
		}
	}

	private void attachOrderReferenceToProduct(Event orderCreatedEvent) {
		logger.log(Level.INFO, "Order({}) is being attached to the product",
				orderCreatedEvent.getReference());

		try {
			// etract the product id from metadata of the event
			UUID producId = Optional.ofNullable(orderCreatedEvent.getMetadata("productId"))
					.map(UUID::fromString).orElseThrow();

			// find the related product from the data repository and update it by the event
			var relatedProduct =
					products.stream().filter(p -> p.getId().equals(producId)).findFirst().orElseThrow();
			relatedProduct.getReferences().add(orderCreatedEvent.getReference());
			relatedProduct.setStock(relatedProduct.getStock() - 1);
		} catch (Exception e) {
			// logging stuff here
			eventManager
					.notify(new Event(orderCreatedEvent.getReference(), "StockUpdateFailed", e.getMessage()));
		}
	}

	private void increaseStock(String reference) {
		// find the related order and increase the stock
		var relatedProduct = products.stream().filter(p -> p.getReferences().contains(reference))
				.findFirst().orElseThrow();
		relatedProduct.setStock(relatedProduct.getStock() + 1);
	}

}
