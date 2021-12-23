package it.uni.main.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dt ;
	private Humidity humidity;
	private long unixTime;
	
	public Forecast5Days(Humidity humidity, long dt){
		this.dt = EpochConverter(dt);
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
	
	
	public long getUnixTime() {
		return unixTime;
	}


	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}


	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	
	
	//da testare
	public Date fromUnixToDate(String d){
		return new java.util.Date(Long.parseLong(d)*1000);
	}
	
	public String EpochConverter(long dt) {
		Date date = new Date(dt);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String myDate = format.format(date);
		return myDate;
	}
	
	
	
	
	
}
