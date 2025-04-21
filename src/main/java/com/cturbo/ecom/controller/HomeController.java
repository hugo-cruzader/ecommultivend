package com.cturbo.ecom.controller;

import com.cturbo.ecom.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeControllerHandler() {
        return ApiResponse.builder()
                .message("Welcome to ecommerce multivendor system Hugo!")
                .build();
    }
}
