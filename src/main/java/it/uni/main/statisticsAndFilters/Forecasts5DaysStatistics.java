package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.model.City;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Stats5Days;
import it.uni.main.service.Forecast5DaysService;

/**
 * Classe servizio per il calcolo delle statistiche per la parte del progetto riguardante l'umidità
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Forecasts5DaysStatistics extends Statistics{
//forecast5DaysHumidity
	public Stats5Days getStats5DaysHumidity()throws NullPointerException {
		//Forecast5DaysService forecast5DaysService = new Forecast5DaysService();
		double mediaHum = mediaUmiditaTotale(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum() );
		double minHum = umiditaMinAssoluta(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum());
		double maxHum = umiditaMaxAssoluta(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum());
		City city = Forecast5DaysService.forecast5DaysHumidity.getCity();
		return new Stats5Days(mediaHum, minHum, maxHum, city);//, forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
	}
	
	
	
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
	protected double umiditaMinAssoluta(Vector<Forecast5Days> previsioni)
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
	protected double umiditaMaxAssoluta(Vector<Forecast5Days> previsioni)
	{
		int valueOf = previsioni.get(0).getHumidity().getValue();
		for(Forecast5Days e : previsioni)
			if(e.getHumidity().getValue() > valueOf )
				valueOf = e.getHumidity().getValue();	
		return valueOf;	
	}


}
