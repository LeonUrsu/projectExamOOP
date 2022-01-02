package it.uni.main.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.exception.StopNotValidException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.Stats5Days;
import it.uni.main.model.Forecast5DaysHumidity;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.service.Forecast5DaysService;
import it.uni.main.statisticsAndFilters.Filters;
import it.uni.main.statisticsAndFilters.Forecasts5DaysStatistics;


@RestController
public class OpenWeatherController 
{
	@Autowired
	private Forecast5DaysService forecast5Day;
	@Autowired 
	private CurrentForecastService currentForecast;
	@Autowired
	private Filters filters;
	@Autowired
	private Forecasts5DaysStatistics statistics5DaysForecasts;
	
	
	
	
	//Si puo aggiungere qui la rilevazione dell'IP per la previsione se non si passa 
	//un parametro nome della citta
	/**
	 * Rotta che ci restituisce un array di oggetti con informazioni sull'umidità degli ultimi 5 giorni
	 * @param  nome della città su cui prendere le previsioni
	 * @return Vector di previsioni
	 */
	@GetMapping("/getCompleteForecast")
	public Forecast5DaysHumidity forecast5day(@RequestParam(value="cityName", defaultValue="Rome") String cityName) {
		return forecast5Day.forecast5day(cityName);
	}
	
	
	
	@GetMapping("/getHumidityStats")
	public Stats5Days forecast5day() {
		return statistics5DaysForecasts.getStats5DaysHumidity();
	}
	
	
	/**
	 * Rotta per carivare il Vector forecastDataCurrentVector di oggetti
	 * @return
	 */
	@GetMapping("/loadCurrent")
	public JsonObject loadVector() {
		currentForecast.forecastCurrOffline();
		JsonObject tmp = new JsonObject();
		tmp.addProperty("Request Status", "Loaded");
		return tmp;
	}
	
	
	/**
	 * Rotta che avvia il salvataggio delle previsoni ogni ora
	 * @param nome città di cui prendere le previsioni
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping("/startCurrentService") //si potrebbe aggiungere un periodo di funzionamento
	public void currentForecast(@RequestParam(value="nome", defaultValue="Rome") String cityName) throws IOException, ParseException{
	currentForecast.ripetizioneMetodo(cityName);	
	}
	
	
	
	/**
	 * Rotta che ferma il salvataggio delle previsioni ogni ora
	 * @throws StopNotValidException 
	 * @throws InterruptedException 
	 */
	@GetMapping("/stopCurrentService")
	public void currentForecastStop(Timer timer) throws StopNotValidException{
	currentForecast.stopTimer();
	}
	
	
	
	/**
	 * Rotta per il filtraggio delle previsioni su una banda passante per l'intervallo orario giornaliero in base ai 
	 * parametri passati
	 * @param initialValue ora iniziale di inizio filtraggio
	 * @param finalValue ora finale del filtraggio
	 * @param days giorni di  filtraggio, se 0 restituisce un errore ancora da stabilire
	 * @return vector di previsioni meteo in base ai parametri passati
	 * @throws IllegalTimeException 
	 * @throws IllegalArgumentException 
	 */
	@GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}")
	public CurrentStats  dailyBand(@PathVariable long initialValue, @PathVariable long finalValue, @PathVariable int days) throws IllegalArgumentException, IllegalTimeException{
		return filters.dailyFilter(finalValue, finalValue, days);
	}
	
	
	
	/**
	 * Rotta per il filtraggio delle previsioni su una banda passante per l'intervallo orario giornaliero in base ai 
	 * parametri passati
	 * @param initialValue ora iniziale di inizio filtraggio
	 * @param finalValue ora finale del filtraggio 
	 * @return vector di previsioni meteo in base ai parametri passati
	 * @throws IllegalTimeException 
	 * @throws IllegalArgumentException 
	 */
	@GetMapping("/filter/weekly/{initialValue}/{finalValue}")
	public CurrentStats weeklyBand(@PathVariable long initialValue, @PathVariable long finalValue) throws IllegalArgumentException, IllegalTimeException{
		return filters.weeklyFilter(initialValue, finalValue);
	}
	

}	
	
	
	
	
	

