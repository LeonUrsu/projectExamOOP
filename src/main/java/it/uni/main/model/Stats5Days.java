package it.uni.main.model;

public class Stats5Days {

	private City city;
	private double averageHumidity;
	private double minHumidity;
	private double maxHumidity;
	//private Vector<Forecast5Days> forecast5days;
	
	public Stats5Days(double mediaUmidità_, double umiditaMinimaAssoluta_, double umiditaMassimaAssoluta_, City city_){//,Vector<Forecast5Days> forecast5days ) {
		this.averageHumidity = mediaUmidità_;
		this.minHumidity = umiditaMinimaAssoluta_;
		this.maxHumidity = umiditaMassimaAssoluta_;
		this.city = city_;
		//this.forecast5days = forecast5days;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public double getMediaUmidità() {
		return averageHumidity;
	}


	public void setMediaUmidità(double mediaUmidità) {
		this.averageHumidity = mediaUmidità;
	}


	public double getUmiditaMinimaAssoluta() {
		return minHumidity;
	}


	public void setUmiditaMinimaAssoluta(double umiditaMinimaAssoluta) {
		this.minHumidity = umiditaMinimaAssoluta;
	}


	public double getUmiditaMassimaAssoluta() {
		return maxHumidity;
	}


	public void setUmiditaMassimaAssoluta(double umiditaMassimaAssoluta) {
		this.maxHumidity = umiditaMassimaAssoluta;
	}


//	public Vector<Forecast5Days> getForecast5days() {
//		return forecast5days;
//	}
//
//
//	public void setForecast5days(Vector<Forecast5Days> forecast5days) {
//		this.forecast5days = forecast5days;
//	}

	
	
	
	
	
	
}
