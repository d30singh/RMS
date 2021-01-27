package com.java.rms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="RATE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long rateId;
	  
	  private String rateDescription;
	  
	  private LocalDate rateEffectiveDate;
	  
	  private LocalDate rateExpirationDate;
	  
	  private int amount;
	  
	  @CreationTimestamp
	  private LocalDate createdAt;

	  @UpdateTimestamp
	  private LocalDate updatedAt;
	  
	  

}
