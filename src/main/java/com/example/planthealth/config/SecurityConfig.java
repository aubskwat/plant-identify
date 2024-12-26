package com.example.planthealth.config;

import com.example.planthealth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF (consider enabling it for production)
                .headers()
                .frameOptions().disable() // Disable X-Frame-Options for H2 console
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/register", "/auth/login", "/", "/error").permitAll() // Allow public access to
                                                                                             // these
                .requestMatchers("/h2-console/**").permitAll() // Allow H2 Console access for dev environment (local
                                                               // only)
                .anyRequest().authenticated() // Protect all other endpoints
                .and()
                .formLogin()
                .loginPage("/auth/login") // Custom login page
                .permitAll()
                .defaultSuccessUrl("/plant/identify", true) // Redirect to user details page after login
                .and()
                .logout()
                .logoutUrl("/auth/logout") // Set the logout URL
                .logoutSuccessUrl("/") // Redirect to the homepage after logout
                .invalidateHttpSession(true) // Invalidate the session
                .clearAuthentication(true) // Clear the authentication
                .deleteCookies("JSESSIONID"); // Remove the session cookie // Allow logout from anywhere

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.example.planthealth.model.User user = userService.getUserDetails(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER") // Assume all users are 'USER'. You can add more roles if needed
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encryption
    }
}
