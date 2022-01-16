package it.uni.main.interfaceToUse;

import java.text.ParseException;
import java.util.Vector;

import it.uni.main.exception.IllegalTimeException;
import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;

/**
 * Classe che rappresenta che definisce dei metodi che andranno implementati obbligatoriamente 
 * nella classe Filters perchè richiesti dal cliente
 * @author Perazzoli Leonardo
 * @author Ursu Leon
 */
public interface FiltersInterface {
	
	public CurrentStats dailyFilter(String initialValueInDay, String finalValueInDay, 
			String initialValue, String finalValue)  throws IllegalArgumentException, IllegalTimeException, ParseException;
	public void daysPeriodFiltering(long initialValue, long finalValue, long initialDay, long finalDay, Vector<ForecastDataCurrent> toFilterVector,
			Vector<ForecastDataCurrent> filteredVector);
	public boolean inDaysBandCheck(long initialDay, long finalDay, ForecastDataCurrent tmp);
	public boolean inHourBandCheck(long initialValue, long finalValue, ForecastDataCurrent tmp);
	public long findBiggestValue(Vector<ForecastDataCurrent> vettore);
	public long findSmallestValue(Vector<ForecastDataCurrent> vettore);
	
}
