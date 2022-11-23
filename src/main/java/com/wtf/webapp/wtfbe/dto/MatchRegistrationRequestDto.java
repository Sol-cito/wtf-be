package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.MatchTypeEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MatchRegistrationRequestDto {
    private int id;
    private int opposingTeamId;
    private int matchTypeId;
    private String matchLocation;
    private int goalsScored;
    private int goalsLost;
    private String matchResult;
    private String shootOutYn;
    private String matchDate;
}
