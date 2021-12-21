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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;


import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService<E> extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	Vector<ForecastDataCurrent> ForecastDataCurrentVector = new Vector<ForecastDataCurrent>();
	
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
	    timer.scheduleAtFixedRate(task,delay,60*60*1000);
	}
	
	
	/**
	 * Metodo che serializza su file un array di oggetti java di tipo Forecast5Days
	 * @param name
	 * @throws ParseException
	 * @throws IOException
	 */	
	public void forecastCurr(String name) throws ParseException, IOException {
		
		JSONObject oggettoJ = leggiJsondaFile("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\response.json");
		
		JSONObject tmp = (JSONObject)oggettoJ.get("main");
		
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));
													 
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		
		String dt = new String(oggettoJ.get("dt").toString());
		
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity, temperature, dt);

		apriDaFILE("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\Test.txt", ForecastDataCurrentVector);
		
		//caricare su un vettore tutti i javaOBj dal file salvati fin'ora <--
		if(ForecastDataCurrentVector.size() < 48) 
		ForecastDataCurrentVector.add(javaObj);
		else {
			ForecastDataCurrentVector.remove(0);
			ForecastDataCurrentVector.add(javaObj);
		}	
		
		//Salvare su file il vettore 
		salvaSuFILE("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\Test.txt",ForecastDataCurrentVector);
		
	}
	
	public boolean compareId() {
		
		return true;
	}
	public void svuotaFileLocale(String nomeFile,Vector<ForecastDataCurrent> vettore) {
		vettore.removeAll(vettore);
		//todo:pulire file txt
	}
	
	public void salvaSuFILE(String nomeFile,Vector<ForecastDataCurrent> vettore) throws IOException{
			ObjectOutputStream oss = null;
			try{
				oss = new ObjectOutputStream(
					   new BufferedOutputStream(
					    new FileOutputStream(nomeFile)));
				oss.writeObject(vettore);
				}
			catch(Exception e){
					System.out.println("nononono");
			}finally {
					oss.close();
		}			
	}

	@SuppressWarnings("unchecked")
	public void apriDaFILE(String nomeFile, Vector<ForecastDataCurrent> vettore){
		ObjectInputStream ois = null;
		try{
			
			ois = new ObjectInputStream(
				new BufferedInputStream(
				new FileInputStream(nomeFile)));
		
			vettore.addAll((Vector<ForecastDataCurrent>)ois.readObject());
		}
		catch(Exception e){
			System.out.println("sei scemo");
		}
		
		}
	
			 

}