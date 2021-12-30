package it.uni.main.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
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

import org.springframework.stereotype.Service;

import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.model.City;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	
	
	/**
	 * vettore che aumenterà dimensionalmente ogni 3600s (60 minuti) di un nuovo 
	 * elemento ForecastDataCurrent fino a raggiungere un valore massimo 
	 * stabilito dal programmatore tramite il PARAMETRO PROGRAMMATORE
	 */
	public Vector<ForecastDataCurrent> ForecastDataCurrentVector = new Vector<ForecastDataCurrent>();
	
	public Vector<ForecastDataCurrent> getForecastDataCurrentVector ()
	{
		return this.ForecastDataCurrentVector;
	}
	
	
	/**
	 * metodo che aggiorna il file locale con una nuova previsione (root)
	 * 
	 * @param name - nome della città
	 */
	Timer timer = null;
	public void ripetizioneMetodo(String name) {
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
		   timer.scheduleAtFixedRate(task,1000,1000 * 60 * 60);
	   }
	}
	
	
	
	/**
	 * Metodo per fermare il Timer (root)
	 */
	public void stopTimer() {
		timer.cancel();
		timer = null;
	}
	

	
	/**
	 * Metodo che serializza su file locale in formato JSON un array di oggetti java di tipo Vector<ForecastDataCurrent>
	 * ogni 3600s (60 minuti) e riempie il ForecastDataCurrentVector della classe
	 * @param cityName - nome della città passata 
	 * @throws ParseException
	 * @throws IOException
	 */	
	public void forecastCurr(String cityName) throws ParseException, IOException {

		if(ForecastDataCurrentVector.isEmpty())
			apriDaFile("currentForecastData.json", ForecastDataCurrentVector);
		else 
			if(!compareId(cityName, ForecastDataCurrentVector)) 
				ForecastDataCurrentVector.removeAllElements();			 	
		
		//Creazione del JAVA Object dal JSONObject
		JsonObject oggettoJ = callApi(ApiReference.UrlCurrP1 + cityName + ApiReference.UrlCurrP2);
		JsonObject tmp = oggettoJ.getAsJsonObject("main");
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));	
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		
		long dt = Long.parseLong(oggettoJ.get("dt").toString());
		tmp = oggettoJ.getAsJsonObject("coord");
		City city = new City(Float.parseFloat(tmp.get("lon").toString()),
							 Float.parseFloat(tmp.get("lat").toString()),
							 Integer.parseInt(oggettoJ.get("id").toString()),
							 				  oggettoJ.get("name").toString());
							 ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity,temperature,dt,city);
		
		//Caricamento dal file delle previsioni Current e posizionamento su ForecastCurrentVector
		if(ForecastDataCurrentVector.size() < 48)    //PARAMETRO PROGRAMMATORE
			ForecastDataCurrentVector.add(javaObj);
		else {
			ForecastDataCurrentVector.remove(0);
			ForecastDataCurrentVector.add(javaObj);
		}	//Salvataggio degli elementi dalla memoria volatile sulla memoria di massa
		
		salvaSuFile("currentForecastData.json",ForecastDataCurrentVector);
	}
	
	
	
	/**
	 * metodo per comparare gli ID di due città, una presente nel vettore e la seconda nel cityName
	 * @param cityName - da cui estrarre l'ID
	 * @param vettore - da cui estrarre l'ID2
	 * @return
	 */
	public boolean compareId(String cityName,Vector<ForecastDataCurrent> vettore ) {
		JsonObject compare1 = callApi(ApiReference.UrlCurrP1 + cityName + ApiReference.UrlCurrP2);
		int ID = Integer.parseInt(compare1.get("id").toString());
		int ID2 = vettore.lastElement().getCity().getID();
		if(ID == ID2)
			return true;
		else
			return false;
	}
	
	
	
	@SuppressWarnings("unused")
	@Deprecated //in data 30/12/21 dopo modifica a ForecastCurr
	/**
	 * metodo che svuota file locale
	 * @param nomeFile parametro formale
	 */
	private void svuotaFileLocale(String nomeFile) {
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
	public void salvaSuFile(String nomeFile,Vector<ForecastDataCurrent> vettore) throws IOException{
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
			System.out.println("salvati " + ForecastDataCurrentVector.size() + " elementi su file: " + nomeFile + "   " + super.CurrentTime());
		}			
	}

	

	/**
	 * metodo che carica un vettore di oggetti da un file  nomeFile e lo carica su un vettore
	 * @param nomeFile - file locale
	 * @param vettore 
	 */
	public void apriDaFile(String nomeFile, Vector<ForecastDataCurrent> vettore){
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