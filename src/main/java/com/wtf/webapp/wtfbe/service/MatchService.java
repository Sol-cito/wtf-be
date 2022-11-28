package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.MatchTypeEntity;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.repository.MatchResultRepository;
import com.wtf.webapp.wtfbe.repository.MatchTypeRepository;
import com.wtf.webapp.wtfbe.repository.TeamRepository;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final JPQLBuilderService jpqlBuilderService;
    private final MatchResultRepository matchResultRepository;
    private final MatchTypeRepository matchTypeRepository;

    private final TeamRepository teamRepository;

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
    public MatchResultDto handleMatchResult(MatchResultRequestDto request) {
        TeamEntity teamEntity = teamRepository.findById(request.getOpposingTeamId()).orElseThrow(() -> {
            // TO-DO : switch to more accurate exception
            throw new RuntimeException("Team entity not found for match registration");
        });
        MatchTypeEntity matchTypeEntity = matchTypeRepository.findById(request.getMatchTypeId()).orElseThrow(() -> {
            // TO-DO : switch to more accurate exception
            throw new RuntimeException("Match type entity not found for match registration");
        });
        MatchResultEntity entity = matchResultRepository.findById(request.getId()).orElseGet(() -> MatchResultEntity.builder().build());
        entity.updateEntity(request, teamEntity, matchTypeEntity);
        return matchResultRepository.save(entity).convertToDto();
    }
}
