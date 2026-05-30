package com.sendemail.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiSummaryService {

	private final ChatClient chatClient;

	public String summarize(String news) {

		String prompt = """
				Summarize the following AI news into
				a concise morning briefing:

				%s
				""".formatted(news);

		return chatClient.prompt().user(prompt).call().content();
	}
}
