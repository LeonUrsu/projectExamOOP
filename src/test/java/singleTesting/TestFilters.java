package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uni.main.statisticsAndFilters.Filters;

class TestFilters {
	
	Filters filters = new Filters(); 
	long sec;
	long sec1;
	String data;
	String data1;
	String data2;
	String format;
	String format1;
	String format2;
	//1642260071
	//15-01-2022 16:21:11
	
	@BeforeEach
	void setUp() throws Exception {
		data = "15-01-2022 16:21:11";
		data1 = "16:21:11";
		data2 = "15-01-2022";
		format = "dd-MM-yyyy HH:mm:ss";
		format1 = "HH:mm:ss";
		format2 = "dd-MM-yyyy";
		sec = 1642260071;
		sec1 = 82000;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testComplete() {
		assertEquals(sec, filters.dataToSecV2(data,format) );
	}

	@Test 
	void testOre() {
		assertEquals(data1, filters.secToDataV2(sec, format1));
	}
	
	@Test
	void testData() {
		assertEquals(data2, filters.secToDataV2(sec, format2));
	}

	
	

	
}
