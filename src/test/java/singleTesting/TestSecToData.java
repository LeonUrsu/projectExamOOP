package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.statisticsAndFilters.Filters;

class TestSecToData {

	Filters filters = new Filters();
	String data;
	long sec;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		sec = 1642152624;	
	}
	
	@Test
	void test() {
		assertEquals("14-01-2022 10:30:24", filters.secToData(sec));
	}

}
