package com.otp.OtpAssignment.Entities;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.otp.OtpAssignment.Entities.EmailOtp;

public class EmailOtpTest {
	@Test
	@DisplayName("Test 1")
	public void generateOtp() {
		EmailOtp emailOtp = new EmailOtp();
		emailOtp.setOtp();
		String otp = emailOtp.getOtp();
		assertTrue(otp.length() == 6);
	}

}