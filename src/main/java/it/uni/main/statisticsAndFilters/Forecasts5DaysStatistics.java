package it.uni.main.statisticsAndFilters;

import org.springframework.stereotype.Service;

import it.uni.main.model.City;
import it.uni.main.model.Stats5Days;
import it.uni.main.service.Forecast5DaysService;

/**
 * Classe servizio per il calcolo delle statistiche per la parte del progetto riguardante l'umidità
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Forecasts5DaysStatistics extends Statistics{

	public Stats5Days getStats5DaysHumidity()throws NullPointerException {
		//Forecast5DaysService forecast5DaysService = new Forecast5DaysService();
		double mediaHum = mediaUmiditaTotale(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum() );
		double minHum = umiditaMinAssoluta(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum());
		double maxHum = umiditaMaxAssoluta(Forecast5DaysService.forecast5DaysHumidity.getForecast5DaysVectorHum());
		City city = Forecast5DaysService.forecast5DaysHumidity.getCity();
		return new Stats5Days(mediaHum, minHum, maxHum, city);//, forecast5DaysService.getForecast5Days().getForecast5DaysVectorHum());
	}
}
