package com.otp.OtpAssignment.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class LimitExceededException extends Exception{
private static final long serialVersionUID=1L;
	
	public LimitExceededException(String errorMessage) {
		super(errorMessage);
	}
}
