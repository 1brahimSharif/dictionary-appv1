package com.example.dictionary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class DictionaryService {

    @Value("${merriam.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://www.dictionaryapi.com/api/v3/references/collegiate/json/";

    private final RestTemplate restTemplate;

    public DictionaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchDefinition(String word) {
        try {
            System.out.println("Calling MW API for: " + word);
            String url = BASE_URL + word + "?key=" + apiKey;
            System.out.println("Full URL: " + url); // ✅ print full URL
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("Response status: " + response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            System.err.println("ERROR in fetchDefinition: " + e.getMessage());
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

}
