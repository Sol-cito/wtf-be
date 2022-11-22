package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.MatchResultRequestDto;
import com.wtf.webapp.wtfbe.dto.MatchTypeDto;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.entity.MatchTypeEntity;
import com.wtf.webapp.wtfbe.repository.MatchTypeRepository;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final JPQLBuilderService jpqlBuilderService;
    private final MatchTypeRepository matchTypeRepository;

    private final String MATCH_ENTITY_NAME = "com.wtf.webapp.wtfbe.entity.MatchResultEntity";

    public List<MatchResultDto> getMatchResult(MatchResultRequestDto request) throws ClassNotFoundException {
        JPQLParamVO jpqlParamVO = JPQLParamVO.builder()
                .entityName(MATCH_ENTITY_NAME)
                .startIdx(request.getStartIdx())
                .limit(request.getLimit())
                .order(request.getOrder())
                .build();
        List<MatchResultEntity> matchResultEntityResult = jpqlBuilderService.getJQPLResult(jpqlParamVO);
        return matchResultEntityResult.stream().map(MatchResultEntity::convertToDto).toList();
    }

    public List<MatchTypeDto> getMatchTypes(){
        return matchTypeRepository.findAll().stream().map(MatchTypeEntity::convertIntoMatchTypeDto).collect(Collectors.toList());
    }
}
