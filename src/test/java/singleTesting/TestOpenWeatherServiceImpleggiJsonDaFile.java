package singleTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonObject;

import it.uni.main.service.OpenWeatherServiceImp;
import it.uni.main.utils.FileReferenceOOPE;

class TestOpenWeatherServiceImpleggiJsonDaFile {
	OpenWeatherServiceImp openWeatherServiceImp = new OpenWeatherServiceImp();; 
	JsonObject readed = new JsonObject();
	JsonObject expected = new JsonObject();
	@BeforeEach
	void setUp() throws Exception {
		readed = openWeatherServiceImp.leggiJsonDaFile("C:\\Users\\DeskTop-L\\eclipse-workspace\\projectExamOOP-main\\src\\test\\java\\singleTesting\\testing.json"); 
		expected.addProperty("testName", 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertEquals(expected, readed);
	}

}
