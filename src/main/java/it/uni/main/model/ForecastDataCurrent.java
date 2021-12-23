package it.uni.main.model;

import java.io.Serializable;

public class ForecastDataCurrent extends Forecast5Days implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private	Temperature temperature;
	private City city;
	
<<<<<<< HEAD
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, long dt, City city) {
		super(humidity,dt);
=======
	public ForecastDataCurrent(Humidity humidity, Temperature temperature, long unixTime,City city) {
		super(humidity,unixTime);
>>>>>>> e286823e5c36eb25f5a18e4bf6ca271a8e2c51de
		this.temperature = temperature;
		this.city = city;
	}
	
	public Temperature getTemperature(){
		return temperature;
	}

	public void setTemperature(Temperature temperature){
		this.temperature = temperature;
	}
	
	public City getCity(){
		return city;
	}

	public void setCity(City city){
		this.city = city;
	}

	@Override
	public String toString(){
		return temperature.toString() + " " +getDayTime().toString() +" "+ getHumidity().toString() +" "+getCity().toString() ;
	}


	
	
	
	
}
