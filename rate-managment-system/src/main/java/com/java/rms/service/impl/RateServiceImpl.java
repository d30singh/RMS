package com.java.rms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import com.java.rms.dto.RateRequest;
import com.java.rms.dto.RateResponse;
import com.java.rms.entity.Rate;
import com.java.rms.repo.RateRepository;
import com.java.rms.service.RateService;
import com.java.rms.service.SurchargeService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RateServiceImpl implements RateService {

	@Autowired
	private RateRepository rateRepository;

	@Autowired
	private SurchargeService SurchargeService;

	@Override
	public RateResponse get(long rateId) {
		Rate rate = rateRepository.findById(rateId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RateId not found in RMS"));
		try {
			SurchargeService.fetchSurcharge();
		} catch (HttpClientErrorException httpClientEx) {
			log.error("Error while invoking surcharge service", httpClientEx);
		}
		return RateResponse.builder().amount(rate.getAmount()).rateDescription(rate.getRateDescription())
				.rateEffectiveDate(rate.getRateEffectiveDate()).rateExpirationDate(rate.getRateExpirationDate())
				.rateId(rate.getRateId()).build();
	}

	@Override
	public void save(RateRequest rateRequest) {
		try {
			Rate rate = Rate.builder().amount(rateRequest.getAmount()).rateDescription(rateRequest.getRateDescription())
					.rateEffectiveDate(rateRequest.getRateEffectiveDate())
					.rateExpirationDate(rateRequest.getRateExpirationDate()).build();
			rateRepository.save(rate);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error. Please contact admin");
		}
	}

	@Override
	public void update(RateRequest rateRequest) {
		Rate rate = rateRepository.findById(Long.valueOf(rateRequest.getRateId()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Internal server error. Please contact admin"));
		try {
			rate = Rate.builder().rateId(rate.getRateId()).amount(rateRequest.getAmount())
					.rateDescription(rateRequest.getRateDescription())
					.rateEffectiveDate(rateRequest.getRateEffectiveDate())
					.rateExpirationDate(rateRequest.getRateExpirationDate()).build();
			rateRepository.save(rate);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error. Please contact admin");
		}
	}

	@Override
	public void delete(long rateId) {
		if (!rateRepository.existsById(rateId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RateId not found in RMS");
		}
		rateRepository.deleteById(rateId);
	}

}
