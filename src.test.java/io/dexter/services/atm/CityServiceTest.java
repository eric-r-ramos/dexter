package io.dexter.services.atm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import io.dexter.config.TestConfig;
import io.dexter.services.atm.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class CityServiceTest {

	@Autowired
	private CityService service;
	
	@Test
	public void reachOriginalServicetest() {
		service.findAllCityWithAtm();
		
		
	}

}
