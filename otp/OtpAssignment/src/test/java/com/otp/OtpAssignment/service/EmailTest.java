package com.otp.OtpAssignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmailTest {

	@Test
	@DisplayName("Email Validation")
	public void EmailTest1() {
		OtpServiceImpl otpServiceImpl = new OtpServiceImpl();
		boolean expected = true;
		boolean actual = otpServiceImpl.validateEmail("piyush.18bcs1072@abes.ac.in");
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Email correct validation")
	public void EmailTest2() {
		OtpServiceImpl otpServiceImpl = new OtpServiceImpl();
		boolean expected = false;
		boolean actual = otpServiceImpl.validateEmail("3432223frdddfd.in");
		assertEquals(expected, actual);

	}

}
