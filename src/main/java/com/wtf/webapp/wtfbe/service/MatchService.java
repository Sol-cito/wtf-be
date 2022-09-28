package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.repository.MatchResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchResultRepository matchRepository;

    public List<MatchResultDto> getAllMatches() {
        List<MatchResultDto> result = new ArrayList<>();
        matchRepository.findAll().stream().forEach(entity -> result.add(entity.convertToDto()));
        return result;
    }
}
