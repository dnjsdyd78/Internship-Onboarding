package com.example.Internship.domain.apipayload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties({"success", "statusCode", "message", "data"})
public class ApiResponse<T> {

    private final Boolean success;

    private final String statusCode;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static <T> ApiResponse<T> onSuccess(T data) {
        return new ApiResponse<>(true, "SuccessStatus", "message", data);
    }

    public static ApiResponse<String> onFailure(BaseCode errorCode) {
        return new ApiResponse<>(false, errorCode.getReasonHttpStatus().getStatusCode(), errorCode.getReasonHttpStatus().getMessage(), "null");
    }
}
