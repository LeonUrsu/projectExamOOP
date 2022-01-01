package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import it.uni.main.model.Forecast5Days;

public class Statistics5DaysForecasts extends Statistics{
	
	
	@Override
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
	
	
	
	@Override
	/**
	 * metodo che trova il valore minimo dell'umidita nelle previsioni
	 * @param previsioni array di oggetti 	 
	 */
	protected double UmiditaMinAssoluta(Vector<Forecast5Days> previsioni)
	{
		int valueOf = previsioni.get(0).getHumidity().getValue(); 
		for(Forecast5Days e : previsioni)
			if(e.getHumidity().getValue() < valueOf )
				valueOf = e.getHumidity().getValue();
		return valueOf;	
	}
	
	
	
	@Override
	/**
	 * metodo che trova il valore massimo dell'umidita nelle previsioni
	 * @param previsioni array di oggetti 	 
	 */
	protected double UmiditaMaxAssoluta(Vector<Forecast5Days> previsioni)
	{
		int valueOf = previsioni.get(0).getHumidity().getValue();
		for(Forecast5Days e : previsioni)
			if(e.getHumidity().getValue() > valueOf )
				valueOf = e.getHumidity().getValue();	
		return valueOf;	
	}


}
