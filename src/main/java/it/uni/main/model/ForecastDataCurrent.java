package it.uni.main.model;

import java.io.Serializable;

/**
 * Classe modello per le previsioni meteo ForecastDataCurrent
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class ForecastDataCurrent extends Forecast5Days implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private	Temperature temperature;
	private City city;
	

	public ForecastDataCurrent(Humidity humidity, Temperature temperature, long unixTime,City city) {
		super(humidity,unixTime);

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
		return temperature.toString() + " " + getDayTime() +" "+ getHumidity().toString() +" "+getCity().toString() ;
	}
	
}
