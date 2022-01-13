package it.uni.main.printsConsole;

/**
 * Classe per la separazione dei messaggi stampati a console dal resto del codice
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class FiltersPrint {
	
	/**
	 * Metodo void per eseguire una stampa a console messaggio: 
	 * Filtrati tutti gli elementi, impossibile fare le statistiche! 
	 */
	public void print1() {
	System.out.println(">>>Filtrati tutti gli elementi, impossibile fare le statistiche!<<<	");
	}
	
	
	/**
	 * Metodo void per eseguire una stampa a console messaggio: 
	 * Filtrati  " + ele + "  elementi!!!
	 * @param ele il parametro di elementi filtrati
	 */
	public void print2(int ele){
	System.out.println(">>>Filtrati  " + ele + "  elementi!!!<<<");	
	}
	
	
	public void print3(int ele){
		System.out.println(">>>Intervallo richiesto troppo grande, il file contiene solo " + ele + " elementi<<<");	
		}
}
