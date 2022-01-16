package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;



/**
 * Classe servizio per il calcolo delle statistiche per la parte del progetto riguardante le previsoni correnti 
 * aggiornate ogni ora dal timer del programma
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class StatisticsCurrentForecasts extends Statistics{
	/**
	 * Metodo per generare java object di statistiche che verrà restiruito al chiamante
	 * @param initialValue tempo d'inizio
	 * @param finalValue tempo di fine
	 * @param initialDay data di inizio
	 * @param finalDay data di fine
	 * @param filteredVector vettore filtrato
	 * @return CurrentStats object di statistiche
	 */
	public CurrentStats currentStats(long initialValue, long finalValue, long initialDay,long finalDay,Vector<ForecastDataCurrent> filteredVector) { 
		double tempMin = round(mediaTempMin(filteredVector),2);
		double tempMax = round(mediaTempMax(filteredVector),2);
		double tempMedia = round(mediaTemp(filteredVector),2);
		double arr[] = new double[filteredVector.size()];
		for(int i=0, u=filteredVector.size() ; i<u ; i++)//
			arr[i] = filteredVector.get(i).getTemperature().getTempFeel();
		double feelTemperatureVariance = round(varianza(arr,filteredVector.size()), 2);
		for(int i=0, u=filteredVector.size() ; i<u ; i++)
			arr[i] = filteredVector.get(i).getTemperature().getTemp();
		double realTemperatureVariance = round(varianza(arr,filteredVector.size()),2);	
		return new CurrentStats(initialValue, finalValue, initialDay, finalDay, filteredVector, tempMin, tempMax, tempMedia, feelTemperatureVariance, realTemperatureVariance);
	}
	
	
	
	/**
	 * metodo per il calcolo della varianza
	 * @param arr vettore di valori su cui fare la varianza
	 * @param size quantità dei valori passati  
	 * @return valore double
	 */
	private double varianza(double[] arr, int size ){
		if (size == 0)
			throw new IllegalArgumentException();
		double tmp = 0;
		double scartoMedia = 0;
		double media = 0;
		double somma = 0;
		double varianza = 0;
		for(double e : arr) 
			somma += e;
		media = somma/size;
		somma = 0;
		for(double e : arr) {
			scartoMedia = e - media;
			tmp = Math.pow(scartoMedia, 2.0);
			somma += tmp;
		}
		varianza = somma/size;
			return varianza;
	}
	
	
	
	/**
	 * metodo per il calcolo della varianza della temperatura percepita
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio 
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double varianzafilteredVectorV1(Vector<ForecastDataCurrent> previsioni){
		double scartoMedia = 0;
		double media = 0;
		double somma = 0;
		double varianza = 0;
		for(ForecastDataCurrent e : previsioni) 
			somma += e.getTemperature().getTempFeel();
		media = somma/previsioni.size();
		somma = 0;
		for(ForecastDataCurrent e : previsioni) {
			scartoMedia = (e.getTemperature().getTempFeel()) - media;
			scartoMedia = Math.pow(scartoMedia, 2.0);
			somma += scartoMedia;
		}
		varianza = somma/previsioni.size();
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
	

	
	/**
	 * metodo per il calcolo della varianza della temperatura percepita
	 * @param previsioni previsioni di tipo Vector di previsioni con possibile filtraggio 
	 * @return valore double
	 */
	@SuppressWarnings("unused")
	private double varianzaTempPercepitaV2(Vector<ForecastDataCurrent> previsioni)
	{
			double sommaScartiQuadMedi = 0;
			double tempMedia = mediaTemp(previsioni);
			for(ForecastDataCurrent d : previsioni)
				sommaScartiQuadMedi += Math.pow(tempMedia-d.getTemperature().getTempFeel(), 2);
			return (sommaScartiQuadMedi/previsioni.size());		
	}
	
	
}
