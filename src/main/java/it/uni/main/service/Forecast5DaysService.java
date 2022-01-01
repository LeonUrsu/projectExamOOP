package it.uni.main.service;

import java.util.Vector;


import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.uni.main.model.City;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Humidity;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	private Vector<Object> forecast5DaysVec = new Vector<Object>();  //riservo spazio statico in memoria per il vettore
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni su cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Vector<Object> forecast5day(String cityName) {

		System.out.println("print name cuty "+ cityName);
		JsonObject oggettoJ = callApi(ApiReference.Url5dayP1 + cityName + ApiReference.Url5dayP2);
		JsonArray forecasts40 = oggettoJ.getAsJsonArray("list");   
//		JsonObject city = oggettoJ.getAsJsonObject("city");
//		City città = new City(city.getAsJsonObject("coord").getAsJsonObject("lat"),
//							  city.getAsJsonObject("coord").getAsJsonObject("lon"),
//							  city.getAsJsonObject("id"),
//							  city.getAsJsonObject("name")
//							 );
		//carico il vettore vuoto con previsioni ogni 24 ore a partire dall'ora della chiamata all'api
		for (int i=0, u=forecasts40.size()  ;  i<u  ;  i++) {
			JsonObject tmpObj = forecasts40.get(i).getAsJsonObject();
			long tmpDate = Long.parseLong(tmpObj.get("dt").toString());//prendo data e ora della previsione
			tmpObj = tmpObj.get("main").getAsJsonObject();
			Humidity humidity = new Humidity(Integer.parseInt(tmpObj.get("humidity").toString()));
			forecast5DaysVec.add(new Forecast5Days(humidity,tmpDate));	
		}
//		forecast5DaysVec.add(city);
		return forecast5DaysVec; 
	}
	
	
	
	
	
	
}