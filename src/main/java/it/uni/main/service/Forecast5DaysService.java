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



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	private static Forecast5DaysHumidity forecast5DaysHumidity ;  //riservo spazio statico in memoria per il vettore
	
	
	/**
	 * Metodo getter del Vector inteno alla classe
	 * @return
	 */
	public Forecast5DaysHumidity getForecast5Days() {
		return forecast5DaysHumidity;
	}
	
	
	
	/**
	 * Metodo che chiama l'api e salva temporanemente mella memoria le previsioni 
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Vector<Forecast5Days> forecast5day(String cityName) {
		Vector<Forecast5Days> vettore = new Vector<Forecast5Days>();
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
			vettore.add(new Forecast5Days(humidity,tmpDate));	
		}
		forecast5DaysHumidity = new Forecast5DaysHumidity(city, vettore);
		return forecast5DaysHumidity.getForecast5DaysVectorHum();
	}
	
	
	
	
	
}