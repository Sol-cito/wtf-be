package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.MatchResultRequestDto;
import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.dto.PlayerMultipartDto;
import com.wtf.webapp.wtfbe.service.MatchService;
import com.wtf.webapp.wtfbe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class PutController {
    private final PlayerService playerService;
    private final MatchService matchService;

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
}
