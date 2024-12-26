package com.example.planthealth.repository;

import com.example.planthealth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);  // For login
}
