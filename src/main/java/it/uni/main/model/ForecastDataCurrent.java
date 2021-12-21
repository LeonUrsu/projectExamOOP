package it.uni.main.model;

import java.io.Serializable;

public class ForecastDataCurrent extends Forecast5Days implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private	Temperature temperature;
	
<<<<<<< HEAD
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, String dayTime) {
		super(humidity, dayTime);
=======
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, String dt) {
		super(humidity,dt);
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1
		this.temperature = temperature;
	}
	
	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return temperature.toString() ;
=======
		return temperature.toString() + " " +getDayTime() +" "+ getHumidity();
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1
	}


	
	
	
	
}
