package it.uni.main.model;

import java.io.Serializable;
import java.util.Date;

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dt ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, String dt) {
		this.dt = dt;
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

	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	
	
	//da testare
	public Date fromUnixToDate(String d)
	{
		return new java.util.Date(Long.parseLong(d)*1000);
	}
	
	
	
	
}
