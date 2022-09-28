package com.wtf.webapp.wtfbe.entity;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "match_result")
public class MatchResultEntity {
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

    private Date matchDate;

    public MatchResultDto convertToDto() {
        return MatchResultDto.builder()
                .id(this.id)
                .opposingTeamName(this.teamEntity.getName())
                .matchTypeName(this.matchTypeEntity.getMatchTypeName())
                .matchLocation(this.matchLocation)
                .goalsScored(this.goalsScored)
                .goalsLost(this.goalsLost)
                .matchResult(this.matchResult)
                .shootOutYn(this.shootOutYn)
                .matchDate(this.matchDate)
                .build();
    }
}
