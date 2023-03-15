package com.wtf.webapp.wtfbe.dto;

import com.wtf.webapp.wtfbe.entity.AssistEntity;
import com.wtf.webapp.wtfbe.entity.PlayerEntity;
import com.wtf.webapp.wtfbe.entity.ScoreEntity;
import lombok.*;

import java.util.List;

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
    private ScorerAndAssisterDto[] scorersAndAssisters;
    private int goalsLost;
    private String matchResult;
    private String shootOutYn;
    private String matchDate;

    public MatchResultDto setScorerAndAssisterDtosByEntities(List<ScoreEntity> scoreEntities, List<AssistEntity> assistEntities) {
        this.scorersAndAssisters = new ScorerAndAssisterDto[scoreEntities.size()];
        for (int i = 0; i < scoreEntities.size(); i++) {
            PlayerEntity scorerEntity = scoreEntities.get(i).getPlayerEntity();
            PlayerEntity assisterEntity = assistEntities.get(i).getPlayerEntity();
            ScorerAndAssisterDto scorerAndAssisterDto = ScorerAndAssisterDto.builder()
                    .index(i)
                    .scorer(scorerEntity == null ? null : scorerEntity.convertToDto())
                    .goalType(scoreEntities.get(i).getGoalType())
                    .assister(assisterEntity == null ? null : assisterEntity.convertToDto())
                    .build();
            this.scorersAndAssisters[i] = scorerAndAssisterDto;
        }
        return this;
    }
}
