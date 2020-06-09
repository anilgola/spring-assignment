package org.anil.exception;

import java.time.LocalDateTime;

public class TicketNotFoundException extends RuntimeException {

	public TicketNotFoundException(String message) {
		super(message);
	}
	
}
