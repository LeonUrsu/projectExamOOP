package it.uni.main.model;

import java.io.Serializable;

public class ForecastDataCurrent extends Forecast5Days implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private	Temperature temperature;
	
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, String dayTime) {
		super(humidity, dayTime);
		this.temperature = temperature;
	}
	
	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}


	
	
	
	
}
