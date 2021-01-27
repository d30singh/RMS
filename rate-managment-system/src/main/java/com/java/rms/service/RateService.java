package com.java.rms.service;

import com.java.rms.dto.RateRequest;
import com.java.rms.dto.RateResponse;

public interface RateService {
	RateResponse get(long rateId);

	void save(RateRequest rateRequest);

	void update(RateRequest rateRequest);

	void delete(long rateId);

}
