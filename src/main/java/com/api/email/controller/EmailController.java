package com.api.email.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.email.models.entity.VerificationCode;
import com.api.email.repository.VerificationCodeRepository;
import com.api.email.services.AccountCreateService;
import com.api.email.services.CodeCreationService;
import com.api.email.services.CodeValidationService;

import jakarta.mail.MessagingException;

@RestController()
@RequestMapping(value = "/mail")
public class EmailController {

	
	@PostMapping("/enviar")
	public  ResponseEntity<?>  enviarCorreo(@RequestParam("email") String email) throws MessagingException{
		
		codeCreationService.SendMessage(email);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/verifications")
	public List<VerificationCode>  getverifications(){
		
		return codeRepository.findAll();
	}
	
	@PostMapping(value ="/verifier")
	public ResponseEntity<?> verifierCode(@RequestBody VerificationCode verificationCode) {
		System.out.println(verificationCode.getEmail());
		
		return ResponseEntity.ok(codeValidationService.validaionCode(verificationCode));
	}
	
	@GetMapping(value = "/account-created")
	public ResponseEntity<?> accountCreated(@RequestParam(name = "email")String email) throws MailException, MessagingException{
		
		accountCreateService.SendMessage(email);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@Autowired
	private CodeCreationService codeCreationService;
	
	@Autowired
	private CodeValidationService codeValidationService;
	
	@Autowired
	private VerificationCodeRepository codeRepository;
	
	@Autowired
	private AccountCreateService accountCreateService;
	
}
