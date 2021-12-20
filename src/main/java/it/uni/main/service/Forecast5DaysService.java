package it.uni.main.service;

import javax.print.attribute.Size2DSyntax;
import it.uni.main.dataDinamic.DataDinamicClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.uni.main.model.Forecast5Days;
import it.uni.main.model.Humidity;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public JSONObject forecast5day(String name) {
		JSONObject oggettoJ = leggiJsondaFile("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\50dayforecast.txt");;
		
		//creo un vettore con 40 previsoni e un altro vuoto
		JSONArray forecasts40 = new JSONArray();
		forecasts40 = (JSONArray) oggettoJ.get("list");
		JSONArray forecasts5 = new JSONArray();
		
		//carico il vettore vuoto con 5 previsioni ogni 24 ore a partire dalla chiamata dell'api
		for(int i=0, u=forecasts40.size()  ;  i<u  ;  i+=8) 
			forecasts5.add(forecasts40.get(i));
		
		//filtra il JSONArray con 5 elementi e li converte in Java Object Array
		//Vector<>
		for(int i=0, u=forecasts5.size()  ;  i<u  ; i++) {
			JSONObject tmpObj = (JSONObject) forecasts5.get(i);
			Humidity humidity = new Humidity((int)tmpObj.get("humidity"));
			Forecast5Days javaObj = new Forecast5Days(humidity);
			forecast5
			
		}

		//Todo salvare i valori in una classe in un database in modo che si possa accedere nuovamente a lor
		
		
		return null;
	}
	
	
	
	
	
	
}
