package it.uni.main.model;



public class ForecastDataCurrent extends Forecast5Days {
	
	
	private	Temperature temperature;
	private DayTime DayTime;
	
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, DayTime dt) {
		super(humidity);
		this.temperature = temperature;
		DayTime = dt;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public DayTime getDayTime() {
		return DayTime;
	}

	public void setDayTime(DayTime dayTime) {
		DayTime = dayTime;
	}

	
	
	
	
}
