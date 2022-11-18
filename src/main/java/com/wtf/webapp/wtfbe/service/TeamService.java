package com.wtf.webapp.wtfbe.service;

import com.wtf.webapp.wtfbe.dto.MultipartImageFileDto;
import com.wtf.webapp.wtfbe.dto.TeamDto;
import com.wtf.webapp.wtfbe.dto.TeamMultipartDto;
import com.wtf.webapp.wtfbe.entity.TeamEntity;
import com.wtf.webapp.wtfbe.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final UtilService utilService;

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream().map(TeamEntity::convertToTeamDto).toList();
    }

    @Transactional
    public TeamEntity registerTeam(TeamMultipartDto teamMultipartDto) throws Exception {
        TeamEntity teamEntity = teamMultipartDto.convertIntoTeamEntity();
        if (teamMultipartDto.getImage() != null) {
            if (teamMultipartDto.isTeamLogoImageNotEmpty()) {
                MultipartImageFileDto teamLogoMultipartDto = teamMultipartDto.getTeamLogoMultipartDto();
                utilService.transferImageFile(teamLogoMultipartDto);
                teamEntity.setTeamLogoSrc(teamLogoMultipartDto.getFullFileName());
            }
        }
        return teamRepository.save(teamEntity);
    }
}
