package com.java.rms.dto;

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
	
	private Long rateId;

	private String rateDescription;

	@NonNull
	private String rateEffectiveDate;
	@NonNull
	
	private String rateExpirationDate;
	
	@NonNull
	private Integer amount;

}
