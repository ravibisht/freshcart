package com.freshcart.service.servicesiml;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.freshcart.model.User;
import com.freshcart.service.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailServices {

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Async
	public void sendEmail(User user) {
	
		String appURL="http://localhost:8080/";
		
		
		try {
			
		MimeMessage passwordresetmail=mailSender.createMimeMessage(); 
		
		MimeMessageHelper helper = new MimeMessageHelper(passwordresetmail, "utf-8");
		
		helper.setFrom("ravi2bisht@gmail.com");
		
		helper.setTo(user.getEmail());
		
		helper.setSubject("Password Reset Request ");
		
		helper.setText("<b>You recently requested to reset your password for your Glance Your Skill account."
				+ "<br>Click the link below to reset it.</b><br>"
		        + appURL+ "/reset?reset_token=" + user.getReset_token(),true);
	    
	     mailSender.send(passwordresetmail);
	 }
		
		catch(MessagingException exception) {
			
			exception.getMessage();
		}
	}

}
