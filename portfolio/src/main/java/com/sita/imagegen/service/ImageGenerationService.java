package com.sita.imagegen.service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ExecutionException;

@Service
public class ImageGenerationService {

    private final RestTemplate restTemplate;
    private final FirebaseService firebaseService;

    @Autowired
    public ImageGenerationService(RestTemplate restTemplate, FirebaseService firebaseService) {
        this.restTemplate = restTemplate;
        this.firebaseService = firebaseService;
    }

    public byte[] generateImage(String prompt) throws ExecutionException, InterruptedException {
        // Retrieve API key from Firebase
        String apiKey = firebaseService.getApiKey("stability_ai");

        // Replace with actual Stability AI API endpoint
        String url = "https://api.stability.ai/generate";

        // Set up request payload
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Make the request
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, requestEntity, byte[].class);

        return responseEntity.getBody();
    }
}