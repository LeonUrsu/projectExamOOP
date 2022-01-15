package it.uni.main.model;

import java.util.Vector;

import it.uni.main.statisticsAndFilters.Filters;


/**
 * Classe modello per le statistiche CurrentDataForecast
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class CurrentStats {
		//ATTRIBUTI INTERNI------------------------------
		private String initialDayInUTC;
		private String finalDayInUTC;
		private long initialValueInUnix = 0;
		private long finalValueInUnix = 0;
		private long initialValueDayInUnix = 0;
		private long finalValueInDayInUnix = 0;
		private int filteredElements;
		private double temperatureMin = 0;
		private double temperatureMax = 0;
		private double averageTemperature = 0;
		private double perceivedTemperatureVariance  = 0;
		private double realTemperatureVariance = 0;
		private City city = null;
		
		//COSTRUTTORE---------------------------------------------
		public CurrentStats(long initialValueInUnix, long finalValueInUnix, long initialValueDayInUnix,long finalValueInDayInUnix,
			Vector<ForecastDataCurrent> filteredVector,double temperatureMin,double temperatureMax,
			double tempMedia,double varianzaTempPercepita,double varianzaTempReale){
			Filters filter = new Filters();
			this.initialDayInUTC= filter.secToDataV2(initialValueDayInUnix + initialValueInUnix);
			this.finalDayInUTC = filter.secToDataV2(finalValueInDayInUnix + finalValueInUnix);
			this.initialValueInUnix = initialValueInUnix;
			this.finalValueInUnix = finalValueInUnix;
			this.initialValueDayInUnix = initialValueDayInUnix;
			this.finalValueInDayInUnix = finalValueInDayInUnix;
			this.filteredElements = filteredVector.size(); 
			this.temperatureMin = temperatureMin;
			this.temperatureMax = temperatureMax;
	 		this.averageTemperature = tempMedia;
			this.perceivedTemperatureVariance = varianzaTempPercepita;
			this.realTemperatureVariance = varianzaTempReale;	
			City tmpC = filteredVector.get(0).getCity();
			this.city = new City(tmpC.getLat(), tmpC.getLon(), tmpC.getID(), tmpC.getCityName());
		}

		
		public String getInitialDayInUTC() {
			return initialDayInUTC;
		}

		public void setInitialDayInUTC(String initialDayInUTC) {
			this.initialDayInUTC = initialDayInUTC;
		}

		public String getFinalDayInUTC() {
			return finalDayInUTC;
		}

		public void setFinalDayInUTC(String finalDayInUTC) {
			this.finalDayInUTC = finalDayInUTC;
		}

		public long getInitialValueInUnix() {
			return initialValueInUnix;
		}

		public void setInitialValueInUnix(long initialValueInUnix) {
			this.initialValueInUnix = initialValueInUnix;
		}

		public long getFinalValueInUnix() {
			return finalValueInUnix;
		}

		public void setFinalValueInUnix(long finalValueInUnix) {
			this.finalValueInUnix = finalValueInUnix;
		}

		public long getInitialValueDayInUnix() {
			return initialValueDayInUnix;
		}

		public void setInitialValueDayInUnix(long initialValueDayInUnix) {
			this.initialValueDayInUnix = initialValueDayInUnix;
		}

		public long getFinalValueInDayInUnix() {
			return finalValueInDayInUnix;
		}

		public void setFinalValueInDayInUnix(long finalValueInDayInUnix) {
			this.finalValueInDayInUnix = finalValueInDayInUnix;
		}

		public int getFilteredElements() {
			return filteredElements;
		}

		public void setFilteredElements(int filteredElements) {
			this.filteredElements = filteredElements;
		}

		public double getTemperatureMin() {
			return temperatureMin;
		}

		public void setTemperatureMin(double temperatureMin) {
			this.temperatureMin = temperatureMin;
		}

		public double getTemperatureMax() {
			return temperatureMax;
		}

		public void setTemperatureMax(double temperatureMax) {
			this.temperatureMax = temperatureMax;
		}

		public double getAverageTemperature() {
			return averageTemperature;
		}

		public void setAverageTemperature(double averageTemperature) {
			this.averageTemperature = averageTemperature;
		}

		public double getPerceivedTemperatureVariance() {
			return perceivedTemperatureVariance;
		}

		public void setPerceivedTemperatureVariance(double perceivedTemperatureVariance) {
			this.perceivedTemperatureVariance = perceivedTemperatureVariance;
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

		public void setCity(City city) {
			this.city = city;
		}

		
		
		
		
		
		
		
		
		
}

