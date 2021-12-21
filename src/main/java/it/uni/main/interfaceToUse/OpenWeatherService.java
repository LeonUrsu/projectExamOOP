package it.uni.main.interfaceToUse;

import org.json.simple.JSONObject;

public interface OpenWeatherService 
{
	public abstract JSONObject toJsonObject(String toConvert);
	public abstract JSONObject callApi(String myUrl);
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	JSONObject toJsonObject(Object toConvert);

	
}
