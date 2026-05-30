package com.sendemail.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sendemail.services.AiSummaryService;
import com.sendemail.services.EmailService;
import com.sendemail.services.NewsService;
import com.sendemail.services.RssNewsService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SendEmailController {

	private final NewsService newsService;
	private final AiSummaryService aiSummaryService;
	private final EmailService emailService;
	private final RssNewsService rssNewsService;

	// @Scheduled(cron = "0 0 8 * * *")
	@GetMapping("/sendMorningNews")
	public void sendMorningNews() {

		// String news = newsService.getLatestAiNews();
		List<String> news;
		try {
			news = rssNewsService.fetchNews();
			log.info("Fetched news: {}", news);

			String summary = aiSummaryService.summarize(news);
			log.info("Generated summary: {}", summary);

			try {
				emailService.sendEmail(summary);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				log.error("Failed to send email", e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
