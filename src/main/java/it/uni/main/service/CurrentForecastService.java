package it.uni.main.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


import it.uni.main.interfaceToUse.ForecastDataCurr;
import it.uni.main.utils.ApiReference;

@Service
public class CurrentForecastService extends OpenWeatherServiceImp implements ForecastDataCurr{
	
	
	public JSONObject forecastCurr(String name) throws ParseException {
		
		JSONObject oggettoJ = leggiJsondaFile("D:\\WorkSpaceECLIPSE\\projectExamOOP-main\\response.json");
	
		JSONObject tmp = (JSONObject) oggettoJ.get("main");
		
		
		return tmp;
		
	}
	
}