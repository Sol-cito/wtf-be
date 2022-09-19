package com.wtf.webapp.wtfbe.controller;

import com.wtf.webapp.wtfbe.entity.PlayerEntity;
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

    @GetMapping(path = "/health")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Hello WTF!", OK);
    }

    @GetMapping(path = "/profile")
    public String getCurrentRunningProfile() {
        return Arrays.asList(env.getActiveProfiles()).stream().findFirst().orElse("");
    }

    @GetMapping(path = "/player")
    public ResponseEntity<String> getPlayerByName() {
        return new ResponseEntity<>("Hello WTF!", OK);
    }

    @GetMapping(path = "/player/all")
    public ResponseEntity<String> getAllPlayers() {
        return new ResponseEntity(playerService.getAllPlayer(), OK);
    }
}
