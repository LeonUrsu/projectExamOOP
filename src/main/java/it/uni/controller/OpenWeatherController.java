package it.uni.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uni.service.OpenWeatherService;



@RestController
public class OpenWeatherController 
{
	@Autowired
	private OpenWeatherService weatherservice;
	 
	@GetMapping("/getForecast")
	public JSONObject forecast5day(@RequestParam(value="nome", defaultvalue="Rome") String nome) {
		return weatherservice.forecast5day(nome);
	}
	

	
	
	
	
	
	
}
