package io.dexter.services;

import java.util.List;

import io.dexter.entities.AtmEntity;

public interface AtmService {

	List<String> listAllCities();
	
	List<AtmEntity> listAllAtms();

	List<AtmEntity> findAtmByCity(String city);

}