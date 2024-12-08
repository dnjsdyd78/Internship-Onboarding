package com.example.Internship.domain.apipayload.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@RequiredArgsConstructor
public class ReasonDto {

    private final String statusCode;
    private final String message;
    private final HttpStatus httpStatus;
    private final Boolean success;
}