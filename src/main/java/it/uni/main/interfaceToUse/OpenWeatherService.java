package it.uni.main.interfaceToUse;

import com.google.gson.JsonObject;

public interface OpenWeatherService 
{
	public abstract JsonObject leggiJsonDaFile(String myFile);
	public abstract JsonObject callApi(String myUrl);
	public abstract void CreateTxtFile();
	public abstract String CurrentTime();
}
