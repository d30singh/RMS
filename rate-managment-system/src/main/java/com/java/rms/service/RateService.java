package com.java.rms.service;

import com.java.rms.dto.RateRequest;
import com.java.rms.dto.RateResponse;
import com.java.rms.dto.UpdateRateRequest;

public interface RateService {
	RateResponse get(long rateId);

	void save(RateRequest rateRequest);

	void update(UpdateRateRequest rateRequest);

	void delete(long rateId);

}
