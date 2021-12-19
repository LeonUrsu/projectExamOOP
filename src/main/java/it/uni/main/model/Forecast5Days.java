package it.uni.main.model;


public class Forecast5Days {
	

	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity) {
		this.humidity = humidity;
	}	

	public Humidity getHumidity() {
		return humidity;
	}

	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}

	
}
