package io.dexter.services.atm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dexter.entities.atm.City;;

@Service
public class CityService {
	
	
	
	@Autowired
	public AtmService atmService;
	
	public List<City> findAllCityWithAtm(){
		List<String> reduceReturn = new ArrayList<String>(atmService.listAllAtm().stream().collect(Collectors.groupingBy(a -> a.getAddress().getCity())).keySet());
		List<City> cities = new ArrayList<City>();
		
		for (String city : reduceReturn) {
			cities.add(new City(city));
		}
		
		return cities;
		
	}
	

}
