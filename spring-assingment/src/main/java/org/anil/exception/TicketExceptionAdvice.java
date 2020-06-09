package org.anil.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TicketExceptionAdvice extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = {TicketNotFoundException.class})
	public ResponseEntity<Object> handleTicketNotFoundException(Exception ex, WebRequest request) throws Exception { 
		String message = ex.getLocalizedMessage();
		ErrorObject errorObject = new ErrorObject(LocalDate.now(), message);
		return new ResponseEntity(errorObject, HttpStatus.NOT_FOUND);
		
	}
}
