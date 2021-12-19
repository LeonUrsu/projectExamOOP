package it.uni.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.uni.main.interfaceToUse.OpenWeatherService;




@Service
public class OpenWeatherServiceImp implements OpenWeatherService{
	
	
	
	
	
	
	
	
	

	
	
	/**
	 * metodo per chiamare un API tramite url con return del JSON ricevuto dall'API
	 * 
	 * @param myUrl url fonte di previsioni di 5 giorni ogni 3 ore
	 * @return String JSON
	 */
	@Override
	public JSONObject callApi(String myUrl) {
		String letto = "";
		try {
			URLConnection openConnection = new URL(myUrl).openConnection();
			InputStream myIS = openConnection.getInputStream();
			BufferedReader myBR = new BufferedReader(new InputStreamReader(myIS));	
			try {
				for( String linea = ""  ;  (linea = myBR.readLine()) != null  ;  letto += linea );	
				}
			finally	{	
					myBR.close();	
					}				
			}
		catch(IOException e) {	
			e.printStackTrace();	
		}
		catch (Exception e) {	
			e.printStackTrace();	
		}	
	return toJsonObject(letto);
	}


	
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	@Override
	public	JSONObject toJsonObject(String toConvert){
		JSONObject JObjectParsed = new JSONObject();
		JSONObject jsonObject2 = null;
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject)parser.parse(toConvert);
		jsonObject2 = jsonObject;
		return jsonObject2;
	}

	
	
	
	
	
	
	
	
	
}
