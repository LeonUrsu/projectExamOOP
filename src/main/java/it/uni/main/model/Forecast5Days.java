package it.uni.main.model;


public class Forecast5Days {
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
