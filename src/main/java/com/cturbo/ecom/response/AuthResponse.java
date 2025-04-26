package com.cturbo.ecom.response;

import com.cturbo.ecom.domain.UserRole;
import lombok.Builder;

@Builder
public record AuthResponse(String jwt, String message, UserRole role) {
}
