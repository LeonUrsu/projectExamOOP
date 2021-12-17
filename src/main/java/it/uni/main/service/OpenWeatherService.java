package it.uni.main.service;

import org.json.simple.JSONObject;

public interface OpenWeatherService 
{
	public abstract JSONObject toJSONObject(String toConvert);
	public abstract JSONObject callApi(String myUrl);
	public abstract JSONObject forecast5day(String name); 
}
