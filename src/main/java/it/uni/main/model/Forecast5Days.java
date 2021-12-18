package it.uni.main.model;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Forecast5Days {
	
	@Id
	@GeneratedValue
	private Long id;
	private Humidity humidity;
	
	public Forecast5Days(Humidity humidity) {
		this.humidity = humidity;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Humidity getHumidity() {
		return humidity;
	}

	public void setHumidity(Humidity humidity) {
		this.humidity = humidity;
	}

	
}
