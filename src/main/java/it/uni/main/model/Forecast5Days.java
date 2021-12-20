package it.uni.main.model;


public class Forecast5Days {
	

	private Humidity humidity;
	private String dataTime ;
	
	
	public Forecast5Days(Humidity humidity, String dataTime) {
		this.humidity = humidity;
		this.dataTime = dataTime;
	}
	
	
	
	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
	
	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public Humidity getHumidity() {
		return humidity;
	}

	

	
}
