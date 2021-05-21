package org.sklsft.generator.model.exception;

public class PopulateTableFailureException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public PopulateTableFailureException (String message) {
		super(message);
	}

	public PopulateTableFailureException (String message, Throwable t) {
		super(message, t);
	}
}
