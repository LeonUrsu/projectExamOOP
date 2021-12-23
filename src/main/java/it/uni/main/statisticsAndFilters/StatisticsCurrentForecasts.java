package it.uni.main.statisticsAndFilters;

import java.util.Vector;

public class StatisticsCurrentForecasts extends Statistics{

	//questa classe ricevera il Vector<ForecastCurrent> da filter
	
	
	
	
	/**
	 * metodo per il calcolo della varianza della temperatura misurata
	 * @param previsioni di tipo Vector di previsioni con possibile filtraggio  
	 * @return valore double
	 */
	protected double varianzaTempMisurata(Vector<Object> previsioni)
	{
		return 0;	
	}
	
	/**
	 * metodo per il calcolo della varianza della temperatura percepita
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio 
	 * @return valore double
	 */
	protected double varianzaTempPercepita(Vector<Object> previsioni)
	{
		return 0;	
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
