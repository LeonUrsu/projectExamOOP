package it.uni.main.exception;

/**
 * Eccezione nel caso vengano inseriti parametri sbagliati nei filtri
 */
public class IllegalTimeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalTimeException() {
		super("I parametri di tempo non rientrano tra 0:00:00 - 23:59:59");
	}
}
