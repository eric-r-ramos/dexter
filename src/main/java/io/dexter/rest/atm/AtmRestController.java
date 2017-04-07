package io.dexter.rest.atm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dexter.entities.atm.Atm;
import io.dexter.services.atm.AtmService;

/**
 * REST controller for managing ATMs services.
 */
@RestController
@RequestMapping("/api")
public class AtmRestController {
	
	@Autowired
	private AtmService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Atm> listAllAtms(){
		
		return service.listAllAtm();
		
	}
	
	@RequestMapping(path="/{city}", method = RequestMethod.GET, produces = "application/json")
	public List<Atm> findAtmByCity(@PathVariable("city")String city){
		return service.findAtmByCity(city);
	}

}
