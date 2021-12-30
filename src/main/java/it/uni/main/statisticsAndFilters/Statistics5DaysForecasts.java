package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import it.uni.main.model.Forecast5Days;

public class Statistics5DaysForecasts extends Statistics{
	
	
	/**
	 * Metodo che ci restituisce il valore medio dell'umidità di un vettore Forecast5Day
	 * @param previsioni previsoni a cui interno ci sono dati riguardo l'umidità
	 * @return umidità media
	 */
	@Override
	protected double mediaUmiditaTotale(Vector<Object> previsioni){
		double totHumidity = 0;
		for(Object e : previsioni)
			totHumidity = ((Forecast5Days)e).getHumidity().getValue();
		if(previsioni.size() == 0) throw new IllegalArgumentException();
	
		return totHumidity/previsioni.size();	
	}
	

}
