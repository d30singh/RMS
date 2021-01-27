package com.java.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.java.rms.service.SurchargeService;

@Service
public class SurchargeServiceImpl implements SurchargeService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String fetchSurcharge() {

		return restTemplate.getForEntity("https://surcharge.free.beeceptor.com/surcharge", String.class).getBody();

	}

}
