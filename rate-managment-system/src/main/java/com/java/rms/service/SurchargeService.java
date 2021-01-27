package com.java.rms.service;

import com.java.rms.exception.SurchargeException;

public interface SurchargeService {
	String fetchSurcharge() throws SurchargeException;
}
