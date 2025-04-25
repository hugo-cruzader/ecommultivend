package com.cturbo.ecom.controller;

import com.cturbo.ecom.model.User;
import com.cturbo.ecom.repository.UserRepository;
import com.cturbo.ecom.response.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req) {
        // Create user in model
        final User user = new User();
        user.setEmail(req.email());
        user.setFullName(req.fullName());
        final User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
