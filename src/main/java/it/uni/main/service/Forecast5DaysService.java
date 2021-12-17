package it.uni.main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uni.main.interfaceToUse.Forecast5DayRepository;

@Service
@Transactional
public class Forecast5DaysService {
	@Autowired Forecast5DayRepository forecast5DayRepository;
	
	
}
