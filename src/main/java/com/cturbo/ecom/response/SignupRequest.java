package com.cturbo.ecom.response;

import lombok.Builder;

@Builder
public record SignupRequest(String email,
                            String fullName,
                            String otp) {
}
