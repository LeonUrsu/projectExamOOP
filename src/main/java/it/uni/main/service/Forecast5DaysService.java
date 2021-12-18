package it.uni.main.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	
	
	/**
	 * 
	 */
	public JSONObject forecast5day(String name) {
		ApiReference apiObj = new ApiReference();
		//Todo salvare i valori in una classe in un database in modo che si possa accedere nuovamente a loro
		return callApi(apiObj.Url5day);
	}
	
}
