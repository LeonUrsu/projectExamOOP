package it.uni.main.controller;


import java.io.IOException;
import java.util.Vector;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uni.main.model.Forecast5Days;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.service.Forecast5DaysService;


@RestController
public class OpenWeatherController 
{
	@Autowired
	private Forecast5DaysService forecast5Day;
	@Autowired 
	private CurrentForecastService currentForecast;
	
	
	//Si puo aggiungere qui la rilevazione dell'IP per la previsione se non si passa 
	//un parametro nome della citta
	@GetMapping("/getForecast")
	public Vector<Forecast5Days> forecast5day(@RequestParam(value="nome", defaultValue="Rome") String nome) {
		return forecast5Day.forecast5day(nome);
	}
	
	
	/**
	 * Rotta che avvia il salvataggio delle previsoni ogni ora
	 * @param nome città di cui prendere le previsioni
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping("/startCurrentService") //si potrebbe aggiungere un periodo di funzionamento
	public void currentForecast(@RequestParam(value="nome", defaultValue="Rome") String nome) throws IOException, ParseException{
	currentForecast.ripetizioneMetodo(nome);	
	}
	
	
	
	/**
	 * Rotta che ferma il salvataggio delle previsioni
	 */
	@GetMapping("/stopCurrentService")
	public void currentForecastStop(){
	}
	
	
	
	/**
	 * Rotta per il filtraggio della banda oraria giornaliera in base ai parametri formali passati
	 * @param initialValue ora iniziale di inizio filtraggio
	 * @param finalValue ora finale del filtraggio
	 * @param days giorni di  filtraggio, se 0 restituisce 
	 * @return vettore di previsioni meteo in base ai parametri passati
	 */
	@GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}")
	public Vector<ForecastDataCurrent>  dailyBand(@PathVariable long initialValue, @PathVariable long finalValue, @PathVariable long days){
		return null;
	}
	
	
	
	/**
	 * Rotta per il filtraggio della banda oraria dell'ultima settimana
	 * @param initialValue
	 * @param finalValue
	 * @return
	 */
	@GetMapping("/filter/weekly/{initialValue}/{finalValue}")
	public Vector<ForecastDataCurrent> weeklyBand(@PathVariable long initialValue, @PathVariable long finalValue){
		return null;
	}
	

}	
	
	
	
	
	

