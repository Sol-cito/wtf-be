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

    @GetMapping(path = "/player")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        return new ResponseEntity<>(playerService.getAllPlayers(), OK);
    }

    @GetMapping(path = "/player", params = "id")
    public ResponseEntity<PlayerDto> getPlayerById(@RequestParam(value = "id") int id) throws Exception {
        return new ResponseEntity<>(playerService.getPlayerById(id), OK);
    }

    @GetMapping(path = "/player", params = "name")
    public ResponseEntity<List<PlayerDto>> getPlayerByName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(playerService.getPlayerByName(name), OK);
    }

    @GetMapping(path = "/player", params = "position")
    public ResponseEntity<List<PlayerDto>> getPlayerByPosition(@RequestParam(value = "position") String position) {
        return new ResponseEntity<>(playerService.getPlayerByPosition(position), OK);
    }

    @GetMapping(path = "/match")
    public ResponseEntity<List<MatchResultDto>> getMatchResult(
            @RequestParam(value = "startIdx") String startIdx,
            @RequestParam(value = "limit", required = false, defaultValue = "-1") int limit,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order) {
        return new ResponseEntity<>(matchService.getMatchResult(), OK);
    }
}
