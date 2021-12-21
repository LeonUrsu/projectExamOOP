package it.uni.main.service;

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
	
	public void ripetizioneMetodo(String name) {
	    TimerTask task = new TimerTask() {
	        public void run() 
	        {
	        	try {
					forecastCurr(name);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.scheduleAtFixedRate(task,delay,60*60*1000 );
	}
	
	
	

	
	public void forecastCurr(String name) throws ParseException, IOException {
		
		JSONObject oggettoJ = leggiJsondaFile("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\response.json");
		Vector<ForecastDataCurrent> marco = new Vector<ForecastDataCurrent>();
		JSONObject tmp = (JSONObject)oggettoJ.get("main");
		
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));
													 
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity, temperature, "data");
		
		//caricare su un vettore tutti i javaOBj dal file salvati fin'ora <--
		marco.add(javaObj);
		
		//Salvare su file il vettore 
		salvaSuFILE("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\Test.txt",marco);
		
		apriDaFILE("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\Test.txt", marco);
	}
	
			public void salvaSuFILE(String nomeFile,Vector<ForecastDataCurrent> vettore) throws IOException{
				ObjectOutputStream oss = null;
				try{
				oss = new ObjectOutputStream(new FileOutputStream(nomeFile));
				oss.writeObject(vettore);
				}
				catch(Exception e){
					//aggiungere il messaggio di errore
				}finally {
					oss.close();
				}			
	}
			
			public void apriDaFILE(String nomeFile, Vector<ForecastDataCurrent> vettore){
				vettore = new Vector<ForecastDataCurrent>();
				ObjectInputStream ois;
				try{
				ois = new ObjectInputStream(new FileInputStream(nomeFile));
				
				System.out.println((JSONObject)ois.readObject());

				}
				catch(Exception e){
				//messaggio di errore
				}
				}
			
			 

}