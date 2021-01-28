package com.java.rms.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "RATE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rateId;

	private String rateDescription;

	@NotNull
	private LocalDate rateEffectiveDate;
	@NotNull
	private LocalDate rateExpirationDate;

	@NotNull
	private int amount;

	@CreationTimestamp
	private LocalDate createdAt;

	@UpdateTimestamp
	private LocalDate updatedAt;

}
