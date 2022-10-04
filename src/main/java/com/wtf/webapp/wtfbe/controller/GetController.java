package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.dto.MatchResultDto;
import com.wtf.webapp.wtfbe.dto.PlayerDto;
import com.wtf.webapp.wtfbe.service.MatchService;
import com.wtf.webapp.wtfbe.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class GetController {
    private final Environment env;
    private final PlayerService playerService;

    private final MatchService matchService;

    @GetMapping(path = "/health")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Hello WTF!", OK);
    }

    @GetMapping(path = "/profile")
    public String getCurrentRunningProfile() {
        return Arrays.asList(env.getActiveProfiles()).stream().findFirst().orElse("");
    }

    @GetMapping(path = "/player", params = "id")
    public ResponseEntity<PlayerDto> getPlayerById(@RequestParam(value = "id") int id) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerById(id), OK);
    }

    @GetMapping(path = "/player", params = "name")
    public ResponseEntity<List<PlayerDto>> getPlayerByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(playerService.getPlayerByName(name), OK);
    }

    @GetMapping(path = "/player")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), OK);
    }

    @GetMapping(path = "/match")
    public ResponseEntity<List<MatchResultDto>> getAllMatch() {
        return new ResponseEntity<>(matchService.getAllMatchResult(), OK);
    }

    @GetMapping(path = "/match/current")
    public ResponseEntity<List<MatchResultDto>> getCurrentMatch() {
        return new ResponseEntity<>(matchService.getCurrentMatchResults(), OK);
    }
}
