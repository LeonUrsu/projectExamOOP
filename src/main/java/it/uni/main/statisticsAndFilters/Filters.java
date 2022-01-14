package it.uni.main.statisticsAndFilters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.TimeZone;
import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.printsConsole.FiltersPrint;
import it.uni.main.utils.ParamVariable;

/**
 * Classe servizio per filtraggio delgi elementi presenti nel Vector CurrentForecastService.forecastDataCurrentVector.
 * filtra gli elementi e li carica sul Vector filteredVectorTime(vedere sotto esempi)
 * @author Perazzoli Leonardo 
 * @author Ursu Leon 
 */
@Service
public class Filters {
	
	public static Vector<ForecastDataCurrent> toFilterVector = new Vector<ForecastDataCurrent>();
	
	
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
	 * metodo per il filtraggio su base giorni delle nostre previsioni
	 * @param initialValue valore iniziale in secondi dalle 0:00
	 * @param finalValue valore finale in secondi dalle 0:00
	 * @return oggetto previsioni
	 * @throws IllegalArgumentException
	 * @throws IllegalTimeException
	 * @throws ParseException 
	 */
	public CurrentStats dailyFilter(String start, String stop)  throws IllegalArgumentException, IllegalTimeException, ParseException
	{	
		if(toFilterVector == null || toFilterVector.size() == 0)
			return null;
		long initialValue = timeConverterTime(start); 
		long finalValue = timeConverterTime(stop);
		long initialDay = timeConverterDate(start);
		long finalDay= timeConverterDate(stop);
		FiltersPrint filtersPrint = new FiltersPrint();
		verifyBand(initialValue, finalValue, initialDay, finalDay);
		Vector<ForecastDataCurrent> filteredVectorTime = new Vector<ForecastDataCurrent>();
		daysPeriodFiltering(initialValue,finalValue,initialDay,finalDay, toFilterVector, filteredVectorTime); 	
		if(filteredVectorTime.size() == 0){
			filtersPrint.print1();
			return null;
		}else
			filtersPrint.print2(filteredVectorTime.size());
		StatisticsCurrentForecasts statisticsCurrentForecasts = new StatisticsCurrentForecasts();
		return statisticsCurrentForecasts.currentStats(initialValue, finalValue, initialDay, finalDay, filteredVectorTime);
	}
	
	
	
	/**
	 * Metodo filtro, ci permette di filtrare elementi di un vettore e salvarli in un'altro vettore.
	 * Il filtraggio avviene per fascia oraria, ad esempio 15:00-18:00 scitti in secondi quindi 15*60*60s-18*60*60s 
	 * intesi come initialValue e finalValue, vengono filtrati 'n' giorni desiderati
	 * @param initialValue tempo iniziale is secondi(s) dalle 0:00
	 * @param finalValue tempo finale is secondi(s) dalle 0:00
	 * @param days giorni di filtraggio dalla data piu recente che si trova nel tmpVec
	 * @param tmpVec vettore di elementi da filtrare
	 * @param filteredVector vettore di elementi filtrati
	 * @throws IllegalArgumentException
	 */
	private void daysPeriodFiltering(long initialValue, long finalValue, long initialDay, long finalDay, Vector<ForecastDataCurrent> toFilterVector,
								  Vector<ForecastDataCurrent> filteredVector) throws IllegalArgumentException{
		long unixMax = findBiggestValue(toFilterVector);
		long unixMin = findSmallestValue(toFilterVector);
		if (unixMin > initialDay || finalDay > unixMax) {
			FiltersPrint filtersPrint = new FiltersPrint();
			filtersPrint.print3(toFilterVector.size());
			throw new IllegalArgumentException();	
		}								
		for(int i=0, u=toFilterVector.size() ; i<u ; i++) {	//filtering process	
			ForecastDataCurrent tmpEle = toFilterVector.get(i);
			if(inDaysBandCheck(initialDay+initialValue, finalDay+finalValue, tmpEle) && inHourBandCheck(initialValue, finalValue, tmpEle))
				filteredVector.add(tmpEle);
		}
	}
	
        
	
	
	/**
	 * Metodo che confronta se la previsione passata rietra nell'intervallo di giorni interessato
	 * ad esempio: 14/01/2022 <= X <= 16/01/2022 (tutto in long)
	 * @param finalValueDays giorno fine Meteo
	 * @param initialValueDays giorno inizio Meteo
	 * @param tmp meteo passato da controllare
	 * @return true se rienra nell'intervallo
	 */
	public boolean inDaysBandCheck(long initialDay, long finalDay, ForecastDataCurrent tmp){
		long dt = tmp.getDayTime();
		if(initialDay <= dt && dt <= finalDay)
			return true;
		return false;
	}
	
	
	
