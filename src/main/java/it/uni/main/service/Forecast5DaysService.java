package it.uni.main.service;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Humidity;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	private Vector<Forecast5Days> forecast5DaysVec = new Vector<Forecast5Days>();  //riservo spazio statico in memoria per il vettore
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Vector<Forecast5Days> forecast5day(String name) {
		//JSONObject oggettoJ = leggiJsondaFile("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\50dayforecast.txt");
		JSONObject oggettoJ = callApi(ApiReference.Url5dayP1 + name + ApiReference.Url5dayP2);
		
		JSONArray forecasts40 = (JSONArray) oggettoJ.get("list");
		
		//carico il vettore vuoto con previsioni ogni 24 ore a partire dall'ora della chiamata all'api
		for (int i=0, u=forecasts40.size()  ;  i<u  ;  i++) {
			//recuperata l'umidità tramite strati a cipolla
			JSONObject tmpObj = (JSONObject)forecasts40.get(i);
			long tmpDate = Long.parseLong(tmpObj.get("dt").toString());//prendo data e ora della previsione
			tmpObj = (JSONObject) tmpObj.get("main");
			Humidity humidity = new Humidity(Integer.parseInt(tmpObj.get("humidity").toString()));
			Forecast5Days tmpForecast = new Forecast5Days(humidity,tmpDate);
			forecast5DaysVec.add(tmpForecast);	
		}
		return forecast5DaysVec; 
	}
	
	
	
	
	
	
}