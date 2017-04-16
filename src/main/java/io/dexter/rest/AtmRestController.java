package io.dexter.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dexter.entities.AtmEntity;
import io.dexter.rest.dto.CityDTO;
import io.dexter.services.AtmService;

/**
 * REST controller for managing ATMs services.
 */
@RestController
@RequestMapping("/api/atm")
public class AtmRestController {
	
	@Autowired
	private AtmService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<AtmEntity> listAllAtms(){
		return service.listAllAtms();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", params="city")
	public List<AtmEntity> findAtmByCity(@RequestParam("city")String city){
		return service.findAtmByCity(city);
	}
	
	@RequestMapping(path="/listCities/", method = RequestMethod.GET, produces = "application/json")
	public List<CityDTO> listAllCities(){
		List<CityDTO> cities = new ArrayList<>();
		service.listAllCities().stream().forEach(city -> cities.add(new CityDTO(city)));
		
		return cities;
		
	}
	
	

}
