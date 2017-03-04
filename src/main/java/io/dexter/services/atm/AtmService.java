package io.dexter.services.atm;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dexter.entities.atm.Atm;

@Service
public class AtmService {

	private RestTemplate restTemplate = new RestTemplate();
	private String urlRest = "https://www.ing.nl/api/locator/atms/";
	
			
	public List<Atm> listAllAtm(){
		
		String result = restTemplate.getForObject(urlRest, String.class).replaceFirst("\\)\\]\\}\\'\\,\\n", "");
		
		try {
			return Arrays.asList(new ObjectMapper().readValue(result, Atm[].class));
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
				
	}
	
	

	public List<Atm> findAtmByCity (String city){
		
		return listAllAtm().stream().filter(atm -> atm.getAddress().getCity().equals(city)).collect(Collectors.toList());
		
	}
	
}
