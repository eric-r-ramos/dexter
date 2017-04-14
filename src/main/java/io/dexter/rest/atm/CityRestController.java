package io.dexter.rest.atm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dexter.entities.atm.City;
import io.dexter.services.atm.CityService;

/**
 * REST controller for managing Cities with ATM services.
 */
@RestController
@RequestMapping("/api/city")
public class CityRestController {
	
	@Autowired
	private CityService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<City> listAllCities(){
		
		return service.findAllCityWithAtm();
		
	}

}

