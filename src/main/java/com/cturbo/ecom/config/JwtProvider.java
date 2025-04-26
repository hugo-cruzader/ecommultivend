package com.cturbo.ecom.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtProvider {
    final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(final Authentication auth) {
        final Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        final String roles = populateAuthorities(authorities);
        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 8640000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
    }

    public String getEmailFromJwtToken(@NonNull final String jwt) {
        final Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt.substring(7)).getPayload();
        return String.valueOf(claims.get("email"));
    }

    private String populateAuthorities(final Collection<? extends GrantedAuthority> authorities) {
        final Set<String> auths = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return String.join(",", auths);
    }
}
