package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.statisticsAndFilters.Filters;

class TestDataToSec {
	
	Filters filters = new Filters(); 
	long sec;
	String data;
	String format;
	
	@BeforeEach
	void setUp() throws Exception {
		data = "14-01-2022 10:30:24";
		format = "00-00-00 HH:mm:ss";
	}

	@Test
	void test() throws ParseException{
		assertEquals(1642152624, filters.dataToSec(data, format));
	}
	
	@Test
	void test1() throws ParseException{
		assertEquals(37824, filters.dataToSec(data, format));
	}
}

