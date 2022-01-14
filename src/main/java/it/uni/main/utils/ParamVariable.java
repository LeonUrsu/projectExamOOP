package it.uni.main.utils;
/**
 * Classe degli parametri globali schelti dal cliente
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class ParamVariable {
	
	/**
	 * Numero massimo degli elementi da salvare dentro al file 
	 */
	public static int currentVectorSize = 120;
	
	/**
	 * Percorso del file  
	 */
	public static String filePath = "temp\\currentForecastData{City}.json";
	
	/**
	 * Tempo in secondi del loop che aggiorna le previsioni settato di default a 1 ora
	 */
	public static int updateTime = 1 * 3600000;
	
	/**
	 * Formato della data e ora 
	 */
	public static String dataFormat = "dd-MM-yyyy HH:mm:ss";
}
