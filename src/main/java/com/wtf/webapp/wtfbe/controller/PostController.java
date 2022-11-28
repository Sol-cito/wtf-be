package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.*;
import com.wtf.webapp.wtfbe.service.MatchService;
import com.wtf.webapp.wtfbe.service.PlayerService;
import com.wtf.webapp.wtfbe.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PlayerService playerService;
    private final TeamService teamService;
    private final MatchService matchService;

    @PostMapping(path = "/player")
    public ResponseEntity<PlayerDto> registerPlayer(@ModelAttribute PlayerMultipartDto playerMultipartDto) throws Exception {
        PlayerDto result = playerService.registerPlayer(playerMultipartDto).convertToDto();
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping(path = "/team")
    public ResponseEntity<TeamDto> registerTeam(@ModelAttribute TeamMultipartDto teamMultipartDto) throws Exception {
        TeamDto result = teamService.registerTeam(teamMultipartDto).convertToTeamDto();
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping(path = "/match")
    public ResponseEntity<MatchResultDto> registerMatchResult(@RequestBody MatchResultRequestDto request) {
        MatchResultDto result = matchService.handleMatchResult(request);
        return new ResponseEntity<>(result, OK);
    }
}
