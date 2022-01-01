package it.uni.main.model;

import java.util.Vector;

public class Stats5Days {
	
	private Vector<Forecast5Days> forecast5Days;
	private City city;
	
	public Stats5Days(City city, Vector<Forecast5Days> forecast5Days ){
	this.city = city;
	this.forecast5Days = new Vector<Forecast5Days>();
	System.out.println("eleee" + forecast5Days.size());
	this.forecast5Days.addAll(0, forecast5Days);
	}

	
	public Vector<Forecast5Days> getForecast5Days() {
		return forecast5Days;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	

	
	
	
	
}
