package com.api.email.services;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.email.models.entity.VerificationCode;
import com.api.email.repository.VerificationCodeRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

@Service
public class CodeCreationService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private VerificationCodeRepository codeRepository;	
	

	public void SendMessage(String email) throws MailException, MessagingException {
		
		Integer code=new Random().nextInt(1000000);
	
		
		javaMailSender.send(generateEmail(email, code));
		saveVerificationCode(email, code);
		 
		
	}

	
	private MimeMessage generateEmail(String email,Integer code) throws MessagingException{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String emailServer=environment.getProperty("spring.mail.username");
		
		helper.setFrom(emailServer);
		helper.setTo(email);
		helper.setSubject("Código de verificación TECNOPRACTICAS");
		
		helper.setText(GenerateBody(email,code),true);
		
		return message;
	}
	
	
	@Transactional
	public void saveVerificationCode(String email,Integer Code) {

		Date expiration = Date.from(Instant.now().plus(10, ChronoUnit.MINUTES));
		
		VerificationCode verificationCode = VerificationCode.builder()
				.email(email).codigo(Code)
				.expiracion(expiration )
				.build();
		
		if(codeRepository.existsByEmail(email)) {
			
			verificationCode =codeRepository.findByEmail(email);
			verificationCode.setCodigo(Code);
			verificationCode.setExpiracion(expiration);
			
		}

		
		codeRepository.save(verificationCode);
	}
	
	
	private String GenerateBody(String email,Integer code){
		
		StringBuilder body = new StringBuilder();

		body
		.append("<div style=\"margin:auto; width:100%;max-width:490px;padding:0 20px\">")
		.append("<div style=\"width:100%;padding:40px 7px;text-align:center\">")
			.append("<img id=\"m_7313030940910532049CompanyLogo\" src=\"https://ci4.googleusercontent.com/proxy/s8bXe0Za9fkCy5thFERylukrlhQCzZsFJxbMGz0VfymE9OQKZLewCxarVTX53GQ97vcQgCk5L103nc4dqMs3-lfp-kkFGl5fEXioW1zlbIRko496qxF_idQ1r-Kf-BDCgkQ2sda8eXtNHh0v5x8J37Mc2zGKHHL3O--GSGS8GuuuqFmN8nICIargwWbCROxwRNK7fSrv5HY1q0vwlBB6=s0-d-e1-ft#https://aadcdn.msauthimages.net/dbd5a2dd-dkse4yjoxa-j2mqzlrm-4i6wux6eyc2w2-vqrzev514/logintenantbranding/0/bannerlogo?ts=636668381988569815\" class=\"CToWUd\" data-bit=\"iit\">")
		.append("</div>")
		.append("<div style=\"max-width:100%;background-color:#f1f1f1;padding:20px 16px;font-weight:bold;font-size:20px;color:rgb(22,24,35)\">")
			.append("Código de <span class=\"il\">verificación</span>")
		.append("</div>")
		.append("<div style=\"max-width:100%;background-color:#f8f8f8;padding:15px 16px;font-size:17px;color:rgba(22,24,35,0.75);line-height:20px\">")
			.append("<p>Estimado/a <strong style=\"margin-bottom:20px;color:rgb(22,24,35);font-weight:bold; font-size: 16pt;\" >"+email+"</strong></p>")
			.append("<p style=\"margin-bottom:20px\">Para <span class=\"il\">verificar</span> tu cuenta, escribe este código en TecnoPracticas:</p>")
			.append("<p style=\"margin-bottom:20px;color:rgb(22,24,35);font-weight:bold; font-size: 20pt;\">"+code+"</p>")
			.append("<p style=\"margin-bottom:20px;color:rgb(22,24,35);font-weight:bold; font-size: 10pt;\">Este codigo caducará en 10 minutos.</p>")
			.append("<p style=\"margin-bottom:20px\">Si no solicitaste este código, puedes ignorar este mensaje.</p>")
			.append("<p>Equipo de asistencia de TecnoPracticas</p>")
		.append("</div>")
		.append("<div style=\"max-width:100%;padding:40px 16px 20px;font-size:15px;color:rgba(22,24,35,0.5);line-height:18px\">")
				.append("<div>Es un correo generado automáticamente. Las respuestas enviadas a esta dirección de correo no se revisan.</div>")
		.append("</div>")
			.append("<div style=\"border:0;background-color:rgba(0,0,0,0.12);height:1px;margin-bottom:16px\"></div>")
			.append("<div style=\"color:rgba(22,24,35,0.5);margin:20px 16px 40px 16px;font-size:12px;line-height:18px\">")
				.append("<div>Fundacion universitaria tecnologico de comfenalco, Cartagena de indias, Colombia.</div>")
			.append("</div>")
		.append("</div>");
		
		return body.toString();
	}
	
	
	

	
	

}
