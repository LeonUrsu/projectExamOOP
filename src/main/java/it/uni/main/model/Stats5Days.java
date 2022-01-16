package it.uni.main.model;


/**
 * Classe modello per le statistiche delle previsioni CurrentDataForecast
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class Stats5Days {

	private City city;
	private double averageHumidity;
	private double minHumidity;
	private double maxHumidity;
	//private Vector<Forecast5Days> forecast5days;
	
	public Stats5Days(double averageHumidity, double minHumidity, double maxHumidity, City city){//,Vector<Forecast5Days> forecast5days ) {
		this.averageHumidity = averageHumidity;
		this.minHumidity = minHumidity;
		this.maxHumidity = maxHumidity;
		this.city = city;
		//this.forecast5days = forecast5days;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public double getMediaUmidita() {
		return averageHumidity;
	}


	public void setMediaUmidita(double mediaUmidita) {
		this.averageHumidity = mediaUmidita;
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




	
	
	
	
	
	
}
