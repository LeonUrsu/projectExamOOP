package it.uni.main.model;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.util.Date;
import java.text.SimpleDateFormat;
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1

public class Forecast5Days implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
<<<<<<< HEAD
	private String dayTime ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, String dayTime) {
		this.dayTime = dayTime;
=======
	private String dt ;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity, String dt) {
		this.dt = dtConverter(dt);
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1
		this.humidity = humidity;
	}
	
	
	public String getDayTime() {
<<<<<<< HEAD
		return dayTime;
	}


	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
=======
		return dt;
	}


	public void setDayTime(String dt) {
		this.dt = dt;
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1
	}


	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}
	
	

	public Humidity getHumidity() {
		return humidity;
	}

<<<<<<< HEAD
=======
	public String dtConverter(String dt) {
    long tmp = Long.parseLong(dt);
	Date date = new java.util.Date(tmp*1000L); 

	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); 
	sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+1")); 
	String formattedDate = sdf.format(date);
	return formattedDate;
	}


	@Override
	public String toString() {
		return "dt =" + dt ;
	}
	
>>>>>>> f892d1f0ae627455c1e455e772b4564899e765e1
	
	
	
}
