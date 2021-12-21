package it.uni.main.model;

import java.io.Serializable;

public class Humidity implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_UNIT = "%";
	
	public int value; 

	public Humidity(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (value < 0 || value > 100)  {
            throw new IllegalArgumentException("Valore umidità deve essere compreso tra 0 e 100");
        }
		this.value = value;
	}

	 public String getUnit() {
	     return DEFAULT_UNIT;
	 }

	@Override
	public String toString() {
		return "Umidità:" + value + DEFAULT_UNIT;
	}
	
	 
}
