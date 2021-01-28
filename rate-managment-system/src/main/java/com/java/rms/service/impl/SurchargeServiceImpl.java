package com.java.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.java.rms.exception.SurchargeException;
import com.java.rms.service.SurchargeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SurchargeServiceImpl implements SurchargeService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallbackfetchSurcharge", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),

	})
	@Override
	public String fetchSurcharge() throws SurchargeException {
		String response = null;
		try {
			response = restTemplate.getForObject("https://surcharge.free.beeceptor.com/surcharge", String.class);
		} catch (HttpClientErrorException httpClientExc) {
			log.error(String.format("Error with status code: %s", httpClientExc.getRawStatusCode()), httpClientExc);
			throw new SurchargeException("Error while fetching the surcharge detail");
		}
		return response;
	}

	public String fallbackfetchSurcharge() {
		log.info("fallback method is called");
		return "Surhage Api is down.Try again later";
	}
}
