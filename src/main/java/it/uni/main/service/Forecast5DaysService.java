package it.uni.main.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public JSONObject forecast5day(String name) {
		
		//JSONObject oggettoJ = ;
		//JSONArray arrayJ = new JSONArray();
		//arrayJ = (JSONArray) oggettoJ.get("list");
		//System.out.println(arrayJ);
		
		
		
		//Todo salvare i valori in una classe in un database in modo che si possa accedere nuovamente a loro
		//return callApi(ApiReference.Url5day.replace("{city name}", name));
		return callApi("api.openweathermap.org/data/2.5/weather?q=Rome&appid=57b8994a4dd46d6b80a3f810f6882a2f");
	}
	
	
	
	
	
	
}
