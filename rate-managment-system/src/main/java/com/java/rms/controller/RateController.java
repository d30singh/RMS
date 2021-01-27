package com.java.rms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.rms.dto.RateRequest;
import com.java.rms.dto.RateResponse;
import com.java.rms.service.RateService;

import lombok.NonNull;

@RestController
public class RateController {

	@Autowired
	private RateService rateService;

	@GetMapping("/{rateId}")
	public RateResponse get(@PathVariable @NonNull Long rateId) {
		return rateService.get(rateId);
	}

	@PostMapping
	public void save(@RequestBody RateRequest rateRequest) {
		rateService.save(rateRequest);
	}

	@PutMapping
	public void update(@RequestBody RateRequest rateRequest) {
		rateService.update(rateRequest);
	}

	@DeleteMapping("/{rateId}")
	public void delete(@PathVariable @NonNull Long rateId) {
		rateService.delete(rateId);
	}
}
