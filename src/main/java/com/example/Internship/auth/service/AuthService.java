package com.example.Internship.auth.service;

import com.example.Internship.auth.dto.request.SigninRequest;
import com.example.Internship.auth.dto.request.SignupRequest;
import com.example.Internship.auth.dto.response.SignupResponse;

public interface AuthService {
    SignupResponse signup(SignupRequest signupRequest);

    String signin(SigninRequest signinRequest);
}
