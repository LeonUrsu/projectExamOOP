package it.uni.main.model;

import java.util.Vector;

public class CurrentStats {
	//ATTRIBUTI INTERNI------------------------------
		private long oraInizio = 0;
		private long oraFine = 0;
		private long filteredDays = 0;
		private double tempMin = 0;
		private double tempMax = 0;
		private double averageTemp = 0;
		private double varianzaTempPercepita = 0;
		private double varianzaTempReale = 0;
		private City città = null;
		
		
		public CurrentStats(long initialValue, long finalValue, int days, 
				Vector<ForecastDataCurrent> filteredVector,double tempMin,double tempMax,
				double tempMedia,double varianzaTempPercepita,double varianzaTempReale){
			
			this.oraInizio = initialValue;
			this.oraFine = finalValue;
			this.filteredDays = days;
			this.tempMin = tempMin;
			this.tempMax = tempMax;
	 		this.averageTemp = tempMedia;
			this.varianzaTempPercepita = varianzaTempPercepita;
			this.varianzaTempReale = varianzaTempReale;	
			City tmpC = filteredVector.get(0).getCity();
			this.città = new City(tmpC.getLat(), tmpC.getLon(), tmpC.getID(), tmpC.getCityName());
		}
		
		public CurrentStats(long initialValue, long finalValue, Vector<ForecastDataCurrent> filteredVector)
		{
		
		}

		
		

		public long getOraInizio() {
			return oraInizio;
		}


		public void setOraInizio(long oraInizio) {
			this.oraInizio = oraInizio;
		}


		public long getOraFine() {
			return oraFine;
		}


		public void setOraFine(long oraFine) {
			this.oraFine = oraFine;
		}


		public long getGiorniDiFiltraggio() {
			return filteredDays;
		}


		public void setGiorniDiFiltraggio(long giorniDiFiltraggio) {
			this.averageTemp = giorniDiFiltraggio;
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


		public double getTempMedia() {
			return averageTemp;
		}


		public void setTempMedia(double tempMedia) {
			this.averageTemp = tempMedia;
		}


		public double getVarianzaTempPercepita() {
			return varianzaTempPercepita;
		}


		public void setVarianzaTempPercepita(double varianzaTempPercepita) {
			this.varianzaTempPercepita = varianzaTempPercepita;
		}


		public double getVarianzaTempReale() {
			return varianzaTempReale;
		}


		public void setVarianzaTempReale(double varianzaTempReale) {
			this.varianzaTempReale = varianzaTempReale;
		}


		public City getCittà() {
			return città;
		}


		public void setCittà(City città) {
			this.città = città;
		}
		
		
		
		
		
	
}
