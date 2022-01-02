package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.model.City;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.utils.FileReferenceOOPE;


@Service
public class Filters {
	//	Filtra il vettore ForecastDataCurrentVector presente nella classe CurrentForecastService 
	//	in base ai parametri passati nel controller sulle rotte:
	//	@GetMapping("/filter/weekly/{initialValue}/{finalValue}")
	//	@GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}")
	// 	e con il vettore filtrato restituisce un oggetto CurrentStats
	
	
	//TO TEST
	public CurrentStats weeklyFilter(long initialValue, long finalValue) throws IllegalArgumentException, IllegalTimeException {
		return dailyFilter(initialValue, finalValue, 7);
	}
	

	
	public CurrentStats dailyFilter(long initialValue, long finalValue, int days)  throws IllegalArgumentException, IllegalTimeException
	{																							//unix format per gli Value
		CurrentForecastService currentForecastService = new CurrentForecastService();
		Vector<ForecastDataCurrent> tmpVec = new Vector<ForecastDataCurrent>();
		tmpVec.addAll(currentForecastService.forecastDataCurrentVector);					//assegno il vettore presente nella RAM
		if(tmpVec == null || tmpVec.size() == 0)
			currentForecastService.apriDaFile(FileReferenceOOPE.myFile, tmpVec );				//IDEA: posso fondere tmpVec con FilteredVec
		Vector<ForecastDataCurrent> filteredVector = new Vector<ForecastDataCurrent>();
		verifyBand(finalValue, finalValue);
		daysPeriodFilter(initialValue, finalValue, days, tmpVec, filteredVector); 				//filtraggio TO-TEST
		StatisticsCurrentForecasts statisticsCurrentForecasts = new StatisticsCurrentForecasts();
		return statisticsCurrentForecasts.currentStats(initialValue, finalValue, days, filteredVector);
	}
	
	
	
	/**
	 * Metodo filtro, ci permette di filtrare elementi di un vettore e salvarli in un'altro vettore.
	 * Il filtraggio avviene per fascia oraria, ad esempio 15:00-18:00 scitti in secondi quindi 15*60*60s-18*60*60s 
	 * inoltre vengono filtrati 'n' giorni desiderati
	 * 
	 * @param initialValue tempo iniziale is secondi(s) dalle 0:00
	 * @param finalValue tempo finale is secondi(s) dalle 0:00
	 * @param days giorni di filtraggio dalla data piu recente che si trova nel tmpVec
	 * @param tmpVec vettore di elementi da filtrare
	 * @param filteredVector vettore di elementi filtrati
	 * @throws IllegalArgumentException
	 */
	private void daysPeriodFilter(long initialValue, long finalValue, int days, Vector<ForecastDataCurrent> toFilterVector,
								  Vector<ForecastDataCurrent> filteredVector) throws IllegalArgumentException{
		//find the biggest dt time in Vector
		long unixMax = findBiggestValue(toFilterVector);
		long unixMin = findSmallestValue(toFilterVector);
		if ( (unixMax - unixMin) < (days * 86400) )
			throw new IllegalArgumentException();
	
		//filtering process	//TO TEST																
		for(int i=0, u=toFilterVector.size() ; i<u ; i++) {
			ForecastDataCurrent tmpEle = toFilterVector.get(i);
			if(forecastInTimeBandCheck(unixMin, unixMax, initialValue, finalValue,days*86400 , tmpEle))
				filteredVector.add(tmpEle);
		}
	}
	
	
	
	/**
	 * Metodo che confronta se la previsone passata rietra nel'intervallo desiderato	
	 * @param finalValueDays
	 * @param initialValueDays
	 * @param initialValue valore mimimo dell'intervallo
	 * @param finalValue valore massimo dell'intervallo
	 * @param unix
	 * @param tmp
	 * @return
	 */
	private boolean forecastInTimeBandCheck(long initialValueDays, long finalValueDays, long initialValue, long finalValue, long unix, ForecastDataCurrent tmp)
	{
		long dt = tmp.getDayTime();
		long dt24 = dt % 86400;		//secondi dalle ore 0:00
		if( initialValueDays <= dt && dt <= finalValueDays && initialValue <= dt24 && dt24 <= finalValue )
			return true;
		return false;
	}
	
	
	
	/**
	 * Trova nel vettore delle previsoni non filtrate il valore unix 
	 * della data piu grande, quindi quello più recente
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
	 * Trova nel vettore delle previsoni non filtrate il valore unix 
	 * della data piu piccolo, quindi quello meno recente
	 * @param vettore previsioni
	 * @return long secondi dal "1 gennaio 1970"
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
	 * @throws IllegalArgumentException
	 */
	private void verifyBand(long a, long b) throws IllegalTimeException {
		if(a<b && 0<=a && b<= 86399);
		else throw new IllegalTimeException();
	}
	
	
}
