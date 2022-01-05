package it.uni.main.exception;

public class NoAPIResponseException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Eccezione nel caso che l'api risponde con null
	 * 
	 *
	 */
	public NoAPIResponseException() {
		super("no API response");
		}
}


