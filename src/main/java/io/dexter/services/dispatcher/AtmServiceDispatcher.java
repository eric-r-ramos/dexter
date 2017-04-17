package io.dexter.services.dispatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dexter.config.security.RestAuthenticationFailureHandler;
import io.dexter.services.dispatcher.entities.Atm;


/**
 * Dispatcher Design Pattern Implementation. 
 * Call the public service from ING and handle the results
 * 
 * @author ericramos
 *
 */
@Service
public class AtmServiceDispatcher {
	
	public static final String ING_SERVICE_URL = "https://www.ing.nl/api/locator/atms/";
	private static final Logger log = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);


	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Call the public service from ING
	 * 
	 * @return Atm List without filter
	 */
	public List<Atm> listAllAtm() {

		String result = restTemplate.getForObject(ING_SERVICE_URL, String.class)
				.replaceFirst("\\)\\]\\}\\'\\,\\n", "");

		try {
			return Arrays.asList(new ObjectMapper().readValue(result, Atm[].class));
		} catch (JsonParseException e) {
			log.error("Error parsing JSON results from original service");
			e.printStackTrace();
		
		} catch (JsonMappingException e) {
			log.error("Error mapping JSON results from original service");
			e.printStackTrace();
		
		} catch (IOException e) {
			log.error("Error calling original service");
			e.printStackTrace();
		}

		return null;

	}

}
