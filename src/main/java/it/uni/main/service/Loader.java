package it.uni.main.service;

import java.util.Random;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.statisticsAndFilters.Filters;
import it.uni.main.utils.ParamVariable;


/**
 * Classe servizio pre il caricamento di elementi da un file locale  sul 
 * CurrentForecastService.toFilerVector in Filters per poter effettuare il filtraggio
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Loader extends OpenWeatherServiceImp{
	/**
	 * Metodo che richiamato, carica un static Vector ForecastDataCurrent di elementi dal file
	 * temp\\currentForecastData{City}.json 
	 * @return true se caricato, false se non caricato
	 * @throws Exception
	 */
	public boolean load(String cityName)throws Exception, JsonSyntaxException{
		Filters.toFilterVector.removeAllElements();
		OpenWeatherServiceImp openWeatherServiceImp = new OpenWeatherServiceImp();
		String fileName = ParamVariable.filePath;
		fileName = fileName.replaceAll("\\{City\\}", cityName);
		String inJson = openWeatherServiceImp.readStringFromFile(fileName);
		if(inJson.isEmpty())
			return false;
		JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		jsonArray = gson.fromJson(inJson, jsonArray.getClass());
		if(jsonArray.size() == 0)
			return false;
		toVectorForecastDataCurrent(jsonArray.toString(), Filters.toFilterVector);	
		return true;
	}
	
	
	
	/**
	 * Metodo che richiamato, carica un static Vector ForecastDataCurrent di elementi dal file
	 * emp\\currentForecastDataTesting.json con dt e altri parametri aggiornati fino agli ultimi 5gg, 
	 * @return true se caricato, false se non caricato
	 * @throws Exception
	 */
	public boolean loadTest()throws Exception, JsonSyntaxException{
		Filters.toFilterVector.removeAllElements();
		OpenWeatherServiceImp openWeatherServiceImp = new OpenWeatherServiceImp();
		String fileName = "temp\\currentForecastDataTesting.json";
		String inJson = openWeatherServiceImp.readStringFromFile(fileName);
		if(inJson.isEmpty())
			return false;
		JsonArray jsonArray = new JsonArray();
		Gson gson = new Gson();
		jsonArray = gson.fromJson(inJson, jsonArray.getClass());
		if(jsonArray.size() == 0)
			return false;
		changeInternalValue(jsonArray,System.currentTimeMillis()/1000);//cambio valore dei 'dt'
		toVectorForecastDataCurrent(jsonArray.toString(), Filters.toFilterVector);	
		return true;
	}
	
	
	
	/*
	 * Metodo che cambia i valori  degli oggetti caricati dal
	 * file in modo da andare, dal momento corrente, fino a 5 giorni prima
	 */
	public void changeInternalValue(JsonArray jsonArray, long seconds){
		int dimArr = jsonArray.size();
		Random random = new Random(System.currentTimeMillis());
		Filters data = new Filters();
		for(long i=0, dt=seconds ; i<dimArr ; i++, dt-=3600) {
			jsonArray.get((int)i).getAsJsonObject().addProperty("dt", dt);
			jsonArray.get((int)i).getAsJsonObject().addProperty("dtString", data.secToData(dt));
			JsonObject jsonObject = jsonArray.get((int)i).getAsJsonObject().get("temperature").getAsJsonObject();
			jsonObject.addProperty("temp", Math.abs(random.nextInt()%30));
			jsonObject.addProperty("tempMax", Math.abs(random.nextInt()%40));
			jsonObject.addProperty("tempMin", Math.abs(random.nextInt()%10));
			jsonObject.addProperty("tempFeel", Math.abs(random.nextInt()%20));
		}
	}
	
	
	
	@Deprecated
	/**
	 * Metodo che crea un Vector<ForecastDataCurrent> in json da un file json non compatibile
	 * @param str
	 * @return
	 */
	public void toJsonForecastDataCurrent(JsonArray jsonArray){
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
		jsonArray= new JsonArray();
		jsonArray.add(newJsonArray.getAsJsonArray());
	}
	
	
	
	/**
	 * Metodo che trasforma una Stringa in oggetto Java Vector<ForecastDataCurrent>
	 * @param inJson
	 * @param vettore
	 */
	private void toVectorForecastDataCurrent(String inJson, Vector<ForecastDataCurrent> vettore){
		Gson gson = new Gson();
		Vector<ForecastDataCurrent> tmpVec = (gson.fromJson(inJson, new TypeToken<Vector<ForecastDataCurrent>>(){}.getType()));
		if(tmpVec.size() != 0)
			vettore.addAll(tmpVec);
	}
	
	
}
