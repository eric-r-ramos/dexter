package io.dexter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.dexter.services.atm.AtmService;
import io.dexter.services.atm.CityService;

@Configuration
@ComponentScan
public class TestConfig {

    
//    @Bean
//    public CityService getCityService() {
//    	return new CityService();
//    }
//    
//    @Bean
//    public AtmService getAtmService() {
//    	return new AtmService();
//    }

}