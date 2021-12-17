package it.uni.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import it.uni.main.model.ApiReference;

@Service
public class OpenWeatherServiceImp implements OpenWeatherService{
	
	
	
	
	/**
	 * 
	 */
	@Override
	public JSONObject forecast5day(String nome) {
		JSONParser Jparser = new JSONParser();
		JSONObject JForecast5day = null;
		ApiReference apiObj = new ApiReference();
		try {
			JForecast5day = (JSONObject) Jparser.parse(callApi(apiObj.Url5day));
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		  }
	return JForecast5day;
	}
	
	
	
	
	
	
	
	/**
	 * metodo per chiamare un API tramite url con return del JSON ricevuto dall'API
	 * 
	 * @param myUrl url fonte di previsioni di 5 giorni ogni 3 ore
	 * @return String JSON
	 */
	public String callApi(String myUrl) {
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
	return letto;
	}

	
	
	
	
	
	
	
	
	
	
	
}
