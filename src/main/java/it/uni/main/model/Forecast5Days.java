package it.uni.main.model;

import java.io.Serializable;

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String dayTime ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, String dayTime) {
		this.dayTime = dayTime;
		this.humidity = humidity;
	}
	
	
	public String getDayTime() {
		return dayTime;
	}


	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}


	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
	
	

	public Humidity getHumidity() {
		return humidity;
	}

	
	
	
}
