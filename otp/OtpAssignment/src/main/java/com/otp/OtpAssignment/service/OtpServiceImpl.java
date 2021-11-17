package com.otp.OtpAssignment.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.naming.LimitExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

import com.otp.OtpAssignment.Entities.EmailOtp;
import com.otp.OtpAssignment.Exception.EmailNotFoundException;
import com.otp.OtpAssignment.Exception.InvalidOTPException;
import com.otp.OtpAssignment.Exception.OTPExpireException;
import com.otp.OtpAssignment.dao.EmailDao;

@RestController
public class OtpServiceImpl implements OtpService {
	@Value("${duration}")
	public int time_duration;
	@Value("${attempts}")
	public int max_attempts;
	@Autowired
	private EmailDao emailDao;

	@Autowired
	private JavaMailSender javaMailSender;

	void sendOtpToEmail(String email, String otp) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject("Email otp sender");
		msg.setText("This Otp " + otp + "  will expire in 2 minutes ");
		javaMailSender.send(msg);
	}

	@Override
	public EmailOtp generateOtp(EmailOtp emailOtp) throws OTPExpireException, EmailNotFoundException {

		emailOtp.setOtp();
		emailOtp.setGenerationTime();
		emailOtp.setExpiryTime(emailOtp.getGenerationTime().plusMinutes(time_duration));

		if (validateEmail(emailOtp.getEmail()) == true) {
			sendOtpToEmail(emailOtp.getEmail(), emailOtp.getOtp());
		} else {
			throw new EmailNotFoundException("Email id was not correct");
		}

		return emailDao.save(emailOtp);
	}

	public boolean validateEmail(String email) {
		String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		if (email.matches(regex))
			return true;
		else
			return false;

	}

	@Override
	public ResponseEntity<String> validateOtp(EmailOtp emailOtp)
			throws OTPExpireException, InvalidOTPException, LimitExceededException {

		EmailOtp email = emailDao.findById(emailOtp.getEmail()).orElse(null);
		if (email.getAttempt() >= max_attempts) {
			throw new LimitExceededException("Number of attempts exceeded");
		}

		email.setAttempt(email.getAttempt() + 1);

		emailDao.save(email);

		if (email != null) {

			if (email.getOtp().equals(emailOtp.getOtp())) {

				LocalDateTime start = email.getGenerationTime();
				LocalDateTime end = email.getGenerationTime().plusMinutes(time_duration);
				LocalDateTime curr = LocalDateTime.now();
				if (ChronoUnit.MINUTES.between(start, curr) <= time_duration
						&& ChronoUnit.MINUTES.between(curr, end) >= 0) {
					email.setAttempt(0);
					emailDao.save(email);
					return new ResponseEntity<String>("Otp entered is correct", HttpStatus.OK);
				} else
					throw new OTPExpireException("Otp entered is expired");
			}
			throw new InvalidOTPException("Otp entered was invalid");
		}
		return new ResponseEntity<String>("Email id not recieved", HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<EmailOtp> getAllEmails() {
		return emailDao.findAll();
	}

}
