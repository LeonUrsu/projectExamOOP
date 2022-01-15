package it.uni.main.utils;


/**
 * Classe con oggetti utili per il servizio
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
public class ApiReference 
{
	//previsioni 5giorni aggiornate dal server ogni tre ore
	public static String Url5day = "https://api.openweathermap.org/data/2.5/forecast?q=Rome&units=metric&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	public static String Url5dayP1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
	public static String Url5dayP2 = "&units=metric&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	
	
	//prevision correnti agiornate dal server ogni ora
	public static String UrlCurr = "https://api.openweathermap.org/data/2.5/weather?q=Rome&units=metric&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	public static String UrlCurrP1 = "https://api.openweathermap.org/data/2.5/weather?q=";
	public static String UrlCurrP2 = "&units=metric&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	
	
	public static String key = "57b8994a4dd46d6b80a3f810f6882a2f";

	//previsioni passate
	public static String UrlHistory = "http://history.openweathermap.org/data/2.5/history/city?id=3183087&type=hour&start={start}&end={end}&appid=57b8994a4dd46d6b80a3f810f6882a2f";
	
	





}
