package it.uni.main.exception;

/**
 * Eccezione nel caso venga lanciata la rotta stopCurrentService prima di StartCurrentService
 * 
 *
 */
public class StopNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StopNotValidException() {
		super("Nulla da stoppare,prima chiamare /startCurrentService");
	}
}
