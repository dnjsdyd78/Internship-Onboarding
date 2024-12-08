package com.example.Internship.domain.apipayload.status;

import com.example.Internship.domain.apipayload.dto.BaseCode;
import com.example.Internship.domain.apipayload.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {
    _TEST(HttpStatus.BAD_REQUEST, "400", "테스트 오류 발생");

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