	/**
	 * Metodo che confronta se la previsione passata rietra nell'intervallo orario interessato
	 * ad esempio 15:00 <= X <= 21:30 (tutto in long)
	 * @param initialValue valore mimimo dell'intervallo giornaliero in s
	 * @param finalValue valore massimo dell'intervallo giornaliero in s
	 * @param tmp meteo passato da controllare
	 * @return true se rienra nell'intervallo
	 */
	public boolean inHourBandCheck(long initialValue, long finalValue, ForecastDataCurrent tmp){
		long dt24 = tmp.getDayTime()%86400;	//secondi dalle ore 0:00 della nostra previsione
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
	private long findBiggestValue(Vector<ForecastDataCurrent> vettore){
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
	private long findSmallestValue(Vector<ForecastDataCurrent> vettore){
		long dt = vettore.get(0).getDayTime();
		for(int i=0, u=vettore.size() ; i<u ; i++) 
			if(dt > vettore.get(i).getDayTime())
				dt = vettore.get(i).getDayTime();
		if(dt == 0) throw new IllegalArgumentException();
		return dt;
	}
	
	
	
	/**
	 * Metodo che verifica se la correttezza dei valori di 4 parametri rientrino nel intervallo orario 0:00:00 -23:59:59 e confronta l
	 * @param a primo paramtetro orario
	 * @param b secondo parametro orario
	 * @param c primo parametro giorno
	 * @param d secondo parametro giorno
	 * @throws IllegalTimeException 
	 */
	private void verifyBand(long a, long b , long c,long d) throws IllegalTimeException {
		if(a<b && a >= 0 && b <= 86399 && c <= d);
		else throw new IllegalTimeException();
	}


	
	
	@Deprecated
	/**
	 * Metoodo che filtra gli elementi in base alle ore passate
	 */
	public void hourFilter(Vector<ForecastDataCurrent> toFilterVector, Vector<ForecastDataCurrent> filteredVector,
						   long initialValue, long finalValue){
		
	}

	/**
	 * 
	 * @param dtTxt
	 * @return
	 * @throws ParseException
	 */
	public long dataToSec(String dtString ,String format) throws ParseException{
		  DateFormat df = new SimpleDateFormat(ParamVariable.dataFormat);
		  Date dt = df.parse(dtString);
		  long epoch = dt.getTime();
		  epoch += TimeZone.getDefault().getOffset(0);
		  return (epoch/1000);
	 }
	
	/**
	 * Metodo che ci restitusce formato GMT+1 in stringa di un formato unix del tempo
	 * @param unixTime tempo in unix
	 * @return UTC in stringa
	 */
	public String secToData(long unixTime) {
		unixTime += TimeZone.getDefault().getOffset(0)/1000;//Settato il GMT+1
		Date dateTime = new java.util.Date((long) Double.valueOf(unixTime).longValue() * 1000);
		DateFormat df = new SimpleDateFormat(ParamVariable.dataFormat);
		String reportDate = df.format(dateTime);
		return reportDate;
	}
	

	
	public long timeConverterTime(String myDate) {
		LocalDateTime localDateTime = LocalDateTime.parse(myDate, DateTimeFormatter.ofPattern(ParamVariable.dataFormat) );
		long millis = localDateTime.toLocalTime().toSecondOfDay();
		return millis;
	}
	
	
	
	public long timeConverterDate(String myDate) {
		LocalDateTime localDateTime = LocalDateTime.parse(myDate, DateTimeFormatter.ofPattern(ParamVariable.dataFormat) );
		long millis = localDateTime.toLocalDate().toEpochDay()*86400;
		return millis;
	}
	
	
	
}
