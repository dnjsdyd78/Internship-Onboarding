package com.example.Internship.auth.dto.response;

import lombok.Getter;

@Getter
public class SigninResponse {
    private final String token;

    public SigninResponse(String token) {
        this.token = token;
    }
}
