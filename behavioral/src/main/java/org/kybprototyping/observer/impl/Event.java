package org.kybprototyping.observer.impl;

import java.util.UUID;

public class Event {

	private final UUID id;
	private final String reference;
	private final String type;
	private final String message;

	public Event(String reference, String type, String message) {
		this.id = UUID.randomUUID();
		this.reference = reference;
		this.type = type;
		this.message = message;
	}

	public UUID getId() {
		return id;
	}

	public String getReference() {
		return reference;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

}
