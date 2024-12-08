package com.example.Internship.domain.apipayload.status;

import com.example.Internship.domain.apipayload.dto.BaseCode;
import com.example.Internship.domain.apipayload.dto.ReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessStatus implements BaseCode {

    _OK(HttpStatus.OK, "200", "Ok");

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String message;


    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .httpStatus(httpStatus)
                .statusCode("200")
                .message("Ok")
                .success(true)
                .build();
    }
}