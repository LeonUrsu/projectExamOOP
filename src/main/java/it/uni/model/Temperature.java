package it.uni.model;

public class Temperature {
	
	private double temp;
	private double tempMin;
	private double tempMax;
	private double tempFeel;
	
	public Temperature(double temp, double tempMin, double tempMax, double tempFeel) {
		this.temp = temp;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.tempFeel = tempFeel;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
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

	public double getTempFeel() {
		return tempFeel;
	}

	public void setTempFeel(double tempFeel) {
		this.tempFeel = tempFeel;
	}

	
	
	


}
