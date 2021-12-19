package it.uni.main.interfaceToUse;

import com.github.cliftonlabs.json_simple.JsonObject;

public interface OpenWeatherService 
{
	public abstract JsonObject toJsonObject(String toConvert);
	public abstract JsonObject callApi(String myUrl);
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */

	
}
