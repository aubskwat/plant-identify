package com.example.planthealth.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class PlantIdService {

    @Value("${plant.id.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public PlantIdService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Change this method signature to accept Map<String, Object>
    public String identifyPlant(Map<String, Object> identificationRequestBody) {
        String url = "https://plant.id/api/v3/identification";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Api-Key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(identificationRequestBody, headers);

        System.out.println("Request Body: " + identificationRequestBody);


        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        System.out.println("Response Body: " + response.getBody());


        return response.getBody();  // The response body contains the identification results
    }
}
