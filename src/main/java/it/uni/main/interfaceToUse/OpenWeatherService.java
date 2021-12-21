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
	JSONObject toJsonObject(Object toConvert);
	
	
	
	/**
	 * metodo per chiamare un API tramite url con return del JSON ricevuto dall'API
	 * 
	 * @param myUrl url fonte di previsioni di 5 giorni ogni 3 ore
	 * @return String JSON
	 */
	JSONObject callApiV2(String myUrl);

	
}
