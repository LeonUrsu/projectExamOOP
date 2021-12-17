package it.uni.main.model;

public class ForecastData5Days {

	private Humidity humidity;
	
	public ForecastData5Days(Humidity humidity) {
		this.humidity = humidity;
	}

	public Humidity getHumidity() {
		return humidity;
	}

	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
	
}
