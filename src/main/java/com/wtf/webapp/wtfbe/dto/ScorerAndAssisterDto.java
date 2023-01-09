package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ScorerAndAssisterDto {
    private int index;
    private int scorerId;
    private String goalType;
    private int assisterId;
}
