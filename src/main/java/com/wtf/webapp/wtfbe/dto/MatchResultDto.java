package com.wtf.webapp.wtfbe.dto;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchResultDto {
    private int id;
    private String opposingTeamName;
    private String matchTypeName;
    private String matchLocation;
    private int goalsScored;
    private int goalsLost;
    private String matchResult;
    private String shootOutYn;
    private Date matchDate;
}
