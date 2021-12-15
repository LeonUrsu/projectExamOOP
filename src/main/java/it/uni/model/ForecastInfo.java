package it.uni.model;

public class ForecastInfo{
	
	private long dt; // Orario della previsione codifica UNIX

	
	private double temp;
	private double tempFeel;
	private	double tempMin;
	private double tempMax;
	private int humidity; //è un dato in %
	
	private int weather; // id della condizione meteo
	private String main;
	private String description;
	
	//GETTER && SETTER
	
	public long getDt() {
		return dt;
	}
	
	public void setDt(long dt) {
		this.dt=dt; //TODO convertirlo in UTC
	}
	
	public double getTemp(){
		return temp;
	}
	
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public double getTempFeel() {
		return tempFeel;
	}
	
	public void setTempFeel(double tempFeel) {
		this.tempFeel= tempFeel;
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
	
	public int getHumidity() {
		return humidity;
	}
	
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	
	public int getWeather() {
		return weather;
	}
	
	public void setWeather(int weather) {
		this.weather = weather;
	}
	
	public String getMain() {
		return main;
	}
	
	public void setMain(String main) {
		this.main = main;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
