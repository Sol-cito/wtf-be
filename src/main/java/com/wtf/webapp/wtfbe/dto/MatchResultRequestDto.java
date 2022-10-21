package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchResultRequestDto {
    private Integer startIdx;
    private Integer limit;
    private QueryOrderDto order;
}


