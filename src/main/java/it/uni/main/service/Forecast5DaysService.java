package it.uni.main.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import org.springframework.stereotype.Service;
import it.uni.main.utils.ApiReference;



@Service
public class Forecast5DaysService extends OpenWeatherServiceImp {
	
	
	
	
	/**metodo che ciama l'api e salva temporanemente mella memoria le previsioni u cui andranno poi fatte le statistiche 
	 *
	 *@param nome della citta su cui cercare le previsioni 
	 */
	public JsonObject forecast5day(String name) {
		ApiReference apiObj = new ApiReference();
		//Todo salvare i valori in una classe in un database in modo che si possa accedere nuovamente a loro
		return callApi(apiObj.Url5day);
	}
	
	
	
	
	
	
}
