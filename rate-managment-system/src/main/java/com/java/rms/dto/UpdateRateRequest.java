package com.java.rms.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRateRequest {
	@NotNull
	private Long rateId;


	private String rateDescription;

	@NotNull
	private String rateEffectiveDate;

	@NotNull
	private String rateExpirationDate;

	@NotNull
	private Integer amount;
}
