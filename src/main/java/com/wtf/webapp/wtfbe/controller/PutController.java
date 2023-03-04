package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.service.MatchService;
import com.wtf.webapp.wtfbe.service.PlayerService;
import com.wtf.webapp.wtfbe.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PutController {
    private final PlayerService playerService;
    private final MatchService matchService;
    private final TeamService teamService;

    @PutMapping(path = "/player")
    public ResponseEntity<PlayerDto> modifyPlayer(@ModelAttribute PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerDto result = playerService.modifyPlayer(playerMultipartDto).convertToDto();
        return new ResponseEntity<>(result, OK);
    }

    @PutMapping(path = "/match")
    public ResponseEntity<MatchResultDto> modifyPlayer(@RequestBody MatchResultRequestDto request) {
        MatchResultDto result = matchService.handleMatchResult(request);
        return new ResponseEntity<>(result, OK);
    }

    @PutMapping(path = "/team")
    public ResponseEntity<TeamDto> modifyTeam(@ModelAttribute TeamMultipartDto request) throws Exception {
        TeamDto result = teamService.modifyTeam(request).convertToTeamDto();
        return new ResponseEntity<>(result, OK);
    }
}
