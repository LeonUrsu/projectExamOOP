package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.model.ForecastDataCurrent;
import it.uni.main.statisticsAndFilters.Filters;


class TestFiltersInDaysBandCheck {
	Filters filters = new Filters();
	ForecastDataCurrent forecastDataCurrent;
	long initialDaysValue;
	long finalDaysValue;
	
	@BeforeEach
	void setUp() throws Exception {
		forecastDataCurrent = new ForecastDataCurrent(null, null, 1640944748 , null);

		initialDaysValue = 1640444748;
		finalDaysValue = 1641312348;
	}

	@AfterEach
	void tearDown() throws Exception {
		forecastDataCurrent = null;
	}

	@Test
	void test() {
		assertEquals(true, filters.inDaysBandCheck(initialDaysValue, finalDaysValue, forecastDataCurrent));

	}

}
