package com.cturbo.ecom.controller;

import com.cturbo.ecom.domain.UserRole;
import com.cturbo.ecom.model.User;
import com.cturbo.ecom.repository.UserRepository;
import com.cturbo.ecom.response.AuthResponse;
import com.cturbo.ecom.response.SignupRequest;
import com.cturbo.ecom.service.AuthService;
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
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) {
//     Create user in model without authorization, removed once authorization is set up
//        final User user = new User();
//        user.setEmail(req.email());
//        user.setFullName(req.fullName());
//        final User savedUser = userRepository.save(user);

        final String jwt = authService.createUser(req);

        return ResponseEntity.ok(AuthResponse.builder().jwt(jwt).message("Register success").role(UserRole.CUSTOMER).build());
    }
}
