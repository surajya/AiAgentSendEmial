package com.sendemail.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	public void sendEmail(String summary) throws MessagingException {

		// SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo("surajvns1571999@gmail.com");
		helper.setSubject("Daily AI News");
		helper.setText(summary);

		mailSender.send(mimeMessage);
	}
}
