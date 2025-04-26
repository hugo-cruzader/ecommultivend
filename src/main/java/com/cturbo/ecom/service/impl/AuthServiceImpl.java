package com.cturbo.ecom.service.impl;

import com.cturbo.ecom.config.JwtProvider;
import com.cturbo.ecom.domain.UserRole;
import com.cturbo.ecom.model.Cart;
import com.cturbo.ecom.model.User;
import com.cturbo.ecom.repository.CartRepository;
import com.cturbo.ecom.repository.UserRepository;
import com.cturbo.ecom.response.SignupRequest;
import com.cturbo.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;

    @Override
    public String createUser(final SignupRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (user == null) {
            final User createdUser = new User();
            createdUser.setEmail(request.email());
            createdUser.setFullName(request.fullName());
            createdUser.setRole(UserRole.CUSTOMER);
            createdUser.setMobile("7775559999");
            createdUser.setPassword(passwordEncoder.encode(request.otp()));
            user = userRepository.save(createdUser);
            final Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        final List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(UserRole.CUSTOMER.name()));
        final Authentication authentication = new UsernamePasswordAuthenticationToken(request.email(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtProvider.generateToken(authentication);
    }
}
