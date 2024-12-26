package com.example.planthealth.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response) {
        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return "error/404";  // This will look for templates/error/404.html
        }
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            return "error/403";  // This will look for templates/error/403.html
        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "error/500";  // This will look for templates/error/500.html
        }
        return "error/error";    // Default error page for other errors
    }

    
}