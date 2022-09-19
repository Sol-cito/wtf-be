package com.wtf.webapp.wtfbe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class GetController {
    private final Environment env;

    @GetMapping(path = "/health")
    public ResponseEntity<String> checkHealth() {
        return new ResponseEntity<>("Hello WTF!", OK);
    }

    @GetMapping(path = "/getProfile")
    public String getCurrentRunningProfile() {
        Arrays.asList(env.getActiveProfiles()).stream().forEach(profile -> System.out.println(profile));


        return Arrays.asList(env.getActiveProfiles()).stream().findFirst().orElse("");
    }
}
