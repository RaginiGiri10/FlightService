package com.bhushan.tx.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bhushan.tx.PdfGeneration.FlightAcknowledgementPdf;
import com.bhushan.tx.dto.FlightBookingAcknowledgement;
import com.bhushan.tx.dto.FlightBookingRequest;
import com.bhushan.tx.entity.PassengerInfo;
import com.bhushan.tx.entity.PaymentInfo;
import com.bhushan.tx.exception.InsufficientAmountException;
import com.bhushan.tx.repository.PassengerInfoRepository;
import com.bhushan.tx.repository.PaymentInfoRepository;
import com.bhushan.tx.utils.PaymentUtils;

@Service
public class FlightBookingService {

	@Autowired
	private PassengerInfoRepository passengerInfoRepository;
	@Autowired
	private PaymentInfoRepository paymentInfoRepository;

	@Transactional
	public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request)
			throws InsufficientAmountException {

		PassengerInfo passengerInfo = request.getPassengerInfo();
		passengerInfo = passengerInfoRepository.save(passengerInfo);

		PaymentInfo paymentInfo = request.getPaymentInfo();

		PaymentUtils.validateCreditLimit(paymentInfo.getAccountno(), passengerInfo.getFare());

		paymentInfo.setPassengerid(passengerInfo.getPid());
		paymentInfo.setAmount(passengerInfo.getFare());
		paymentInfoRepository.save(paymentInfo);
		FlightBookingAcknowledgement flightBookingAcknowledgement = new FlightBookingAcknowledgement("SUCCESS",
				passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo);
		FlightAcknowledgementPdf.createPdf(flightBookingAcknowledgement);
		return flightBookingAcknowledgement;

	}
}
