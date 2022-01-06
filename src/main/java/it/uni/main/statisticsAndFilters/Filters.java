package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.printsConsole.FiltersPrint;
import it.uni.main.service.CurrentForecastService;
import it.uni.main.utils.FileReferenceOOPE;


@Service
public class Filters {
	/**
	 * 	Filtra il vettore ForecastDataCurrentVector presente nella classe CurrentForecastService in 
	 * base ai parametri passati nel controller sulle rotte:@GetMapping("/filter/weekly/{initialValue}/{finalValue}") 
	 * @GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}") 
	 * e con il vettore filtrato restituisce un oggetto CurrentStats
	 */
	

	
	/**
	 * Vector filteredVectorTime filtrato. il fatto che è settato static e posizionato qui ci permette di 
	 * applicare altri filtri in futuro in base ad altri valori, e assegnare ad altri Vector 
	 * es:filteredVectorTemperature, filteredVectorCountry e fare un ulteriore filtraggio degli elementi in 
	 * comuni tra tutti i Vector presenti e non vuoti 
	 */
	public static Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
	//public static Vector<ForecastDataCurrent> filteredVectorTemperature = new Vector<ForecastDataCurrent>();
	//public static Vector<ForecastDataCurrent> filteredVectorCountry = new Vector<ForecastDataCurrent>();
<<<<<<< HEAD
	
	
	
	/**
	 * metodo per il filtraggio settimanale delle nostre previsioni
	 * @param initialValue valore iniziale in secondi dalle 0:00
	 * @param finalValue valore finale in secondi dalle 0:00
	 * @return oggetto previsioni
	 * @throws IllegalArgumentException
	 * @throws IllegalTimeException
	 */
=======
	
	
	
	
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
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
		CurrentForecastService currentForecastService = new CurrentForecastService();
		Vector<ForecastDataCurrent> tmpVec = new Vector<ForecastDataCurrent>();
<<<<<<< HEAD
		tmpVec.addAll(CurrentForecastService.forecastDataCurrentVector);	
		if(tmpVec == null || tmpVec.size() == 0)
			currentForecastService.readVectorFromFile(FileReferenceOOPE.myFile, tmpVec );		//IDEA: posso fondere tmpVec con FilteredVec
		verifyBand(initialValue, finalValue);
		Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
		daysPeriodFilter(initialValue, finalValue, days, tmpVec, filteredVectorTime); 			//filtraggio 
=======
		tmpVec.addAll(CurrentForecastService.forecastDataCurrentVector);					//assegno il vettore presente nella RAM
		if(tmpVec == null || tmpVec.size() == 0)
			currentForecastService.readVectorFromFile(FileReferenceOOPE.myFile, tmpVec );				//IDEA: posso fondere tmpVec con FilteredVec
		verifyBand(initialValue, finalValue);
		Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
		daysPeriodFilter(initialValue, finalValue, days, tmpVec, filteredVectorTime); 				//filtraggio 
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
		if(filteredVectorTime.size() == 0){
			FiltersPrint.print1();
			return null;
		}
		System.out.println(filteredVectorTime.size());
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
	private void daysPeriodFilter(long initialValue, long finalValue, int days, Vector<ForecastDataCurrent> toFilterVector,
								  Vector<ForecastDataCurrent> filteredVector) throws IllegalArgumentException{
		long daysSec = days * 86400;
		long unixMax = findBiggestValue(toFilterVector);
		long unixMin = findSmallestValue(toFilterVector);
		long diff = unixMax - unixMin;
<<<<<<< HEAD
		if ( diff >= daysSec || days == 0 ) 
			throw new IllegalArgumentException();	
=======
		if ( diff <= daysSec || days == 0 ) 
			throw new IllegalArgumentException();										
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
		for(int i=0, u=toFilterVector.size() ; i<u ; i++) {			//filtering process	
			ForecastDataCurrent tmpEle = toFilterVector.get(i);
			if(inDaysBandCheck(unixMin, unixMax, tmpEle) && inHourBandCheck(initialValue, finalValue, tmpEle))
				filteredVector.add(tmpEle);
			//System.out.println("filtering" + filteredVector.size());
		}
	}
	
	
	
	/**
	 * Metodo che confronta se la previsione passata rietra nell'intervallo di giorni interessato
	 * @param finalValueDays giorno fine Meteo
	 * @param initialValueDays giorno inizio Meteo
	 * @param tmp meteo passato da controllare
	 * @return true se rienra nell'intervallo
	 */
	public boolean inDaysBandCheck(long initialValueDays, long finalValueDays, ForecastDataCurrent tmp)
	{
		long dt = tmp.getDayTime();
		if(initialValueDays <= dt && dt <= finalValueDays)
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
	
	
	
<<<<<<< HEAD
=======
	
>>>>>>> ca74f5d261deb9eeefb76ab750fa7fe2d2f9fbeb
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
	
}
