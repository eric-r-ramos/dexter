package io.dexter.services.dispatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dexter.services.dispatcher.entities.Atm;

@Service
public class AtmServiceDispatcher {
	
	public static final String ING_SERVICE_URL = "https://www.ing.nl/api/locator/atms/";

	private RestTemplate restTemplate = new RestTemplate();

	public List<Atm> listAllAtm() {

		String result = restTemplate.getForObject(ING_SERVICE_URL, String.class)
				.replaceFirst("\\)\\]\\}\\'\\,\\n", "");

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

}
