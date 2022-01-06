package it.uni.main.model;

import java.util.Vector;

/**
 * Classe modello per le statistiche delle previsoni rigardanti solo l'umidità
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class Forecast5DaysHumidity {
	
	private City city;
	private Vector<Forecast5Days> forecast5DaysVectorHum = new Vector<Forecast5Days>() ;
	
	
	public Forecast5DaysHumidity(City city ,Vector<Forecast5Days> forecast5Days){
		this.city = city;
		this.forecast5DaysVectorHum.addAll(forecast5Days);
	}

	public Vector<Forecast5Days> getForecast5DaysVectorHum() {
		return forecast5DaysVectorHum;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	

	
	
	
	
}
