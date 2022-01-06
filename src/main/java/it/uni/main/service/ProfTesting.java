package it.uni.main.service;

import java.util.Vector;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import it.uni.main.model.ForecastDataCurrent;
<<<<<<< HEAD
import it.uni.main.statisticsAndFilters.Filters;
=======
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb



@Service
public class ProfTesting extends OpenWeatherServiceImp{
	

	/**
<<<<<<< HEAD
	 * Metodo che richiamato, carica un static Vector ForecastDataCurrent di elementi dal file
	 *  'daysHistory,json' con dt aggiornato, 
=======
	 * Metodo che richiamato, carica un static Vector ForecastDataCurrent di elementi
	 * con dt aggiornato ma con gli altri parametri lasciati invariati
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
	 * @return true se caricato, false se non caricato
	 * @throws Exception
	 */
	public boolean writeHistoryWeather()throws Exception{
<<<<<<< HEAD
		CurrentForecastService.forecastDataCurrentVector.removeAllElements();
		OpenWeatherServiceImp openWeatherServiceImp = new OpenWeatherServiceImp();
		String inJson = openWeatherServiceImp.readStringFromFile("daysHistory.json");
		if(inJson.isEmpty())
			return false;
=======
		//CurrentForecastService currentForecastService = new CurrentForecastService();
		CurrentForecastService.forecastDataCurrentVector.removeAllElements();
		OpenWeatherServiceImp openWeatherServiceImp = new OpenWeatherServiceImp();
		String inJson = openWeatherServiceImp.readStringFromFile("currentForecastData.json");
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
		JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		jsonArray = gson.fromJson(inJson, jsonArray.getClass());
		if(jsonArray.size() == 0)
			return false;
<<<<<<< HEAD
		changeDtTime(jsonArray,System.currentTimeMillis()/1000); //cambio valore dei 'dt'		
		toVectorForecastDataCurrent(jsonArray.toString(), CurrentForecastService.forecastDataCurrentVector);	
		return true; // se true, sono stati caricati 89 elementi sul Vector
	}

=======
		else {
			changeDtTime(jsonArray,System.currentTimeMillis());	//cambio valore dei 'dt'
		//jsonArray = toForecastDataCurrentJson(jsonArray);
		toVectorForecastDataCurrent(jsonArray.toString(), CurrentForecastService.forecastDataCurrentVector);
		//toVectorForecastDataCurrent(jsonArray.toString(), CurrentForecastService.forecastDataCurrentVector);
		return true;
		}
	}
	
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
	
	
	/*
	 * Metodo che cambia i valori 'dt' degli oggetti caricati dal
	 * file in modo da andare dal momento corrente fino a 5 giorni prima
	 */
<<<<<<< HEAD
	public void changeDtTime(JsonArray jsonArray, long seconds){
		int dimArr = jsonArray.size();
		for(long i=0, dt=seconds ; i<dimArr ; i++, dt-=3600)
=======
	public void changeDtTime(JsonArray jsonArray, long millis){
		int dimArr = jsonArray.size();
		for(long i=0, dt=millis ; i<dimArr ; i++, dt-=86400)
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
			jsonArray.get((int)i).getAsJsonObject().addProperty("dt", dt);
	}
	
	
<<<<<<< HEAD
	
	
	@Deprecated
	/**
	 * Metodo che crea un Vector<ForecastDataCurrent> in json da un file json non compatibile
	 * @param str
	 * @return
	 */
	public void toJsonForecastDataCurrent(JsonArray jsonArray){
=======
	/**
	 * Metodo che crea un Vector<ForecastDataCurrent> da un file json non compatibile
	 * @param str
	 * @return
	 */
	public JsonArray toForecastDataCurrentJson(JsonArray jsonArray){
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
		JsonArray newJsonArray = new JsonArray();
		
		for(int i=0, u=jsonArray.size() ; i<u ; i++) {
		JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
		
		JsonObject tmpJson = new JsonObject();	
		JsonObject jsonTemperature = new JsonObject();
		jsonTemperature.addProperty("temp", jsonObject.get("temp").getAsDouble());  	
		jsonTemperature.addProperty("temp_min", 111);
		jsonTemperature.addProperty("temp_max", 333);
		jsonTemperature.addProperty("feels_like", 222);
		tmpJson.add("temperature", jsonTemperature);
		JsonObject jsonHumidity = new JsonObject();
		jsonHumidity.addProperty("value", jsonObject.get("humidity").getAsInt());
		tmpJson.add("humidity", jsonHumidity);
		JsonObject jsonCity = new JsonObject();
		jsonCity.addProperty("lon", 13.8512);
		jsonCity.addProperty("lat", 41.1177);
		jsonCity.addProperty("ID", 4219762);
		jsonCity.addProperty("name", "Rome");
		tmpJson.add("city", jsonCity);
		tmpJson.addProperty("dt", jsonObject.get("dt").getAsLong());
		newJsonArray.add(tmpJson);
		}
<<<<<<< HEAD
		jsonArray= new JsonArray();
		jsonArray.add(newJsonArray.getAsJsonArray());
=======
	return newJsonArray;
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
	}
	
	
	
	/**
	 * Metodo che trasforma una Stringa in oggetto Java
	 * @param inJson
	 * @param vettore
	 */
<<<<<<< HEAD
	private void toVectorForecastDataCurrent(String inJson, Vector<ForecastDataCurrent> vettore){
=======
	@SuppressWarnings("unused")
	private void toVectorForecastDataCurrent(String inJson,Vector<ForecastDataCurrent> vettore){
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
		Gson gson = new Gson();
		Vector<ForecastDataCurrent> tmpVec = (gson.fromJson(inJson, new TypeToken<Vector<ForecastDataCurrent>>(){}.getType()));
		if(tmpVec.size() != 0)
			vettore.addAll(tmpVec);
	}
	
	
}
