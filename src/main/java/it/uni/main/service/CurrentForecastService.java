package it.uni.main.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService<E> extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	private Vector<ForecastDataCurrent> ForecastDataCurrentVector = new Vector<ForecastDataCurrent>();
	
	
	/**
	 * metodo che aggiorna il file locale con una nuova previsione 
	 * 
	 * @param name - nome della città
	 */
	public void ripetizioneMetodo(String name) {
	    TimerTask task = new TimerTask() {
	        public void run() 
	        {
	        try {
	        	forecastCurr(name);
			}catch(ParseException e) {
					e.printStackTrace();
			}catch(IOException e) {
					e.printStackTrace();
			}
	      }
	   };
	    Timer timer = new Timer("Timer");
	    long delay = 1000L;
	    timer.scheduleAtFixedRate(task,delay,5000);
	}
	
	
	
	/**
	 * Metodo che serializza su file un array di oggetti java di tipo Forecast5Days
	 * @param name
	 * @throws ParseException
	 * @throws IOException
	 */	
	public void forecastCurr(String name) throws ParseException, IOException {
		JSONObject oggettoJ = leggiJsondaFile("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\fileCurrent.json");
		
		JSONObject tmp = (JSONObject)oggettoJ.get("main");
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));	
		
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		
		String dt = new String(oggettoJ.get("dt").toString());
		
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity, temperature, dt);
		
		//caricare su un vettore tutti i javaOBj dal file salvati fin'ora <--
		apriDaFILE("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\prova2.dat", ForecastDataCurrentVector);
		
		
		if(ForecastDataCurrentVector.size() < 48) {
		ForecastDataCurrentVector.add(javaObj);
		}
		else {
			ForecastDataCurrentVector.remove(0);
			ForecastDataCurrentVector.add(javaObj);
		}	
		
		//Salvare su file il vettore 
		salvaSuFILE("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\prova2.dat",ForecastDataCurrentVector);
	}
	
	
	
	public boolean compareId() {
		
		return true;
	}
	
	
	

	/**
	 * salva su un file passato nel parametro formale
	 * @param nomeFile parametro formale
	 */
	@SuppressWarnings("unused")
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
	 * @param nomeFile
	 * @param vettore
	 * @throws IOException
	 */
	public void salvaSuFILE(String nomeFile,Vector<ForecastDataCurrent> vettore) throws IOException{
			ObjectOutputStream oss = null;
			try{
				oss = new ObjectOutputStream(
					   new BufferedOutputStream(
					    new FileOutputStream(nomeFile)));
				oss.writeObject(vettore);
				}catch(Exception e){
					System.out.println("nononono");
				 }finally {
					oss.close();
					System.out.println("salvato su file: " + nomeFile + "   " + super.CurrentTime());
				  }			
	}

	
	
	
	/**
	 * metodo che carica un vettore di oggetto da un file locale e lo carica su un vettore
	 * @param nomeFile
	 * @param vettore
	 */
	@SuppressWarnings("unchecked")
	public void apriDaFILE(String nomeFile, Vector<ForecastDataCurrent> vettore){
		ObjectInputStream ois = null;
		try{
			vettore = new Vector<ForecastDataCurrent>();
			ois = new ObjectInputStream(
				new BufferedInputStream(
				new FileInputStream(nomeFile)));
			vettore.addAll((Vector<ForecastDataCurrent>)ois.readObject());
			ois.close();

		}
		catch(Exception e){
			System.out.println("file " + nomeFile + " vuoto "  );
		}
	}
	
			 

}