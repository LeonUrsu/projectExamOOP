package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.statisticsAndFilters.Filters;

class TestFilters {
	
	Filters filters = new Filters(); 
	long sec;
	String data;
	String format;
	//1642260071
	//15-01-2022 16:21:11
	
	@BeforeEach
	void setUp() throws Exception {
		data = "15-01-2022 16:21:11";
		sec = 1642260071;
		format = "dd-MM-yyyy HH:mm:ss";
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void test1() {
		assertEquals(1642260071, filters.dataToSecV2(data,format) );
	}

	@Test
	void test2() {
		assertEquals(data, filters.secToDataV2(sec));
	}

	
	
}
