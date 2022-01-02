package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.model.City;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Stats5Days;
import it.uni.main.service.Forecast5DaysService;


@Service
public class Forecasts5DaysStatistics extends Statistics{

	public Stats5Days getStats5DaysHumidity() {
		Forecast5DaysService forecast5DaysService = new Forecast5DaysService();
		double mediaHum = mediaUmiditaTotale(forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
		double minHum = umiditaMinAssoluta(forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
		double maxHum = umiditaMaxAssoluta(forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
		City city = forecast5DaysService.getForecast5Days().getCity();
		return new Stats5Days(mediaHum, minHum, maxHum, city, forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
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
