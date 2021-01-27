package com.java.rms.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurchargeResponse {
private int surchargeRate;
private String surchargeDescription;
}
