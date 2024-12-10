package com.example.Internship.domain.apipayload.status;

import com.example.Internship.domain.apipayload.dto.BaseCode;
import com.example.Internship.domain.apipayload.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {

    // auth
    _EXIST_USERNAME(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 사용자 아이디 입니다."),
    _EXIST_NICKNAME(HttpStatus.BAD_REQUEST, "400", "이미 존재하는 닉네임 입니다."),
    _SIGN_IN_ERROR(HttpStatus.BAD_REQUEST,  "400","로그인에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String message;


    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .httpStatus(httpStatus)
                .statusCode(statusCode)
                .message(message)
                .success(false)
                .build();
    }
}