package it.uni.main.exception;

public class NoAPIResponseException extends Exception {
	
	
	/**
	 * Eccezione nel caso che l'api risponde con null
	 * 
	 *
	 */
	public NoAPIResponseException() {
		super("no API response");
		}
}


