package it.uni.main.controller;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public JSONObject forecast5day(@RequestParam(value="nome", defaultValue="Rome") String nome) {
		return forecast5Day.forecast5day(nome);
	}
	
	
	@GetMapping("/prova")
	public JSONObject currentForecast(@RequestParam(value="nome", defaultValue="Rome") String nome){
		return null;
		
		
	}
	
	
	
	
	
}	
	
	
	
	
	

