package it.uni.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;


@Service
public class OpenWeatherServiceImp {
	

	
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
//			if(isObject) 
//				return (JSONObject) JSONValue.parseWithException(letto);	 //parse JSON Object	
//			else 
//				return (JSONArray) JSONValue.parseWithException(letto);	//parse JSON Array
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
