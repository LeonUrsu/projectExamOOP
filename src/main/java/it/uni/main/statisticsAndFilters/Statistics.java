package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import it.uni.main.model.Forecast5Days;

/**
 * super classe pre le statistiche da cui derivano altri tipi di classi a seconda dei modelli disponibili  
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class Statistics {

	//tutti i metodi presenti in questa classe ricevono un Vector<Object> ,siccome la classe Forecast5Days una volta richiamata 
	//la rotta associata mantiene il Vector<Forecasts5Days> in memoria che non deriva da nessun filtraggio
	//e inoltre necessitiamo di due tipi di Statistiche differenti per quanto riguarda la Forecast5Days e la CurrentForecast
	//sarebbe corretto dividere i metodi appartenenti a entrambe le classi e quelli non appartenenti tramite l'Ereditarietà
	
	
	
	/**
	 * metodo che trova il valore medio dell'umidita nelle previsioni
	 * @param previsioni array di oggetti 	 
	 */
	protected double mediaUmiditaTotale(Vector<Forecast5Days> previsioni)
	{
		double somma = 0;
		for(Forecast5Days e : previsioni)
			somma += e.getHumidity().getValue();
		int size = previsioni.size();
		if(size == 0) throw new IllegalArgumentException();
		return somma/size;	
	}
	
	
		
	/**
	 * metodo che trova il valore minimo dell'umidita nelle previsioni
	 * @param previsioni array di oggetti 	 
	 */
	protected double umiditaMinAssoluta(Vector<Forecast5Days> previsioni)
	{
		int valueOf = previsioni.get(0).getHumidity().getValue(); 
		for(Forecast5Days e : previsioni)
			if(e.getHumidity().getValue() < valueOf )
				valueOf = e.getHumidity().getValue();
		return valueOf;	
	}
	
	

	/**
	 * metodo che trova il valore massimo dell'umidita nelle previsioni
	 * @param previsioni array di oggetti 	 
	 */
	protected double umiditaMaxAssoluta(Vector<Forecast5Days> previsioni)
	{
		int valueOf = previsioni.get(0).getHumidity().getValue();
		for(Forecast5Days e : previsioni)
			if(e.getHumidity().getValue() > valueOf )
				valueOf = e.getHumidity().getValue();	
		return valueOf;	
	}

	
	
	
	
	
	
	
	
}
