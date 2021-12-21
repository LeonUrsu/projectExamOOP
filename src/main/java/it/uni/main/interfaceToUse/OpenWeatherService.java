package it.uni.main.interfaceToUse;

import org.json.simple.JSONObject;

public interface OpenWeatherService 
{
	public abstract JSONObject toJsonObject(String toConvert);
	public abstract JSONObject callApi(String myUrl);
	
	
}
