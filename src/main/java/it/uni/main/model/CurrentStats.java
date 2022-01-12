package it.uni.main.model;

import java.util.Vector;


/**
 * Classe modello per le statistiche CurrentDataForecast
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class CurrentStats {
		//ATTRIBUTI INTERNI------------------------------
		private long startTime = 0;
		private long stopTime = 0;
		private long filteredDays = 0;
		private int filteredElements;
		private double tempMin = 0;
		private double tempMax = 0;
		private double averageTemp = 0;
		private double PerceivedTemperatureVariance  = 0;
		private double realTemperatureVariance = 0;
		private City city = null;
		
		
		
		public CurrentStats(long initialValue, long finalValue, int days, 
				Vector<ForecastDataCurrent> filteredVector,double tempMin,double tempMax,
				double tempMedia,double varianzaTempPercepita,double varianzaTempReale){
			this.startTime = initialValue;
			this.stopTime = finalValue;
			this.filteredDays = days;
			this.filteredElements = filteredVector.size(); 
			this.tempMin = tempMin;
			this.tempMax = tempMax;
	 		this.averageTemp = tempMedia;
			this.PerceivedTemperatureVariance = varianzaTempPercepita;
			this.realTemperatureVariance = varianzaTempReale;	
			City tmpC = filteredVector.get(0).getCity();
			this.city = new City(tmpC.getLat(), tmpC.getLon(), tmpC.getID(), tmpC.getCityName());
		}



		public long getStartTime() {
			return startTime;
		}



		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}



		public long getStopTime() {
			return stopTime;
		}



		public void setStopTime(long stopTime) {
			this.stopTime = stopTime;
		}



		public long getFilteredDays() {
			return filteredDays;
		}



		public void setFilteredDays(long filteredDays) {
			this.filteredDays = filteredDays;
		}



		public int getFilteredElements() {
			return filteredElements;
		}



		public void setFilteredElements(int filteredElements) {
			this.filteredElements = filteredElements;
		}



		public double getTempMin() {
			return tempMin;
		}



		public void setTempMin(double tempMin) {
			this.tempMin = tempMin;
		}



		public double getTempMax() {
			return tempMax;
		}



		public void setTempMax(double tempMax) {
			this.tempMax = tempMax;
		}



		public double getAverageTemp() {
			return averageTemp;
		}



		public void setAverageTemp(double averageTemp) {
			this.averageTemp = averageTemp;
		}



		public double getPerceivedTemperatureVariance() {
			return PerceivedTemperatureVariance;
		}



		public void setPerceivedTemperatureVariance(double perceivedTemperatureVariance) {
			PerceivedTemperatureVariance = perceivedTemperatureVariance;
		}



		public double getRealTemperatureVariance() {
			return realTemperatureVariance;
		}



		public void setRealTemperatureVariance(double realTemperatureVariance) {
			this.realTemperatureVariance = realTemperatureVariance;
		}



		public City getCity() {
			return city;
		}



		public void setCity(City città) {
			this.city = città;
		}
		


		
		
	
}
