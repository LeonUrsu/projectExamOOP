package it.uni.main.service;

import java.util.Vector;

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
	public static Vector<Forecast5Days> forecast5DaysVec = null; 
	
	
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Vector<Forecast5Days> forecast5day(String name) {
		JSONObject oggettoJ = leggiJsondaFile("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\50dayforecast.txt");;
		//"C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\50dayforecast.txt"
		//creo un vettore con 40 previsoni e un altro vuoto
		JSONArray forecasts40 = new JSONArray();
		forecasts40 = (JSONArray) oggettoJ.get("list");
		JSONArray forecasts5 = new JSONArray();
		
		//carico il vettore vuoto con 5 previsioni ogni 24 ore a partire dalla chiamata dell'api
		for(int i=0, u=forecasts40.size()  ;  i<u  ;  i+=8) 
			forecasts5.add(forecasts40.get(i));
		
		//filtra il JSONArray con 5 elementi, li converte in Forecast5Days Array e li salva in modo statico
		Vector<Forecast5Days> forecast5DaysVec = new Vector<Forecast5Days>();
		for(int i=0, u=forecasts5.size()  ;  i<u  ; i++) {
			JSONObject tmpObj;
			tmpObj = (JSONObject) forecasts5.get(i);
			System.out.println("------------" + tmpObj);
			Humidity humidity = new Humidity(Integer.parseInt((tmpObj.get("humidity")).toString()));
			
			Forecast5Days javaObj = new Forecast5Days(humidity);
			forecast5DaysVec.add(javaObj);
		}
		
	
		
		
		return forecast5DaysVec;
	}
	
	
	
	
	
	
}
