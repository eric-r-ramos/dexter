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

@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	private AtmServiceDispatcher dispatcher;

	@Override
	public List<String> listAllCities() {
		List<String> cities = new ArrayList<String>(dispatcher.listAllAtm().stream()
				.collect(Collectors.groupingBy(a -> a.getAddress().getCity())).keySet());
		Collections.sort(cities);
		return cities;
	}

	public List<AtmEntity> listAllAtms() {

		List<AtmEntity> list = new ArrayList<>();
		dispatcher.listAllAtm().forEach(atm -> list.add(convert(atm)));
		
		return list;
	}

	public List<AtmEntity> findAtmByCity(String city) {
		return listAllAtms().stream().filter(atm -> atm.getCity().trim().equalsIgnoreCase(city.trim()))
				.collect(Collectors.toList());
	}

	private AtmEntity convert(Atm atm) {
		Address address = atm.getAddress();
		if (atm != null && atm.getAddress() != null) {
			return new AtmEntity(address.getStreet(), address.getHousenumber(), address.getPostalcode(),
					address.getCity());
		}
		
		return null;
	}
}
