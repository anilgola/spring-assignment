package org.anil.exception;

import java.time.LocalDate;

public class ErrorObject {
	private LocalDate date;
	private String message;
	
	public ErrorObject() {}

	public ErrorObject(LocalDate date, String message) {
		super();
		this.date = date;
		this.message = message;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
