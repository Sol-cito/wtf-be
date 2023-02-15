package com.wtf.webapp.wtfbe.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wtf.webapp.wtfbe.dto.PlayerMatchStatDto;
import com.wtf.webapp.wtfbe.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryDSLService {
    private final JPAQueryFactory queryFactory;

    public List<PlayerMatchStatDto> getPlayerScoresByMatchResult(int playerId, int limit) {
        QMatchResultEntity qMresult = QMatchResultEntity.matchResultEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;
        QMatchTypeEntity qMtype = QMatchTypeEntity.matchTypeEntity;
        QScoreEntity qScore = QScoreEntity.scoreEntity;

        List<Tuple> scoreResult = queryFactory
                .select(qMresult.matchDate,
                        qMtype.matchTypeName,
                        qTeam.name,
                        qTeam.teamLogoSrc,
                        qMresult.id.count()
                )
                .from(qMresult)
                .where(qScore.playerEntity.id.eq(playerId))
                .join(qMresult.teamEntity, qTeam)
                .join(qMresult.matchTypeEntity, qMtype)
                .join(qScore).on(qMresult.eq(qScore.matchResultEntity))
                .groupBy(qMresult)
                .orderBy(qMresult.matchDate.desc())
                .limit(limit)
                .fetch();
        return scoreResult.stream().map(PlayerMatchStatDto::convertIntoPlayerMatchDto).collect(Collectors.toList());
    }

    public List<PlayerMatchStatDto> getPlayerAssistsByMatchResult(int playerId, int limit) {
        QMatchResultEntity qMresult = QMatchResultEntity.matchResultEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;
        QMatchTypeEntity qMtype = QMatchTypeEntity.matchTypeEntity;
        QAssistEntity qAssist = QAssistEntity.assistEntity;

        List<Tuple> assistResult = queryFactory
                .select(qMresult.matchDate,
                        qMtype.matchTypeName,
                        qTeam.name,
                        qTeam.teamLogoSrc,
                        qMresult.id.count()
                )
                .from(qMresult)
                .where(qAssist.playerEntity.id.eq(playerId))
                .join(qMresult.teamEntity, qTeam)
                .join(qMresult.matchTypeEntity, qMtype)
                .join(qAssist).on(qMresult.eq(qAssist.matchResultEntity))
                .groupBy(qMresult)
                .orderBy(qMresult.matchDate.desc())
                .limit(limit)
                .fetch();
        return assistResult.stream().map(PlayerMatchStatDto::convertIntoPlayerMatchDto).collect(Collectors.toList());
    }
}

