package com.otp.OtpAssignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.otp.OtpAssignment.Entities.EmailOtp;

@Repository
public interface EmailDao extends JpaRepository<EmailOtp,String>{
	

}
