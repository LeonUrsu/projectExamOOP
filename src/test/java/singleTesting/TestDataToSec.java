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
	
	@BeforeEach
	void setUp() throws Exception {
		data = "14-01-2022 10:30:24";
	}

	@Test
	void test(){
		try {
			assertEquals(1642152624, filters.dataToSec(data));
		} catch (ParseException e) {
			System.out.println("sbagliato");
			e.printStackTrace();
		}
	}

}
