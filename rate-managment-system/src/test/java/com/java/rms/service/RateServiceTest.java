package com.java.rms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.java.rms.dto.RateResponse;
import com.java.rms.entity.Rate;
import com.java.rms.repo.RateRepository;
import com.java.rms.service.impl.RateServiceImpl;

@ExtendWith(SpringExtension.class)
public class RateServiceTest {

	@InjectMocks
	private RateServiceImpl rateService;
	
	@Mock
	private RateRepository rateReposiotry;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private SurchargeService surchargeService;
	

	
	@Test
	public void testGetRate() {
		String surchargeResponse = "vat 100";
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(surchargeResponse);
		Rate mockData =
				  Rate.builder().amount(10).rateDescription("Test")
				  .rateEffectiveDate(LocalDate.now()).rateExpirationDate(LocalDate.now())
				  .rateId(1l)
				  .build();
		Optional<Rate> rate = Optional.of(mockData);
		when(rateReposiotry.findById(1l)).thenReturn(rate);
		RateResponse result =rateService.get(1l);
		assertEquals(1, result.getRateId());
	}
	
}
