package io.dexter.rest.atm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dexter.entities.atm.Atm;
import io.dexter.entities.atm.AtmDTO;
import io.dexter.services.atm.AtmService;

/**
 * REST controller for managing ATMs services.
 */
@RestController
@RequestMapping("/api/atm")
public class AtmRestController {
	
	@Autowired
	private AtmService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<AtmDTO> listAllAtms(){
		return this.mapAtmToAtmDTO(service.listAllAtm());
		
	}
	
	@RequestMapping(path="/city/{city}", method = RequestMethod.GET, produces = "application/json")
	public List<AtmDTO> findAtmByCity(@PathVariable("city")String city){
		return this.mapAtmToAtmDTO(service.findAtmByCity(city));
	}
	
	
	
	private List<AtmDTO> mapAtmToAtmDTO (List<Atm> listAtm){
		
		List<AtmDTO> returnList = new ArrayList<AtmDTO>();
		
		for (Atm atm : listAtm) {
			returnList.add(
				new AtmDTO(
					atm.getAddress().getStreet(), 
					atm.getAddress().getHousenumber(),
					atm.getAddress().getPostalcode(),
					atm.getAddress().getCity()
				)
			); 
		}
		return returnList;
	}

}
