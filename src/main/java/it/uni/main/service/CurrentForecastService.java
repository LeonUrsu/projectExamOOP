package it.uni.main.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService<E> extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	
	
	/**
	 * vettore che aumenterà dimensionalmente ogni 60minuti di un nuovo 
	 * elemento ForecastDataCurrent fino a raggiungere un valore massimo 
	 * stabilito dal programmatore tramite il PARAMETRO PROGRAMMATORE
	 */
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
	    
	    timer.scheduleAtFixedRate(task,delay,1000 );//* 60 * 60);
	}
	
	
	
	/**
	 * Metodo che serializza su file un array di oggetti java di tipo Forecast5Days
	 * @param name
	 * @throws ParseException
	 * @throws IOException
	 */	
	public void forecastCurr(String name) throws ParseException, IOException {
		apriDaFILE("C:\\Users\\DeskTop-L\\Desktop\\OOP EXAM\\prova2.dat", ForecastDataCurrentVector);
		
		//Creazione del JAVA Object dal JSONObject
		JSONObject oggettoJ = callApi(ApiReference.UrlCurrP1 + name + ApiReference.Url5dayP2);
		JSONObject tmp = (JSONObject)oggettoJ.get("main");
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));	
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		String dt = new String(oggettoJ.get("dt").toString());
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity, temperature, dt);
		
		//Caricamento dal file delle previsioni Current e posizionamento su ForecastCurrentVector
		if(ForecastDataCurrentVector.size() < 48) {   //PARAMETRO PROGRAMMATORE
		ForecastDataCurrentVector.add(javaObj);
		}
		else {
			ForecastDataCurrentVector.remove(0);
			ForecastDataCurrentVector.add(javaObj);
		}	
		//Salvataggio degli elementi dalla memoria volatile sulla memoria di massa
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
	 * metodo che carica un vettore di oggetto da un file locale e lo carica su un vettore
	 * @param nomeFile
	 * @param vettore
	 */
	public void apriDaFILE(String nomeFile, Vector<ForecastDataCurrent> vettore){
		try{
			Scanner scr = new Scanner(new BufferedReader(new FileReader(nomeFile)));
			String inJSON = "";
			while(scr.hasNext())
				inJSON += scr.nextLine();
			Gson gson = new Gson();
			Vector<ForecastDataCurrent> tmpVec = (gson.fromJson(inJSON, new TypeToken<Vector<ForecastDataCurrent>>(){}.getType()));
			if(tmpVec.size() > vettore.size()) 
				sincronizzaElementi(vettore, tmpVec);
			vettore = tmpVec;	
		}
		catch(Exception e){
			System.out.println("file " + nomeFile + "  vuoto "  );
		}
	}
	
	
	
	/**
	 * Metodo che ci permette di caricare sul Vector gli oggetti se e solo se esistenti già nel file locale 
	 *
	 * @param vettore su cui sincronizzare
	 * @param toSinc da inizializzare
	 * @return
	 */
	private void sincronizzaElementi(Vector<ForecastDataCurrent> vettore, Vector<ForecastDataCurrent> toSinc ){
		for(int i=0, u=toSinc.size() ; i<u ; i++) {				//insertElementAt(E obj, int index) toUSE
			ForecastDataCurrent tmpFor = toSinc.get(i);
			Temperature temperature = new Temperature(tmpFor.getTemperature().getTemp(),
													  tmpFor.getTemperature().getTempMin(),
													  tmpFor.getTemperature().getTempMax(),
													  tmpFor.getTemperature().getTempFeel());
			Humidity humidity = new Humidity(tmpFor.getHumidity().getValue());
			String dt = new String(tmpFor.getDayTime());								
			tmpFor = new ForecastDataCurrent(humidity, temperature, dt);
			vettore.insertElementAt(tmpFor, i);
		}
	}
	
	
	@SuppressWarnings("unused")
	private ObjectOutputStream BufferedOutputStream(FileOutputStream fileOutputStream) {
		// TODO Auto-generated method stub
		return null;
	}
		
}