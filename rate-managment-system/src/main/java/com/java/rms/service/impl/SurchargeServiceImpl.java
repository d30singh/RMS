package com.java.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.java.rms.exception.SurchargeException;
import com.java.rms.service.SurchargeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SurchargeServiceImpl implements SurchargeService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String fetchSurcharge() throws SurchargeException{
		String response = null;
		try {
			response = restTemplate.getForEntity("https://surcharge.free.beeceptor.com/surcharge", String.class)
					.getBody();
		} catch (HttpClientErrorException httpClientExc) {
			log.error(String.format("Error with status code: %s", httpClientExc.getRawStatusCode()), httpClientExc);
			throw new SurchargeException("Error while fetching the surcharge detail");
		}
		return response;
	}

}
