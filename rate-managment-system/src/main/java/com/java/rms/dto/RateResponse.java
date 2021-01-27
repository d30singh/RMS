package com.java.rms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateResponse {
	private long rateId;
	
	private String rateDescription;

	private LocalDate rateEffectiveDate;

	private LocalDate rateExpirationDate;

	private int amount;

	private String surchargeResponse;
}
