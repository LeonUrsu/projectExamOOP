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
	
	
<<<<<<< HEAD
=======
	
	
>>>>>>> 7b83ff9f5aca71a94cc963594c51dcfcb2c47d89
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public Vector<Forecast5Days> forecast5day(String name) {
<<<<<<< HEAD
		
		JSONObject oggettoJ = leggiJsondaFile("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\50dayforecast.txt");
		forecast5DaysVec = new Vector<Forecast5Days>(); //riservo spazio statico in memoria per il vettore
		JSONArray forecasts40 = new JSONArray();
		forecasts40 = (JSONArray) oggettoJ.get("list");
		
		//carico il vettore vuoto con 5 previsioni ogni 24 ore a partire dalla chiamata dell'api
		for (int i=0, u=forecasts40.size()  ;  i<u  ;  i+=8)
		{	
			//recuperata l'umidità tramite strati a cipolla
			JSONObject tmpObj = (JSONObject)forecasts40.get(i);
			String tmpDate = (String) tmpObj.get("dt_txt"); //data e ora della previsione
			System.out.println(tmpDate);
			tmpObj = (JSONObject) tmpObj.get("main");
			long humidityLong = (long)tmpObj.get("humidity");
			System.out.println((int)humidityLong);
			Humidity humidity = new Humidity((int)humidityLong);
			
			Forecast5Days tmpForecast = new Forecast5Days(humidity, tmpDate);
			
			forecast5DaysVec.add(tmpForecast);
		}
		
=======
		JSONObject oggettoJ = leggiJsondaFile("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\response5Days.json");;
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
		for(int i=0 ;  i< forecasts5.size()  ; i++) {
			
			JSONObject tmpObj = (JSONObject) forecasts5.get(i);
			System.out.println("------------" + tmpObj);
			
			System.out.println(tmpObj.get("temp"));
			Humidity humidity = new Humidity(Integer.parseInt(tmpObj.get("humidity").toString()));
			
			Forecast5Days javaObj = new Forecast5Days(humidity);
			forecast5DaysVec.add(javaObj);
		}
		
	
		
		
>>>>>>> 7b83ff9f5aca71a94cc963594c51dcfcb2c47d89
		return forecast5DaysVec;
	}
	
	
	
	
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 7b83ff9f5aca71a94cc963594c51dcfcb2c47d89
