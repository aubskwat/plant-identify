package com.example.planthealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Add this annotation to register the class as a Spring MVC controller
public class PageController {

    @GetMapping("/")
    public String showHomePage() {
        return "home"; // Return the home page view (home.html)
    }
}
