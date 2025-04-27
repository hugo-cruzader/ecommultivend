package com.cturbo.ecom.service.impl;

import com.cturbo.ecom.domain.UserRole;
import com.cturbo.ecom.model.Seller;
import com.cturbo.ecom.model.User;
import com.cturbo.ecom.repository.SellerRepository;
import com.cturbo.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private static final String SELLER_PREFIX = "seller_";

    //TODO: Needs refactoring
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if(username.startsWith(SELLER_PREFIX)) {
            final String actualUsername = username.substring(SELLER_PREFIX.length());
            final Seller seller = sellerRepository.findByEmail(actualUsername);
            if (seller !=  null) {
                return buildUserDetails(seller.getEmail(), seller.getPassword(), seller.getRole());
            }
            throw new UsernameNotFoundException("User or seller not found with email - " + username);
        }
        final User user = userRepository.findByEmail(username);
        if (user !=null) {
            return buildUserDetails(user.getEmail(), user.getPassword(),  user.getRole());
        }
        throw new UsernameNotFoundException("User or seller not found with email - " + username);
    }

    private UserDetails buildUserDetails(final String email, final String password, UserRole role) {
        final List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + Optional.ofNullable(role).orElse(UserRole.CUSTOMER)));
        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }
}
