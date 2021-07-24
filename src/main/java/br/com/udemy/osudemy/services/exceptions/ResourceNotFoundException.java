package br.com.udemy.osudemy.services.exceptions;

// exceção que o compilador não obriga tratar
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);
	}

}
