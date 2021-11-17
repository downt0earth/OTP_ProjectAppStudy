package com.otp.OtpAssignment.service;

import java.util.List;

import javax.naming.LimitExceededException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.otp.OtpAssignment.Entities.EmailOtp;
import com.otp.OtpAssignment.Exception.EmailNotFoundException;
import com.otp.OtpAssignment.Exception.InvalidOTPException;
import com.otp.OtpAssignment.Exception.OTPExpireException;

@Service
public interface OtpService {

	public EmailOtp generateOtp(EmailOtp emailOtp) throws OTPExpireException, EmailNotFoundException;

	public ResponseEntity<String> validateOtp(EmailOtp emailotp)
			throws OTPExpireException,
			InvalidOTPException,
			LimitExceededException
			;

	public List<EmailOtp> getAllEmails();

}
