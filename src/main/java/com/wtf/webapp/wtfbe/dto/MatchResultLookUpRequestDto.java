package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchResultLookUpRequestDto {
    private Integer startIdx;
    private Integer limit;
    private QueryOrderDto order;
}


