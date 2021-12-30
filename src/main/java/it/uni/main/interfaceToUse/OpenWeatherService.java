package it.uni.main.interfaceToUse;

import com.google.gson.JsonObject;

public interface OpenWeatherService 
{
	public abstract JsonObject toJsonObject(String toConvert);
	public abstract JsonObject callApi(String myUrl);
	
	
}
