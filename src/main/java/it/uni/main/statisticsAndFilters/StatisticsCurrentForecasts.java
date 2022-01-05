package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;

@Service
public class StatisticsCurrentForecasts extends Statistics{
	
	
	/**
	 * Metodo per generare java object che verrà restiruito al controllerS
	 * @param initialValue tempo d'inizio
	 * @param finalValue tempo di fine
	 * @param days quantità giorni da considerare
	 * @param filteredVector vettore filtrato
	 * @return CurrentStats object
	 */
	public CurrentStats currentStats(long initialValue, long finalValue, int days,Vector<ForecastDataCurrent> filteredVector) { 
		double tempMin = mediaTempMin(filteredVector);
		double tempMax = mediaTempMax(filteredVector);
		double tempMedia = mediaTemp(filteredVector);
		double feelTemperatureVariance = varianzaTempPercepita(filteredVector);
		double realTemperatureVariance = varianzaTempReale(filteredVector);
		
		return new CurrentStats(initialValue, finalValue, days, filteredVector, tempMin, tempMax, tempMedia, feelTemperatureVariance, realTemperatureVariance);
	}
	
	
	
	
	/**
	 * metodo per il calcolo della varianza della temperatura misurata
	 * @param previsioni di tipo Vector di previsioni con possibile filtraggio  
	 * @return valore double
	 */
	private double varianzaTempReale(Vector<ForecastDataCurrent> previsioni)
	{
		double tmp = 0;
		double scartoMedia = 0;
		double media = 0;
		double somma = 0;
		int contatore = 0;
		double varianza = 0;
		
		for(ForecastDataCurrent e : previsioni) {
			somma += e.getTemperature().getTemp();
			contatore = previsioni.size();
		}
		
		media = somma/contatore;
		somma = 0;
		for(ForecastDataCurrent e : previsioni) {
			scartoMedia = (e.getTemperature().getTemp()) - media;
			tmp = Math.pow(scartoMedia, 2.0);
			somma += tmp;
		}
		
		varianza = somma/contatore;
		
			return varianza;
	}
	
	/**
	 * metodo per il calcolo della varianza della temperatura percepita
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio 
	 * @return valore double
	 */
	private double varianzaTempPercepita(Vector<ForecastDataCurrent> previsioni)
	{
		double tmp = 0;
		double scartoMedia = 0;
		double media = 0;
		double somma = 0;
		int contatore = 0;
		double varianza = 0;
		
		for(ForecastDataCurrent e : previsioni) {
			somma += e.getTemperature().getTempFeel();
			contatore = previsioni.size();
		}
		
		media = somma/contatore;
		somma = 0;
		for(ForecastDataCurrent e : previsioni) {
			scartoMedia = (e.getTemperature().getTempFeel()) - media;
			tmp = Math.pow(scartoMedia, 2.0);
			somma += tmp;
		}
		
		varianza = somma/contatore;
		
			return varianza;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	private double mediaTemp(Vector<ForecastDataCurrent> previsioni)
	{
		double media = 0;
		double somma = 0;
		int contatore = 0;
		for(ForecastDataCurrent e : previsioni) {
			somma += e.getTemperature().getTemp();
			contatore++;
		}
		media = somma/contatore;
		return media;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura massima gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double mediaTempMax(Vector<ForecastDataCurrent> previsioni)
	{
		double media = 0;
		double somma = 0;
		int contatore = 0;
		for(ForecastDataCurrent e : previsioni) {
			somma += e.getTemperature().getTempMax();
			contatore++;
		}
		media = somma/contatore;
		return media;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura minima gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double mediaTempMin(Vector<ForecastDataCurrent> previsioni)
	{
		double media = 0;
		double somma = 0;
		int contatore = 0;
		for(ForecastDataCurrent e : previsioni) {
			somma += e.getTemperature().getTempMin();
			contatore++;
		}
		media = somma/contatore;
		return media;
	}
	
	
	
	/**
	 * metodo per il calcolo della media della temperatura minima assoluta gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double TempMinAssoluta(Vector<ForecastDataCurrent> previsioni)
	{

		double tempMinAssoluta = previsioni.get(0).getTemperature().getTempMin();
		for(int i = 0; i < previsioni.size(); i++) 
			if(tempMinAssoluta > previsioni.get(i).getTemperature().getTempMin())
				tempMinAssoluta = previsioni.get(i).getTemperature().getTempMin();
			
		return tempMinAssoluta;
	}
	
	
	/**
	 * metodo per il calcolo della media della temperatura massima assoluta gioraliera 
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double TempMaxAssoluta(Vector<ForecastDataCurrent> previsioni)
	{
		double tempMaxAssoluta = previsioni.get(0).getTemperature().getTempMax();
		for(int i = 0; i < previsioni.size(); i++) 
			if(tempMaxAssoluta > previsioni.get(i).getTemperature().getTempMax())
				tempMaxAssoluta = previsioni.get(i).getTemperature().getTempMax();
			
		return tempMaxAssoluta;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
