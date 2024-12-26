package com.example.planthealth.controller;

import com.example.planthealth.model.User;
import com.example.planthealth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Show registration form
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/auth/login"; // Redirect to login after successful registration
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Handle login form submission (Spring Security will handle the authentication)
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.verifyLogin(username, password);
        if (user != null) {
            return "redirect:/plant/identify"; // Redirect to user details after login
        }
        return "redirect:/auth/login?error"; // Show error if login fails
    }

    // Show user details
    @GetMapping("/user-details")
    public String getUserDetails(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged-in username
        User user = userService.getUserDetails(username);
        model.addAttribute("user", user); // Add user to the model
        return "user-details"; // Display user details in the view
    }

}
