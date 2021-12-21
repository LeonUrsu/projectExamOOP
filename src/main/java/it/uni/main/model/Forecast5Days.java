package it.uni.main.model;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dt ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, String dt) {
		this.dt = dtConverter(dt);
		this.humidity = humidity;
	}
	
	
	public String getDayTime() {
		return dt;
	}


	public void setDayTime(String dt) {
		this.dt = dt;
	}


	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
	
	

	public Humidity getHumidity() {
		return humidity;
	}

	public String dtConverter(String dt) {
    long tmp = Long.parseLong(dt);
	Date date = new java.util.Date(tmp*1000L); 

	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
	sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1")); 
	String formattedDate = sdf.format(date);
	return formattedDate;
	}


	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	
	
	
	
}
