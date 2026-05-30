package com.sendemail.services;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RssNewsService {

	public List<String> fetchNews() throws Exception {

		List<String> feedUrls = List.of("https://techcrunch.com/category/artificial-intelligence/feed/",
				"https://techcrunch.com/feed/", "https://techcrunch.com/category/startups/feed/",
				"https://techcrunch.com/category/apps/feed/", "https://techcrunch.com/category/security/feed/");

		List<String> allTitles = new ArrayList<>();

		for (String feedUrl : feedUrls) {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(feedUrl)).GET().build();
			HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(response.body()));

			feed.getEntries().forEach(entry -> allTitles.add(entry.getTitle()));
		}

		// Remove duplicates
		List<String> uniqueTitles = allTitles.stream().distinct().collect(Collectors.toList());
		log.info("Fetched news: {}", uniqueTitles);
		return uniqueTitles;
	}
}
