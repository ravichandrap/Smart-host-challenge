package com.rooms.occupancy.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ PotentialGuestsException.class })
	public ResponseEntity<Object> potentialGuestsException(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorCode(String.valueOf(HttpStatus.UNAUTHORIZED));
		errors.setDescription("Potential Guests not found");
		errors.setStatus(HttpStatus.NOT_FOUND);
		errors.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ FileNotFoundException.class })
	public ResponseEntity<Object> fileNotFound(Exception ex, WebRequest request) {
		ErrorResponse errors = new ErrorResponse();
		errors.setErrorCode(String.valueOf(HttpStatus.UNAUTHORIZED));
		errors.setDescription("Potential Guests file not found "+ex.getMessage());
		errors.setStatus(HttpStatus.NOT_FOUND);
		errors.setTimestamp(LocalDateTime.now());

		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
	}
}
