package com.otp.OtpAssignment.Entities;

import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class EmailOtp {
	@Id
	private String email;
	private String otp;
	private LocalDateTime generationTime;
	private LocalDateTime expiryTime;
	private int attempt;
//	private LocalDateTime lastOtpGeneratedTime;
	

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}


	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	public EmailOtp(String email) {
		this.email = email;
	}

	public EmailOtp(String email, String otp) {
		this.email = email;
		this.otp = otp;
	}

	public EmailOtp() {
		super();
		this.attempt=0;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp() {
		this.otp = String.valueOf(new Random().nextInt(900000) + 100000);
	}

	public LocalDateTime getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime() {
		this.generationTime = LocalDateTime.now();
	}

}
