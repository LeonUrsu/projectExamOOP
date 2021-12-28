package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import it.uni.main.model.ForecastDataCurrent;

public class Filters {
	
	//	Filtra il vettore ForecastDataCurrentVector presente nella classe CurrentForecastService 
	//	in base ai parametri passati nel controller sulle rotte:
	//	@GetMapping("/filter/weekly/{initialValue}/{finalValue}")
	//	@GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}")
	// 	e con il vettore filtrato restituisce un oggetto StatisticsCurrentForecasts
	
	
	
	public StatisticsCurrentForecasts weeklyFilter(long initialValue, long finalValue)  // unix format per gli initial
	{
		Vector<ForecastDataCurrent> filteredVector = new Vector<ForecastDataCurrent>();
		
		//ToDo filtraggio ForecastDataCurrentVector in filteredVector
		//ToDo aggiungere City al oggetto StatisticsCurrentForecasts
	 	return new StatisticsCurrentForecasts(initialValue, finalValue, filteredVector);
	}
	


	public StatisticsCurrentForecasts dailyFilter(long initialValue, long finalValue, int days)  // unix format per gli initial
	{
		Vector<ForecastDataCurrent> filteredVector = new Vector<ForecastDataCurrent>();
		
		//ToDo filtraggio ForecastDataCurrentVector in filteredVector
		//ToDo aggiungere City al oggetto StatisticsCurrentForecasts
		
	 	return new StatisticsCurrentForecasts(initialValue, finalValue, days, filteredVector);
	}
	
	
	
	
	
	
}
