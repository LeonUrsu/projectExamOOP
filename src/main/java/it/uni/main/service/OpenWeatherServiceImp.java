package it.uni.main.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import java.net.URLConnection;
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
		JSONObject Jobject= new JSONObject();

		try {
				
			URLConnection openConnection = new URL(myUrl).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data = "";
			String line = "";
			try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
			   in.close();
			}
				Jobject = (JSONObject) JSONValue.parseWithException(data);	 
				
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Jobject;
	}


	
	/**
	 * metodo che che converte oggetto di tipo String in tipo JsonObject
	 * @param toConvert oggetto di tipo String
	 */
	@Override
	public	JSONObject toJsonObject(String toConvert) 
	{
		
		//JSONParser parser = new JSONParser();
		//Object obj = parser.parse()
		
		
		
		return null;
	}

	
	public JSONObject leggiJsondaFile(String myFile)
	{
		JSONObject Jobject = null;
		String data = "";
		String line = "";
		try {
		   FileReader FR = new FileReader(myFile);
		   BufferedReader buf = new BufferedReader( FR );
		  
		   while ( ( line = buf.readLine() ) != null ) {
			   data+= line;
		   }
		   buf.close();
		   Jobject = (JSONObject) JSONValue.parseWithException(data);
		}
	 catch (IOException | ParseException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Jobject;
	}
	
	
	
	
	
	
	
}
