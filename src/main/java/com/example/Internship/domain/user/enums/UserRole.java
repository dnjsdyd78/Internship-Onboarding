package com.example.Internship.domain.user.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_USER(Authority.USER),
    ROLE_ADMIN(Authority.ADMIN);

    private final String userRole;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException()
                );
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE) // 무단으로 생성 방지
    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }

}
