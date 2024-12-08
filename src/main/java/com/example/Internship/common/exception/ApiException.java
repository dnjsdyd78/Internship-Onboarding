package com.example.Internship.common.exception;

import com.example.Internship.domain.apipayload.dto.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private final BaseCode errorCode;
}
