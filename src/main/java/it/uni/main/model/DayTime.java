package it.uni.main.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

public class DayTime {
	
	private long unixTime;
	
	public String dtConvert(long unixTime) {
	
	this.unixTime = unixTime;
	
	final DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	final String formattedDtm = Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT-4")).format(formatter);
	
	return formattedDtm;
	
	}
}
