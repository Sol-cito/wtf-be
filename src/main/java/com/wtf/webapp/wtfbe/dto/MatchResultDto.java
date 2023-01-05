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

    public void setScorerAndAssisterDtosByEntities(List<ScoreEntity> scoreEntityList, List<AssistEntity> assistEntityList) {
        this.scorersAndAssisters = new ScorerAndAssisterDto[scoreEntityList.size()];
        for (int i = 0; i < scoreEntityList.size(); i++) {
            PlayerEntity scorerEntity = scoreEntityList.get(i).getPlayerEntity();
            PlayerEntity assisterEntity = assistEntityList.get(i).getPlayerEntity();
            ScorerAndAssisterDto scorerAndAssisterDto = ScorerAndAssisterDto.builder()
                    .index(i)
                    .scorerId(scorerEntity == null ? -1 : scorerEntity.getId())
                    .goalType(scoreEntityList.get(i).getGoalType())
                    .assisterId(assisterEntity == null ? -1 : assisterEntity.getId())
                    .build();
            this.scorersAndAssisters[i] = scorerAndAssisterDto;
        }
    }
}
