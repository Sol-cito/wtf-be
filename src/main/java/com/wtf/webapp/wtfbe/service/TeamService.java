package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.TeamDto;
import com.wtf.webapp.wtfbe.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream().map(entity -> entity.convertToTeamDto()).toList();
    }
}
