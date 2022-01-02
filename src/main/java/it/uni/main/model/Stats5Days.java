package it.uni.main.model;

import java.util.Vector;

public class Stats5Days {

	private City city;
	private double mediaUmidità;
	private double umiditaMinimaAssoluta;
	private double umiditaMassimaAssoluta;
	private Vector<Forecast5Days> forecast5days;
	
	public Stats5Days(double mediaUmidità_, double umiditaMinimaAssoluta_, double umiditaMassimaAssoluta_, City city_,Vector<Forecast5Days> forecast5days ) {
		this.mediaUmidità = mediaUmidità_;
		this.umiditaMinimaAssoluta = umiditaMinimaAssoluta_;
		this.umiditaMassimaAssoluta = umiditaMassimaAssoluta_;
		this.city = city_;
		this.forecast5days = forecast5days;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public double getMediaUmidità() {
		return mediaUmidità;
	}


	public void setMediaUmidità(double mediaUmidità) {
		this.mediaUmidità = mediaUmidità;
	}


	public double getUmiditaMinimaAssoluta() {
		return umiditaMinimaAssoluta;
	}


	public void setUmiditaMinimaAssoluta(double umiditaMinimaAssoluta) {
		this.umiditaMinimaAssoluta = umiditaMinimaAssoluta;
	}


	public double getUmiditaMassimaAssoluta() {
		return umiditaMassimaAssoluta;
	}


	public void setUmiditaMassimaAssoluta(double umiditaMassimaAssoluta) {
		this.umiditaMassimaAssoluta = umiditaMassimaAssoluta;
	}


	public Vector<Forecast5Days> getForecast5days() {
		return forecast5days;
	}


	public void setForecast5days(Vector<Forecast5Days> forecast5days) {
		this.forecast5days = forecast5days;
	}

	
	
	
	
	
	
}
