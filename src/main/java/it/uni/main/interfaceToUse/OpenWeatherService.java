package it.uni.main.interfaceToUse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface OpenWeatherService 
{
	public abstract void apriDaFile(String nomeFile, JsonObject jObj, JsonArray jArr);
	public abstract JsonObject callApi(String myUrl);
	public abstract void CreateTxtFile();
	public abstract String CurrentTime();
}
