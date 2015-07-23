package com.honey.exception;

public class HoneyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6939954223192362291L;

	/**
	 * 
	 */
	public HoneyException() {
		super();
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public HoneyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**  
	 * @param message  
	 */ 
	public HoneyException(String message) {
		super(message);
	}

	/**  
	 * @param cause  
	 */ 
	public HoneyException(Throwable cause) {
		super(cause);
	}
}
