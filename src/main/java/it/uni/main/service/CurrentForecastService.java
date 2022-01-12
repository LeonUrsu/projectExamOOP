package it.uni.main.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.stereotype.Service;

import it.uni.main.exception.StopNotValidException;
import it.uni.main.model.City;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;
import it.uni.main.utils.ParamVariable;


/**
 * Classe servizio della parte del progetto che riguarda il salvataggio ogni ora delle previsioni meteo su 
 * un file locale e il loro aggiornamento una volta che hanno superato il valore ParamVariable.currentVectorSize
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class CurrentForecastService extends OpenWeatherServiceImp{
	
	
	
	/**
	 * vettore che aumenterà dimensionalmente ogni 3600s (60 minuti) di un nuovo 
	 * elemento ForecastDataCurrent fino a raggiungere un valore massimo 
	 * stabilito dal programmatore tramite il PARAMETRO PROGRAMMATORE
	 */
	public static Vector<ForecastDataCurrent> forecastDataCurrentVector = new Vector<ForecastDataCurrent>();

	
	
	/**
	 * metodo che aggiorna il file locale con una nuova previsione (root)
	 * 
	 * @param name - nome della città
	 */
	Timer timer = null;
	public void ripetizioneMetodo(String name)  throws ParseException, IOException {
		if(timer == null) {
		    TimerTask task = new TimerTask() {
		        public void run() {
			        try {
			        	forecastCurr(name);
					}catch(ParseException e) {
							e.printStackTrace();
					}catch(IOException e) {
							e.printStackTrace();
					}
		        }
		   };
		   timer = new Timer("Timer");
		   timer.scheduleAtFixedRate(task,1000,1000 * 3);//0 * 60);
	   }
	}
	
	
	
	/**
	 * Metodo per fermare il Timer (root)
	 * @throws StopNotValidException 
	 */
	public void stopTimer() throws StopNotValidException {
		
		if(timer == null) 
			throw new StopNotValidException(); 
		else {
			timer.cancel();
			timer = null;
		}
	}
	

	
	/**
	 * Metodo che serializza su file locale in formato JSON un array di oggetti java di tipo Vector<ForecastDataCurrent>
	 * ogni 3600s (60 minuti) e riempie il ForecastDataCurrentVector della classe
	 * @param cityName - nome della città passata 
	 * @throws ParseException
	 * @throws IOException
	 */	
	private void forecastCurr(String cityName) throws ParseException, IOException {
		String fileName = "currentForecastData{City}.json";
		fileName = fileName.replaceAll("\\{City\\}", cityName);
		checkVector(cityName, fileName, forecastDataCurrentVector);
		//Creazione del JAVA Object dal JSONObject
		JsonObject oggettoJ = callApi(ApiReference.UrlCurrP1 + cityName + ApiReference.UrlCurrP2);
		JsonObject tmp = oggettoJ.getAsJsonObject("main");
		Temperature temperature = new Temperature(tmp.get("temp").getAsDouble(),
												tmp.get("temp_min").getAsDouble(),
												tmp.get("temp_max").getAsDouble(),
												tmp.get("feels_like").getAsDouble());	
		Humidity humidity = new Humidity(tmp.get("humidity").getAsInt());
		long dt = oggettoJ.get("dt").getAsLong();
		tmp = oggettoJ.getAsJsonObject("coord");
		City city = new City(tmp.get("lon").getAsFloat(),
							 tmp.get("lat").getAsFloat(),
							 oggettoJ.get("id").getAsInt(),
							 oggettoJ.get("name").getAsString());
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity,temperature,dt,city);
		//Caricamento dal file delle previsioni Current e posizionamento su ForecastCurrentVector
		if(forecastDataCurrentVector.size() < ParamVariable.currentVectorSize)    //PARAMETRO PROGRAMMATORE
			forecastDataCurrentVector.add(javaObj);
		else {
			forecastDataCurrentVector.remove(0);
			forecastDataCurrentVector.add(javaObj);
		}	//Salvataggio degli elementi dalla memoria volatile sulla memoria di massa
		salvaSuFile(fileName, forecastDataCurrentVector);
	}
	
	
	
	/**
	 * Metodo che controllase il vettore è pieno oppure vuoto e crea file a seconda se è pieno
	 * @param fileName
	 * @param forecastDataCurrentVector
	 * @throws IOException
	 */
	public void checkVector(String cityName, String fileName, Vector<ForecastDataCurrent> forecastDataCurrentVector) throws IOException {
		if(forecastDataCurrentVector.isEmpty()){
			if(checkOrCreateFile(fileName)) return;//false se file esistente 
			else readVectorFromFile(fileName, forecastDataCurrentVector);
		}
		else if(compareId(cityName, forecastDataCurrentVector))return;//false se id diversi
			 else {
				 forecastDataCurrentVector.removeAllElements();
				 if(checkOrCreateFile(fileName)) return;//false se file esistente 
				 else readVectorFromFile(fileName, forecastDataCurrentVector);
			 }  
	}
	
	

	/**
	 * metodo che carica un vettore di oggetti da un file  nomeFile e lo carica su un vettore
	 * @param nomeFile - file locale
	 * @param vettore 
	 */
	public void readVectorFromFile(String nomeFile, Vector<ForecastDataCurrent> vettore){
		try{
			Scanner scr = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			String inJSON = "";
			while(scr.hasNext())
				inJSON += scr.nextLine();
			Gson gson = new Gson();
			Vector<ForecastDataCurrent> tmpVec = (gson.fromJson(inJSON, new TypeToken<Vector<ForecastDataCurrent>>(){}.getType()));
			if(tmpVec.size() != 0)
				vettore.addAll(tmpVec);
		}
		catch(Exception e){
			System.out.println("apertura file " + nomeFile + " non riuscita"  );
		}
	}
	
	

	/**
	 * metodo per comparare gli ID di due città, una presente nel vettore e la seconda nel cityName
	 * @param cityName - da cui estrarre l'ID
	 * @param vettore - da cui estrarre l'ID2
	 * @return
	 */
	private boolean compareId(String cityName, Vector<ForecastDataCurrent> vettore ) {
		JsonObject oggettoJ = callApi(ApiReference.UrlCurrP1 + cityName + ApiReference.UrlCurrP2);
		int ID = oggettoJ.get("id").getAsInt();
		int ID2 = vettore.lastElement().getCity().getID();
		if(ID == ID2)
			return true;
		else
			return false;
	}
	
	
	
	@SuppressWarnings("unused")
	/**
	 * metodo che svuota file locale
	 * @param nomeFile parametro formale
	 */
	private void clearFileLocale(String nomeFile) {
		try{
			FileWriter FW = new FileWriter(nomeFile);
			FW.write("");
			FW.close();
	}catch(IOException e){
		e.printStackTrace();
		}
	}

		
	/**
	 * Metodo che passando un oggetto Vector come parametro locale lo salva su un file di nome nomeFile
	 * @param nomeFile - file locale
	 * @param vettore
	 * @throws IOException
	 */
	 private void salvaSuFile(String nomeFile,Vector<ForecastDataCurrent> vettore) throws IOException{
		PrintWriter oss = null;
		try{
			oss = new PrintWriter(new BufferedWriter(new FileWriter(nomeFile)));
			Gson gson = new Gson();
			String inJSON = gson.toJson(vettore);
			oss.print(inJSON);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			oss.close();
			System.out.println("salvati " + forecastDataCurrentVector.size() + " elementi su file: " + nomeFile + "   " + super.CurrentTime());
		}			
	}
	
	 
	 
	 /**
	  * Metodo per creare un nuovo fle con il nome della città
	  */
	 private boolean checkOrCreateFile(String nomeNuovoFile) throws IOException{
	      File myObj = new File(nomeNuovoFile);
	      if (myObj.createNewFile()) {
	    	  System.out.println("File creato: " + myObj.getName());
	    	  return true;
	      } 
	      else {
	    	  System.out.println("il File esiste già");
	    	  return false;
	      }
	}
 

	
	
	@SuppressWarnings("unused")
	@Deprecated //in data 30/12/21 dopo modifica a ForecastCurr
	/**
	 * Metodo che ci permette di caricare sul Vector gli oggetti (se e solo se) esistenti già nel file locale 
	 *
	 * @param vettore su cui sincronizzare
	 * @param toSinc da inizializzare
	 * @return
	 */
	private void sincronizzaElementi(Vector<ForecastDataCurrent> vettore, Vector<ForecastDataCurrent> toSinc, String name ){
		if(compareId(name, toSinc)){
		for(int i=0, u=toSinc.size() ; i<u ; i++) {	//insertElementAt(E obj, int index) toUSE
			ForecastDataCurrent tmpFor = toSinc.get(i);
			Temperature temperature = new Temperature(tmpFor.getTemperature().getTemp(),
													  tmpFor.getTemperature().getTempMin(),
													  tmpFor.getTemperature().getTempMax(),
													  tmpFor.getTemperature().getTempFeel());
			Humidity humidity = new Humidity(tmpFor.getHumidity().getValue());
			long dt = tmpFor.getDayTime();
			City city = new City(tmpFor.getCity().getLat(),
								 tmpFor.getCity().getLon(),
								 tmpFor.getCity().getID(),
								 tmpFor.getCity().getCityName());
			tmpFor = new ForecastDataCurrent(humidity, temperature, dt,	city);
			vettore.insertElementAt(tmpFor, i);
		}
	 }
	}
	
	
	/**
	 * sei inutile
	 * @param fileOutputStream
	 * @return
	 */
	@SuppressWarnings("unused")
	private ObjectOutputStream BufferedOutputStream(FileOutputStream fileOutputStream) {
		// TODO Auto-generated method stub
		return null;
	}
		
}