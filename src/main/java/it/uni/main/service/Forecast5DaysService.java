package it.uni.main.service;

import java.util.Vector;


import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.uni.main.model.City;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Humidity;
import it.uni.main.model.Forecast5DaysHumidity;
import it.uni.main.utils.ApiReference;


/**
 * Classe servizio per la rilevazione dei dati delle previsioni con all'interno porenente solamente l'umidità
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	public static Forecast5DaysHumidity forecast5DaysHumidity ;  //riservo spazio statico in memoria per il vettore
	
		
	/**
	 * Metodo che chiama l'api e salva temporanemente mella memoria le previsioni 
	 * per poter richiamare una rotta per le statistiche
	 * @param nome della citta su cui cercare le previsioni 
	 */
	public Forecast5DaysHumidity forecast5day(String cityName) {
		JsonObject Jcity = callApi(ApiReference.Url5dayP1 + cityName + ApiReference.Url5dayP2);
		JsonArray forecasts40 = Jcity.getAsJsonArray("list"); 
		Jcity = Jcity.getAsJsonObject("city");	
		City city = getCity(Jcity);
		Vector<Forecast5Days> vettore = new Vector<Forecast5Days>();
		riempimentoVettore(vettore, forecasts40);
		forecast5DaysHumidity = new Forecast5DaysHumidity(city, vettore);
		return forecast5DaysHumidity;
	}
	
	
	
	/**
	 * Metodo che riempie un Vector<Forecast5Days> con oggetti trasformati da JsonArray
	 * @param vettore da riempire
	 * @param forecasts40 da cui trasformare
	 */
	private void riempimentoVettore(Vector<Forecast5Days> vettore, JsonArray forecasts40 ){
		for (int i=0, u=forecasts40.size()  ;  i<u  ;  i++) {
			JsonObject tmpObj = forecasts40.get(i).getAsJsonObject();
			long tmpDate = tmpObj.get("dt").getAsLong();   //prendo data e ora della previsione
			tmpObj = tmpObj.get("main").getAsJsonObject();
			Humidity humidity = new Humidity(tmpObj.get("humidity").getAsInt());
			vettore.add(new Forecast5Days(humidity,tmpDate));	
		}
	}

	
	
	/**
	 * Metodo per estrarre una città da un JsonObject che la contiene
	 * @param Jcity
	 * @return City() object
	 */
	private City getCity(JsonObject Jcity){
		float lat = Jcity.getAsJsonObject("coord").get("lat").getAsFloat();
		float lon = Jcity.getAsJsonObject("coord").get("lon").getAsFloat();
		int id =   Jcity.get("id").getAsInt();
		String name =  Jcity.get("name").getAsString();
		return new City(lat, lon, id, name);
	}
	
	
	
	
}