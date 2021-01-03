package com.bhushan.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhushan.tx.dto.FlightBookingAcknowledgement;
import com.bhushan.tx.dto.FlightBookingRequest;
import com.bhushan.tx.exception.InsufficientAmountException;
import com.bhushan.tx.service.FlightBookingService;

@RestController
public class FlightBookingController {

	@Autowired
	private FlightBookingService service;

	@PostMapping("/bookFlightTicket")
	public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request)
			throws InsufficientAmountException {
		return service.bookFlightTicket(request);
	}
}
