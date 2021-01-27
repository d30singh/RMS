package com.java.rms.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.java.rms.dto.RateRequest;
import com.java.rms.dto.RateResponse;
import com.java.rms.entity.Rate;
import com.java.rms.exception.SurchargeException;
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
		String surchargeResponse = null;
		try {
			surchargeResponse = SurchargeService.fetchSurcharge();
		} catch (SurchargeException surchargeExc) {
			log.error("Error while invoking surcharge service", surchargeExc);
		}
		return RateResponse.builder().amount(rate.getAmount()).rateDescription(rate.getRateDescription())
				.rateEffectiveDate(rate.getRateEffectiveDate()).rateExpirationDate(rate.getRateExpirationDate())
				.rateId(rate.getRateId()).surchargeResponse(surchargeResponse).build();
	}

	@Override
	public void save(RateRequest rateRequest) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			Rate rate = Rate.builder().amount(rateRequest.getAmount()).rateDescription(rateRequest.getRateDescription())
					.rateEffectiveDate(LocalDate.parse(rateRequest.getRateEffectiveDate(), formatter))
					.rateExpirationDate(LocalDate.parse(rateRequest.getRateExpirationDate(), formatter)).build();
			rateRepository.save(rate);

		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error. Please contact admin");
		}

	}

	@Override
	public void update(RateRequest rateRequest) {
		Rate rate = rateRepository.findById(rateRequest.getRateId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Internal server error. Please contact admin"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			rate = Rate.builder().rateId(rate.getRateId()).amount(rateRequest.getAmount())
					.rateDescription(rateRequest.getRateDescription())
					.rateEffectiveDate(LocalDate.parse(rateRequest.getRateEffectiveDate(), formatter))
					.rateExpirationDate(LocalDate.parse(rateRequest.getRateExpirationDate(), formatter))
					.createdAt(rate.getCreatedAt()).build();
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
