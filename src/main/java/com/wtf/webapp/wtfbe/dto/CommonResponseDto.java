package com.wtf.webapp.wtfbe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommonResponseDto<T> {
    private boolean successOrNot;
    private HttpStatus statusCode;
    private T data;
}
