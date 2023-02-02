package com.wtf.webapp.wtfbe.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PlayerStatDto {
    private int scores;
    private int assists;
}
