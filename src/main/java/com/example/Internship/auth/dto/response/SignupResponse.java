package com.example.Internship.auth.dto.response;

import com.example.Internship.domain.user.enums.UserRole;
import lombok.Getter;

@Getter
public class SignupResponse {
    private final String username;
    private final String nickname;
    private final UserRole role;

    public SignupResponse(String username, String nickname, UserRole role) {
        this.username = username;
        this.nickname = nickname;
        this.role = role;
    }
}
