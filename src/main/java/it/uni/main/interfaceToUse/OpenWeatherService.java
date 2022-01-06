package it.uni.main.interfaceToUse;

import com.google.gson.JsonObject;

/**
 * Interface per assicurare l'implementazione dei seguenti metodi nella classe OpenWeatherServiceImp
 * durante la progettazione
 * @author DeskTop-L
 *
 */
public interface OpenWeatherService 
{
	public abstract JsonObject leggiJsonDaFile(String myFile);
	public abstract JsonObject callApi(String myUrl);
	public abstract void CreateTxtFile();
	public abstract String CurrentTime();
}
