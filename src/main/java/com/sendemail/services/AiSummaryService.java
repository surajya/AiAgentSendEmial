package com.sendemail.services;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiSummaryService {

	private final ChatClient chatClient;

	public String summarize(List<String> news) {

		String prompt = """
				You are an AI news analyst.

				Analyze these AI news headlines.

				1. Select top 5 important stories.
				2. Explain why they matter.
				3. Create a concise morning briefing.
				4. Keep under 300 words.

				News:
				%s
				""".formatted(news);

		return chatClient.prompt().user(prompt).call().content();
	}
}
