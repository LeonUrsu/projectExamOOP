package it.uni.main.model;


public class ForecastDataCurrent extends Forecast5Days {
	
	
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
