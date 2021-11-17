package com.otp.OtpAssignment.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.naming.LimitExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otp.OtpAssignment.Entities.EmailOtp;
import com.otp.OtpAssignment.Exception.EmailNotFoundException;
import com.otp.OtpAssignment.Exception.InvalidOTPException;
import com.otp.OtpAssignment.Exception.OTPExpireException;
import com.otp.OtpAssignment.dao.EmailDao;
import com.otp.OtpAssignment.service.OtpService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/otp")
public class MyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	private OtpService otpService;

	@GetMapping("/all-emails")
	public List<EmailOtp> getAllEmails() {
		return otpService.getAllEmails();
	}

	@PostMapping("/emailotp")
	public EmailOtp generateOtp(@RequestBody EmailOtp emailOtp) throws OTPExpireException, EmailNotFoundException {
		LOGGER.info("An INFO Message");

		return otpService.generateOtp(emailOtp);
	}

	@PostMapping("/validateotp")
	public ResponseEntity<String> validateOtp(@RequestBody EmailOtp emailOtp)
			throws OTPExpireException, InvalidOTPException, EmailNotFoundException,LimitExceededException {
		return otpService.validateOtp(emailOtp);
	}

}
