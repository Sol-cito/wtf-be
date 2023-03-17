package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.MatchResultRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "match_result")
public class MatchResultEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "opposing_team_id")
    private TeamEntity teamEntity;

    @OneToOne
    @JoinColumn(name = "match_type_id")
    private MatchTypeEntity matchTypeEntity;

    private String matchLocation;

    private int goalsScored;

    private int goalsLost;

    private String matchResult;

    private String shootOutYn;

    private String matchDate;

    @OneToMany(mappedBy = "matchResultEntity")
    private List<ScoreEntity> scoreEntities;

    @OneToMany(mappedBy = "matchResultEntity")
    private List<AssistEntity> assistEntities;

    public MatchResultDto convertToDto() {
        return MatchResultDto.builder()
                .id(this.id)
                .opposingTeam(this.teamEntity.convertToTeamDto())
                .matchType(this.matchTypeEntity.convertIntoMatchTypeDto())
                .matchLocation(this.matchLocation)
                .goalsScored(this.goalsScored)
                .goalsLost(this.goalsLost)
                .matchResult(this.matchResult)
                .shootOutYn(this.shootOutYn)
                .matchDate(this.matchDate)
                .build();
    }

    public void updateEntity(MatchResultRequestDto dto, TeamEntity teamEntity, MatchTypeEntity matchTypeEntity) {
        this.teamEntity = teamEntity;
        this.matchTypeEntity = matchTypeEntity;
        this.matchLocation = dto.getMatchLocation();
        this.goalsScored = dto.getGoalsScored();
        this.goalsLost = dto.getGoalsLost();
        this.matchResult = dto.getMatchResult();
        this.shootOutYn = dto.getShootOutYn();
        this.matchDate = dto.getMatchDate();
    }
}
