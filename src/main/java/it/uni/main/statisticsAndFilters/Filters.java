package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

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
	public CurrentStats weeklyFilter(long initialValue, long finalValue) throws IllegalArgumentException {
		return dailyFilter(initialValue, finalValue, 7);
	}
	

	//TO TEST
	public CurrentStats dailyFilter(long initialValue, long finalValue, int days)  throws IllegalArgumentException
	{																							//unix format per gli Value
		CurrentForecastService currentForecastService = new CurrentForecastService();
		Vector<ForecastDataCurrent> tmpVec = new Vector<ForecastDataCurrent>();
		tmpVec.addAll(currentForecastService.getForecastDataCurrentVector());					//assegno il vettore presente nella RAM
		if(tmpVec == null || tmpVec.size() == 0)
			currentForecastService.apriDaFile(FileReferenceOOPE.myFile, tmpVec );				//IDEA: posso fondere tmpVec con FilteredVec
		Vector<ForecastDataCurrent> filteredVector = new Vector<ForecastDataCurrent>();
		confrontaOre(finalValue, finalValue);
		daysPeriodFilter(initialValue, finalValue, days, tmpVec, filteredVector); 				//filtraggio TO-TEST
		StatisticsCurrentForecasts statisticsCurrentForecasts = new StatisticsCurrentForecasts();
		return statisticsCurrentForecasts.currentStats(initialValue, finalValue, days, filteredVector);
	}
	
	
	
	/**
	 * Metodo filtro, ci permette di filtrare elementi di un vettore e salvarli in un'altro vettore.
	 * Il filtraggio avviene per fascia oraria, ad esempio 15:00 - 18:00 scitti in secondi quindi 15*60*60s - 18*60*60s 
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
		long unix = findBiggestValue(toFilterVector);
		
		//look if we can filter 'n' days OR if in tmpVec there are 'n days' of forecasts //TO-TEST
		if(days!=0) {
			boolean looked = true;
			unix -= days * 86400;							//valore minimo accettabile per il filtraggio
			for(int i=0, u=toFilterVector.size(); i<u ; i++) 
				if(toFilterVector.get(i).getDayTime() <= unix )
					looked = false;
			if(looked) throw new IllegalArgumentException();
		}	
		
		//filtering process	//TO TEST																
		for(int i=0, u=toFilterVector.size() ; i<u ; i++) {
			ForecastDataCurrent tmpEle = toFilterVector.get(i);
			if(controllTimeBand(initialValue, finalValue, unix, tmpEle))
				filteredVector.add(tmpEle);
		}
	}
	
	
	
	/**
	 * Metodo che confronta se la previsone passata rietra nel'intervallo desiderato
	 * @param initialValue valore mimimo dell'intervallo
	 * @param finalValue valore massimo dell'intervallo
	 * @param unix ?
	 * @param tmp 
	 * @return
	 */
	private boolean controllTimeBand(long initialValue, long finalValue, long unix, ForecastDataCurrent tmp)
	{
		long tmpTime = tmp.getDayTime() % 86400;								//secondi dalle ore 0:00
		if(initialValue <= tmpTime && tmpTime <= finalValue && unix < tmpTime)
			return true;
		return false;
	}
	
	

	/**
	 * Trova nel vettore delle previsoni non filtrate il valore unix 
	 * della data piu grande, quindi quello più recente
	 * @param vettore
	 * @return long secondi dal "1 gennaio 1970"
	 */
	public long findBiggestValue(Vector<ForecastDataCurrent> vettore)
	{
		long dt = 0;
		for(int i=0, u=vettore.size() ; i<u ; i++) 
			if(dt < vettore.get(i).getDayTime())
				dt = vettore.get(i).getDayTime();
		if(dt == 0) throw new IllegalArgumentException();
		return dt;
	}
	
	
	
	/**
	 * Metodo che verifica la correttezza dei valori di due parametri che sono intesi come tempo in secondi dalle 0:00
	 * @param a primo paramtetro 
	 * @param b secondo parametro
	 * @throws IllegalArgumentException
	 */
	private void confrontaOre(long a, long b) throws IllegalArgumentException{ //si puo creare un tipo illegalTimeException
		if(a!=b && 0<a && a< 86400 && 1<b && b<= 86400);
		else throw new IllegalArgumentException();
	}
	
	
}
