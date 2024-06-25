package com.sita.imagegen.controller;

import com.sita.imagegen.service.ImageGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ImageGenerationController {

    @Autowired
    private ImageGenerationService imageGenerationService;

    @GetMapping("/generate-image")
    public ResponseEntity<byte[]> generateImage(@RequestParam String prompt) {
        byte[] imageBytes = imageGenerationService.generateImage(prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}