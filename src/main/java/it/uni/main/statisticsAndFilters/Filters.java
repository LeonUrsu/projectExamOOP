package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.printsConsole.FiltersPrint;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.utils.FileReferenceOOPE;

/**
 * Classe servizio per filtraggio delgi elementi presenti nel Vector CurrentForecastService.forecastDataCurrentVector.
 * filtra gli elementi e li carica sul Vector filteredVectorTime(vedere sotto esempi)
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Filters {
	
	/**
	 * Vector filteredVectorTime filtrato. il fatto che è settato static e posizionato qui ci permette di 
	 * applicare altri filtri in futuro in base ad altri valori
	 * es:filteredVectorTemperature, filteredVectorCountry e fare un ulteriore filtraggio degli elementi in 
	 * comuni tra tutti i Vector presenti con il metodo equals() o altri
	 */
	public static Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
	//public static Vector<ForecastDataCurrent> filteredVectorTemperature = new Vector<ForecastDataCurrent>();
	//public static Vector<ForecastDataCurrent> filteredVectorCountry = new Vector<ForecastDataCurrent>();

	
	
	/**
	 * metodo per il filtraggio settimanale delle nostre previsioni
	 * @param initialValue valore iniziale in secondi dalle 0:00
	 * @param finalValue valore finale in secondi dalle 0:00
	 * @return oggetto previsioni
	 * @throws IllegalArgumentException
	 * @throws IllegalTimeException
	 */
	public CurrentStats weeklyFilter(long initialValue, long finalValue) throws IllegalArgumentException, IllegalTimeException {
		return dailyFilter(initialValue, finalValue, 7);
	}
	
	
	
	/**
	 * metodo per il filtraggio su base giorni delle nostre previsioni
	 * @param initialValue valore iniziale in secondi dalle 0:00
	 * @param finalValue valore finale in secondi dalle 0:00
	 * @return oggetto previsioni
	 * @throws IllegalArgumentException
	 * @throws IllegalTimeException
	 */
	public CurrentStats dailyFilter(long initialValue, long finalValue, int days)  throws IllegalArgumentException, IllegalTimeException
	{																							//unix format per gli Value
		FiltersPrint filtersPrint = new FiltersPrint();
		if(CurrentForecastService.forecastDataCurrentVector == null || CurrentForecastService.forecastDataCurrentVector.size() == 0)
			return null;
		verifyBand(initialValue, finalValue);
		Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
		daysPeriodFiltering(initialValue, finalValue, days, CurrentForecastService.forecastDataCurrentVector, filteredVectorTime); 	
		if(filteredVectorTime.size() == 0){
			filtersPrint.print1();
			return null;
		}else
			filtersPrint.print2(filteredVectorTime.size());
		StatisticsCurrentForecasts statisticsCurrentForecasts = new StatisticsCurrentForecasts();
		return statisticsCurrentForecasts.currentStats(initialValue, finalValue, days, filteredVectorTime);
	}
	
	
	
	
	/**
	 * Metodo filtro, ci permette di filtrare elementi di un vettore e salvarli in un'altro vettore.
	 * Il filtraggio avviene per fascia oraria, ad esempio 15:00-18:00 scitti in secondi quindi 15*60*60s-18*60*60s 
	 * intesi come initialValue e finalValue, vengono filtrati 'n' giorni desiderati
	 * 
	 * @param initialValue tempo iniziale is secondi(s) dalle 0:00
	 * @param finalValue tempo finale is secondi(s) dalle 0:00
	 * @param days giorni di filtraggio dalla data piu recente che si trova nel tmpVec
	 * @param tmpVec vettore di elementi da filtrare
	 * @param filteredVector vettore di elementi filtrati
	 * @throws IllegalArgumentException
	 */
	private void daysPeriodFiltering(long initialValue, long finalValue, int days, Vector<ForecastDataCurrent> toFilterVector,
								  Vector<ForecastDataCurrent> filteredVector) throws IllegalArgumentException{
		long daysSec = days * 86400;
		long unixMax = findBiggestValue(toFilterVector);
		long unixMin = findSmallestValue(toFilterVector);
		long diff = unixMax - unixMin;
		if ( diff <= daysSec || days == 0 ) 
			throw new IllegalArgumentException();										
		for(int i=0, u=toFilterVector.size() ; i<u ; i++) {			//filtering process	
			ForecastDataCurrent tmpEle = toFilterVector.get(i);
			if(inDaysBandCheck(unixMax, tmpEle, days) && inHourBandCheck(initialValue, finalValue, tmpEle))
				filteredVector.add(tmpEle);
		}
	}
	
	
	
	/**
	 * Metodo che confronta se la previsione passata rietra nell'intervallo di giorni interessato
	 * @param finalValueDays giorno fine Meteo
	 * @param initialValueDays giorno inizio Meteo
	 * @param tmp meteo passato da controllare
	 * @return true se rienra nell'intervallo
	 */
	public boolean inDaysBandCheck(long finalValueDays, ForecastDataCurrent tmp, int days)
	{
		long initialValue = finalValueDays - (days*84600);
		long dt = tmp.getDayTime();
		if(initialValue <= dt && dt <= finalValueDays)
			return true;
		return false;
	}
	
	
	
	/**
	 * Metodo che confronta se la previsione passata rietra nell'intervallo orario interessato
	 * @param initialValue valore mimimo dell'intervallo giornaliero in s
	 * @param finalValue valore massimo dell'intervallo giornaliero in s
	 * @param tmp meteo passato da controllare
	 * @return true se rienra nell'intervallo
	 */
	public boolean inHourBandCheck(long initialValue, long finalValue, ForecastDataCurrent tmp)
	{
		long dt24 = tmp.getDayTime() % 86400;		//secondi dalle ore 0:00 della nostra previsione
		if(initialValue <= dt24 && dt24 <= finalValue )
			return true;
		return false;
	}
	
	
	
	/**
	 * Trova nel vettore delle previsoni non filtrate il valore unix chiamato 'dt'
	 * piu grande, quindi quello più recente
	 * @param vettore previsioni
	 * @return long secondi dal "1 gennaio 1970"
	 */
	private long findBiggestValue(Vector<ForecastDataCurrent> vettore)
	{
		long dt = vettore.get(0).getDayTime();
		for(int i=0, u=vettore.size() ; i<u ; i++) 
			if(dt < vettore.get(i).getDayTime())
				dt = vettore.get(i).getDayTime();
		if(dt == 0) throw new IllegalArgumentException();
		return dt;
	}
	
	
	
	/**
	 * Trova nel vettore delle previsoni non filtrate il valore unix chiamato 'dt'
	 * piu piccolo che è presente, quindi quello meno recente
	 * @param vettore previsioni ForecastDataCurrent
	 * @return long secondi dal "1 gennaio 1970"
	 * @throws IllegalArgumentException
	 */
	private long findSmallestValue(Vector<ForecastDataCurrent> vettore)
	{
		long dt = vettore.get(0).getDayTime();
		for(int i=0, u=vettore.size() ; i<u ; i++) 
			if(dt > vettore.get(i).getDayTime())
				dt = vettore.get(i).getDayTime();
		if(dt == 0) throw new IllegalArgumentException();
		return dt;
	}
	
	
	
	/**
	 * Metodo che verifica la correttezza dei valori di due parametri rientrino nel intervallo orario 0:00:00 -23:59:59
	 * @param a primo paramtetro 
	 * @param b secondo parametro
	 * @throws IllegalTimeException 
	 */
	private void verifyBand(long a, long b) throws IllegalTimeException {
		if(a<b && 0<=a && b<= 86399); 
		else throw new IllegalTimeException();
	}
	
	
	
	
	@Deprecated
	/**
	 * Metodo che filtra gli elementi, si passa un vettore di elementi e vvengono filtrati i giorni che rientrano 
	 * nell'intervallo dei gioni che si vuole filtrare
	 */
	public void dayFilter(Vector<ForecastDataCurrent> toFilterVector,Vector<ForecastDataCurrent> filteredVector, int days){
		long daysSec = days * 86400;
		long unixMin = findSmallestValue(toFilterVector);
		long unixMax = findBiggestValue(toFilterVector);
		long diff = unixMax - unixMin;
		if ( diff <= daysSec || days == 0 ) 
			throw new IllegalArgumentException();		
		for(int i=0, u=toFilterVector.size(); i<u ; i++)
			if(inDaysBandCheck(unixMax, toFilterVector.get(i), days))
				filteredVector.add(toFilterVector.get(i));
	}
	
	
	@Deprecated
	/**
	 * Metoodo che filtra gli elementi in base alle ore passate
	 */
	public void hourFilter(Vector<ForecastDataCurrent> toFilterVector, Vector<ForecastDataCurrent> filteredVector,
						   long initialValue, long finalValue){
		
	}

}
