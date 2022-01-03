package it.uni.main.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;


import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import it.uni.main.interfaceToUse.OpenWeatherService;
import it.uni.main.model.ForecastDataCurrent;





@Service
public class OpenWeatherServiceImp implements OpenWeatherService{
	
	
	/**
	 * metodo per chiamare un API tramite url con return del JSON ricevuto dall'API
	 * 
	 * @param myUrl API di collegamento
	 * @return String JSON
	 */
	@Override
	public JsonObject callApi(String myUrl) 
	{
		JsonObject Jobject = new JsonObject();
		try {
			URLConnection openConnection = new URL(myUrl).openConnection();
			InputStream in = openConnection.getInputStream();//QUI ECCEZIONE
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
			Gson gson = new Gson();
			Jobject = gson.fromJson(data, JsonObject.class);		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Jobject;
	}
	

	
	/**
	 * metodo che carica un vettore di oggetti da un file  nomeFile e lo carica su un vettore
	 * @param nomeFile - file locale
	 * @param vettore 
	 */
	public String readStringFromFile(String nomeFile){
		String inJSON = "";
		try{
			Scanner scr = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			while(scr.hasNext()) 
				inJSON += scr.nextLine();
		}
		catch(Exception e){
			System.out.println("apertura file " + nomeFile + " non riuscita"  );
		}
	return inJSON;
	}
	
	
	
	
	@Deprecated
	@Override
	/*
	 * metodo che converte un file txt con JSON e restituisce un oggetto JSONObject
	 * @param myFile - file con jSON txt 
	 */
	public JsonObject leggiJsonDaFile(String myFile)
	{
		JsonObject Jobject = null;
		String data = "";
		String line = "";
		try {
		   FileReader FR = new FileReader(myFile);
		   BufferedReader buf = new BufferedReader( FR );
		   while ( ( line = buf.readLine() ) != null ) {
			   data+= line;
		   }
		   buf.close();
		   Gson gson = new Gson();
		   Jobject = gson.fromJson(data, JsonObject.class);
		}
		 catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Jobject;
	}
	
	
	
	@Override
	@Deprecated
	/**
	 * Metodo per creare un file txt vuoto
	 */
	public void CreateTxtFile() {
		String fileName = "my-file.txt";
	    String encoding = "UTF-8";
	    try{
	    	PrintWriter writer = new PrintWriter(fileName, encoding);
	    	writer.close();
	    }
	    catch (IOException e){
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}


	
	
	
	
	/**
	 * Metodo che restituisce la data nel formato desiderato
	 * @return data
	 */
	public String CurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}




	
	
}
