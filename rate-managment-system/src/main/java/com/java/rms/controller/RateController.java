package com.java.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.rms.service.SurchargeService;

@RestController
public class RateController {
	@Autowired
	private SurchargeService SurchargeService;

	@GetMapping("/{rateId}")
	public String get() {
		return SurchargeService.fetchSurcharge();

	}

	@PostMapping
	public void save() {

	}

	@PutMapping
	public void updte() {

	}

	@DeleteMapping
	public void delete() {

	}

}
