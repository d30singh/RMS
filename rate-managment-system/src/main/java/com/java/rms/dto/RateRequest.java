package com.java.rms.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {

	@NonNull
	private String rateId;

	private String rateDescription;

	@NonNull
	private LocalDateTime rateEffectiveDate;
	@NonNull
	
	private LocalDateTime rateExpirationDate;
	
	@NonNull
	private Integer amount;

}
