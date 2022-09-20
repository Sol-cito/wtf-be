package com.wtf.webapp.wtfbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponseDto<T> {
    private boolean successOrNot;
    private HttpStatus statusCode;
    private T data;
}
