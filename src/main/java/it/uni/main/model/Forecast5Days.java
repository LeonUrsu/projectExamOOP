package it.uni.main.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dt ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, long unixTime){
		dt = EpochConverter(unixTime);
		this.humidity = humidity;
	}
	

	public String getDayTime(){
		return dt;
	}


	public void setDayTime(String dt){
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
	

	public String EpochConverter(long unixTime) {
		java.util.Date dateTime = new java.util.Date((long) Double.valueOf(unixTime).longValue() * 1000);
		String fromTimeZone = "GMT+1";
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone(fromTimeZone));
		String reportDate = df.format(dateTime);
		return reportDate;
	}
	
	
}
