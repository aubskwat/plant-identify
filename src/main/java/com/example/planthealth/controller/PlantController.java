package com.example.planthealth.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.planthealth.service.PlantIdService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Controller
@RequestMapping("/plant")
public class PlantController {

    private final PlantIdService plantIdService;

    public PlantController(PlantIdService plantIdService) {
        this.plantIdService = plantIdService;
    }

    @GetMapping("/identify")
    public String showPlantIdentificationForm() {
        return "plant-identify"; // Returns the HTML page (in templates directory) with the form
    }

    @PostMapping("/identify")
    public String identifyPlantByImage(@RequestParam String imageBlob, Model model) {

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("images", Arrays.asList(imageBlob));
        try {
            String response = plantIdService.identifyPlant(requestBody);

            model.addAttribute("plantResult", response);
            // return "plant-identify"; // Return the same view with the result
            return "redirect:/plant/result?jsonResult="
                    + URLEncoder.encode(response, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            model.addAttribute("error", "Error processing image: " + e.getMessage());
            return "plant-identify";
        }

    }

    @GetMapping("/result")
    public String showResult(@RequestParam String jsonResult, Model model) {
        try {
            // Parse the JSON string to an object
            ObjectMapper mapper = new ObjectMapper();
            PlantResult result = mapper.readValue(jsonResult, PlantResult.class);

            model.addAttribute("result", result);
            return "plant-result";

        } catch (Exception e) {
            model.addAttribute("error", "Error processing result: " + e.getMessage());
            return "plant-identification";
        }
    }

    // Data classes to parse the JSON result
    @Data
    public static class PlantResult {
        private String access_token;
        private String model_version;
        private String custom_id;
        private Input input;
        private Result result;
        private String status;
        private boolean sla_compliant_client;
        private boolean sla_compliant_system;
        private double created;
        private double completed;
    }

    @Data
    public static class Input {
        private Double latitude;
        private Double longitude;
        private List<String> images;
        private String datetime;
    }

    @Data
    public static class Result {
        private Classification classification;
        private IsPlant is_plant;
    }

    @Data
    public static class Classification {
        private List<Suggestion> suggestions;
    }

    @Data
    public static class Suggestion {
        private String id;
        private String name;
        private double probability;
        private Details details;
    }

    @Data
    public static class Details {
        private String language;
        private String entity_id;
    }

    @Data
    public static class IsPlant {
        private double probability;
        private double threshold;
        private boolean binary;
    }
}
