package com.api.email.services;

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
public class AccountCreateService {

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private VerificationCodeRepository codeRepository;	
	

	public Boolean SendMessage(String email) throws MailException, MessagingException {
		
		
		javaMailSender.send(generateEmail(email));
		deleteVerificationCode(email);
		
		return true ;
		
	}

	
	private MimeMessage generateEmail(String email) throws MessagingException{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		String emailServer=environment.getProperty("spring.mail.username");
		
		helper.setFrom(emailServer);
		helper.setTo(email);
		helper.setSubject("La cuenta de TECNOPRACTICAS ya existe");
		helper.setText(GenerateBody(),true);
		
		return message;
	}
	
	
	@Transactional
	public void deleteVerificationCode(String email) {
		 VerificationCode verificationCode = codeRepository.findByEmail(email);
		  if (verificationCode != null) {
		   codeRepository.delete(verificationCode);
		  }
	}
	
	
	private String GenerateBody(){
		
		StringBuilder body = new StringBuilder();

		body.append("<div style=\"margin:auto; width:100%;max-width:490px;padding:0 20px; \">\r\n"
				+ "    <div style=\"width:100%;padding:40px 7px;text-align:center\">\r\n"
				+ "        <img id=\"m_155485289888041627m_7313030940910532049CompanyLogo\" src=\"https://ci4.googleusercontent.com/proxy/s8bXe0Za9fkCy5thFERylukrlhQCzZsFJxbMGz0VfymE9OQKZLewCxarVTX53GQ97vcQgCk5L103nc4dqMs3-lfp-kkFGl5fEXioW1zlbIRko496qxF_idQ1r-Kf-BDCgkQ2sda8eXtNHh0v5x8J37Mc2zGKHHL3O--GSGS8GuuuqFmN8nICIargwWbCROxwRNK7fSrv5HY1q0vwlBB6=s0-d-e1-ft#https://aadcdn.msauthimages.net/dbd5a2dd-dkse4yjoxa-j2mqzlrm-4i6wux6eyc2w2-vqrzev514/logintenantbranding/0/bannerlogo?ts=636668381988569815\" class=\"CToWUd\" data-bit=\"iit\">\r\n"
				+ "    </div>\r\n"
				+ "    <div style=\"max-width:100%;background:#252f3d;border-radius:3px 3px 0 0;padding:20px 16px;font-weight:bold;font-size:20px;color:white\">\r\n"
				+ "         <span style=\"\">La cuenta de TECNOPRACTICAS ya existe</span>\r\n"
				+ "    </div>\r\n"
				+ "    <div style=\"max-width:100%;background-color:#f8f8f8;padding:15px 16px;font-size:17px;color:rgba(22,24,35,0.75);line-height:20px\">\r\n"
				+ "        <p style=\"margin-bottom:20px\">Gracias por iniciar el proceso de creación de la nueva cuenta de TECNOPRACTICAS. Parece que ya tiene una cuenta con nosotros. Inicie sesión en su cuenta mediante la dirección de correo electrónico donde recibió este mensaje.</p>\r\n"
				+ "        <hr style=\"left: 0;\">\r\n"
				+ "        <p>Equipo de asistencia de TecnoPracticas.</p>\r\n"
				+ "    </div>\r\n"
				+ "    <div style=\"max-width:100%;padding:40px 16px 20px;font-size:15px;color:rgba(22,24,35,0.5);line-height:18px\">\r\n"
				+ "        <div>Es un correo generado automáticamente. Las respuestas enviadas a esta dirección de correo no se revisan.</div>\r\n"
				+ "    </div>\r\n"
				+ "    <div style=\"border:0;background-color:rgba(0,0,0,0.12);height:1px;margin-bottom:16px\"></div>\r\n"
				+ "    <div style=\"color:rgba(22,24,35,0.5);margin:20px 16px 40px 16px;font-size:12px;line-height:18px\">\r\n"
				+ "        <div>Fundacion universitaria tecnologico de comfenalco, Cartagena de indias, Colombia.</div>\r\n"
				+ "    </div>\r\n"
				+ "    </div>");
		return body.toString();
	}
	
	
	

	
	
}
