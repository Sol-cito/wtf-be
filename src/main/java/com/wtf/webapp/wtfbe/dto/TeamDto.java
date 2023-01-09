package com.wtf.webapp.wtfbe.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TeamDto {
    private int id;
    private String name;
    private String hometown;
    private String teamLogoSrc;
}
