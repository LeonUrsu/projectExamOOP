package it.uni.main.model;


public class Forecast5Days {
	

	private Humidity humidity;
<<<<<<< HEAD
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

=======
	
	public Forecast5Days(Humidity humidity) {
		this.humidity = humidity;
	}
	
	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
		
>>>>>>> 7b83ff9f5aca71a94cc963594c51dcfcb2c47d89
	public Humidity getHumidity() {
		return humidity;
	}

	

	
}
