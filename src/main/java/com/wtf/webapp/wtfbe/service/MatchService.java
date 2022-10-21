package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.MatchResultRequestDto;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final JPQLBuilderService jpqlBuilderService;

    private final String MATCH_ENTITY_NAME = "com.wtf.webapp.wtfbe.entity.MatchResultEntity";

    public List<MatchResultDto> getMatchResult(MatchResultRequestDto request) throws ClassNotFoundException {
        JPQLParamVO jpqlParamVO = JPQLParamVO.builder()
                .entityName(MATCH_ENTITY_NAME)
                .startIdx(request.getStartIdx())
                .limit(request.getLimit())
                .order(request.getOrder())
                .build();
        List<MatchResultEntity> matchResultEntityResult = jpqlBuilderService.getJQPLResult(jpqlParamVO);
        return matchResultEntityResult.stream().map(entity -> entity.convertToDto()).toList();
    }
}
