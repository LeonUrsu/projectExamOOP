package it.uni.main.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import it.uni.main.model.Humidity;



@RestController
public class OpenWeatherController 
{
//	@Autowired
//	private OpenWeatherService weatherservice;
//	
	
	@RequestMapping("/ciao")
	  public Humidity T()
	  {
		  return new Humidity(12);
	  }
	
//	//Si puo aggiungere qui la rilevazione dell'ip per la previsione se non si passa un parametro
//	@GetMapping("/getForecast")
//	public JSONObject forecast5day(@RequestParam(value="nome", defaultValue="Rome") String nome) {
//		//return weatherservice.forecast5day(nome);
//		return new JSONObject();
//		
		
		
	 
	}
	

	
	
	
	
	
	

