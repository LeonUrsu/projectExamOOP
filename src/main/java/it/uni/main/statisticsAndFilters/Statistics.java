package it.uni.main.statisticsAndFilters;

import java.util.Vector;

public class Statistics {

	//tutti i metodi presenti in questa classe ricevono un Vector<Object> ,siccome la classe Forecast5Days una volta richiamata 
	//la rotta associata mantiene il Vector<Forecasts5Days> in memoria che non deriva da nessun filtraggio
	//e inoltre necessitiamo di due tipi di Statistiche differenti per quanto riguarda la Forecast5Days e la CurrentForecast
	//sarebbe corretto dividere i metodi appartenenti a entrambe le classi e quelli non appartenenti tramite l'Ereditarietà
	
	
	protected double mediaUmiditaTotale(Vector<Object> previsioni)
	{
		return 0;	
	}
	
	
	protected double mediaUmiditaMin(Vector<Object> previsioni)
	{
		return 0;	
	}
	
	
	protected double mediaUmiditaMax(Vector<Object> previsioni)
	{
		return 0;	
	}
	
	
	
	
	
	
	
	
	
}
