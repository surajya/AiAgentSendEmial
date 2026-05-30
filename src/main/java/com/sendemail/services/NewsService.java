package com.sendemail.services;

import org.springframework.stereotype.Service;

@Service
public class NewsService {

	public String getLatestAiNews() {

		return """
				1. Microsoft released new AI features.
				2. Google expanded Gemini capabilities.
				3. Anthropic announced enterprise updates.
				4. OpenAI launched new API improvements.
				""";
	}
}