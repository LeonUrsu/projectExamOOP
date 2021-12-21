package it.uni.main.model;


public class Forecast5Days {
	
	 

	private Humidity humidity;
	private String dayTime ;
	
	
	
	public Forecast5Days(Humidity humidity, String dayTime) {
		this.humidity = humidity;
		this.dayTime = dayTime;
		
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
