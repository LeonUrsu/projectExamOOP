package it.uni.model;

public class GeoLocation {

	private float lat;
	private float lon;
	
	public GeoLocation (float lat, float lon) {
		this.lat = lat;
		this.lon = lon;
	
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	
}
