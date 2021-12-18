package it.uni.main.service;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uni.main.interfaceToUse.Forecast5DayRepository;
import it.uni.main.utils.ApiReference;



@Service
@Transactional
public class Forecast5DaysService extends OpenWeatherServiceImp {
	@Autowired Forecast5DayRepository forecast5DayRepository;
	
	
	
	/**
	 * 
	 */
	public JSONObject forecast5day(String name) {
		ApiReference apiObj = new ApiReference();
		//Todo salvare i valori in una classe in un database in modo che si possa accedere nuovamente a loro
		return callApi(apiObj.Url5day);
	}
	
}
