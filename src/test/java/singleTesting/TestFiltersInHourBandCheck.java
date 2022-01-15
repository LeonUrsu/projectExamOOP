package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.statisticsAndFilters.Filters;

class TestFiltersInHourBandCheck {
	Filters filters = new Filters();
	ForecastDataCurrent forecastDataCurrent;
	long initialValue;
	long finalValue;
	
	
	@BeforeEach
	void setUp() throws Exception {
		forecastDataCurrent = new ForecastDataCurrent(null, null, 1640944748 , null);
		initialValue = 1;
		finalValue = 86399;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals(true, filters.inHourBandCheck(initialValue, finalValue, forecastDataCurrent));
	}
	
	
	
	@Test
	void test2() {
		
		
		
	}
	
	
	
	
	
	

}
