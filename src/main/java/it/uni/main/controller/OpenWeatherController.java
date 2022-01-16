package it.uni.main.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.exception.StopNotValidException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.Stats5Days;
import it.uni.main.model.Forecast5DaysHumidity;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.service.Forecast5DaysService;
import it.uni.main.service.Loader;
import it.uni.main.statisticsAndFilters.Filters;
import it.uni.main.statisticsAndFilters.Forecasts5DaysStatistics;

/**
 * Classe che contiene tutte le rotte disponibile del servizio meteo
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
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
	@Autowired
	private Loader loader;
	
	
	
	//IDEA: Si puo aggiungere qui la rilevazione dell'IP per la previsione se non si passa 
	//un parametro nome della città invece di usare il default
	/**
	 * Rotta che ci restituisce un array di oggetti con informazioni sull'umidità degli ultimi 5 giorni
	 * @param cityName della città su cui prendere le previsioni
	 * @return Vector di previsioni
	 */
	@GetMapping("/getCompleteForecast")
	public Forecast5DaysHumidity forecast5day(@RequestParam(value="cityName", defaultValue="Rome") String cityName) {
		Forecast5DaysHumidity forecast5DaysHumidity = null;
		try {
			forecast5DaysHumidity = forecast5Day.forecast5day(cityName);
		}catch(NullPointerException e) {
			return null;
		}
		return forecast5DaysHumidity;
	}
	
	
	
	/**
	 * Rotta calcolo statistiche riguardanti le umidità 
	 * @return ritorna al chiamante le statistiche in json dell'umidità dei prossimi 5 giorni
	 */
	@GetMapping("/getHumidityStats")
	public Stats5Days forecast5day() {
		Stats5Days stats5Days = null;
		try {
			stats5Days = statistics5DaysForecasts.getStats5DaysHumidity();
		}catch(NullPointerException e) {
			return null;
		}
		return stats5Days;	
	}
	
	
	
	/**
	 * Rotta pre caricare in vettore di previsioni posizionato nella classe Filters chiamato toFileterVector 
	 * @return ritorna al chiamante true se è stato caricato
	 */
	@GetMapping("/load/{cityName}")
	public boolean writeHistoryWeather(@PathVariable String cityName) {
		boolean response = false;
		try {
			if(loader.load(cityName))
				response = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	/**
	 * Rotta che carica il Vettore per testare i filtri
	 * @return ritorna al chiamante true se è stato caricato
	 */
	@GetMapping("/loadFilterTest")
	public boolean loadTest() {
		boolean response = false;
		try {
			if(loader.loadTest())
				response = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	/**
	 * Rotta che avvia il salvataggio delle previsoni ogni ora
	 * @param cityName città di cui prendere le previsioni
	 */
	@GetMapping("/startCurrentService")
	public boolean currentForecast(@RequestParam(value="nome", defaultValue="Rome") String cityName) {
		try {
			currentForecast.ripetizioneMetodo(cityName);	
		}catch (IOException | ParseException e) {
			return false;
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * Rotta che ferma il salvataggio delle previsioni ogni ora
	 */
	@GetMapping("/stopCurrentService")
	public boolean currentForecastStop(Timer timer) {
		try {
			currentForecast.stopTimer();	
		}catch (StopNotValidException e) {
			return false;
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * Rotta per il filtraggio delle previsioni su una fascia oraria nei giorni compresi 
	 * delle date passate 
	 * @param initialValue ora iniziale di inizio filtraggio 
	 * @param finalValue ora finale del filtraggio 
	 * @param initialValueInDay data di inizio filtraggio 
	 * @param finalValueInDay data di fine filtraggio
	 * @return oggetto Statistiche degli elementi filtrati
	 */
	@GetMapping("/filter/{initialValueInDay}/{finalValueInDay}/{initialValue}/{finalValue}")
	public CurrentStats dailyBand(@PathVariable String initialValueInDay, @PathVariable String finalValueInDay,
								  @PathVariable String initialValue, @PathVariable String finalValue) {
		CurrentStats currentStats = null;
		try {		
			currentStats = filters.dailyFilter(initialValueInDay, finalValueInDay, initialValue, finalValue);
		}catch(IllegalArgumentException | IllegalTimeException e) {
			return null;
		}catch (Exception e){
			return null;
		}
		return currentStats;
	}
	

}	
	
	
	
	
	

