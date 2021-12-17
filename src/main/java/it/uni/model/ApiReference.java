package it.uni.model;

public class ApiReference 
{
	
	//previsioni 5giorni ogni tre ore
	private static String Url1 = "https://api.openweathermap.org/data/2.5/forecast?q=Rome&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	//prevision correnti
	private static String Url2 = "api.openweathermap.org/data/2.5/weather?q=Rome&appid=57b8994a4dd46d6b80a3f810f6882a2f";

	private static String key = "57b8994a4dd46d6b80a3f810f6882a2f";

	//GETTER SETTER-------------------------------------------------
	public String getUrl1() {
		return Url1;
	}

	public void setUrl1(String url1) {
		Url1 = url1;
	}

	public String getUrl2() {
		return Url2;
	}

	public void setUrl2(String url2) {
		Url2 = url2;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	//GETTER SETTER-------------------------------------------------





}
