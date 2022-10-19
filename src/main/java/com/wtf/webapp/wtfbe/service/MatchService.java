package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.entity.MatchResultEntity;
import com.wtf.webapp.wtfbe.repository.MatchResultRepository;
import com.wtf.webapp.wtfbe.vo.JPQLParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchResultRepository matchResultRepository;
    private final JPQLBuilderService jpqlBuilderService;

    private final String MATCH_ENTITY_NAME = "com.wtf.webapp.wtfbe.entity.MatchResultEntity";

    public List<MatchResultDto> getMatchResult(Integer startIdx, Integer limit, String order) throws ClassNotFoundException {
        JPQLParamVO jpqlParamVO = JPQLParamVO.builder()
                .entityName(MATCH_ENTITY_NAME)
                .startIdx(startIdx)
                .limit(limit)
                .order(order)
                .build();
        List<MatchResultEntity> matchResultEntityResult = jpqlBuilderService.getJQPLResult(jpqlParamVO);

        return matchResultEntityResult.stream().map(entity -> entity.convertToDto()).collect(Collectors.toList());
    }
}
