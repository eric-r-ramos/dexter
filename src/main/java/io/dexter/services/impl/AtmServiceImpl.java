package io.dexter.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dexter.entities.AtmEntity;
import io.dexter.services.AtmService;
import io.dexter.services.dispatcher.AtmServiceDispatcher;
import io.dexter.services.dispatcher.entities.Address;
import io.dexter.services.dispatcher.entities.Atm;;

/**
 * Implementation of <code>AtmService</code>
 * it uses the dispatcher services from the original service in order to get the data
 * 
 * @author ericramos
 *
 */
@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	private AtmServiceDispatcher dispatcher;

	/**
	 * This service invoke the original service (method used to return full list of ATM) and group the cities returned.
	 * It aim to get all cities that has a ATM from ING		 
	 */
	@Override
	public List<String> listAllCities() {
		List<String> cities = new ArrayList<String>(dispatcher.listAllAtm().stream()
				.collect(Collectors.groupingBy(a -> a.getAddress().getCity())).keySet());
		Collections.sort(cities);
		return cities;
	}

	
	/**
	 * Invoke dispacther and get ATM data without filter
	 * @return list of <code>AtmEntity</code>
	 */
	public List<AtmEntity> listAllAtms() {

		List<AtmEntity> list = new ArrayList<>();
		dispatcher.listAllAtm().forEach(atm -> list.add(convert(atm)));
		
		return list;
	}

	/**
	 * Invoke dispacther and get ATM data and filter it using the city parameter
	 * @param city the City used as filter
	 * @return list of <code>AtmEntity</code> 
	 */
	public List<AtmEntity> findAtmByCity(String city) {
		return listAllAtms().stream().filter(atm -> atm.getCity().trim().equalsIgnoreCase(city.trim()))
				.collect(Collectors.toList());
	}

	
	/**
	 * Convert Atm (dispatcher entity) for AtmEntity
	 * 
	 * @param atm
	 * @return AtmEmtity filled
	 */
	private AtmEntity convert(Atm atm) {
		Address address = atm.getAddress();
		if (atm != null && atm.getAddress() != null) {
			return new AtmEntity(address.getStreet(), address.getHousenumber(), address.getPostalcode(),
					address.getCity());
		}
		
		return null;
	}
}
