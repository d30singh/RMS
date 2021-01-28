package com.java.rms.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
import com.java.rms.dto.UpdateRateRequest;
import com.java.rms.service.RateService;

@RestController
public class RateController {

	@Autowired
	private RateService rateService;

	@GetMapping("/{rateId}")
	public RateResponse get(@PathVariable @NotNull Long rateId) {
		return rateService.get(rateId);
	}

	@PostMapping
	public void save(@RequestBody @Valid RateRequest rateRequest) {
		rateService.save(rateRequest);
	}

	@PutMapping
	public void update(@RequestBody @Valid UpdateRateRequest rateRequest) {
		rateService.update(rateRequest);
	}

	@DeleteMapping("/{rateId}")
	public void delete(@PathVariable @NotNull Long rateId) {
		rateService.delete(rateId);
	}
}
