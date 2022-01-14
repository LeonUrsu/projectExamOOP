package it.uni.main.model;

import java.io.Serializable;

import it.uni.main.statisticsAndFilters.Filters;



/**
 * Classe modello per le previsioni Forecast5Days
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long dt;
	private String dtString;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, long dt){
		this.dt = dt;
		Filters data = new Filters();
		dtString = data.secToData(dt);
		this.humidity = humidity;
	}
	

	public long getDayTime(){
		return dt;
	}


	public String getDtString() {
		return dtString;
	}

	public void setDtString(String dtString) {
		this.dtString = dtString;
	}

	public void setDayTime(long dt){
		this.dt = dt;
	}


	public void setHumidity(Humidity humidity){
		this.humidity = humidity;
	}
	
	public Humidity getHumidity(){
		return humidity;
	}
	

	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	

	
	
	
}
