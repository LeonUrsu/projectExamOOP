package it.uni.main.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;


import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.model.Forecast5Days;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.model.Humidity;
import it.uni.main.model.Temperature;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	
	public JSONObject forecastCurr(String name) throws ParseException {
		
		JSONObject oggettoJ = leggiJsondaFile("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\response.json");
	
		JSONObject tmp = (JSONObject) oggettoJ.get("main");
		
	
		
	
		Temperature temperature = new Temperature(Double.parseDouble(tmp.get("temp").toString()),
												Double.parseDouble(tmp.get("temp_min").toString()),
												Double.parseDouble(tmp.get("temp_max").toString()),
												Double.parseDouble(tmp.get("feels_like").toString()));
													 
		Humidity humidity = new Humidity(Integer.parseInt(tmp.get("humidity").toString()));
		
		Daytime daytime = new DayTime(Integer.parseInt(oggettoJ.get("dt").toString()));
		
		ForecastDataCurrent javaObj = new ForecastDataCurrent(humidity, temperature, Integer.parseInt(oggettoJ.get("dt").toString()));

		
		System.out.println(javaObj.getDayTime());
		System.out.println(javaObj.getHumidity());
		return null ;
		
	}
	
}