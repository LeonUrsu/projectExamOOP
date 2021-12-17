package it.uni.main.model;

import it.uni.main.interfaceToUse.ForecastData;

public class ForecastDataImpl implements ForecastData{
	
	private long dayTime;
	private Humidity humidity;
	private	Temperature temperature;
	
	public ForecastDataImpl(Humidity humidity, Temperature temperature,long dayTime) {
		this.humidity = humidity;
		this.temperature = temperature;
		this.dayTime = dayTime;
	}

	public Humidity getHumidity() {
		return humidity;
	}

	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public long getDayTime() {
		return dayTime;
	}

	public void setDayTime(long dayTime) {
		this.dayTime = dayTime;
	}
	
	
	
}
