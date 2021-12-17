package it.uni.main.service;

import org.json.simple.JSONObject;

public interface OpenWeatherService 
{
	public abstract String callApi(String myUrl);
	public abstract JSONObject forecast5day(String nome);
	
}
