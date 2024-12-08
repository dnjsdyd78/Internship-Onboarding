package com.example.Internship.auth.controller;

import com.example.Internship.auth.dto.request.SigninRequest;
import com.example.Internship.auth.dto.request.SignupRequest;
import com.example.Internship.auth.dto.response.SigninResponse;
import com.example.Internship.auth.dto.response.SignupResponse;
import com.example.Internship.auth.service.AuthService;
import com.example.Internship.domain.apipayload.dto.ApiResponse;
import com.example.Internship.domain.apipayload.status.SuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입
     *
     * @param signupRequest 회원가입 시 필요한 body ( 이메일, 비밀번호, 닉네임, 주소, 사용자 권한 )
     * @return Dto(이름,닉네임,권한)
     */
    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {

        SignupResponse response = authService.signup(signupRequest);
        return ApiResponse.onSuccess(response);

    }

    /**
     * 로그인
     *
     * @param signinRequest 로그인 시 필요한 body ( 이름, 비밀번호 )
     * @return Dto(토큰)
     */
    @PostMapping("/signin")
    public ApiResponse<SigninResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {

        String token = authService.signin(signinRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        SigninResponse response = new SigninResponse(token);

        return ApiResponse.onSuccess(response);
    }
}