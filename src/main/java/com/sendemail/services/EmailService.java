package com.sendemail.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	public void sendEmail(String summary) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo("surajvns1571999@gmail.com");
		message.setSubject("Daily AI News");
		message.setText(summary);

		mailSender.send(message);
	}
}
