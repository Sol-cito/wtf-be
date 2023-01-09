package com.wtf.webapp.wtfbe.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchResultRequestDto {
    private int id;
    private int opposingTeamId;
    private int matchTypeId;
    private String matchLocation;
    private int goalsScored;
    private ScorerAndAssisterDto[] scorersAndAssisters;
    private int goalsLost;
    private String matchResult;
    private String shootOutYn;
    private String matchDate;
}
