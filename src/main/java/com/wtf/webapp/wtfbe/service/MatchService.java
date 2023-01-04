package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.MatchTypeEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.repository.*;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final JPQLBuilderService jpqlBuilderService;
    private final MatchResultRepository matchResultRepository;
    private final MatchTypeRepository matchTypeRepository;
    private final TeamRepository teamRepository;
    private final ScoreRepository scoreRepository;
    private final AssistRepository assistRepository;

    private final String MATCH_ENTITY_NAME = "com.wtf.webapp.wtfbe.entity.MatchResultEntity";

    public List<MatchResultDto> getMatchResult(MatchResultLookUpRequestDto request) throws ClassNotFoundException {
        JPQLParamVO jpqlParamVO = JPQLParamVO.builder().entityName(MATCH_ENTITY_NAME).startIdx(request.getStartIdx()).limit(request.getLimit()).order(request.getOrder()).build();

        List<MatchResultEntity> matchResultEntityResult = jpqlBuilderService.getJQPLResult(jpqlParamVO, MatchResultEntity.class);
        return matchResultEntityResult.stream().map(MatchResultEntity::convertToDto).toList();
    }

    public List<MatchTypeDto> getMatchTypes() {
        return matchTypeRepository.findAll().stream().map(MatchTypeEntity::convertIntoMatchTypeDto).collect(Collectors.toList());
    }

    @Transactional
    public MatchResultDto handleMatchResult(MatchResultRequestDto request) throws EntityNotFoundException {
        TeamEntity teamEntity = teamRepository.findById(request.getOpposingTeamId()).orElseThrow(() -> {
            throw new EntityNotFoundException("Team entity not found for match registration");
        });
        MatchTypeEntity matchTypeEntity = matchTypeRepository.findById(request.getMatchTypeId()).orElseThrow(() -> {
            throw new EntityNotFoundException("Match type entity not found for match registration");
        });
        MatchResultEntity entity = matchResultRepository.findById(request.getId()).orElseGet(() -> MatchResultEntity.builder().build());
        entity.updateEntity(request, teamEntity, matchTypeEntity);
        return matchResultRepository.save(entity).convertToDto();
    }

    @Transactional
    public void deleteMatchResult(int matchResultId) throws EntityNotFoundException {
        MatchResultEntity matchResultEntity = matchResultRepository.findById(matchResultId).orElseThrow(() -> {
            throw new EntityNotFoundException("Match result entity not found for match deletion");
        });
        scoreRepository.deleteAll(scoreRepository.findByMatchResultEntity(matchResultEntity));
        matchResultRepository.delete(matchResultEntity);
    }

    public void handleScorerAndAssister(int matchResultId, ScorerAndAssisterDto[] scorersAndAssisters) {
        Arrays.stream(scorersAndAssisters).forEach(ele -> {

        });
    }
}
