package com.sendemail.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sendemail.services.AiSummaryService;
import com.sendemail.services.EmailService;
import com.sendemail.services.NewsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SendEmailController {

	private final NewsService newsService;
	private final AiSummaryService aiSummaryService;
	private final EmailService emailService;

	// @Scheduled(cron = "0 0 8 * * *")
	@GetMapping("/sendMorningNews")
	public void sendMorningNews() {

		String news = newsService.getLatestAiNews();
		log.info("Fetched news: {}", news);

		String summary = aiSummaryService.summarize(news);
		log.info("Generated summary: {}", summary);

		emailService.sendEmail(summary);
	}
}
