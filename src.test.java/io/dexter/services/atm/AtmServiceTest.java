package io.dexter.services.atm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.dexter.entities.AtmEntity;
import io.dexter.services.AtmService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmServiceTest {

	@Autowired
	private AtmService service;
	
	@Test
	public void listAllAtmsTest() {
		List<AtmEntity> list = service.listAllAtms();
		assertNotNull(list);
		assertTrue(list.size() > 0);
		
		List<AtmEntity> listWithAmsterdam = list.stream().filter(atm -> atm.getCity().trim().equalsIgnoreCase("Amsterdam".trim()))
				.collect(Collectors.toList());
		
		//the entiry list MUST a ATM in Amsterdam
		assertNotNull(listWithAmsterdam);
		assertTrue(listWithAmsterdam.size() > 0);
		
	}
	
	@Test
	public void findAtmByCityTest() {
		String city = "Amsterdam";
		
		List<AtmEntity> list = service.findAtmByCity(city);
		assertNotNull(list);
		assertTrue(list.size() > 0);
		
	}
	
	@Test
	public void listAllCitiesTest() {
		
		List<String> list = service.listAllCities();
		assertNotNull(list);
		assertTrue(list.size() > 0);
		
		
	}

}
