package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.MatchTypeEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchResultDto {
    private int id;

    private TeamDto opposingTeam;

    private MatchTypeDto matchType;
    private String matchLocation;
    private int goalsScored;
    private int goalsLost;
    private String matchResult;
    private String shootOutYn;
    private String matchDate;
}
