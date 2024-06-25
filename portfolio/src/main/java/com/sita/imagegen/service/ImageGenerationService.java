package com.sita.imagegen.service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageGenerationService {

    private final RestTemplate restTemplate;

    public ImageGenerationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] generateImage(String prompt) {
        String url = "https://api.stability.ai/generate";
        String apiKey = "";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, requestEntity, byte[].class);

        return responseEntity.getBody();
    }
}