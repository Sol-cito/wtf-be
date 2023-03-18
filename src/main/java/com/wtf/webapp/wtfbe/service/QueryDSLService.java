package com.wtf.webapp.wtfbe.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wtf.webapp.wtfbe.dto.PlayerMatchStatDto;
import com.wtf.webapp.wtfbe.dto.PlayerRecentStatDto;
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

    public List<PlayerRecentStatDto> getPlayerRecentScore(int playerId, int limit) {
        QMatchResultEntity qMresult = QMatchResultEntity.matchResultEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;
        QScoreEntity qScore = QScoreEntity.scoreEntity;
        QMatchTypeEntity qMatchType = QMatchTypeEntity.matchTypeEntity;

        List<Tuple> recentStatResult = queryFactory
                .select(qMresult.matchDate,
                        qTeam.name,
                        qMresult.matchLocation,
                        qMatchType.matchTypeName,
                        qScore.playerEntity.id.
                                when(playerId).then(1).
                                otherwise(0).sum()
                )
                .from(qMresult)
                .join(qMresult.scoreEntities, qScore)
                .join(qMresult.teamEntity, qTeam)
                .join(qMresult.matchTypeEntity, qMatchType)
                .groupBy(qMresult)
                .orderBy(qMresult.matchDate.asc())
                .limit(limit)
                .fetch();
        return recentStatResult.stream().map(PlayerRecentStatDto::convertIntoPlayerRecentStatDto).collect(Collectors.toList());
    }

    public List<PlayerRecentStatDto> getPlayerRecentAssist(int playerId, int limit) {
        QMatchResultEntity qMresult = QMatchResultEntity.matchResultEntity;
        QTeamEntity qTeam = QTeamEntity.teamEntity;
        QAssistEntity qAssist = QAssistEntity.assistEntity;
        QMatchTypeEntity qMatchType = QMatchTypeEntity.matchTypeEntity;

        List<Tuple> recentStatResult = queryFactory
                .select(qMresult.matchDate,
                        qTeam.name,
                        qMresult.matchLocation,
                        qMatchType.matchTypeName,
                        qAssist.playerEntity.id.
                                when(playerId).then(1).
                                otherwise(0).sum()
                )
                .from(qMresult)
                .join(qMresult.assistEntities, qAssist)
                .join(qMresult.teamEntity, qTeam)
                .join(qMresult.matchTypeEntity, qMatchType)
                .groupBy(qMresult)
                .orderBy(qMresult.matchDate.asc())
                .limit(limit)
                .fetch();
        return recentStatResult.stream().map(PlayerRecentStatDto::convertIntoPlayerRecentStatDto).collect(Collectors.toList());
    }
}

