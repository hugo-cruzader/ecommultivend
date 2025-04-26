package com.cturbo.ecom.service;

import com.cturbo.ecom.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest request);
}
