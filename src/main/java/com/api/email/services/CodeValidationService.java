package com.api.email.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.email.models.entity.VerificationCode;
import com.api.email.repository.VerificationCodeRepository;

@Service
public class CodeValidationService {

	
	@Autowired
	private VerificationCodeRepository codeRepository;
	
	
	public Boolean validaionCode(VerificationCode verificationCode) {
		
		String email =verificationCode.getEmail();
		Integer code= verificationCode.getCodigo();
		
		if(codeRepository.existsByEmail(email)&& validateExpirationCode(email) ) {
			
			VerificationCode verificationExist=codeRepository.findByEmail(email);
			
			return verificationExist.getCodigo().equals(code);
		}
		
		return false;
	}
	

	
	
	private Boolean validateExpirationCode(String email) {
		
		Date expiration=codeRepository.findByEmail(email).getExpiracion();
		
		return expiration.after(new Date());
		
	}
}
