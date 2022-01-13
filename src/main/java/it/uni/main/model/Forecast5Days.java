package it.uni.main.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



/**
 * Classe modello per le previsioni Forecast5Days
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long dt;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, long dt){
		this.dt = dt;
		this.humidity = humidity;
	}
	

	public long getDayTime(){
		return dt;
	}


	public void setDayTime(long dt){
		this.dt = dt;
	}


	public void setHumidity(Humidity humidity){
		this.humidity = humidity;
	}
	
	public Humidity getHumidity(){
		return humidity;
	}
	

	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	

	@Deprecated
	/**
	 * Metodo che ci restitusce formato UTC in stringa di un formato unix del tempo
	 * @param unixTime tempo in unix
	 * @return UTC in stringa
	 */
	public String EpochConverter(long unixTime) {
		Date dateTime = new java.util.Date((long) Double.valueOf(unixTime).longValue() * 1000);
		String fromTimeZone = "GMT+1";
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone(fromTimeZone));
		String reportDate = df.format(dateTime);
		return reportDate;
	}
	
	
}
