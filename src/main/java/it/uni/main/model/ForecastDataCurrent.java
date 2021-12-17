package it.uni.main.model;

import it.uni.main.interfaceToUse.ForecastData;

public class ForecastDataCurrent extends ForecastData5Days implements ForecastData{
	
	private long dayTime;
	private	Temperature temperature;
	
	public ForecastDataCurrent(Humidity humidity, Temperature temperature,long dayTime) {
		super(humidity);
		this.temperature = temperature;
		this.dayTime = dayTime;
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
