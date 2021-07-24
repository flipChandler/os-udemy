package br.com.udemy.osudemy.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.udemy.osudemy.services.exceptions.DatabaseException;
import br.com.udemy.osudemy.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standarError = new StandardError(Instant.now(),
				status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standarError);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standarError = new StandardError(Instant.now(),
				status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standarError);
	}
	
}
