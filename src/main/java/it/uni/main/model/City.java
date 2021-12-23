package it.uni.main.model;


public class City extends GeoLocation {
	
	private String country;
	private int ID;
	private String cityName;
	
	public City(float lat, float lon, int ID, String cityName) {
		super(lat, lon);
		this.ID= ID;
		this.cityName = cityName;
		
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Città: " + cityName + " Stato: " + country + " Coordinate:" + getLat() + " " + getLon();
	}

	

		
}

