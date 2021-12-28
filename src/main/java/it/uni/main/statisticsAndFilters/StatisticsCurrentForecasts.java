package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.model.ForecastDataCurrent;

@Service
public class StatisticsCurrentForecasts extends Statistics{
	//questa classe riceverà un Vector<ForecastDataCurrent> filtrato dalla classe Filter 
	//in base ai parametri passati sulle rotte del controller 
	
	
	
	
	/**
	 * metodo per il calcolo della varianza della temperatura misurata
	 * @param previsioni di tipo Vector di previsioni con possibile filtraggio  
	 * @return valore double
	 */
	protected double varianzaTempReale(Vector<ForecastDataCurrent> previsioni)
	{

		return 0;	
	}
	
	/**
	 * metodo per il calcolo della varianza della temperatura percepita
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio 
	 * @return valore double
	 */
	protected double varianzaTempPercepita(Vector<ForecastDataCurrent> previsioni)
	{
		return 0;	
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	protected double mediaTemp(Vector<ForecastDataCurrent> previsioni)
	{
		double media = 0;
		for(ForecastDataCurrent e : previsioni)
			media += e.getTemperature().getTemp();
		
		return media;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura massima gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	protected double mediaTempMax(Vector<Object> previsioni)
	{
		return 0;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura minima gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	protected double mediaTempMin(Vector<Object> previsioni)
	{
		return 0;
	}
	
	
	
	/**
	 * metodo per il calcolo della media della temperatura minima assoluta gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	protected double TempMinAssoluta(Vector<Object> previsioni)
	{
		return 0;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura massima assoluta gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	protected double TempMaxAssoluta(Vector<Object> previsioni)
	{
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
