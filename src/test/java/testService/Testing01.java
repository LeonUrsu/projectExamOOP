package testService;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Testing01 {}//{
	//57b8994a4dd46d6b80a3f810f6882a2f
	//api.openweathermap.org/data/2.5/forecast?q=Rome&appid=57b8994a4dd46d6b80a3f810f6882a2f
	
	
//	
//	@BeforeAll
//	public void setUp() 
//	{
//	res =;
//		}
//	
//	
//	@AfterAll
//	public void tearDown() {}
//	
//	@Test
//	public void testToString(){
//	assertEquals(i1.toString(), "12,16");
//	assertEquals(i2.toString(), "1,99");
//	}}
//	
//
//	public static String callApi(String myUrl) {
//		String letto = "";
//		try {
//			URLConnection openConnection = new URL(myUrl).openConnection();
//			InputStream myIS = openConnection.getInputStream();
//			BufferedReader myBR = new BufferedReader(new InputStreamReader(myIS));	
//			try {
//				for( String linea = ""  ;  (linea = myBR.readLine()) != null  ;  letto += linea );	
//			}
//			finally	{	
//				myBR.close();	
//			}				
////			if(isObject) 
////				return (JSONObject) JSONValue.parseWithException(letto);	 //parse JSON Object	
////			else 
////				return (JSONArray) JSONValue.parseWithException(letto);	//parse JSON Array
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}	
//	return letto;
//	}
//	
//	
//}
