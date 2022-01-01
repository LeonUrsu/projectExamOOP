package it.uni.main.service;

import java.util.Vector;


import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.uni.main.model.City;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Humidity;
import it.uni.main.model.Stats5Days;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	/**
	 * Vector interno alla classe
	 */
	private Vector<Forecast5Days> forecast5DaysVec = new Vector<Forecast5Days>();  //riservo spazio statico in memoria per il vettore
	
	
	
	/**
	 * Metodo getter del Vector inteno alla classe
	 * @return
	 */
	public Vector<Forecast5Days> getForecast5DaysVec() {
		return forecast5DaysVec;
	}


	
	/**
	 * Metodo che chiama l'api e salva temporanemente mella memoria le previsioni 
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Stats5Days forecast5day(String cityName) {
		JsonObject oggettoJ = callApi(ApiReference.Url5dayP1 + cityName + ApiReference.Url5dayP2);
		JsonArray forecasts40 = oggettoJ.getAsJsonArray("list"); 
		JsonObject Jcity = oggettoJ.getAsJsonObject("city");	
		float lat = Jcity.getAsJsonObject("coord").get("lat").getAsFloat();
		float lon = Jcity.getAsJsonObject("coord").get("lon").getAsFloat();
		int id =   Jcity.get("id").getAsInt();
		String name =  Jcity.get("name").getAsString();
		City city = new City(lat, lon, id, name);
		for (int i=0, u=forecasts40.size()  ;  i<u  ;  i++) {
			JsonObject tmpObj = forecasts40.get(i).getAsJsonObject();
			long tmpDate = tmpObj.get("dt").getAsLong();   //prendo data e ora della previsione
			tmpObj = tmpObj.get("main").getAsJsonObject();
			Humidity humidity = new Humidity(tmpObj.get("humidity").getAsInt());
			forecast5DaysVec.add(new Forecast5Days(humidity,tmpDate));	
		}
		System.out.println("-->" + forecast5DaysVec.size());
		return new Stats5Days(city, forecast5DaysVec); 
	}
	
	
	
	
	
}