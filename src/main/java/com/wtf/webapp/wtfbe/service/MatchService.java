package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.MatchResultLookUpRequestDto;
import com.wtf.webapp.wtfbe.dto.MatchResultRequestDto;
import com.wtf.webapp.wtfbe.dto.MatchTypeDto;
import com.wtf.webapp.wtfbe.entity.*;
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

    private final PlayerRepository playerRepository;

    private final String MATCH_ENTITY_NAME = "com.wtf.webapp.wtfbe.entity.MatchResultEntity";

    public List<MatchResultDto> getMatchResult(MatchResultLookUpRequestDto request) throws ClassNotFoundException {
        JPQLParamVO jpqlParamVO = JPQLParamVO.builder().entityName(MATCH_ENTITY_NAME).startIdx(request.getStartIdx()).limit(request.getLimit()).order(request.getOrder()).build();
        List<MatchResultEntity> matchResultEntityResult = jpqlBuilderService.getJQPLResult(jpqlParamVO, MatchResultEntity.class);

        return matchResultEntityResult.stream().map(matchResultEntity -> {
            MatchResultDto matchResultDto = matchResultEntity.convertToDto();
            List<ScoreEntity> scoreEntityList = scoreRepository.findByMatchResultEntity(matchResultEntity);
            List<AssistEntity> assistEntityList = assistRepository.findByMatchResultEntity(matchResultEntity);
            matchResultDto.setScorerAndAssisterDtosByEntities(scoreEntityList, assistEntityList);
            return matchResultDto;
        }).collect(Collectors.toList());
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
        MatchResultEntity matchResultEntity = matchResultRepository.findById(request.getId()).orElseGet(() -> MatchResultEntity.builder().build());
        matchResultEntity.updateEntity(request, teamEntity, matchTypeEntity);
        MatchResultEntity saveResultMathcReusltEntity = matchResultRepository.save(matchResultEntity);

        scoreRepository.deleteAll(scoreRepository.findByMatchResultEntity(matchResultEntity));
        assistRepository.deleteAll(assistRepository.findByMatchResultEntity(matchResultEntity));

        Arrays.stream(request.getScorersAndAssisters()).forEach(ele -> {
            PlayerEntity scorerEntity = playerRepository.findById(ele.getScorerId()).orElse(null);
            ScoreEntity scoreEntity = ScoreEntity.builder().matchResultEntity(matchResultEntity).playerEntity(scorerEntity).goalType(ele.getGoalType()).build();
            scoreRepository.save(scoreEntity);
            PlayerEntity assisterEntity = playerRepository.findById(ele.getAssisterId()).orElse(null);
            AssistEntity assistEntity = AssistEntity.builder().matchResultEntity(matchResultEntity).scoreEntity(scoreEntity).playerEntity(assisterEntity).build();
            assistRepository.save(assistEntity);
        });
        return saveResultMathcReusltEntity.convertToDto();
    }

    @Transactional
    public void deleteMatchResult(int matchResultId) throws EntityNotFoundException {
        MatchResultEntity matchResultEntity = matchResultRepository.findById(matchResultId).orElseThrow(() -> {
            throw new EntityNotFoundException("Match result entity not found for match deletion");
        });
        scoreRepository.deleteAll(scoreRepository.findByMatchResultEntity(matchResultEntity));
        matchResultRepository.delete(matchResultEntity);
    }
}
