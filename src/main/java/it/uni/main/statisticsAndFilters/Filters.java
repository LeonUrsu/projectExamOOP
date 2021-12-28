package it.uni.main.statisticsAndFilters;

import java.util.Vector;

import org.springframework.stereotype.Service;

import it.uni.main.model.CurrentStats;
import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.service.CurrentForecastService;


@Service
public class Filters {
	
	//	Filtra il vettore ForecastDataCurrentVector presente nella classe CurrentForecastService 
	//	in base ai parametri passati nel controller sulle rotte:
	//	@GetMapping("/filter/weekly/{initialValue}/{finalValue}")
	//	@GetMapping("/filter/daily/{initialValue}/{finalValue}/{days}")
	// 	e con il vettore filtrato restituisce un oggetto s
	
	
	//TO TEST
	public Vector<ForecastDataCurrent> weeklyFilter(long initialValue, long finalValue) throws IllegalArgumentException {
		return dailyFilter(initialValue, finalValue, 7);
	}
	

	
	//TO TEST
	public Vector<ForecastDataCurrent> dailyFilter(long initialValue, long finalValue, int days)  throws IllegalArgumentException
	{								// unix format per gli Value
		//fare apri da file se non c'è nella RAM
		Vector<ForecastDataCurrent> tmpVec = CurrentForecastService.ForecastDataCurrentVector;
		Vector<ForecastDataCurrent> filteredVector = new Vector<ForecastDataCurrent>();
		confrontaOre(finalValue, finalValue);
		daysPeriodFilter(initialValue, finalValue, days, tmpVec, filteredVector);
		//creazione statistiche poi return delle stesse
	 	return filteredVector;
	}
	
	
	
	private boolean controllTimeBand(long initialValue, long finalValue,long unix,ForecastDataCurrent tmp)
	{
		long tmpTime = tmp.getDayTime() % 86400;
		if(initialValue <= tmpTime && tmpTime <= finalValue && unix < tmpTime)
			return true;
		return false;
	}
	
	
	

	private void daysPeriodFilter(long initialValue, long finalValue, int days, Vector<ForecastDataCurrent> tmpVec,
								  Vector<ForecastDataCurrent> filteredVector) throws IllegalArgumentException{
		//find the biggest
		long unix = 0;
		for(int i=0, u=tmpVec.size() ; i<u ; i++) 
			if(unix < tmpVec.get(i).getDayTime())
			unix = tmpVec.get(i).getDayTime();
		if(unix == 0) throw new IllegalArgumentException();
		
		//look if we can filter 'n' days
		if(days!=0) {
			boolean looked = true;
			unix -= days * 86400;
			for(int i=0, u=tmpVec.size(); i<u ; i++) 
				if(tmpVec.get(i).getDayTime() <= unix )
					looked = false;
			if(looked) throw new IllegalArgumentException();
		}	
		
		//filtering
		for(int i=0, u=tmpVec.size() ; i<u ; i++) {
			ForecastDataCurrent tmpEle = tmpVec.get(i);
			if(controllTimeBand(initialValue, finalValue, unix, tmpEle))
				filteredVector.add(tmpEle);
		}
	}
	
	
	boolean confrontaOre(long a, long b) throws IllegalArgumentException{
		if(a==b)	
			return true;
		return false;
	}
	
	
}
